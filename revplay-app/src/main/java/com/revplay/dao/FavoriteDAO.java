package com.revplay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FavoriteDAO extends BaseDAO 
{
	public void addFavorite(int userId, int songId) {
	    String sql = "INSERT INTO favorites (user_id, song_id) VALUES (?, ?)";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, userId);
	        ps.setInt(2, songId);

	        int rows = ps.executeUpdate();

	        if (rows > 0) {
	            System.out.println("Song added to favorites");
	        }

	    } catch (Exception e) {
	        System.out.println("Failed to add favorite");
	        System.out.println("Reason: " + e.getMessage());
	    }
	}



	public void getFavoritesByUser(int userId) {

	    String sql =
	        "SELECT s.song_id, s.title " +
	        "FROM favorites f JOIN songs s ON f.song_id = s.song_id " +
	        "WHERE f.user_id = ?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, userId);
	        ResultSet rs = ps.executeQuery();

	        System.out.println("\n--- Favorite Songs ---");
	        boolean found = false;

	        while (rs.next()) {
	            found = true;
	            System.out.println(
	                rs.getInt("song_id") + " | " +
	                rs.getString("title")
	            );
	        }

	        if (!found) {
	            System.out.println("No favorite songs found");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	public void removeFavorite(int userId, int songId) {

	    String sql = "DELETE FROM favorites WHERE user_id = ? AND song_id = ?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, userId);
	        ps.setInt(2, songId);
	        int rows = ps.executeUpdate();

	        if (rows > 0) {
	            System.out.println("Song removed from favorites");
	        } else {
	            System.out.println("Song not found in favorites");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void getUsersWhoFavoritedSong(int songId) {

	    String sql =
	        "SELECT u.user_id, u.name, u.email " +
	        "FROM favorites f " +
	        "JOIN users u ON f.user_id = u.user_id " +
	        "WHERE f.song_id = ?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, songId);
	        ResultSet rs = ps.executeQuery();

	        System.out.println("\n--- Users who favorited this song ---");
	        boolean found = false;

	        while (rs.next()) {
	            found = true;
	            System.out.println(
	                rs.getInt("user_id") + " | " +
	                rs.getString("name") + " | " +
	                rs.getString("email")
	            );
	        }

	        if (!found) {
	            System.out.println("No users have favorited this song yet.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


}
