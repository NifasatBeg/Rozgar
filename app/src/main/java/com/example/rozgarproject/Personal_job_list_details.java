package com.example.rozgarproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rozgarproject.Adapter.ApprovedLabourAdapter;
import com.example.rozgarproject.Adapter.RequestListAdapter;
import com.example.rozgarproject.Models.ApprovedLabour;
import com.example.rozgarproject.Models.Request;

import java.util.ArrayList;
import java.util.List;

public class Personal_job_list_details extends AppCompatActivity {

    public RecyclerView recyclerView1;
    private ApprovedLabourAdapter approvedLabourAdapter;
    public List<ApprovedLabour> approvedLabourArrayList;

    public RecyclerView recyclerView2;
    private RequestListAdapter requestListAdapter;
    public List<Request> requestList;
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
        requestList = new ArrayList<>();




        recyclerView1 = findViewById(R.id.ApprovedLabourList);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        approvedLabourAdapter = new ApprovedLabourAdapter(this);
        recyclerView1.setAdapter(approvedLabourAdapter);

        recyclerView2 = findViewById(R.id.RequestList);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        requestListAdapter = new RequestListAdapter(this);
        recyclerView2.setAdapter(requestListAdapter);

        ApprovedLabour rohan = new ApprovedLabour("Name : Rohan Bondre", "Age : 12", "Contact no : 7249132220");
        approvedLabourArrayList.add(rohan);
        approvedLabourArrayList.add(rohan);
        approvedLabourArrayList.add(rohan);
        approvedLabourArrayList.add(rohan);
        approvedLabourArrayList.add(rohan);
        approvedLabourArrayList.add(rohan);


        Request rohan1 = new Request("Name : Nifasat Beg", "Age : 21", "Contact no : 7249132220");
        requestList.add(rohan1);
        requestList.add(rohan1);

        approvedLabourAdapter.setApprovedLabourList(approvedLabourArrayList);
        requestListAdapter.setRequestList(requestList);


//        approvedLabourAdapter.setTasks(approvedLabourArrayList);


    }
}