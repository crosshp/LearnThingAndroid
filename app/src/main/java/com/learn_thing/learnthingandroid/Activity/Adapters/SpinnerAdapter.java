package com.learn_thing.learnthingandroid.Activity.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn_thing.learnthingandroid.Activity.CreateSubjectActivity;
import com.learn_thing.learnthingandroid.R;

/**
 * Created by Andrew on 10.03.2016.
 */
public class SpinnerAdapter extends ArrayAdapter<String> {
    CreateSubjectActivity activity;
    String[] imgName = {"Алохус", "Франчезис", "Санвояж", "Фуркаах", "Кинесит", "Рубістон", "Лоліпоп", "Орнатік"};
    Integer[] img = {R.drawable.imagespinnercard1, R.drawable.imagespinnercard2, R.drawable.imagespinnercard3,
            R.drawable.imagespinnercard4, R.drawable.imagespinnercard5, R.drawable.imagespinnercard6,
            R.drawable.imagespinnercard7, R.drawable.imagespinnercard8};

    public SpinnerAdapter(CreateSubjectActivity context) {
        super(context, R.layout.spinner_item);
        this.activity = context;
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public String getItem(int position) {
        return imgName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_item, parent, false);
        ImageView imageView = (ImageView) row.findViewById(R.id.imgSpinner);
        TextView textView = (TextView) row.findViewById(R.id.imgSpinnerName);
        imageView.setImageResource(img[position]);
        textView.setText(imgName[position]);
        return row;
    }
}

