package com.revplay.model;


public class Song 
{
	
	private int songId;
    private String title;
    private String genre;
    private int duration;
    private int artistId;

    public Song() {}

    public Song(int songId, String title, String genre, int duration, int artistId) {
        this.songId = songId;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.artistId = artistId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }
    
    @Override
	public String toString() {
	    return " Song Details\n" +
	           "------------------\n" +
	           "ID       : " + songId + "\n" +
	           "Title    : " + title + "\n" +
	           "Genre    : " + genre + "\n" +
	           "Duration : " + duration + " sec";
	}
	
}
