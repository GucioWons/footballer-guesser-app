package com.guciowons.footballer_guesser_app.ui.scoreboard.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.domain.leagues.entity.League;

import java.util.List;

public class ScoreboardLeaguesAdapter extends RecyclerView.Adapter<ScoreboardLeaguesAdapter.ScoreboardLeaguesViewHolder> {
    private List<League> leagues;

    public ScoreboardLeaguesAdapter(List<League> leagues) {
        this.leagues = leagues;
    }

    @NonNull
    @Override
    public ScoreboardLeaguesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View leagueView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_scoreboard_leagues, parent, false);
        return new ScoreboardLeaguesViewHolder(leagueView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreboardLeaguesViewHolder holder, int position) {
        holder.imageView.setImageBitmap(leagues.get(position).getLogo());
    }

    @Override
    public int getItemCount() {
        return leagues.size();
    }

    public void setLeagues(List<League> leagues){
        this.leagues = leagues;
        notifyDataSetChanged();
    }

    public class ScoreboardLeaguesViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ScoreboardLeaguesViewHolder(final View view) {
            super(view);
            setUpViews(view);
        }

        private void setUpViews(View view) {
            imageView = view.findViewById(R.id.scoreboard_league_image);
        }
    }
}
