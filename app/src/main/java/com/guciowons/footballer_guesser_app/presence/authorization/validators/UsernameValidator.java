package com.guciowons.footballer_guesser_app.presence.authorization.validators;

public class UsernameValidator {
    public static String validateUsername(String username){
        if(username.isEmpty()){
            return "Username cannot be empty!";
        } else{
            return "Success";
        }
    }
}
