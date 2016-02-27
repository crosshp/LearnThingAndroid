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
        //   Realm.deleteRealm(config);
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
        return realm.where(SubjectCard.class).max("id").intValue()+1;
    }

    public List<SubjectCard> getAllRealmResultSubjects() {
        RealmQuery<SubjectCard> query = realm.where(SubjectCard.class);
        RealmResults<SubjectCard> result = query.findAll();
        return result.subList(0, result.size());
    }

}
