package jp.reifrontier.silentlogsdkandroid.domain.Daily;

import android.location.Location;
import com.google.a.a.a;
import com.google.a.a.c;
import java.util.TimeZone;

public class RFLTrackPoint {
    @a
    @c(a = "hacc")
    private float accuracy;
    @a
    @c(a = "lat")
    private double latitude;
    @a
    @c(a = "lon")
    private double longitude;
    @a
    @c(a = "time")
    private int timestamp;
    @a
    @c(a = "timezone")
    private String timezone = TimeZone.getDefault().getID();

    public RFLTrackPoint(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.accuracy = location.getAccuracy();
        this.timestamp = (int) Math.floor((double) (location.getTime() / 1000));
    }

    public String getTimezone() {
        return this.timezone;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public float getAccuracy() {
        return this.accuracy;
    }

    public int getTimestamp() {
        return this.timestamp;
    }
}
