package com.example.rozgarproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rozgarproject.Models.AppliedStatusObj;
import com.example.rozgarproject.Models.ApprovedLabour;
import com.example.rozgarproject.Personal_job_list_details;
import com.example.rozgarproject.R;
import com.example.rozgarproject.ViewAppliedStatus;
import com.google.firebase.database.core.Context;

import java.text.BreakIterator;
import java.util.List;

public class AppliedJobStatusAdapter extends RecyclerView.Adapter<AppliedJobStatusAdapter.ViewHolder> {

    private ViewAppliedStatus activity;
    private List<AppliedStatusObj> approvedjobList;


    public AppliedJobStatusAdapter(ViewAppliedStatus activity){
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.applied_job_status_details,parent, false);
        return new ViewHolder(itemview);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        AppliedStatusObj item= approvedjobList.get(position);
        holder.JobID.setText(item.getJobID());
        holder.JobTitle.setText(item.getJobTitle());
        holder.jannouncement.setText(item.getAnnouncement());
        holder.jstatus.setText(item.getJobStatus());
        holder.jLocation.setText(item.getLocation());
    }

    public int getItemCount() {
        return approvedjobList.size();
    }

    public void setApprovedjobList(List<AppliedStatusObj> approvedjobList){
        this.approvedjobList = approvedjobList;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView JobTitle;
        public TextView JobID;
        public TextView jLocation;
        public TextView jstatus;
        public TextView jannouncement;

        public ViewHolder(View itemView) {
            super(itemView);
            JobTitle = itemView.findViewById(R.id.allJobTitle);
            JobID = itemView.findViewById(R.id.StatusJobId);
            jLocation = itemView.findViewById(R.id.allJobLocation);
            jstatus = itemView.findViewById(R.id.appluedStatus);
            jannouncement = itemView.findViewById(R.id.Instruction);

        }
    }
}
