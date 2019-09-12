package com.benjamin.mahshop;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class shopCart implements Parcelable {
    private double grandSubtotal;
    private ArrayList<Item> items;

    private String delimiter = "-";

    /**
     * Creates a new shopping cart object
     */
    public shopCart() {
        this.grandSubtotal = 0;
        items = new ArrayList<>();
    }

    /**
     * Adds an item to the cart
     * @param name
     * @param price
     * @param quantity
     */
    public void addItem(String name, double price, int quantity) {
        boolean itemExists = false;
        // Iterate through list and check if item exists
        for (Item x : items) {
            if (x.getName().equals(name)) {
                // Update quantity
                x.setQuantity(quantity);
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            items.add(new Item(name, price, quantity));
        }
    }

    /**
     * Retrieves an item string at a given index
     * @param index
     * @return
     */
    public String getItemString(int index) {
        if (index < 0 || index > items.size()) {
            throw new IndexOutOfBoundsException("Invalid index :(");
        }

        return items.get(index).toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
