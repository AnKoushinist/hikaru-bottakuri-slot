package com.igaworks.commerce.model;

import com.igaworks.commerce.IgawPurchaseItem;

public class PurchaseItem extends IgawPurchaseItem {
    private String createdAt;
    private int key;
    private int retryCnt;

    public PurchaseItem(int i, String str, String str2, String str3, double d, int i2, String str4, String str5, String str6, int i3) {
        this.key = i;
        this.orderID = str;
        this.productID = str2;
        this.productName = str3;
        this.price = d;
        this.quantity = i2;
        this.currency = str4;
        this.category = str5;
        this.createdAt = str6;
        this.retryCnt = i3;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public int getKey() {
        return this.key;
    }

    public int getRetryCnt() {
        return this.retryCnt;
    }
}
