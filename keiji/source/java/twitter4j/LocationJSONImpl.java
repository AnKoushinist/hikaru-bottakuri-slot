package twitter4j;

import com.tapjoy.TJAdUnitConstants.String;
import com.unity3d.ads.metadata.MediationMetaData;
import twitter4j.conf.Configuration;

final class LocationJSONImpl implements Location {
    private static final long serialVersionUID = -1312752311160422264L;
    private final String countryCode;
    private final String countryName;
    private final String name;
    private final int placeCode;
    private final String placeName;
    private final String url;
    private final int woeid;

    LocationJSONImpl(JSONObject jSONObject) {
        try {
            this.woeid = ParseUtil.getInt("woeid", jSONObject);
            this.countryName = ParseUtil.getUnescapedString("country", jSONObject);
            this.countryCode = ParseUtil.getRawString("countryCode", jSONObject);
            if (jSONObject.isNull("placeType")) {
                this.placeName = null;
                this.placeCode = -1;
            } else {
                JSONObject jSONObject2 = jSONObject.getJSONObject("placeType");
                this.placeName = ParseUtil.getUnescapedString(MediationMetaData.KEY_NAME, jSONObject2);
                this.placeCode = ParseUtil.getInt("code", jSONObject2);
            }
            this.name = ParseUtil.getUnescapedString(MediationMetaData.KEY_NAME, jSONObject);
            this.url = ParseUtil.getUnescapedString(String.URL, jSONObject);
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    static ResponseList<Location> createLocationList(HttpResponse httpResponse, Configuration configuration) {
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
        }
        return createLocationList(httpResponse.asJSONArray(), configuration.isJSONStoreEnabled());
    }

    static ResponseList<Location> createLocationList(JSONArray jSONArray, boolean z) {
        try {
            int length = jSONArray.length();
            ResponseList<Location> responseListImpl = new ResponseListImpl(length, null);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                LocationJSONImpl locationJSONImpl = new LocationJSONImpl(jSONObject);
                responseListImpl.add(locationJSONImpl);
                if (z) {
                    TwitterObjectFactory.registerJSONObject(locationJSONImpl, jSONObject);
                }
            }
            if (z) {
                TwitterObjectFactory.registerJSONObject(responseListImpl, jSONArray);
            }
            return responseListImpl;
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public int getWoeid() {
        return this.woeid;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public int getPlaceCode() {
        return this.placeCode;
    }

    public String getName() {
        return this.name;
    }

    public String getURL() {
        return this.url;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocationJSONImpl)) {
            return false;
        }
        if (this.woeid != ((LocationJSONImpl) obj).woeid) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.woeid;
    }

    public String toString() {
        return "LocationJSONImpl{woeid=" + this.woeid + ", countryName='" + this.countryName + '\'' + ", countryCode='" + this.countryCode + '\'' + ", placeName='" + this.placeName + '\'' + ", placeCode='" + this.placeCode + '\'' + ", name='" + this.name + '\'' + ", url='" + this.url + '\'' + '}';
    }
}
