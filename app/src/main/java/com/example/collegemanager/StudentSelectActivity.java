package com.example.collegemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StudentSelectActivity extends AppCompatActivity {

    String lab;
//    lab - 0 computer, 1 chemisty, 2 physics, 3 gymnasium, 4 sportsRoom ,
    // ComputerLab Button
    public void computerLab(View v){
        lab = "0";
        Intent intent = new Intent(StudentSelectActivity.this, StudentMainActivity.class);
        intent.putExtra("key", lab);
        startActivity(intent);
    }

    // ChemistryLab Button
    public void chemistryLab(View v){
        lab = "1";
        Intent intent = new Intent(StudentSelectActivity.this, StudentMainActivity.class);
        intent.putExtra("key", lab);
        startActivity(intent);
    }

    // PhysicsLab Button
    public void physicsLab(View v){
        lab = "2";
        Intent intent = new Intent(StudentSelectActivity.this, StudentMainActivity.class);
        intent.putExtra("key", lab);
        startActivity(intent);
    }

    // Gymnasium Button
    public void gymnasium(View v){
        lab = "3";
        Intent intent = new Intent(StudentSelectActivity.this, StudentMainActivity.class);
        intent.putExtra("key", lab);
        startActivity(intent);
    }

    // MachineShop Button
    public void machineShop(View v){
        lab = "4";
        Intent intent = new Intent(StudentSelectActivity.this, StudentMainActivity.class);
        intent.putExtra("key", lab);
        startActivity(intent);
    }

    // HardwareLab Button
    public void hardwareLab(View v){
        lab = "5";
        Intent intent = new Intent(StudentSelectActivity.this, StudentMainActivity.class);
        intent.putExtra("key", lab);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_select);
    }
}