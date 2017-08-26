package jp.co.vaz.creator.hikaru2.InAppPurchase.util;

public class IabResult {
    int a;
    String b;

    public IabResult(int i, String str) {
        this.a = i;
        if (str == null || str.trim().length() == 0) {
            this.b = IabHelper.a(i);
        } else {
            this.b = str + " (response: " + IabHelper.a(i) + ")";
        }
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public boolean c() {
        return this.a == 0;
    }

    public boolean d() {
        return !c();
    }

    public String toString() {
        return "IabResult: " + b();
    }
}
