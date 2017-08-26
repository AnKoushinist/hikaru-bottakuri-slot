package twitter4j;

import com.tapjoy.TJAdUnitConstants.String;

final class URLEntityJSONImpl extends EntityIndex implements URLEntity {
    private static final long serialVersionUID = 7333552738058031524L;
    private String displayURL;
    private String expandedURL;
    private String url;

    URLEntityJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    URLEntityJSONImpl(int i, int i2, String str, String str2, String str3) {
        setStart(i);
        setEnd(i2);
        this.url = str;
        this.expandedURL = str2;
        this.displayURL = str3;
    }

    URLEntityJSONImpl() {
    }

    private void init(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("indices");
            setStart(jSONArray.getInt(0));
            setEnd(jSONArray.getInt(1));
            this.url = jSONObject.getString(String.URL);
            if (jSONObject.isNull("expanded_url")) {
                this.expandedURL = this.url;
            } else {
                this.expandedURL = jSONObject.getString("expanded_url");
            }
            if (jSONObject.isNull("display_url")) {
                this.displayURL = this.url;
            } else {
                this.displayURL = jSONObject.getString("display_url");
            }
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public String getText() {
        return this.url;
    }

    public String getURL() {
        return this.url;
    }

    public String getExpandedURL() {
        return this.expandedURL;
    }

    public String getDisplayURL() {
        return this.displayURL;
    }

    public int getStart() {
        return super.getStart();
    }

    public int getEnd() {
        return super.getEnd();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        URLEntityJSONImpl uRLEntityJSONImpl = (URLEntityJSONImpl) obj;
        if (this.displayURL == null ? uRLEntityJSONImpl.displayURL != null : !this.displayURL.equals(uRLEntityJSONImpl.displayURL)) {
            return false;
        }
        if (this.expandedURL == null ? uRLEntityJSONImpl.expandedURL != null : !this.expandedURL.equals(uRLEntityJSONImpl.expandedURL)) {
            return false;
        }
        if (this.url != null) {
            if (this.url.equals(uRLEntityJSONImpl.url)) {
                return true;
            }
        } else if (uRLEntityJSONImpl.url == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.url != null) {
            hashCode = this.url.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.expandedURL != null) {
            hashCode = this.expandedURL.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.displayURL != null) {
            i = this.displayURL.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "URLEntityJSONImpl{url='" + this.url + '\'' + ", expandedURL='" + this.expandedURL + '\'' + ", displayURL='" + this.displayURL + '\'' + '}';
    }
}
