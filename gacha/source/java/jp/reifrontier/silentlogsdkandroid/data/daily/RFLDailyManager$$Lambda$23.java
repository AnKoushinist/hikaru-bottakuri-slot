package jp.reifrontier.silentlogsdkandroid.data.daily;

import java.util.List;
import rx.functions.Action1;

final /* synthetic */ class RFLDailyManager$$Lambda$23 implements Action1 {
    private final RFLDailyManager arg$1;
    private final String arg$2;
    private final Integer arg$3;
    private final Integer arg$4;

    private RFLDailyManager$$Lambda$23(RFLDailyManager rFLDailyManager, String str, Integer num, Integer num2) {
        this.arg$1 = rFLDailyManager;
        this.arg$2 = str;
        this.arg$3 = num;
        this.arg$4 = num2;
    }

    public static Action1 lambdaFactory$(RFLDailyManager rFLDailyManager, String str, Integer num, Integer num2) {
        return new RFLDailyManager$$Lambda$23(rFLDailyManager, str, num, num2);
    }

    public void call(Object obj) {
        RFLDailyManager.lambda$postResponse$22(this.arg$1, this.arg$2, this.arg$3, this.arg$4, (List) obj);
    }
}
