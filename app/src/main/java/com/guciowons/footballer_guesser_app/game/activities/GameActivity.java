package com.guciowons.footballer_guesser_app.game.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.game.entities.League;
import com.guciowons.footballer_guesser_app.game.entities.Player;

import java.util.List;

public class GameActivity extends AppCompatActivity {
    private RecyclerView playersRecycler;
    private List<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playersRecycler = findViewById(R.id.players_recycler);
    }

    public void updateAdapter(){
        PlayersAdapter playersAdapter = new PlayersAdapter(players);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        playersRecycler.setLayoutManager(layoutManager);
        playersRecycler.setItemAnimator(new DefaultItemAnimator());
        playersRecycler.setAdapter(playersAdapter);
    }

    public void setPlayers(List<Player> players){
        this.players = players;
    }
}