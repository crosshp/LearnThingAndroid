package com.learn_thing.learnthingandroid.Activity.Adapters;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.learn_thing.learnthingandroid.DataBase.SubjectDB;
import com.learn_thing.learnthingandroid.Entity.SubjectCard;
import com.learn_thing.learnthingandroid.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Andrew on 23.07.2015.
 */
public class SubjectCardAdapter extends RecyclerView.Adapter<SubjectCardAdapter.PersonViewHolder> {
    private View view = null;

    public void setData(List<SubjectCard> data) {
        this.data = data;
    }

    List<SubjectCard> data;
    SubjectCardAdapter adapter = this;

    public List<SubjectCard> getData() {
        return data;
    }

    public void addData(ArrayList<SubjectCard> arrayList) {
        for (SubjectCard subjectCard : arrayList) {
            data.add(subjectCard);
        }
    }

    public SubjectCardAdapter(List<SubjectCard> data) {
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
    public void onBindViewHolder(final PersonViewHolder personViewHolder, int i) {
        SubjectCard objectItem = data.get(i);
        personViewHolder.name.setText(objectItem.getName());
        personViewHolder.statusValue.setText(objectItem.getStatus());
        personViewHolder.checkBox.setChecked(false);
        int randomPaint = (int) (Math.random() * 5) + 1;
        try (InputStream is = view.getContext().getResources().getAssets().open("imageCard"+randomPaint+".jpg")) {
            Bitmap bitmapFactory = BitmapFactory.decodeStream(is);
            personViewHolder.imageView.setImageBitmap(bitmapFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }
        personViewHolder.closeButton.setOnClickListener(new View.OnClickListener() {

            public void showAlert() {
                AlertDialog.Builder ad = new AlertDialog.Builder(personViewHolder.itemView.getContext());
                ad.setTitle("Увага");  // заголовок
                ad.setMessage("Ви впевнені, що хочете видалити"); // сообщение
                ad.setPositiveButton("Так", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        int position = personViewHolder.getAdapterPosition();
                        SubjectDB subjectDB = new SubjectDB(personViewHolder.itemView.getContext());
                        subjectDB.deleteById(data.get(position).getId());
                        adapter.setData(subjectDB.getAllRealmResultSubjects());
                        adapter.notifyDataSetChanged();
                    }
                });
                ad.setNegativeButton("Ні", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        return;
                    }
                });
                ad.setCancelable(true);
                ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        return;
                    }
                }).show();
            }

            @Override
            public void onClick(View v) {
                showAlert();
            }
        });

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
        ImageButton closeButton = null;

        PersonViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.headerCard);
            statusValue = (TextView) itemView.findViewById(R.id.statusValue);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            imageView = (ImageView) itemView.findViewById(R.id.imageTechnic);
            closeButton = (ImageButton) itemView.findViewById(R.id.deleteCardButton);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}



