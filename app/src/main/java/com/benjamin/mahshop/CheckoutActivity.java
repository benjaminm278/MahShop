package com.benjamin.mahshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CheckoutActivity extends AppCompatActivity {

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
    }
}
