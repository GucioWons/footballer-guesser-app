package com.guciowons.footballer_guesser_app.data.requests;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.data.mappers.JsonToPlayerMapper;
import com.guciowons.footballer_guesser_app.domain.entities.Club;
import com.guciowons.footballer_guesser_app.ui.game.activities.GameActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SvgCrestRequestManager {
    public StringRequest getSvgCrestRequest(GameActivity activity, Club club, JSONObject clubJson, Integer clubsQuantity, Integer i){
        return new StringRequest(club.getUrl(),
                response -> convertPlayerSvg(activity, response, club, clubJson, clubsQuantity, i),
                error -> setClubCrest(activity,
                        BitmapFactory.decodeResource(activity.getResources(), R.drawable.badge),
                        club, clubJson, i, clubsQuantity));
    }

    private void convertPlayerSvg(GameActivity activity, String response12, Club club, JSONObject clubJson, Integer clubsQuantity, Integer i){
        try {
            getBitmapFromSvg(activity, SVG.getFromString(response12).renderToPicture(), club, clubJson, clubsQuantity, i);
        } catch (SVGParseException e) {
            setClubCrest(activity,
                    BitmapFactory.decodeResource(activity.getResources(), R.drawable.badge),
                    club, clubJson, i, clubsQuantity);
        }
    }

    private void getBitmapFromSvg(GameActivity activity, Picture picture, Club club, JSONObject clubJson, Integer clubsQuantity, Integer i){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            Bitmap bitmap = Bitmap.createBitmap(picture);
            setClubCrest(activity, bitmap, club, clubJson, clubsQuantity, i);
        }else {
            setClubCrest(activity,
                    BitmapFactory.decodeResource(activity.getResources(), R.drawable.badge),
                    club, clubJson, i, clubsQuantity);
        }
    }

    private void setClubCrest(GameActivity activity, Bitmap bitmap, Club club, JSONObject clubJson, Integer clubsQuantity, Integer i){
        club.setCrest(bitmap);
        try {
            convertPlayers(activity, club, clubJson.getJSONArray("footballers"), clubsQuantity, i);
        } catch (JSONException e) {
            //TODO
        }
    }

    private void convertPlayers(GameActivity activity, Club club, JSONArray playersJson, Integer clubsQuantity, Integer i){
        for (int j = 0; j < playersJson.length(); j++) {
            try {
                JsonToPlayerMapper jsonToPlayerMapper = new JsonToPlayerMapper();
                activity.addPlayer(jsonToPlayerMapper.mapJsonToPlayer(playersJson.getJSONObject(j), club));
            } catch (JSONException e) {
                //TODO
            }
            if(i +1 == clubsQuantity && j+1 == playersJson.length()){
                activity.drawAnswer();
                activity.endLoadingDialog();
            }
        }
    }
}
