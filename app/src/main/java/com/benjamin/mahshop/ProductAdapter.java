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
    private shopCart cart;

    /**
     * Creates an adapter by setting the layout inflater and list of items
     * @param ctx
     */
    public ProductAdapter(Context ctx, LinkedList<Item> listOfItems, shopCart c) {
        mInflater = LayoutInflater.from(ctx); // Inflates adapter
        this.mListOfItems = listOfItems; // Gets list of items
        this.cart = c;
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
        if (cart.contains(x.getName())) {
            quantity = cart.getItemWithMenuIndex(position).getQuantity();
            subTotal = x.getSubTotal();
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
        public final CardView c;
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
         * Creates
         * @param itemView
         * @param adapter
         */
        public ProductViewHolder(@NonNull View itemView, ProductAdapter adapter) {
            super(itemView);
            // Define views
            c = itemView.findViewById(R.id.ItemCardView);
            nameTxt = c.findViewById(R.id.name_text);
            descriptionTxt = c.findViewById(R.id.description_text);
            priceTxt = c.findViewById(R.id.price_text);
            itemImg = c.findViewById(R.id.imageView);
            quantityTxt = c.findViewById(R.id.quantity_text);
            subTotalTxt = c.findViewById(R.id.subtotal_text);
            this.mAdapter = adapter;

            incrementBtn = c.findViewById(R.id.increment_button);
            decrementBtn = c.findViewById(R.id.decrement_button);

            // Sets on click listeners
            incrementBtn.setOnClickListener(this);
            decrementBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("Abc123", v.getId() + " Success!!!");

            int btnId = v.getId();
            int y = getLayoutPosition();
            Item a = mListOfItems.get(y);
            //Item a = cart.getItemWithMenuIndex(y);
            // Checks which button was pressed
            if (btnId == incrementBtn.getId()) {
                incrementQuantity(a);
                changeSubtotal(a);
            }
            else if (btnId == decrementBtn.getId()) {
                decrementQuantity(a);
                changeSubtotal(a);
            }
        }

        public void incrementQuantity(Item item) {
            // Add item to cart
            cart.addItem(item);

            // Update quantity display
            quantityTxt.setText(item.getQuantity() + "");
        }

        public void changeSubtotal(Item y) {
            // Update subtotal display
            subTotalTxt.setText("$" + String.format("%.2f", y.getSubTotal()));
        }

        public void decrementQuantity(Item item) {
            cart.decreaseItemCount(item);
            //item.decreaseQuantityByOne();
            quantityTxt.setText(item.getQuantity() + "");
        }
    }
}