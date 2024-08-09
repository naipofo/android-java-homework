package com.example.todolistex;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.List;
import java.util.function.Function;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private StorageManager manager() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        return new StorageManager(appContext, false);
    }

    @Test
    public void todoAddingeTest() {
        StorageManager storageManager = manager();
        storageManager.addTodo("Zakupy");
        TodoEntry e =  storageManager.getTodos().get(0);
        assertEquals("Zakupy", e.title);
        assertEquals(false, e.checked);
    }

    @Test
    public void todoCheckTest() {
        StorageManager storageManager = manager();
        storageManager.addTodo("Zakupy");
        TodoEntry initial =  storageManager.getTodos().get(0);
        assertEquals(false, initial.checked);

        storageManager.markTodo(0);
        TodoEntry checked =  storageManager.getTodos().get(0);

        assertEquals(true, checked.checked);
    }

    @Test
    public void todoRemoveTest() {
        StorageManager storageManager = manager();

        storageManager.addTodo("Zakupy1");
        storageManager.addTodo("Zakupy2");
        storageManager.addTodo("Zakupy3");
        storageManager.addTodo("Zakupy4");

        List<TodoEntry> initial =  storageManager.getTodos();

        assertEquals(4, initial.size());

        storageManager.removeTodo(0);
        storageManager.removeTodo(0);
        storageManager.removeTodo(0);

        List<TodoEntry> removed =  storageManager.getTodos();

        assertEquals(1, removed.size());
    }

}