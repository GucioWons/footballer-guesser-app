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

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<Player> players;

    public HistoryAdapter(List<Player> players){
        this.players = players;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View gameView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_history, parent, false);
        return new HistoryViewHolder(gameView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        Player player = players.get(position);
        holder.nameText.setText(player.getName());
        holder.numberText.setText(player.getNumber().toString());
        holder.countryText.setText(player.getNationality());
        holder.clubText.setText(player.getClubShortcut());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void addPlayer(Player player){
        players.add(0, player);
        notifyItemInserted(0);
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{
        private TextView nameText, countryText, numberText, clubText;

        public HistoryViewHolder(final View view){
            super(view);
            nameText = view.findViewById(R.id.history_name_text);
            numberText = view.findViewById(R.id.history_number_text);
            countryText = view.findViewById(R.id.history_country_text);
            clubText = view.findViewById(R.id.history_club_text);
        }
    }
}
