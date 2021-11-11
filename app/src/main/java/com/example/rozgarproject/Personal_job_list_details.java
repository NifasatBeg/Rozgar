package com.example.rozgarproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Personal_job_list_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_job_list_details);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String date = intent.getStringExtra("date");
        String numberOfWorkers = intent.getStringExtra("numberOfWorkers");

        TextView JobTitle = findViewById(R.id.JobTitle);
        JobTitle.setText(title);
        TextView creationDate = findViewById(R.id.creationDate);
        creationDate.setText(date);
        TextView RequiredWorkers = findViewById(R.id.RequiredSlot);
//        String tmp = "Required Workers : " + RequiredWorkers;
        RequiredWorkers.setText(numberOfWorkers);

    }
}