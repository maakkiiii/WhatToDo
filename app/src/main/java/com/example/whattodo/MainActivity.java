package com.example.whattodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button newList;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        newList = findViewById(R.id.button);
        textView = findViewById(R.id.taskList);

        newList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddList.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        ArrayList<String> tasks = intent.getStringArrayListExtra("Tasklist");

        if (tasks != null) {
            StringBuilder buildList = new StringBuilder();

            for (String task : tasks) {
                buildList.append(task).append("\n"+"\n");
            }

            String showList = buildList.toString();

            textView.setText(showList);
        }
    }
}