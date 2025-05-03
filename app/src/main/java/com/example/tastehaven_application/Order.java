package com.example.tastehaven_application;

public class Order {
    private String order_id;
    private String table_number;
    private String waiter_id;
    private String order_time;
    private String status;

    public Order() {}

    public Order(String order_id, String table_number, String waiter_id, String order_time, String status) {
        this.setOrder_id(order_id);
        this.setTable_number(table_number);
        this.setWaiter_id(waiter_id);
        this.setOrder_time(order_time);
        this.setStatus(status);
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public String getWaiter_id() {
        return waiter_id;
    }

    public void setWaiter_id(String waiter_id) {
        this.waiter_id = waiter_id;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getters and setters...
}
