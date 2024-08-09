package com.example.todolistex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;
import java.util.function.Consumer;

public class TodoListAdapter extends ArrayAdapter<TodoEntry> {

    private int resourceLayout;
    private Context mContext;
    Consumer<Integer> onCheck;
    Consumer<Integer> onEdit;
    public TodoListAdapter(Context context, int resource, List<TodoEntry> items, Consumer<Integer> onCheck, Consumer<Integer> onEdit) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
        this.onCheck = onCheck;
        this.onEdit = onEdit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }
        TodoEntry p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.text1);
            tt1.setText(p.title);

            CheckBox ch1 = (CheckBox) v.findViewById(R.id.check);
            ch1.setChecked(p.checked);
            ch1.setOnClickListener((a)-> this.onCheck.accept(position));

            v.findViewById(R.id.imageButton).setOnClickListener((a)-> this.onEdit.accept(position));
        }

        return v;
    }

}