package com.guciowons.footballer_guesser_app.presence.game.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guciowons.footballer_guesser_app.data.models.player.Player;
import com.guciowons.footballer_guesser_app.databinding.ItemsPlayersBinding;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>{
    private List<Player> players;
    private final RecyclerViewClickListener listener;

    public PlayersAdapter(List<Player> players, RecyclerViewClickListener listener){
        this.players = players;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsPlayersBinding itemBinding = ItemsPlayersBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PlayersViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersViewHolder holder, int position) {
        setUpItem(holder, players.get(position));
    }

    private void setUpItem(PlayersViewHolder holder, Player player){
        holder.itemBinding.playerText.setText(player.getName());
        holder.itemBinding.playerClubImage.setImageBitmap(player.getClub().getCrest());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void setFilteredList(List<Player> filteredList){
        notifyItemRangeRemoved(0, players.size());
        this.players = filteredList;
        notifyItemRangeInserted(0, filteredList.size());
    }

    public Player getPlayer(Integer position){
        return players.get(position);
    }

    public class PlayersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final ItemsPlayersBinding itemBinding;

        public PlayersViewHolder(ItemsPlayersBinding itemBinding){
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            itemBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }
}
