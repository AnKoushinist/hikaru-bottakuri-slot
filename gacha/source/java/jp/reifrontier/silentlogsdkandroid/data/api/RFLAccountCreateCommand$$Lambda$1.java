package jp.reifrontier.silentlogsdkandroid.data.api;

import retrofit2.Response;
import rx.functions.Func1;

final /* synthetic */ class RFLAccountCreateCommand$$Lambda$1 implements Func1 {
    private static final RFLAccountCreateCommand$$Lambda$1 instance = new RFLAccountCreateCommand$$Lambda$1();

    private RFLAccountCreateCommand$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return RFLAccountCreateCommand.lambda$execute$0((Response) obj);
    }
}
