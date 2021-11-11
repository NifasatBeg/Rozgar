package com.example.rozgarproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rozgarproject.Models.ApprovedLabour;
import com.example.rozgarproject.Personal_job_list_details;
import com.example.rozgarproject.R;
import com.google.firebase.database.core.Context;

import java.util.List;

public class ApprovedLabourAdapter extends RecyclerView.Adapter<ApprovedLabourAdapter.ViewHolder> {

    private Personal_job_list_details activity;
    private List<ApprovedLabour> approvedLabourList;

    public ApprovedLabourAdapter(Personal_job_list_details activity){
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_approved_labours,parent, false);
        return new ViewHolder(itemview);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        ApprovedLabour item= approvedLabourList.get(position);

        holder.LabourName.setText(item.getName());
        holder.Age.setText(item.getAge());
        holder.ContactNumber.setText(item.getContactNumber());

    }

    public int getItemCount() {
        return approvedLabourList.size();
    }

    public void setApprovedLabourList(List<ApprovedLabour> approvedLabourList){
        this.approvedLabourList = approvedLabourList;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView LabourName;
        public TextView Age;
        public TextView ContactNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            LabourName = itemView.findViewById(R.id.LabourName);
            Age = itemView.findViewById(R.id.LabourAge);
            ContactNumber = itemView.findViewById(R.id.LabourNumber);
        }


    }
}
