package com.kth.DB;


import com.kth.BO.Item;

import java.sql.*;
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
}
