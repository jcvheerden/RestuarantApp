package com.example.tastehaven_application;

import java.util.List;

public class Order {
    private String tableNumber;
    private String waiterId;
    private String status;
    private long orderTime;
    private List<OrderItem> orderItems;

    // Default constructor for Firebase
    public Order() {
    }

    public Order(String tableNumber, String waiterId, String status, long orderTime, List<OrderItem> orderItems) {
        this.tableNumber = tableNumber;
        this.waiterId = waiterId;
        this.status = status;
        this.orderTime = orderTime;
        this.orderItems = orderItems;
    }

    // Getters and Setters
    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
