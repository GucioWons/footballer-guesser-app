package com.guciowons.footballer_guesser_app.presence.game.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.databinding.ActivityGameBinding;
import com.guciowons.footballer_guesser_app.data.models.player.Player;
import com.guciowons.footballer_guesser_app.data.game.requests.flag.FlagRequestManager;
import com.guciowons.footballer_guesser_app.domain.game.viewmodel.GameViewModel;
import com.guciowons.footballer_guesser_app.presence.BaseActivity;
import com.guciowons.footballer_guesser_app.presence.authorization.landing.activities.LandingActivity;
import com.guciowons.footballer_guesser_app.presence.game.adapters.HistoryAdapter;
import com.guciowons.footballer_guesser_app.presence.game.dialogs.FinishDialog;
import com.guciowons.footballer_guesser_app.presence.leagues.activities.LeaguesActivity;
import com.guciowons.footballer_guesser_app.presence.loading.dialogs.LoadingDialog;
import com.guciowons.footballer_guesser_app.presence.game.dialogs.SearchDialog;

import java.util.ArrayList;

public class GameActivity extends BaseActivity {
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
        setUpMenu();
        setUpObservers();
        setUpViews();
        setUpErrorObserver(gameViewModel);
    }
    private void setUpMenu(){
        binding.appBar.toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.logout){
                logoutUser(gameViewModel);
            }
            return true;
        });
    }

    private void setUpObservers(){
        gameViewModel = new GameViewModel(getApplication());
        gameViewModel.fetchPlayers(id);
        setUpListsObservers();
        setUpHintsObservers();
    }

    private void setUpListsObservers(){
        gameViewModel.getAllPlayers().observe(this, players -> loadingDialog.dismiss());
        gameViewModel.getHistoryViewModel().getHistory().observe(this, history -> historyAdapter.setPlayers(history));
    }

    private void setUpHintsObservers(){
        gameViewModel.getHintViewModel().getClubHint().observe(this, club -> binding.hintClubImage.setImageBitmap(club.getCrest()));
        gameViewModel.getHintViewModel().getCountryHint().observe(this, country -> loadCountryImage(country));
        gameViewModel.getHintViewModel().getNumberHint().observe(this, number -> binding.hintNumberText.setText(number.toString()));
        gameViewModel.getHintViewModel().getPositionHint().observe(this, position -> binding.hintPositionText.setText(position));
    }

    private void setUpHistoryRecycler(){
        binding.historyRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, true));
        historyAdapter = new HistoryAdapter(new ArrayList<>());
        binding.historyRecycler.setAdapter(historyAdapter);
    }

    private void setUpViews(){
        binding.searchButton.setOnClickListener(view -> startSearchDialog());
        binding.appBar.toolbar.setTitle(name);
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