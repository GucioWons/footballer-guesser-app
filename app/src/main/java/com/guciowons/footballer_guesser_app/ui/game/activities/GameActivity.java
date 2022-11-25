package com.guciowons.footballer_guesser_app.ui.game.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.databinding.ActivityGameBinding;
import com.guciowons.footballer_guesser_app.domain.entities.Club;
import com.guciowons.footballer_guesser_app.domain.entities.Player;
import com.guciowons.footballer_guesser_app.data.requests.FlagRequestManager;
import com.guciowons.footballer_guesser_app.domain.models.GameViewModel;
import com.guciowons.footballer_guesser_app.ui.game.adapters.HistoryAdapter;
import com.guciowons.footballer_guesser_app.ui.game.dialogs.FinishDialog;
import com.guciowons.footballer_guesser_app.ui.game.dialogs.LoadingDialog;
import com.guciowons.footballer_guesser_app.ui.game.dialogs.SearchDialog;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private ActivityGameBinding binding;
    private GameViewModel gameViewModel;
    private LoadingDialog loadingDialog;
    private FinishDialog finishDialog;
    private SearchDialog searchDialog;

    private HistoryAdapter historyAdapter;
    private Observer<List<Player>> playersObserver, historyObserver;
    private Observer<Club> clubObserver;
    private Observer<String> countryObserver, positionObserver;
    private Observer<Integer> numberObserver;
    private String name;
    private Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        startLoadingDialog();
        setUpHistoryRecycler();
        getExtras();
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        gameViewModel.fetchPlayers(id);
        setUpPlayersObserver();
        setUpHistoryObserver();
        setUpClubObserver();
        setUpCountryObserver();
        setUpNumberObserver();
        setUpPositionObserver();

        setUpViews();
    }

    public void setUpPlayersObserver(){
        playersObserver = (Observer<List<Player>>) players -> {
            gameViewModel.drawAnswer();
            endLoadingDialog();
        };
        gameViewModel.getAllPlayers().observe(this, playersObserver);
    }

    public void setUpHistoryObserver(){
        historyObserver = (Observer<List<Player>>) players -> historyAdapter.setPlayers(players);
        gameViewModel.getHistory().observe(this, historyObserver);
    }

    public void setUpClubObserver(){
        clubObserver = (Observer<Club>) club -> {
            if(club != null) {
                binding.hintClubImage.setImageBitmap(club.getCrest());
            }
        };
        gameViewModel.getClubHint().observe(this, clubObserver);
    }

    public void setUpCountryObserver(){
        countryObserver = (Observer<String>) country -> {
            if(country != null) {
                loadCountryImage(country);
            }
        };
        gameViewModel.getCountryHint().observe(this, countryObserver);
    }

    public void setUpNumberObserver(){
        numberObserver = (Observer<Integer>) number -> {
            if(number != null) {
                binding.hintNumberText.setText(number);
            }
        };
        gameViewModel.getNumberHint().observe(this, numberObserver);
    }

    public void setUpPositionObserver(){
        positionObserver = (Observer<String>) position -> {
            if(position != null) {
                binding.hintPositionText.setText(position);
            }
        };
        gameViewModel.getPositionHint().observe(this, positionObserver);
    }

    private void setUpHistoryRecycler(){
        binding.historyRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, true));
        historyAdapter = new HistoryAdapter(new ArrayList<>());
        binding.historyRecycler.setAdapter(historyAdapter);
    }
//
    private void setUpViews(){
        binding.gameText.setText(name);
        binding.searchButton.setOnClickListener(view -> startSearchDialog());
    }
//
    private void getExtras(){
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        id = extras.getInt("id");
    }

    public void checkAnswer(Player player){
        gameViewModel.addPlayerToHistory(player);
        if(gameViewModel.checkAnswer(player)){
            startFinishDialog(player.getName());
        }
    }

    private void loadCountryImage(String country){
        RequestQueue requestQueue = Volley.newRequestQueue(GameActivity.this);
        FlagRequestManager flagRequestManager = new FlagRequestManager();
        requestQueue.add(flagRequestManager.getFlagRequest(country, binding.hintCountryImage, requestQueue));
    }

    public void startSearchDialog(){
        searchDialog = new SearchDialog(GameActivity.this, gameViewModel.getAllPlayers().getValue());
        searchDialog.show();
    }

    private void startLoadingDialog(){
        loadingDialog = new LoadingDialog(GameActivity.this);
        loadingDialog.show();
    }

    private void startFinishDialog(String name){
        finishDialog = new FinishDialog(GameActivity.this, name);
        finishDialog.show();
        gameViewModel.clearHistory();
        gameViewModel.clearHints();
    }

    public void endLoadingDialog(){
        loadingDialog.dismiss();
    }
}