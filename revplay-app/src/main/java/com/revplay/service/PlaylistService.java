package com.revplay.service;

import com.revplay.dao.PlaylistDAO;
import com.revplay.exception.RevPlayException;
import com.revplay.model.Playlist;

public class PlaylistService 
{
	private PlaylistDAO playlistDAO = new PlaylistDAO();

	public void createPlaylist(int userId, int playlistId, String name, int isPublic) 
	{
	    playlistDAO.createPlaylist(userId, playlistId, name, isPublic);
	    System.out.println("Playlist created successfully");
	}
	
	public void addSongToPlaylist(int playlistId, int songId) 
	{
	    playlistDAO.addSong(playlistId, songId);
	    System.out.println("Song added to playlist");
	}
	
	public void removeSongFromPlaylist(int playlistId, int songId) 
	{
	    playlistDAO.removeSong(playlistId, songId);
	    System.out.println("Song removed from playlist");
	}

	public void viewUserPlaylists(int userId) 
	{
	    playlistDAO.getUserPlaylists(userId);
	}

	public void updatePlaylist(int playlistId, String name) 
	{
	    playlistDAO.updatePlaylist(playlistId, name);
	    System.out.println("Playlist updated");
	}
    public Playlist getPlaylist(int playlistId) throws RevPlayException 
    {
        if (playlistId <= 0) 
        {
            throw new RevPlayException("Invalid playlist ID");
        }
        return playlistDAO.getPlaylistById(playlistId);
    }

    public void deletePlaylist(int playlistId) 
    {
        playlistDAO.deletePlaylist(playlistId);
        System.out.println("Playlist deleted");
    }
    
    public void viewPublicPlaylists() {
        playlistDAO.getPublicPlaylists();
    }
}
