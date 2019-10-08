package com.benjamin.mahshop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.benjamin.mahshop.model.Item;

import java.util.LinkedList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private LinkedList<Item> mListOfItems;
    private LayoutInflater mInflater;

    /**
     * Creates an adapter by setting the layout inflater and list of items
     * @param ctx
     * @param listOfItems
     */
    public ProductAdapter(Context ctx, LinkedList<Item> listOfItems) {
        mInflater = LayoutInflater.from(ctx); // Inflates adapter
        this.mListOfItems = listOfItems; // Gets list of items
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
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

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Item x = mListOfItems.get(position);
        Log.d("Pass", "you did it!");
        holder.nameTxt.setText(x.getName());
        holder.descriptionTxt.setText(x.getDescription());
        holder.priceTxt.setText(Double.toString(x.getPrice()));
        holder.quantityTxt.setText(x.getQuantity() + "");
    }

    /**
     * Returns the size of the list
     * @return
     */
    @Override
    public int getItemCount() {
        return mListOfItems.size();
    }

    /*********************
     * View holder class *
     *********************/
    class ProductViewHolder extends RecyclerView.ViewHolder {
        public final CardView c;
        public final TextView nameTxt;
        public final TextView descriptionTxt;
        public final TextView priceTxt;
        public final ImageView itemImg;
        public final TextView quantityTxt;
        final ProductAdapter mAdapter;
        /**
         * Creates
         * @param itemView
         * @param adapter
         */
        public ProductViewHolder(@NonNull View itemView, ProductAdapter adapter) {
            super(itemView);
            c = itemView.findViewById(R.id.ItemCardView);
            nameTxt = c.findViewById(R.id.name_text);
            descriptionTxt = c.findViewById(R.id.description_text);
            priceTxt = c.findViewById(R.id.description_text);
            itemImg = c.findViewById(R.id.imageView);
            quantityTxt = c.findViewById(R.id.quantity_text);
            this.mAdapter = adapter;
        }
    }
}