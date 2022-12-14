package com.guciowons.footballer_guesser_app.presence.scoreboard.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.data.models.Score;

import java.util.List;

public class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ScoresViewHolder>{
    private List<Score> scores;

    public ScoresAdapter(List<Score> scores) {
        this.scores = scores;
    }

    @NonNull
    @Override
    public ScoresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View scoreView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_scores, parent, false);
        return new ScoresViewHolder(scoreView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoresViewHolder holder, int position) {
        setUpItem(holder, scores.get(position), position+1);
    }

    private void setUpItem(ScoresViewHolder holder, Score score, Integer position) {
        holder.positionText.setText(position.toString() + ".");
        holder.nameText.setText(score.getName());
        holder.pointsText.setText(score.getPoints().toString());
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public void setScores(List<Score> scores){
        this.scores = scores;
        notifyDataSetChanged();
    }

    public class ScoresViewHolder extends RecyclerView.ViewHolder {
        private TextView positionText, nameText, pointsText;

        public ScoresViewHolder(final View view) {
            super(view);
            setUpViews(view);
        }

        private void setUpViews(View view) {
            positionText = view.findViewById(R.id.score_position);
            nameText = view.findViewById(R.id.score_name);
            pointsText = view.findViewById(R.id.score_points);
        }
    }
}
