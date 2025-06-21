package com.example.weighttracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addWeight;
    FloatingActionButton deleteWeight;
    WeightDatabaseHelper dbHelper;
    SharedPreferences sharedPreferences;
    ListView weightListView;
    Cursor cursor;
    WeightAdapter adapter;
    TextView goalWeightTextView;
    ImageButton editGoalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new WeightDatabaseHelper(this);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        weightListView = findViewById(R.id.weightListView);
        addWeight = (FloatingActionButton) findViewById(R.id.addWeightButton);
        deleteWeight = (FloatingActionButton) findViewById(R.id.deleteWeightButton);
        editGoalButton = (ImageButton) findViewById(R.id.editGoalButton);
        goalWeightTextView = findViewById(R.id.goalWeightTextView);

        loadWeightData();

        String userID = sharedPreferences.getString("loggedInUser", "defaultUser");

        Cursor cursor = dbHelper.getAllWeights(userID);
        WeightAdapter adapter = new WeightAdapter(this, cursor);
        weightListView.setAdapter(adapter);

        String goalWeight = dbHelper.getGoalWeight(userID);
        goalWeightTextView.setText("Goal Weight: " + goalWeight);

        // Default from XML
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        // Listener for Add Weight button
        addWeight.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddWeight.class);
            startActivityForResult(intent, 1);
        });

        // Listener for Delete Weight button
        deleteWeight.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DeleteWeight.class);
            startActivityForResult(intent, 2);
        });

        editGoalButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SetGoalWeight.class);
            startActivityForResult(intent, 3);
        });
    }

    private void loadWeightData() {
        String userID = sharedPreferences.getString("loggedInUser", null);
        if (userID != null) {
            cursor = dbHelper.getAllWeights(userID);
            adapter = new WeightAdapter(this, cursor);
            weightListView.setAdapter(adapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cursor != null) {
            cursor.close();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            loadWeightData();
            String userID = sharedPreferences.getString("loggedInUser", "defaultUser");
            String goalWeight = dbHelper.getGoalWeight(userID);
            if (goalWeight != null && !goalWeight.isEmpty()) {
                goalWeightTextView.setText("Goal Weight: " + goalWeight + " lbs");
            } else {
                goalWeightTextView.setText("Goal Weight: Not Set");
            }
        }
    }
}