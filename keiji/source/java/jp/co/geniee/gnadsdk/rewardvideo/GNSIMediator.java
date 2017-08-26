package jp.co.geniee.gnadsdk.rewardvideo;

import android.app.Activity;
import android.os.Handler;
import java.util.ArrayList;
import java.util.LinkedList;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee.GNSAdapteeListener;

interface GNSIMediator {
    void a();

    void a(Activity activity, String str, String str2, Handler handler, ArrayList<GNSAdaptee> arrayList, LinkedList<GNSAdaptee> linkedList, GNSAdapteeListener gNSAdapteeListener, GNSVideoMediator gNSVideoMediator);

    void a(GNSAdaptee gNSAdaptee);

    void a(GNSRewardVideoAdListener gNSRewardVideoAdListener);

    void a(GNSVideoRewardException gNSVideoRewardException);

    void a(GNSZoneInfo gNSZoneInfo);

    void a(boolean z);

    void b();

    void b(boolean z);

    void c();

    void d();

    boolean e();

    void f();

    boolean g();
}
