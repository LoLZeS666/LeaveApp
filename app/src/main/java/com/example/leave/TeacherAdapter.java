package com.example.leave;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.ViewHolder>{

    ArrayList<String[]> list = new ArrayList<>();

    public TeacherAdapter(ArrayList<String[]> list, Context context) {
        this.list = list;
        this.context = context;
    }

    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.teacher_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String[] student = list.get(position);
        holder.heading.setText(student[0]);
        holder.subheading.setText(student[1]);
        holder.approve.setText(student[2]);
        holder.status.setText(student[3]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView heading, subheading, approve, status;
        LinearLayout container;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            container = itemView.findViewById(R.id.mainTeacherContainer);
            heading = itemView.findViewById(R.id.mainTeacherHead);
            subheading = itemView.findViewById(R.id.mainTeacherSubHead);
            approve = itemView.findViewById(R.id.mainTeacherApprove);
            status = itemView.findViewById(R.id.mainTeacherStatus);
        }
    }
}

