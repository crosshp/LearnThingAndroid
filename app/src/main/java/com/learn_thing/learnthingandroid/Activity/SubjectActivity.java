package com.learn_thing.learnthingandroid.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.learn_thing.learnthingandroid.Activity.Adapters.MethodicAdapter;
import com.learn_thing.learnthingandroid.DataBase.SubjectDB;
import com.learn_thing.learnthingandroid.Entity.Methodic;
import com.learn_thing.learnthingandroid.Entity.SubjectCard;
import com.learn_thing.learnthingandroid.R;

import java.util.ArrayList;
import java.util.List;

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
    RecyclerView recyclerView = null;
    MethodicAdapter adapter = null;
    String currentStatus = "";
    int id = 0;

    @Override
    protected void onPause() {
        super.onPause();
        SubjectDB subjectDB = new SubjectDB(activity);
        subjectDB.beginTransaction();
        subjectCard.setStatus(currentStatus);
        subjectDB.getRealm().copyToRealmOrUpdate(subjectCard);
        subjectDB.commitTransaction();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subject_layout);
        image = (ImageView) findViewById(R.id.imageSubject);
        spinner = (Spinner) findViewById(R.id.statusSpinner);
        nameSubject = (TextView) findViewById(R.id.headerCard);
        id = getIntent().getIntExtra("id", 0);
        writeNoteButton = (FloatingActionButton) findViewById(R.id.writeNoteButton);
        writeNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, NotesActivity.class);
                intent.putExtra("id", id);
                activity.startActivity(intent);
            }
        });
        SubjectDB subjectDB = new SubjectDB(activity);
        subjectCard = subjectDB.getSubjectById(id);
        nameSubject.setText(subjectCard.getName());
        spinner.setSelection(getPositionInsSpinner(subjectCard.getStatus()));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentStatus = getResources().getStringArray(R.array.status_array)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.notesList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapter = new MethodicAdapter(getAllMethodics());
        recyclerView.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Subject");
    }

    public int getPositionInsSpinner(String s) {
        String[] array = getResources().getStringArray(R.array.status_array);
        int i = 0;
        for (String string : array) {
            if (string.equals(s)) {
                return i;
            }
            i++;
        }
        return 0;
    }

    private List<Methodic> getAllMethodics() {
        List<Methodic> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Methodic methodic = new Methodic();
            methodic.setName("Ololo " + i);
            methodic.setDescription("Description " + i);
            methodic.setImg("menu.jpg");
            list.add(methodic);
        }
        return list;
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
