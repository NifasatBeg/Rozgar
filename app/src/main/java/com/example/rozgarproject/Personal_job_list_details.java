package com.example.rozgarproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rozgarproject.Adapter.ApprovedLabourAdapter;
import com.example.rozgarproject.Adapter.RequestListAdapter;
import com.example.rozgarproject.Models.ApprovedLabour;
import com.example.rozgarproject.Models.Request;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Personal_job_list_details extends AppCompatActivity {


    public interface MyCallback {
        void onCallback(List<String> value);
    }
    public void readData(MyCallback myCallback, String jobID) {
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("AppliedWorkers").child(jobID);
        List<String> userIDlist = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot: datasnapshot.getChildren()){
                    String ID = snapshot.getKey();
                    ID += '$';
                    ID += snapshot.getValue(String.class);
                    userIDlist.add(ID);
                }
                myCallback.onCallback(userIDlist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public String fetchID(String mixID) {
        String tmp = "";
        for (int i = 0; i < mixID.length(); i++) {
            if (mixID.charAt(i) == '$') return tmp;
            else {
                tmp += mixID.charAt(i);
            }

        }
        return tmp;
    }
    DatabaseReference reference;

    public RecyclerView recyclerView1;
    private ApprovedLabourAdapter approvedLabourAdapter;
    public List<ApprovedLabour> approvedLabourArrayList;
    public List<String> userIDlist;

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
        String jobID = intent.getStringExtra("JobPostID");
//        Toast.makeText(Personal_job_list_details.this, "ID : " + jobID , Toast.LENGTH_SHORT).show();
        TextView JobTitle = findViewById(R.id.JobTitle);
        JobTitle.setText(title);
        TextView creationDate = findViewById(R.id.creationDate);
        creationDate.setText(date);
        TextView RequiredWorkers = findViewById(R.id.RequiredSlot);
//        String tmp = "Required Workers : " + RequiredWorkers;
        RequiredWorkers.setText(numberOfWorkers);
        TextView bookedSlot = findViewById(R.id.BookedSlot);


        approvedLabourArrayList = new ArrayList<>();
        requestList = new ArrayList<>();
        userIDlist = new ArrayList<>();

        readData(new MyCallback() {
            @Override
            public void onCallback(List<String> userIDlist) {

                recyclerView1 = findViewById(R.id.ApprovedLabourList);
                recyclerView1.setLayoutManager(new LinearLayoutManager(Personal_job_list_details.this));
                approvedLabourAdapter = new ApprovedLabourAdapter(Personal_job_list_details.this);


                recyclerView2 = findViewById(R.id.RequestList);
                recyclerView2.setLayoutManager(new LinearLayoutManager(Personal_job_list_details.this));




                DatabaseReference reference1;
                reference1 = FirebaseDatabase.getInstance().getReference("Users");
                reference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot ds: snapshot.getChildren()){
                            String jobId1 = ds.getKey();
                            boolean check = false;
                            String tmpID1 = "";
                            for (String tmpID : userIDlist){
                                if(tmpID.contains(jobId1)){
                                    check = true;
                                    tmpID1 = tmpID;
                                    break;
                                }
                            }
                            if(check == false) continue;
                            String jname = ds.child("fullName").getValue(String.class);
                            String jage = ds.child("age").getValue(String.class);
                            String jnumber = ds.child("phoneNumber").getValue(String.class);

                            if(tmpID1.endsWith("1")){
                                Request rohan1 = new Request("Name : " + jname, "Age : "+jage, "Contact number : "+jnumber, fetchID(tmpID1),jobID);
                                requestList.add(rohan1);
                            }
                            else if(tmpID1.endsWith("2")){

                                ApprovedLabour rohan = new ApprovedLabour("Name : " + jname, "Age : "+jage, "Contact number : "+jnumber);
                                approvedLabourArrayList.add(rohan);
                            }
                        }
                        requestListAdapter = new RequestListAdapter(Personal_job_list_details.this);
                        requestListAdapter.setRequestList(requestList);
                        requestListAdapter.setRequestList(requestList);
                        approvedLabourAdapter.setApprovedLabourList(approvedLabourArrayList);
                        bookedSlot.setText("Slot Booked : " + approvedLabourArrayList.size());

                        recyclerView1.setAdapter(approvedLabourAdapter);
                        recyclerView2.setAdapter(requestListAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        },jobID);

    }
}