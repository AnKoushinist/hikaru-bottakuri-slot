package jp.co.vaz.creator.hikaru.InAppPurchase.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    Map<String, Purchase> mPurchaseMap = new HashMap();
    Map<String, SkuDetails> mSkuMap = new HashMap();

    Inventory() {
    }

    public SkuDetails getSkuDetails(String str) {
        return (SkuDetails) this.mSkuMap.get(str);
    }

    public Purchase getPurchase(String str) {
        return (Purchase) this.mPurchaseMap.get(str);
    }

    public boolean hasPurchase(String str) {
        return this.mPurchaseMap.containsKey(str);
    }

    public boolean hasDetails(String str) {
        return this.mSkuMap.containsKey(str);
    }

    public void erasePurchase(String str) {
        if (this.mPurchaseMap.containsKey(str)) {
            this.mPurchaseMap.remove(str);
        }
    }

    List<String> getAllOwnedSkus() {
        return new ArrayList(this.mPurchaseMap.keySet());
    }

    List<String> getAllOwnedSkus(String str) {
        List<String> arrayList = new ArrayList();
        for (Purchase purchase : this.mPurchaseMap.values()) {
            if (purchase.getItemType().equals(str)) {
                arrayList.add(purchase.getSku());
            }
        }
        return arrayList;
    }

    List<Purchase> getAllPurchases() {
        return new ArrayList(this.mPurchaseMap.values());
    }

    void addSkuDetails(SkuDetails skuDetails) {
        this.mSkuMap.put(skuDetails.getSku(), skuDetails);
    }

    void addPurchase(Purchase purchase) {
        this.mPurchaseMap.put(purchase.getSku(), purchase);
    }
}
