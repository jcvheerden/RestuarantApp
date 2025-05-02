package com.example.tastehaven_application;

public class MenuItem {
    public String item_id;
    public String name;
    public String description;
    public double price;
    public boolean availability;

    public MenuItem() {}

    public MenuItem(String item_id, String name, String description, double price, boolean availability) {
        this.item_id = item_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.availability = availability;
    }
}
