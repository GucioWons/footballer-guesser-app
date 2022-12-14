package com.guciowons.footballer_guesser_app.ui.scoreboard.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.domain.leagues.entity.League;

import java.util.List;

public class ScoreboardLeaguesAdapter extends RecyclerView.Adapter<ScoreboardLeaguesAdapter.ScoreboardLeaguesViewHolder> {
    private Integer selected;
    private List<League> leagues;
    private RecyclerViewClickListener listener;

    public ScoreboardLeaguesAdapter(List<League> leagues, RecyclerViewClickListener listener) {
        this.leagues = leagues;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ScoreboardLeaguesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View leagueView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_scoreboard_leagues, parent, false);
        return new ScoreboardLeaguesViewHolder(leagueView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreboardLeaguesViewHolder holder, int position) {
        if(selected != null && selected == position){
            holder.imageButton.setBackground(holder.itemView.getResources().getDrawable(R.color.light_green));
        }else {
            holder.imageButton.setBackground(holder.itemView.getResources().getDrawable(R.color.purple_200));
        }
        holder.imageButton.setImageBitmap(leagues.get(position).getLogo());
    }

    @Override
    public int getItemCount() {
        return leagues.size();
    }

    public void setLeagues(List<League> leagues){
        this.leagues = leagues;
        notifyDataSetChanged();
    }

    public void deleteSelected(){
        this.selected = null;
        notifyDataSetChanged();
    }

    public class ScoreboardLeaguesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageButton imageButton;

        public ScoreboardLeaguesViewHolder(final View view) {
            super(view);
            setUpViews(view);
        }

        private void setUpViews(View view) {
            imageButton = view.findViewById(R.id.scoreboard_league_image);
            imageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            selected = getAdapterPosition();
            listener.onClick(view, leagues.get(getAdapterPosition()).getId());
            notifyDataSetChanged();
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, int id);
    }
}
