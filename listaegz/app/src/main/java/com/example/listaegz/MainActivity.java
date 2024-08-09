package com.example.listaegz;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edit;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        edit = findViewById(R.id.textin);
        adapter = new ArrayAdapter<>(this, R.layout.list_element, new String[]{
                "Zakupy: chleb, masło, ser",
                "Do zrobienia: obiad, umyć podłogi",
                "weekend: kino, spacer z psem"
        });

        ((ListView)findViewById(R.id.lista)).setAdapter(adapter);
        findViewById(R.id.button).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        adapter.addAll(edit.getText().toString());
        edit.getText().clear();
    }
}