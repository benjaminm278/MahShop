package com.benjamin.mahshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MenuActivity extends AppCompatActivity {
    private shopCart cart;
    public static final String EXTRA_CART = "extraCart";

    private DecimalFormat df = new DecimalFormat("##.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Creates a new shopping cart
        cart = new shopCart();
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
            // Increase quantity
            /*updateDisplays(quantity, quantityTxt,
                    price, priceTxt,
                    subtotalTxt);
            */
            cart.addItem(name, price, currentQuantity);
            updateQuantityDisplay(currentQuantity + 1, quantityTxt);
            updatePriceDisplay(currentQuantity + 1, priceTxt, subtotalTxt);

            //Toast.makeText(this, cart.getItemString(0), Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, nameTxt.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    // To be implemented after functionality completion
    public void updateDisplays() {}

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
     * @param txt
     * @param subtotalTxt
     */
    private void updatePriceDisplay(int quantity, TextView txt, TextView subtotalTxt) {
        String k = txt.getText().toString(); // Retrieve price
        String priceStr = k.substring(1); // Break it up
        double price = Double.parseDouble(priceStr); // Converts to double
        double subtotal = price * quantity; // Compute
        String st = df.format(subtotal);
        subtotalTxt.setText("$" + st); // Display
    }

    /**
     * Opens a new instance of checkout
     * @param view
     */
    public void startCheckoutActivity(View view) {
        // testClass test = new testClass("Leo");
        // Open new checkout activity
        Intent checkoutActivity = new Intent(this, CheckoutActivity.class);
        checkoutActivity.putExtra("CART", cart);
        // checkoutActivity.putExtra("TEST", test);
        startActivity(checkoutActivity);
    }
}
