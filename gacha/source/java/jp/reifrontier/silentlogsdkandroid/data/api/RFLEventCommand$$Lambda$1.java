package jp.reifrontier.silentlogsdkandroid.data.api;

import retrofit2.Response;
import rx.functions.Func1;

final /* synthetic */ class RFLEventCommand$$Lambda$1 implements Func1 {
    private static final RFLEventCommand$$Lambda$1 instance = new RFLEventCommand$$Lambda$1();

    private RFLEventCommand$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return RFLEventCommand.lambda$execute$0((Response) obj);
    }
}
