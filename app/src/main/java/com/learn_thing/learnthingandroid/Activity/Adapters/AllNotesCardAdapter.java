package com.learn_thing.learnthingandroid.Activity.Adapters;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.learn_thing.learnthingandroid.Activity.AllNotesActivity;
import com.learn_thing.learnthingandroid.DataBase.NoteDB;
import com.learn_thing.learnthingandroid.Entity.Note;
import com.learn_thing.learnthingandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 27.02.2016.
 */
public class AllNotesCardAdapter extends RecyclerView.Adapter<AllNotesCardAdapter.PersonViewHolder> {

    public void setData(List<Note> data) {
        this.data = data;
    }
    AllNotesActivity activity = null;
    List<Note> data;
    AllNotesCardAdapter adapter = this;
    NoteDB noteDB = null;

    public List<Note> getData() {
        return data;
    }


    public void addData(ArrayList<Note> arrayList) {
        for (Note subjectCard : arrayList) {
            data.add(subjectCard);
        }
    }

    public AllNotesCardAdapter(List<Note> data, AllNotesActivity activity) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notes_card, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, int i) {
        final Note objectItem = data.get(i);
        personViewHolder.name.setText(objectItem.getNoteName());
        personViewHolder.text.setText(objectItem.getNoteText());
        personViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder ad = new AlertDialog.Builder(personViewHolder.itemView.getContext());
                ad.setTitle("Увага");  // заголовок
                ad.setMessage("Ви впевнені, що хочете видалити"); // сообщение
                ad.setPositiveButton("Так", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int arg1) {
                        int position = personViewHolder.getAdapterPosition();
                        noteDB = new NoteDB(personViewHolder.itemView.getContext());
                        noteDB.deleteNote(data.get(position).getId());
                        synchNotes();
                        activity.synch();

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
        });
    }

    public void synchNotes() {
        List<Note> notes = noteDB.getNotes();
        for (int i = 0; i < notes.size(); i++) {
            noteDB.getRealm().beginTransaction();
            notes.get(i).setId(i + 1);
            noteDB.getRealm().commitTransaction();
            noteDB.saveNote(notes.get(i));
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
        TextView text;
        ImageButton button;

        PersonViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.noteName);
            text = (TextView) itemView.findViewById(R.id.noteText);
            button = (ImageButton) itemView.findViewById(R.id.noteDeleteButton);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

