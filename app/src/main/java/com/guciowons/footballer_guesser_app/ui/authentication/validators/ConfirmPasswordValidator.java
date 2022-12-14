package com.guciowons.footballer_guesser_app.ui.authentication.validators;

public class ConfirmPasswordValidator {
    public static String validateConfirmPassword(String password, String confirmPassword){
        if(confirmPassword.isEmpty()){
            return "You have to confirm password!";
        }else if(!password.equals(confirmPassword)){
            return "Confirm password has to be the same as password!";
        }else{
            return "Success";
        }
    }
}
