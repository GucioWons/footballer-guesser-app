package com.guciowons.footballer_guesser_app.domain.game.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.game.repositories.PlayerRepository;
import com.guciowons.footballer_guesser_app.data.models.player.Player;
import com.guciowons.footballer_guesser_app.domain.BaseViewModel;
import com.guciowons.footballer_guesser_app.domain.game.senders.ScoreSender;
import com.guciowons.footballer_guesser_app.domain.preferences.EncryptedPreferencesGetter;

import java.util.List;

public class GameViewModel extends BaseViewModel {
    private PlayerRepository playerRepository;
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
    }

    public void fetchPlayers(Integer id){
        playerRepository = new PlayerRepository(application, id);
        players = playerRepository.getPlayers();
        answer = playerRepository.getAnswer();
    }

    public boolean checkAnswer(Player player, int id){
        if(answer.getValue().equals(player)){
            ScoreSender.sendScore(id, account.getInt("id", 0), historyViewModel.getHistorySize(), requestQueue);
            return true;
        }else{
            hintViewModel.checkHints(answer.getValue(), player);
            removePlayer(player);
            getHistoryViewModel().addPlayerToHistory(player, getHintViewModel());
            return false;
        }
    }

    public void removePlayer(Player player){
        playerRepository.removePlayer(player);
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