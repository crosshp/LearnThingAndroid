package com.learn_thing.learnthingandroid.Activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import com.learn_thing.learnthingandroid.Entity.SubjectCard;
import com.learn_thing.learnthingandroid.R;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 23.07.2015.
 */
public class SubjectCardAdapter extends RecyclerView.Adapter<SubjectCardAdapter.PersonViewHolder> {
    private View view = null;

    List<SubjectCard> data;

    public List<SubjectCard> getData() {
        return data;
    }

    SubjectCardAdapter() {
        data = new ArrayList();
        for (int i = 0; i < 15; i++) {
            data.add(new SubjectCard());
        }
    }

    public void addData(ArrayList<SubjectCard> arrayList) {
        for (SubjectCard subjectCard : arrayList) {
            data.add(subjectCard);
        }
    }

    SubjectCardAdapter(List<SubjectCard> data) {
        this.data = data;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.predmet_card, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        view = v;
        return pvh;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        SubjectCard objectItem = data.get(i);
        personViewHolder.name.setText("Subject " + i);
        personViewHolder.statusValue.setText("Ololo " + i);
        personViewHolder.checkBox.setChecked(false);
        try (InputStream is = view.getContext().getResources().getAssets().open("pomadoro.jpg")) {
            Bitmap bitmapFactory = BitmapFactory.decodeStream(is);
            personViewHolder.imageView.setImageBitmap(bitmapFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onG(View v) {

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView statusValue;
        CheckBox checkBox = null;
        ImageView imageView = null;

        PersonViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.headerCard);
            statusValue = (TextView) itemView.findViewById(R.id.statusValue);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            imageView = (ImageView) itemView.findViewById(R.id.imageTechnic);

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}



