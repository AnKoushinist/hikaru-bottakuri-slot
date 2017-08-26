package jp.reifrontier.silentlogsdkandroid.data.api;

import retrofit2.Response;
import rx.functions.Func1;

final /* synthetic */ class RFLPostDailyCommand$$Lambda$1 implements Func1 {
    private static final RFLPostDailyCommand$$Lambda$1 instance = new RFLPostDailyCommand$$Lambda$1();

    private RFLPostDailyCommand$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return RFLPostDailyCommand.lambda$execute$0((Response) obj);
    }
}
