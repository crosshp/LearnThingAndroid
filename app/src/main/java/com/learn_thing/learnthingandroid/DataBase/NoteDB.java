package com.learn_thing.learnthingandroid.DataBase;

import android.content.Context;

import com.learn_thing.learnthingandroid.Entity.Note;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Andrew on 27.02.2016.
 */
public class NoteDB {
    Context context = null;
    Realm realm = null;

    public NoteDB(Context context) {
        this.context = context;
        RealmConfiguration config = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(config);
        realm = Realm.getInstance(config);
    }

    public void saveNote(Note note) {
        realm.beginTransaction();
        realm.copyToRealm(note);
        realm.commitTransaction();
    }

    public RealmResults<Note> getAllNotes(int id) {
        RealmQuery<Note> query = realm.where(Note.class).equalTo("idSubject", id);
        RealmResults<Note> result = query.findAll();
        return result;
    }

    public List<Note> getNotes() {
        RealmQuery<Note> query = realm.where(Note.class);
        RealmResults realmResults = query.findAll();
        return realmResults.subList(0, realmResults.size());
    }
}


