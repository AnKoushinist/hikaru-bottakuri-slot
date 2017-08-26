package jp.reifrontier.silentlogsdkandroid.domain.Daily;

import android.os.Build;
import android.os.Build.VERSION;
import com.google.a.a.a;
import com.google.a.a.c;
import java.util.ArrayList;
import java.util.List;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLActivity.RFL_ACTIVITY_TYPE;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import jp.reifrontier.silentlogsdkandroid.util.RFLDateUtils;

public class RFLDaily {
    @a(a = false, b = false)
    private ArrayList<RFLActivity> allActivities;
    @a(a = false, b = false)
    private int date;
    @a
    @c(a = "date")
    private String dateString;
    @a
    @c(a = "device_name")
    private String deviceName = (Build.MANUFACTURER + " " + Build.MODEL);
    @a
    @c(a = "device_os")
    private String deviceOS = VERSION.RELEASE;
    @a(a = false, b = false)
    private int distance;
    @a(a = false, b = false)
    private RFLActivity lastAct;
    @a
    @c(a = "segments")
    private List<RFLSegment> segments;
    @a
    @c(a = "steps")
    private int steps;
    @a
    @c(a = "timedifference")
    private int timeDifference;
    @a
    @c(a = "timezone")
    private String timezone;

    public RFLDaily(int i, String str) {
        this.date = i;
        this.timezone = str;
        this.dateString = RFLDateUtils.getAPIDate(i, RFLConstants.kRFLDateTimeFormat_API_Model_DateTime_Plus);
    }

    public String getTimezone() {
        return this.timezone;
    }

    public int getDate() {
        return this.date;
    }

    public int getTimeDifference() {
        return this.timeDifference;
    }

    public String getDateString() {
        return this.dateString;
    }

    public List<RFLSegment> getSegments() {
        return this.segments;
    }

    public int getSteps() {
        return this.steps;
    }

    public int getDistance() {
        return this.distance;
    }

    public List<RFLActivity> getAllActivities() {
        if (this.allActivities == null) {
            this.allActivities = new ArrayList();
            int i = 0;
            int i2 = 0;
            for (RFLSegment activities : this.segments) {
                for (RFLActivity rFLActivity : activities.getActivities()) {
                    this.allActivities.add(rFLActivity);
                    i2 += rFLActivity.getSteps();
                    i += rFLActivity.getDistance();
                }
            }
            this.steps = i2;
            this.distance = i;
        }
        return this.allActivities;
    }

    public RFLActivity getLastActivity() {
        if (this.allActivities == null) {
            this.allActivities = new ArrayList();
            int i = 0;
            int i2 = 0;
            for (RFLSegment activities : this.segments) {
                for (RFLActivity rFLActivity : activities.getActivities()) {
                    this.allActivities.add(rFLActivity);
                    i2 += rFLActivity.getSteps();
                    i += rFLActivity.getDistance();
                }
            }
            this.steps = i2;
            this.distance = i;
        }
        return (RFLActivity) this.allActivities.get(this.allActivities.size() - 1);
    }

    public void setSegments(List<RFLSegment> list) {
        this.segments = list;
        this.allActivities = new ArrayList();
        int i = 0;
        long j = 0;
        int i2 = 0;
        for (RFLSegment activities : list) {
            for (RFLActivity rFLActivity : activities.getActivities()) {
                this.allActivities.add(rFLActivity);
                i += rFLActivity.getSteps();
                if (rFLActivity.getActivityType() != RFL_ACTIVITY_TYPE.STAY) {
                    i2 += rFLActivity.getDistance();
                    j = (long) (((double) j) + getDifference(rFLActivity));
                }
            }
        }
        this.steps = i;
        this.distance = i2;
        this.timeDifference = (int) j;
    }

    private double getDifference(RFLActivity rFLActivity) {
        return (double) (rFLActivity.getToDate() - rFLActivity.getFromDate());
    }
}
