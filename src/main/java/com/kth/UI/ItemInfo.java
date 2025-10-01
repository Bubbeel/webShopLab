package com.kth.UI;

public class ItemInfo {
    private String title;
    private String genre;
    private double price;

    public ItemInfo(String genre, String title, double price) {
        this.title = title;
        this.genre = genre;
        this .price = price;
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
