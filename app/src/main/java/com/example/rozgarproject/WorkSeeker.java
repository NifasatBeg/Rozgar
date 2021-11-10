package com.example.rozgarproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class WorkSeeker extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_seeker);
        Button AppliedJobs = (Button) findViewById(R.id.AppliedJobs);
        Button SearchJob = (Button) findViewById(R.id.Search_Job);
        AppliedJobs.setOnClickListener(WorkSeeker.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.AppliedJobs:
                startActivity(new Intent(WorkSeeker.this,PersonalJobsApplied.class));
                break;
            case R.id.Search_Job:
                startActivity(new Intent(WorkSeeker.this,Openings.class));
                break;
        }
    }
}