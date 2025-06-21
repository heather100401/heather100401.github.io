package com.example.weighttracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.weighttracker.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements SmsPermission.SmsPermissionListener {

    ActivityLoginBinding binding;
    LoginDatabaseHelper dbHelper;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new LoginDatabaseHelper(this);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // creating account
        binding.createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.usernameEditText.getText().toString();
                String password = binding.passwordEditText.getText().toString();

                if (username.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter username and password.", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean checkUsername = dbHelper.checkUsername(username);

                    if (checkUsername == false){        // username is not taken
                        boolean insert = dbHelper.insertData(username, password);
                        
                        if (insert == true){            // inserted successfully
                            Toast.makeText(LoginActivity.this, "Account Created", Toast.LENGTH_SHORT).show();

                            showSmsPermissionDialog();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Account cannot be created.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Username already taken.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // logging in
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.usernameEditText.getText().toString();
                String password = binding.passwordEditText.getText().toString();

                if (username.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter username and password.", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean authenticate = dbHelper.checkPassword(username, password);

                    if (authenticate == true) {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("loggedInUser", username);
                        editor.apply();

                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void showSmsPermissionDialog() {
        SmsPermission smsPermissionDialog = new SmsPermission();
        smsPermissionDialog.show(getSupportFragmentManager(), "smsPermissionDialog");
    }

    @Override
    public void onSmsPermissionGranted() {
        Toast.makeText(this, "SMS permission granted. You can now receive notifications.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), SetGoalWeight.class);
        startActivity(intent);
    }

    @Override
    public void onSmsPermissionDenied() {
        Toast.makeText(this, "SMS permission denied. Notifications will not be sent.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), SetGoalWeight.class);
        startActivity(intent);
    }

    }
