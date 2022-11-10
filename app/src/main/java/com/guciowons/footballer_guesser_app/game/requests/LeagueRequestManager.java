package com.guciowons.footballer_guesser_app.game.requests;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guciowons.footballer_guesser_app.game.activities.LeaguesActivity;
import com.guciowons.footballer_guesser_app.preferences.EncryptedPreferencesGetter;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LeagueRequestManager {
    public JsonArrayRequest getLeaguesRequest(LeaguesActivity activity){
        String url = "http://192.168.0.3:8080/leagues";
        return new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    List<JSONObject> lista = new ArrayList<>();
                    for(int i = 0; i<response.length(); i++){
                        try {
                            lista.add(response.getJSONObject(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(lista);
                }, error -> {
            String body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
            Toast.makeText(activity, body, Toast.LENGTH_SHORT).show();
        });
    }
}
