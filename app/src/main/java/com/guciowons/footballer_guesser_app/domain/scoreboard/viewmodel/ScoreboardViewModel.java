package com.guciowons.footballer_guesser_app.domain.scoreboard.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.leagues.repositories.LeagueRepository;
import com.guciowons.footballer_guesser_app.data.models.League;
import com.guciowons.footballer_guesser_app.data.models.Score;
import com.guciowons.footballer_guesser_app.data.scoreboard.requests.ScoresRequestManager;
import com.guciowons.footballer_guesser_app.domain.BaseViewModel;
import com.guciowons.footballer_guesser_app.domain.preferences.EncryptedPreferencesGetter;
import com.guciowons.footballer_guesser_app.domain.scoreboard.senders.GetScoresSender;

import java.util.List;

public class ScoreboardViewModel extends BaseViewModel {
    private LeagueRepository leagueRepository;
    private MutableLiveData<List<Score>> scores = new MutableLiveData<>();
    private MutableLiveData<List<League>> leagues;

    private String time;
    private Integer leagueId;

    private RequestQueue requestQueue;

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

    public void setError(String error) {
        leagueRepository.setError(error);
    }

    public MutableLiveData<List<Score>> getScores(){
        return scores;
    }

    public MutableLiveData<List<League>> getLeagues(){
        return leagues;
    }

    public void setScores(List<Score> newScores){
        scores.setValue(newScores);
    }

    public void setLeagues(List<League> newLeagues){
        leagues.postValue(newLeagues);
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
