package com.example.notekeeper;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.ColorRes;

@Entity(tableName = "note_table")
public class Note {
    private String title;
    private String description;
    private String time;
    @ColorRes private int color;
    @PrimaryKey(autoGenerate = true)
    public int id = 0;

    public Note(String title, String description, String time, int color) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.color = color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }
}
