package com.kth.DB;

import com.kth.BO.User;
import java.sql.*;

public class UserDB extends com.kth.BO.User {

    public UserDB(String username, String password, int id) {
        super(username, password, id);
    }

    public static User findByUsername(String username){
        try {
            Connection con = DBManager.getConnection();
            PreparedStatement st = con.prepareStatement("SELECT user_id, username, password FROM users WHERE username=?");
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String usern = rs.getString("username");
                String password = rs.getString("password");
                return new User(usern, password, id);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
