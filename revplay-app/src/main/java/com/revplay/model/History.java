package com.revplay.model;

public class History 
{
	private int historyId;
    private int userId;
    private int songId;
    private String playedAt;   

    public History() {}

    public History(int historyId, int userId, int songId, String playedAt) 
    {
        this.historyId = historyId;
        this.userId = userId;
        this.songId = songId;
        this.playedAt = playedAt;
    }

    public int getHistoryId() 
    {
        return historyId;
    }

    public void setHistoryId(int historyId) 
    {
        this.historyId = historyId;
    }

    public int getUserId() 
    {
        return userId;
    }

    public void setUserId(int userId) 
    {
        this.userId = userId;
    }

    public int getSongId() 
    {
        return songId;
    }

    public void setSongId(int songId) 
    {
        this.songId = songId;
    }

    public String getPlayedAt() 
    {
        return playedAt;
    }

    public void setPlayedAt(String playedAt) 
    {
        this.playedAt = playedAt;
    }
}
