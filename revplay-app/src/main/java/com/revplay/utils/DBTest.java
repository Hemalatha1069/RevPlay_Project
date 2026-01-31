package com.revplay.utils;

import java.sql.Connection;
import com.revplay.config.DatabaseConfig;

public class DBTest {

	public static void main(String[] args) 
	{
		try (Connection con = DatabaseConfig.getConnection()) 
		{
            System.out.println("Database connected successfully!");
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
	}

}
