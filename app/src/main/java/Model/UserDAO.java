package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	public boolean insertUser(User user) {
	    boolean result = false;

	    try (Connection conn = DBconnector.getConnection()) {
	        String sql = "INSERT INTO user (id, name, password) VALUES (?, ?, ?)";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, user.getId());
	        pstmt.setString(2, user.getName()); 
	        pstmt.setString(3, user.getPassword()); 

	        int rows = pstmt.executeUpdate();
	        if (rows > 0) {
	            result = true;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return result;
	}

    
    public User findUserById(String id) {
        User user = null;

        try (Connection conn = DBconnector.getConnection()) {
            String sql = "SELECT * FROM user WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");
                user = new User(id, name, password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
}

