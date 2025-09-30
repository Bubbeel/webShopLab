package com.kth.BO;

import java.util.ArrayList;

public class User {
    private final String username;
    private final int id;
    private ArrayList<Item> shoppingCart;


    public User(String username, int id) {
        this.username = username;
        this.id = id;
        this.shoppingCart = new ArrayList<Item>();
    }

    private ArrayList<Item> addToShoppingCart(Item item) {
        shoppingCart.add(item);
        return shoppingCart;
    }

    private void viewShoppingCart() {
        if (shoppingCart.isEmpty()) {
            System.out.println("No Items");
        }
        else {
            System.out.println("Shopping cart contains");
            for (Item item : shoppingCart) {
                System.out.println("- " + item);
            }
        }
    }
}
