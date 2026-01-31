package com.revplay.service;

import com.revplay.dao.BaseDAO;
import com.revplay.dao.FavoriteDAO;
import com.revplay.dao.SongDAO;
import com.revplay.model.Song;

public class FavoriteService extends BaseDAO {

    private FavoriteDAO favoriteDAO = new FavoriteDAO();
    private SongDAO songDAO = new SongDAO();   

    public void addFavorite(int userId, int songId) {   

        Song song = songDAO.getSongById(songId);

        if (song == null) {
            System.out.println("Song not found. Cannot add to favorites.");
            return;
        }

        try {
            favoriteDAO.addFavorite(userId, songId);
            System.out.println("Song added to favorites: " + song.getTitle());
        } catch (Exception e) {
            System.out.println("Song already in favorites");
        }
    }

    public void viewFavorites(int userId) {
        favoriteDAO.getFavoritesByUser(userId);
    }

    public void removeFavorite(int userId, int songId) {
        favoriteDAO.removeFavorite(userId, songId);
        System.out.println("Song removed from favorites");
    }
}
