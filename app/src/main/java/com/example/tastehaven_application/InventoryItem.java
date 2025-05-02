package com.example.tastehaven_application;

public class InventoryItem {
    public String item_id;
    public String item_name;
    public int quantity_in_stock;
    public int reorder_threshold;

    public InventoryItem() {}

    public InventoryItem(String item_id, String item_name, int quantity_in_stock, int reorder_threshold) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.quantity_in_stock = quantity_in_stock;
        this.reorder_threshold = reorder_threshold;
    }
}

