package jp.reifrontier.silentlogsdkandroid.data.api;

import retrofit2.Response;
import rx.functions.Func1;

final /* synthetic */ class RFLAuthCommand$$Lambda$1 implements Func1 {
    private static final RFLAuthCommand$$Lambda$1 instance = new RFLAuthCommand$$Lambda$1();

    private RFLAuthCommand$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return RFLAuthCommand.lambda$execute$0((Response) obj);
    }
}
