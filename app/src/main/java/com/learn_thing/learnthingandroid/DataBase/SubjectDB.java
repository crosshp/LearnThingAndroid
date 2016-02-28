package com.learn_thing.learnthingandroid.DataBase;

import android.content.Context;

import com.learn_thing.learnthingandroid.Entity.SubjectCard;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Andrew on 28.02.2016.
 */
public class SubjectDB {
    Context context = null;
    Realm realm = null;

    public SubjectDB(Context context) {
        this.context = context;
        RealmConfiguration config = new RealmConfiguration.Builder(context).build();
        //Realm.deleteRealm(config);
        Realm.setDefaultConfiguration(config);

        realm = Realm.getInstance(config);
    }

    public void saveSubject(SubjectCard subjectCard) {

        realm.beginTransaction();
        subjectCard.setId(getNextKey());
        realm.copyToRealm(subjectCard);
        realm.commitTransaction();
    }

    public int getNextKey() {
        try {
            return realm.where(SubjectCard.class).max("id").intValue() + 1;
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public List<SubjectCard> getAllRealmResultSubjects() {
        RealmQuery<SubjectCard> query = realm.where(SubjectCard.class);
        RealmResults<SubjectCard> result = query.findAll();
        result.sort("id");
        return result.subList(0, result.size());
    }

    public void deleteById(int id) {
        RealmResults<SubjectCard> result = realm.where(SubjectCard.class).equalTo("id",id).findAll();
        realm.beginTransaction();
        result.removeLast();
        realm.commitTransaction();
    }

}
