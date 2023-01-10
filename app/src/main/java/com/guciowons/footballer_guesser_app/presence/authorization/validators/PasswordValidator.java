package com.guciowons.footballer_guesser_app.presence.authorization.validators;

public class PasswordValidator {
    public static String validatePassword(String password){
        if(password.isEmpty()){
            return "Password cannot be empty!";
        } else if(password.length()<6){
            return "Password is too short!";
        } else{
            return "Success";
        }
    }
}
