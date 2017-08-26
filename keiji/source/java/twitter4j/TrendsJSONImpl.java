package twitter4j;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import twitter4j.conf.Configuration;

final class TrendsJSONImpl extends TwitterResponseImpl implements Serializable, Trends {
    private static final long serialVersionUID = 2054973282133379835L;
    private Date asOf;
    private Location location;
    private Date trendAt;
    private Trend[] trends;

    public int compareTo(Trends trends) {
        return this.trendAt.compareTo(trends.getTrendAt());
    }

    TrendsJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        init(httpResponse.asString(), configuration.isJSONStoreEnabled());
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(this, httpResponse.asString());
        }
    }

    TrendsJSONImpl(String str) {
        this(str, false);
    }

    TrendsJSONImpl(String str, boolean z) {
        init(str, z);
    }

    void init(String str, boolean z) {
        try {
            JSONArray jSONArray;
            JSONObject jSONObject;
            if (str.startsWith("[")) {
                jSONArray = new JSONArray(str);
                if (jSONArray.length() > 0) {
                    jSONObject = jSONArray.getJSONObject(0);
                } else {
                    throw new TwitterException("No trends found on the specified woeid");
                }
            }
            jSONObject = new JSONObject(str);
            this.asOf = ParseUtil.parseTrendsDate(jSONObject.getString("as_of"));
            this.location = extractLocation(jSONObject, z);
            jSONArray = jSONObject.getJSONArray("trends");
            this.trendAt = this.asOf;
            this.trends = jsonArrayToTrendArray(jSONArray, z);
        } catch (Throwable e) {
            throw new TwitterException(e.getMessage(), e);
        }
    }

    TrendsJSONImpl(Date date, Location location, Date date2, Trend[] trendArr) {
        this.asOf = date;
        this.location = location;
        this.trendAt = date2;
        this.trends = trendArr;
    }

    static ResponseList<Trends> createTrendsList(HttpResponse httpResponse, boolean z) {
        JSONObject asJSONObject = httpResponse.asJSONObject();
        try {
            Date parseTrendsDate = ParseUtil.parseTrendsDate(asJSONObject.getString("as_of"));
            JSONObject jSONObject = asJSONObject.getJSONObject("trends");
            Location extractLocation = extractLocation(asJSONObject, z);
            Object responseListImpl = new ResponseListImpl(jSONObject.length(), httpResponse);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                Trend[] jsonArrayToTrendArray = jsonArrayToTrendArray(jSONObject.getJSONArray(str), z);
                if (str.length() == 19) {
                    responseListImpl.add(new TrendsJSONImpl(parseTrendsDate, extractLocation, ParseUtil.getDate(str, "yyyy-MM-dd HH:mm:ss"), jsonArrayToTrendArray));
                } else if (str.length() == 16) {
                    responseListImpl.add(new TrendsJSONImpl(parseTrendsDate, extractLocation, ParseUtil.getDate(str, "yyyy-MM-dd HH:mm"), jsonArrayToTrendArray));
                } else if (str.length() == 10) {
                    responseListImpl.add(new TrendsJSONImpl(parseTrendsDate, extractLocation, ParseUtil.getDate(str, "yyyy-MM-dd"), jsonArrayToTrendArray));
                }
            }
            Collections.sort(responseListImpl);
            return responseListImpl;
        } catch (Throwable e) {
            throw new TwitterException(e.getMessage() + ":" + httpResponse.asString(), e);
        }
    }

    private static Location extractLocation(JSONObject jSONObject, boolean z) {
        if (jSONObject.isNull("locations")) {
            return null;
        }
        try {
            ResponseList createLocationList = LocationJSONImpl.createLocationList(jSONObject.getJSONArray("locations"), z);
            if (createLocationList.size() != 0) {
                return (Location) createLocationList.get(0);
            }
            return null;
        } catch (JSONException e) {
            throw new AssertionError("locations can't be null");
        }
    }

    private static Trend[] jsonArrayToTrendArray(JSONArray jSONArray, boolean z) {
        Trend[] trendArr = new Trend[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            trendArr[i] = new TrendJSONImpl(jSONArray.getJSONObject(i), z);
        }
        return trendArr;
    }

    public Trend[] getTrends() {
        return this.trends;
    }

    public Location getLocation() {
        return this.location;
    }

    public Date getAsOf() {
        return this.asOf;
    }

    public Date getTrendAt() {
        return this.trendAt;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Trends)) {
            return false;
        }
        Trends trends = (Trends) obj;
        if (this.asOf == null ? trends.getAsOf() != null : !this.asOf.equals(trends.getAsOf())) {
            return false;
        }
        if (this.trendAt == null ? trends.getTrendAt() != null : !this.trendAt.equals(trends.getTrendAt())) {
            return false;
        }
        if (Arrays.equals(this.trends, trends.getTrends())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.asOf != null) {
            hashCode = this.asOf.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.trendAt != null) {
            hashCode = this.trendAt.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.trends != null) {
            i = Arrays.hashCode(this.trends);
        }
        return hashCode + i;
    }

    public String toString() {
        Object obj;
        StringBuilder append = new StringBuilder().append("TrendsJSONImpl{asOf=").append(this.asOf).append(", trendAt=").append(this.trendAt).append(", trends=");
        if (this.trends == null) {
            obj = null;
        } else {
            obj = Arrays.asList(this.trends);
        }
        return append.append(obj).append('}').toString();
    }
}
