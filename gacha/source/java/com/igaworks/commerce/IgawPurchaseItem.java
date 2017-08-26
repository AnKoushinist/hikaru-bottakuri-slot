package com.igaworks.commerce;

public class IgawPurchaseItem {
    protected String category;
    protected String currency;
    protected String orderID;
    protected double price;
    protected String productID;
    protected String productName;
    protected int quantity;

    protected IgawPurchaseItem() {
    }

    public String getOrderID() {
        return this.orderID;
    }

    public String getProductName() {
        return this.productName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getProductID() {
        return this.productID;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getCategory() {
        return this.category;
    }
}
