package com.revplay.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.revplay.exception.RevPlayException;
import com.revplay.model.User;

public class AuthServiceTest {

    private AuthService authService;

    @Before
    public void setup() {
        authService = new AuthService();
    }

    @Test
    public void testRegisterUser_NullUser() {
        try {
            authService.registerUser(null);
            fail("Exception should be thrown");
        } catch (RevPlayException e) {
            assertEquals("User cannot be null", e.getMessage());
        }
    }

    @Test
    public void testLoginUser_InvalidCredentials() {
        try {
            authService.loginUser("wrong@mail.com", "wrong");
            fail("Exception expected");
        } catch (RevPlayException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testLoginUser_NullEmail() {
        try {
            authService.loginUser(null, "pass");
            fail("Exception expected");
        } catch (RevPlayException e) {
            assertEquals("Email or password cannot be empty", e.getMessage());
        }
    }

    @Test
    public void testLoginUser_NullPassword() {
        try {
            authService.loginUser("test@mail.com", null);
            fail("Exception expected");
        } catch (RevPlayException e) {
            assertEquals("Email or password cannot be empty", e.getMessage());
        }
    }

    @Test
    public void testUserObjectCreation() {
        User user = new User();
        user.setName("Test");
        user.setEmail("test@mail.com");
        user.setPassword("1234");

        assertEquals("Test", user.getName());
        assertEquals("test@mail.com", user.getEmail());
    }

}
