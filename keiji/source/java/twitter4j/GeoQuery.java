package twitter4j;

import com.tapjoy.TJAdUnitConstants.String;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class GeoQuery implements Serializable {
    private static final long serialVersionUID = 5434503339001056634L;
    private String accuracy = null;
    private String granularity = null;
    private String ip = null;
    private GeoLocation location;
    private int maxResults = -1;
    private String query = null;

    public GeoQuery(GeoLocation geoLocation) {
        this.location = geoLocation;
    }

    public GeoQuery(String str) {
        this.ip = str;
    }

    public GeoLocation getLocation() {
        return this.location;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String str) {
        this.query = str;
    }

    public String getIp() {
        return this.ip;
    }

    public String getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(String str) {
        this.accuracy = str;
    }

    public GeoQuery accuracy(String str) {
        setAccuracy(str);
        return this;
    }

    public String getGranularity() {
        return this.granularity;
    }

    public void setGranularity(String str) {
        this.granularity = str;
    }

    public GeoQuery granularity(String str) {
        setGranularity(str);
        return this;
    }

    public int getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(int i) {
        this.maxResults = i;
    }

    public GeoQuery maxResults(int i) {
        setMaxResults(i);
        return this;
    }

    HttpParameter[] asHttpParameterArray() {
        List arrayList = new ArrayList();
        if (this.location != null) {
            appendParameter(String.LAT, this.location.getLatitude(), arrayList);
            appendParameter(String.LONG, this.location.getLongitude(), arrayList);
        }
        if (this.ip != null) {
            appendParameter("ip", this.ip, arrayList);
        }
        appendParameter("accuracy", this.accuracy, arrayList);
        appendParameter("query", this.query, arrayList);
        appendParameter("granularity", this.granularity, arrayList);
        appendParameter("max_results", this.maxResults, arrayList);
        return (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()]);
    }

    private void appendParameter(String str, String str2, List<HttpParameter> list) {
        if (str2 != null) {
            list.add(new HttpParameter(str, str2));
        }
    }

    private void appendParameter(String str, int i, List<HttpParameter> list) {
        if (i > 0) {
            list.add(new HttpParameter(str, String.valueOf(i)));
        }
    }

    private void appendParameter(String str, double d, List<HttpParameter> list) {
        list.add(new HttpParameter(str, String.valueOf(d)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GeoQuery geoQuery = (GeoQuery) obj;
        if (this.maxResults != geoQuery.maxResults) {
            return false;
        }
        if (this.accuracy == null ? geoQuery.accuracy != null : !this.accuracy.equals(geoQuery.accuracy)) {
            return false;
        }
        if (this.granularity == null ? geoQuery.granularity != null : !this.granularity.equals(geoQuery.granularity)) {
            return false;
        }
        if (this.ip == null ? geoQuery.ip != null : !this.ip.equals(geoQuery.ip)) {
            return false;
        }
        if (this.location != null) {
            if (this.location.equals(geoQuery.location)) {
                return true;
            }
        } else if (geoQuery.location == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = (this.location != null ? this.location.hashCode() : 0) * 31;
        if (this.ip != null) {
            hashCode = this.ip.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.accuracy != null) {
            hashCode = this.accuracy.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.granularity != null) {
            i = this.granularity.hashCode();
        }
        return ((hashCode + i) * 31) + this.maxResults;
    }

    public String toString() {
        return "GeoQuery{location=" + this.location + ", query='" + this.query + '\'' + ", ip='" + this.ip + '\'' + ", accuracy='" + this.accuracy + '\'' + ", granularity='" + this.granularity + '\'' + ", maxResults=" + this.maxResults + '}';
    }
}
