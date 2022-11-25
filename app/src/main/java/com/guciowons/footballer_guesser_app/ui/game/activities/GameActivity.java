package com.guciowons.footballer_guesser_app.ui.game.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.data.requests.players.PlayersRequestManager;
import com.guciowons.footballer_guesser_app.domain.entities.Player;
import com.guciowons.footballer_guesser_app.data.requests.FlagRequestManager;
import com.guciowons.footballer_guesser_app.domain.models.GameViewModel;
import com.guciowons.footballer_guesser_app.domain.models.LeaguesViewModel;
import com.guciowons.footballer_guesser_app.ui.game.adapters.HistoryAdapter;
import com.guciowons.footballer_guesser_app.ui.game.adapters.LeaguesAdapter;
import com.guciowons.footballer_guesser_app.ui.game.dialogs.FinishDialog;
import com.guciowons.footballer_guesser_app.ui.game.dialogs.LoadingDialog;
import com.guciowons.footballer_guesser_app.ui.game.dialogs.SearchDialog;

import java.util.ArrayList;
import java.util.List;

import it.unimi.dsi.util.XoRoShiRo128PlusRandom;

public class GameActivity extends AppCompatActivity {
    private GameViewModel gameViewModel;
    private LoadingDialog loadingDialog;
    private FinishDialog finishDialog;
    private SearchDialog searchDialog;
    private RequestQueue requestQueue;

    private HistoryAdapter historyAdapter;
    private RecyclerView historyRecycler;
    private Button button;
    private TextView gameText, hintNameText, hintNumberText, hintPositionText;
    private ImageView hintCountryImage, hintClubImage;

    private String name;
    private Integer id;
    private Player answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        startLoadingDialog();
        historyRecycler = findViewById(R.id.history_recycler);
        setUpHistoryRecycler();
        getExtras();
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        gameViewModel.fetchPlayers(id);
        gameViewModel.getAllPlayers().observe(this, players -> {
            System.out.println(players.size());
            endLoadingDialog();
        });

        gameViewModel.getHistory().observe(this, players -> {
            historyAdapter.setPlayers(players);
        });
        setUpViews();
//        setUpHistoryAdapter();
//        getPlayersData(id);
    }

    private void setUpHistoryRecycler(){
        historyRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        historyAdapter = new HistoryAdapter(new ArrayList<>());
        historyRecycler.setAdapter(historyAdapter);
    }
//
    private void setUpViews(){
        gameText = findViewById(R.id.game_text);
        gameText.setText(name);
//        hintNameText = findViewById(R.id.hint_name_text);
//        hintClubImage = findViewById(R.id.hint_club_image);
//        hintCountryImage = findViewById(R.id.hint_country_image);
//        hintNumberText = findViewById(R.id.hint_number_text);
//        hintPositionText = findViewById(R.id.hint_position_text);
        button = findViewById(R.id.search_button);
        button.setOnClickListener(view -> startSearchDialog());
//        historyRecycler = findViewById(R.id.history_recycler);
    }
//
    private void getExtras(){
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        id = extras.getInt("id");
    }
//
    public void checkAnswer(Player player){
//        if(player.equals(answer)){
//            showHints(player);
//            startFinishDialog();
//        }else{
//            checkHints(player);
//        }
        gameViewModel.addPlayerToHistory(player);
    }
//
//    private void showHints(Player player){
//        hintNameText.setText(player.getName());
//        hintClubImage.setImageBitmap(player.getClub().getCrest());
//        loadCountryImage(player.getNationality());
//        hintNumberText.setText(player.getNumber().toString());
//        hintPositionText.setText(player.getPosition());
//    }
//
//    private void checkHints(Player player){
//        if(player.getClub().equals(answer.getClub())){
//            hintClubImage.setImageBitmap(player.getClub().getCrest());
//        }
//        if(player.getNationality().equals(answer.getNationality())){
//            loadCountryImage(player.getNationality());
//        }
//        if(player.getNumber().equals(answer.getNumber())){
//            hintNumberText.setText(player.getNumber().toString());
//        }
//        if(player.getPosition().equals(answer.getPosition())){
//            hintPositionText.setText(player.getPosition());
//        }
//    }
//
//    private void loadCountryImage(String country){
//        FlagRequestManager flagRequestManager = new FlagRequestManager();
//        requestQueue.add(flagRequestManager.getFlagRequest(country, hintCountryImage, requestQueue));
//    }
//
//    public void addPlayerToHistory(Player player){
//        players.remove(player);
//        historyAdapter.addPlayer(player);
//    }
//
//    public void drawAnswer(){
//        XoRoShiRo128PlusRandom xoroRandom = new XoRoShiRo128PlusRandom();
//        answer = players.get(xoroRandom.nextInt(players.size()));
//    }
//
    public void startSearchDialog(){
        searchDialog = new SearchDialog(GameActivity.this, gameViewModel.getAllPlayers().getValue());
        searchDialog.show();
    }
//
    private void startLoadingDialog(){
        loadingDialog = new LoadingDialog(GameActivity.this);
        loadingDialog.show();
    }
//
//    private void startFinishDialog(){
//        finishDialog = new FinishDialog(GameActivity.this, answer.getName());
//        finishDialog.show();
//    }
//
    public void endLoadingDialog(){
        loadingDialog.dismiss();
    }
}