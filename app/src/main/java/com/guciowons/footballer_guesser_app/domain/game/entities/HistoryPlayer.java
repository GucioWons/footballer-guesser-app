package com.guciowons.footballer_guesser_app.domain.game.entities;

public class HistoryPlayer extends Player{
    private final boolean nationalityCorrect;
    private final boolean shirtCorrect;
    private final boolean positionCorrect;
    private final boolean clubCorrect;


    public HistoryPlayer(String name, String nationality, Integer number, String position, Club club, boolean nationalityCorrect, boolean shirtCorrect, boolean positionCorrect, boolean clubCorrect) {
        super(name, nationality, number, position, club);
        this.nationalityCorrect = nationalityCorrect;
        this.shirtCorrect = shirtCorrect;
        this.positionCorrect = positionCorrect;
        this.clubCorrect = clubCorrect;
    }

    public boolean isNationalityCorrect() {
        return nationalityCorrect;
    }

    public boolean isShirtCorrect() {
        return shirtCorrect;
    }

    public boolean isPositionCorrect() {
        return positionCorrect;
    }

    public boolean isClubCorrect() {
        return clubCorrect;
    }
}
