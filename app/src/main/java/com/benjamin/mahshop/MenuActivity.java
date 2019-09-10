package com.benjamin.mahshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
      }

    /**
     * Changes the quantity
     * @param view
     */
    public void changeQuantity(View view) {
        // Get tag of button
        String s = (String) view.getTag();
        Log.d("String s", s);

        // Check for type of button
        String btnCode = Character.toString(s.charAt(0));
        //int number = Integer.parseInt(Character.toString(s.charAt(1)));

        // Get references
        View x = (View) view.getParent();
        TextView quantityTxt = x.findViewById(R.id.quantity_text);
        TextView priceTxt = x.findViewById(R.id.price_text);
        TextView subtotalTxt = x.findViewById(R.id.subtotal_text);

        //TextView txt = (TextView) x.findViewWithTag("q" + number);
        //TextView txt2 = (TextView) x.findViewWithTag("p" + number);
        //TextView txt3 = (TextView) x.findViewWithTag("sb" + number);

        String currentQuantityStr = quantityTxt.getText().toString();
        int quantity = Integer.parseInt(currentQuantityStr);

        if (view.getId() == findViewById(R.id.decrement_button).getId()) {
            // Decrease quantity only if quantity > 0
            if (quantity > 0) {
                quantity--;
                updateQuantityDisplay(quantity, quantityTxt);
                updatePriceDisplay(quantity, priceTxt, subtotalTxt);
            }
        }
        else if (view.getId() == findViewById(R.id.increment_button).getId()) {
            // Increase quantity
            quantity++;
            updateQuantityDisplay(quantity, quantityTxt);
            updatePriceDisplay(quantity, priceTxt, subtotalTxt);
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
     * @param txt
     * @param subtotalTxt
     */
    private void updatePriceDisplay(int quantity, TextView txt, TextView subtotalTxt) {
        String k = txt.getText().toString(); // Retrieve price
        String priceStr = k.substring(1); // Break it up
        double price = Double.parseDouble(priceStr); // Converts to double
        double subtotal = price * quantity; // Compute
        subtotalTxt.setText("$" + subtotal); // Display
    }
}
