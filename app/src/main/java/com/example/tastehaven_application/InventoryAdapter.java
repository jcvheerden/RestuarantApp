package com.example.tastehaven_application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {

    private List<InventoryItem> itemList;
    private DatabaseReference inventoryRef;

    public InventoryAdapter(List<InventoryItem> itemList) {
        this.itemList = itemList;
        this.inventoryRef = FirebaseDatabase.getInstance().getReference("inventory");
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_item_layout, parent, false);
        return new InventoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        InventoryItem item = itemList.get(position);
        holder.name.setText(item.item_name);
        holder.quantity.setText("Qty: " + item.quantity_in_stock);
        holder.threshold.setText("Threshold: " + item.reorder_threshold);

        // Delete logic
        holder.deleteBtn.setOnClickListener(v -> {
            inventoryRef.child(item.item_id).removeValue();
            itemList.remove(position);
            notifyItemRemoved(position);
        });

        // Edit logic (increase quantity for simplicity)
        holder.editBtn.setOnClickListener(v -> {
            int updatedQty = item.quantity_in_stock + 1;
            inventoryRef.child(item.item_id).child("quantity_in_stock").setValue(updatedQty);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class InventoryViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantity, threshold;
        Button editBtn, deleteBtn;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemNameText);
            quantity = itemView.findViewById(R.id.itemQtyText);
            threshold = itemView.findViewById(R.id.itemThresholdText);
            editBtn = itemView.findViewById(R.id.editButton);
            deleteBtn = itemView.findViewById(R.id.deleteButton);
        }
    }
}

