package com.guciowons.footballer_guesser_app.ui.authentication.validators;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmailValidatorTest {

    @Test
    public void validateEmailEmpty() {
        String response = EmailValidator.validateEmail("");
        assertEquals("Email cannot be empty!", response);
    }

    @Test
    public void validateEmailWithoutSite() {
        String response = EmailValidator.validateEmail("username");
        assertEquals("Email is not valid!", response);
    }

    @Test
    public void validateEmailWithoutUsername() {
        String response = EmailValidator.validateEmail("@example.com");
        assertEquals("Email is not valid!", response);
    }

    @Test
    public void validateEmailWithoutDomain() {
        String response = EmailValidator.validateEmail("username@example");
        assertEquals("Email is not valid!", response);
    }

    @Test
    public void validateEmailCorrect() {
        String response = EmailValidator.validateEmail("username@example.com");
        assertEquals("Success", response);
    }
}