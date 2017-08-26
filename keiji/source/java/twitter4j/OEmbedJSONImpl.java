package twitter4j;

import com.tapjoy.TJAdUnitConstants.String;
import com.unity3d.ads.metadata.MediationMetaData;
import java.io.Serializable;
import twitter4j.conf.Configuration;

public class OEmbedJSONImpl extends TwitterResponseImpl implements Serializable, OEmbed {
    private static final long serialVersionUID = -2207801480251709819L;
    private String authorName;
    private String authorURL;
    private long cacheAge;
    private String html;
    private String url;
    private String version;
    private int width;

    public /* bridge */ /* synthetic */ int getAccessLevel() {
        return super.getAccessLevel();
    }

    public /* bridge */ /* synthetic */ RateLimitStatus getRateLimitStatus() {
        return super.getRateLimitStatus();
    }

    OEmbedJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        JSONObject asJSONObject = httpResponse.asJSONObject();
        init(asJSONObject);
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(this, asJSONObject);
        }
    }

    OEmbedJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    private void init(JSONObject jSONObject) {
        try {
            this.html = jSONObject.getString(String.HTML);
            this.authorName = jSONObject.getString("author_name");
            this.url = jSONObject.getString(String.URL);
            this.version = jSONObject.getString(MediationMetaData.KEY_VERSION);
            this.cacheAge = jSONObject.getLong("cache_age");
            this.authorURL = jSONObject.getString("author_url");
            this.width = jSONObject.getInt("width");
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public String getHtml() {
        return this.html;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public String getURL() {
        return this.url;
    }

    public String getVersion() {
        return this.version;
    }

    public long getCacheAge() {
        return this.cacheAge;
    }

    public String getAuthorURL() {
        return this.authorURL;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OEmbedJSONImpl oEmbedJSONImpl = (OEmbedJSONImpl) obj;
        if (this.cacheAge != oEmbedJSONImpl.cacheAge) {
            return false;
        }
        if (this.width != oEmbedJSONImpl.width) {
            return false;
        }
        if (this.authorName == null ? oEmbedJSONImpl.authorName != null : !this.authorName.equals(oEmbedJSONImpl.authorName)) {
            return false;
        }
        if (this.authorURL == null ? oEmbedJSONImpl.authorURL != null : !this.authorURL.equals(oEmbedJSONImpl.authorURL)) {
            return false;
        }
        if (this.html == null ? oEmbedJSONImpl.html != null : !this.html.equals(oEmbedJSONImpl.html)) {
            return false;
        }
        if (this.url == null ? oEmbedJSONImpl.url != null : !this.url.equals(oEmbedJSONImpl.url)) {
            return false;
        }
        if (this.version != null) {
            if (this.version.equals(oEmbedJSONImpl.version)) {
                return true;
            }
        } else if (oEmbedJSONImpl.version == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = (this.html != null ? this.html.hashCode() : 0) * 31;
        if (this.authorName != null) {
            hashCode = this.authorName.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.url != null) {
            hashCode = this.url.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.version != null) {
            hashCode = this.version.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (((hashCode + hashCode2) * 31) + ((int) (this.cacheAge ^ (this.cacheAge >>> 32)))) * 31;
        if (this.authorURL != null) {
            i = this.authorURL.hashCode();
        }
        return ((hashCode + i) * 31) + this.width;
    }

    public String toString() {
        return "OEmbedJSONImpl{html='" + this.html + '\'' + ", authorName='" + this.authorName + '\'' + ", url='" + this.url + '\'' + ", version='" + this.version + '\'' + ", cacheAge=" + this.cacheAge + ", authorURL='" + this.authorURL + '\'' + ", width=" + this.width + '}';
    }
}
