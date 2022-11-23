package com.guciowons.footballer_guesser_app.ui.game.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.data.requests.LeaguesRequestManager;
import com.guciowons.footballer_guesser_app.domain.entities.League;
import com.guciowons.footballer_guesser_app.ui.game.adapters.LeaguesAdapter;
import com.guciowons.footballer_guesser_app.ui.game.dialogs.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

public class LeaguesActivity extends AppCompatActivity {
    private List<League> leagues;
    private RecyclerView leaguesRecycler;
    private LeaguesAdapter leaguesAdapter;
    private LeaguesAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leagues);
        leagues = new ArrayList<>();
        leaguesRecycler = findViewById(R.id.leagues_recycler);
        setUpAdapter();
        getLeaguesData();
    }

    private void getLeaguesData(){
        RequestQueue requestQueue = Volley.newRequestQueue(LeaguesActivity.this);
        LeaguesRequestManager leaguesRequestManager = new LeaguesRequestManager();
        requestQueue.add(leaguesRequestManager.getLeaguesRequest(LeaguesActivity.this, requestQueue, 80, 80));
    }

    public void addLeague(League league){
        leaguesAdapter.addLeague(league);
    }

    public void setUpAdapter(){
        setOnClickListener();
        leaguesAdapter = new LeaguesAdapter(leagues, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        leaguesRecycler.setLayoutManager(layoutManager);
        leaguesRecycler.setItemAnimator(new DefaultItemAnimator());
        leaguesRecycler.setAdapter(leaguesAdapter);
    }

    private void setOnClickListener() {
        listener = (view, position) -> {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            intent.putExtra("id", leagues.get(position).getId());
            intent.putExtra("name", leagues.get(position).getName());
            startActivity(intent);
        };
    }
}