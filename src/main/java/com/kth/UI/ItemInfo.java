package com.kth.UI;

import com.kth.BO.Item;
import com.kth.DB.ItemDB;

import java.util.Collection;

public class ItemInfo {
    private String title;
    private String genre;
    private double price;

    public ItemInfo(String genre, String title, double price) {
        this.title = title;
        this.genre = genre;
        this .price = price;
    }

    public static Collection<Item> getUserShoppingCart(int userId) {
        return Item.getUserShoppingCart(userId);
    }

    public static Collection<Item> searchItemsByName(String search) {
        return  Item.searchItemsByName(search);
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

}
