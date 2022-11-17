package com.guciowons.footballer_guesser_app.game.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.game.entities.Player;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.MyViewHolder>{
    private List<Player> players;

    public PlayersAdapter(List<Player> players){
        this.players = players;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameText;

        public MyViewHolder(final View view){
            super(view);
            nameText = view.findViewById(R.id.player_text);
        }
    }

    @NonNull
    @Override
    public PlayersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View gameView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_players, parent, false);
        return new PlayersAdapter.MyViewHolder(gameView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersAdapter.MyViewHolder holder, int position) {
        String name = players.get(position).getName();
        holder.nameText.setText(name);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void setFilteredList(List<Player> filteredList){
        this.players = filteredList;
        notifyDataSetChanged();
    }
}
