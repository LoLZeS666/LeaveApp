package com.example.leave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.nio.file.FileStore;
import java.util.HashMap;
import java.util.Map;

public class ShowApplication extends AppCompatActivity {
    FirebaseDatabase database;
    String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_application);
        TextView name, start, end, reason, roll;
        database = FirebaseDatabase.getInstance();
        name = findViewById(R.id.name);
        start = findViewById(R.id.startDate);
        end = findViewById(R.id.endDate);
        reason = findViewById(R.id.reason);
        reason.setMovementMethod(new ScrollingMovementMethod());
        roll = findViewById(R.id.roll);
        Intent intent = getIntent();
        UID = intent.getStringExtra("UID");
        database.getReference().child("Users").child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nm = snapshot.child("name").getValue(String.class);
                String rll  = snapshot.child("roll").getValue(String.class);
                name.setText(nm);
                roll.setText(rll);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        start.setText(intent.getStringExtra("Start"));
        end.setText(intent.getStringExtra("End"));
        reason.setText(intent.getStringExtra("Reason"));
        String count = String.valueOf(intent.getIntExtra("cnt", 0));
        Toast.makeText(this, String.valueOf(intent.getIntExtra("cnt", 0)), Toast.LENGTH_SHORT).show();
        Button confirm = findViewById(R.id.confirm);
        RadioGroup radioGroup = findViewById(R.id.grp);
        Button show = findViewById(R.id.showdoc);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), showProof.class);
                intent1.putExtra("UID", intent.getStringExtra("UID"));
                intent1.putExtra("cnt", intent.getIntExtra("cnt", 0));
                startActivity(intent1);
            }
        });
        // find the radiobutton by returned id

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                Toast.makeText(ShowApplication.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
                Map<String, Object> mp = new HashMap<>();
                mp.put("pending", false);
                mp.put("status", radioButton.getText());
                database.getReference().child("Applications").child(UID).child(count).updateChildren(mp);
                Intent intent1 = new Intent(getApplicationContext(), TeacherDashboard.class);
                startActivity(intent1);
            }
        });
    }
}