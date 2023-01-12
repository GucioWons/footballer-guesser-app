package com.guciowons.footballer_guesser_app.data.game.requests.players;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageRequest;
import com.guciowons.footballer_guesser_app.data.game.repositories.PlayerRepository;
import com.guciowons.footballer_guesser_app.data.models.player.Club;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PngCrestRequestManager extends CrestRequestManager {
    public PngCrestRequestManager(PlayersRequestManager playersRequestManager) {
        super(playersRequestManager);
    }

    public ImageRequest getPngCrestRequest(Club club, JSONObject clubJson){
        return new ImageRequest(club.getUrl(),
                crest -> setClubCrest(crest, club, clubJson),
                64, 64, ImageView.ScaleType.CENTER, null,
                error -> {

                });
    }
}
