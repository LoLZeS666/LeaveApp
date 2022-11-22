package com.example.leave;

import android.content.Context;
import android.content.Intent;
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

import kotlin.Triple;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.ViewHolder> {

    ArrayList<Triple<application, String, Integer>> list = new ArrayList<>();

    public TeacherAdapter(ArrayList<Triple<application, String, Integer>> list, Context context) {
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
        application student = list.get(position).getFirst();
        holder.heading.setText(student.getReason().toString());
        holder.subheading.setText("From: " + student.getStart().toString());
        holder.approve.setText("To: " + student.getEnd().toString());
        holder.status.setText("Pending");
        String UID = list.get(position).getSecond();
        int cnt = list.get(position).getThird();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowApplication.class);
                intent.putExtra("UID", UID);
                intent.putExtra("Start", student.getStart().toString());
                intent.putExtra("End", student.getEnd().toString());
                intent.putExtra("Reason", student.getReason().toString());
                intent.putExtra("cnt", cnt);
                context.startActivity(intent);
            }
        });

        //todo change how to set status#FF5722
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

