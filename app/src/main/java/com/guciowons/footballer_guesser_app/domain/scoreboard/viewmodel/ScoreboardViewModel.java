package com.guciowons.footballer_guesser_app.domain.scoreboard.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.leagues.repositories.LeagueRepository;
import com.guciowons.footballer_guesser_app.data.models.League;
import com.guciowons.footballer_guesser_app.data.models.Score;
import com.guciowons.footballer_guesser_app.data.scoreboard.requests.ScoresRequestManager;

import java.util.List;

public class ScoreboardViewModel extends AndroidViewModel {
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
        time = null;
        leagueId = null;
        fetchScores();
    }

    public void setTime(String time){
        this.time = time;
        fetchScores();
    }

    public void setLeagueId(Integer leagueId){
        this.leagueId = leagueId;
        fetchScores();
    }

    private void fetchScores() {
        ScoresRequestManager scoresRequestManager = new ScoresRequestManager();
        requestQueue.add(scoresRequestManager.getScores(this, createUrl()));
    }

    private String createUrl(){
        String url = "http://footballerguesserservice-env.eba-iwqz7xzh.eu-central-1.elasticbeanstalk.com/scores?";
        if(time != null){
            url = url + "time=" + time + "&";
        }
        if(leagueId != null){
            url = url + "leagueId=" + leagueId;
        }
        return url;
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
