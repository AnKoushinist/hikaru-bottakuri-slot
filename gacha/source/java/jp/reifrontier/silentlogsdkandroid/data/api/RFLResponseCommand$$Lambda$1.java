package jp.reifrontier.silentlogsdkandroid.data.api;

import retrofit2.Response;
import rx.functions.Func1;

final /* synthetic */ class RFLResponseCommand$$Lambda$1 implements Func1 {
    private static final RFLResponseCommand$$Lambda$1 instance = new RFLResponseCommand$$Lambda$1();

    private RFLResponseCommand$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return RFLResponseCommand.lambda$execute$0((Response) obj);
    }
}
