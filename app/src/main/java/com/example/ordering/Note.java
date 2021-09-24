package com.example.ordering;

import java.util.ArrayList;
import java.util.Date;

public class Note {

    public static ArrayList<Note> noteArrayList = new ArrayList<>();
    public static String NOTE_EDIT_EXTRA = "noteEdit";

    private int id;
    private String item;
    private String description;
    private Date deleted;

    public Note(int id, String title, String description, Date deleted) {
        this.id = id;
        this.item = title;
        this.description = description;
        this.deleted = deleted;
    }

    public Note(int id, String title, String description) {
        this.id = id;
        this.item = title;
        this.description = description;
        deleted = null;
    }

    public static Note getNoteForID(int passedNoteID) {
        for(Note note : noteArrayList)
        {
            if(note.getId() == passedNoteID)
                return note;
        }
        return null;
    }

    public static ArrayList<Note> nonDeletedNotes()
    {
        ArrayList<Note> nonDeleted = new ArrayList<>();
        for(Note note : noteArrayList)
        {
            if(note.getDeleted() == null)
                nonDeleted.add(note);
        }
        return nonDeleted;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return item;
    }

    public String getDescription() {
        return description;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setItem(String item) {
    }

    public void setDescription(String description) {
    }

    public void setDeleted(Date date) {
    }
}
