package com.learn_thing.learnthingandroid.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.learn_thing.learnthingandroid.Activity.Adapters.MethodicListAdapter;
import com.learn_thing.learnthingandroid.DataBase.MethodicDB;
import com.learn_thing.learnthingandroid.Entity.Methodic;
import com.learn_thing.learnthingandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 10.03.2016.
 */
public class AllMethodicActivity extends AppCompatActivity {
    ListView listView = null;
    AllMethodicActivity activity = this;


    @Override
    public void onBackPressed() {
        Toast.makeText(activity, "Тест не пройдено!", Toast.LENGTH_LONG).show();
        finish();
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
        setContentView(R.layout.all_methodic_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Methodics");
        listView = (ListView) findViewById(R.id.methodicListView);
        final MethodicListAdapter listAdapter = new MethodicListAdapter(activity, getAllMethodics());
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, MethodicActivity.class);
                intent.putExtra("id", listAdapter.getItem(position).getId());
                activity.startActivity(intent);
            }
        });
    }

    private List<Methodic> getAllMethodics() {
        MethodicDB methodicDB = new MethodicDB(activity);
        return methodicDB.getAllMethodics();
    }
}
