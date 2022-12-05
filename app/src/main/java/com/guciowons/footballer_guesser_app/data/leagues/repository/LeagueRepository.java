package com.guciowons.footballer_guesser_app.data.leagues.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.leagues.request.LeaguesRequestManager;
import com.guciowons.footballer_guesser_app.domain.leagues.entity.League;

import java.util.List;

public class LeagueRepository {
    private MutableLiveData<List<League>> leagues = new MutableLiveData<>();
    private RequestQueue requestQueue;

    public LeagueRepository(Application application){
        requestQueue = Volley.newRequestQueue(application);
        fetchLeagues();
    }

    private void fetchLeagues(){
        LeaguesRequestManager leaguesRequestManager = new LeaguesRequestManager();
        requestQueue.add(leaguesRequestManager.getLeaguesRequest(LeagueRepository.this));
    }

    public MutableLiveData<List<League>> getLeagues(){
        return leagues;
    }

    public void setLeagues(List<League> leagues){
        this.leagues.postValue(leagues);
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
