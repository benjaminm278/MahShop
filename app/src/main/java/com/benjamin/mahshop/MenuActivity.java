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
    private int[] quantity;
    private int numberOfCards;
    private TextView quantityText;
    private ConstraintLayout parentOfCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Retrieves quantity text
        quantityText = findViewById(R.id.quantity_text);
        parentOfCards = findViewById(R.id.constraintLayoutCards);
        numberOfCards = parentOfCards.getChildCount();
        quantity = new int[numberOfCards];
        /*
        ConstraintLayout cl = findViewById(R.id.constraintLayoutCards);
        int f = cl.getChildCount();
        Log.d("child", Integer.toString(f));
    */}

    /**
     * Changes the quantity
     * @param view
     */
    public void changeQuantity(View view) {
        int currentCardIndex = parentOfCards.indexOfChild(view); // Retrieve index from parent
        Log.d("IndexCard", Integer.toString(currentCardIndex));
        // Get type of button pushed
        /*int quantityButton = view.getId();

        // Check for type of button
        if (quantityButton == R.id.decrement_button) {
            // Decrease quantity only if quantity > 0
            if (quantity[currentCardIndex] > 0) {
                quantity[currentCardIndex]--;
                updateQuantityDisplay(currentCardIndex);
            }
        }
        else if (quantityButton == R.id.increment_button) {
            // Increase quantity
            quantity[currentCardIndex]++;
            updateQuantityDisplay(currentCardIndex);
        }*/
    }

    /**
     * Updates quantity display
     */
    private void updateQuantityDisplay(int currentCardIndex) {
        String newNumber = Integer.toString(quantity[currentCardIndex]);
        quantityText.setText(newNumber);
    }
}
