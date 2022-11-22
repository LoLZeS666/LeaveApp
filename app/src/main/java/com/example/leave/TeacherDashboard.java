package com.example.leave;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeacherDashboard extends AppCompatActivity {
    ArrayList<String[]> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        list.add(new String[]{"Personal Work", "Approved for 2 days", "Asked by Ashlesh Jain", "Pending"});
        list.add(new String[]{"Personal Work", "Approved for 2 days", "Asked by Ashlesh Jain", "Pending"});
        list.add(new String[]{"Personal Work", "Approved for 2 days", "Asked by Ashlesh Jain", "Pending"});
        list.add(new String[]{"Personal Work", "Approved for 2 days", "Asked by Ashlesh Jain", "Pending"});
        list.add(new String[]{"Personal Work", "Approved for 2 days", "Asked by Ashlesh Jain", "Pending"});

        TeacherAdapter adapter = new TeacherAdapter(list, this);
        RecyclerView teacherDash = (RecyclerView)findViewById(R.id.teacherRecyclerView);
        teacherDash.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        teacherDash.setLayoutManager(layoutManager);
    }
}
