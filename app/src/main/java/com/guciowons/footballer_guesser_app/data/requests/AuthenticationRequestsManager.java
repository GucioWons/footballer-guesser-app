package com.guciowons.footballer_guesser_app.data.requests;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guciowons.footballer_guesser_app.data.preferences.EncryptedPreferencesGetter;
import com.guciowons.footballer_guesser_app.ui.game.activities.LeaguesActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class AuthenticationRequestsManager {
    public JsonObjectRequest getAuthenticationRequest(AppCompatActivity activity, JSONObject params, String endpoint){
        String url = "http://192.168.0.2:8080/" + endpoint;
        return new JsonObjectRequest(Request.Method.POST, url, params,
                response -> {
                    EncryptedPreferencesGetter encryptedPreferencesGetter = new EncryptedPreferencesGetter();
                    saveData(response, params, encryptedPreferencesGetter.getEncryptedPreferences(activity));
                    authenticateUser(activity);
                }, error -> {
            String body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
            Toast.makeText(activity, body, Toast.LENGTH_SHORT).show();
        });
    }

    private void authenticateUser(AppCompatActivity activity){
        Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(activity, LeaguesActivity.class);
        activity.startActivity(intent);
        activity.finishAffinity();
    }

    private void saveData(JSONObject response, JSONObject params, SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            editor.putInt("id", response.getInt("id"));
            editor.putString("username", response.getString("username"));
            editor.putString("email", response.getString("email"));
            editor.putString("password", params.getString("password"));
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
