package jp.co.vaz.creator.hikaru2.InAppPurchase.util;

public class IabException extends Exception {
    IabResult a;

    public IabException(IabResult iabResult) {
        this(iabResult, null);
    }

    public IabException(int i, String str) {
        this(new IabResult(i, str));
    }

    public IabException(IabResult iabResult, Exception exception) {
        super(iabResult.b(), exception);
        this.a = iabResult;
    }

    public IabException(int i, String str, Exception exception) {
        this(new IabResult(i, str), exception);
    }

    public IabResult a() {
        return this.a;
    }
}
