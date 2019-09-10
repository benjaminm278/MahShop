package com.benjamin.mahshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MenuActivity extends AppCompatActivity {
    private int[] quantity;
    private int numberOfCards;
    private TextView quantityText;
    private ConstraintLayout parentOfCards;
    private final String INCREASE = "i";
    private final String DECREASE = "d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Retrieves quantity text
        quantityText = findViewById(R.id.quantity_text1);
        parentOfCards = findViewById(R.id.constraintLayoutCards);
        numberOfCards = parentOfCards.getChildCount();
        quantity = new int[numberOfCards];

        // Hardcode stuff

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
        // Get tag of button
        String s = (String) view.getTag();
        String t = s.replaceAll("\\D+", "");
        String u = s.replaceAll("^[a-zA-Z]", "");
        int number = Integer.parseInt(t);
        Log.d("abc", t);

        //int quantityButton = view.getId();

        // Check for type of button
        if (u.equals(DECREASE)) {
            // Decrease quantity only if quantity > 0
            if (quantity[number - 1] > 0) {
                quantity[number - 1]--;
                updateQuantityDisplay(number - 1, view);
            }
        }
        else if (u.equals(INCREASE)) {
            // Increase quantity
            quantity[number - 1]++;
            updateQuantityDisplay(number - 1, view);
        }
    }

    /**
     * Updates quantity display
     */
    private void updateQuantityDisplay(int currentCardIndex, View view) {
        View v = (View) view.getParent();
        TextView tx = v.findViewWithTag("q1");
        String newNumber = Integer.toString(quantity[currentCardIndex]);
        quantityText.setText(newNumber);
        tx.setText(newNumber);
    }
}
