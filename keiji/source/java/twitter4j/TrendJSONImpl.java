package twitter4j;

import com.tapjoy.TJAdUnitConstants.String;
import com.unity3d.ads.metadata.MediationMetaData;
import java.io.Serializable;

final class TrendJSONImpl implements Serializable, Trend {
    private static final long serialVersionUID = -4353426776065521132L;
    private final String name;
    private String query;
    private String url;

    TrendJSONImpl(JSONObject jSONObject, boolean z) {
        this.url = null;
        this.query = null;
        this.name = ParseUtil.getRawString(MediationMetaData.KEY_NAME, jSONObject);
        this.url = ParseUtil.getRawString(String.URL, jSONObject);
        this.query = ParseUtil.getRawString("query", jSONObject);
        if (z) {
            TwitterObjectFactory.registerJSONObject(this, jSONObject);
        }
    }

    TrendJSONImpl(JSONObject jSONObject) {
        this(jSONObject, false);
    }

    public String getName() {
        return this.name;
    }

    public String getURL() {
        return this.url;
    }

    public String getQuery() {
        return this.query;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Trend)) {
            return false;
        }
        Trend trend = (Trend) obj;
        if (!this.name.equals(trend.getName())) {
            return false;
        }
        if (this.query == null ? trend.getQuery() != null : !this.query.equals(trend.getQuery())) {
            return false;
        }
        if (this.url != null) {
            if (this.url.equals(trend.getURL())) {
                return true;
            }
        } else if (trend.getURL() == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = this.name.hashCode() * 31;
        if (this.url != null) {
            hashCode = this.url.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.query != null) {
            i = this.query.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "TrendJSONImpl{name='" + this.name + '\'' + ", url='" + this.url + '\'' + ", query='" + this.query + '\'' + '}';
    }
}
