package com.guciowons.footballer_guesser_app.domain.authentication.splash.view_model;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.domain.authentication.splash.request.SplashRequestManager;
import com.guciowons.footballer_guesser_app.domain.preferences.EncryptedPreferencesGetter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SplashViewModel extends AndroidViewModel {
    private RequestQueue requestQueue;
    private MutableLiveData<String> response = new MutableLiveData<>();
    private SharedPreferences account;
    private Integer id;
    private String email, username, password;

    public SplashViewModel(@NonNull Application application) {
        super(application);
        requestQueue = Volley.newRequestQueue(application);
        account = getEncryptedPreferences();
    }

    public MutableLiveData<String> getResponse(){
        return response;
    }

    public void authenticateUser(){
        loadData();
        if(id != 0 || email != null || username != null || password != null) {
            SplashRequestManager splashRequestManager = new SplashRequestManager();
            requestQueue.add(splashRequestManager.getLoginRequest(this, getJsonParams()));
        }else{
            response.setValue("No preferences");
        }
    }

    private JSONObject getJsonParams(){
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("email", email);
        params.put("password", password);
        return new JSONObject(params);
    }

    private SharedPreferences getEncryptedPreferences(){
        EncryptedPreferencesGetter encryptedPreferencesGetter = new EncryptedPreferencesGetter();
        return encryptedPreferencesGetter.getEncryptedPreferences(getApplication());
    }

    private void loadData(){
        id = account.getInt("id", 0);
        email = account.getString("email", null);
        username = account.getString("username", null);
        password = account.getString("password", null);
    }

    public void setResponse(JSONObject user, JSONObject params){
        saveData(user, params);
        response.setValue("Success");
    }

    private void saveData(JSONObject user, JSONObject params){
        SharedPreferences.Editor editor = account.edit();
        try {
            editor.putInt("id", user.getInt("id"));
            editor.putString("username", user.getString("username"));
            editor.putString("email", user.getString("email"));
            editor.putString("password", params.getString("password"));
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setErrorResponse(String error){
        response.setValue(error);
    }
}
