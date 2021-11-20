package com.example.rozgarproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobPostAdapter extends RecyclerView.Adapter<JobPostAdapter.JobsViewAdapter> {
    List<JobPost>list;
    Context context;
    public JobPostAdapter(List<JobPost>list,Context context){
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public JobsViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.personal_job_list,parent,false);
        return new JobsViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobsViewAdapter holder, int position) {
            JobPost currItem = list.get(position);
            holder.JobTitle.setText(currItem.getJobTitle());
            holder.JobDate.setText(currItem.getJobDate());
            holder.NumberofWorkers.setText(currItem.getNumberofWorkers());
            holder.JobID.setText(currItem.getJobID());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class JobsViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView JobTitle,JobDate,NumberofWorkers,JobID;

        public JobsViewAdapter(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            JobTitle = itemView.findViewById(R.id.JobTitle);
            JobDate = itemView.findViewById(R.id.JobDate);
            NumberofWorkers = itemView.findViewById(R.id.WorkersNumber);
            JobID = itemView.findViewById(R.id.PostJobID);
        }

        @Override
        public void onClick(View view) {
            int position = this.getAdapterPosition();
            JobPost currItem = list.get(position);
            String title = currItem.getJobTitle();
            String jobDate = currItem.getJobDate();
            String numberOfWorkers = currItem.getNumberofWorkers();
            String JobpostID = currItem.getJobID();


            Intent intent = new Intent(context,Personal_job_list_details.class);
            intent.putExtra("title",title);
            intent.putExtra("date",jobDate);
            intent.putExtra("numberOfWorkers",numberOfWorkers);
            intent.putExtra("JobPostID",JobpostID);
//            Toast.makeText(context, ""+JobpostID, Toast.LENGTH_SHORT).show();
            context.startActivity(intent);
        }
    }
}
