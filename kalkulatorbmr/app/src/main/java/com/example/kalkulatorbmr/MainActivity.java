package com.example.kalkulatorbmr;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener((e) -> obliczanko());
    }

    void obliczanko() {
        float height = Float.parseFloat(((EditText) findViewById(R.id.wzrost)).getText().toString());
        float weight = Float.parseFloat(((EditText) findViewById(R.id.waga)).getText().toString());
        float age = Float.parseFloat(((EditText) findViewById(R.id.wiek)).getText().toString());

        double bmr = BrmCalculator.calculate(!((RadioButton) findViewById(R.id.radio_f)).isChecked(), weight, height, age);
        ((TextView) findViewById(R.id.bigResult)).setText(bmr + " BMR");
    }
}