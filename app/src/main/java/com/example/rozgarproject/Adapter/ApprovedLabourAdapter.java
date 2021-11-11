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

    private Personal_job_list_details context;
    private List<ApprovedLabour> approvedLabourList;

    public ApprovedLabourAdapter(Personal_job_list_details context, List<ApprovedLabour> approvedLabourList) {
        this.context = context;
        this.approvedLabourList = approvedLabourList;
    }

    @NonNull
    @Override
    public ApprovedLabourAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_personal_job_list_details,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApprovedLabour approvedLabour = approvedLabourList.get(position);

        holder.LabourName.setText(approvedLabour.getName());
        holder.Age.setText(approvedLabour.getAge());
        holder.ContactNumber.setText(approvedLabour.getContactNumber());

    }

    @Override
    public int getItemCount() {
        return approvedLabourList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView LabourName;
        public TextView Age;
        public TextView ContactNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            LabourName = itemView.findViewById(R.id.LabourName);
            Age = itemView.findViewById(R.id.LabourAge);
            ContactNumber = itemView.findViewById(R.id.LabourNumber);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
