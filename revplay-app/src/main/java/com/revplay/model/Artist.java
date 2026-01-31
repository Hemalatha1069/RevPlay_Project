package com.revplay.model;

public class Artist 
{
	private int artistId;
    private String name;
    private String email;
    private String password;
    private String genre;
    public Artist() {}

    public Artist(int artistId, String name, String email, String password) 
    {
        this.artistId = artistId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getArtistId() 
    {
        return artistId;
    }

    public void setArtistId(int artistId) 
    {
        this.artistId = artistId;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }
    public String getGenre() 
    {
        return genre;
    }

    public void setGenre(String genre) 
    {
        this.genre = genre;
    }
}
