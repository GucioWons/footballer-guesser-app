package com.guciowons.footballer_guesser_app.presence.game.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.data.models.player.Player;
import com.guciowons.footballer_guesser_app.presence.game.activities.GameActivity;
import com.guciowons.footballer_guesser_app.presence.game.adapters.PlayersAdapter;

import java.util.List;
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
        dialog = createDialog().create();
        dialog.show();
    }

    private AlertDialog.Builder createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_search, null);
        builder.setView(dialogView);
        builder.setCancelable(true);
        setUpViews(dialogView);
        return builder;
    }

    private void setUpViews(View dialogView){
        searchRecycler = dialogView.findViewById(R.id.players_recycler);
        searchView = dialogView.findViewById(R.id.player_searchview);
        searchView.setOnQueryTextListener(setUpQueryTextListener());
        updateAdapter();
    }

    private SearchView.OnQueryTextListener setUpQueryTextListener(){
        return new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        };
    }

    private void filterList(String newText){
        playersAdapter.setFilteredList(players.stream().filter(
                player -> player.getName().toLowerCase().contains(newText.toLowerCase()) ||
                        player.getClub().getName().toLowerCase().contains(newText.toLowerCase()))
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
