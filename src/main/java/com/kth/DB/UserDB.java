package com.kth.DB;

import com.kth.BO.Item;
import com.kth.BO.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDB extends com.kth.BO.User {

    public UserDB(String username, String password, int id) {
        super(username, password, id);
    }

    public static User findByUsername(String username) {
        String sql = "SELECT user_id, username, password FROM users WHERE username=?";
        try (Connection con = DBManager.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, username);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getInt("user_id")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateShoppingCart(int userId, String gameTitle) {
        String insertCartSql = "INSERT INTO shopping_carts (user_id) VALUES (?) " +
                "ON DUPLICATE KEY UPDATE user_id = user_id";
        String findCartSql = "SELECT cart_id FROM shopping_carts WHERE user_id = ?";
        String findGameSql = "SELECT game_id FROM games WHERE title=?";
        String insertCartItemSql = "INSERT INTO cart_items (cart_id, game_id) VALUES (?, ?)";

        try (Connection con = DBManager.getConnection()) {

            // Ensure cart exists
            try (PreparedStatement st1 = con.prepareStatement(insertCartSql)) {
                st1.setInt(1, userId);
                st1.executeUpdate();
            }

            int cartId = 0;
            try (PreparedStatement st2 = con.prepareStatement(findCartSql)) {
                st2.setInt(1, userId);
                try (ResultSet rs2 = st2.executeQuery()) {
                    if (rs2.next()) {
                        cartId = rs2.getInt("cart_id");
                    }
                }
            }

            int gameId = 0;
            try (PreparedStatement st3 = con.prepareStatement(findGameSql)) {
                st3.setString(1, gameTitle);
                try (ResultSet rs3 = st3.executeQuery()) {
                    if (rs3.next()) {
                        gameId = rs3.getInt("game_id");
                    }
                }
            }

            if (cartId > 0 && gameId > 0) {
                try (PreparedStatement st4 = con.prepareStatement(insertCartItemSql)) {
                    st4.setInt(1, cartId);
                    st4.setInt(2, gameId);
                    st4.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ItemDB> getShoppingCart(int userId) {
        ArrayList<ItemDB> cartItems = new ArrayList<>();
        String sql = "SELECT g.game_id, g.title, g.genre, g.price " +
                "FROM cart_items ci " +
                "JOIN shopping_carts sc ON ci.cart_id = sc.cart_id " +
                "JOIN games g ON ci.game_id = g.game_id " +
                "WHERE sc.user_id = ?";

        try (Connection con = DBManager.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, userId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    cartItems.add(new ItemDB(
                            rs.getInt("game_id"),
                            rs.getString("title"),
                            rs.getString("genre"),
                            rs.getDouble("price")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }
}
