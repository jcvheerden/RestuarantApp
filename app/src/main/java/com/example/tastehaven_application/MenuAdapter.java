package com.example.tastehaven_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private Context context;
    private List<MenuItem> menuItems;
    private LayoutInflater inflater;

    // Constructor with required parameters
    public MenuAdapter(Context context, List<MenuItem> menuItems) {
        this.context = context;
        this.menuItems = menuItems;
        this.inflater = LayoutInflater.from(context); // Initialize LayoutInflater
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.menu_item_layout, parent, false); // Inflate your item layout
        return new MenuViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);
        holder.nameTextView.setText(menuItem.getName());
        holder.descriptionTextView.setText(menuItem.getDescription());
        holder.priceTextView.setText("$" + menuItem.getPrice());

        // Optional: Handle item clicks or other actions
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Item clicked: " + menuItem.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    // ViewHolder class to hold the views for each menu item
    public static class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView descriptionTextView;
        TextView priceTextView;

        public MenuViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.menu_item_name); // Reference to name view in your item layout
            descriptionTextView = itemView.findViewById(R.id.menu_item_description); // Reference to description view
            priceTextView = itemView.findViewById(R.id.menu_item_price); // Reference to price view
        }
    }
}
