package com.guciowons.footballer_guesser_app.data.requests;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.guciowons.footballer_guesser_app.data.mappers.JsonToLeagueMapper;
import com.guciowons.footballer_guesser_app.domain.entities.League;
import com.guciowons.footballer_guesser_app.ui.game.activities.LeaguesActivity;

import org.json.JSONException;

import java.nio.charset.StandardCharsets;

public class LeaguesRequestManager {
    public JsonArrayRequest getLeaguesRequest(LeaguesActivity activity, RequestQueue requestQueue, int min, int max){
        String url = "http://192.168.0.2:8080/leagues";
        return new JsonArrayRequest(Request.Method.GET, url, null,
                response1 -> {
                    for(int i = 0; i< response1.length(); i++) {
                        try {
                            League league = JsonToLeagueMapper.mapJsonToLeague(response1.getJSONObject(i));
                            requestQueue.add(new ImageRequest(league.getUrl(), response2 -> {
                                league.setLogo(response2);
                                activity.addLeague(league);
                            }, min, max, ImageView.ScaleType.CENTER, null, error -> {

                            }));
                        } catch (JSONException e) {
                            //TODO
                        }
                    }
                    },
                error -> showError(error, activity));
    }

    private void showError(VolleyError error, LeaguesActivity activity){
//        activity.endLoadingDialog();
        String body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
        Toast.makeText(activity, body, Toast.LENGTH_SHORT).show();
    }
}
