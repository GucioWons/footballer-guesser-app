package com.guciowons.footballer_guesser_app.ui.authentication.validators;

import static org.junit.Assert.assertEquals;

import com.guciowons.footballer_guesser_app.presence.authorization.validators.DefaultTextValidator;

import org.junit.Test;

public class DefaultTextValidatorTest {

    @Test
    public void validateTextEmpty() {
        System.out.println("---------------------------------------");
        System.out.println("Testing empty text: ");
        String response = DefaultTextValidator.validateText("", "Text");
        System.out.println("Response: " + response);
        assertEquals("Text cannot be empty!", response);
        System.out.println("Test passed!");
        System.out.println("---------------------------------------");
    }

    @Test
    public void validateTextCorrect() {
        System.out.println("---------------------------------------");
        System.out.println("Testing correct text: ");
        String response = DefaultTextValidator.validateText("username", "Text");
        System.out.println("Response: " + response);
        assertEquals("Success", response);
        System.out.println("Test passed!");
    }
}
