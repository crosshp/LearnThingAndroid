package com.learn_thing.learnthingandroid.Activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.learn_thing.learnthingandroid.R;

/**
 * Created by Andrew on 10.03.2016.
 */
public class PersonalActivity extends Activity {
    ImageView avatar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_layout);
        avatar = (ImageView) findViewById(R.id.profileImage);
        Bitmap bitmapFactory = BitmapFactory.decodeResource(getResources(), R.drawable.face);
        bitmapFactory = Bitmap.createScaledBitmap(bitmapFactory, 90, 90, false);
        avatar.setImageBitmap(getRoundedCornerBitmap(bitmapFactory,90));
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
