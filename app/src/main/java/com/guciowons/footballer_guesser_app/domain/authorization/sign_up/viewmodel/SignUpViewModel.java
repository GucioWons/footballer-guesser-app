package com.guciowons.footballer_guesser_app.domain.authorization.sign_up.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.authorization.sign_up.RegisterRequestManager;
import com.guciowons.footballer_guesser_app.domain.authorization.BaseAuthViewModel;

import org.json.JSONObject;

import java.util.HashMap;

public class SignUpViewModel extends BaseAuthViewModel {
    public SignUpViewModel(@NonNull Application application) {
        super(application);
        requestQueue = Volley.newRequestQueue(application);
    }

    public void signUpUser(String username, String email, String password){
        RegisterRequestManager registerRequestManager = new RegisterRequestManager();
        requestQueue.add(registerRequestManager.getRegisterRequest(this, getJsonParams(username, email, password)));
    }

    private JSONObject getJsonParams(String username, String email, String password){
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("email", email);
        params.put("password", password);
        return new JSONObject(params);
    }
}
