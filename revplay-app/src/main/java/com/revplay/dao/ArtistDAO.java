package com.revplay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revplay.model.Artist;
import com.revplay.utils.PasswordUtil;

public class ArtistDAO extends BaseDAO 
{
	public void saveArtist(Artist artist) 
	{}
	
	public void registerArtist(Artist artist) {
	    String sql = "INSERT INTO artists (artist_id, name, email, password, genre) VALUES (?, ?, ?, ?, ?)";

	    try (
	        Connection con = getConnection();
	        PreparedStatement ps = con.prepareStatement(sql)
	    ) {
	        ps.setInt(1, artist.getArtistId());
	        ps.setString(2, artist.getName());
	        ps.setString(3, artist.getEmail());
	        ps.setString(4, PasswordUtil.hashPassword(artist.getPassword()));
	        ps.setString(5, artist.getGenre());
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void searchArtists(String keyword) {

	    String sql = "SELECT artist_id, name, genre FROM artists WHERE LOWER(name) LIKE ?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, "%" + keyword.toLowerCase() + "%");
	        ResultSet rs = ps.executeQuery();

	        System.out.println("\nArtists:");
	        boolean found = false;

	        while (rs.next()) {
	            found = true;
	            System.out.println(
	                rs.getInt("artist_id") + " | " +
	                rs.getString("name") + " | " +
	                confirm(rs.getString("genre"))
	            );
	        }

	        if (!found) {
	            System.out.println("No artists found");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	private String confirm(String value) {
	    return value == null ? "-" : value;
	}


	public Artist getArtistByEmailAndPassword(String email, String password) {

	    Artist artist = null;
	    String sql = "SELECT * FROM artists WHERE email = ?";

	    try (
	        Connection con = getConnection();
	        PreparedStatement ps = con.prepareStatement(sql)
	    ) {
	        ps.setString(1, email);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String storedHash = rs.getString("password");
	            String inputHash = PasswordUtil.hashPassword(password);

	            if (storedHash.equals(inputHash)) {
	                artist = new Artist();
	                artist.setArtistId(rs.getInt("artist_id"));
	                artist.setName(rs.getString("name"));
	                artist.setEmail(rs.getString("email"));
	                artist.setGenre(rs.getString("genre"));
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return artist;
	}
	



}
