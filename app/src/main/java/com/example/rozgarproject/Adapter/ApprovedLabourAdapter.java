package com.example.rozgarproject.Adapter;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rozgarproject.Models.ApprovedLabour;
import com.example.rozgarproject.Personal_job_list_details;
import com.example.rozgarproject.R;

import java.util.List;

public class ApprovedLabourAdapter extends RecyclerView.Adapter<ApprovedLabourAdapter.ViewHolder> {

    private static Personal_job_list_details activity;
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
        public ImageView Connectphone;

        public ViewHolder(View itemView) {
            super(itemView);
            LabourName = itemView.findViewById(R.id.LabourName);
            Age = itemView.findViewById(R.id.LabourAge);
            ContactNumber = itemView.findViewById(R.id.LabourNumber);
            Connectphone = itemView.findViewById(R.id.connectPhone);
            Connectphone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle("Connecting");
                    builder.setMessage("Are You Sure want to connect?");
                    builder.setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String tmp = ContactNumber.getText().toString();
                            Uri u = Uri.parse("tel:" + tmp);
                            Intent intent = new Intent(Intent.ACTION_DIAL,u);
//                            Log.d("no",ContactNumber.getText().toString() + "");
                            activity.startActivity(intent);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }
            });

        }


    }
}
