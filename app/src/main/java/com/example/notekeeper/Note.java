package com.example.notekeeper;

// Known as Model Class
public class Note {
    private String title, description, time;
    private int color;

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
