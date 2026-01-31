package com.revplay.model;

public class Playlist 
{
	private int playlistId;
    private String name;
    private int userId;
    private int isPublic;

    public Playlist() {}

    public Playlist(int playlistId, String name, int userId) 
    {
        this.playlistId = playlistId;
        this.name = name;
        this.userId = userId;
    }

    public int getPlaylistId() 
    {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) 
    {
        this.playlistId = playlistId;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public int getUserId() 
    {
        return userId;
    }

    public void setUserId(int userId) 
    {
        this.userId = userId;
    }
    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

}
