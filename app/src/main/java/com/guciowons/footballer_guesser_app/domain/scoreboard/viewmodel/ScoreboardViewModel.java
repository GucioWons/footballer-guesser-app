package com.guciowons.footballer_guesser_app.domain.scoreboard.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.leagues.repositories.LeagueRepository;
import com.guciowons.footballer_guesser_app.data.models.League;
import com.guciowons.footballer_guesser_app.data.models.Score;
import com.guciowons.footballer_guesser_app.domain.BaseViewModel;
import com.guciowons.footballer_guesser_app.domain.scoreboard.senders.GetScoresSender;

import java.util.List;

public class ScoreboardViewModel extends BaseViewModel {
    private final LeagueRepository leagueRepository;
    private final RequestQueue requestQueue;

    private String time;
    private Integer leagueId;

    private final MutableLiveData<List<Score>> scores = new MutableLiveData<>();
    private final MutableLiveData<List<League>> leagues;

    public ScoreboardViewModel(@NonNull Application application) {
        super(application);
        requestQueue = Volley.newRequestQueue(application);
        leagueRepository = new LeagueRepository(application);
        leagues = leagueRepository.getLeagues();
        error = leagueRepository.getError();
        time = null;
        leagueId = null;
        GetScoresSender.fetchScores(this, time, leagueId, requestQueue);
    }

    public void setTime(String time){
        this.time = time;
        GetScoresSender.fetchScores(this, time, leagueId, requestQueue);
    }

    public void setLeagueId(Integer leagueId){
        this.leagueId = leagueId;
        GetScoresSender.fetchScores(this, time, leagueId, requestQueue);
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

    public void setError(String error) {
        leagueRepository.setError(error);
    }

    public void setScores(List<Score> newScores){
        scores.setValue(newScores);
    }

    public MutableLiveData<List<Score>> getScores(){
        return scores;
    }

    public void setLeagues(List<League> newLeagues){
        leagues.postValue(newLeagues);
    }

    public MutableLiveData<List<League>> getLeagues(){
        return leagues;
    }
}
