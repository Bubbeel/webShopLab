package com.kth.BO;

import com.kth.DB.UserDB;

import java.util.ArrayList;

public class  User {
    private final String username;
    private final String password;
    private final int id;
    private ArrayList<Item> shoppingCart;

    public User(String username, String password, int id) {
        this.username = username;
        this.password = password;
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

    public static User authenticate(String username, String password) {
        User dbUser = UserDB.findByUsername(username);
        if (dbUser == null) {
            return null;
        }

        if (dbUser.password.equals(password)) {
            return dbUser;
        } else {
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Item> getShoppingCart() {
        return shoppingCart;
    }

    public void updateShoppingCart(String gameTitle) {
        UserDB.updateShoppingCart(this.id, gameTitle);
    }
}
