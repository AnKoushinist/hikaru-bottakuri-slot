package jp.reifrontier.silentlogsdkandroid.data.daily;

import rx.Observable;
import rx.functions.Func0;

final /* synthetic */ class RFLDailyLoader$$Lambda$1 implements Func0 {
    private final RFLDailyLoader arg$1;
    private final long arg$2;

    private RFLDailyLoader$$Lambda$1(RFLDailyLoader rFLDailyLoader, long j) {
        this.arg$1 = rFLDailyLoader;
        this.arg$2 = j;
    }

    public static Func0 lambdaFactory$(RFLDailyLoader rFLDailyLoader, long j) {
        return new RFLDailyLoader$$Lambda$1(rFLDailyLoader, j);
    }

    public Object call() {
        return Observable.just(this.arg$1.loadDaily(this.arg$2));
    }
}
