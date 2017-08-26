package jp.reifrontier.silentlogsdkandroid;

import java.util.TimeZone;
import rx.functions.Action0;

final /* synthetic */ class SilentLogOperation$$Lambda$5 implements Action0 {
    private final SilentLogOperation arg$1;
    private final String arg$2;
    private final TimeZone arg$3;

    private SilentLogOperation$$Lambda$5(SilentLogOperation silentLogOperation, String str, TimeZone timeZone) {
        this.arg$1 = silentLogOperation;
        this.arg$2 = str;
        this.arg$3 = timeZone;
    }

    public static Action0 lambdaFactory$(SilentLogOperation silentLogOperation, String str, TimeZone timeZone) {
        return new SilentLogOperation$$Lambda$5(silentLogOperation, str, timeZone);
    }

    public void call() {
        this.arg$1.rflDailyManager.postDaily(this.arg$1.postDailyRxBus, this.arg$2, this.arg$3.getID(), true);
    }
}
