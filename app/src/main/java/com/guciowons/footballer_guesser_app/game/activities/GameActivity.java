package com.guciowons.footballer_guesser_app.game.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.game.entities.League;
import com.guciowons.footballer_guesser_app.game.entities.Player;
import com.guciowons.footballer_guesser_app.game.requests.LeagueRequestManager;
import com.guciowons.footballer_guesser_app.game.requests.PlayersRequestManager;

import java.util.List;

public class GameActivity extends AppCompatActivity {
    private TextView gameText;
    private RecyclerView playersRecycler;
    private List<Player> players;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        loadingDialog = new LoadingDialog(GameActivity.this);
        loadingDialog.loadingAlertDialog();
        gameText = findViewById(R.id.game_text);

        Bundle extras = getIntent().getExtras();
        gameText.setText(extras.getString("name"));

        playersRecycler = findViewById(R.id.players_recycler);

        loadData(extras.getInt("id"));
    }

    public void loadData(Integer id){
        RequestQueue requestQueue = Volley.newRequestQueue(GameActivity.this);
        PlayersRequestManager playersRequestManager = new PlayersRequestManager();
        requestQueue.add(playersRequestManager.getPlayersRequest(GameActivity.this, id));
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

    public LoadingDialog getLoadingDialog(){
        return loadingDialog;
    }
}