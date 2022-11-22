package com.example.leave;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    ArrayList<application> list = new ArrayList<>();

    public StudentAdapter(ArrayList<application> list, Context context) {
        this.list = list;
        this.context = context;
    }

    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        application student = list.get(position);
        holder.heading.setText(student.getReason().toString());
        holder.subheading.setText("From: " + student.getStart().toString());
        holder.approve.setText("To: " + student.getEnd().toString());
        holder.status.setText(student.getStatus());
        //todo change how to set status
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView heading, subheading, approve, status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            heading = itemView.findViewById(R.id.mainStudentHead);
            subheading = itemView.findViewById(R.id.mainStudentSubHead);
            approve = itemView.findViewById(R.id.mainStudentApprove);
            status = itemView.findViewById(R.id.mainStudentStatus);
        }
    }
}

