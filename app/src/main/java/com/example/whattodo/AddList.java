package com.example.whattodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class AddList extends AppCompatActivity {

    Button newTask;
    Button hinzufuegen;

    EditText taskField;
    String task;
    ArrayList<String> tasks;

    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        taskField = findViewById(R.id.task);
        tasks = new ArrayList<>();

        newTask = findViewById(R.id.newTask);
        hinzufuegen = findViewById(R.id.hinzufuegen);

        mediaPlayer = MediaPlayer.create(this, R.raw.button_sound);

        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = taskField.getText().toString();
                tasks.add(task);
                taskField.getText().clear();
            }
        });

        hinzufuegen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent = new Intent(AddList.this, MainActivity.class);
                intent.putStringArrayListExtra("Tasklist", tasks);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
