package com.benjamin.mahshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.benjamin.mahshop.model.Item;
import com.benjamin.mahshop.model.shopCart;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class CheckoutActivity extends AppCompatActivity {
    private TableLayout billLayout;
    private final double TPS_RATE = 0.05;
    private final double TPQ_RATE = 0.09975;
    private double subtotal;
    private double tps;
    private double tpq;
    private double total;
    private Double shippingCost;
    private shopCart cart;
    private DecimalFormat df = new DecimalFormat("#,###.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Retrieve previous activity (menu) and its passed data
        Bundle menuIntentExtras = getIntent().getExtras();
        cart = menuIntentExtras.getParcelable("CART");
        shippingCost = (Double) menuIntentExtras.get("SHIPPING");

        //cart = menuIntent.getParcelableExtra("CART");

        // Fill bill contents
        writeBill();
        fillAmounts();
    }

    /**
     * Creates a display for the bill
     */
    public void writeBill() {
        // Retrieve all necessary variables
        billLayout = findViewById(R.id.billTable);
        //menuIntent = getIntent();

        // Iterates for each row
        for (int itemIndex = 0; itemIndex < cart.getNumberOfItems(); itemIndex++) {
            Item item = cart.getItem(itemIndex);

            // Creates new table row
            TableRow tr = new TableRow(this);

            // Creates new textviews for each field
            TextView item_name_table_cell = new TextView(this);
            TextView unit_price_table_cell = new TextView(this);
            TextView quantity_table_cell = new TextView(this);
            TextView subtotal_table_cell = new TextView(this);

            // Fills textviews
            item_name_table_cell.setText(item.getName()); // Item name
            unit_price_table_cell.setText(getString(R.string.dollar_sign) + df.format(item.getPrice())); // Unit price
            quantity_table_cell.setText(item.getQuantity() + ""); // Quantity
            subtotal_table_cell.setText(getString(R.string.dollar_sign) + df.format(item.getSubTotal())); // Subtotal

            TextView t = (TextView) findViewById(R.id.item_textview);
            LinearLayout.LayoutParams x = (LinearLayout.LayoutParams) t.getLayoutParams();
            item_name_table_cell.setLayoutParams(x);

            TextView t2 = (TextView) findViewById(R.id.unit_price_textview);
            LinearLayout.LayoutParams x2 = (LinearLayout.LayoutParams) t2.getLayoutParams();
            unit_price_table_cell.setLayoutParams(x2);

            TextView t3 = (TextView) findViewById(R.id.quantity_textview);
            LinearLayout.LayoutParams x3 = (LinearLayout.LayoutParams) t3.getLayoutParams();
            quantity_table_cell.setLayoutParams(x3);

            TextView t4 = (TextView) findViewById(R.id.cost_textview);
            LinearLayout.LayoutParams x4 = (LinearLayout.LayoutParams) t4.getLayoutParams();
            subtotal_table_cell.setLayoutParams(x4);

            // Adds table cells to row
            tr.addView(item_name_table_cell);
            tr.addView(unit_price_table_cell);
            tr.addView(quantity_table_cell);
            tr.addView(subtotal_table_cell);
            tr.setWeightSum(1.0f);

            // Add table row to bill
            billLayout.addView(tr);
        }
    }

    /**
     * Fills in the fields displaying amounts
     */
    public void fillAmounts() {
        // Computes amounts
        setSubtotal();
        setTPSamount();
        setTPQamount();
        setShippingCost();
        setGrandTotal();
    }

    /**
     * Displays subtotal
     */
    private void setSubtotal() {
        TextView subTotalTxt = findViewById(R.id.subtotal_textView);
        subtotal = cart.getTotal();
        setAmountDisplay(subtotal, subTotalTxt);
    }

    /**
     * Displays TPS
     */
    private void setTPSamount() {
        TextView tpsTxt = findViewById(R.id.tps_textView);
        tps = subtotal * TPS_RATE;
        setAmountDisplay(tps, tpsTxt);
    }

    /**
     * Displays TPQ
     */
    private void setTPQamount() {
        TextView tpqTxt = findViewById(R.id.tpq_textView);
        tpq = subtotal * TPQ_RATE;
        setAmountDisplay(tpq, tpqTxt);
    }

    private void setShippingCost() {
        TextView shipTxt = findViewById(R.id.shipping_textView);
        setAmountDisplay(shippingCost, shipTxt);
    }

    /**
     * Displays total price
     */
    private void setGrandTotal() {
        TextView totalTxt = findViewById(R.id.total_textview);
        total = subtotal + tps + tpq + shippingCost;
        setAmountDisplay(total, totalTxt);
    }

    /**
     * Helper method: Formats and displays amount
     * @param amount
     * @param amountText
     */
    private void setAmountDisplay(double amount, TextView amountText) {
        String formattedAmount = String.format("%.2f", amount);
        amountText.setText(getString(R.string.dollar_sign) + formattedAmount);
    }

    /**
     * Launches the end activity
     * @param view
     */
    public void launchEndActivity(View view) {
        Intent finalActivity = new Intent(this, endOfCheckoutActivity.class);
        startActivity(finalActivity);
    }

    /**
     * Closes this activity
     * @param view
     */
    public void launchPreviousActivity(View view) {
        finish();
    }
}
