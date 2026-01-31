package com.revplay.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revplay.model.User;
import com.revplay.utils.PasswordUtil;

public class UserDAO extends BaseDAO 
{
	public void registerUser(User user) {
	    String sql = "INSERT INTO users (user_id, name, email, password) VALUES (?, ?, ?, ?)";

	    try (
	        Connection con = getConnection();
	        PreparedStatement ps = con.prepareStatement(sql)
	    ) {
	        ps.setInt(1, user.getUserId());
	        ps.setString(2, user.getName());
	        ps.setString(3, user.getEmail());
	        ps.setString(4, PasswordUtil.hashPassword(user.getPassword()));
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	public User getUserByEmailAndPassword(String email, String password) {

        User user = null;

        String sql = "SELECT * FROM users WHERE email = ?";

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
                    user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    // ❌ do NOT set password back into object
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
	
	public User getUserByEmail(String email) {

	    User user = null;
	    String sql = "SELECT * FROM users WHERE email = ?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, email);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            user = new User();
	            user.setUserId(rs.getInt("user_id"));
	            user.setEmail(rs.getString("email"));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return user;
	}
	public boolean updatePassword(int userId, String hashedPassword) {

	    String sql = "UPDATE users SET password = ? WHERE user_id = ?";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, hashedPassword);
	        ps.setInt(2, userId);

	        int rows = ps.executeUpdate();
	        return rows > 0;   // ✅ success indicator

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}


}
