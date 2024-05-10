package com.example.lostfound;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Adapter for managing items in a RecyclerView
public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    // Class variables for list of data and application context
    private List<String[]> itemList;
    private Context appContext;

    // Constructor to set the application context and list of data
    public ItemListAdapter(Context context, List<String[]> itemList) {
        this.appContext = context;
        this.itemList = itemList;
    }

    // Method to inflate the view for each item and create ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    // Method to bind data to each ViewHolder based on position
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get data array for current item
        String[] itemData = itemList.get(position);
        // Display data in the TextView of the ViewHolder
        holder.itemText.setText(itemData[6].toUpperCase() + ": " + itemData[1]);
    }

    // Method to get the total number of items
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // ViewHolder class to hold item views
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemText;

        // Constructor for ViewHolder, initializing views and setting listeners
        public ViewHolder(View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.text_view_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(appContext, ItemDetailActivity.class);
                    intent.putExtra("itemDetails", itemList.get(getAdapterPosition()));
                    appContext.startActivity(intent);
                }
            });
        }
    }
}
