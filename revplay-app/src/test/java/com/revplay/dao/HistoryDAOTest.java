package com.revplay.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class HistoryDAOTest 
{
	 private HistoryDAO historyDAO = new HistoryDAO();

	    @Test
	    public void testHistoryExists() {
	        List<String> history = historyDAO.getUserHistory(1);
	        assertNotNull(history);
	    }

	    @Test
	    public void testHistoryEmpty() {
	        List<String> history = historyDAO.getUserHistory(999);
	        assertTrue(history.isEmpty());
	    }
	    
}
