package com.guciowons.footballer_guesser_app.domain.game.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.game.repositories.PlayerRepository;
import com.guciowons.footballer_guesser_app.data.models.player.Player;
import com.guciowons.footballer_guesser_app.domain.game.senders.ScoreSender;
import com.guciowons.footballer_guesser_app.domain.preferences.EncryptedPreferencesGetter;

import java.util.List;

public class GameViewModel extends AndroidViewModel {
    private PlayerRepository playerRepository;
    private SharedPreferences account;
    private HintViewModel hintViewModel;
    private HistoryViewModel historyViewModel;

    private MutableLiveData<List<Player>> players;
    private MutableLiveData<Player> answer = new MutableLiveData<>();

    private RequestQueue requestQueue;

    public GameViewModel(@NonNull Application application) {
        super(application);
        hintViewModel = new HintViewModel();
        historyViewModel = new HistoryViewModel();
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

    public boolean checkAnswer(Player player, int id){
        if(answer.getValue().equals(player)){
            ScoreSender.sendScore(id, account.getInt("id", 0), historyViewModel.getHistorySize(), requestQueue);
            return true;
        }else{
            hintViewModel.checkHints(answer.getValue(), player);
            return false;
        }
    }

    public void removePlayer(Player player){
        playerRepository.removePlayer(player);
    }

    public void clearAnswer(){
        answer.postValue(null);
    }

    private SharedPreferences getEncryptedPreferences(){
        EncryptedPreferencesGetter encryptedPreferencesGetter = new EncryptedPreferencesGetter();
        return encryptedPreferencesGetter.getEncryptedPreferences(getApplication());
    }

    public HintViewModel getHintViewModel(){
        return hintViewModel;
    }

    public HistoryViewModel getHistoryViewModel(){
        return historyViewModel;
    }

    public MutableLiveData<List<Player>> getAllPlayers(){
        return players;
    }
}