package com.guciowons.footballer_guesser_app.presence.game.activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.databinding.ActivityGameBinding;
import com.guciowons.footballer_guesser_app.data.models.player.Player;
import com.guciowons.footballer_guesser_app.data.game.requests.flag.FlagRequestManager;
import com.guciowons.footballer_guesser_app.domain.game.viewmodel.GameViewModel;
import com.guciowons.footballer_guesser_app.presence.BaseActivity;
import com.guciowons.footballer_guesser_app.presence.game.adapters.HistoryAdapter;
import com.guciowons.footballer_guesser_app.presence.game.dialogs.FinishDialog;
import com.guciowons.footballer_guesser_app.presence.game.dialogs.SearchDialog;

import java.util.ArrayList;

public class GameActivity extends BaseActivity {
    private ActivityGameBinding binding;
    private GameViewModel gameViewModel;

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
        getExtras();
        setUpViewModel();
        setUpViews();
    }

    private void setUpViewModel(){
        gameViewModel = new GameViewModel(getApplication());
        gameViewModel.setUpPlayerRepository(id);
        setUpListsObservers();
        setUpHintsObservers();
        setUpErrorObserver(gameViewModel);
    }

    private void setUpListsObservers(){
        gameViewModel.getAllPlayers().observe(this, players -> loadingDialog.dismiss());
        gameViewModel.getHistoryViewModel().getHistory().observe(this, history -> {
            if(history.size() != 0){
                historyAdapter.addPlayer(history.get(history.size()-1));
                binding.historyRecycler.smoothScrollToPosition(history.size()-1);
            }
        });
    }

    private void setUpHintsObservers(){
        gameViewModel.getHintViewModel().getClubHint().observe(this, club -> binding.hintClubImage.setImageBitmap(club.getCrest()));
        gameViewModel.getHintViewModel().getCountryHint().observe(this, this::loadCountryImage);
        gameViewModel.getHintViewModel().getNumberHint().observe(this, number -> binding.hintNumberText.setText(String.format("%s", number.toString())));
        gameViewModel.getHintViewModel().getPositionHint().observe(this, position -> binding.hintPositionText.setText(position));
    }

    private void loadCountryImage(String country){
        RequestQueue requestQueue = Volley.newRequestQueue(GameActivity.this);
        FlagRequestManager flagRequestManager = new FlagRequestManager();
        requestQueue.add(flagRequestManager.getFlagRequest(country, binding.hintCountryImage, requestQueue));
    }

    private void setUpViews(){
        binding.searchButton.setOnClickListener(view -> startSearchDialog());
        binding.appBar.toolbar.setTitle(name);
        setUpMenu(binding.appBar.toolbar, gameViewModel);
        setUpHistoryRecycler();
    }

    public void startSearchDialog(){
        SearchDialog searchDialog = new SearchDialog(GameActivity.this, gameViewModel.getAllPlayers().getValue());
        searchDialog.show();
    }

    private void setUpHistoryRecycler(){
        binding.historyRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, true));
        historyAdapter = new HistoryAdapter(new ArrayList<>());
        binding.historyRecycler.setAdapter(historyAdapter);
    }

    private void getExtras(){
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        id = extras.getInt("id");
    }

    public void checkAnswer(Player player){
        if(gameViewModel.checkAnswer(player, id)){
            startFinishDialog(player.getName());
        }
    }

    private void startFinishDialog(String name){
        FinishDialog finishDialog = new FinishDialog(GameActivity.this, name);
        finishDialog.show();
    }
}