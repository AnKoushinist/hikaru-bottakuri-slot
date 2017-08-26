package twitter4j;

import java.io.Serializable;

public class GeoLocation implements Serializable {
    private static final long serialVersionUID = 6353721071298376949L;
    private double latitude;
    private double longitude;

    public GeoLocation(double d, double d2) {
        this.latitude = d;
        this.longitude = d2;
    }

    GeoLocation() {
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeoLocation)) {
            return false;
        }
        GeoLocation geoLocation = (GeoLocation) obj;
        if (Double.compare(geoLocation.getLatitude(), this.latitude) != 0) {
            return false;
        }
        if (Double.compare(geoLocation.getLongitude(), this.longitude) != 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.latitude);
        int i = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        long doubleToLongBits2 = Double.doubleToLongBits(this.longitude);
        return (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }

    public String toString() {
        return "GeoLocation{latitude=" + this.latitude + ", longitude=" + this.longitude + '}';
    }
}
