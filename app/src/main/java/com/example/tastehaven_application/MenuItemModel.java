package com.example.tastehaven_application;

public class MenuItemModel {
    private String item_id, name, description;
    private double price;
    private boolean availability;
    private int selectedQuantity;

    public MenuItemModel() {}

    public String getItem_id() { return item_id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public boolean isAvailability() { return availability; }
    public int getSelectedQuantity() { return selectedQuantity; }

    public void setSelectedQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }
}
