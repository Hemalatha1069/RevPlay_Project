package com.revplay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revplay.model.History;

public class HistoryDAO extends BaseDAO {

    public void saveHistory(History history) {

        String sql =
            "INSERT INTO listening_history (history_id, user_id, song_id, played_at) " +
            "VALUES (listening_history_seq.NEXTVAL, ?, ?, CURRENT_TIMESTAMP)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, history.getUserId());
            ps.setInt(2, history.getSongId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getUserHistory(int userId) {

        List<String> historyList = new ArrayList<>();

        String sql =
            "SELECT h.history_id, s.title, s.genre, h.played_at " +
            "FROM listening_history h " +
            "JOIN songs s ON h.song_id = s.song_id " +
            "WHERE h.user_id = ? " +
            "ORDER BY h.played_at DESC";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String record =
                    "🎵 " + rs.getString("title") +
                    " | " + rs.getString("genre") +
                    " | Played at: " + rs.getTimestamp("played_at");

                historyList.add(record);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return historyList;
    }
}
