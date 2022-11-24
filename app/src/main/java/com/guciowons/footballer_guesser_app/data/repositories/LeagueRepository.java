package com.guciowons.footballer_guesser_app.data.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.requests.LeaguesRequestManager;
import com.guciowons.footballer_guesser_app.domain.entities.League;

import java.util.List;

public class LeagueRepository {
    private MutableLiveData<List<League>> leagues = new MutableLiveData<>();
    private RequestQueue requestQueue;

    public LeagueRepository(Application application){
        requestQueue = Volley.newRequestQueue(application);
        getLeagues();
    }

    public MutableLiveData<List<League>> getAllLeagues(){
        return leagues;
    }

    private void getLeagues(){
        LeaguesRequestManager leaguesRequestManager = new LeaguesRequestManager();
        requestQueue.add(leaguesRequestManager.getLeaguesRequest(LeagueRepository.this));
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

    public void setLeagues(List<League> leagues){
        this.leagues.postValue(leagues);
    }
}
