package com.guciowons.footballer_guesser_app.ui.authentication.validators;

import static org.junit.Assert.*;

import com.guciowons.footballer_guesser_app.presence.authorization.validators.ConfirmPasswordValidator;

import org.junit.Test;

public class ConfirmPasswordValidatorTest {

    @Test
    public void validateConfirmPasswordEmpty() {
        System.out.println("---------------------------------------");
        System.out.println("Testing empty confirmed password: ");
        String response = ConfirmPasswordValidator.validateConfirmPassword("password", "");
        System.out.println("Response: " + response);
        assertEquals("You have to confirm password!", response);
        System.out.println("Test passed!");
    }

    @Test
    public void validateConfirmPasswordIncorrect() {
        System.out.println("---------------------------------------");
        System.out.println("Testing incorrect confirmed password: ");
        String response = ConfirmPasswordValidator.validateConfirmPassword("password", "password1");
        System.out.println("Response: " + response);
        assertEquals("Confirm password has to be the same as password!", response);
        System.out.println("Test passed!");
    }

    @Test
    public void validateConfirmPasswordCorrect() {
        System.out.println("---------------------------------------");
        System.out.println("Testing correct confirmed password: ");
        String response = ConfirmPasswordValidator.validateConfirmPassword("password", "password");
        System.out.println("Response: " + response);
        assertEquals("Success", response);
        System.out.println("Test passed!");
        System.out.println("---------------------------------------");
    }
}