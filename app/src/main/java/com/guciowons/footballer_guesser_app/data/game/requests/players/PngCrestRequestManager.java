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
    public ImageRequest getPngCrestRequest(PlayerRepository repository, Club club,
                                           JSONObject clubJson, Integer i, Integer clubsQuantity){
        return new ImageRequest(club.getUrl(),
                crest -> setClubCrest(repository, crest, club, clubJson, i, clubsQuantity),
                64, 64, ImageView.ScaleType.CENTER, null,
                error -> showError());
    }

    private void setClubCrest(PlayerRepository repository, Bitmap crest, Club club,
                              JSONObject clubJson, Integer i, Integer clubsQuantity){
        club.setCrest(crest);
        try {
            convertPlayers(repository, club, clubJson.getJSONArray("footballers"), i, clubsQuantity);
        } catch (JSONException e) {
            showError();
        }
    }

    private void showError() {
    }

    private void convertPlayers(PlayerRepository repository, Club club, JSONArray playersJson, Integer i, Integer clubsQuantity) {
        for (int j = 0; j < playersJson.length(); j++) {
            addPlayerToActivity(repository, club, playersJson, clubsQuantity, i, j);
        }
    }
}
