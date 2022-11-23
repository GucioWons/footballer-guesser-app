package com.guciowons.footballer_guesser_app.ui.game.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.domain.entities.Player;
import com.guciowons.footballer_guesser_app.data.requests.ImageRequestManager;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>{
    private List<Player> players;
    private RecyclerViewClickListener listener;

    public PlayersAdapter(List<Player> players, RecyclerViewClickListener listener){
        this.players = players;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View gameView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_players, parent, false);
        return new PlayersViewHolder(gameView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersViewHolder holder, int position) {
        Player player = players.get(position);
        holder.nameText.setText(player.getName());
        holder.clubImage.setImageBitmap(player.getClub().getCrest());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void setFilteredList(List<Player> filteredList){
        this.players = filteredList;
        notifyDataSetChanged();
    }

    public Player getPlayer(Integer position){
        return players.get(position);
    }

    public class PlayersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nameText;
        private ImageView clubImage;
        private Context context;

        public PlayersViewHolder(final View view){
            super(view);
            nameText = view.findViewById(R.id.player_text);
            clubImage = view.findViewById(R.id.player_club_image);
            context = view.getContext();
            view.setOnClickListener(this);
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
