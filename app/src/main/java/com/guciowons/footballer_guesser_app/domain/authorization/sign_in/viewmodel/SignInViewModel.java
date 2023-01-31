package com.guciowons.footballer_guesser_app.domain.authorization.sign_in.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.guciowons.footballer_guesser_app.data.authorization.sign_in.LoginRequestManager;
import com.guciowons.footballer_guesser_app.domain.authorization.BaseAuthViewModel;

import org.json.JSONObject;

import java.util.HashMap;

public class SignInViewModel extends BaseAuthViewModel {
    public SignInViewModel(@NonNull Application application) {
        super(application);
    }

    public void logInUser(String email, String password){
        LoginRequestManager loginRequestManager = new LoginRequestManager();
        requestQueue.add(loginRequestManager.getLoginRequest(this, getJsonParams(email, password)));
    }

    private JSONObject getJsonParams(String email, String password){
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        return new JSONObject(params);
    }
}
