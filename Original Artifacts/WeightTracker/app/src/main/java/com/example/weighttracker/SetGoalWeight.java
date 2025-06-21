package com.example.weighttracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SetGoalWeight extends AppCompatActivity {

    EditText goalWeightEditText;
    Button setGoalButton;
    WeightDatabaseHelper dbHelper;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal_weight);

        goalWeightEditText = findViewById(R.id.goalWeightEditText);
        setGoalButton = findViewById(R.id.setGoalButton);
        dbHelper = new WeightDatabaseHelper(this);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userID = sharedPreferences.getString("loggedInUser", "defaultUser");

        setGoalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String goalWeight = goalWeightEditText.getText().toString();

                if (!goalWeight.isEmpty()) {
                    // Save goal weight in the database
                    boolean isInserted = dbHelper.insertGoalWeight(userID, goalWeight);
                    if (isInserted) {
                        Toast.makeText(SetGoalWeight.this, "Goal weight set!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SetGoalWeight.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SetGoalWeight.this, "Failed to set goal weight.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SetGoalWeight.this, "Please enter a goal weight.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
