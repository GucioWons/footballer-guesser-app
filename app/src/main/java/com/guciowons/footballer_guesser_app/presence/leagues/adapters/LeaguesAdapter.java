package com.guciowons.footballer_guesser_app.presence.leagues.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guciowons.footballer_guesser_app.data.models.League;
import com.guciowons.footballer_guesser_app.databinding.ItemsLeaguesBinding;

import java.util.List;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesAdapter.LeaguesViewHolder> {
    private List<League> leagues;
    private final RecyclerViewClickListener listener;

    public LeaguesAdapter(List<League> leagues, RecyclerViewClickListener listener){
        this.leagues = leagues;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LeaguesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsLeaguesBinding itemBinding = ItemsLeaguesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LeaguesViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaguesViewHolder holder, int position) {
        setUpItem(holder, leagues.get(position));
    }

    private void setUpItem(LeaguesViewHolder holder, League league){
        holder.itemBinding.leaguesText.setText(league.getName());
        holder.itemBinding.leagueImage.setImageBitmap(league.getLogo());
    }

    @Override
    public int getItemCount() {
        return leagues.size();
    }

    public void setLeagues(List<League> leagues){
        this.leagues = leagues;
        notifyItemRangeInserted(0, leagues.size());
    }

    public class LeaguesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final ItemsLeaguesBinding itemBinding;

        public LeaguesViewHolder(ItemsLeaguesBinding itemBinding){
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            itemBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, leagues.get(getAdapterPosition()).getName(), leagues.get(getAdapterPosition()).getId());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, String name, Integer id);
    }
}
