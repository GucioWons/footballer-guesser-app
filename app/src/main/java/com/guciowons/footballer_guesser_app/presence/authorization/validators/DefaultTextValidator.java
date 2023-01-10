package com.guciowons.footballer_guesser_app.presence.authorization.validators;

public class DefaultTextValidator {
    public static String validateText(String text, String field){
        if(text.isEmpty()){
            return field + " cannot be empty!";
        } else{
            return "Success";
        }
    }
}
