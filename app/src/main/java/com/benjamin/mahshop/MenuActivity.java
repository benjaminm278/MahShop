package com.benjamin.mahshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
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
        Log.d("String s", s);

        // Check for type of button
        String btnCode = Character.toString(s.charAt(0));
        int number = Integer.parseInt(Character.toString(s.charAt(1)));

        if (btnCode.equals(DECREASE)) {
            // Decrease quantity only if quantity > 0
            if (quantity[number - 1] > 0) {
                quantity[number - 1]--;
                updateQuantityDisplay(quantity[number - 1], number, view);
            }
        }
        else if (btnCode.equals(INCREASE)) {
            // Increase quantity
            quantity[number - 1]++;
            updateQuantityDisplay(quantity[number - 1], number, view);
        }
    }

    /**
     * Updates quantity display
     */
    private void updateQuantityDisplay(int newQuantity, int number, View view) {
        View x = (View) view.getParent();
        TextView txt = (TextView) x.findViewWithTag("q" + number);
        /*
        View v = (View) view.getRootView();
        TextView tx = v.findViewWithTag("q1");*/
        String a = Integer.toString(newQuantity);
        txt.setText(a);
        //tx.setText(newNumber);
    }
}
