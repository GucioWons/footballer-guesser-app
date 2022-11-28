package com.guciowons.footballer_guesser_app.data.game.requests;

import android.graphics.Bitmap;
import android.graphics.Picture;

import com.android.volley.toolbox.StringRequest;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.guciowons.footballer_guesser_app.data.game.repositories.PlayerRepository;
import com.guciowons.footballer_guesser_app.domain.game.entities.Club;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SvgCrestRequestManager extends CrestRequestManager {
    public StringRequest getSvgCrestRequest(PlayerRepository repository, Club club, JSONObject clubJson, Integer i, Integer clubsQuantity){
        return new StringRequest(club.getUrl(),
                response -> convertPlayerSvg(repository, response, club, clubJson, i, clubsQuantity),
                error -> {
                    //TODO
                });
    }

    private void convertPlayerSvg(PlayerRepository repository, String response12, Club club, JSONObject clubJson, Integer i, Integer clubsQuantity){
        try {
            getBitmapFromSvg(repository, SVG.getFromString(response12).renderToPicture(), club, clubJson, i, clubsQuantity);
        } catch (SVGParseException e) {
            //TODO
        }
    }

    private void getBitmapFromSvg(PlayerRepository repository, Picture picture, Club club, JSONObject clubJson, Integer i, Integer clubsQuantity){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            Bitmap bitmap = Bitmap.createBitmap(picture);
            setClubCrest(repository, bitmap, club, clubJson, i, clubsQuantity);
        }else {
            //TODO
        }
    }

    private void setClubCrest(PlayerRepository repository, Bitmap bitmap, Club club, JSONObject clubJson, Integer i, Integer clubsQuantity){
        club.setCrest(bitmap);
        try {
            convertPlayers(repository, club, clubJson.getJSONArray("footballers"), i, clubsQuantity);
        } catch (JSONException e) {
            //TODO
        }
    }

    private void convertPlayers(PlayerRepository repository, Club club, JSONArray playersJson, Integer i, Integer clubsQuantity){
        for (int j = 0; j < playersJson.length(); j++) {
            addPlayerToActivity(repository, club, playersJson, clubsQuantity, i, j);
        }
    }
}
