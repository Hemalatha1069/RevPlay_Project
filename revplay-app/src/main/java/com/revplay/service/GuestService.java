package com.revplay.service;

import com.revplay.dao.AlbumDAO;
import com.revplay.dao.SongDAO;
import com.revplay.exception.RevPlayException;
import com.revplay.model.Album;
import com.revplay.model.Song;

public class GuestService 
{
	private SongDAO songDAO = new SongDAO();
    private AlbumDAO albumDAO = new AlbumDAO();

    public Song viewSong(int songId) throws RevPlayException {

        if (songId <= 0) {
            throw new RevPlayException("Invalid Song ID");
        }

        Song song = songDAO.getSongById(songId);

        if (song == null) {
            throw new RevPlayException("Song not found");
        }

        return song;
    }

    public Album viewAlbum(int albumId) {
        Album album = albumDAO.getAlbumById(albumId);
        if (album == null) {
            throw new RuntimeException("Album not found");
        }
        return album;
    }
}
