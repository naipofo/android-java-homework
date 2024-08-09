package com.example.todolistex;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void serializationWorks() throws IOException, ClassNotFoundException {
        TodoEntry a = new TodoEntry("asd", true);
        TodoEntry b = (TodoEntry) ToStringSeri.fromString(ToStringSeri.toString(a));
        assertEquals(b.title, "asd");
        assertEquals(b.checked, true);
    }
}