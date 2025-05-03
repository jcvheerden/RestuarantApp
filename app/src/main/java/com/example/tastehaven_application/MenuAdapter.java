package com.example.tastehaven_application;

import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private final ArrayList<MenuItemModel> items;

    public MenuAdapter(ArrayList<MenuItemModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_card, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItemModel item = items.get(position);
        holder.txtName.setText(item.getName());
        holder.txtDescription.setText(item.getDescription());
        holder.txtPrice.setText("R" + item.getPrice());

        holder.numberPicker.setMinValue(0);
        holder.numberPicker.setMaxValue(20);
        holder.numberPicker.setValue(0);

        holder.numberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> item.setSelectedQuantity(newVal));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDescription, txtPrice;
        NumberPicker numberPicker;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.menuItemName);
            txtDescription = itemView.findViewById(R.id.menuItemDescription);
            txtPrice = itemView.findViewById(R.id.menuItemPrice);
            numberPicker = itemView.findViewById(R.id.numberPickerQuantity);
        }
    }
}
