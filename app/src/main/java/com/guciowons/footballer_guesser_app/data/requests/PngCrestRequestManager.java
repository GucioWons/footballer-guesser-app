package com.guciowons.footballer_guesser_app.data.requests;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.guciowons.footballer_guesser_app.R;
import com.guciowons.footballer_guesser_app.data.mappers.JsonToPlayerMapper;
import com.guciowons.footballer_guesser_app.domain.entities.Club;
import com.guciowons.footballer_guesser_app.ui.game.activities.GameActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PngCrestRequestManager {
    public ImageRequest getPngCrestRequest(GameActivity activity, Club club, JSONObject clubJson, Integer i, Integer clubsQuantity){
        return new ImageRequest(club.getUrl(),
                crest -> setClubCrest(activity, crest, club, clubJson, i, clubsQuantity),
                64, 64, ImageView.ScaleType.CENTER, null,
                error -> setClubCrest(activity,
                        BitmapFactory.decodeResource(activity.getResources(), R.drawable.badge),
                        club, clubJson, i, clubsQuantity));
    }

    private void setClubCrest(GameActivity activity, Bitmap crest, Club club, JSONObject clubJson, Integer i, Integer clubsQuantity){
        club.setCrest(crest);
        try {
            convertPlayers(activity, club, clubJson.getJSONArray("footballers"), i, clubsQuantity);
        } catch (JSONException e) {
            //TODO
        }
    }

    private void convertPlayers(GameActivity activity, Club club, JSONArray playersJson, Integer i, Integer clubsQuantity) {
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
