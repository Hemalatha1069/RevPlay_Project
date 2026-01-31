package com.revplay.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revplay.model.Song;

public class SongDAOTest 
{

    private SongDAO songDAO = new SongDAO();

    @Test
    public void testGetSongByIdSuccess() {
        Song song = songDAO.getSongById(1);
        assertNotNull(song);
        assertEquals("Shape of You", song.getTitle());
    }

    @Test
    public void testGetSongByIdFailure() {
        Song song = songDAO.getSongById(999);
        assertNull(song);
    }
    
}
