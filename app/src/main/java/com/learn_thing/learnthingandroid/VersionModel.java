package com.learn_thing.learnthingandroid;

/**
 * Created by Andrew on 05.03.2016.
 */
public class VersionModel {
    public String name;

    public static final String[] data = {"Cupcake", "Donut", "Eclair",
            "Froyo", "Gingerbread", "Honeycomb",
            "Icecream Sandwich", "Jelly Bean", "Kitkat", "Lollipop"};

    VersionModel(String name){
        this.name=name;
    }
}