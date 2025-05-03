package com.example.tastehaven_application;

import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.*;
import java.util.*;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private final List<Order> orders;

    public OrderAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item_card, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.tableNumber.setText("Table: " + order.getTable_number());
        holder.orderTime.setText("Time: " + order.getOrder_time());
        holder.status.setText("Status: " + order.getStatus());

        holder.status.setOnClickListener(v -> {
            // Update order status
            String newStatus = getNextStatus(order.getStatus());
            order.setStatus(newStatus);
            DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("orders").child(order.getOrder_id());
            orderRef.setValue(order);
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    private String getNextStatus(String currentStatus) {
        switch (currentStatus) {
            case "received":
                return "preparing";
            case "preparing":
                return "ready";
            case "ready":
                return "served";
            default:
                return currentStatus;
        }
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tableNumber, orderTime, status;

        public OrderViewHolder(View itemView) {
            super(itemView);
            tableNumber = itemView.findViewById(R.id.orderTableNumber);
            orderTime = itemView.findViewById(R.id.orderTime);
            status = itemView.findViewById(R.id.orderStatus);
        }
    }
}
