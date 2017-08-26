package twitter4j;

import com.tapjoy.TapjoyConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Query implements Serializable {
    public static final Unit KILOMETERS = Unit.km;
    public static final Unit MILES = Unit.mi;
    public static final ResultType MIXED = ResultType.mixed;
    public static final ResultType POPULAR = ResultType.popular;
    public static final ResultType RECENT = ResultType.recent;
    private static final HttpParameter WITH_TWITTER_USER_ID = new HttpParameter("with_twitter_user_id", TapjoyConstants.TJC_TRUE);
    private static final long serialVersionUID = 7196404519192910019L;
    private int count = -1;
    private String geocode = null;
    private String lang = null;
    private String locale = null;
    private long maxId = -1;
    private String nextPageQuery = null;
    private String query = null;
    private ResultType resultType = null;
    private String since = null;
    private long sinceId = -1;
    private String until = null;

    public enum ResultType {
        popular,
        mixed,
        recent
    }

    public enum Unit {
        mi,
        km
    }

    public Query(String str) {
        this.query = str;
    }

    static Query createWithNextPageQuery(String str) {
        Query query = new Query();
        query.nextPageQuery = str;
        return query;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String str) {
        this.query = str;
    }

    public Query query(String str) {
        setQuery(str);
        return this;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public Query lang(String str) {
        setLang(str);
        return this;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public Query locale(String str) {
        setLocale(str);
        return this;
    }

    public long getMaxId() {
        return this.maxId;
    }

    public void setMaxId(long j) {
        this.maxId = j;
    }

    public Query maxId(long j) {
        setMaxId(j);
        return this;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public Query count(int i) {
        setCount(i);
        return this;
    }

    public String getSince() {
        return this.since;
    }

    public void setSince(String str) {
        this.since = str;
    }

    public Query since(String str) {
        setSince(str);
        return this;
    }

    public long getSinceId() {
        return this.sinceId;
    }

    public void setSinceId(long j) {
        this.sinceId = j;
    }

    public Query sinceId(long j) {
        setSinceId(j);
        return this;
    }

    public String getGeocode() {
        return this.geocode;
    }

    public void setGeoCode(GeoLocation geoLocation, double d, Unit unit) {
        this.geocode = geoLocation.getLatitude() + "," + geoLocation.getLongitude() + "," + d + unit.name();
    }

    public void setGeoCode(GeoLocation geoLocation, double d, String str) {
        this.geocode = geoLocation.getLatitude() + "," + geoLocation.getLongitude() + "," + d + str;
    }

    public Query geoCode(GeoLocation geoLocation, double d, String str) {
        setGeoCode(geoLocation, d, str);
        return this;
    }

    public String getUntil() {
        return this.until;
    }

    public void setUntil(String str) {
        this.until = str;
    }

    public Query until(String str) {
        setUntil(str);
        return this;
    }

    public ResultType getResultType() {
        return this.resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public Query resultType(ResultType resultType) {
        setResultType(resultType);
        return this;
    }

    HttpParameter[] asHttpParameterArray() {
        List arrayList = new ArrayList(12);
        appendParameter("q", this.query, arrayList);
        appendParameter("lang", this.lang, arrayList);
        appendParameter("locale", this.locale, arrayList);
        appendParameter("max_id", this.maxId, arrayList);
        appendParameter("count", (long) this.count, arrayList);
        appendParameter("since", this.since, arrayList);
        appendParameter("since_id", this.sinceId, arrayList);
        appendParameter("geocode", this.geocode, arrayList);
        appendParameter("until", this.until, arrayList);
        if (this.resultType != null) {
            arrayList.add(new HttpParameter("result_type", this.resultType.name()));
        }
        arrayList.add(WITH_TWITTER_USER_ID);
        return (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()]);
    }

    private void appendParameter(String str, String str2, List<HttpParameter> list) {
        if (str2 != null) {
            list.add(new HttpParameter(str, str2));
        }
    }

    private void appendParameter(String str, long j, List<HttpParameter> list) {
        if (0 <= j) {
            list.add(new HttpParameter(str, String.valueOf(j)));
        }
    }

    String nextPage() {
        return this.nextPageQuery;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Query query = (Query) obj;
        if (this.maxId != query.maxId) {
            return false;
        }
        if (this.count != query.count) {
            return false;
        }
        if (this.sinceId != query.sinceId) {
            return false;
        }
        if (this.geocode == null ? query.geocode != null : !this.geocode.equals(query.geocode)) {
            return false;
        }
        if (this.lang == null ? query.lang != null : !this.lang.equals(query.lang)) {
            return false;
        }
        if (this.locale == null ? query.locale != null : !this.locale.equals(query.locale)) {
            return false;
        }
        if (this.nextPageQuery == null ? query.nextPageQuery != null : !this.nextPageQuery.equals(query.nextPageQuery)) {
            return false;
        }
        if (this.query == null ? query.query != null : !this.query.equals(query.query)) {
            return false;
        }
        if (this.resultType == null ? query.resultType != null : !this.resultType.equals(query.resultType)) {
            return false;
        }
        if (this.since == null ? query.since != null : !this.since.equals(query.since)) {
            return false;
        }
        if (this.until != null) {
            if (this.until.equals(query.until)) {
                return true;
            }
        } else if (query.until == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = (this.query != null ? this.query.hashCode() : 0) * 31;
        if (this.lang != null) {
            hashCode = this.lang.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.locale != null) {
            hashCode = this.locale.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (((((hashCode + hashCode2) * 31) + ((int) (this.maxId ^ (this.maxId >>> 32)))) * 31) + this.count) * 31;
        if (this.since != null) {
            hashCode = this.since.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (((hashCode + hashCode2) * 31) + ((int) (this.sinceId ^ (this.sinceId >>> 32)))) * 31;
        if (this.geocode != null) {
            hashCode = this.geocode.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.until != null) {
            hashCode = this.until.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.resultType != null) {
            hashCode = this.resultType.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.nextPageQuery != null) {
            i = this.nextPageQuery.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "Query{query='" + this.query + '\'' + ", lang='" + this.lang + '\'' + ", locale='" + this.locale + '\'' + ", maxId=" + this.maxId + ", count=" + this.count + ", since='" + this.since + '\'' + ", sinceId=" + this.sinceId + ", geocode='" + this.geocode + '\'' + ", until='" + this.until + '\'' + ", resultType='" + this.resultType + '\'' + ", nextPageQuery='" + this.nextPageQuery + '\'' + '}';
    }
}
