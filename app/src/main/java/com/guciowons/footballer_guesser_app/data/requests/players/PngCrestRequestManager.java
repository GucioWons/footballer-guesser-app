package com.guciowons.footballer_guesser_app.data.requests.players;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageRequest;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.data.repositories.PlayerRepository;
import com.guciowons.footballer_guesser_app.domain.entities.Club;
import com.guciowons.footballer_guesser_app.ui.game.activities.GameActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PngCrestRequestManager extends CrestRequestManager {
    public ImageRequest getPngCrestRequest(PlayerRepository repository, Club club, JSONObject clubJson, Integer i, Integer clubsQuantity){
        return new ImageRequest(club.getUrl(),
                crest -> setClubCrest(repository, crest, club, clubJson, i, clubsQuantity),
                64, 64, ImageView.ScaleType.CENTER, null,
                error -> {

                });
    }

    private void setClubCrest(PlayerRepository repository, Bitmap crest, Club club, JSONObject clubJson, Integer i, Integer clubsQuantity){
        club.setCrest(crest);
        try {
            convertPlayers(repository, club, clubJson.getJSONArray("footballers"), i, clubsQuantity);
        } catch (JSONException e) {
            //TODO
        }
    }

    private void convertPlayers(PlayerRepository repository, Club club, JSONArray playersJson, Integer i, Integer clubsQuantity) {
        for (int j = 0; j < playersJson.length(); j++) {
            addPlayerToActivity(repository, club, playersJson, clubsQuantity, i, j);
        }
    }
}
