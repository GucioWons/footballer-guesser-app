package com.guciowons.footballer_guesser_app.domain.entities;

import android.graphics.Bitmap;

public class League {
    private final Integer id;
    private final String name;
    private final String url;
    private Bitmap logo;

    public League(Integer id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Bitmap getLogo() {
        return logo;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }
}