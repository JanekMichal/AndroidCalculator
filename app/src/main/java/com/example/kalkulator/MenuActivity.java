package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void simpleCalcClick(View view) {
        Intent intent = new Intent(getBaseContext(), SimpleCalc.class);
        startActivity(intent);
    }

    public void advancedCalcClick(View view) {
        Intent intent = new Intent(getBaseContext(), AdvancedCalc.class);
        startActivity(intent);
    }

    public void aboutCalcClick(View view) {
        Intent intent = new Intent(getBaseContext(), AboutActivity.class);
        startActivity(intent);
    }

    public void exitClick(View view) {
        finish();
        System.exit(0);
    }
}