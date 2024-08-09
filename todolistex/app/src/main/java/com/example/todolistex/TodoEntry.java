package com.example.todolistex;

import java.io.Serializable;

public class TodoEntry implements Serializable {
    String title;
    Boolean checked;
    TodoEntry(String title, Boolean checked){
        this.title = title;
        this.checked = checked;
    }
}