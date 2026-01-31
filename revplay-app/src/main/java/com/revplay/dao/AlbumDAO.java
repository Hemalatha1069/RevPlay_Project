package com.revplay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revplay.model.Album;
import com.revplay.model.Song;

public class AlbumDAO extends BaseDAO 
{
	public void saveAlbum(Album album) 
	{}

	public void searchAlbums(String keyword) {

	    String sql = "SELECT album_id, title FROM albums WHERE LOWER(title) LIKE ?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, "%" + keyword.toLowerCase() + "%");
	        ResultSet rs = ps.executeQuery();

	        System.out.println("\nAlbums:");
	        boolean found = false;

	        while (rs.next()) {
	            found = true;
	            System.out.println(
	                rs.getInt("album_id") + " | " +
	                rs.getString("title")
	            );
	        }

	        if (!found) {
	            System.out.println("No albums found");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void getAlbumsByTitle(String albumTitle) {

	    String sql = "SELECT album_id, title FROM albums WHERE LOWER(title) = ?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, albumTitle.toLowerCase());
	        ResultSet rs = ps.executeQuery();

	        System.out.println("\nAlbums: " + albumTitle);
	        boolean found = false;

	        while (rs.next()) {
	            found = true;
	            System.out.println(
	                rs.getInt("album_id") + " | " +
	                rs.getString("title")
	            );
	        }

	        if (!found) {
	            System.out.println("No albums found");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public Album getAlbumById(int albumId) {

	    Album album = null;
	    String sql = "SELECT * FROM albums WHERE album_id = ?";

	    try (
	        Connection con = getConnection();
	        PreparedStatement ps = con.prepareStatement(sql)
	    ) {
	        ps.setInt(1, albumId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) 
	        {
	            album = new Album();
	            album.setAlbumId(rs.getInt("album_id"));
	            album.setTitle(rs.getString("title"));
	            album.setArtistId(rs.getInt("artist_id"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return album;
	}
	private SongDAO songDAO = new SongDAO();

    public void uploadSong(Song song) {

        boolean inserted = songDAO.addSong(song);

        if (inserted) {
            System.out.println(" Song uploaded successfully");
        } else {
            System.out.println(" Song upload failed");
        }
    }
    public boolean createAlbum(Album album) {

        String sql =
            "INSERT INTO albums (album_id, title, artist_id, release_date) " +
            "VALUES (albums_seq.NEXTVAL, ?, ?, ?)";

        try (
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, album.getTitle());
            ps.setInt(2, album.getArtistId());
            ps.setString(3, album.getReleaseDate());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void updateAlbum(int albumId, String title, String releaseDate) {
    	String sql =
    	        "UPDATE albums " +
    	        "SET title = ?, release_date = ? " +
    	        "WHERE album_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, releaseDate);
            ps.setInt(3, albumId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAlbum(int albumId) {
        String sql = "DELETE FROM albums WHERE album_id=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, albumId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
