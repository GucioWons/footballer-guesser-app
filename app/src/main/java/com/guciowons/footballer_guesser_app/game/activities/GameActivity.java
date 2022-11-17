package com.guciowons.footballer_guesser_app.game.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
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
    private String name;
    private Integer id;
    private TextView gameText;
    private RecyclerView playersRecycler;
    private List<Player> players;
    private LoadingDialog loadingDialog;
    private SearchDialog searchDialog;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameText = findViewById(R.id.game_text);
        playersRecycler = findViewById(R.id.players_recycler);
        button = findViewById(R.id.search_button);
        button.setOnClickListener(view -> startSearchDialog());
        getExtras();
        gameText.setText(name);
        getPlayersData(id);
    }

    private void getExtras(){
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        id = extras.getInt("id");
    }

    private void getPlayersData(Integer id){
        startLoadingDialog();
        RequestQueue requestQueue = Volley.newRequestQueue(GameActivity.this);
        PlayersRequestManager playersRequestManager = new PlayersRequestManager();
        requestQueue.add(playersRequestManager.getPlayersRequest(GameActivity.this, id));
    }

    public void startSearchDialog(){
        searchDialog = new SearchDialog(GameActivity.this);
        searchDialog.show();
    }

    private void startLoadingDialog(){
        loadingDialog = new LoadingDialog(GameActivity.this);
        loadingDialog.show();
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

    public void endLoadingDialog(){
        loadingDialog.dismiss();
    }
}