package com.learn_thing.learnthingandroid.Activity.Adapters;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn_thing.learnthingandroid.Entity.Methodic;
import com.learn_thing.learnthingandroid.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 28.02.2016.
 */
public class MethodicAdapter extends RecyclerView.Adapter<MethodicAdapter.PersonViewHolder> {
    private View view = null;

    public void setData(List<Methodic> data) {
        this.data = data;
    }

    List<Methodic> data;

    public List<Methodic> getData() {
        return data;
    }


    public void addData(ArrayList<Methodic> arrayList) {
        for (Methodic subjectCard : arrayList) {
            data.add(subjectCard);
        }
    }

    public MethodicAdapter(List<Methodic> data) {
        this.data = data;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.methodic_card, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        view = v;
        return pvh;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        Methodic objectItem = data.get(i);
        personViewHolder.name.setText(objectItem.getName());
        personViewHolder.description.setText(objectItem.getDescription());
        try (InputStream is = view.getContext().getResources().getAssets().open(objectItem.getImg())) {
            Bitmap bitmapFactory = BitmapFactory.decodeStream(is);
            personViewHolder.img.setImageBitmap(bitmapFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        TextView description;
        ImageView img;

        PersonViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.methodicName);
            description = (TextView) itemView.findViewById(R.id.methodicDescription);
            img = (ImageView) itemView.findViewById(R.id.methodicImg);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}