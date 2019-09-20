package com.benjamin.mahshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.DecimalFormat;

public class MenuActivity extends AppCompatActivity {
    private shopCart cart;
    public static final String EXTRA_CART = "com.benjamin.mahshop.extra.CART";
    public static final String PARCEL_CART = "com.benjamin.mahshop.parcel.CART";

    /**
     * Places data in activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Creates a new shopping cart
        cart = new shopCart();

        // Retrieve data if device has changed orientation
        if (savedInstanceState != null) {
            cart = savedInstanceState.getParcelable(PARCEL_CART);
            reAddItems();
        }
    }

    /**
     * Retrieves data from current activity to preserve
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Place data
        outState.putParcelable(PARCEL_CART, cart);
    }

    /**
     * Readds items and their values into their cardviews
     */
    public void reAddItems() {
        // Variable declaration
        ConstraintLayout clc = findViewById(R.id.constraintLayoutCards);
        ViewGroup groupOfCards = clc;
        int numberOfChildren = groupOfCards.getChildCount(); // Get number of cardviews

        // Iterate through cart
        for (int i = 0; i < numberOfChildren; i++) {
            // Retrieve name
            TextView nameTxt = groupOfCards.getChildAt(i).findViewById(R.id.name_text);
            String cardName = nameTxt.getText().toString();

            // Check if the cart has a particular item
            if (cart.contains(cardName)) {
                // Retrieves string of a particular item
                String[] itemData = cart.getItemString(cart.indexOf(cardName)).split("-");

                // Sets quantity
                TextView quantityTxt = groupOfCards.getChildAt(i).findViewById(R.id.quantity_text);
                quantityTxt.setText(itemData[2]);

                // Sets subtotal
                TextView subtotalTxt = groupOfCards.getChildAt(i).findViewById(R.id.subtotal_text);
                subtotalTxt.setText("$" + itemData[3]);
            }
        }
    }

    /**
     * Changes the quantity
     * @param view
     */
    public void changeQuantity(View view) {
        // Get references
        View x = (View) view.getParent();
        TextView quantityTxt = x.findViewById(R.id.quantity_text);
        TextView priceTxt = x.findViewById(R.id.price_text);
        TextView subtotalTxt = x.findViewById(R.id.subtotal_text);
        TextView nameTxt = x.findViewById(R.id.name_text);
        int decrement_button_id = findViewById(R.id.decrement_button).getId();
        int increment_button_id = findViewById(R.id.increment_button).getId();

        // Retrieve important values
        String name = nameTxt.getText().toString();

        String currentQuantityStr = quantityTxt.getText().toString();
        int currentQuantity = Integer.parseInt(currentQuantityStr);

        String priceStr = priceTxt.getText().toString().substring(1);
        double price = Double.parseDouble(priceStr);

        if (view.getId() == decrement_button_id) {
            // Decrease quantity only if quantity > 0
            if (currentQuantity > 0) {
                cart.decreaseItemCount(name, price, currentQuantity);
                updateQuantityDisplay(currentQuantity - 1, quantityTxt);
                updatePriceDisplay(currentQuantity - 1, priceTxt, subtotalTxt);
            }
        }
        else if (view.getId() == increment_button_id) {
            cart.addItem(name, price, currentQuantity);
            updateQuantityDisplay(currentQuantity + 1, quantityTxt);
            updatePriceDisplay(currentQuantity + 1, priceTxt, subtotalTxt);
        }
    }

    /**
     * Updates quantity display
     */
    private void updateQuantityDisplay(int quantity, TextView txt) {
        String a = Integer.toString(quantity);
        txt.setText(a);
    }

    /**
     * Computes new subtotal
     * @param quantity
     * @param priceTxt
     * @param subtotalTxt
     */
    private void updatePriceDisplay(int quantity, TextView priceTxt, TextView subtotalTxt) {
        // Retrieve price
        String k = priceTxt.getText().toString();
        String priceStr = k.substring(1); // Separate $ and price number
        double price = Double.parseDouble(priceStr);

        // Compute, format and display
        double subtotal = price * quantity;
        String formattedSubtotal = String.format("%.2f", subtotal);
        subtotalTxt.setText("$" + formattedSubtotal);
    }

    /**
     * Opens a new instance of checkout
     * @param view
     */
    public void startCheckoutActivity(View view) {
        // Open new checkout activity
        Intent checkoutActivity = new Intent(this, CheckoutActivity.class);
        checkoutActivity.putExtra(EXTRA_CART, cart);
        startActivity(checkoutActivity);
    }
}
