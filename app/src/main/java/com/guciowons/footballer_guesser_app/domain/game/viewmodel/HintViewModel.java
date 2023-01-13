package com.guciowons.footballer_guesser_app.domain.game.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.data.models.player.Club;
import com.guciowons.footballer_guesser_app.data.models.player.Player;

public class HintViewModel {
    private MutableLiveData<Club> clubHint = new MutableLiveData<>();
    private MutableLiveData<String> countryHint = new MutableLiveData<>();
    private MutableLiveData<Integer> numberHint = new MutableLiveData<>();
    private MutableLiveData<String> positionHint = new MutableLiveData<>();

    public void checkHints(Player answer, Player player){
        if(answer.getClub().equals(player.getClub()) && clubHint.getValue() == null){
            clubHint.setValue(player.getClub());
        }
        if(answer.getNationality().equals(player.getNationality()) && countryHint.getValue() == null){
            countryHint.setValue(player.getNationality());
        }
        if(answer.getNumber().equals(player.getNumber()) && numberHint.getValue() == null){
            numberHint.setValue(player.getNumber());
        }
        if(answer.getPosition().equals(player.getPosition()) && positionHint.getValue() == null){
            positionHint.setValue(player.getPosition());
        }
    }

    public MutableLiveData<Club> getClubHint() {
        return clubHint;
    }

    public MutableLiveData<String> getCountryHint() {
        return countryHint;
    }

    public MutableLiveData<Integer> getNumberHint() {
        return numberHint;
    }

    public MutableLiveData<String> getPositionHint() {
        return positionHint;
    }
}
