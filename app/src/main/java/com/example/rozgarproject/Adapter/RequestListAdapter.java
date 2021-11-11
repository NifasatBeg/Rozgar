package com.example.rozgarproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rozgarproject.Models.ApprovedLabour;
import com.example.rozgarproject.Models.Request;
import com.example.rozgarproject.Personal_job_list_details;
import com.example.rozgarproject.R;
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


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView LabourName;
        public TextView Age;
        public TextView ContactNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            LabourName = itemView.findViewById(R.id.RLabourName);
            Age = itemView.findViewById(R.id.RAge);
            ContactNumber = itemView.findViewById(R.id.RContactNumber);
        }


    }
}
