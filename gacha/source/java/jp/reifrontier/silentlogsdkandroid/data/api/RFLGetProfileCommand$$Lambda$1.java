package jp.reifrontier.silentlogsdkandroid.data.api;

import retrofit2.Response;
import rx.functions.Func1;

final /* synthetic */ class RFLGetProfileCommand$$Lambda$1 implements Func1 {
    private static final RFLGetProfileCommand$$Lambda$1 instance = new RFLGetProfileCommand$$Lambda$1();

    private RFLGetProfileCommand$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return RFLGetProfileCommand.lambda$execute$0((Response) obj);
    }
}
