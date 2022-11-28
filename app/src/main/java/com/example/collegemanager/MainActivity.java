package com.example.collegemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Login as Admin Activity's Button
    public void conAsAdmin(View v){
        Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
        startActivity(intent);
    }

    // Continue as user Activity's Button
    public void conAsStudent(View v){
        Intent intent = new Intent(MainActivity.this, StudentSelectActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}