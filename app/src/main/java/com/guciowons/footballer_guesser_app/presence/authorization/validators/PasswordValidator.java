package com.guciowons.footballer_guesser_app.presence.authorization.validators;

public class PasswordValidator {
    public static String validatePassword(String password){
        if(password.isEmpty()){
            return "Password cannot be empty!";
        } else{
            return "Success";
        }
    }
}
