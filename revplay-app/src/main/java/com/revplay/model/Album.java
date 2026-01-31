package com.revplay.model;

public class Album 
{
	private int albumId;
    private String title;
    private int artistId;
    private String releaseDate;

    public Album() {}

    public Album(int albumId, String title, int artistId, String releaseDate) 
    {
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
        this.releaseDate = releaseDate;
    }

    public int getAlbumId() 
    {
        return albumId;
    }

    public void setAlbumId(int albumId) 
    {
        this.albumId = albumId;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public int getArtistId() 
    {
        return artistId;
    }

    public void setArtistId(int artistId) 
    {
        this.artistId = artistId;
    }

    public String getReleaseDate() 
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) 
    {
        this.releaseDate = releaseDate;
    }
    
    @Override
    public String toString() {
        return " Album Details\n" +
               "------------------\n" +
               "ID    : " + albumId + "\n" +
               "Title : " + title;
    }

}
