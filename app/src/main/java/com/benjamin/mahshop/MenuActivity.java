package com.benjamin.mahshop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.benjamin.mahshop.model.Item;
import com.benjamin.mahshop.model.shopCart;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.util.LinkedList;

public class MenuActivity extends AppCompatActivity {
    private LinkedList<Item> listOfItems = new LinkedList();
    private Double currentShippingCost = null;
    private final Double EXPRESS_COST = 50.00;
    private final Double REGULAR_COST = 10.00;
    private final Double NO_HURRY_COST = 0.00;
    private final String SHIPPING_EXP_OPT = String.format("Express (+$%.2f)", EXPRESS_COST);
    private final String SHIPPING_REG_OPT = String.format("Regular (+$%.2f)", REGULAR_COST);
    private final String SHIPPING_NONE_OPT = String.format("No hurry (+$%.2f)", NO_HURRY_COST);
    CharSequence[] shippingOptions = {SHIPPING_EXP_OPT, SHIPPING_REG_OPT, SHIPPING_NONE_OPT};
    private shopCart cart = new shopCart();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        Log.d("test", "here");

        try {
            cart.addItem(new Item(getResources().getString(R.string.name1), // Title
                    getResources().getString(R.string.description1), // Description
                    Double.parseDouble(getResources().getString(R.string.price1)), // Price
                    R.drawable.bentoboxsushi, // Image link
                    Integer.parseInt(getResources().getString(R.string.quantity_default_value)),
                    0));
            cart.addItem(new Item(getResources().getString(R.string.name2), // Title
                    getResources().getString(R.string.description2), // Description
                    Double.parseDouble(getResources().getString(R.string.price2)), // Price
                    R.drawable.dragonsushiroll, // Image link
                    Integer.parseInt(getResources().getString(R.string.quantity_default_value)),
                    1));
            cart.addItem(new Item(getResources().getString(R.string.name3), // Title
                    getResources().getString(R.string.description3), // Description
                    Double.parseDouble(getResources().getString(R.string.price3)), // Price
                    R.drawable.spicyandsoursoup, // Image link
                    Integer.parseInt(getResources().getString(R.string.quantity_default_value)),
                    2));

            // Add items to list
            listOfItems.add(new Item(getResources().getString(R.string.name1), // Title
                    getResources().getString(R.string.description1), // Description
                    Double.parseDouble(getResources().getString(R.string.price1)), // Price
                    R.drawable.bentoboxsushi, // Image link
                    Integer.parseInt(getResources().getString(R.string.quantity_default_value)), 0)); // Quantity
            listOfItems.add(new Item(getResources().getString(R.string.name2), // Title
                    getResources().getString(R.string.description2), // Description
                    Double.parseDouble(getResources().getString(R.string.price2)), // Price
                    R.drawable.dragonsushiroll, // Image link
                    Integer.parseInt(getResources().getString(R.string.quantity_default_value)), 1)); // Quantity
            listOfItems.add(new Item(getResources().getString(R.string.name3), // Title
                    getResources().getString(R.string.description3), // Description
                    Double.parseDouble(getResources().getString(R.string.price3)), // Price
                    R.drawable.spicyandsoursoup, // Image link
                    Integer.parseInt(getResources().getString(R.string.quantity_default_value)), 2)); // Quantity
        }
        catch (Exception e) {
            Log.d("test", "here2");
        }

        // Retrieve recycler view
        RecyclerView rc = findViewById(R.id.itemRecyclerView);
        // Create adapter
        ProductAdapter pa = new ProductAdapter(this, cart);
        // Connect adapter to RecyclerView
        rc.setAdapter(pa);
        // Set layout manager
        rc.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Displays alert containing shipping options
     * @param view
     */
    public void showShippingAlert(View view) {
        // Instantiates a new alert dialog builder
        AlertDialog.Builder a = new AlertDialog.Builder(this);

        // Title
        a.setTitle("Shipping Options");

        // Set choices
        a.setSingleChoiceItems(shippingOptions, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                // Iterate through each choice
                switch (item) {
                    case 0:
                        currentShippingCost = EXPRESS_COST;
                        break;
                    case 1:
                        currentShippingCost = REGULAR_COST;
                        break;
                    case 2:
                        currentShippingCost = NO_HURRY_COST;
                        break;
                }
            }
        });

        // Options at bottom of alert
        a.setPositiveButton("Checkout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (currentShippingCost != null) {
                    openCheckoutActivity();
                }
            }
        });
        a.setNegativeButton("Go back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        // Display
        a.show();
    }

    /**
     * Opens the checkout activity
     */
    public void openCheckoutActivity() {
        Intent checkOutActivity = new Intent(this, CheckoutActivity.class);
        checkOutActivity.putExtra("CART", listOfItems);
        startActivity(checkOutActivity);
    }
}
