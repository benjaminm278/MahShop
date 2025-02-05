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
import android.widget.Toast;

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
    public final String CART_EXTRA = "com.benjamin.mahshop.extra.CART";
    public final String SHIPPING_EXTRA = "com.benjamin.mahshop.extra.SHIPPING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            cart = savedInstanceState.getParcelable(CART_EXTRA);
        }

        // Add items to list
        listOfItems.add(new Item(getResources().getString(R.string.name1), // Title
                getResources().getString(R.string.description1), // Description
                Double.parseDouble(getResources().getString(R.string.price1)), // Price
                R.drawable.bentoboxsushi, // Image link
                Integer.parseInt(getResources().getString(R.string.quantity_default_value)))); // Quantity
        listOfItems.add(new Item(getResources().getString(R.string.name2), // Title
                getResources().getString(R.string.description2), // Description
                Double.parseDouble(getResources().getString(R.string.price2)), // Price
                R.drawable.dragonsushiroll, // Image link
                Integer.parseInt(getResources().getString(R.string.quantity_default_value)))); // Quantity
        listOfItems.add(new Item(getResources().getString(R.string.name3), // Title
                getResources().getString(R.string.description3), // Description
                Double.parseDouble(getResources().getString(R.string.price3)), // Price
                R.drawable.spicyandsoursoup, // Image link
                Integer.parseInt(getResources().getString(R.string.quantity_default_value)))); // Quantity
        listOfItems.add(new Item(getResources().getString(R.string.name4), // Title
                getResources().getString(R.string.description4), // Description
                Double.parseDouble(getResources().getString(R.string.price4)), // Price
                R.drawable.egg_rolls, // Image link
                Integer.parseInt(getResources().getString(R.string.quantity_default_value)))); // Quantity
        listOfItems.add(new Item(getResources().getString(R.string.name5), // Title
                getResources().getString(R.string.description5), // Description
                Double.parseDouble(getResources().getString(R.string.price5)), // Price
                R.drawable.fried_rice, // Image link
                Integer.parseInt(getResources().getString(R.string.quantity_default_value)))); // Quantity
        listOfItems.add(new Item(getResources().getString(R.string.name6), // Title
                getResources().getString(R.string.description6), // Description
                Double.parseDouble(getResources().getString(R.string.price6)), // Price
                R.drawable.asian_cuisine_beef, // Image link
                Integer.parseInt(getResources().getString(R.string.quantity_default_value)))); // Quantity
        listOfItems.add(new Item(getResources().getString(R.string.name7), // Title
                getResources().getString(R.string.description7), // Description
                Double.parseDouble(getResources().getString(R.string.price7)), // Price
                R.drawable.soy_noodles, // Image link
                Integer.parseInt(getResources().getString(R.string.quantity_default_value)))); // Quantity
        listOfItems.add(new Item(getResources().getString(R.string.name8), // Title
                getResources().getString(R.string.description8), // Description
                Double.parseDouble(getResources().getString(R.string.price8)), // Price
                R.drawable.spicy_dumplings, // Image link
                Integer.parseInt(getResources().getString(R.string.quantity_default_value)))); // Quantity
        listOfItems.add(new Item(getResources().getString(R.string.name9), // Title
                getResources().getString(R.string.description9), // Description
                Double.parseDouble(getResources().getString(R.string.price9)), // Price
                R.drawable.spicy_noodles_with_pepper, // Image link
                Integer.parseInt(getResources().getString(R.string.quantity_default_value)))); // Quantity
        listOfItems.add(new Item(getResources().getString(R.string.name10), // Title
                getResources().getString(R.string.description10), // Description
                Double.parseDouble(getResources().getString(R.string.price10)), // Price
                R.drawable.stick_rice_thai, // Image link
                Integer.parseInt(getResources().getString(R.string.quantity_default_value)))); // Quantity

        // Retrieve recycler view
        RecyclerView itemsRC = findViewById(R.id.itemRecyclerView);
        // Create adapter
        ProductAdapter pa = new ProductAdapter(this, listOfItems, cart);
        // Connect adapter to RecyclerView
        itemsRC.setAdapter(pa);
        // Set layout manager
        itemsRC.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Place data
        outState.putParcelable(CART_EXTRA, cart);
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
        AlertDialog.Builder alertBox = new AlertDialog.Builder(this);

        // Title
        alertBox.setTitle(getResources().getString(R.string.title_shipping_options));

        // Set choices
        alertBox.setSingleChoiceItems(shippingOptions, -1, new DialogInterface.OnClickListener() {
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
        alertBox.setPositiveButton(getResources().getString(R.string.button_text_checkout), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (currentShippingCost != null) {
                    openCheckoutActivity();
                }
                else if (currentShippingCost == null) {
                    displayShippingErrorMsg();
                }
            }
        });
        alertBox.setNegativeButton(getResources().getString(R.string.button_text_back), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        // Display
        alertBox.show();
    }

    /**
     * Displays an error message if the user did not select a shipping option
     */
    private void displayShippingErrorMsg() {
        Toast.makeText(this, getResources().getString(R.string.toast_need_shipping_option_msg),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Opens the checkout activity
     */
    public void openCheckoutActivity() {
        Intent checkOutActivity = new Intent(this, CheckoutActivity.class);
        checkOutActivity.putExtra(CART_EXTRA, cart);
        checkOutActivity.putExtra(SHIPPING_EXTRA, currentShippingCost);
        startActivity(checkOutActivity);
    }

    /**
     * Displays the cost
     * @param item
     */
    public void displayCosts(MenuItem item) {
        // Instantiates a new alert dialog builder
        AlertDialog.Builder alertBox = new AlertDialog.Builder(this);

        // Title
        alertBox.setTitle(getResources().getString(R.string.title_menu_and_options));

        String itemNames = "";
        // Get names of items
        for (int i = 0; i < listOfItems.size(); i++) {
            Item it = listOfItems.get(i);
            itemNames += it.getName() + " ($" + it.getPrice() + ")\n";
        }

        // Get shipping rates
        String shippingRates = String.format("\n%s\n",
                getResources().getString(R.string.header_shipping_rates));
        shippingRates += String.format("%s\n", SHIPPING_EXP_OPT);
        shippingRates += String.format("%s\n", SHIPPING_REG_OPT);
        shippingRates += String.format("%s\n", SHIPPING_NONE_OPT);

        // Get currency message
        String currencyMessage = "\n" + getResources().getString(R.string.currencyMessage);

        alertBox.setMessage(itemNames + shippingRates + currencyMessage);

        // Options at bottom of alert
        alertBox.setPositiveButton(getResources().getString(R.string.button_text_ok), 
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        // Display
        alertBox.show();
    }

    /**
     * Empties the cart
     * @param item
     */
    public void emptyCart(MenuItem item) {
        cart = new shopCart();
        // Retrieve recycler view
        RecyclerView itemsRC = findViewById(R.id.itemRecyclerView);
        // Create brand new adapter
        ProductAdapter pa = new ProductAdapter(this, listOfItems, cart);
        // Connect the new adapter to RecyclerView
        itemsRC.setAdapter(pa);
        // Set layout manager
        itemsRC.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Displays current items in cart
     * @param item
     */
    public void viewCurrentCart(MenuItem item) {
        // Instantiates a new alert dialog builder
        AlertDialog.Builder alertBox = new AlertDialog.Builder(this);

        // Title
        alertBox.setTitle(getResources().getString(R.string.title_currently_in_cart));

        if (cart.getNumberOfItems() != 0) {
            String itemNames = "";
            // Get names of items
            for (int i = 0; i < cart.getNumberOfItems(); i++) {
                Item it = cart.getItem(i);
                itemNames += it.getQuantity() + " " + it.getName() + " ($" + it.getSubTotal() + ")\n";
            }

            alertBox.setMessage(itemNames);
        }
        else {
            alertBox.setMessage(getResources().getString(R.string.blank_cart_text));
        }

        // Options at bottom of alert
        alertBox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });

        // Display
        alertBox.show();
    }
}
