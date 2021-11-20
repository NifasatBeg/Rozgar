package com.example.rozgarproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WorkSeeker extends AppCompatActivity implements View.OnClickListener{
    Button filter,viewAll,applyFilter,viewStatusofApplied;
    EditText location,salary;
    RecyclerView recyclerView;
    List<allJobs> list;
    allJobsAdapter adapter;
    DatabaseReference reference;
    TextToSpeech toSpeech;
    Button btn_speechtext;
    String Speechout;
    static int voicebreak = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_seeker);
        recyclerView = findViewById(R.id.recycler_all_job_id);
        filter = findViewById(R.id.Filter);
        viewAll = findViewById(R.id.ViewAllJobs);
        applyFilter = findViewById(R.id.ApplyFilter);
        location = findViewById(R.id.SearchLocation);
        salary = findViewById(R.id.SearchSalary);
        btn_speechtext = findViewById(R.id.Speechbtn);
        viewStatusofApplied = findViewById(R.id.AppliedStatusbutton);
        viewStatusofApplied.setOnClickListener(WorkSeeker.this);
        filter.setOnClickListener(WorkSeeker.this);
        viewAll.setOnClickListener(WorkSeeker.this);
        applyFilter.setOnClickListener(WorkSeeker.this);
        reference = FirebaseDatabase.getInstance().getReference().child("Job Posts");
        list = new ArrayList<>();

        toSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!= TextToSpeech.ERROR){
                    toSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });

        /*speechRecognizer.stopListening();

        Make a speech recognition listener and write up this code to get the data processed(add angular brackets behind String):
        ArrayList .String. data = results.getStringArrayList(speechRecognizer.RESULTS_RECOGNITION);
        editText.setText(data.get(0));*/



        btn_speechtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voicebreak += 1;
                if(voicebreak == 1){
                    int count = 1;
                    Speechout = "";
                    for(allJobs obj : list){
                        String tmp = ", Job Number " + count;
                        tmp += ", Job Title " + obj.JobTitle;
                        tmp += ", Created On " + obj.JobDate;
                        tmp += ", Location " + obj.JobLocation;
                        tmp += ", Job Salary " + obj.salary;
                        tmp += ", Workers Required " + obj.NumberofWorkers;
                        Speechout += tmp;
                        count += 1;
                    }
                    Log.d("dbrohan","anyth" +list.size() +Speechout);

                    toSpeech.speak(Speechout,TextToSpeech.QUEUE_FLUSH,null);
                }
                else {
                    voicebreak = 0;
                    toSpeech.stop();
                }
            }
        });



        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    for(DataSnapshot ds1: ds.getChildren()){
                        String JobTitle = ds1.child("JobTitle").getValue(String.class);
                        String JobDate = ds1.child("date").getValue(String.class);
                        String JobWorkers = ds1.child("workersNumber").getValue(String.class);
                        String JobLocation = ds1.child("address").getValue(String.class);
                        String recruiterId = ds1.child("id").getValue(String.class);
                        String jobId = ds1.child("jobId").getValue(String.class);
                        String salary = ds1.child("salary").getValue(String.class);
                        String apply = "Apply";
                        allJobs job = new allJobs("JOB TITLE - " + JobTitle, "JOB CREATED ON - "+JobDate,"WORKERS REQUIRED - " +JobWorkers, "LOCATION - " +JobLocation,recruiterId,jobId,"SALARY - " + salary,apply);
                        list.add(job);
                    }
                }
                adapter = new allJobsAdapter(list,WorkSeeker.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(WorkSeeker.this,"Error occured",Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Filter:
                location.setVisibility(View.VISIBLE);
                salary.setVisibility(View.VISIBLE);
                applyFilter.setVisibility(View.VISIBLE);
                break;
            case R.id.ViewAllJobs:
                location.setVisibility(View.GONE);
                salary.setVisibility(View.GONE);
                applyFilter.setVisibility(View.GONE);
                adapter.getFilter().filter("");
                break;
            case R.id.ApplyFilter:
                location.setVisibility(View.GONE);
                salary.setVisibility(View.GONE);
                applyFilter.setVisibility(View.GONE);
                String charSequence = "";
                charSequence += location.getText().toString();
                charSequence += '$';
                charSequence += salary.getText().toString();
                adapter.getFilter().filter(charSequence);
                break;
            case  R.id.AppliedStatusbutton:
                Intent intent = new Intent(WorkSeeker.this,ViewAppliedStatus.class);
                startActivity(intent);
        }
    }
}