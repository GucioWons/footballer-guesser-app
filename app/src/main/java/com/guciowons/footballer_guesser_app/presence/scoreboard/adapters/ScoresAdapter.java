package com.guciowons.footballer_guesser_app.presence.scoreboard.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guciowons.footballer_guesser_app.data.models.Score;
import com.guciowons.footballer_guesser_app.databinding.ItemsScoresBinding;

import java.util.List;

public class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ScoresViewHolder>{
    private List<Score> scores;

    public ScoresAdapter(List<Score> scores) {
        this.scores = scores;
    }

    @NonNull
    @Override
    public ScoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsScoresBinding itemBinding = ItemsScoresBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ScoresViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoresViewHolder holder, int position) {
        setUpItem(holder, scores.get(position), position+1);
    }

    private void setUpItem(ScoresViewHolder holder, Score score, Integer position) {
        holder.itemBinding.scorePosition.setText(String.format("%s.", position));
        holder.itemBinding.scoreName.setText(score.getName());
        holder.itemBinding.scorePoints.setText(String.format("%s", score.getPoints()));
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public void setScores(List<Score> scores){
        notifyItemRangeRemoved(0, this.scores.size());
        this.scores = scores;
        notifyItemRangeInserted(0, scores.size());
    }

    public class ScoresViewHolder extends RecyclerView.ViewHolder {
        private final ItemsScoresBinding itemBinding;

        public ScoresViewHolder(ItemsScoresBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
