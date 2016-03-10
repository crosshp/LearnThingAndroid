package com.learn_thing.learnthingandroid.Activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.learn_thing.learnthingandroid.Activity.Adapters.MethodicAdapter;
import com.learn_thing.learnthingandroid.Activity.Adapters.MethodicListAdapter;
import com.learn_thing.learnthingandroid.Activity.Adapters.SubjectCardAdapter;
import com.learn_thing.learnthingandroid.DataBase.SubjectDB;
import com.learn_thing.learnthingandroid.Entity.Methodic;
import com.learn_thing.learnthingandroid.Entity.SubjectCard;
import com.learn_thing.learnthingandroid.R;
import com.learn_thing.learnthingandroid.VersionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 28.02.2016.
 */
public class SubjectActivity extends Activity {
    FloatingActionButton writeNoteButton = null;
    SubjectActivity activity = this;
    SubjectCard subjectCard = null;
    ImageView image = null;
    Spinner spinner = null;
    RecyclerView listView = null;
    String currentStatus = "";
    int id = 0;
    CollapsingToolbarLayout collapsingToolbar;
    MethodicAdapter adapter;

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

        setContentView(R.layout.activity_animate_toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbar.setExpandedTitleColor(Color.WHITE);

        listView = (RecyclerView) findViewById(R.id.scrollableview);

        List<String> listData = new ArrayList<String>();
        int ct = 0;
        for (int i = 0; i < VersionModel.data.length * 2; i++) {
            listData.add(VersionModel.data[ct]);
            ct++;
            if (ct == VersionModel.data.length) {
                ct = 0;
            }
        }

        if (adapter == null) {
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            listView.setLayoutManager(llm);
            adapter = new MethodicAdapter(getAllMethodics());
            listView.setAdapter(adapter);
        }

        image = (ImageView) findViewById(R.id.header);

        spinner = (Spinner) findViewById(R.id.statusSpinner);
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
        image.setImageResource(MainActivity.img[subjectCard.getImg()]);
        collapsingToolbar.setTitle(subjectCard.getName());
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
            methodic.setDescription("Description gjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" + i);
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
