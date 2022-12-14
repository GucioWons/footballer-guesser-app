package com.guciowons.footballer_guesser_app.ui.game.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.databinding.ActivityGameBinding;
import com.guciowons.footballer_guesser_app.domain.game.entities.Player;
import com.guciowons.footballer_guesser_app.data.game.requests.FlagRequestManager;
import com.guciowons.footballer_guesser_app.domain.game.view_model.GameViewModel;
import com.guciowons.footballer_guesser_app.ui.game.adapters.HistoryAdapter;
import com.guciowons.footballer_guesser_app.ui.game.dialogs.FinishDialog;
import com.guciowons.footballer_guesser_app.ui.loadingdialog.LoadingDialog;
import com.guciowons.footballer_guesser_app.ui.game.dialogs.SearchDialog;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private ActivityGameBinding binding;
    private GameViewModel gameViewModel;
    private LoadingDialog loadingDialog;
    private FinishDialog finishDialog;
    private SearchDialog searchDialog;

    private HistoryAdapter historyAdapter;
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
        setUpObservers();
        setUpViews();
    }

    private void setUpObservers(){
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        gameViewModel.fetchPlayers(id);
        setUpPlayersObserver();
        setUpHistoryObserver();
        setUpClubObserver();
        setUpCountryObserver();
        setUpNumberObserver();
        setUpPositionObserver();
    }

    private void setUpPlayersObserver(){
        gameViewModel.getAllPlayers().observe(this, players -> {
            gameViewModel.drawAnswer();
            loadingDialog.dismiss();
        });
    }

    private void setUpHistoryObserver(){
        gameViewModel.getHistory().observe(this, history -> historyAdapter.setPlayers(history));
    }

    private void setUpClubObserver(){
        gameViewModel.getClubHint().observe(this, club -> {
            if(club != null) {
                binding.hintClubImage.setImageBitmap(club.getCrest());
            }
        });
    }

    private void setUpCountryObserver(){
        gameViewModel.getCountryHint().observe(this, country -> {
            if(country != null) {
                loadCountryImage(country);
            }
        });
    }

    private void setUpNumberObserver(){
        gameViewModel.getNumberHint().observe(this, number -> {
            if(number != null) {
                binding.hintNumberText.setText(number.toString());
            }
        });
    }

    private void setUpPositionObserver(){
        gameViewModel.getPositionHint().observe(this, position ->{
            if(position != null) {
                binding.hintPositionText.setText(position);
            }
        });
    }

    private void setUpHistoryRecycler(){
        binding.historyRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, true));
        historyAdapter = new HistoryAdapter(new ArrayList<>());
        binding.historyRecycler.setAdapter(historyAdapter);
    }

    private void setUpViews(){
        binding.gameText.setText(name);
        binding.searchButton.setOnClickListener(view -> startSearchDialog());
    }

    private void getExtras(){
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        id = extras.getInt("id");
    }

    public void checkAnswer(Player player){
        gameViewModel.addPlayerToHistory(player);
        if(gameViewModel.checkAnswer(player)){
            startFinishDialog(player.getName());
            gameViewModel.sendScore(id);
            gameViewModel.clearHints();
            gameViewModel.clearHistory();
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
    }
}