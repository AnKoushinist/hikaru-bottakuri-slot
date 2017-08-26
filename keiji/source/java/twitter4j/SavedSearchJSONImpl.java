package twitter4j;

import com.unity3d.ads.metadata.MediationMetaData;
import java.util.Date;
import twitter4j.conf.Configuration;

final class SavedSearchJSONImpl extends TwitterResponseImpl implements SavedSearch {
    private static final long serialVersionUID = 846086437256360810L;
    private Date createdAt;
    private int id;
    private String name;
    private int position;
    private String query;

    SavedSearchJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
        }
        JSONObject asJSONObject = httpResponse.asJSONObject();
        init(asJSONObject);
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.registerJSONObject(this, asJSONObject);
        }
    }

    SavedSearchJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    static ResponseList<SavedSearch> createSavedSearchList(HttpResponse httpResponse, Configuration configuration) {
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
        }
        JSONArray asJSONArray = httpResponse.asJSONArray();
        try {
            ResponseList<SavedSearch> responseListImpl = new ResponseListImpl(asJSONArray.length(), httpResponse);
            for (int i = 0; i < asJSONArray.length(); i++) {
                JSONObject jSONObject = asJSONArray.getJSONObject(i);
                SavedSearchJSONImpl savedSearchJSONImpl = new SavedSearchJSONImpl(jSONObject);
                responseListImpl.add(savedSearchJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(savedSearchJSONImpl, jSONObject);
                }
            }
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.registerJSONObject(responseListImpl, asJSONArray);
            }
            return responseListImpl;
        } catch (Throwable e) {
            throw new TwitterException(e.getMessage() + ":" + httpResponse.asString(), e);
        }
    }

    private void init(JSONObject jSONObject) {
        this.createdAt = ParseUtil.getDate("created_at", jSONObject, "EEE MMM dd HH:mm:ss z yyyy");
        this.query = ParseUtil.getUnescapedString("query", jSONObject);
        this.position = ParseUtil.getInt("position", jSONObject);
        this.name = ParseUtil.getUnescapedString(MediationMetaData.KEY_NAME, jSONObject);
        this.id = ParseUtil.getInt("id", jSONObject);
    }

    public int compareTo(SavedSearch savedSearch) {
        return this.id - savedSearch.getId();
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public String getQuery() {
        return this.query;
    }

    public int getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SavedSearch)) {
            return false;
        }
        if (this.id != ((SavedSearch) obj).getId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((this.createdAt.hashCode() * 31) + this.query.hashCode()) * 31) + this.position) * 31) + this.name.hashCode()) * 31) + this.id;
    }

    public String toString() {
        return "SavedSearchJSONImpl{createdAt=" + this.createdAt + ", query='" + this.query + '\'' + ", position=" + this.position + ", name='" + this.name + '\'' + ", id=" + this.id + '}';
    }
}
