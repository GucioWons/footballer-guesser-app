package com.guciowons.footballer_guesser_app.domain.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.data.repositories.HistoryRepository;
import com.guciowons.footballer_guesser_app.data.repositories.PlayerRepository;
import com.guciowons.footballer_guesser_app.domain.entities.Club;
import com.guciowons.footballer_guesser_app.domain.entities.Player;

import java.util.List;

import it.unimi.dsi.util.XoRoShiRo128PlusRandom;

public class GameViewModel extends AndroidViewModel {
    private PlayerRepository playerRepository;
    private HistoryRepository historyRepository;
    private MutableLiveData<List<Player>> players;
    private MutableLiveData<List<Player>> history;
    private MutableLiveData<Club> clubHint = new MutableLiveData<>();
    private MutableLiveData<String> countryHint = new MutableLiveData<>();
    private MutableLiveData<Integer> numberHint = new MutableLiveData<>();
    private MutableLiveData<String> positionHint = new MutableLiveData<>();
    private Player answer;

    public GameViewModel(@NonNull Application application) {
        super(application);
        historyRepository = new HistoryRepository(getApplication());
        history = historyRepository.getHistory();
    }

    public void fetchPlayers(Integer id){
        playerRepository = new PlayerRepository(getApplication(), id);
        players = playerRepository.getAllPlayers();
    }

    public void drawAnswer(){
        XoRoShiRo128PlusRandom xoroRandom = new XoRoShiRo128PlusRandom();
        answer = players.getValue().get(xoroRandom.nextInt(players.getValue().size()));
        System.out.println(answer.getName());
    }

    public boolean checkAnswer(Player player){
        if(answer.equals(player)){
            return true;
        }else{
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
            return false;
        }
    }

    public void addPlayerToHistory(Player player){
        historyRepository.addPlayer(player);
    }

    public void clearHistory(){
        historyRepository.clearHistory();
    }

    public void clearHints(){
        clubHint.postValue(null);
        countryHint.postValue(null);
        numberHint.postValue(null);
        positionHint.postValue(null);
    }

    public MutableLiveData<List<Player>> getHistory(){
        return history;
    }

    public MutableLiveData<List<Player>> getAllPlayers(){
        return players;
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