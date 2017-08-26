package twitter4j.api;

import twitter4j.GeoLocation;
import twitter4j.GeoQuery;
import twitter4j.Place;
import twitter4j.ResponseList;

public interface PlacesGeoResources {
    Place getGeoDetails(String str);

    ResponseList<Place> getSimilarPlaces(GeoLocation geoLocation, String str, String str2, String str3);

    ResponseList<Place> reverseGeoCode(GeoQuery geoQuery);

    ResponseList<Place> searchPlaces(GeoQuery geoQuery);
}
