package com.learn_thing.learnthingandroid.Entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Andrew on 21.02.2016.
 */
public class SubjectCard extends RealmObject {
    private String name;
    private String status;
    private boolean isReminder;
    private String motivation;

    @PrimaryKey
    private int id;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isReminder() {
        return isReminder;
    }

    public void setIsReminder(boolean isReminder) {
        this.isReminder = isReminder;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

}
