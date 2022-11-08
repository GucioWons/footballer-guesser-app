package com.guciowons.footballer_guesser_app.authentication.requests;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guciowons.footballer_guesser_app.game.LeaguesActivity;
import com.guciowons.footballer_guesser_app.authentication.activities.SignInActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class LoginRequest {
    public JsonObjectRequest getLoginRequest(SignInActivity signInActivity, JSONObject params){
        return new JsonObjectRequest(Request.Method.POST, "http://192.168.0.2:8080/login", params,
                response -> {
                    saveData(response, signInActivity.getSharedPreferences("Account", 0));
                    userLoggedIn(signInActivity);
                }, error -> {
                    String body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    Toast.makeText(signInActivity, body, Toast.LENGTH_SHORT).show();
        });
    }

    private void userLoggedIn(SignInActivity signInActivity){
        Toast.makeText(signInActivity, "Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(signInActivity, LeaguesActivity.class);
        signInActivity.startActivity(intent);
        signInActivity.finish();
    }

    private void saveData(JSONObject response, SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            editor.putInt("id", response.getInt("id"));
            editor.putString("username", response.getString("username"));
            editor.putString("email", response.getString("email"));
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
