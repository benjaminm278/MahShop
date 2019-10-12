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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;

public class MenuActivity extends AppCompatActivity {
    private LinkedList<Item> listOfItems = new LinkedList();
    private Double currentShippingCost;
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

        if (savedInstanceState != null) {
            cart = savedInstanceState.getParcelable("OUTSTATE_CART");
        }

        try {
            // Add items to list
            listOfItems.add(new Item(getResources().getString(R.string.name1), // Title
                    getResources().getString(R.string.description1), // Description
                    Double.parseDouble(getResources().getString(R.string.price1)), // Price
                    R.drawable.bentoboxsushi, // Image link
                    Integer.parseInt(getResources().getString(R.string.quantity_default_value)),
                    listOfItems.size())); // Quantity
            listOfItems.add(new Item(getResources().getString(R.string.name2), // Title
                    getResources().getString(R.string.description2), // Description
                    Double.parseDouble(getResources().getString(R.string.price2)), // Price
                    R.drawable.dragonsushiroll, // Image link
                    Integer.parseInt(getResources().getString(R.string.quantity_default_value)),
                    listOfItems.size())); // Quantity
            listOfItems.add(new Item(getResources().getString(R.string.name3), // Title
                    getResources().getString(R.string.description3), // Description
                    Double.parseDouble(getResources().getString(R.string.price3)), // Price
                    R.drawable.spicyandsoursoup, // Image link
                    Integer.parseInt(getResources().getString(R.string.quantity_default_value)),
                    listOfItems.size())); // Quantity
        }
        catch (Exception e) {
            Log.d("test", "here2");
        }

        // Retrieve recycler view
        RecyclerView rc = findViewById(R.id.itemRecyclerView);
        // Create adapter
        ProductAdapter pa = new ProductAdapter(this, listOfItems, cart);
        // Connect adapter to RecyclerView
        rc.setAdapter(pa);
        // Set layout manager
        rc.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Place data
        outState.putParcelable("OUTSTATE_CART", cart);
    }

    /**
     * Initializes app bar stuff with menu configurations
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.food_items_menu, menu);
        return true;
    }

    /**
     * Processes the item clicked from one of the options in the app bar
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
        checkOutActivity.putExtra("CART", cart);
        checkOutActivity.putExtra("SHIPPING", currentShippingCost);
        startActivity(checkOutActivity);
    }

    /**
     * Displays the cost
     * @param item
     */
    public void displayCosts(MenuItem item) {
        // Instantiates a new alert dialog builder
        AlertDialog.Builder a = new AlertDialog.Builder(this);

        // Title
        a.setTitle("Menu & options");

        String itemNames = "";
        // Get names of items
        for (int i = 0; i < listOfItems.size(); i++) {
            Item it = listOfItems.get(i);
            itemNames += it.getName() + " ($" + it.getPrice() + ")\n";
        }

        // Get shipping rates
        String shippingRates = "\nShipping rates:\n";
        shippingRates += String.format("%s\n", SHIPPING_EXP_OPT);
        shippingRates += String.format("%s\n", SHIPPING_REG_OPT);
        shippingRates += String.format("%s\n", SHIPPING_NONE_OPT);

        // Get currency message
        String currencyMessage = "\n" + getResources().getString(R.string.currencyMessage);

        a.setMessage(itemNames + shippingRates + currencyMessage);

        // Options at bottom of alert
        a.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        // Display
        a.show();
    }

    /**
     * Empties the cart
     * @param item
     */
    public void emptyCart(MenuItem item) {
        cart = new shopCart();
        // Retrieve recycler view
        RecyclerView rc = findViewById(R.id.itemRecyclerView);
        // Create adapter
        ProductAdapter pa = new ProductAdapter(this, listOfItems, cart);
        // Connect adapter to RecyclerView
        rc.setAdapter(pa);
        // Set layout manager
        rc.setLayoutManager(new LinearLayoutManager(this));
    }
}
