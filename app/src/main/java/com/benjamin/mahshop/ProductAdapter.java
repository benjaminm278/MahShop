package com.benjamin.mahshop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.benjamin.mahshop.model.Item;

import java.util.LinkedList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private LinkedList<Item> mListOfItems;
    private LayoutInflater mInflater;

    public ProductAdapter(Context ctx, LinkedList<Item> listOfItems) {
        mInflater = LayoutInflater.from(ctx); // Inflates adapter
        this.mListOfItems = listOfItems; // Gets list of items
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Gets id of layout to be used
        int cardLayout = R.layout.card_item;
        // Puts layout into a view
        View myItemView = mInflater.inflate(cardLayout, parent, false);
        // Returns the view holder of the item view
        return new ProductViewHolder(myItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Item x = mListOfItems.get(position);
        Log.d("Pass", "you did it!");
    }

    @Override
    public int getItemCount() {
        return mListOfItems.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        final ProductAdapter mAdapter;
        public ProductViewHolder(@NonNull View itemView, ProductAdapter adapter) {
            super(itemView);
            this.mAdapter = adapter;
        }
    }

}