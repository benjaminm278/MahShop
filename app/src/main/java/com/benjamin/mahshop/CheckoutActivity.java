package com.benjamin.mahshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {
    private static final String EXTRA_CART = "com.benjamin.mahshop.extra.CART";
    private TableLayout billLayout;
    private Intent menuIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Test code
        TableLayout t = findViewById(R.id.billTable);
        TableRow tr = new TableRow(this);
        TextView txt = new TextView(this);
        txt.setText("1");
        tr.addView(txt);
        try {
            t.addView(tr);
        }
        catch (Exception e) {
            Log.d("Exception err", e.getMessage());
        }

        writeBill();
    }

    public void writeBill() {
        // Retrieve all necessary variables
        billLayout = findViewById(R.id.billTable);
        menuIntent = getIntent();
        //testClass t = menuIntent.getParcelableExtra("TEST");
        shopCart s = menuIntent.getParcelableExtra("CART");
        try {
            Log.d("helloTest", s.getTotal() + "");
        }
        catch (Exception e) {
            Log.d("thissucks", s + "");
        }
        /*shopCart s = menuIntent.getParcelableExtra("CART");
        try {
            Toast.makeText(this, s.getItemString(0) + "", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Log.d("Wtfman", "$" + s.getTotal());
        }*/
    }
}
