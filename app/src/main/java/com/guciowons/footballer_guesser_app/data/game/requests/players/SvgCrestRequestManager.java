package com.guciowons.footballer_guesser_app.data.game.requests.players;

import android.graphics.Bitmap;
import android.graphics.Picture;

import com.android.volley.toolbox.StringRequest;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.guciowons.footballer_guesser_app.data.game.repositories.PlayerRepository;
import com.guciowons.footballer_guesser_app.data.models.player.Club;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SvgCrestRequestManager extends CrestRequestManager {
    public SvgCrestRequestManager(PlayersRequestManager playersRequestManager) {
        super(playersRequestManager);
    }

    public StringRequest getSvgCrestRequest(Club club, JSONObject clubJson){
        return new StringRequest(club.getUrl(),
                response -> convertPlayerSvg(response, club, clubJson),
                error -> showError());
    }

    private void convertPlayerSvg(String response12, Club club, JSONObject clubJson){
        try {
            getBitmapFromSvg(SVG.getFromString(response12).renderToPicture(), club, clubJson);
        } catch (SVGParseException e) {
            showError();
        }
    }

    private void getBitmapFromSvg(Picture picture, Club club, JSONObject clubJson){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            Bitmap bitmap = Bitmap.createBitmap(picture);
            setClubCrest(bitmap, club, clubJson);
        }else {
            showError();
        }
    }

    //TODO
    private void showError() {
    }
}
