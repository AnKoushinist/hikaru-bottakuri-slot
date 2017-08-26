package jp.co.vaz.creator.hikaru.InAppPurchase.util;

public class IabException extends Exception {
    IabResult mResult;

    public IabException(IabResult iabResult) {
        this(iabResult, null);
    }

    public IabException(int i, String str) {
        this(new IabResult(i, str));
    }

    public IabException(IabResult iabResult, Exception exception) {
        super(iabResult.getMessage(), exception);
        this.mResult = iabResult;
    }

    public IabException(int i, String str, Exception exception) {
        this(new IabResult(i, str), exception);
    }

    public IabResult getResult() {
        return this.mResult;
    }
}
