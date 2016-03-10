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
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Subjects");
        setSupportActionBar(toolbar);
        initializeMenu();
        final Intent intent = getIntent();
        emptySubject = (TextView) findViewById(R.id.emptySubjects);
        name = intent.getStringExtra(HelloActivity.NAME);
        Toast.makeText(activity, name, Toast.LENGTH_LONG).show();
        recyclerView = (RecyclerView) findViewById(R.id.cardList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        SubjectDB subjectDB = new SubjectDB(activity);
        cardAdapter = new SubjectCardAdapter(subjectDB.getAllRealmResultSubjects());
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
    }

    public void initializeMenu() {
        ProfileDrawerItem profileDrawerItem = new ProfileDrawerItem().withEmail("your@mail.com").withName("Andrew").withIcon(getResources().getDrawable(R.drawable.ic_account));
        final AccountHeader accountHeader = new AccountHeaderBuilder().withActivity(this).withHeaderBackground(R.drawable.imagecard3).addProfiles(profileDrawerItem).build();

        final SecondaryDrawerItem menu_item1 = new SecondaryDrawerItem().withName(R.string.menu_item1).withIdentifier(1).withIcon(R.drawable.ic_view_list);
        final SecondaryDrawerItem menu_item2 = new SecondaryDrawerItem().withName(R.string.menu_item2).withIdentifier(2);//.withIcon(R.drawable.ic_menu_aid).withEnabled(false);
        final SecondaryDrawerItem menu_item3 = new SecondaryDrawerItem().withName(R.string.menu_item3).withIdentifier(3);//.withIcon(R.drawable.ic_menu_about).withEnabled(false);
        final SecondaryDrawerItem menu_item4 = new SecondaryDrawerItem().withName(R.string.menu_item4).withIdentifier(4);//.withIcon(R.drawable.ic_menu_help).withEnabled(false);

        drawer = new DrawerBuilder().
                withActivity(this).
                withDisplayBelowToolbar(true).withToolbar(toolbar).
                withActionBarDrawerToggleAnimated(true).
                addDrawerItems(menu_item1, menu_item2, menu_item3, menu_item4).
                withAccountHeader(accountHeader).
                withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        switch (iDrawerItem.getIdentifier()) {
                            case 1: {
                                Intent intent = new Intent(activity, AllMethodicActivity.class);
                                activity.startActivity(intent);
                                break;
                            }
                            case 2: {
                                Intent intent = new Intent(activity, MethodicActivity.class);
                                activity.startActivity(intent);
                                break;
                            }
                            case 3: {
                                Intent intent = new Intent(activity, PersonalActivity.class);
                                activity.startActivity(intent);
                                break;
                            }
                            case 4: {

                                break;
                            }
                        }
                        return true;
                    }
                }).withOnDrawerListener(new Drawer.OnDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View view) {

            }

            @Override
            public void onDrawerSlide(View view, float v) {
                InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
            }
        }).build();
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
