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
    private shopCart s;
    private final double TPS_RATE = 0.05;
    private final double TPQ_RATE = 0.09975;
    private String dollarSign = "$";

    private double subtotal;
    private double tps;
    private double tpq;
    private double total;

    private DecimalFormat df = new DecimalFormat("##.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        menuIntent = getIntent();
        s = menuIntent.getParcelableExtra("CART");

        writeBill();
        fillAmounts();
    }

    public void writeBill() {
        // Retrieve all necessary variables
        billLayout = findViewById(R.id.billTable);
        menuIntent = getIntent();

        Toast.makeText(this, s.getNumberOfItems() + "***", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < s.getNumberOfItems(); i++) {
            String[] itemData = s.getItemString(i).split("-");

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

    public void fillAmounts() {
        // Get data
        menuIntent = getIntent();

        setSubtotal();
        setTPSamount();
        setTPQamount();
        setGrandTotal();
    }

    private void setSubtotal() {
        // References
        TextView subTotalTxt = findViewById(R.id.subtotal_textView);
        subtotal = Double.parseDouble(df.format(s.getTotal()));
        subTotalTxt.setText(dollarSign + " " + subtotal);
    }

    private void setTPSamount() {
        // Variables
        TextView tpsTxt = findViewById(R.id.tps_textView);
        tps = Double.parseDouble(df.format(subtotal * TPS_RATE));
        tpsTxt.setText(dollarSign + " " + tps);
    }

    private void setTPQamount() {
        // Variables
        TextView tpsTxt = findViewById(R.id.tpq_textView);
        tpq = Double.parseDouble(df.format(subtotal * TPQ_RATE));
        tpsTxt.setText(dollarSign + " " + tpq);
    }

    private void setGrandTotal() {
        // Variables
        TextView tpsTxt = findViewById(R.id.total_textview);
        total = Double.parseDouble(df.format(subtotal + tps + tpq));
        tpsTxt.setText(dollarSign + " " + total);
    }

    public void launchEndActivity(View view) {
        Intent finalActivity = new Intent(this, endOfCheckoutActivity.class);
        startActivity(finalActivity);
    }

    public void launchPreviousActivity(View view) {
        finish();
    }
}
