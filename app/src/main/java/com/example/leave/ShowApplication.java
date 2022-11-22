package com.example.leave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.nio.file.FileStore;

public class ShowApplication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_application);
        TextView name, start, end, reason;
        name = findViewById(R.id.name);
        start = findViewById(R.id.startDate);
        end = findViewById(R.id.endDate);
        reason = findViewById(R.id.reason);
        Intent intent = getIntent();
        name.setText(intent.getStringExtra("UID"));
        start.setText(intent.getStringExtra("Start"));
        end.setText(intent.getStringExtra("End"));
        reason.setText(intent.getStringExtra("Reason"));
        Toast.makeText(this, String.valueOf(intent.getIntExtra("cnt", 0)), Toast.LENGTH_SHORT).show();
    }
}