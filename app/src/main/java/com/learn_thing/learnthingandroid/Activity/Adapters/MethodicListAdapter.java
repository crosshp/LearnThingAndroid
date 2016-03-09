package com.learn_thing.learnthingandroid.Activity.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn_thing.learnthingandroid.Entity.Methodic;
import com.learn_thing.learnthingandroid.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 05.03.2016.
 */
public class MethodicListAdapter extends ArrayAdapter<Methodic> {
    private List<Methodic> data;
    private Context context;

    public MethodicListAdapter(Context context, List<Methodic> data) {
        super(context, R.layout.methodic_list);
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        // возвращаем количество элементов списка
        return data.size();
    }

    @Override
    public Methodic getItem(int position) {
        // получение одного элемента по индексу
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // заполнение элементов списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // задаем вид элемента списка, который мы создали высше
        View view = inflater.inflate(R.layout.methodic_list, parent, false);

        // проставляем данные для элементов
        TextView name = (TextView) view.findViewById(R.id.methodicName);
        TextView text = (TextView) view.findViewById(R.id.methodicDescription);
        ImageView img = (ImageView) view.findViewById(R.id.methodicImg);

        // получаем элемент со списка
        Methodic objectItem = data.get(position);

        // устанавливаем значения компонентам одного эелемента списка
        name.setText(objectItem.getName());
        text.setText(objectItem.getDescription());
        try (InputStream is = view.getContext().getResources().getAssets().open(objectItem.getImg())) {
            Bitmap bitmapFactory = BitmapFactory.decodeStream(is);
            bitmapFactory = Bitmap.createScaledBitmap(bitmapFactory, 90, 90, false);
            img.setImageBitmap(getRoundedCornerBitmap(bitmapFactory, 90));
        } catch (IOException e) {
            e.printStackTrace();
        }

        view.offsetLeftAndRight(35);
        view.offsetTopAndBottom(35);
        return view;
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
