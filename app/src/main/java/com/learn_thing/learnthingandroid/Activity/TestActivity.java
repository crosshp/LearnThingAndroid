package com.learn_thing.learnthingandroid.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.learn_thing.learnthingandroid.R;

/**
 * Created by Andrew on 20.02.2016.
 */
public class TestActivity extends AppCompatActivity {
    Button nextButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        nextButton = (Button) findViewById(R.id.action_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
