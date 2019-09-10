package com.benjamin.mahshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.ViewParent;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    private int quantity;
    private TextView quantityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Retrieves quantity text
        quantityText = findViewById(R.id.quantity_text);

        ConstraintLayout cl = findViewById(R.id.constraintLayoutCards);
        int f = cl.getChildCount();
        Log.d("child", Integer.toString(f));
    }

    /**
     * Changes the quantity
     * @param view
     */
    public void changeQuantity(View view) {
        // Check for type of button
        if (view.getId() == R.id.decrement_button) {
            // Decrease quantity only if quantity > 0
            if (quantity > 0) {
                quantity--;
                updateQuantityDisplay();
            }
        }
        else if (view.getId() == R.id.increment_button) {
            // Increase quantity
            quantity++;
            updateQuantityDisplay();
        }
    }

    /**
     * Updates quantity display
     */
    private void updateQuantityDisplay() {
        String newNumber = Integer.toString(quantity);
        quantityText.setText(newNumber);
    }
}
