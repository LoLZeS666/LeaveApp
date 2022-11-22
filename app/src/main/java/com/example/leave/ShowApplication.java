package com.example.leave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.nio.file.FileStore;
import java.util.HashMap;
import java.util.Map;

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
        String count = String.valueOf(intent.getIntExtra("cnt", 0));
        Toast.makeText(this, String.valueOf(intent.getIntExtra("cnt", 0)), Toast.LENGTH_SHORT).show();
        Button confirm = findViewById(R.id.confirm);
        RadioGroup radioGroup = findViewById(R.id.grp);
        // find the radiobutton by returned id

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                Toast.makeText(ShowApplication.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                Map<String, Object> mp = new HashMap<>();
                mp.put("pending", false);
                mp.put("status", radioButton.getText());
                database.getReference().child("Applications").child(name.getText().toString()).child(count).updateChildren(mp);
                Intent intent1 = new Intent(getApplicationContext(), TeacherDashboard.class);
                startActivity(intent1);
            }
        });
    }
}