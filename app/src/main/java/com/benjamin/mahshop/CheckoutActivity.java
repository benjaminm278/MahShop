package com.benjamin.mahshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class CheckoutActivity extends AppCompatActivity {
    private TableLayout billLayout;
    private Intent menuIntent;
    private shopCart cart;
    private final double TPS_RATE = 0.05;
    private final double TPQ_RATE = 0.09975;
    private String dollarSign = "$";

    private double subtotal;
    private double tps;
    private double tpq;
    private double total;

    private DecimalFormat df = new DecimalFormat("#0.00");

    public final String EXTRA_CART = "com.benjamin.mahshop.extra.CART";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        menuIntent = getIntent();
        cart = menuIntent.getParcelableExtra(EXTRA_CART);

        writeBill();
        fillAmounts();
    }

    /**
     * Creates a display for the bill
     */
    public void writeBill() {
        // Retrieve all necessary variables
        billLayout = findViewById(R.id.billTable);
        menuIntent = getIntent();

        for (int i = 0; i < cart.getNumberOfItems(); i++) {
            String[] itemData = cart.getItemString(i).split("-");

            TableRow tr = new TableRow(this);
            TextView item_name_table_cell = new TextView(this);
            TextView unit_price_table_cell = new TextView(this);
            TextView quantity_table_cell = new TextView(this);
            TextView subtotal_table_cell = new TextView(this);

            item_name_table_cell.setText(itemData[0]);
            unit_price_table_cell.setText(itemData[1]);
            quantity_table_cell.setText(itemData[2]);
            subtotal_table_cell.setText(itemData[3]);

            tr.addView(item_name_table_cell);
            tr.addView(unit_price_table_cell);
            tr.addView(quantity_table_cell);
            tr.addView(subtotal_table_cell);

            billLayout.addView(tr);
        }
    }

    /**
     * Fills in the fields displaying amounts
     */
    public void fillAmounts() {
        // Get data
        menuIntent = getIntent();

        setSubtotal();
        setTPSamount();
        setTPQamount();
        setGrandTotal();
    }

    /**
     * Displays subtotal
     */
    private void setSubtotal() {
        // References
        TextView subTotalTxt = findViewById(R.id.subtotal_textView);
        subtotal = cart.getTotal();
        setAmountDisplay(subtotal, subTotalTxt);
    }

    /**
     * Displays TPS
     */
    private void setTPSamount() {
        // Variables
        TextView tpsTxt = findViewById(R.id.tps_textView);
        tps = subtotal * TPS_RATE;
        setAmountDisplay(tps, tpsTxt);
    }

    /**
     * Displays TPQ
     */
    private void setTPQamount() {
        // Variables
        TextView tpqTxt = findViewById(R.id.tpq_textView);
        tpq = subtotal * TPQ_RATE;
        setAmountDisplay(tpq, tpqTxt);
    }

    /**
     * Displays total price
     */
    private void setGrandTotal() {
        // Variables
        TextView totalTxt = findViewById(R.id.total_textview);
        total = subtotal + tps + tpq;
        setAmountDisplay(total, totalTxt);
    }

    private void setAmountDisplay(double amount, TextView amountText) {
        String formattedAmount = String.format("%.2f", amount);
        amountText.setText(dollarSign + " " + formattedAmount);
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
