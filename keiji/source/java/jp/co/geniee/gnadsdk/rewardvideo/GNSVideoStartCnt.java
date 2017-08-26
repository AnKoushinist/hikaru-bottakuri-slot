package jp.co.geniee.gnadsdk.rewardvideo;

import android.content.Context;

public class GNSVideoStartCnt {
    private GNSVideoStartCnt() {
    }

    public static void a(Context context, long j) {
        if (GNSPrefUtil.a(context, "video_start_cnt_first_unixtime", 0) + j > System.currentTimeMillis()) {
            GNSPrefUtil.b(context, "video_start_cnt", GNSPrefUtil.a(context, "video_start_cnt", 0) + 1);
            return;
        }
        GNSPrefUtil.b(context, "video_start_cnt_first_unixtime", System.currentTimeMillis());
        GNSPrefUtil.b(context, "video_start_cnt", 1);
    }

    public static int b(Context context, long j) {
        if (GNSPrefUtil.a(context, "video_start_cnt_first_unixtime", 0) + j > System.currentTimeMillis()) {
            return GNSPrefUtil.a(context, "video_start_cnt", 0);
        }
        return 0;
    }
}
