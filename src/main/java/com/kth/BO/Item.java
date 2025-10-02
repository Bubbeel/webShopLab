package com.kth.BO;

import java.util.Collection;
import com.kth.DB.ItemDB;

public class Item {
    private int id;
    private String title;
    private String genre;
    private double price;

    public Item(int id, String title, String genre, double price) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.price = price;
    }

    public static Collection searchItems(String group) {
        return ItemDB.searchItems(group);
    }

    public static Collection<Item> getUserShoppingCart(int userId) {
        return ItemDB.getUserShoppingCart(userId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }
}
