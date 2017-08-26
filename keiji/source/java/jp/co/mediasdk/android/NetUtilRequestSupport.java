package jp.co.mediasdk.android;

public class NetUtilRequestSupport extends NetUtilResponseSupport {
    protected HashMapEX n = new HashMapEX();
    protected HashMapEX o = new HashMapEX();
    protected HashMapEX p = new HashMapEX();

    public boolean b(String str, String str2) {
        this.p.c(str, str2);
        return true;
    }

    public boolean a(HashMapEX hashMapEX) {
        return this.p.a(hashMapEX);
    }
}
