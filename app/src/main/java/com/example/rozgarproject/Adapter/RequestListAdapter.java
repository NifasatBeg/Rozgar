package com.example.rozgarproject.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rozgarproject.Models.ApprovedLabour;
import com.example.rozgarproject.Models.Request;
import com.example.rozgarproject.Personal_job_list_details;
import com.example.rozgarproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.List;

public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.ViewHolder> {

    private Personal_job_list_details activity;
    private List<Request> requestList;

    public RequestListAdapter(Activity activity){
        this.activity = (Personal_job_list_details) activity;
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_labour_request,parent, false);
        return new ViewHolder(itemview);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Request item= requestList.get(position);

        holder.LabourName.setText(item.getName());
        holder.Age.setText(item.getAge());
        holder.ContactNumber.setText(item.getContactNumber());
        holder.RequestedID.setText(item.getJobID());
        holder.RequesteduserID.setText(item.getUserID());

    }

    public int getItemCount() {
        return requestList.size();
    }

    public void setRequestList(List<Request> requestList){
        this.requestList = requestList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView LabourName;
        public TextView Age;
        public TextView ContactNumber;
        public TextView RequestedID,RequesteduserID;
        public Button AcceptRequest,RejectRequest;

        public ViewHolder(View itemView) {
            super(itemView);
            LabourName = itemView.findViewById(R.id.RLabourName);
            Age = itemView.findViewById(R.id.RAge);
            ContactNumber = itemView.findViewById(R.id.RContactNumber);
            RequestedID = itemView.findViewById(R.id.RequestedJobId);
            RequesteduserID = itemView.findViewById(R.id.RequestedUserId);
            AcceptRequest = itemView.findViewById(R.id.Accept);
            RejectRequest = itemView.findViewById(R.id.Reject);
//            String tmp = RequestedID.getText().toString();
            Log.d("task",LabourName.getText().toString() + Age.getText().toString());
            AcceptRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    String rid = RecruiterId.getText().toString();
                    String jid = RequestedID.getText().toString();
                    FirebaseAuth mAuth;
                    DatabaseReference reference;
                    mAuth = FirebaseAuth.getInstance();
                    String uid = mAuth.getCurrentUser().getUid();
                    Log.d("task1",jid);
                    FirebaseDatabase.getInstance().getReference("AppliedWorkers").child(jid).child(RequesteduserID.getText().toString()).setValue("2");
                    Log.d("task1",RequesteduserID.getText().toString());
//                    Log.d("task1",jid);
                    AcceptRequest.setText("Accepted request");
//                    Toast.makeText(context,"approved",Toast.LENGTH_SHORT).show();
//                    Toast.makeText(Personal_job_list_details.this, "Accepted", Toast.LENGTH_SHORT).show();
                }
            });
            RejectRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    String rid = RecruiterId.getText().toString();
                    String jid = RequestedID.getText().toString();
                    FirebaseAuth mAuth;
                    DatabaseReference reference;
                    mAuth = FirebaseAuth.getInstance();
                    String uid = mAuth.getCurrentUser().getUid();
                    Log.d("task1",jid);
                    FirebaseDatabase.getInstance().getReference("AppliedWorkers").child(jid).child(RequesteduserID.getText().toString()).setValue("3");
                    Log.d("task1",RequesteduserID.getText().toString());
//                    Log.d("task1",jid);
                    AcceptRequest.setText("Accepted request");
//                    Toast.makeText(context,"approved",Toast.LENGTH_SHORT).show();
//                    Toast.makeText(Personal_job_list_details.this, "Accepted", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
