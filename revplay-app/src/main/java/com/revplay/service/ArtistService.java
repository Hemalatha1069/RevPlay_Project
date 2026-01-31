package com.revplay.service;

import com.revplay.dao.AlbumDAO;
import com.revplay.dao.SongDAO;
import com.revplay.exception.RevPlayException;
import com.revplay.model.Album;
import com.revplay.model.Song;
import com.revplay.dao.FavoriteDAO;

public class ArtistService 
{
	private SongDAO songDAO = new SongDAO();
	private FavoriteDAO favoriteDAO = new FavoriteDAO();
	
    public void uploadSong(Song song) {

        boolean inserted = songDAO.addSong(song);

        if (inserted) {
            System.out.println(" Song uploaded successfully");
        } else {
            System.out.println(" Song upload failed");
        }
        if (song.getTitle() == null || song.getTitle().isEmpty()) {
            System.out.println("Song title cannot be empty");
            return;
        }

        if (song.getDuration() <= 0) {
            System.out.println("Invalid song duration");
            return;
        }

    }
    
    private AlbumDAO albumDAO = new AlbumDAO();

    public void createAlbum(Album album) {

        boolean created = albumDAO.createAlbum(album);

        if (created) {
            System.out.println(" Album created successfully");
        } else {
            System.out.println(" Album creation failed");
        }
    }
    
    public void updateSong(Song song) throws RevPlayException {

        if (song == null) {
            throw new RevPlayException("Song cannot be null");
        }

        if (song.getTitle() == null || song.getTitle().trim().isEmpty()) {
            throw new RevPlayException("Song title cannot be empty");
        }

        if (song.getGenre() == null || song.getGenre().trim().isEmpty()) {
            throw new RevPlayException("Genre cannot be empty");
        }

        if (song.getDuration() <= 0) {
            throw new RevPlayException("Duration must be greater than 0");
        }

        songDAO.updateSong(
            song.getSongId(),
            song.getTitle(),
            song.getGenre(),
            song.getDuration()
        );

        System.out.println("Song updated successfully");
    }


    public void updateAlbum(Album album) {
        albumDAO.updateAlbum(
            album.getAlbumId(),
            album.getTitle(),
            album.getReleaseDate()
        );
        System.out.println("Album updated successfully");
    }

    public void deleteSong(int songId) throws RevPlayException {
        if (songId <= 0) {
            throw new RevPlayException("Invalid Song ID");
        }
        songDAO.deleteSong(songId);
        System.out.println("Song deleted successfully");
    }

    public void deleteAlbum(int albumId) throws RevPlayException {
        if (albumId <= 0) {
            throw new RevPlayException("Invalid Album ID");
        }
        albumDAO.deleteAlbum(albumId);
        System.out.println("Album deleted successfully");
    }

    public void viewSongPlayCount(int songId) throws RevPlayException {

        if (songId <= 0) {
            throw new RevPlayException("Invalid Song ID");
        }

        int count = songDAO.getPlayCount(songId);

        System.out.println("Total Plays: " + count);
    }
    
    public void viewUsersWhoFavoritedSong(int songId) throws RevPlayException {

        if (songId <= 0) {
            throw new RevPlayException("Invalid Song ID");
        }

        favoriteDAO.getUsersWhoFavoritedSong(songId);
    }

}
