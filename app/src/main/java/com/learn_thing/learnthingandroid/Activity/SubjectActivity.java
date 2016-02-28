package com.learn_thing.learnthingandroid.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.learn_thing.learnthingandroid.DataBase.SubjectDB;
import com.learn_thing.learnthingandroid.Entity.SubjectCard;
import com.learn_thing.learnthingandroid.R;

/**
 * Created by Andrew on 28.02.2016.
 */
public class SubjectActivity extends AppCompatActivity {
    FloatingActionButton writeNoteButton = null;
    SubjectActivity activity = this;
    SubjectCard subjectCard = null;
    ImageView image = null;
    Spinner spinner = null;
    TextView nameSubject = null;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_layout);
        image = (ImageView) findViewById(R.id.imageSubject);
        spinner = (Spinner) findViewById(R.id.statusSpinner);
        nameSubject = (TextView) findViewById(R.id.headerCard);
        id = getIntent().getIntExtra("id",0);
        writeNoteButton = (FloatingActionButton) findViewById(R.id.writeNoteButton);
        writeNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,NotesActivity.class);
                intent.putExtra("id",id);
                activity.startActivity(intent);
            }
        });
        SubjectDB subjectDB = new SubjectDB(activity);
        subjectCard = subjectDB.getSubjectById(id);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}