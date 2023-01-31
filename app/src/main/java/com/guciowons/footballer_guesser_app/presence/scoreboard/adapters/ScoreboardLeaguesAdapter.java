package com.guciowons.footballer_guesser_app.presence.scoreboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.data.models.League;
import com.guciowons.footballer_guesser_app.databinding.ItemsScoreboardLeaguesBinding;

import java.util.List;

public class ScoreboardLeaguesAdapter extends RecyclerView.Adapter<ScoreboardLeaguesAdapter.ScoreboardLeaguesViewHolder> {
    private Integer selected;
    private List<League> leagues;
    private final RecyclerViewClickListener listener;

    public ScoreboardLeaguesAdapter(List<League> leagues, RecyclerViewClickListener listener) {
        this.leagues = leagues;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ScoreboardLeaguesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsScoreboardLeaguesBinding itemBinding = ItemsScoreboardLeaguesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ScoreboardLeaguesViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreboardLeaguesViewHolder holder, int position) {
        setUpItem(holder.itemBinding.scoreboardLeagueImage, holder.context, position);
    }

    private void setUpItem(ImageButton imageButton, Context context, int position){
        if(selected != null && selected == position){
            imageButton.setBackgroundTintList(context.getColorStateList(R.color.light_green));
        }else {
            imageButton.setBackgroundTintList(context.getColorStateList(R.color.button_light));
        }
        imageButton.setImageBitmap(leagues.get(position).getLogo());
    }

    @Override
    public int getItemCount() {
        return leagues.size();
    }

    public void setLeagues(List<League> leagues){
        this.leagues = leagues;
        notifyItemRangeInserted(0, leagues.size());
    }

    public void deleteSelected(){
        Integer temp = selected;
        this.selected = null;
        notifyItemChanged(temp);
    }

    public class ScoreboardLeaguesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final ItemsScoreboardLeaguesBinding itemBinding;
        private final Context context;

        public ScoreboardLeaguesViewHolder(ItemsScoreboardLeaguesBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            itemBinding.scoreboardLeagueImage.setOnClickListener(this);
            context = itemBinding.getRoot().getContext();
        }

        @Override
        public void onClick(View view) {
            if(selected != null){
                notifyItemChanged(selected);
            }
            selected = getAdapterPosition();
            listener.onClick(view, leagues.get(getAdapterPosition()).getId());
            notifyItemChanged(selected);
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, int id);
    }
}
