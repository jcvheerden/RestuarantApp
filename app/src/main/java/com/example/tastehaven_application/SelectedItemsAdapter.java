package com.example.tastehaven_application;

import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SelectedItemsAdapter extends RecyclerView.Adapter<SelectedItemsAdapter.ViewHolder> {

    private final List<MenuItem> selectedItems;

    public SelectedItemsAdapter(List<MenuItem> selectedItems) {
        this.selectedItems = selectedItems;
    }

    @NonNull
    @Override
    public SelectedItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selected_item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem item = selectedItems.get(position);
        holder.name.setText(item.getName());
        holder.price.setText("R" + item.getPrice());
    }

    @Override
    public int getItemCount() {
        return selectedItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.selectedItemName);
            price = view.findViewById(R.id.selectedItemPrice);
        }
    }
}
