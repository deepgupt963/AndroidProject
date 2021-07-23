package com.test.kirana;

import java.io.Serializable;

public class GroceryItem implements Serializable {
    // Name of commodity.
    private String name;
    // Price per item.
    private double price;
    // Quantity of commodity.
    private int quantity;
    // Content quantity (size)
    private String netWeight;

    GroceryItem() { }
    GroceryItem(String name, String netQuantity, int quantity, double price) {
        this.name = name; this.quantity = quantity; this.price = price; this.netWeight = netQuantity;
    }

    String getName() { return name; }
    double getPrice() { return price; }
    double getTotalPrice() { return price*quantity; }
    String getNetWeight() { return netWeight; }
    int getQuantity() { return quantity; }

    void setName(String name) { this.name=name; }
    void setQuantity(int quantity) { this.quantity=quantity; }
    void setPrice(double price) { this.price=price; }
    void setNetWeight(String netQuantity) { this.netWeight=netQuantity; }
}
