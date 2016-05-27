package com.learn_thing.learnthingandroid.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.learn_thing.learnthingandroid.Activity.Adapters.RecyclerClickListener;
import com.learn_thing.learnthingandroid.Activity.Adapters.SubjectCardAdapter;
import com.learn_thing.learnthingandroid.DataBase.SubjectDB;
import com.learn_thing.learnthingandroid.Entity.SubjectCard;
import com.learn_thing.learnthingandroid.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {
    MainActivity activity = this;
    static SubjectCardAdapter cardAdapter = null;
    RecyclerView recyclerView = null;
    String name = null;
    Drawer drawer = null;
    Toolbar toolbar = null;
    static TextView emptySubject = null;
    public static Integer[] img = {R.drawable.imagecard1, R.drawable.imagecard2, R.drawable.imagecard3,
            R.drawable.imagecard4, R.drawable.imagecard5, R.drawable.imagecard6,
            R.drawable.imagecard7, R.drawable.imagecard8};

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
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Subjects");
        setSupportActionBar(toolbar);
        emptySubject = (TextView) findViewById(R.id.emptySubjects);
        recyclerView = (RecyclerView) findViewById(R.id.cardList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        SubjectDB subjectDB = new SubjectDB(activity);
        cardAdapter = new SubjectCardAdapter(subjectDB.getAllRealmResultSubjects(),getApplicationContext());
        cardAdapter.setActivity(activity);
        recyclerView.setAdapter(cardAdapter);
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(activity.getResources().getColor(R.color.status_bar));
        }

        recyclerView.addOnItemTouchListener(new RecyclerClickListener(this) {
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }

            @Override
            public void onItemClick(RecyclerView recyclerView, View itemView, int position) {
                Intent intentForNote = new Intent(activity, SubjectActivity.class);
                SubjectCard subjectCard = cardAdapter.getData().get(position);
                intentForNote.putExtra("id", subjectCard.getId());
                activity.startActivity(intentForNote);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, CreateSubjectActivity.class);
                view.getContext().startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void onResume() {
        super.onResume();
        SubjectDB subjectDB = new SubjectDB(activity);
        cardAdapter.setData(subjectDB.getAllRealmResultSubjects());
        cardAdapter.notifyDataSetChanged();
        checkEmpty();
    }

    public static void checkEmpty() {
        if (!cardAdapter.getData().isEmpty()) {
            emptySubject.setVisibility(View.INVISIBLE);
        } else {
            emptySubject.setVisibility(View.VISIBLE);
        }
    }

}
