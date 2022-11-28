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
import com.guciowons.footballer_guesser_app.domain.entities.League;
import com.guciowons.footballer_guesser_app.data.requests.ImageRequestManager;

import java.util.List;

public class LeaguesAdapter extends RecyclerView.Adapter<LeaguesAdapter.LeaguesViewHolder> {
    private List<League> leagues;
    private RecyclerViewClickListener listener;

    public LeaguesAdapter(List<League> leagues, RecyclerViewClickListener listener){
        this.leagues = leagues;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LeaguesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View leagueView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_leagues, parent, false);
        return new LeaguesViewHolder(leagueView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaguesViewHolder holder, int position) {
        setUpItem(holder, leagues.get(position));
    }

    private void setUpItem(LeaguesViewHolder holder, League league){
        holder.nameText.setText(league.getName());
        holder.leagueImage.setImageBitmap(league.getLogo());
    }

    @Override
    public int getItemCount() {
        return leagues.size();
    }

    public void setLeagues(List<League> leagues){
        this.leagues = leagues;
        notifyDataSetChanged();
    }

    public class LeaguesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nameText;
        private ImageView leagueImage;
        private Context context;

        public LeaguesViewHolder(final View view){
            super(view);
            setUpViews(view);
            view.setOnClickListener(this);
        }

        private void setUpViews(View view){
            nameText = view.findViewById(R.id.leagues_text);
            leagueImage = view.findViewById(R.id.league_image);
            context = view.getContext();
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
