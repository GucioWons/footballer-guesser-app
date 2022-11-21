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
import com.guciowons.footballer_guesser_app.game.entities.Player;
import com.guciowons.footballer_guesser_app.game.requests.PlayersRequestManager;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private LoadingDialog loadingDialog;
    private SearchDialog searchDialog;

    private HistoryAdapter historyAdapter;
    private RecyclerView historyRecycler;
    private Button button;
    private TextView gameText, hintNameText, hintCountryText, hintClubText, hintNumberText, hintPositionText;

    private String name, hintName, hintCountry, hintClub, hintPosition;
    private Integer id, hintNumber;
    private Player answer;
    private List<Player> history;
    private List<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameText = findViewById(R.id.game_text);
        hintNameText = findViewById(R.id.hint_name_text);
        hintClubText = findViewById(R.id.hint_club_text);
        hintCountryText = findViewById(R.id.hint_country_text);
        hintNumberText = findViewById(R.id.hint_number_text);
        hintPositionText = findViewById(R.id.hint_position_text);
        button = findViewById(R.id.search_button);
        historyRecycler = findViewById(R.id.history_recycler);
        button.setOnClickListener(view -> startSearchDialog());
        history = new ArrayList<>();
        updateAdapter();
        getExtras();
        gameText.setText(name);
        getPlayersData(id);
    }

    private void updateAdapter(){
        historyAdapter = new HistoryAdapter(history);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        historyRecycler.setLayoutManager(layoutManager);
        historyRecycler.setItemAnimator(new DefaultItemAnimator());
        historyRecycler.setAdapter(historyAdapter);
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
        searchDialog = new SearchDialog(GameActivity.this, players);
        searchDialog.show();
    }

    private void startLoadingDialog(){
        loadingDialog = new LoadingDialog(GameActivity.this);
        loadingDialog.show();
    }

    public void setAnswer(Player answer){
        this.answer = answer;
    }

    public Player getAnswer(){
        return answer;
    }

    public void setPlayers(List<Player> players){
        this.players = players;
    }

    public void endLoadingDialog(){
        loadingDialog.dismiss();
    }

    public void checkAnswer(Player player){
        if(player.equals(answer)){
            hintNameText.setText(player.getName());
            hintClubText.setText(player.getClubShortcut());
            hintCountryText.setText(player.getNationality());
            hintNumberText.setText(player.getNumber().toString());
            hintPositionText.setText(player.getPosition());
        }else{
            if(player.getClub().equals(answer.getClub())){
                hintClubText.setText(player.getClubShortcut());
            }
            if(player.getNationality().equals(answer.getNationality())){
                hintCountryText.setText(player.getNationality());
            }
            if(player.getNumber() == answer.getNumber()){
                hintNumberText.setText(player.getNumber().toString());
            }
            if(player.getPosition().equals(answer.getPosition())){
                hintPositionText.setText(player.getPosition());
            }
        }
        addPlayerToHistory(player);
    }
    public void addPlayerToHistory(Player player){
        players.remove(player);
        historyAdapter.addPlayer(player);
    }

}