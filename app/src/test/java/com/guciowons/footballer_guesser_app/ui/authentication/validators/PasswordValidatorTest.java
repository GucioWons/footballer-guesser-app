package com.guciowons.footballer_guesser_app.ui.authentication.validators;

import static org.junit.Assert.*;

import com.guciowons.footballer_guesser_app.presence.authorization.validators.PasswordValidator;

import org.junit.Test;

public class PasswordValidatorTest {

    @Test
    public void validatePasswordEmpty() {
        System.out.println("---------------------------------------");
        System.out.println("Testing empty password: ");
        String response = PasswordValidator.validatePassword("");
        System.out.println("Response: " + response);
        assertEquals("Password cannot be empty!", response);
        System.out.println("Test passed!");
    }

    @Test
    public void validatePasswordTooShort(){
        System.out.println("---------------------------------------");
        System.out.println("Testing too short password: ");
        String response = PasswordValidator.validatePassword("short");
        System.out.println("Response: " + response);
        assertEquals("Password is too short!", response);
        System.out.println("Test passed!");
    }

    @Test
    public void validatePasswordCorrect() {
        System.out.println("---------------------------------------");
        System.out.println("Testing correct password: ");
        String response = PasswordValidator.validatePassword("password");
        System.out.println("Response: " + response);
        assertEquals("Success", response);
        System.out.println("Test passed!");
        System.out.println("---------------------------------------");
    }
}