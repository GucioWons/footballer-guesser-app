package com.guciowons.footballer_guesser_app.game.entities;

public class Player {
    private final String name;
    private final String nationality;
    private final Integer number;
    private final String position;
    private final String club;
    private final String clubShortcut;
    private final String league;

    public Player(String name, String nationality, Integer number, String position, String club, String clubShortcut, String league) {
        this.name = name;
        this.nationality = nationality;
        this.number = number;
        this.position = position;
        this.club = club;
        this.clubShortcut = clubShortcut;
        this.league = league;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public Integer getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public String getClub() {
        return club;
    }

    public String getClubShortcut() {
        return clubShortcut;
    }

    public String getLeague() {
        return league;
    }
}
