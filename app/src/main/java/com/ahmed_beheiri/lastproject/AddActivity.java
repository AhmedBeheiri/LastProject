package com.ahmed_beheiri.lastproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.ahmed_beheiri.lastproject.Database.DBManager;

public class AddActivity extends AppCompatActivity {

    private EditText name;
    private Spinner Gender;
    private RadioButton staff, student;
    private SeekBar agesk;
    private Button save, cancel;
    private String type;
    private int age;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = (EditText) findViewById(R.id.nameed);
        Gender = (Spinner) findViewById(R.id.spinner);
        staff = (RadioButton) findViewById(R.id.staff);
        student = (RadioButton) findViewById(R.id.student);
        agesk = (SeekBar) findViewById(R.id.ageseekbar);
        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);
        dbManager = new DBManager(this);
        agesk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                age = progress + 18;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddActivity.this, DisplayActivity.class);
                startActivity(i);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (staff.isSelected()) {
                    type = "staff";
                } else {
                    type = "student";
                }
                dbManager.Insert(new Person(name.getText().toString(), type, Gender.getSelectedItem().toString(), age));

                Intent i = new Intent(AddActivity.this, DisplayActivity.class);
                startActivity(i);
                finish();


            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        dbManager.close();
    }
}
