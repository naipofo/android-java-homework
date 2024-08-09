package com.example.logowanko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.login).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView alert = findViewById(R.id.alert);
        alert.setText(login());
    }

    private String login(){
        EditText email = findViewById(R.id.email);
        EditText pass = findViewById(R.id.pass);
        EditText pass2 = findViewById(R.id.pass2);
        if (!email.getText().toString().contains("@")){
            return "Nieprawidłowy adres e-mail";
        } else if (!pass.getText().toString().equals(pass2.getText().toString())) {
            return "Hasła się różnią";
        } else {
            return "Witaj " + email.getText().toString();
        }
    }
}