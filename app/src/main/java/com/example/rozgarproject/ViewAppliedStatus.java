package com.example.rozgarproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.rozgarproject.Adapter.AppliedJobStatusAdapter;
import com.example.rozgarproject.Adapter.ApprovedLabourAdapter;
import com.example.rozgarproject.Adapter.RequestListAdapter;
import com.example.rozgarproject.Models.AppliedStatusObj;
import com.example.rozgarproject.Models.ApprovedLabour;
import com.example.rozgarproject.Models.Request;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewAppliedStatus extends AppCompatActivity {
    public interface FirebaseCallback {
        void onResponse(List<String> value);
    }
    public void readFirebaseName(FirebaseCallback callback) {
        DatabaseReference reference;
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();
        reference = FirebaseDatabase.getInstance().getReference("AppliedWorkers");
        List<String> userIDlist = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot: datasnapshot.getChildren()){
                    if(snapshot.child(uid).exists()){
                        String ID1 = snapshot.getKey();
                        ID1 += snapshot.child(uid).getValue(String.class);
                        userIDlist.add(ID1);
                    }
                }

                callback.onResponse(userIDlist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public List<String> userIDlist;
    public RecyclerView recyclerView4;
    private AppliedJobStatusAdapter appliedJobStatusAdapterr;
    public List<AppliedStatusObj> approvedjoblist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applied_status);
        FirebaseAuth mAuth;
        DatabaseReference reference;
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("AppliedWorkers");
        userIDlist = new ArrayList<>();
        approvedjoblist = new ArrayList<>();

        readFirebaseName(new FirebaseCallback(){
            @Override
            public void onResponse(List<String> userIDlist) {

                recyclerView4 = findViewById(R.id.ApprovedAppliedList);
                recyclerView4.setLayoutManager(new LinearLayoutManager(ViewAppliedStatus.this));
                appliedJobStatusAdapterr = new AppliedJobStatusAdapter(ViewAppliedStatus.this);
                DatabaseReference reference1;
                reference1 = FirebaseDatabase.getInstance().getReference().child("Job Posts");
                reference1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds: snapshot.getChildren()){
                            for(DataSnapshot ds1: ds.getChildren()){
                                String jobID = ds1.getKey();
                                boolean check = false;
                                String tmpID1 = "";
                                for (String tmpID : userIDlist){
                                    if(tmpID.contains(jobID)){
                                        check = true;
                                        tmpID1 = tmpID;
                                        break;
                                    }
                                }
                                if(check == false) continue;
                                String JobTitle = ds1.child("JobTitle").getValue(String.class);
                                String JobLocation = ds1.child("address").getValue(String.class);
                                String jobId = "12";
                                String apply = "Apply";
                                String status = "";
                                String announcement = "";

                                if(tmpID1.endsWith("1")){
                                    status = "Pending";
                                }
                                else if(tmpID1.endsWith("2")){
                                    status = "Approved";
                                }
                                else{
                                    status = "Rejected";
                                }
                                AppliedStatusObj job1 = new AppliedStatusObj(jobID,"Status - " + status,announcement,"Job Location - " + JobLocation,"Job Title - "+JobTitle);
                                approvedjoblist.add(job1);
                            }
                        }
                        appliedJobStatusAdapterr.setApprovedjobList(approvedjoblist);
                        recyclerView4.setAdapter(appliedJobStatusAdapterr);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ViewAppliedStatus.this,"Error occured",Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
            }
        });


    }
}