package com.guciowons.footballer_guesser_app.ui.authentication.validators;

import static org.junit.Assert.*;

import com.guciowons.footballer_guesser_app.presence.authorization.validators.EmailValidator;

import org.junit.Test;

public class EmailValidatorTest {

    @Test
    public void validateEmailEmpty() {
        System.out.println("---------------------------------------");
        System.out.println("Testing empty email: ");
        String response = EmailValidator.validateEmail("");
        System.out.println("Response: " + response);
        assertEquals("Email cannot be empty!", response);
        System.out.println("Test passed!");
    }

    @Test
    public void validateEmailWithoutSite() {
        System.out.println("---------------------------------------");
        System.out.println("Testing email without site: ");
        String response = EmailValidator.validateEmail("username");
        System.out.println("Response: " + response);
        assertEquals("Email is not valid!", response);
        System.out.println("Test passed!");
    }

    @Test
    public void validateEmailWithoutUsername() {
        System.out.println("---------------------------------------");
        System.out.println("Testing email without username: ");
        String response = EmailValidator.validateEmail("@example.com");
        System.out.println("Response: " + response);
        assertEquals("Email is not valid!", response);
        System.out.println("Test passed!");
    }

    @Test
    public void validateEmailWithoutDomain() {
        System.out.println("---------------------------------------");
        System.out.println("Testing email without domain: ");
        String response = EmailValidator.validateEmail("username@example");
        System.out.println("Response: " + response);
        assertEquals("Email is not valid!", response);
        System.out.println("Test passed!");
        System.out.println("---------------------------------------");
    }

    @Test
    public void validateEmailCorrect() {
        System.out.println("---------------------------------------");
        System.out.println("Testing correct email: ");
        String response = EmailValidator.validateEmail("username@example.com");
        System.out.println("Response: " + response);
        assertEquals("Success", response);
        System.out.println("Test passed!");
    }
}