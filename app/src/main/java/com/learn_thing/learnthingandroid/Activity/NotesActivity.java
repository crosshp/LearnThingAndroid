package com.learn_thing.learnthingandroid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.learn_thing.learnthingandroid.Activity.Adapters.NotesCardAdapter;
import com.learn_thing.learnthingandroid.DataBase.NoteDB;
import com.learn_thing.learnthingandroid.Entity.Note;
import com.learn_thing.learnthingandroid.R;

import java.util.List;

/**
 * Created by Andrew on 27.02.2016.
 */
public class NotesActivity extends AppCompatActivity {
    NotesActivity activity = this;
    static NotesCardAdapter cardAdapter = null;
    RecyclerView recyclerView = null;
    FloatingActionButton button = null;
    Integer idSubject;


    @Override
    protected void onResume() {
        super.onResume();
        cardAdapter.setData(getAllNotes());
        cardAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Notes");
        idSubject = getIntent().getIntExtra("id", -1);
        recyclerView = (RecyclerView) findViewById(R.id.notesList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        cardAdapter = new NotesCardAdapter(getAllNotes());
        recyclerView.setAdapter(cardAdapter);
        button = (FloatingActionButton) findViewById(R.id.addNoteButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, NewNoteActivity.class);
                intent.putExtra("id", idSubject);
                activity.startActivity(intent);
            }
        });
    }

    public List<Note> getAllNotes() {
        NoteDB noteDB = new NoteDB(activity);
        List<Note> list = noteDB.getAllNotes(idSubject);
        return list;
    }
}