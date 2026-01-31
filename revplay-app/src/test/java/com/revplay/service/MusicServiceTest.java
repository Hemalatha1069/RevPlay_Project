package com.revplay.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class MusicServiceTest 
{
	 private MusicService musicService = new MusicService();

	    @Test
	    public void testPlaySong() {
	        musicService.playSong(1, 1);
	        assertTrue(true); // no exception = pass
	    }

	    @Test
	    public void testPauseSong() {
	        musicService.pauseSong();
	        assertTrue(true);
	    }
	
}
