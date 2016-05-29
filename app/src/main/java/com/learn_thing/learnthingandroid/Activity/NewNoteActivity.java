package com.learn_thing.learnthingandroid.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.learn_thing.learnthingandroid.DataBase.NoteDB;
import com.learn_thing.learnthingandroid.Entity.Note;
import com.learn_thing.learnthingandroid.R;

import java.util.List;

/**
 * Created by Andrew on 27.02.2016.
 */
public class NewNoteActivity extends AppCompatActivity {
    NewNoteActivity activity = this;
    Button button = null;
    EditText nameEdit = null;
    EditText textEdit = null;
    Integer idSubject = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_note);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("New note");
        idSubject = getIntent().getIntExtra("id", -1);
        button = (Button) findViewById(R.id.addNewNoteButton);
        nameEdit = (EditText) findViewById(R.id.nameNoteEdit);
        textEdit = (EditText) findViewById(R.id.textNoteEdit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                String text = textEdit.getText().toString();
                if (name.length() == 0 || text.length() == 0) {
                    Toast.makeText(activity, "Заповніть поля!", Toast.LENGTH_SHORT).show();
                } else {
                    NoteDB noteDB = new NoteDB(activity);
                    Note note = new Note();
                    note.setNoteText(text);
                    note.setNoteName(name);
                    note.setIdSubject(idSubject);
                    note.setId(noteDB.getNotes().size() + 1);
                    noteDB.saveNote(note);
                    finish();
                }
            }
        });
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
