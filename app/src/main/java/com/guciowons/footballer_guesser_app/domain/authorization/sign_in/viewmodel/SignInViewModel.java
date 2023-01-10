package com.guciowons.footballer_guesser_app.domain.authorization.sign_in.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.data.authorization.sign_in.LoginRequestManager;
import com.guciowons.footballer_guesser_app.domain.preferences.EncryptedPreferencesGetter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignInViewModel extends AndroidViewModel {
    private RequestQueue requestQueue;
    private MutableLiveData<String> response = new MutableLiveData<>();

    public SignInViewModel(@NonNull Application application) {
        super(application);
        requestQueue = Volley.newRequestQueue(application);
    }

    public MutableLiveData<String> getResponse(){
        return response;
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

    public void setResponse(JSONObject user, JSONObject params){
        saveData(user, params);
        response.setValue("Success");
    }

    private void saveData(JSONObject user, JSONObject params){
        EncryptedPreferencesGetter encryptedPreferencesGetter = new EncryptedPreferencesGetter();
        SharedPreferences sharedPreferences = encryptedPreferencesGetter.getEncryptedPreferences(getApplication());
        SharedPreferences.Editor editor = sharedPreferences.edit();
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
