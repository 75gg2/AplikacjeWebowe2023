package com.example.javaandroid.Structures;

import android.util.Log;

public class Note {
    private String title;
    private String description;
    private String colorCode;
    public final static String[] colors = {"#DFFF00", "#FFBF00", "#FF7F50", "#DE3163", "#9FE2BF", "#40E0D0", "#6495ED", "#CCCCFF"};

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColorCode() {
        return colorCode;
    }

    public String getColor() {
        return colors[Integer.parseInt(getColorCode())];
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public Note(String title, String description, String colorCode) {
        this.title = title;
        this.description = description;
        this.colorCode = colorCode;
    }
}