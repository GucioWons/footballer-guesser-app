package com.guciowons.footballer_guesser_app.domain;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import com.guciowons.footballer_guesser_app.domain.preferences.EncryptedPreferencesGetter;

public class BaseViewModel {
    protected Application application;
    protected SharedPreferences account;

    private MutableLiveData<String> error = new MutableLiveData<>();

    public BaseViewModel(Application application) {
        this.application = application;
        account = getEncryptedPreferences();
    }

    private SharedPreferences getEncryptedPreferences(){
        EncryptedPreferencesGetter encryptedPreferencesGetter = new EncryptedPreferencesGetter();
        return encryptedPreferencesGetter.getEncryptedPreferences(application);
    }

    public void logoutUser(){
        account.edit().clear().apply();
    }

    public void setError(String s) {
        error.setValue(s);
    }
}
