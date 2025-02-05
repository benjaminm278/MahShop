package com.benjamin.mahshop.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.benjamin.mahshop.R;

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
     */
    public void addItem(Item i) {
        boolean found = false;
        // Iterates through cart
        for (int j = 0; j < items.size(); j++) {
            // Compares current item with each item in cart
            if (i.equals(items.get(j))) {
                // Changes quantity
                items.get(items.indexOf(i)).increaseQuantityByOne();
                found = true;
            }
        }

        // Item doesn't exist?
        if (!found) {
            // Adds new item to cart with new quantity
            i.increaseQuantityByOne();
            items.add(i);
        }

        // Updates the grand subtotal of the cart
        grandSubtotal += i.getPrice();
    }

    /**
     * Decreases item by one and removes item if needed
     */
    public void decreaseItemCount(Item i) {
        i.decreaseQuantityByOne();

        if (i.getQuantity() <= 0) {
            // Remove from list
            items.remove(i);
        }

        grandSubtotal -= i.getPrice();
    }

    /**
     * Returns the grand subtotal of the items in cart
     * @return
     */
    public double getTotal() {
        return grandSubtotal;
    }

    /**
     * Checks if an item is in the shopping cart based on the item's name
     * @param name
     * @return
     */
    public boolean contains(String name) {
        return indexOf(name) != -1; // Method below
    }

    /**
     * Retrieves the index of an item given the name
     * @param name
     * @return
     */
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
     * Returns the item based on index
     * @param index
     * @return
     */
    public Item getItem(int index) {
        return items.get(index);
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

    /**
     * Returns the size of shopping cart
     * @return
     */
    public int getNumberOfItems() {
        return items.size();
    }

    /*****************************
     * Parcelable implementation *
     *****************************/
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
