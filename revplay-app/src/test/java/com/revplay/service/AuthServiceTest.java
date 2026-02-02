package com.revplay.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revplay.dao.ArtistDAO;
import com.revplay.dao.UserDAO;
import com.revplay.exception.RevPlayException;
import com.revplay.model.Artist;
import com.revplay.model.User;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private ArtistDAO artistDAO;

    @InjectMocks
    private AuthService authService;


    @Test
    public void testRegisterUser_NullUser() {
        try {
            authService.registerUser(null);
            fail("Exception expected");
        } catch (RevPlayException e) {
            assertEquals("User cannot be null", e.getMessage());
        }
    }

    @Test
    public void testRegisterUser_DuplicateEmail() {
        User user = new User();
        user.setUserId(1);
        user.setName("Hema");
        user.setEmail("hema@gmail.com");
        user.setPassword("12345");

        when(userDAO.getUserByEmail("hema@gmail.com"))
                .thenReturn(new User());

        try {
            authService.registerUser(user);
            fail("Exception expected");
        } catch (RevPlayException e) {
            assertEquals("Email already exists", e.getMessage());
        }
    }

    @Test
    public void testRegisterUser_Success() throws RevPlayException {
        User user = new User();
        user.setUserId(2);
        user.setName("Hema");
        user.setEmail("hema2@gmail.com");
        user.setPassword("12345");

        when(userDAO.getUserByEmail("hema2@gmail.com"))
                .thenReturn(null);

        authService.registerUser(user);

        verify(userDAO, times(1)).registerUser(user);
    }


    @Test
    public void testLoginUser_InvalidCredentials() {
        when(userDAO.getUserByEmailAndPassword("wrong@mail.com", "wrong"))
                .thenReturn(null);

        try {
            authService.loginUser("wrong@mail.com", "wrong");
            fail("Exception expected");
        } catch (RevPlayException e) {
            assertEquals("Invalid user credentials", e.getMessage());
        }
    }

    @Test
    public void testLoginUser_Success() throws RevPlayException {
        User mockUser = new User();
        mockUser.setUserId(1);
        mockUser.setEmail("hema@gmail.com");

        when(userDAO.getUserByEmailAndPassword("hema@gmail.com", "12345"))
                .thenReturn(mockUser);

        User user = authService.loginUser("hema@gmail.com", "12345");

        assertNotNull(user);
        assertEquals("hema@gmail.com", user.getEmail());
    }


    @Test
    public void testRegisterArtist_DuplicateEmail() {
        Artist artist = new Artist();
        artist.setArtistId(1);
        artist.setName("AR Rahman");
        artist.setEmail("arr@gmail.com");
        artist.setPassword("music123");
        artist.setGenre("Classical");

        when(artistDAO.getArtistByEmail("arr@gmail.com"))
                .thenReturn(new Artist());

        try {
            authService.registerArtist(artist);
            fail("Exception expected");
        } catch (RevPlayException e) {
            assertEquals("Artist email already exists", e.getMessage());
        }
    }

    @Test
    public void testRegisterArtist_Success() throws RevPlayException {
        Artist artist = new Artist();
        artist.setArtistId(2);
        artist.setName("New Artist");
        artist.setEmail("new@gmail.com");
        artist.setPassword("music123");
        artist.setGenre("Pop");

        when(artistDAO.getArtistByEmail("new@gmail.com"))
                .thenReturn(null);

        authService.registerArtist(artist);

        verify(artistDAO, times(1)).registerArtist(artist);
    }
}
