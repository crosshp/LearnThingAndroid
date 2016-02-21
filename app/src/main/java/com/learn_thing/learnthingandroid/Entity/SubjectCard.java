package com.learn_thing.learnthingandroid.Entity;

/**
 * Created by Andrew on 21.02.2016.
 */
public class SubjectCard {
    private String name;
    private String status;
    private boolean isReminder;

    public String getName() {
        return name;
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
}
