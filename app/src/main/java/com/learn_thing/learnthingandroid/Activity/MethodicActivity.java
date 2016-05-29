package com.learn_thing.learnthingandroid.Activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.learn_thing.learnthingandroid.DataBase.MethodicDB;
import com.learn_thing.learnthingandroid.Entity.Methodic;
import com.learn_thing.learnthingandroid.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Andrew on 10.03.2016.
 */
public class MethodicActivity extends AppCompatActivity {
    Context activity = this;
    Button button = null;
    Button buttonGame = null;
    int id = 0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.methodic_layout);
        id = getIntent().getIntExtra("id", 1);
        button = (Button) findViewById(R.id.checkButton);
        if(id==7) button.setVisibility(View.GONE);
        buttonGame = (Button) findViewById(R.id.buttonGame);
        buttonGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                startActivity(intent);
            }
        });
        Methodic methodic = getMethodic(id);
        ImageView image = (ImageView) findViewById(R.id.methodicImage);
        TextView nameText = (TextView) findViewById(R.id.headerCard);
        TextView descriptionText = (TextView) findViewById(R.id.methodicDescription);

        try (InputStream is = getResources().getAssets().open("imageCard" + String.valueOf(methodic.getImg()) + ".jpg")) {
            Bitmap bitmapFactory = BitmapFactory.decodeStream(is);
            image.setImageBitmap(bitmapFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*if (!methodic.isInteractive()) {
            button.setVisibility(View.INVISIBLE);
        }*/
        nameText.setText(methodic.getName());
        descriptionText.setText(methodic.getDescription());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackgroundResource(R.drawable.click);
                Intent intent = new Intent(getBaseContext(), TestActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Methodic");
    }

    public Methodic getMethodic(int id) {
        MethodicDB methodicDB = new MethodicDB(activity);
        return methodicDB.getMethodicById(id);
    }
}
