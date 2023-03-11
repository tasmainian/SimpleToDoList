package com.example.todolistsimple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView;
    private Button addButton;
    private Button deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        addButton = findViewById(R.id.addButton);
        deleteButton = findViewById(R.id.deleteButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItems(view);
            }
        });

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.item_text, items);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = view.findViewById(R.id.checkbox);
                checkBox.toggle();
            }
        });
    }

    private void addItem(View view) {
        EditText input = findViewById(R.id.editText);
        String itemText = input.getText().toString();

        if (!(itemText.equals(""))){
            itemsAdapter.add(itemText);
            input.setText("");
        }
        else {
            Toast.makeText(getApplicationContext(), "Please enter text", Toast.LENGTH_LONG).show();
        }
    }

    public void removeItems(View view) {
        boolean isAnyChecked = false;
        int count = itemsAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View v = listView.getChildAt(i);
            CheckBox checkBox = v.findViewById(R.id.checkbox);
            if (checkBox.isChecked()) {
                checkBox.toggle();
                items.remove(i);
                isAnyChecked = true;
            }
        }
        if(!isAnyChecked) {
            Toast.makeText(getApplicationContext(), "Please check the items to remove", Toast.LENGTH_LONG).show();
        }
        itemsAdapter.notifyDataSetChanged();
    }
}