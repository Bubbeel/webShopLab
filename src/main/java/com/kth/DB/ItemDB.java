package com.kth.DB;

import com.kth.BO.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ItemDB extends com.kth.BO.Item {

    protected ItemDB(int id, String title, String genre, double price) {
        super(id, title, genre, price);
    }

    public static Collection<Item> searchItems() {
        ArrayList<Item> items = new ArrayList<>();
        String sql = "SELECT game_id, title, genre, price FROM games";

        try (Connection con = DBManager.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                items.add(new ItemDB(
                        rs.getInt("game_id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getDouble("price")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static Collection<Item> getUserShoppingCart(int userId) {
        ArrayList<Item> cart = new ArrayList<>();
        String query = "SELECT g.game_id, g.title, g.genre, g.price, ci.quantity " +
                "FROM shopping_carts sc " +
                "JOIN cart_items ci ON sc.cart_id = ci.cart_id " +
                "JOIN games g ON ci.game_id = g.game_id " +
                "WHERE sc.user_id = ?";

        try (Connection con = DBManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            st.setInt(1, userId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    cart.add(new Item(
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
        return cart;
    }

    public static Collection<Item> searchItemsByName(String search) {
        ArrayList<Item> searchResults = new ArrayList<>();
        String query = "SELECT game_id, title, genre, price FROM games WHERE title LIKE ?";

        try (Connection con = DBManager.getConnection();
             PreparedStatement st = con.prepareStatement(query)) {

            st.setString(1, "%" + search + "%");

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    searchResults.add(new Item(
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

        return searchResults;
    }
}
