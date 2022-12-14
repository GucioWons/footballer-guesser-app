package com.guciowons.footballer_guesser_app.domain.scoreboard.entity;

public class Score {
    private final String name;
    private final Integer points;

    public Score(String name, Integer points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public Integer getPoints() {
        return points;
    }
}
