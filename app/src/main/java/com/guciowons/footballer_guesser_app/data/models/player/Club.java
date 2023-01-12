package com.guciowons.footballer_guesser_app.data.models.player;

import android.graphics.Bitmap;

public class Club {
    private final String name;
    private final String shortcut;
    private String url;
    private Bitmap crest;

    public Club(String name, String shortcut, String url) {
        this.name = name;
        this.shortcut = shortcut;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public Bitmap getCrest() {
        return crest;
    }

    public void setCrest(Bitmap crest) {
        this.crest = crest;
    }
}
