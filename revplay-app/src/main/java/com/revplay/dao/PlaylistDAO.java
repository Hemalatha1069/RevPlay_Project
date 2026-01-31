package com.revplay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revplay.model.Playlist;

public class PlaylistDAO extends BaseDAO {

    public void createPlaylist(int userId, int playlistId, String name, int isPublic) {
        String sql = "INSERT INTO playlists VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ps.setInt(2, userId);
            ps.setString(3, name);
            ps.setInt(4, isPublic);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSong(int playlistId, int songId) {
        String sql = "INSERT INTO playlist_songs VALUES (?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ps.setInt(2, songId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Song already exists in playlist");
        }
    }

    public void removeSong(int playlistId, int songId) {
        String sql = "DELETE FROM playlist_songs WHERE playlist_id = ? AND song_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ps.setInt(2, songId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getUserPlaylists(int userId) {
        String sql = "SELECT playlist_id, name FROM playlists WHERE user_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- My Playlists ---");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("playlist_id") + " | " +
                    rs.getString("name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePlaylist(int playlistId, String name) {
        String sql = "UPDATE playlists SET name = ? WHERE playlist_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, playlistId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePlaylist(int playlistId) {
        String sql = "DELETE FROM playlists WHERE playlist_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPublicPlaylists() {
        String sql = "SELECT playlist_id, name FROM playlists WHERE is_public = 1";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            System.out.println("\n--- Public Playlists ---");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("playlist_id") + " | " +
                    rs.getString("name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Playlist getPlaylistById(int playlistId) {

        String sql = "SELECT playlist_id, name, user_id, is_public FROM playlists WHERE playlist_id = ?";
        Playlist playlist = null;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                playlist = new Playlist();
                playlist.setPlaylistId(rs.getInt("playlist_id"));
                playlist.setName(rs.getString("name"));
                playlist.setUserId(rs.getInt("user_id"));
                playlist.setIsPublic(rs.getInt("is_public"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return playlist;
    }

}
