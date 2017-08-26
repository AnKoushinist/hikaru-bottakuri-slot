package jp.co.vaz.creator.hikaru2.InAppPurchase.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    Map<String, SkuDetails> a = new HashMap();
    Map<String, Purchase> b = new HashMap();

    Inventory() {
    }

    public SkuDetails a(String str) {
        return (SkuDetails) this.a.get(str);
    }

    public Purchase b(String str) {
        return (Purchase) this.b.get(str);
    }

    public boolean c(String str) {
        return this.b.containsKey(str);
    }

    List<String> d(String str) {
        List<String> arrayList = new ArrayList();
        for (Purchase purchase : this.b.values()) {
            if (purchase.a().equals(str)) {
                arrayList.add(purchase.c());
            }
        }
        return arrayList;
    }

    void a(SkuDetails skuDetails) {
        this.a.put(skuDetails.a(), skuDetails);
    }

    void a(Purchase purchase) {
        this.b.put(purchase.c(), purchase);
    }
}
