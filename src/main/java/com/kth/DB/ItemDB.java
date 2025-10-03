package com.kth.DB;


import com.kth.BO.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class ItemDB extends com.kth.BO.Item {

    public static Collection searchItems(){
        Vector v = new Vector();
        try {
            Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select id, title, genre, price from games");

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                double price = rs.getDouble("price");
                v.addElement(new ItemDB(id, title, genre, price));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return v;
    }

    protected ItemDB(int id, String title, String genre, double price) {
        super(id, title, genre, price);
    }

    public static Collection<Item> getUserShoppingCart(int userId) {
        ArrayList<Item> cart = new ArrayList<>();

        String query = "SELECT g.game_id, g.title, g.genre, g.price, ci.quantity FROM shopping_carts sc JOIN cart_items ci ON sc.cart_id = ci.cart_id JOIN games g ON ci.game_id = g.game_id WHERE sc.user_id = ? ";

        try {
            Connection con = DBManager.getConnection();
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, userId);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("game_id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                double price = rs.getDouble("price");
                Item item = new Item(id, title, genre, price);
                cart.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Item item : cart) {
            System.out.println(cart.get(0));
        }
        return cart;
    }
}
