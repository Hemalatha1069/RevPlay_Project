package com.revplay.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.revplay.config.DatabaseConfig;

public class BaseDAO 
{
	protected Connection getConnection() throws SQLException 
	{
        return DatabaseConfig.getConnection();
    }
}
