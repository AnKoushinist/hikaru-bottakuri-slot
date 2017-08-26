package twitter4j;

import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import twitter4j.conf.Configuration;

final class PlaceJSONImpl extends TwitterResponseImpl implements Serializable, Place {
    private static final long serialVersionUID = -6368276880878829754L;
    private GeoLocation[][] boundingBoxCoordinates;
    private String boundingBoxType;
    private Place[] containedWithIn;
    private String country;
    private String countryCode;
    private String fullName;
    private GeoLocation[][] geometryCoordinates;
    private String geometryType;
    private String id;
    private String name;
    private String placeType;
    private String streetAddress;
    private String url;

    PlaceJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        JSONObject asJSONObject = httpResponse.asJSONObject();
        init(asJSONObject);
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(this, asJSONObject);
        }
    }

    PlaceJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    PlaceJSONImpl() {
    }

    private void init(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2;
            JSONArray jSONArray;
            this.name = ParseUtil.getUnescapedString(MediationMetaData.KEY_NAME, jSONObject);
            this.streetAddress = ParseUtil.getUnescapedString("street_address", jSONObject);
            this.countryCode = ParseUtil.getRawString(TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, jSONObject);
            this.id = ParseUtil.getRawString("id", jSONObject);
            this.country = ParseUtil.getRawString("country", jSONObject);
            if (jSONObject.isNull("place_type")) {
                this.placeType = ParseUtil.getRawString(MoatAdEvent.EVENT_TYPE, jSONObject);
            } else {
                this.placeType = ParseUtil.getRawString("place_type", jSONObject);
            }
            this.url = ParseUtil.getRawString(String.URL, jSONObject);
            this.fullName = ParseUtil.getRawString("full_name", jSONObject);
            if (jSONObject.isNull("bounding_box")) {
                this.boundingBoxType = null;
                this.boundingBoxCoordinates = (GeoLocation[][]) null;
            } else {
                jSONObject2 = jSONObject.getJSONObject("bounding_box");
                this.boundingBoxType = ParseUtil.getRawString(MoatAdEvent.EVENT_TYPE, jSONObject2);
                this.boundingBoxCoordinates = JSONImplFactory.coordinatesAsGeoLocationArray(jSONObject2.getJSONArray("coordinates"));
            }
            if (jSONObject.isNull("geometry")) {
                this.geometryType = null;
                this.geometryCoordinates = (GeoLocation[][]) null;
            } else {
                jSONObject2 = jSONObject.getJSONObject("geometry");
                this.geometryType = ParseUtil.getRawString(MoatAdEvent.EVENT_TYPE, jSONObject2);
                jSONArray = jSONObject2.getJSONArray("coordinates");
                if (this.geometryType.equals("Point")) {
                    this.geometryCoordinates = (GeoLocation[][]) Array.newInstance(GeoLocation.class, new int[]{1, 1});
                    this.geometryCoordinates[0][0] = new GeoLocation(jSONArray.getDouble(1), jSONArray.getDouble(0));
                } else if (this.geometryType.equals("Polygon")) {
                    this.geometryCoordinates = JSONImplFactory.coordinatesAsGeoLocationArray(jSONArray);
                } else {
                    this.geometryType = null;
                    this.geometryCoordinates = (GeoLocation[][]) null;
                }
            }
            if (jSONObject.isNull("contained_within")) {
                this.containedWithIn = null;
                return;
            }
            jSONArray = jSONObject.getJSONArray("contained_within");
            this.containedWithIn = new Place[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                this.containedWithIn[i] = new PlaceJSONImpl(jSONArray.getJSONObject(i));
            }
        } catch (Throwable e) {
            throw new TwitterException(e.getMessage() + ":" + jSONObject.toString(), e);
        }
    }

    public int compareTo(Place place) {
        return this.id.compareTo(place.getId());
    }

    static ResponseList<Place> createPlaceList(HttpResponse httpResponse, Configuration configuration) {
        JSONObject jSONObject = null;
        try {
            jSONObject = httpResponse.asJSONObject();
            return createPlaceList(jSONObject.getJSONObject("result").getJSONArray("places"), httpResponse, configuration);
        } catch (Throwable e) {
            throw new TwitterException(e.getMessage() + ":" + jSONObject.toString(), e);
        }
    }

    static ResponseList<Place> createPlaceList(JSONArray jSONArray, HttpResponse httpResponse, Configuration configuration) {
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
        }
        try {
            int length = jSONArray.length();
            ResponseList<Place> responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                PlaceJSONImpl placeJSONImpl = new PlaceJSONImpl(jSONObject);
                responseListImpl.add(placeJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(placeJSONImpl, jSONObject);
                }
            }
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.registerJSONObject(responseListImpl, jSONArray);
            }
            return responseListImpl;
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public String getName() {
        return this.name;
    }

    public String getStreetAddress() {
        return this.streetAddress;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getId() {
        return this.id;
    }

    public String getCountry() {
        return this.country;
    }

    public String getPlaceType() {
        return this.placeType;
    }

    public String getURL() {
        return this.url;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getBoundingBoxType() {
        return this.boundingBoxType;
    }

    public GeoLocation[][] getBoundingBoxCoordinates() {
        return this.boundingBoxCoordinates;
    }

    public String getGeometryType() {
        return this.geometryType;
    }

    public GeoLocation[][] getGeometryCoordinates() {
        return this.geometryCoordinates;
    }

    public Place[] getContainedWithIn() {
        return this.containedWithIn;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Place) && ((Place) obj).getId().equals(this.id)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public String toString() {
        Object obj;
        Object obj2 = null;
        StringBuilder append = new StringBuilder().append("PlaceJSONImpl{name='").append(this.name).append('\'').append(", streetAddress='").append(this.streetAddress).append('\'').append(", countryCode='").append(this.countryCode).append('\'').append(", id='").append(this.id).append('\'').append(", country='").append(this.country).append('\'').append(", placeType='").append(this.placeType).append('\'').append(", url='").append(this.url).append('\'').append(", fullName='").append(this.fullName).append('\'').append(", boundingBoxType='").append(this.boundingBoxType).append('\'').append(", boundingBoxCoordinates=");
        if (this.boundingBoxCoordinates == null) {
            obj = null;
        } else {
            obj = Arrays.asList(this.boundingBoxCoordinates);
        }
        append = append.append(obj).append(", geometryType='").append(this.geometryType).append('\'').append(", geometryCoordinates=");
        if (this.geometryCoordinates == null) {
            obj = null;
        } else {
            obj = Arrays.asList(this.geometryCoordinates);
        }
        StringBuilder append2 = append.append(obj).append(", containedWithIn=");
        if (this.containedWithIn != null) {
            obj2 = Arrays.asList(this.containedWithIn);
        }
        return append2.append(obj2).append('}').toString();
    }
}
