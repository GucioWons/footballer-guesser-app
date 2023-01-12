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
    private SharedPreferences account;

    private MutableLiveData<List<Player>> players;
    private MutableLiveData<List<HistoryPlayer>> history;
    private MutableLiveData<Player> answer = new MutableLiveData<>();
    private MutableLiveData<Club> clubHint = new MutableLiveData<>();
    private MutableLiveData<String> countryHint = new MutableLiveData<>();
    private MutableLiveData<Integer> numberHint = new MutableLiveData<>();
    private MutableLiveData<String> positionHint = new MutableLiveData<>();

    private RequestQueue requestQueue;

    public GameViewModel(@NonNull Application application) {
        super(application);
        historyRepository = new HistoryRepository();
        history = historyRepository.getHistory();
        requestQueue = Volley.newRequestQueue(application);
        account = getEncryptedPreferences();
    }

    public void logoutUser(){
        account.edit().clear().apply();
    }

    public void fetchPlayers(Integer id){
        playerRepository = new PlayerRepository(getApplication(), id);
        players = playerRepository.getPlayers();
        answer = playerRepository.getAnswer();
    }

    public boolean checkAnswer(Player player){
        if(answer.getValue().equals(player)){
            return true;
        }else{
            if(answer.getValue().getClub().equals(player.getClub()) && clubHint.getValue() == null){
                clubHint.setValue(player.getClub());
            }
            if(answer.getValue().getNationality().equals(player.getNationality()) && countryHint.getValue() == null){
                countryHint.setValue(player.getNationality());
            }
            if(answer.getValue().getNumber().equals(player.getNumber()) && numberHint.getValue() == null){
                numberHint.setValue(player.getNumber());
            }
            if(answer.getValue().getPosition().equals(player.getPosition()) && positionHint.getValue() == null){
                positionHint.setValue(player.getPosition());
            }
            return false;
        }
    }

    public void removePlayer(Player player){
        playerRepository.removePlayer(player);
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

    public void clearAnswer(){
        answer.postValue(null);
    }

    public void sendScore(int leagueId) {
        int playerId = account.getInt("id", 0);
        if(playerId != 0 && history.getValue() != null){
            int points;
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