package com.guciowons.footballer_guesser_app.presence.game.adapters;

import android.content.Context;
import android.view.LayoutInflater;
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
import com.guciowons.footballer_guesser_app.databinding.ItemsHistoryBinding;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private List<HistoryPlayer> players;

    public HistoryAdapter(List<HistoryPlayer> players){
        this.players = players;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsHistoryBinding itemBinding = ItemsHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HistoryViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        setUpItem(holder, players.get(position));
    }

    private void setUpItem(HistoryViewHolder holder, HistoryPlayer player){
        holder.itemBinding.historyNameText.setText(player.getName());
        setUpNumber(holder.itemBinding.historyNumberText, holder.itemBinding.imageView, player, holder.context);
        setUpCountry(holder.itemBinding.historyCountryImage, player, holder.context);
        setUpPosition(holder.itemBinding.historyPositionText, player, holder.context);
        setUpClub(holder.itemBinding.historyClubImage, player, holder.context);
    }

    private void setUpNumber(TextView numberText, ImageView shirtImage, HistoryPlayer player, Context context){
        numberText.setText(String.format("%s", player.getNumber()));
        if(player.isShirtCorrect()){
            shirtImage.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.shirt_green));
        }
    }

    private void setUpCountry(ImageView countryImage, HistoryPlayer player, Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        FlagRequestManager flagRequestManager = new FlagRequestManager();
        requestQueue.add(flagRequestManager.getFlagRequest(player.getNationality(), countryImage, requestQueue));
        if(player.isNationalityCorrect()){
            countryImage.setBackground(AppCompatResources.getDrawable(context, R.drawable.rectangle_green));
        }
    }

    private void setUpPosition(TextView positionText, HistoryPlayer player, Context context){
        positionText.setText(player.getPosition());
        if(player.isPositionCorrect()){
            positionText.setTextColor(context.getColorStateList(R.color.green));
        }
    }

    private void setUpClub(ImageView clubImage, HistoryPlayer player, Context context){
        clubImage.setImageBitmap(player.getClub().getCrest());
        if(player.isClubCorrect()){
            clubImage.setBackground(AppCompatResources.getDrawable(context, R.drawable.circle_green));
        }
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void addPlayer(HistoryPlayer player){
        players.add(player);
        notifyItemInserted(players.indexOf(player));
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{
        private final ItemsHistoryBinding itemBinding;
        private final Context context;

        public HistoryViewHolder(ItemsHistoryBinding itemBinding){
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            context = itemBinding.getRoot().getContext();
        }
    }
}
