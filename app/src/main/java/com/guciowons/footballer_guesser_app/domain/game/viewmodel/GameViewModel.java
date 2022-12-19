package com.guciowons.footballer_guesser_app.domain.game.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.game.repositories.HistoryRepository;
import com.guciowons.footballer_guesser_app.data.game.repositories.PlayerRepository;
import com.guciowons.footballer_guesser_app.data.models.player.Club;
import com.guciowons.footballer_guesser_app.data.models.player.HistoryPlayer;
import com.guciowons.footballer_guesser_app.data.models.player.Player;
import com.guciowons.footballer_guesser_app.data.game.requests.score.SendScoreRequestManager;
import com.guciowons.footballer_guesser_app.domain.preferences.EncryptedPreferencesGetter;

import java.util.List;

import it.unimi.dsi.util.XoRoShiRo128PlusRandom;

public class GameViewModel extends AndroidViewModel {
    private PlayerRepository playerRepository;
    private HistoryRepository historyRepository;

    private MutableLiveData<List<Player>> players;
    private MutableLiveData<List<HistoryPlayer>> history;
    private MutableLiveData<Club> clubHint = new MutableLiveData<>();
    private MutableLiveData<String> countryHint = new MutableLiveData<>();
    private MutableLiveData<Integer> numberHint = new MutableLiveData<>();
    private MutableLiveData<String> positionHint = new MutableLiveData<>();

    private RequestQueue requestQueue;
    private Player answer;

    public GameViewModel(@NonNull Application application) {
        super(application);
        historyRepository = new HistoryRepository(getApplication());
        history = historyRepository.getHistory();
        requestQueue = Volley.newRequestQueue(application);
    }

    public void fetchPlayers(Integer id){
        playerRepository = new PlayerRepository(getApplication(), id);
        players = playerRepository.getPlayers();
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
        historyRepository.addPlayer(new HistoryPlayer(player.getName(), player.getNationality(),
                player.getNumber(), player.getPosition(), player.getClub(),
                player.getNationality().equals(countryHint.getValue()),
                player.getNumber().equals(numberHint.getValue()),
                player.getPosition().equals(positionHint.getValue()),
                player.getClub().equals(clubHint.getValue())));
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

    public void sendScore(Integer leagueId) {
        Integer playerId = getEncryptedPreferences().getInt("id", 0);
        if(playerId != 0){
            Integer points;
            if(history.getValue().size() <= 10){
                points = 8;
            }else if(history.getValue().size() <= 15){
                points = 5;
            }else if(history.getValue().size() <= 20){
                points = 3;
            }else{
                points = 1;
            }
            SendScoreRequestManager sendScoreRequestManager = new SendScoreRequestManager();
            requestQueue.add(sendScoreRequestManager.sendScoreRequest(playerId, leagueId, points));
        }
    }

    private SharedPreferences getEncryptedPreferences(){
        EncryptedPreferencesGetter encryptedPreferencesGetter = new EncryptedPreferencesGetter();
        return encryptedPreferencesGetter.getEncryptedPreferences(getApplication());
    }

    public MutableLiveData<List<HistoryPlayer>> getHistory(){
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