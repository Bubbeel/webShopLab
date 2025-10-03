package com.kth.DB;

import com.kth.BO.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

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

    public static void updateShoppingCart(int userId, String gameTitle){
        try {
            Connection con = DBManager.getConnection();

            PreparedStatement st1 = con.prepareStatement("INSERT INTO shopping_carts (user_id) VALUES (?) ON DUPLICATE KEY UPDATE user_id = user_id");
            st1.setInt(1, userId);
            st1.executeUpdate();

            PreparedStatement st2 = con.prepareStatement("SELECT cart_id FROM shopping_carts WHERE user_id = ?");
            st2.setInt(1, userId);
            ResultSet rs2 = st2.executeQuery();
            int cartId = 0;
            if (rs2.next()) {
                cartId = rs2.getInt("cart_id");
            }

            PreparedStatement st3 = con.prepareStatement("SELECT game_id FROM games WHERE title=?");
            st3.setString(1, gameTitle);
            ResultSet rs3 = st3.executeQuery();
            int gameId = 0;
            if (rs3.next()) {
                gameId = rs3.getInt("game_id");
            }

            PreparedStatement st4 = con.prepareStatement("INSERT INTO cart_items (cart_id, game_id) VALUES (?, ?)");
            st4.setInt(1, cartId);
            st4.setInt(2, gameId);

            st4.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<ItemDB> getShoppingCart(int userId) {
        ArrayList<ItemDB> cartItems = new ArrayList<>();

        try (Connection con = DBManager.getConnection()) {
            PreparedStatement st = con.prepareStatement(
                    "SELECT g.game_id, g.title, g.genre, g.price " +
                            "FROM cart_items ci " +
                            "JOIN shopping_carts sc ON ci.cart_id = sc.cart_id " +
                            "JOIN games g ON ci.game_id = g.game_id " +
                            "WHERE sc.user_id = ?"
            );
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ItemDB item = new ItemDB(
                        rs.getInt("game_id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getDouble("price")
                );
                cartItems.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }

}
