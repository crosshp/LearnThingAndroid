package com.learn_thing.learnthingandroid.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.learn_thing.learnthingandroid.R;

/**
 * Created by Andrew on 27.05.2016.
 */
public class GameActivity extends Activity {
    TextView game1 = null;
    TextView game2 = null;
    TextView game3 = null;
    TextView game4 = null;
    TextView game5 = null;
    TextView game6 = null;
    TextView game7 = null;
    TextView game8 = null;
    TextView game9 = null;
    TextView timer = null;
    ProgressBar progressBar = null;
    Button button = null;
    Thread t = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        game1 = (TextView) findViewById(R.id.game1);
        game2 = (TextView) findViewById(R.id.game2);
        game3 = (TextView) findViewById(R.id.game3);
        game4 = (TextView) findViewById(R.id.game4);
        game5 = (TextView) findViewById(R.id.game5);
        game6 = (TextView) findViewById(R.id.game6);
        game7 = (TextView) findViewById(R.id.game7);
        game8 = (TextView) findViewById(R.id.game8);
        game9 = (TextView) findViewById(R.id.game9);
        timer = (TextView) findViewById(R.id.timer);
        progressBar = (ProgressBar) findViewById(R.id.progressBarGame);
        button = (Button) findViewById(R.id.buttonGameCheck);
        button.setEnabled(false);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        t = new Thread() {
            @Override
            public void run() {
                    for (int i = 15; i >= 0; i--) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        final int finalI = i;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                timer.setText(String.valueOf(finalI));
                            }
                        });

                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            visibility();
                        }
                    });
                }
        };
        t.start();
    }

    private void visibility() {
        progressBar.setVisibility(View.GONE);
        button.setEnabled(true);
    }
}
