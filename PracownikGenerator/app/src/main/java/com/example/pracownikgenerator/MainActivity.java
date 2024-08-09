package com.example.pracownikgenerator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pracownikgenerator.passwords.BasicPasswordGenerator;
import com.example.pracownikgenerator.passwords.PasswordGenerator;
import com.example.pracownikgenerator.passwords.PasswordRule;
import com.example.pracownikgenerator.passwords.RuleExtractorBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String haslo = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        spinner
        Spinner spinner = (Spinner) findViewById(R.id.jobSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.jobs_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        findViewById(R.id.button).setOnClickListener((l)-> {
            haslo = generatePassword();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(haslo);
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        findViewById(R.id.button2).setOnClickListener((l)-> {
            haslo = generatePassword();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            StringBuilder b = new StringBuilder();
            b.append("Dane pracownika: ");
            b.append(((EditText) findViewById(R.id.imieEditText)).getText().toString());
            b.append(" ");
            b.append(((EditText) findViewById(R.id.nazwiskoEditText)).getText().toString());
            b.append(" ");
            b.append((String) spinner.getSelectedItem());
            b.append(" Haslo: ");
            b.append(haslo);
            builder.setMessage(b.toString());
            AlertDialog dialog = builder.create();
            dialog.show();
        });

    }

    String generatePassword(){
        int totalLength = Integer.parseInt(((EditText) findViewById(R.id.passwordLengthEditText)).getText().toString());
        PasswordGenerator generator = new BasicPasswordGenerator();
        return generator.generatePassword(passwordRuleExtract(), totalLength);
    }

    List<PasswordRule> passwordRuleExtract(){
        return new RuleExtractorBuilder()
                .checkBox(R.id.checkBox, PasswordRule.HAS_CAPITAL)
                .checkBox(R.id.checkBox1, PasswordRule.HAS_NUMBER)
                .checkBox(R.id.checkBox2, PasswordRule.HAS_SPECIAL)
                .build()
                .extract(this);
    }
}