package jp.reifrontier.silentlogsdkandroid.util;

import android.location.Location;
import java.util.ArrayList;
import java.util.List;
import jp.reifrontier.silentlogsdkandroid.domain.Daily.RFLTrackPoint;
import org.cocos2dx.lib.GameControllerDelegate;

public class RFLDailyUtils {
    public static int maxInt(List<Integer> list) {
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            int intValue = ((Integer) list.get(i2)).intValue();
            if (i < intValue) {
                i = intValue;
            }
        }
        return i;
    }

    public static RFLTrackPoint getCenterTpFrom(List<RFLTrackPoint> list) {
        RFLTrackPoint rFLTrackPoint = (RFLTrackPoint) list.get(0);
        double latitude = rFLTrackPoint.getLatitude();
        double longitude = rFLTrackPoint.getLongitude();
        double d = longitude;
        double d2 = latitude;
        for (RFLTrackPoint rFLTrackPoint2 : list) {
            double latitude2 = rFLTrackPoint2.getLatitude();
            double longitude2 = rFLTrackPoint2.getLongitude();
            if (d2 > latitude2) {
                d2 = latitude2;
            }
            if (latitude < latitude2) {
                latitude = latitude2;
            }
            if (d > longitude2) {
                d = longitude2;
            }
            if (longitude < longitude2) {
                longitude = longitude2;
            }
        }
        latitude = ((latitude - d2) * 0.5d) + d2;
        longitude = ((longitude - d) * 0.5d) + d;
        Location location = new Location("silentlog_android");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setTime(((long) rFLTrackPoint.getTimestamp()) * 1000);
        location.setAccuracy(rFLTrackPoint.getAccuracy());
        return new RFLTrackPoint(location);
    }

    public static boolean detectSamePlaceWithLocationAndDistance(Location location, Location location2, double d) {
        if (5.0d + d >= getDistanceBetweenAAndB(location, location2)) {
            return true;
        }
        return false;
    }

    public static boolean shouldStopLocationUpdates(ArrayList<Location> arrayList) {
        if (arrayList.size() <= 2 || ((Location) arrayList.get(0)).distanceTo((Location) arrayList.get(arrayList.size() - 1)) <= 300.0f) {
            return true;
        }
        return false;
    }

    public static double getTpDistanceBetweenAAndB(RFLTrackPoint rFLTrackPoint, RFLTrackPoint rFLTrackPoint2) {
        Location location = new Location("silentlog_android");
        location.setLatitude(rFLTrackPoint.getLatitude());
        location.setLongitude(rFLTrackPoint.getLongitude());
        Location location2 = new Location("silentlog_android");
        location2.setLatitude(rFLTrackPoint2.getLatitude());
        location2.setLongitude(rFLTrackPoint2.getLongitude());
        return getDistanceBetweenAAndB(location, location2);
    }

    public static double getDistanceBetweenAAndB(Location location, Location location2) {
        if (location == null || location2 == null) {
            return 0.0d;
        }
        return (double) location.distanceTo(location2);
    }

    public static double getSpeedBetweenAAndBLocation(Location location, Location location2) {
        if (location == null || location2 == null) {
            return 0.0d;
        }
        long time = location2.getTime() - location.getTime();
        int i = ((int) time) / GameControllerDelegate.THUMBSTICK_LEFT_X;
        if (time > 0) {
            return getDistanceBetweenAAndB(location, location2) / ((double) i);
        }
        return 0.0d;
    }

    public static double getSpeedBetweenAAndB(RFLTrackPoint rFLTrackPoint, RFLTrackPoint rFLTrackPoint2) {
        double timestamp = (double) (rFLTrackPoint2.getTimestamp() - rFLTrackPoint.getTimestamp());
        if (timestamp <= 0.0d) {
            return 0.0d;
        }
        Location location = new Location("silentlog_android");
        location.setLatitude(rFLTrackPoint.getLatitude());
        location.setLongitude(rFLTrackPoint.getLongitude());
        Location location2 = new Location("silentlog_android");
        location2.setLatitude(rFLTrackPoint2.getLatitude());
        location2.setLongitude(rFLTrackPoint2.getLongitude());
        return getDistanceBetweenAAndB(location, location2) / timestamp;
    }

    public static double getSpeedBetweenAAndBWithDistance(RFLTrackPoint rFLTrackPoint, RFLTrackPoint rFLTrackPoint2, double d) {
        double timestamp = (double) (rFLTrackPoint2.getTimestamp() - rFLTrackPoint.getTimestamp());
        if (timestamp > 0.0d) {
            return d / timestamp;
        }
        return 0.0d;
    }

    public static boolean detectSamePlaceWithLastLocation(Location location, Location location2) {
        return getDistanceBetweenAAndB(location, location2) < 100.0d;
    }

    public static int getAverageDistance(List<RFLTrackPoint> list) {
        if (list.size() == 0) {
            return 0;
        }
        RFLTrackPoint rFLTrackPoint = (RFLTrackPoint) list.get(0);
        double latitude = rFLTrackPoint.getLatitude();
        double longitude = rFLTrackPoint.getLongitude();
        rFLTrackPoint = (RFLTrackPoint) list.get(list.size() - 1);
        double latitude2 = rFLTrackPoint.getLatitude();
        double longitude2 = rFLTrackPoint.getLongitude();
        Location location = new Location("silentlog_android");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        Location location2 = new Location("silentlog_android");
        location2.setLatitude(latitude2);
        location2.setLongitude(longitude2);
        return (int) location.distanceTo(location2);
    }

    public static void extractGravity(float[] fArr, float[] fArr2, float[] fArr3) {
        fArr2[0] = (fArr2[0] * 0.8f) + (fArr[0] * 0.19999999f);
        fArr2[1] = (fArr2[1] * 0.8f) + (fArr[1] * 0.19999999f);
        fArr2[2] = (fArr2[2] * 0.8f) + (fArr[2] * 0.19999999f);
        fArr3[0] = fArr[0] - fArr2[0];
        fArr3[1] = fArr[1] - fArr2[1];
        fArr3[2] = fArr[2] - fArr2[2];
    }

    public static double getAngleBetweenAAndBAndC(RFLTrackPoint rFLTrackPoint, RFLTrackPoint rFLTrackPoint2, RFLTrackPoint rFLTrackPoint3) {
        double longitude = rFLTrackPoint2.getLongitude() - rFLTrackPoint3.getLongitude();
        double latitude = rFLTrackPoint2.getLatitude() - rFLTrackPoint3.getLatitude();
        double abs = (Math.abs(Math.atan2(longitude, latitude) - Math.atan2(rFLTrackPoint2.getLongitude() - rFLTrackPoint.getLongitude(), rFLTrackPoint2.getLatitude() - rFLTrackPoint.getLatitude())) / 3.141592653589793d) * 180.0d;
        if (abs > 180.0d) {
            return 360.0d - abs;
        }
        return abs;
    }

    public static Boolean isNeedUpdate(double d, double d2) {
        Boolean valueOf = Boolean.valueOf(false);
        if (d - d2 > 600.0d) {
            return Boolean.valueOf(true);
        }
        return valueOf;
    }
}
