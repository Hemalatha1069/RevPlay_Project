package com.revplay.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig 
{
	private static final String URL =
            "jdbc:oracle:thin:@localhost:1522:XE";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "Hema@123";

    private DatabaseConfig() {}

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found");
        }

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
