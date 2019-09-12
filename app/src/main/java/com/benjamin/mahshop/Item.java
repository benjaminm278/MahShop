package com.benjamin.mahshop;

public class Item {
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
}
