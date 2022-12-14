package com.guciowons.footballer_guesser_app.ui.authentication.validators;

import android.widget.EditText;

public class PasswordValidator {
    public static String validatePassword(String password){
        if(password.isEmpty()){
            return "Password cannot be empty!";
        } else{
            return "Success";
        }
    }
}
