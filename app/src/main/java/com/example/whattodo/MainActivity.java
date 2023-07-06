package com.example.whattodo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    Button newList;
    TextView textView;

    ArrayList<String> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intentAlarm = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intentAlarm, PendingIntent.FLAG_IMMUTABLE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,13);
        calendar.set(Calendar.MINUTE,24);
        calendar.set(Calendar.SECOND,0);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


        newList = findViewById(R.id.button);
        textView = findViewById(R.id.taskList);
        tasks = new ArrayList<>();

        newList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddList.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        ArrayList<String> receivedTasks = intent.getStringArrayListExtra("Tasklist");

        if (receivedTasks != null) {
            tasks.addAll(receivedTasks);
            displayTasks();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        saveTasks();
    }
    private void saveTasks(){
        SharedPreferences sharedPreferences = getSharedPreferences("SaveList", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("TaskList", new HashSet<>(tasks));
        editor.apply();
    }

    private void displayTasks(){
        StringBuilder buildList = new StringBuilder();

        for (String task : tasks) {
            buildList.append(task).append("\n"+"\n");
        }

        String showList = buildList.toString();

        textView.setText(showList);
    }
}