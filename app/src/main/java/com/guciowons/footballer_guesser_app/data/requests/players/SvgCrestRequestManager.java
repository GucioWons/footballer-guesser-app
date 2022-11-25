package com.guciowons.footballer_guesser_app.data.requests.players;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;

import com.android.volley.toolbox.StringRequest;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.data.repositories.PlayerRepository;
import com.guciowons.footballer_guesser_app.domain.entities.Club;
import com.guciowons.footballer_guesser_app.ui.game.activities.GameActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SvgCrestRequestManager extends CrestRequestManager {
    public StringRequest getSvgCrestRequest(PlayerRepository repository, Club club, JSONObject clubJson, Integer clubsQuantity, Integer i){
        return new StringRequest(club.getUrl(),
                response -> convertPlayerSvg(repository, response, club, clubJson, clubsQuantity, i),
                error -> {
                    //TODO
                });
    }

    private void convertPlayerSvg(PlayerRepository repository, String response12, Club club, JSONObject clubJson, Integer clubsQuantity, Integer i){
        try {
            getBitmapFromSvg(repository, SVG.getFromString(response12).renderToPicture(), club, clubJson, clubsQuantity, i);
        } catch (SVGParseException e) {
            //TODO
        }
    }

    private void getBitmapFromSvg(PlayerRepository repository, Picture picture, Club club, JSONObject clubJson, Integer clubsQuantity, Integer i){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            Bitmap bitmap = Bitmap.createBitmap(picture);
            setClubCrest(repository, bitmap, club, clubJson, clubsQuantity, i);
        }else {
            //TODO
        }
    }

    private void setClubCrest(PlayerRepository repository, Bitmap bitmap, Club club, JSONObject clubJson, Integer clubsQuantity, Integer i){
        club.setCrest(bitmap);
        try {
            convertPlayers(repository, club, clubJson.getJSONArray("footballers"), clubsQuantity, i);
        } catch (JSONException e) {
            //TODO
        }
    }

    private void convertPlayers(PlayerRepository repository, Club club, JSONArray playersJson, Integer clubsQuantity, Integer i){
        for (int j = 0; j < playersJson.length(); j++) {
            addPlayerToActivity(repository, club, playersJson, clubsQuantity, i, j);
        }
    }
}
