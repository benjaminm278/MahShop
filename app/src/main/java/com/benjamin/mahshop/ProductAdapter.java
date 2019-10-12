package com.benjamin.mahshop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.benjamin.mahshop.model.Item;
import com.benjamin.mahshop.model.shopCart;

import java.util.LinkedList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private LinkedList<Item> mListOfItems;
    private LayoutInflater mInflater;
    private shopCart mCart;

    /**
     * Creates an adapter by setting the layout inflater and list of items
     * @param ctx
     */
    public ProductAdapter(Context ctx, LinkedList<Item> listOfItems, shopCart cart) {
        mInflater = LayoutInflater.from(ctx); // Inflates adapter
        this.mListOfItems = listOfItems; // Gets list of items
        this.mCart = cart;
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
     * Adds stuff to card
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Item x = mListOfItems.get(position);
        //Item x = cart.getItemByMenuIndex(position);
        holder.itemImg.setBackgroundResource(x.getImageId());
        holder.nameTxt.setText(x.getName());
        holder.priceTxt.setText(String.format("$%s", Double.toString(x.getPrice())));
        holder.descriptionTxt.setText(x.getDescription());

        int quantity = 0;
        Double subTotal = 0.00;
        if (mCart.contains(x.getName())) {
            quantity = mCart.getItem(mCart.indexOf(x.getName())).getQuantity();
            subTotal = mCart.getItem(mCart.indexOf(x.getName())).getSubTotal();
        }

        holder.quantityTxt.setText(quantity + ""); // Check
        holder.subTotalTxt.setText(String.format("$%.2f", subTotal)); // Check
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
    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final CardView card;
        public final TextView nameTxt;
        public final TextView descriptionTxt;
        public final TextView priceTxt;
        public final ImageView itemImg;
        public final TextView quantityTxt;
        public final TextView subTotalTxt;
        public final Button incrementBtn;
        public final Button decrementBtn;
        final ProductAdapter mAdapter;
        /**
         * Creates a view holder for an item
         * @param itemView
         * @param adapter
         */
        public ProductViewHolder(@NonNull View itemView, ProductAdapter adapter) {
            super(itemView);
            // Define views
            card = itemView.findViewById(R.id.ItemCardView);
            nameTxt = card.findViewById(R.id.name_text);
            descriptionTxt = card.findViewById(R.id.description_text);
            priceTxt = card.findViewById(R.id.price_text);
            itemImg = card.findViewById(R.id.imageView);
            quantityTxt = card.findViewById(R.id.quantity_text);
            subTotalTxt = card.findViewById(R.id.subtotal_text);
            this.mAdapter = adapter;

            incrementBtn = card.findViewById(R.id.increment_button);
            decrementBtn = card.findViewById(R.id.decrement_button);

            // Sets on click listeners
            incrementBtn.setOnClickListener(this);
            decrementBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Variable declaration
            int btnId = v.getId(); // id of this button
            int positionOfCard = getLayoutPosition(); // position of this card
            Item item = mListOfItems.get(positionOfCard); // gets item from the list of items

            // Checks which button was pressed
            if (btnId == incrementBtn.getId()) {
                incrementQuantity(item);
                changeSubtotal(item);
                // Log message as requested in Part V of assignment 2
                Log.d("ItemStatus", "Item count increased: " + item.getName() + " $" + item.getPrice());
                Log.d("ItemStatus", "" + item.getQuantity());
            }
            else if (btnId == decrementBtn.getId()) {
                decrementQuantity(item);
                changeSubtotal(item);
                // Log message as requested in Part V of assignment 2
                Log.d("ItemStatus", "Item count decreased: " + item.getName() + " $" + item.getPrice());
                Log.d("ItemStatus", "" + item.getQuantity());
            }
        }

        public void incrementQuantity(Item item) {
            // Add item to cart
            mCart.addItem(item);

            // Update quantity display
            quantityTxt.setText(item.getQuantity() + "");
        }

        public void changeSubtotal(Item y) {
            // Update subtotal display
            subTotalTxt.setText("$" + String.format("%.2f", y.getSubTotal()));
        }

        public void decrementQuantity(Item item) {
            mCart.decreaseItemCount(item);
            quantityTxt.setText(item.getQuantity() + "");
        }
    }
}