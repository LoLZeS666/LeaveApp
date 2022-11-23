package com.example.leave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NewApplication extends AppCompatActivity {

    final Calendar myCalendar1 = Calendar.getInstance();
    final Calendar myCalendar2 = Calendar.getInstance();

    EditText editText1, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_application);

//        Spinner spinner = (Spinner) findViewById(R.id.docSpinner);
//// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.documents, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
////        spinner.setPrompt("choose_doc");
//        spinner.setAdapter(adapter);

        editText1 = (EditText) findViewById(R.id.startDate);
        editText2 = (EditText) findViewById(R.id.endDate);
        DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar1.set(Calendar.YEAR, year);
                myCalendar1.set(Calendar.MONTH, month);
                myCalendar1.set(Calendar.DAY_OF_MONTH, day);

                updateLabel1();
            }
        };
        DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, month);
                myCalendar2.set(Calendar.DAY_OF_MONTH, day);

                updateLabel2();
            }
        };

        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(NewApplication.this, date1, myCalendar1.get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH), myCalendar1.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(NewApplication.this, date2, myCalendar2.get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH), myCalendar2.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Button apply = findViewById(R.id.applyButton);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText start, end, reason;
                Spinner type;
                start = findViewById(R.id.startDate);
                end = findViewById(R.id.endDate);
                reason = findViewById(R.id.reason);
                type = findViewById(R.id.docSpinner);
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String UID = mAuth.getUid();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                application app = new application(start.getText().toString(), end.getText().toString(), reason.getText().toString(),
                        type.getSelectedItem().toString(), "Pending", true, 0);
                Map<String, Object> mp = new HashMap<>();
                database.getReference().child("Users").child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Integer cnt = snapshot.child("num_app").getValue(Integer.class);
                        mp.put("num_app", ++cnt);
                        database.getReference().child("Users").child(UID).updateChildren(mp);
                        database.getReference().child("Applications").child(UID).child(cnt.toString()).setValue(app);
                        Toast.makeText(NewApplication.this, "Application Submitted to Teacher!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), studentLanding.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        Button cancel = findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), studentLanding.class);
                startActivity(intent);
            }
        });


    }

    private void updateLabel1() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        editText1.setText(dateFormat.format(myCalendar1.getTime()));


    }

    private void updateLabel2() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        editText2.setText(dateFormat.format(myCalendar2.getTime()));
    }

}