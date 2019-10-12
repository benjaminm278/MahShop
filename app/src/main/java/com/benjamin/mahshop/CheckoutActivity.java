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
    public final String CART_EXTRA = "com.benjamin.mahshop.extra.CART";
    public final String SHIPPING_EXTRA = "com.benjamin.mahshop.extra.SHIPPING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Retrieve previous activity (menu) and its passed data
        Bundle menuIntentExtras = getIntent().getExtras();
        cart = menuIntentExtras.getParcelable(CART_EXTRA);
        shippingCost = (Double) menuIntentExtras.get(SHIPPING_EXTRA);

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

            // Fills TextViews
            item_name_table_cell.setText(item.getName()); // Item name
            unit_price_table_cell.setText(getString(R.string.dollar_sign) + df.format(item.getPrice())); // Unit price
            quantity_table_cell.setText(item.getQuantity() + ""); // Quantity
            subtotal_table_cell.setText(getString(R.string.dollar_sign) + df.format(item.getSubTotal())); // Subtotal

            // Sets layout parameters for TextView cells
            setLayoutOfTextView((TextView) findViewById(R.id.item_textview), item_name_table_cell);
            setLayoutOfTextView((TextView) findViewById(R.id.unit_price_textview), unit_price_table_cell);
            setLayoutOfTextView((TextView) findViewById(R.id.quantity_textview), quantity_table_cell);
            setLayoutOfTextView((TextView) findViewById(R.id.cost_textview), subtotal_table_cell);

            // Adds table cells to row
            tr.addView(item_name_table_cell);
            tr.addView(unit_price_table_cell);
            tr.addView(quantity_table_cell);
            tr.addView(subtotal_table_cell);

            // Add table row to bill
            billLayout.addView(tr);
        }
    }

    /**
     * Makes a text view have the say layout parameters as the header
     * @param header
     * @param textViewCell
     */
    private void setLayoutOfTextView(TextView header, TextView textViewCell) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) header.getLayoutParams();
        textViewCell.setLayoutParams(lp);
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
