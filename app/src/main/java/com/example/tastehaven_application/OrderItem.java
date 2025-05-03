package com.example.tastehaven_application;

public class OrderItem {
    private String order_item_id;
    private String order_id;
    private String item_id;
    private int quantity;
    private String notes;

    public OrderItem() {}

    public OrderItem(String order_item_id, String order_id, String item_id, int quantity, String notes) {
        this.setOrder_item_id(order_item_id);
        this.setOrder_id(order_id);
        this.setItem_id(item_id);
        this.setQuantity(quantity);
        this.setNotes(notes);
    }

    public String getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(String order_item_id) {
        this.order_item_id = order_item_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getItemid() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Getters and setters...
}
