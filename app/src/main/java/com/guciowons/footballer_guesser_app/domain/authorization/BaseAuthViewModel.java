package com.guciowons.footballer_guesser_app.domain.authorization;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guciowons.footballer_guesser_app.domain.preferences.EncryptedPreferencesGetter;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseAuthViewModel {
    protected RequestQueue requestQueue;
    private final MutableLiveData<String> response = new MutableLiveData<>();
    protected final Application application;
    protected final SharedPreferences account;

    public BaseAuthViewModel(Application application) {
        this.application = application;
        requestQueue = Volley.newRequestQueue(application);
        account = getEncryptedPreferences();
    }

    private void saveData(JSONObject user, JSONObject params){
        SharedPreferences.Editor editor = getEncryptedPreferences().edit();
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

    private SharedPreferences getEncryptedPreferences(){
        EncryptedPreferencesGetter encryptedPreferencesGetter = new EncryptedPreferencesGetter();
        return encryptedPreferencesGetter.getEncryptedPreferences(application);
    }

    public void setErrorResponse(String error){
        response.setValue(error);
    }

    public void setResponse(JSONObject user, JSONObject params){
        saveData(user, params);
        response.setValue("Success");
    }

    public MutableLiveData<String> getResponse(){
        return response;
    }
}
