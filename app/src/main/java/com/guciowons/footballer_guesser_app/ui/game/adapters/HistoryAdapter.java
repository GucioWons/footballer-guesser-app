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
import com.guciowons.footballer_guesser_app.data.requests.FlagRequestManager;
import com.guciowons.footballer_guesser_app.data.requests.ImageRequestManager;

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
        holder.positionText.setText(player.getPosition());
        holder.clubImage.setImageBitmap(player.getClub().getCrest());
        RequestQueue requestQueue = Volley.newRequestQueue(holder.context);
        FlagRequestManager flagRequestManager = new FlagRequestManager();
        requestQueue.add(flagRequestManager.getFlagRequest(player.getNationality(), holder.countryImage, requestQueue));
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
        private TextView nameText, numberText, positionText;
        private ImageView clubImage, countryImage;
        private Context context;

        public HistoryViewHolder(final View view){
            super(view);
            nameText = view.findViewById(R.id.history_name_text);
            numberText = view.findViewById(R.id.history_number_text);
            countryImage = view.findViewById(R.id.history_country_image);
            clubImage = view.findViewById(R.id.history_club_image);
            positionText = view.findViewById(R.id.history_position_text);
            context = view.getContext();
        }
    }
}
