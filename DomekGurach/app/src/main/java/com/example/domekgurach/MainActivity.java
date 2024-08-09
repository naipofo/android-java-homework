package com.example.domekgurach;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int polubienia = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.polub).setOnClickListener((e)->changeCount(1));
        findViewById(R.id.usun).setOnClickListener((e)->changeCount(-1));
    }
    
    @SuppressLint("SetTextI18n")
    void changeCount(int amount){
        polubienia = Math.max(polubienia + amount, 0);
        TextView v = findViewById(R.id.polobienia);
        v.setText(polubienia + " polubień");
    }
}