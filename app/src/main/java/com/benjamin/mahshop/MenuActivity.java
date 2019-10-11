package com.benjamin.mahshop;

import android.content.DialogInterface;
import android.os.Bundle;

import com.benjamin.mahshop.model.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.util.LinkedList;

public class MenuActivity extends AppCompatActivity {
    private LinkedList<Item> listOfItems = new LinkedList();
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
        }
        catch (Exception e) {
            Log.d("test", "here2");
        }

        // Retrieve recycler view
        RecyclerView rc = findViewById(R.id.itemRecyclerView);
        // Create adapter
        ProductAdapter pa = new ProductAdapter(this, listOfItems);
        // Connect adapter to RecyclerView
        rc.setAdapter(pa);
        // Set layout manager
        rc.setLayoutManager(new LinearLayoutManager(this));
    }

    public void showShippingAlert(View view) {
        AlertDialog.Builder a = new AlertDialog.Builder(this);

        a.setTitle("Shipping Options");

        a.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        a.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        a.show();
    }
}
