package com.benjamin.mahshop;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

public class shopCart implements Parcelable {
    private double grandSubtotal;
    private ArrayList<Item> items;

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
        // Creates new item, but is not added in cart yet
        Item i = new Item(name, price, quantity);

        // Increase quantity
        quantity++;

        boolean found = false;

        // Iterates through cart
        for (int j = 0; j < items.size(); j++) {
            // Compares current item with each item in cart
            if (i.equals(items.get(j))) {
                // Changes quantity
                items.get(items.indexOf(i)).setQuantity(quantity);
                found = true;
            }
        }

        // Item doesn't exist?
        if (!found) {
            // Adds new item to cart with new quantity
            items.add(new Item(name, price, quantity));
        }

        // Updates the grand subtotal of the cart
        grandSubtotal += price;
    }

    /**
     *
     * @param name
     * @param price
     * @param quantity
     */
    public void decreaseItemCount(String name, double price, int quantity) {
        Item i = new Item(name, price, quantity);

        if (items.contains(i)) {
            // Item exists
            // Decrease count and price
            quantity--;

            // Checks quantity
            if (quantity > 0) {
                // Item stays in cart
                items.get(items.indexOf(i)).setQuantity(quantity);
            }
            else {
                items.remove(i);
            }
        }

        grandSubtotal -= price;
    }

    public double getTotal() {
        return grandSubtotal;
    }

    public boolean contains(String name) {
        return indexOf(name) != -1;
    }

    public int indexOf(String name) {
        // Iterate through each item in cart and compare names
        for (int i = 0; i < items.size(); i++) {
            if (name.equals(items.get(i).getName())) {
                return i;
            }
        }

        return -1;
    }
    /**
     * Retrieves an item string at a given index
     * @param index
     * @return
     */
    public String getItemString(int index) {
        // Verifies index
        if (index < 0 || index > items.size()) {
            throw new IndexOutOfBoundsException("Invalid index :(");
        }

        return items.get(index).toString();
    }

    public int getNumberOfItems() {
        return items.size();
    }

    protected shopCart(Parcel in) {
        grandSubtotal = in.readDouble();
        items = in.readArrayList(Item.class.getClassLoader());
    }

    public static final Creator<shopCart> CREATOR = new Creator<shopCart>() {
        @Override
        public shopCart createFromParcel(Parcel source) {
            return new shopCart(source);
        }

        @Override
        public shopCart[] newArray(int size) {
            return new shopCart[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writes to Parcel
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(grandSubtotal);
        dest.writeList(items);
    }
}
