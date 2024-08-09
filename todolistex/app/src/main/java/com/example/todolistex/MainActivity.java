package com.example.todolistex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    StorageManager storage;
    TodoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = new StorageManager(this, true);
        adapter = new TodoListAdapter(this, R.layout.todo_element, storage.getTodos(), (i)->{
            storage.markTodo(i);
            update();
        }, (i)-> {
            Log.i("ASDASD", "ASDJHASKJDHASJKSHDKJASHDKJ");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Edytuj");

            final EditText input = new EditText(this);
            input.setText(storage.getTodos().get(i).title);
            builder.setView(input);

            builder.setPositiveButton("OK", (dialog, which) -> {
                storage.editTodo(i, input.getText().toString());
                update();
            });
            builder.setNegativeButton("Usuń", (dialog, which) -> {
                storage.removeTodo(i);
                dialog.cancel();
                update();
            });

            builder.show();
        });


        ((ListView)findViewById(R.id.lista)).setAdapter(adapter);
        findViewById(R.id.dodaj).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dodaj");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            storage.addTodo(input.getText().toString());
            update();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    void update(){
        adapter.clear();
        adapter.addAll(storage.getTodos());
    }
}