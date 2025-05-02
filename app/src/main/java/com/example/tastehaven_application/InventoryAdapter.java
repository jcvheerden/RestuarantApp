package com.example.tastehaven_application;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {

    private ArrayList<InventoryItem> inventoryList;

    public InventoryAdapter(ArrayList<InventoryItem> inventoryList) {
        this.inventoryList = inventoryList;
    }

    @Override
    public InventoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory, parent, false);
        return new InventoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InventoryViewHolder holder, int position) {
        InventoryItem item = inventoryList.get(position);
        holder.itemName.setText(item.getItemName());
        holder.quantity.setText(String.valueOf(item.getQuantityInStock()));
        holder.reorderThreshold.setText(String.valueOf(item.getReorderThreshold()));
    }

    @Override
    public int getItemCount() {
        return inventoryList.size();
    }

    public static class InventoryViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, quantity, reorderThreshold;

        public InventoryViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            quantity = itemView.findViewById(R.id.quantityInStock);
            reorderThreshold = itemView.findViewById(R.id.reorderThreshold);
        }
    }
}
