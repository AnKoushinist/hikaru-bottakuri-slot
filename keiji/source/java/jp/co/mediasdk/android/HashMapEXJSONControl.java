package jp.co.mediasdk.android;

public class HashMapEXJSONControl extends HashMapEXByteArraySupport {
    protected boolean a = true;

    public boolean f(String str) {
        if (!StringUtilEqualsSupport.a("media_user_attributes", str) && this.a) {
            return super.f(str);
        }
        return false;
    }
}
