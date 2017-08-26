package jp.reifrontier.silentlogsdkandroid.domain.Daily;

import com.google.a.a.a;
import com.google.a.a.c;
import java.util.List;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLActivity.RFL_ACTIVITY_TYPE;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.TwitterResponse;

public class RFLSegment {
    @a
    @c(a = "activities")
    private List<RFLActivity> activities;
    @a
    @c(a = "endTime")
    private int endTime;
    @a
    @c(a = "place")
    private String place;
    @a
    private RFL_SEGMENT_TYPE segment_type;
    @a
    @c(a = "startTime")
    private int startTime;
    @a
    @c(a = "steps")
    private int steps;
    @a
    @c(a = "type")
    private String stringType;
    @a
    @c(a = "timezone")
    private String timezone;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLActivity$RFL_ACTIVITY_TYPE = new int[RFL_ACTIVITY_TYPE.values().length];
        static final /* synthetic */ int[] $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLSegment$RFL_SEGMENT_TYPE = new int[RFL_SEGMENT_TYPE.values().length];

        static {
            try {
                $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLSegment$RFL_SEGMENT_TYPE[RFL_SEGMENT_TYPE.RFLSegmentTypeNone.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLSegment$RFL_SEGMENT_TYPE[RFL_SEGMENT_TYPE.RFLSegmentTypeBlank.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLSegment$RFL_SEGMENT_TYPE[RFL_SEGMENT_TYPE.RFLSegmentTypeMove.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLSegment$RFL_SEGMENT_TYPE[RFL_SEGMENT_TYPE.RFLSegmentTypePlace.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLSegment$RFL_SEGMENT_TYPE[RFL_SEGMENT_TYPE.RFLSegmentTypeUnknown.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLActivity$RFL_ACTIVITY_TYPE[RFL_ACTIVITY_TYPE.TRANSPORT.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLActivity$RFL_ACTIVITY_TYPE[RFL_ACTIVITY_TYPE.WALK.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLActivity$RFL_ACTIVITY_TYPE[RFL_ACTIVITY_TYPE.STAY.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public enum RFL_SEGMENT_TYPE {
        RFLSegmentTypeNone,
        RFLSegmentTypeUnknown,
        RFLSegmentTypePlace,
        RFLSegmentTypeMove,
        RFLSegmentTypeBlank
    }

    public RFLSegment(List<RFLActivity> list) {
        int i = 0;
        this.activities = list;
        RFLActivity rFLActivity = (RFLActivity) list.get(0);
        this.startTime = rFLActivity.getFromDate();
        this.endTime = ((RFLActivity) list.get(list.size() - 1)).getToDate();
        this.timezone = rFLActivity.getTimeZone();
        for (RFLActivity steps : list) {
            i = steps.getSteps() + i;
        }
        this.steps = i;
        this.segment_type = getSegmentTypeFromActivity(rFLActivity);
        this.stringType = getSegmentTypeFromType(this.segment_type);
        this.place = "place";
    }

    public String getTimezone() {
        return this.timezone;
    }

    public RFL_SEGMENT_TYPE getSegment_type() {
        return this.segment_type;
    }

    public List<RFLActivity> getActivities() {
        return this.activities;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getEndTime() {
        return this.endTime;
    }

    public int getSteps() {
        return this.steps;
    }

    public RFL_SEGMENT_TYPE getSegmentTypeFromActivity(RFLActivity rFLActivity) {
        switch (AnonymousClass1.$SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLActivity$RFL_ACTIVITY_TYPE[rFLActivity.getActivityType().ordinal()]) {
            case TwitterResponse.READ /*1*/:
            case TwitterResponse.READ_WRITE /*2*/:
                return RFL_SEGMENT_TYPE.RFLSegmentTypeMove;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return RFL_SEGMENT_TYPE.RFLSegmentTypePlace;
            default:
                return RFL_SEGMENT_TYPE.RFLSegmentTypeUnknown;
        }
    }

    public String getSegmentTypeFromType(RFL_SEGMENT_TYPE rfl_segment_type) {
        String str = BuildConfig.FLAVOR;
        switch (AnonymousClass1.$SwitchMap$jp$reifrontier$silentlogsdkandroid$domain$Daily$RFLSegment$RFL_SEGMENT_TYPE[rfl_segment_type.ordinal()]) {
            case TwitterResponse.READ /*1*/:
                return "none";
            case TwitterResponse.READ_WRITE /*2*/:
                return "blank";
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return "move";
            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                return "place";
            case AdInfo.BANNER_KIND_BANNER5 /*5*/:
                return "unknown";
            default:
                return str;
        }
    }
}
