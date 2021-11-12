package com.example.rozgarproject.Adapter;

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

import java.util.List;

public class RequestListAdapter extends RecyclerView.Adapter<RequestListAdapter.ViewHolder> {

    private Personal_job_list_details activity;
    private List<Request> requestList;

    public RequestListAdapter(Personal_job_list_details activity){
        this.activity = activity;
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
        public TextView RequestedID;
        public Button AcceptRequest,RejectRequest;

        public ViewHolder(View itemView) {
            super(itemView);
            LabourName = itemView.findViewById(R.id.RLabourName);
            Age = itemView.findViewById(R.id.RAge);
            ContactNumber = itemView.findViewById(R.id.RContactNumber);
            RequestedID = itemView.findViewById(R.id.RequestedJobId);
            AcceptRequest = itemView.findViewById(R.id.Accept);
            RejectRequest = itemView.findViewById(R.id.Reject);
            AcceptRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(Personal_job_list_details.this, "Accepted", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
