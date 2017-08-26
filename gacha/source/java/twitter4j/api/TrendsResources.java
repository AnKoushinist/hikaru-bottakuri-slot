package twitter4j.api;

import twitter4j.GeoLocation;
import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Trends;

public interface TrendsResources {
    ResponseList<Location> getAvailableTrends();

    ResponseList<Location> getClosestTrends(GeoLocation geoLocation);

    Trends getPlaceTrends(int i);
}
