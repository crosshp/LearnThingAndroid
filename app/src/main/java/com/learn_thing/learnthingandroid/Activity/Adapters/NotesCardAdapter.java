package com.learn_thing.learnthingandroid.Activity.Adapters;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learn_thing.learnthingandroid.Entity.Note;
import com.learn_thing.learnthingandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 27.02.2016.
 */
public class NotesCardAdapter extends RecyclerView.Adapter<NotesCardAdapter.PersonViewHolder> {
    private View view = null;

    public void setData(List<Note> data) {
        this.data = data;
    }

    List<Note> data;

    public List<Note> getData() {
        return data;
    }


    public void addData(ArrayList<Note> arrayList) {
        for (Note subjectCard : arrayList) {
            data.add(subjectCard);
        }
    }

    public NotesCardAdapter(List<Note> data) {
        this.data = data;
        System.out.println(data.size());
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notes_card, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        view = v;
        return pvh;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        Note objectItem = data.get(i);
        personViewHolder.name.setText(objectItem.getNoteName());
        personViewHolder.text.setText(objectItem.getNoteText());
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
        TextView text;

        PersonViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.noteName);
            text = (TextView) itemView.findViewById(R.id.noteText);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

