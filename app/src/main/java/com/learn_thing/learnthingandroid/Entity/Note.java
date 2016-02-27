package com.learn_thing.learnthingandroid.Entity;

import io.realm.RealmObject;

/**
 * Created by Andrew on 27.02.2016.
 */
public class Note extends RealmObject {
    private String noteName;
    private String noteText;
    private Integer id;


    private Integer idSubject;

    public Integer getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
