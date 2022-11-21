package com.guciowons.footballer_guesser_app.game.entities;

public class League {
    private final Integer id;
    private final String name;
    private final String url;

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
}