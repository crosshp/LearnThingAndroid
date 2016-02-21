package com.learn_thing.learnthingandroid.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.learn_thing.learnthingandroid.R;


public class HelloActivity extends AppCompatActivity {
    Button button = null;
    static String NAME = "name";
    EditText editText = null;
    HelloActivity activity = this;
    String MY_PREFS_NAME = "name_preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_layout);
        String nameBySP = getName();
        if (nameBySP != null) {
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtra(NAME, nameBySP);
            activity.startActivity(intent);
        } else {
            button = (Button) findViewById(R.id.startButton);
            editText = (EditText) findViewById(R.id.nameEdit);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = editText.getText().toString();
                    if (name.length() != 0) {
                        Intent intent = new Intent(activity, MainActivity.class);
                        intent.putExtra(NAME, name);
                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("name", name);
                        editor.commit();
                        activity.startActivity(intent);

                    } else {
                        Toast.makeText(activity, "Введіть своє ім’я!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    public String getName() {
        String name = null;
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        name = prefs.getString("name", null);
        return name;
    }
}
