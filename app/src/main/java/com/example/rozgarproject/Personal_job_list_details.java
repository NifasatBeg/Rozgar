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

    public RecyclerView recyclerView1, recyclerView2;
    private ApprovedLabourAdapter approvedLabourAdapter;
    public ArrayList<ApprovedLabour> approvedLabourArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_job_list_details);

        recyclerView1 = findViewById(R.id.ApprovedLabourList);


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

        ApprovedLabour rohan = new ApprovedLabour("Rohan Bondre", "12", "7249132220");
        approvedLabourArrayList.add(rohan);
        approvedLabourArrayList.add(rohan);




        recyclerView1 = findViewById(R.id.ApprovedLabourList);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        approvedLabourAdapter = new ApprovedLabourAdapter(Personal_job_list_details.this, approvedLabourArrayList);
        recyclerView1.setAdapter(approvedLabourAdapter);


    }
}