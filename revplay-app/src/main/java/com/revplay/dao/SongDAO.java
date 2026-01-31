package com.revplay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revplay.model.Song;

public class SongDAO extends BaseDAO 
{
	
	public void searchSongs(String keyword) {

	    String sql = "SELECT song_id, title, genre FROM songs WHERE LOWER(title) LIKE ?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, "%" + keyword.toLowerCase() + "%");
	        ResultSet rs = ps.executeQuery();

	        System.out.println("\nSongs:");
	        boolean found = false;

	        while (rs.next()) {
	            found = true;
	            System.out.println(
	                rs.getInt("song_id") + " | " +
	                rs.getString("title") + " | " +
	                rs.getString("genre")
	            );
	        }

	        if (!found) {
	            System.out.println("No songs found");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void getSongsByGenre(String genre) {

	    String sql = "SELECT song_id, title FROM songs WHERE LOWER(genre) = ?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, genre.toLowerCase());
	        ResultSet rs = ps.executeQuery();

	        System.out.println("\nSongs in Genre: " + genre);
	        boolean found = false;

	        while (rs.next()) {
	            found = true;
	            System.out.println(
	                rs.getInt("song_id") + " | " +
	                rs.getString("title")
	            );
	        }

	        if (!found) {
	            System.out.println("No songs found");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void getSongsByArtist(String artistName) {

	    String sql =
	        "SELECT s.song_id, s.title " +
	        "FROM songs s JOIN artists a ON s.artist_id = a.artist_id " +
	        "WHERE LOWER(a.name) = ?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, artistName.toLowerCase());
	        ResultSet rs = ps.executeQuery();

	        System.out.println("\nSongs by Artist: " + artistName);
	        boolean found = false;

	        while (rs.next()) {
	            found = true;
	            System.out.println(
	                rs.getInt("song_id") + " | " +
	                rs.getString("title")
	            );
	        }

	        if (!found) {
	            System.out.println("No songs found");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public Song getSongById(int songId) {

        Song song = null;
        String sql = "SELECT * FROM songs WHERE song_id = ?";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, songId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                song = new Song();
                song.setSongId(rs.getInt("song_id"));
                song.setTitle(rs.getString("title"));
                song.setGenre(rs.getString("genre"));
                song.setDuration(rs.getInt("duration"));
                song.setArtistId(rs.getInt("artist_id"));
                // albumId intentionally NOT set
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return song;
    }
	public boolean addSong(Song song) {

	    String sql = "INSERT INTO songs (song_id, title, genre, duration, artist_id) " +
	                 "VALUES (songs_seq.NEXTVAL, ?, ?, ?, ?)";

	    try (
	        Connection con = getConnection();
	        PreparedStatement ps = con.prepareStatement(sql)
	    ) {
	        ps.setString(1, song.getTitle());
	        ps.setString(2, song.getGenre());
	        ps.setInt(3, song.getDuration());
	        ps.setInt(4, song.getArtistId());

	        return ps.executeUpdate() > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public void updateSong(int songId, String title, String genre, int duration) {
		String sql =
		        "UPDATE songs " +
		        "SET title = ?, genre = ?, duration = ? " +
		        "WHERE song_id = ?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, title);
	        ps.setString(2, genre);
	        ps.setInt(3, duration);
	        ps.setInt(4, songId);

	        int rows = ps.executeUpdate();
	        if (rows == 0) {
	            System.out.println("Song not found");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void deleteSong(int songId) {
	    String sql = "DELETE FROM songs WHERE song_id=?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, songId);
	        ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public int getPlayCount(int songId) {
	    String sql = "SELECT COUNT(*) FROM listening_history WHERE song_id = ?";
	    int count = 0;

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, songId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            count = rs.getInt(1);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return count;
	}


}
