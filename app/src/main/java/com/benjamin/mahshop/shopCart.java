package com.benjamin.mahshop;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

public class shopCart implements Parcelable {
    private double grandSubtotal;
    private ArrayList<Item> items;

    public double getTotal() { return grandSubtotal;}

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
        Item i = new Item(name, price, quantity);

        // Checks if
        if (items.contains(i)) {
            // Item exists
            // Just update quantity
            items.get(items.indexOf(i)).setQuantity(quantity);
        }
        else {
            items.add(new Item(name, price, quantity));
        }

        grandSubtotal += price;

        Log.d("theTotal", "Q: " + quantity + " " + items.indexOf(i));
    }

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
                // Remove item completely
                items.remove(i);
            }
        }

        grandSubtotal -= price;
        Log.d("theTotal", quantity + "");
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
