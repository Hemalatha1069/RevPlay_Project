package com.revplay.service;

import com.revplay.dao.ArtistDAO;
import com.revplay.dao.UserDAO;
import com.revplay.exception.RevPlayException;
import com.revplay.model.Artist;
import com.revplay.model.User;
import com.revplay.utils.PasswordUtil;

public class AuthService 
{
	private UserDAO userDAO = new UserDAO();
    private ArtistDAO artistDAO = new ArtistDAO();

    public void registerUser(User user) throws RevPlayException {

        if (user.getUserId() <= 0) {
            throw new RevPlayException("User ID must be positive");
        }

        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new RevPlayException("Name cannot be empty");
        }

        if (user.getEmail() == null || !user.getEmail().contains("@")) {
            throw new RevPlayException("Invalid email format");
        }

        if (user.getPassword() == null || user.getPassword().length() < 5) {
            throw new RevPlayException("Password must be at least 5 characters");
        }

        userDAO.registerUser(user);
    }

    
    public void registerArtist(Artist artist) throws RevPlayException {

        if (artist == null) {
            throw new RevPlayException("Artist cannot be null");
        }

        if (artist.getEmail() == null || artist.getPassword() == null) {
            throw new RevPlayException("Email and password are required");
        }

        artistDAO.registerArtist(artist);
    }

    public User loginUser(String email, String password) throws RevPlayException {

        if (email == null || email.trim().isEmpty()) {
            throw new RevPlayException("Email cannot be empty");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new RevPlayException("Password cannot be empty");
        }

        User user = userDAO.getUserByEmailAndPassword(email, password);

        if (user == null) {
            throw new RevPlayException("Invalid user credentials");
        }

        return user;
    }

   
    public Artist loginArtist(String email, String password) throws RevPlayException {

        if (email == null || email.trim().isEmpty()) {
            throw new RevPlayException("Email cannot be empty");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new RevPlayException("Password cannot be empty");
        }

        Artist artist = artistDAO.getArtistByEmailAndPassword(email, password);

        if (artist == null) {
            throw new RevPlayException("Invalid artist credentials");
        }

        return artist;
    }


    
    public void changePassword(int userId, String newPassword) throws RevPlayException {

        if (newPassword == null || newPassword.length() < 5) {
            throw new RevPlayException("Password must be at least 5 characters");
        }

        String hashed = PasswordUtil.hashPassword(newPassword);

        boolean updated = userDAO.updatePassword(userId, hashed);

        if (!updated) {
            throw new RevPlayException("Password update failed");
        }
    }


    public void forgotPassword(String email, String newPassword) throws RevPlayException {

        User user = userDAO.getUserByEmail(email);

        if (user == null) {
            throw new RevPlayException("Email not found");
        }

        String hashed = PasswordUtil.hashPassword(newPassword);
        userDAO.updatePassword(user.getUserId(), hashed);
    }
    
    

}
