package com.learn_thing.learnthingandroid.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.learn_thing.learnthingandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 27.05.2016.
 */
public class GameActivity extends Activity {
    LinearLayout line1 = null;
    LinearLayout line2 = null;
    LinearLayout line3 = null;
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
    EditText editText = null;
    Thread t = null;
    private View root;
    private int result = 0;
    List<Integer> viewNumbers = null;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int[] numbers = new int[100];
        viewNumbers = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }
        for (int i = 0; i < 9; i++) {
            viewNumbers.add(Integer.valueOf((int) (Math.random() * 100)));
        }
        Log.e("Size", String.valueOf(viewNumbers.size()));
        setContentView(R.layout.game_activity);
        root = findViewById(android.R.id.content);
        editText = (EditText) findViewById(R.id.editTextGame);
        editText.setVisibility(View.GONE);
        line1 = (LinearLayout) findViewById(R.id.line1Game);
        line2 = (LinearLayout) findViewById(R.id.line2Game);
        line3 = (LinearLayout) findViewById(R.id.line3Game);
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
        game1.setText(String.valueOf(viewNumbers.get(0)));
        game2.setText(String.valueOf(viewNumbers.get(1)));
        game3.setText(String.valueOf(viewNumbers.get(2)));
        game4.setText(String.valueOf(viewNumbers.get(3)));
        game5.setText(String.valueOf(viewNumbers.get(4)));
        game6.setText(String.valueOf(viewNumbers.get(5)));
        game7.setText(String.valueOf(viewNumbers.get(6)));
        game8.setText(String.valueOf(viewNumbers.get(7)));
        game9.setText(String.valueOf(viewNumbers.get(8)));
        progressBar = (ProgressBar) findViewById(R.id.progressBarGame);
        button = (Button) findViewById(R.id.buttonGameCheck);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nonParse = editText.getText().toString();
                if (nonParse.length() == 0) {
                    Snackbar.make(root, "Заповніть поле!", Snackbar.LENGTH_SHORT).show();
                } else {
                    String[] splitStrings = nonParse.split("\\s+|,\\s*");
                    try {
                        for (String number : splitStrings) {
                            if (viewNumbers.contains(Integer.valueOf(number))) {
                                result++;
                            }
                        }
                    } catch (NumberFormatException e) {
                        Snackbar.make(root, "Некоректні дані", Snackbar.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getBaseContext(), "Результат = " + result, Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = getSharedPreferences("result", MODE_PRIVATE).edit();
                    SharedPreferences sharedPreferences = getSharedPreferences("result", MODE_PRIVATE);
                    int number = sharedPreferences.getInt("numberOfTry", 1);
                    float resultPref = sharedPreferences.getFloat("result", 1);
                    if (result > 9) {
                        editor.putFloat("result", 1 + resultPref);
                    } else {
                        editor.putFloat("result", (result / 9) * 5 + resultPref);
                    }
                    editor.putInt("numberOfTry", number + 1);
                    editor.commit();
                    finish();
                }
            }
        });
        t = new Thread() {
            @Override
            public void run() {
                for (int i = 30; i >= 0; i--) {
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
        button.setVisibility(View.VISIBLE);
        line1.setVisibility(View.GONE);
        line2.setVisibility(View.GONE);
        line3.setVisibility(View.GONE);
        editText.setVisibility(View.VISIBLE);
        timer.setVisibility(View.GONE);
    }
}
