package com.learn_thing.learnthingandroid.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.learn_thing.learnthingandroid.DataBase.SubjectDB;
import com.learn_thing.learnthingandroid.Entity.SubjectCard;
import com.learn_thing.learnthingandroid.R;

/**
 * Created by Andrew on 28.02.2016.
 */
public class CreateSubjectActivity extends AppCompatActivity {
    EditText nameSubject = null;
    EditText motivationEdit = null;
    Spinner spinner = null;
    Button button = null;
    CreateSubjectActivity activity = this;
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
        setContentView(R.layout.add_subject);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Subject");
        nameSubject = (EditText) findViewById(R.id.newSubjectName);
        motivationEdit = (EditText) findViewById(R.id.motivationEdit);
        spinner = (Spinner) findViewById(R.id.spinner);
        button = (Button) findViewById(R.id.startButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameSubject.getText().toString();
                String motivation = motivationEdit.getText().toString();
                if (name.length() == 0 || motivation.length() == 0) {
                    Toast.makeText(activity, "Заповніть всі поля!", Toast.LENGTH_SHORT).show();
                } else {
                    SubjectCard subjectCard = new SubjectCard();
                    subjectCard.setIsReminder(false);
                    subjectCard.setStatus(getResources().getStringArray(R.array.status_array)[0]);
                    subjectCard.setName(name);
                    subjectCard.setMotivation(motivation);
                    SubjectDB subjectDB = new SubjectDB(activity);
                    subjectDB.saveSubject(subjectCard);
                    finish();
                }
            }
        });
    }
}
