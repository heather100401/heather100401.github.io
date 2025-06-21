package com.example.weighttracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.weighttracker.databinding.ActivityDeleteWeightBinding;

import java.util.Calendar;

public class DeleteWeight extends AppCompatActivity {

    DatePicker datePicker;
    WeightDatabaseHelper dbHelper;
    ActivityDeleteWeightBinding binding;
    String date;
    String dateYear;
    String dateMonth;
    String dateDay;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new WeightDatabaseHelper(this);
        binding = ActivityDeleteWeightBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        datePicker = binding.deleteDateDatePicker;
        Calendar today = Calendar.getInstance();
        dateYear = String.valueOf(today.get(Calendar.YEAR));
        dateMonth = String.valueOf(today.get(Calendar.MONTH) + 1);
        dateDay = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        date = dateYear + "-" + dateMonth + "-" + dateDay;

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userID = sharedPreferences.getString("loggedInUser", null);

        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        datePicker.init(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),
                (view, year, monthOfYear, dayOfMonth) -> {
                    dateYear = String.valueOf(year);
                    dateMonth = String.valueOf(monthOfYear + 1);
                    dateDay = String.valueOf(dayOfMonth);
                    date = dateYear + "-" + dateMonth + "-" + dateDay;
                });

        binding.deleteButton.setOnClickListener(v -> {
            if (userID != null) {
                date = dateYear + "-" + dateMonth + "-" + dateDay;
                boolean delete = dbHelper.deleteData(userID, date);

                if (delete) {
                    Toast.makeText(DeleteWeight.this, "Weight deleted", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK); // Successful deletion
                    finish();
                } else {
                    Toast.makeText(DeleteWeight.this, "No weight entry found for this date.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(DeleteWeight.this, "User not logged in.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.cancelButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}
