package com.example.datacalc;

import static com.example.datacalc.UnitMeasure.units;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] unitNames = new String[units.length];
        for (int i = 0; i < units.length; i++) {
            unitNames[i] = units[i].name;
        }

        Spinner spinner = findViewById(R.id.typesSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unitNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Get references to the EditText and Spinner
        EditText editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        editTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                update();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }
    public void update() {
        try {

        EditText editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        long number = Long.parseLong(editTextNumber.getText().toString());

        Spinner typesSpinner = (Spinner) findViewById(R.id.typesSpinner);
        int selectedIndex = typesSpinner.getSelectedItemPosition();
        UnitMeasure selectedUnit = units[selectedIndex];

        String[] results = new String[units.length];
        for (int i = 0; i < units.length; i++) {
            double valueInUnit = (double) number * selectedUnit.value / units[i].value;
            results[i] = units[i].name + ": " + valueInUnit;
        }

        ListView resultList = findViewById(R.id.ResultList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, results);
        resultList.setAdapter(adapter);
        } catch (Exception ignore){}
    }


}