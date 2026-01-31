package com.revplay.service;

import java.util.List;

import com.revplay.dao.AlbumDAO;
import com.revplay.dao.ArtistDAO;
import com.revplay.dao.FavoriteDAO;
import com.revplay.dao.HistoryDAO;
import com.revplay.dao.SongDAO;
import com.revplay.model.History;
import com.revplay.model.Song;

public class MusicService 
{
    private HistoryDAO historyDAO = new HistoryDAO();
    private SongDAO songDAO = new SongDAO();
    private ArtistDAO artistDAO = new ArtistDAO();
    private AlbumDAO albumDAO = new AlbumDAO();
    private FavoriteDAO favoriteDAO = new FavoriteDAO();
    
    
    public void searchLibrary(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Search keyword cannot be empty");
            return;
        }

        System.out.println("\n--- Search Results ---");

        songDAO.searchSongs(keyword);
        artistDAO.searchArtists(keyword);
        albumDAO.searchAlbums(keyword);
    }

    public void browseByGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            System.out.println("Genre cannot be empty");
            return;
        }
        songDAO.getSongsByGenre(genre);
    }

    public void browseByArtist(String artistName) {
        if (artistName == null || artistName.trim().isEmpty()) {
            System.out.println("Artist name cannot be empty");
            return;
        }
        songDAO.getSongsByArtist(artistName);
    }

    public void browseByAlbum(String albumTitle) {
        if (albumTitle == null || albumTitle.trim().isEmpty()) {
            System.out.println("Album name cannot be empty");
            return;
        }
        albumDAO.getAlbumsByTitle(albumTitle);
    }


    public void playSong(int songId, int userId) 
    {
        Song song = songDAO.getSongById(songId);

        if (song != null) 
        {
            System.out.println("Playing song: " + song.getTitle());

            History history = new History();
            history.setSongId(songId);
            history.setUserId(userId);

            historyDAO.saveHistory(history);
        } 
        else 
        {
            System.out.println("Song not found");
        }
    }

    public void pauseSong() 
    {
        System.out.println("Song paused");
    }

    public void skipSong() 
    {
        System.out.println("Song skipped");
    }

    public void repeatSong() 
    {
        System.out.println("Repeating song");
    }
    
    public void addToFavorites(int userId, int songId) {
        favoriteDAO.addFavorite(userId, songId);
        System.out.println("Song added to favorites");
    }

    public void viewFavorites(int userId) {
        favoriteDAO.getFavoritesByUser(userId);
    }


    public void viewHistory(int userId) 
    {
        List<String> historyList = historyDAO.getUserHistory(userId);

        if (historyList.isEmpty()) 
        {
            System.out.println("No play history found.");
            return;
        }

        System.out.println("----- Your Play History -----");
        for (String record : historyList) 
        {
            System.out.println(record);
        }
    }
}

