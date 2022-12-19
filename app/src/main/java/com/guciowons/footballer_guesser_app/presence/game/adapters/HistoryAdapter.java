package com.guciowons.footballer_guesser_app.presence.game.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.data.models.player.HistoryPlayer;
import com.guciowons.footballer_guesser_app.data.game.requests.flag.FlagRequestManager;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<HistoryPlayer> players;

    public HistoryAdapter(List<HistoryPlayer> players){
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
        setUpItem(holder, players.get(position));
    }

    private void setUpItem(HistoryViewHolder holder, HistoryPlayer player){
        holder.nameText.setText(player.getName());
        holder.numberText.setText(player.getNumber().toString());
        if(player.isShirtCorrect()){
            holder.shirtImage.setImageDrawable(AppCompatResources.getDrawable(holder.context, R.drawable.shirt_green));
        }
        holder.positionText.setText(player.getPosition());
        if(player.isPositionCorrect()){
            holder.positionText.setTextColor(holder.context.getColorStateList(R.color.green));
        }
        holder.clubImage.setImageBitmap(player.getClub().getCrest());
        if(player.isClubCorrect()){
            holder.clubImage.setBackground(AppCompatResources.getDrawable(holder.context, R.drawable.circle_green));
        }
        setUpCountry(holder, player);
        if(player.isNationalityCorrect()){
            holder.countryImage.setBackground(AppCompatResources.getDrawable(holder.context, R.drawable.rectangle_green));
        }
    }

    private void setUpCountry(HistoryViewHolder holder, HistoryPlayer player){
        RequestQueue requestQueue = Volley.newRequestQueue(holder.context);
        FlagRequestManager flagRequestManager = new FlagRequestManager();
        requestQueue.add(flagRequestManager.getFlagRequest(player.getNationality(), holder.countryImage, requestQueue));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void setPlayers(List<HistoryPlayer> players){
        this.players = players;
        notifyDataSetChanged();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{
        private TextView nameText, numberText, positionText;
        private ImageView clubImage, countryImage, shirtImage;
        private Context context;

        public HistoryViewHolder(final View view){
            super(view);
            setUpViews(view);
        }

        private void setUpViews(View view){
            nameText = view.findViewById(R.id.history_name_text);
            numberText = view.findViewById(R.id.history_number_text);
            countryImage = view.findViewById(R.id.history_country_image);
            clubImage = view.findViewById(R.id.history_club_image);
            positionText = view.findViewById(R.id.history_position_text);
            shirtImage = view.findViewById(R.id.imageView);
            context = view.getContext();
        }
    }
}
