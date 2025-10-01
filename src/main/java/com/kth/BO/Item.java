package com.kth.BO;

import java.util.Collection;

public class Item {
    private String name;
    private String type;
    private int id;

    protected Item(String name, String type, int id) {
        this.name = name;
        this.type = type;
        this.id = id;
    }

    public static Collection searchItems(String group) {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
