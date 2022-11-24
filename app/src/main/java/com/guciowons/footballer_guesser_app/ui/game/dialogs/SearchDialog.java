package com.guciowons.footballer_guesser_app.ui.game.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.domain.entities.Player;
import com.guciowons.footballer_guesser_app.ui.game.activities.GameActivity;
import com.guciowons.footballer_guesser_app.ui.game.adapters.PlayersAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SearchDialog {
    private List<Player> players;
    private GameActivity activity;
    private RecyclerView searchRecycler;
    private PlayersAdapter playersAdapter;
    private SearchView searchView;
    private Dialog dialog;
    private PlayersAdapter.RecyclerViewClickListener listener;

    public SearchDialog(GameActivity activity, List<Player> players) {
        this.activity = activity;
        this.players = players;
    }

    public void show(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_search, null);
        builder.setView(dialogView);
        builder.setCancelable(true);
        searchRecycler = dialogView.findViewById(R.id.players_recycler);
        searchView = dialogView.findViewById(R.id.player_searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
        updateAdapter();

        dialog = builder.create();
        dialog.show();
    }

    private void filterList(String newText){
        playersAdapter.setFilteredList(players.stream().filter(
                player -> player.getName().toLowerCase().contains(newText.toLowerCase()))
                .collect(Collectors.toList()));
    }

    private void updateAdapter(){
        setOnClickListener();
        playersAdapter = new PlayersAdapter(players, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity.getApplicationContext());
        searchRecycler.setLayoutManager(layoutManager);
        searchRecycler.setItemAnimator(new DefaultItemAnimator());
        searchRecycler.setAdapter(playersAdapter);
    }

    private void setOnClickListener(){
        listener = (view, position) -> {
            activity.checkAnswer(playersAdapter.getPlayer(position));
            dialog.dismiss();
        };
    }
}
