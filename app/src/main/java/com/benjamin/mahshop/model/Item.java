package com.benjamin.mahshop.model;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

public class Item extends Object implements Parcelable {
    private String name;
    private String description;
    private double price;
    private int imageId;
    private int quantity;
    private double subTotal;

    private char delimiter = '-';

    /**
     * Creates a new item
     * @param name
     * @param price
     * @param quantity
     */
    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.subTotal = price * quantity;
    }

    /**
     *
     * @param name
     * @param description
     * @param price
     * @param imageId
     * @param quantity
     */
    public Item(String name, String description, double price, int imageId, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageId = imageId;
        this.quantity = quantity;
        this.subTotal = price * quantity;
    }

    /**
     * Updates or sets quantity
     * @param quantity
     */
    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }


    /**
     * Returns name of item
     * @return
     */
    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public int getImageId() {
        return this.imageId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public double getSubTotal() {
        return this.quantity * this.price;
    }

    /**
     * Compares two items
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Item)) {
            return false;
        }

        Item otherItem = (Item) o;

        return this.name == otherItem.name
                && this.price == otherItem.price;
    }

    /**
     * Returns a string of an item with delimiter
     * @return
     */
    @Override
    public String toString() {
        return this.name + delimiter
                + this.price + delimiter
                + this.quantity + delimiter
                + this.subTotal;
    }

    /*
        Start of parcelable implementations
     */

    /**
     * Stores item attributes in parcel object
     * @param in
     */
    protected Item(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        quantity = in.readInt();
        subTotal = in.readDouble();
    }

    /**
     * Creates a creator
     */
    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Reads from parcel object
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeInt(quantity);
        dest.writeDouble(subTotal);
    }
}
