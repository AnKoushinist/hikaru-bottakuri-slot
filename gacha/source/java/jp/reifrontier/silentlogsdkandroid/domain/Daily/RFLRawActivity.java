package jp.reifrontier.silentlogsdkandroid.domain.Daily;

import com.google.a.a.c;
import com.google.android.gms.location.DetectedActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import jp.reifrontier.silentlogsdkandroid.util.RFLDailyUtils;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.TwitterResponse;

public class RFLRawActivity {
    @c(a = "timestamp")
    public long timestamp;
    @c(a = "timezone")
    private String timezone;
    @c(a = "type")
    private RFL_RAW_ACTIVITY_TYPE type;

    public enum RFL_RAW_ACTIVITY_TYPE {
        STAY,
        TRANSPORT,
        WALK,
        UNKNOWN
    }

    public RFLRawActivity(long j, String str, RFL_RAW_ACTIVITY_TYPE rfl_raw_activity_type) {
        this.timestamp = j;
        this.timezone = str;
        this.type = rfl_raw_activity_type;
    }

    public RFLRawActivity(long j, String str, ArrayList<DetectedActivity> arrayList) {
        int i;
        this.timestamp = j;
        this.timezone = str;
        Iterator it = arrayList.iterator();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (it.hasNext()) {
            DetectedActivity detectedActivity = (DetectedActivity) it.next();
            switch (detectedActivity.a()) {
                case TwitterResponse.NONE /*0*/:
                case TwitterResponse.READ /*1*/:
                    i4 += detectedActivity.b();
                    i = i2;
                    i2 = i3;
                    i3 = i4;
                    break;
                case TwitterResponse.READ_WRITE /*2*/:
                case AdInfo.BANNER_KIND_BANNER7 /*7*/:
                case AdInfo.BANNER_KIND_BANNER8 /*8*/:
                    i3 += detectedActivity.b();
                    i = i2;
                    i2 = i3;
                    i3 = i4;
                    break;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                case AdInfo.BANNER_KIND_BANNER5 /*5*/:
                    i = i2 + detectedActivity.b();
                    i2 = i3;
                    i3 = i4;
                    break;
                default:
                    i = i2;
                    i2 = i3;
                    i3 = i4;
                    break;
            }
            i4 = i3;
            i3 = i2;
            i2 = i;
        }
        i = RFLDailyUtils.maxInt(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(i4), Integer.valueOf(i3), Integer.valueOf(i2)})));
        if (i == i4) {
            this.type = RFL_RAW_ACTIVITY_TYPE.TRANSPORT;
        } else if (i == i3) {
            this.type = RFL_RAW_ACTIVITY_TYPE.WALK;
        } else {
            this.type = RFL_RAW_ACTIVITY_TYPE.STAY;
        }
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getTimezone() {
        return this.timezone;
    }

    public RFL_RAW_ACTIVITY_TYPE getType() {
        return this.type;
    }

    public void setType(RFL_RAW_ACTIVITY_TYPE rfl_raw_activity_type) {
        this.type = rfl_raw_activity_type;
    }
}
