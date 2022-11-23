package com.guciowons.footballer_guesser_app.domain.entities;

import java.util.Objects;

public class Player {
    private final String name;
    private final String nationality;
    private final Integer number;
    private final String position;
    private final Club club;

    public Player(String name, String nationality, Integer number, String position, Club club) {
        this.name = name;
        this.nationality = nationality;
        this.number = number;
        this.position = position;
        this.club = club;
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

    public Club getClub() {
        return club;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(nationality, player.nationality) && Objects.equals(number, player.number) && Objects.equals(position, player.position) && Objects.equals(club, player.club);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nationality, number, position, club);
    }
}
