package com.learn_thing.learnthingandroid.Entity;

/**
 * Created by Andrew on 28.02.2016.
 */
public class Methodic {
    private String name;
    private int img;
    private String description;
    private int id;

    public boolean isInteractive() {
        return isInteractive;
    }

    public void setIsInteractive(boolean isInteractive) {
        this.isInteractive = isInteractive;
    }

    private boolean isInteractive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
