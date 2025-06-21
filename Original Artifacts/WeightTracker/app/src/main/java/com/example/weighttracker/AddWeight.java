package com.example.weighttracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.weighttracker.databinding.ActivityAddWeightBinding;

import java.util.Calendar;

public class AddWeight extends AppCompatActivity {

    DatePicker datePicker;
    WeightDatabaseHelper dbHelper;
    SharedPreferences sharedPreferences;
    ActivityAddWeightBinding binding;
    String date;
    String weight;
    String dateYear;
    String dateMonth;
    String dateDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new WeightDatabaseHelper(this);
        binding = ActivityAddWeightBinding.inflate(getLayoutInflater());
        datePicker = binding.dateDatePicker;
        weight = binding.addWeightEditText.getText().toString();
        Calendar today = Calendar.getInstance();
        dateYear = String.valueOf(today.get(Calendar.YEAR));
        dateMonth = String.valueOf(today.get(Calendar.MONTH));
        dateDay = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
        date = dateYear + "-" + dateMonth + "-" + dateDay;

        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userID = sharedPreferences.getString("loggedInUser", "defaultUser");
        String goalWeight = sharedPreferences.getString(userID + "_goalWeight", "Not set");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        datePicker.init(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateYear = String.valueOf(year);
                        dateMonth = String.valueOf(monthOfYear+1);
                        dateDay = String.valueOf(dayOfMonth);
                        date = dateYear + "-" + dateMonth + "-" + dateDay;
                    }
                });


        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight = binding.addWeightEditText.getText().toString();
                date = dateYear + "-" + dateMonth + "-" + dateDay;

                // Check if the weight input is valid
                if (!weight.isEmpty()) {
                    boolean insert = dbHelper.insertData(userID, date, weight, goalWeight);
                    if (insert) {
                        Toast.makeText(AddWeight.this, "Weight added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AddWeight.this, "Weight cannot be added.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddWeight.this, "Please enter a valid weight.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}