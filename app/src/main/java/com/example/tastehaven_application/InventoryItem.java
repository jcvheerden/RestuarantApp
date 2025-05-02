package com.example.tastehaven_application;

public class InventoryItem {
    private String id;
    private String itemName;
    private int quantityInStock;
    private int reorderThreshold;
    private String timestamp;

    // Default constructor
    public InventoryItem() {
        // Required for Firebase
    }

    // Constructor with 5 parameters (id, name, quantity, threshold, timestamp)
    public InventoryItem(String id, String itemName, int quantityInStock, int reorderThreshold, String timestamp) {
        this.id = id;
        this.itemName = itemName;
        this.quantityInStock = quantityInStock;
        this.reorderThreshold = reorderThreshold;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public int getReorderThreshold() {
        return reorderThreshold;
    }

    public void setReorderThreshold(int reorderThreshold) {
        this.reorderThreshold = reorderThreshold;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
