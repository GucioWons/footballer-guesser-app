package com.guciowons.footballer_guesser_app.ui.authentication.validators;

import android.widget.EditText;

public class UsernameValidator {
    public static String validateUsername(String username){
        if(username.isEmpty()){
            return "Username cannot be empty!";
        } else{
            return "Success";
        }
    }
}
