package com.guciowons.footballer_guesser_app.authentication.requests;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guciowons.footballer_guesser_app.game.LeaguesActivity;
import com.guciowons.footballer_guesser_app.authentication.activities.SignInActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class LoginRequest {
    public JsonObjectRequest getLoginRequest(AppCompatActivity activity, JSONObject params){
        return new JsonObjectRequest(Request.Method.POST, "http://192.168.0.3:8080/login", params,
                response -> {
                    saveData(response, params, activity.getSharedPreferences("Account", 0));
                    userLoggedIn(activity);
                }, error -> {
                    String body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    Toast.makeText(activity, body, Toast.LENGTH_SHORT).show();
        });
    }

    private void userLoggedIn(AppCompatActivity activity){
        Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(activity, LeaguesActivity.class);
        activity.startActivity(intent);
        activity.finish();
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
