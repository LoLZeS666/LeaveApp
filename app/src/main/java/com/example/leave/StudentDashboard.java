package com.example.leave;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentDashboard extends AppCompatActivity {

    ArrayList<String[]> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        list.add(new String[]{"Personal Work", "Approved for 2 days", "Approved by Shweta Bhandari", "Pending"});
        list.add(new String[]{"Personal Work", "Approved for 2 days", "Approved by Shweta Bhandari", "Pending"});
        list.add(new String[]{"Personal Work", "Approved for 2 days", "Approved by Shweta Bhandari", "Pending"});
        list.add(new String[]{"Personal Work", "Approved for 2 days", "Approved by Shweta Bhandari", "Pending"});
        list.add(new String[]{"Personal Work", "Approved for 2 days", "Approved by Shweta Bhandari", "Pending"});

        StudentAdapter adapter = new StudentAdapter(list, this);
        RecyclerView studentDash = (RecyclerView)findViewById(R.id.studentRecyclerView);
        studentDash.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        studentDash.setLayoutManager(layoutManager);
    }
}
