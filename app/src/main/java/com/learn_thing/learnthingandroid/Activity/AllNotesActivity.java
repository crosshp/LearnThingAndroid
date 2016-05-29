package com.learn_thing.learnthingandroid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.learn_thing.learnthingandroid.Activity.Adapters.AllNotesCardAdapter;
import com.learn_thing.learnthingandroid.Activity.Adapters.NotesCardAdapter;
import com.learn_thing.learnthingandroid.DataBase.NoteDB;
import com.learn_thing.learnthingandroid.Entity.Note;
import com.learn_thing.learnthingandroid.R;

import java.util.List;

/**
 * Created by Andrew on 12.03.2016.
 */
public class AllNotesActivity extends AppCompatActivity {
    AllNotesActivity activity = this;
    static AllNotesCardAdapter cardAdapter = null;
    RecyclerView recyclerView = null;
    FloatingActionButton button = null;
    TextView emptyNotes = null;

    public void synch(){
        cardAdapter.setData(getAllNotes());
        cardAdapter.notifyDataSetChanged();
        if (!cardAdapter.getData().isEmpty()) {
            emptyNotes.setVisibility(View.INVISIBLE);
        }
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
        recyclerView = (RecyclerView) findViewById(R.id.notesList);
        emptyNotes = (TextView) findViewById(R.id.emptyNotes);
        emptyNotes.setVisibility(View.INVISIBLE);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        cardAdapter = new AllNotesCardAdapter(getAllNotes(),this);
        recyclerView.setAdapter(cardAdapter);
        button = (FloatingActionButton) findViewById(R.id.addNoteButton);
        button.setVisibility(View.INVISIBLE);
    }

    public List<Note> getAllNotes() {
        NoteDB noteDB = new NoteDB(activity);
        List<Note> list = noteDB.getNotes();
        return list;
    }
}