package com.benjamin.mahshop;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private String name;
    private double price;
    private int quantity;

    private char delimiter = '-';

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.name + delimiter
                + this.price + delimiter
                + this.quantity;
    }

    protected Item(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        quantity = in.readInt();
    }

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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeInt(quantity);
    }
}
