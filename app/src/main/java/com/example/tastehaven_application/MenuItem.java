package com.example.tastehaven_application;

public class MenuItem {
    private String item_id;
    private String name;
    private String description;
    private double price;
    private boolean availability;

    public MenuItem() {}

    public MenuItem(String item_id, String name, String description, double price, boolean availability) {
        this.setItem_id(item_id);
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
        this.setAvailability(availability);
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
