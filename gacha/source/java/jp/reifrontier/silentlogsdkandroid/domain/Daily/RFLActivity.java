package jp.reifrontier.silentlogsdkandroid.domain.Daily;

import android.location.Location;
import com.google.a.a.a;
import com.google.a.a.c;
import java.util.List;
import jp.reifrontier.silentlogsdkandroid.util.RFLDailyUtils;
import jp.reifrontier.silentlogsdkandroid.util.RFLDateUtils;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.TwitterResponse;

public class RFLActivity {
    @a
    private RFL_ACTIVITY_TYPE activityType;
    @a
    @c(a = "calories")
    private int calories;
    @a
    @c(a = "dataType")
    private String dataType = "sensor";
    @a
    @c(a = "detailType")
    private String detailType;
    @a
    @c(a = "totalDistance")
    private int distance;
    @a
    @c(a = "startTime")
    private int fromDate;
    @a
    @c(a = "steps")
    private int steps;
    @a
    @c(a = "activity")
    private String stringType;
    @a
    @c(a = "activityTagList")
    private List<RFLTag> tagList;
    @a
    @c(a = "timezone")
    private String timezone;
    @a
    @c(a = "endTime")
    private int toDate;
    @a
    @c(a = "totalTime")
    private int totalTime;
    @a
    @c(a = "trackPoints")
    private List<RFLTrackPoint> trackPoints;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLActivity$RFL_ACTIVITY_TYPE = new int[RFL_ACTIVITY_TYPE.values().length];

        static {
            try {
                $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLActivity$RFL_ACTIVITY_TYPE[RFL_ACTIVITY_TYPE.STAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLActivity$RFL_ACTIVITY_TYPE[RFL_ACTIVITY_TYPE.WALK.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLActivity$RFL_ACTIVITY_TYPE[RFL_ACTIVITY_TYPE.TRANSPORT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum RFL_ACTIVITY_TYPE {
        STAY,
        TRANSPORT,
        WALK
    }

    public RFLActivity(int i, String str, RFL_ACTIVITY_TYPE rfl_activity_type) {
        this.fromDate = i;
        this.timezone = str;
        this.activityType = rfl_activity_type;
        this.stringType = getActivityStringType(rfl_activity_type);
        this.detailType = getActivityDetailType(rfl_activity_type);
        this.calories = 0;
    }

    public int getFromDate() {
        return this.fromDate;
    }

    public int getToDate() {
        return this.toDate;
    }

    public String getTimeZone() {
        return this.timezone;
    }

    public int getSteps() {
        return this.steps;
    }

    public RFL_ACTIVITY_TYPE getActivityType() {
        if (this.activityType != null) {
            return this.activityType;
        }
        return getActivityTypeFromString(this.stringType);
    }

    public List<RFLTrackPoint> getTrackPoints() {
        return this.trackPoints;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getAverageDistance() {
        return RFLDailyUtils.getAverageDistance(this.trackPoints);
    }

    public String getActivityString() {
        String str = BuildConfig.FLAVOR;
        switch (AnonymousClass1.$SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLActivity$RFL_ACTIVITY_TYPE[this.activityType.ordinal()]) {
            case TwitterResponse.READ /*1*/:
                return "sty";
            case TwitterResponse.READ_WRITE /*2*/:
                return "wlk";
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return "trp";
            default:
                return str;
        }
    }

    public String getFromToString() {
        return RFLDateUtils.getFromToString((double) this.fromDate, (double) this.toDate, this.timezone);
    }

    public void setSteps(int i) {
        this.steps = i;
    }

    public void setActivityType(RFL_ACTIVITY_TYPE rfl_activity_type) {
        this.activityType = rfl_activity_type;
        this.stringType = getActivityStringType(rfl_activity_type);
        this.detailType = getActivityDetailType(rfl_activity_type);
    }

    public void setToDate(int i) {
        this.toDate = i;
        this.totalTime = i - this.fromDate;
    }

    public void setTrackPoints(List<RFLTrackPoint> list) {
        int i;
        this.trackPoints = null;
        this.trackPoints = list;
        if (list.size() > 0) {
            i = 0;
            Location location = null;
            for (RFLTrackPoint rFLTrackPoint : list) {
                int distanceTo;
                Location location2 = new Location("silentlog_android");
                location2.setLatitude(rFLTrackPoint.getLatitude());
                location2.setLongitude(rFLTrackPoint.getLongitude());
                if (location != null) {
                    distanceTo = ((int) location.distanceTo(location2)) + i;
                } else {
                    distanceTo = i;
                }
                location = location2;
                i = distanceTo;
            }
        } else {
            i = 0;
        }
        this.distance = i;
    }

    public void setTagList(List<RFLTag> list) {
        this.tagList = list;
    }

    public String getActivityStringType(RFL_ACTIVITY_TYPE rfl_activity_type) {
        String str = BuildConfig.FLAVOR;
        switch (AnonymousClass1.$SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLActivity$RFL_ACTIVITY_TYPE[rfl_activity_type.ordinal()]) {
            case TwitterResponse.READ /*1*/:
                return "sty";
            case TwitterResponse.READ_WRITE /*2*/:
                return "wlk";
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return "trp";
            default:
                return str;
        }
    }

    public String getActivityDetailType(RFL_ACTIVITY_TYPE rfl_activity_type) {
        String str = BuildConfig.FLAVOR;
        switch (AnonymousClass1.$SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLActivity$RFL_ACTIVITY_TYPE[rfl_activity_type.ordinal()]) {
            case TwitterResponse.READ /*1*/:
                return "stay";
            case TwitterResponse.READ_WRITE /*2*/:
                return "walk";
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return "auto";
            default:
                return str;
        }
    }

    public RFL_ACTIVITY_TYPE getActivityTypeFromString(String str) {
        Object obj = -1;
        switch (str.hashCode()) {
            case 114232:
                if (str.equals("sty")) {
                    obj = null;
                    break;
                }
                break;
            case 117814:
                if (str.equals("wlk")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case TwitterResponse.NONE /*0*/:
                return RFL_ACTIVITY_TYPE.STAY;
            case TwitterResponse.READ /*1*/:
                return RFL_ACTIVITY_TYPE.WALK;
            default:
                return RFL_ACTIVITY_TYPE.TRANSPORT;
        }
    }
}
