package com.guciowons.footballer_guesser_app.domain.game.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.data.game.repositories.PlayerRepository;
import com.guciowons.footballer_guesser_app.data.models.player.Player;
import com.guciowons.footballer_guesser_app.domain.BaseViewModel;
import com.guciowons.footballer_guesser_app.domain.game.senders.ScoreSender;

import java.util.List;
import java.util.Objects;

public class GameViewModel extends BaseViewModel {
    private final HintViewModel hintViewModel;
    private final HistoryViewModel historyViewModel;
    private PlayerRepository playerRepository;

    private MutableLiveData<List<Player>> players;
    private MutableLiveData<Player> answer;

    public GameViewModel(@NonNull Application application) {
        super(application);
        hintViewModel = new HintViewModel();
        historyViewModel = new HistoryViewModel();
    }

    public void setUpPlayerRepository(Integer id){
        playerRepository = new PlayerRepository(application, id);
        players = playerRepository.getPlayers();
        answer = playerRepository.getAnswer();
        error = playerRepository.getError();
    }

    public boolean checkAnswer(Player player, int id){
        if(Objects.requireNonNull(answer.getValue()).equals(player)){
            ScoreSender.sendScore(id, account.getInt("id", 0), historyViewModel.getHistorySize(), playerRepository.getRequestQueue());
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