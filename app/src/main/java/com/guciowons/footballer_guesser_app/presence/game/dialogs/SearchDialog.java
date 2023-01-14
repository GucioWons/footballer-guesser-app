package com.guciowons.footballer_guesser_app.presence.game.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.guciowons.footballer_guesser_app.data.models.player.Player;
import com.guciowons.footballer_guesser_app.databinding.DialogSearchBinding;
import com.guciowons.footballer_guesser_app.presence.game.activities.GameActivity;
import com.guciowons.footballer_guesser_app.presence.game.adapters.PlayersAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class SearchDialog {
    private DialogSearchBinding binding;
    private final List<Player> players;
    private final GameActivity activity;
    private PlayersAdapter playersAdapter;
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
        binding = DialogSearchBinding.inflate(activity.getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(binding.getRoot());
        builder.setCancelable(true);
        setUpViews();
        return builder;
    }

    private void setUpViews(){
        binding.playerSearchview.setOnQueryTextListener(setUpQueryTextListener());
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
        binding.playersRecycler.setLayoutManager(layoutManager);
        binding.playersRecycler.setItemAnimator(new DefaultItemAnimator());
        binding.playersRecycler.setAdapter(playersAdapter);
    }

    private void setOnClickListener(){
        listener = (view, position) -> {
            activity.checkAnswer(playersAdapter.getPlayer(position));
            dialog.dismiss();
        };
    }
}
