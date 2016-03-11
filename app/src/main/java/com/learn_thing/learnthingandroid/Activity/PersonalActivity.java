package com.learn_thing.learnthingandroid.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn_thing.learnthingandroid.DataBase.SubjectDB;
import com.learn_thing.learnthingandroid.R;

/**
 * Created by Andrew on 10.03.2016.
 */
public class PersonalActivity extends Activity {
    ImageView avatar = null;
    PersonalActivity activity = this;
    TextView nameText = null;
    TextView rate = null;
    TextView countOfSubject = null;
    FloatingActionButton methodicButton = null;
    FloatingActionButton subjectButton = null;
    FloatingActionButton noteButton = null;
    FloatingActionButton helpButton = null;
    FloatingActionButton aboutButton = null;
    FloatingActionButton newButton = null;

    @Override
    protected void onResume() {
        super.onResume();
        SubjectDB subjectDB = new SubjectDB(activity);
        countOfSubject.setText(String.valueOf(subjectDB.getSubjectsSize()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_layout);
        avatar = (ImageView) findViewById(R.id.profileImage);
        Bitmap bitmapFactory = BitmapFactory.decodeResource(getResources(), R.drawable.face);
        bitmapFactory = Bitmap.createScaledBitmap(bitmapFactory, 90, 90, false);
        avatar.setImageBitmap(getRoundedCornerBitmap(bitmapFactory, 90));
        nameText = (TextView) findViewById(R.id.nameTextView);
        nameText.setText(getIntent().getStringExtra("name"));
        countOfSubject = (TextView) findViewById(R.id.textView3);
        SubjectDB subjectDB = new SubjectDB(activity);
        countOfSubject.setText(String.valueOf(subjectDB.getSubjectsSize()));
        rate = (TextView) findViewById(R.id.textView2);

        methodicButton = (FloatingActionButton) findViewById(R.id.fabMethodic);
        methodicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AllMethodicActivity.class);
                activity.startActivity(intent);
            }
        });
        subjectButton = (FloatingActionButton) findViewById(R.id.fabSubject);
        subjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
            }
        });
        noteButton = (FloatingActionButton) findViewById(R.id.fabNotes);
        noteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AllNotesActivity.class);
                activity.startActivity(intent);
            }
        });
        helpButton = (FloatingActionButton) findViewById(R.id.fabHelp);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, HelpActivity.class);
                activity.startActivity(intent);
            }
        });
        aboutButton = (FloatingActionButton) findViewById(R.id.fabAbout);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AboutActivity.class);
                activity.startActivity(intent);
            }
        });
        newButton = (FloatingActionButton) findViewById(R.id.fabNew);
    }


    public Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
}
