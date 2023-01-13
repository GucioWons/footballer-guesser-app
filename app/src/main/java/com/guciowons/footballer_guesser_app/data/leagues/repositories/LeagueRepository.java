package com.guciowons.footballer_guesser_app.data.leagues.repositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.BaseRepository;
import com.guciowons.footballer_guesser_app.data.leagues.requests.LeaguesRequestManager;
import com.guciowons.footballer_guesser_app.data.models.League;

import org.apache.commons.lang3.mutable.Mutable;

import java.util.List;

public class LeagueRepository extends BaseRepository {
    private MutableLiveData<List<League>> leagues = new MutableLiveData<>();

    public LeagueRepository(Application application){
        super(application);
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
}
