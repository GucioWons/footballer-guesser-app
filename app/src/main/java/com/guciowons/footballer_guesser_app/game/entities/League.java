package com.guciowons.footballer_guesser_app.game.entities;

public class League {
    private final Integer id;
    private final String name;

    public League(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}