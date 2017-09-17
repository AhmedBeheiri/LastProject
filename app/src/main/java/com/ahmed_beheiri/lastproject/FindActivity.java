package com.ahmed_beheiri.lastproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ahmed_beheiri.lastproject.Database.DBManager;

import java.util.ArrayList;

public class FindActivity extends AppCompatActivity {
    private EditText searcheditText;
    private Button searchbutton;
    private DBManager dbManager;
    private ArrayList<Person> resultsearch;
    private ListView listView;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        searcheditText = (EditText) findViewById(R.id.searchedittext);
        searchbutton = (Button) findViewById(R.id.searchbutton);
        listView = (ListView) findViewById(R.id.searchlistvieew);

        dbManager = new DBManager(this);
        resultsearch = new ArrayList<>();
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = searcheditText.getText().toString();
                try {
                    int id = Integer.parseInt(txt);
                    resultsearch = dbManager.selectbyID(id);
                    if (resultsearch != null) {
                        arrayAdapter = new ArrayAdapter(FindActivity.this, R.layout.listrow, resultsearch);
                        listView.setAdapter(arrayAdapter);

                    } else {
                        Toast.makeText(FindActivity.this, "Couldn't find Results with that Id", Toast.LENGTH_LONG).show();
                    }
                } catch (NumberFormatException e) {
                    resultsearch = dbManager.selectbyname(txt);
                    if (resultsearch != null) {
                        arrayAdapter = new ArrayAdapter(FindActivity.this, R.layout.listrow, resultsearch);
                        listView.setAdapter(arrayAdapter);

                    } else {
                        Toast.makeText(FindActivity.this, "Couldn't find Results with that Name", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        dbManager.close();
    }
}
