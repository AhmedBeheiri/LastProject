package com.ahmed_beheiri.lastproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ahmed_beheiri.lastproject.Database.DBManager;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ArrayList<Person> personarrayList;
    private ArrayList<String> stringArrayList;
    private ListView personlistview;
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        dbManager = new DBManager(this);
        personarrayList = new ArrayList<>();
        stringArrayList = new ArrayList<>();
        personlistview = (ListView) findViewById(R.id.displayListview);
        personarrayList = dbManager.select("");
        if (personarrayList != null) {
            adapter = new ArrayAdapter(this, R.layout.listrow, personarrayList);
            personlistview.setAdapter(adapter);

        } else {
            Toast.makeText(this, "Empty list please Add some people", Toast.LENGTH_LONG).show();
        }


        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.myFAB);
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(i);
                finish();
            }
        });


        personlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogshow(view, position);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sortname:
                personarrayList.clear();
                personarrayList = dbManager.select("Name");
                adapter = new ArrayAdapter(this, R.layout.listrow, personarrayList);
                personlistview.setAdapter(adapter);
                break;
            case R.id.sortage:
                personarrayList.clear();
                personarrayList = dbManager.select("Age");
                adapter = new ArrayAdapter(this, R.layout.listrow, personarrayList);
                personlistview.setAdapter(adapter);
                break;
            case R.id.find:
                Intent i = new Intent(this, FindActivity.class);
                startActivity(i);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    public void dialogshow(View v, final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.title));
        builder.setMessage(getResources().getString(R.string.message) + personarrayList.get(position));
        builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbManager.delete(personarrayList.get(position).getId());
                personarrayList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        dbManager.close();
    }
}
