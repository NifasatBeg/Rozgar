package com.example.rozgarproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rozgarproject.Adapter.ApprovedLabourAdapter;
import com.example.rozgarproject.Models.ApprovedLabour;
import com.example.rozgarproject.Models.Request;

import java.util.ArrayList;
import java.util.List;

public class Personal_job_list_details extends AppCompatActivity {

    public RecyclerView recyclerView1;
    private ApprovedLabourAdapter approvedLabourAdapter;
    public List<ApprovedLabour> approvedLabourArrayList;

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

        approvedLabourArrayList = new ArrayList<>();




        recyclerView1 = findViewById(R.id.ApprovedLabourList);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        approvedLabourAdapter = new ApprovedLabourAdapter(this);
        recyclerView1.setAdapter(approvedLabourAdapter);

        ApprovedLabour rohan = new ApprovedLabour("Rohan Bondre", "12", "7249132220");
        approvedLabourArrayList.add(rohan);
        approvedLabourArrayList.add(rohan);

        approvedLabourAdapter.setApprovedLabourList(approvedLabourArrayList);


//        approvedLabourAdapter.setTasks(approvedLabourArrayList);


    }
}