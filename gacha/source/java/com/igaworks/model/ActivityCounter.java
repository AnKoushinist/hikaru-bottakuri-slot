package com.igaworks.model;

public class ActivityCounter implements Comparable {
    private String activity;
    private int activityCounterNo;
    private int counter;
    private int day;
    private int dayUpdated;
    private String group;
    private int hour;
    private int hourUpdated;
    private int month;
    private int monthUpdated;
    private String noCountingUpdateDatetime;
    private String registDatetime;
    private String updateDatetime;
    private int year;
    private int yearUpdated;

    public ActivityCounter(int i, int i2, int i3, int i4, int i5, String str, String str2, int i6, int i7, int i8, int i9, int i10, String str3, String str4, String str5) {
        this.activityCounterNo = i;
        this.year = i2;
        this.month = i3;
        this.day = i4;
        this.hour = i5;
        this.group = str;
        this.activity = str2;
        this.counter = i6;
        this.yearUpdated = i7;
        this.monthUpdated = i8;
        this.dayUpdated = i9;
        this.hourUpdated = i10;
        this.registDatetime = str3;
        this.updateDatetime = str4;
        this.noCountingUpdateDatetime = str5;
    }

    public String getNoCountingUpdateDatetime() {
        return this.noCountingUpdateDatetime;
    }

    public int getActivityCounterNo() {
        return this.activityCounterNo;
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public int getHour() {
        return this.hour;
    }

    public int getCounter() {
        return this.counter;
    }

    public int getYearUpdated() {
        return this.yearUpdated;
    }

    public int getMonthUpdated() {
        return this.monthUpdated;
    }

    public int getDayUpdated() {
        return this.dayUpdated;
    }

    public int getHourUpdated() {
        return this.hourUpdated;
    }

    public String getUpdateDatetime() {
        return this.updateDatetime;
    }

    public int compareTo(Object obj) {
        try {
            ActivityCounter activityCounter = (ActivityCounter) obj;
            if (activityCounter == null) {
                return -1;
            }
            if (this.year == activityCounter.year && this.month == activityCounter.month && this.day == activityCounter.day && this.hour == activityCounter.hour) {
                if (this.registDatetime.compareTo(activityCounter.registDatetime) <= 0) {
                    return 1;
                }
                return -1;
            } else if (this.year > activityCounter.getYear()) {
                return -1;
            } else {
                if (this.year == activityCounter.year && this.month > activityCounter.month) {
                    return -1;
                }
                if (this.year == activityCounter.year && this.month == activityCounter.month && this.day > activityCounter.day) {
                    return -1;
                }
                if (this.year == activityCounter.year && this.month == activityCounter.month && this.day == activityCounter.day && this.hour > activityCounter.hour) {
                    return -1;
                }
                return 1;
            }
        } catch (Exception e) {
            return -1;
        }
    }
}
