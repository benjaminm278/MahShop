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
    private static final String EXTRA_CART = "com.benjamin.mahshop.extra.CART";
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

        for (int i = 0; i < s.getNumberOfItems(); i++) {
            String[] stuff = s.getItemString(i).split("-");

            TableRow tr = new TableRow(this);
            TextView t1 = new TextView(this);
            TextView t2 = new TextView(this);
            TextView t3 = new TextView(this);
            TextView t4 = new TextView(this);

            t1.setText(stuff[0]);
            t2.setText(stuff[1]);
            t3.setText(stuff[2]);
            t4.setText(stuff[3]);

            tr.addView(t1);
            tr.addView(t2);
            tr.addView(t3);
            tr.addView(t4);

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
