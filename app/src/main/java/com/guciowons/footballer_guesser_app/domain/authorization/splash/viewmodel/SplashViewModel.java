package com.guciowons.footballer_guesser_app.domain.authorization.splash.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.authorization.sign_in.LoginRequestManager;
import com.guciowons.footballer_guesser_app.domain.authorization.BaseAuthViewModel;
import com.guciowons.footballer_guesser_app.domain.preferences.EncryptedPreferencesGetter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SplashViewModel extends BaseAuthViewModel {
    private SharedPreferences account;
    private Integer id;
    private String email, username, password;

    public SplashViewModel(@NonNull Application application) {
        super(application);
        account = getEncryptedPreferences();
    }

    public void authenticateUser(){
        loadData();
        if(id != 0 || email != null || username != null || password != null) {
            LoginRequestManager loginRequestManager = new LoginRequestManager();
            requestQueue.add(loginRequestManager.getLoginRequest(this, getJsonParams()));
        }else{
            setErrorResponse("No preferences");
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
        return encryptedPreferencesGetter.getEncryptedPreferences(application);
    }

    private void loadData(){
        id = account.getInt("id", 0);
        email = account.getString("email", null);
        username = account.getString("username", null);
        password = account.getString("password", null);
    }
}
