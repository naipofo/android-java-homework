package com.example.todolistex;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Stores and modifies the to-do storage
 */
public class StorageManager {
    Context context;

    StorageManager(Context context, Boolean def) {
        this.context = context;
        List<TodoEntry> l = getTodos();
        if (l.size() < 1){
            if (def){
                setTodos(def());
            } else {
                setTodos(new ArrayList<>());
            }
        }
    }

    static private List<TodoEntry> def() {
        ArrayList<TodoEntry> a = new ArrayList<>();
        a.add(new TodoEntry("Zakupy: chleb, masło, ser", true));
        return a;
    }

    ArrayList<TodoEntry> getTodos() {
        try {
            SharedPreferences sharedPref = context.getSharedPreferences("tods", Context.MODE_PRIVATE);
            return (ArrayList<TodoEntry>) ToStringSeri.fromString(sharedPref.getString("t", null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setTodos(List<TodoEntry> list)  {
        try {
        SharedPreferences sharedPref = context.getSharedPreferences("tods", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("t", ToStringSeri.toString((Serializable) list));
        editor.apply();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> void withTodo(Function<List<TodoEntry>,T> f){
        List<TodoEntry> todos = new ArrayList<>(getTodos());
        f.apply(todos);
        setTodos(todos);
    }

    void addTodo(String todo)  {
        withTodo((t)-> t.add(new TodoEntry(todo, false)));
    }

    void editTodo(Integer i, String title)  {
        withTodo((t)-> t.get(i).title = title);
    }

    void markTodo(Integer i)  {
        withTodo((t)-> t.get(i).checked = !t.get(i).checked);
    }

    void removeTodo(Integer i)  {
        withTodo((t)-> t.remove(t.get(i)));
    }
}
