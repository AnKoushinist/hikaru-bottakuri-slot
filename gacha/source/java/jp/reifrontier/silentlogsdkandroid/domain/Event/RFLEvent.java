package jp.reifrontier.silentlogsdkandroid.domain.Event;

import android.location.Location;
import com.google.a.a.c;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RFLEvent {
    @c(a = "event_center")
    private RFLEventCenter eventCenter;
    @c(a = "event_end")
    private Integer eventEnd;
    @c(a = "event_id")
    private Integer eventId;
    @c(a = "event_name")
    private String eventName;
    @c(a = "poi")
    private RFLNotifiedPoi eventPoi;
    @c(a = "event_start")
    private Integer eventStart;
    @c(a = "event_time_range")
    private RFLEventTimeRange eventTimeRange;
    @c(a = "notification")
    private RFLNotification notification;
    @c(a = "timings")
    private List<RFLTiming> timings;

    private class RFLEventCenter {
        @c(a = "lat")
        private Double lat;
        @c(a = "lon")
        private Double lon;
        @c(a = "range")
        private Float range;

        private RFLEventCenter() {
        }

        Double getLat() {
            return this.lat;
        }

        Double getLon() {
            return this.lon;
        }

        Float getRange() {
            return this.range;
        }
    }

    private class RFLEventTimeRange {
        @c(a = "end")
        private String end;
        @c(a = "start")
        private String start;
        @c(a = "weekday")
        private RFLWeekDay weekDay;

        class RFLWeekDay {
            @c(a = "fri")
            private Integer fri;
            @c(a = "mon")
            private Integer mon;
            @c(a = "sat")
            private Integer sat;
            @c(a = "sun")
            private Integer sun;
            @c(a = "thu")
            private Integer thu;
            @c(a = "tue")
            private Integer tue;
            @c(a = "wed")
            private Integer wed;

            RFLWeekDay() {
            }

            boolean isMatchDayOfWeek(int i) {
                if (i == 1 && this.sun.intValue() == 1) {
                    return true;
                }
                if (i == 2 && this.mon.intValue() == 1) {
                    return true;
                }
                if (i == 3 && this.tue.intValue() == 1) {
                    return true;
                }
                if (i == 4 && this.wed.intValue() == 1) {
                    return true;
                }
                if (i == 5 && this.thu.intValue() == 1) {
                    return true;
                }
                if (i == 6 && this.fri.intValue() == 1) {
                    return true;
                }
                if (i == 7 && this.sat.intValue() == 1) {
                    return true;
                }
                return false;
            }
        }

        private RFLEventTimeRange() {
        }
    }

    public Integer getEventId() {
        return this.eventId;
    }

    public String getEventName() {
        return this.eventName;
    }

    public RFLNotification getNotification() {
        return this.notification;
    }

    public boolean isMatchRange() {
        if (this.eventCenter == null || this.eventCenter.getLat() == null || this.eventCenter.getLon() == null || this.eventCenter.getRange() == null) {
            return false;
        }
        return true;
    }

    public Location getCenterLocation() {
        if (this.eventCenter == null || this.eventCenter.getLat() == null || this.eventCenter.getLon() == null) {
            return null;
        }
        Location location = new Location("SilentLog");
        location.setLatitude(this.eventCenter.getLat().doubleValue());
        location.setLongitude(this.eventCenter.getLon().doubleValue());
        return location;
    }

    public float getCenterRange() {
        if (this.eventCenter.getRange() != null) {
            return this.eventCenter.getRange().floatValue();
        }
        return 10000.0f;
    }

    public boolean isMatchPeriod() {
        Calendar instance = Calendar.getInstance();
        Date time = instance.getTime();
        instance.setTimeInMillis(((long) this.eventStart.intValue()) * 1000);
        Date time2 = instance.getTime();
        instance.setTimeInMillis(((long) this.eventEnd.intValue()) * 1000);
        Date time3 = instance.getTime();
        if (time.compareTo(time2) == 1 && time.compareTo(time3) == -1) {
            return true;
        }
        return false;
    }

    public boolean isMatchDayOfWeek() {
        return this.eventTimeRange.weekDay.isMatchDayOfWeek(Calendar.getInstance().get(7));
    }

    public boolean isMatchTime() {
        String[] split = this.eventTimeRange.start.split(":");
        String[] split2 = this.eventTimeRange.end.split(":");
        if (split.length == 2 && split2.length == 2) {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            int parseInt3 = Integer.parseInt(split2[0]);
            int parseInt4 = Integer.parseInt(split2[1]);
            Calendar instance = Calendar.getInstance();
            Date time = instance.getTime();
            instance.set(11, parseInt);
            instance.set(12, parseInt2);
            Date time2 = instance.getTime();
            instance.set(11, parseInt3);
            instance.set(12, parseInt4);
            Date time3 = instance.getTime();
            if (time.compareTo(time2) == 1 && time.compareTo(time3) == -1) {
                return true;
            }
        }
        return false;
    }

    public RFLNotifiedPoi getEventPoi() {
        return this.eventPoi;
    }

    public void setEventPoi(RFLNotifiedPoi rFLNotifiedPoi) {
        this.eventPoi = rFLNotifiedPoi;
    }

    public List<RFLTiming> getTimings() {
        return this.timings;
    }
}
