package com.guciowons.footballer_guesser_app.domain.authorization.splash.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.guciowons.footballer_guesser_app.data.authorization.sign_in.LoginRequestManager;
import com.guciowons.footballer_guesser_app.domain.authorization.BaseAuthViewModel;

import org.json.JSONObject;

import java.util.HashMap;

public class SplashViewModel extends BaseAuthViewModel {

    private Integer id;
    private String email, username, password;

    public SplashViewModel(@NonNull Application application) {
        super(application);
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

    private void loadData(){
        id = account.getInt("id", 0);
        email = account.getString("email", null);
        username = account.getString("username", null);
        password = account.getString("password", null);
    }

    private JSONObject getJsonParams(){
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("email", email);
        params.put("password", password);
        return new JSONObject(params);
    }
}
