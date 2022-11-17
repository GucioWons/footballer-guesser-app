package com.guciowons.footballer_guesser_app.game.entities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(nationality, player.nationality) && Objects.equals(number, player.number) && Objects.equals(position, player.position) && Objects.equals(club, player.club) && Objects.equals(clubShortcut, player.clubShortcut) && Objects.equals(league, player.league);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nationality, number, position, club, clubShortcut, league);
    }
}
