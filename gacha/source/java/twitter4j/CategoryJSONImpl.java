package twitter4j;

import com.unity3d.ads.metadata.MediationMetaData;
import java.io.Serializable;
import twitter4j.conf.Configuration;

final class CategoryJSONImpl implements Serializable, Category {
    private static final long serialVersionUID = 3811335888122469876L;
    private String name;
    private int size;
    private String slug;

    CategoryJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    void init(JSONObject jSONObject) {
        this.name = jSONObject.getString(MediationMetaData.KEY_NAME);
        this.slug = jSONObject.getString("slug");
        this.size = ParseUtil.getInt("size", jSONObject);
    }

    static ResponseList<Category> createCategoriesList(HttpResponse httpResponse, Configuration configuration) {
        return createCategoriesList(httpResponse.asJSONArray(), httpResponse, configuration);
    }

    static ResponseList<Category> createCategoriesList(JSONArray jSONArray, HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
            }
            ResponseList<Category> responseListImpl = new ResponseListImpl(jSONArray.length(), httpResponse);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                CategoryJSONImpl categoryJSONImpl = new CategoryJSONImpl(jSONObject);
                responseListImpl.add(categoryJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(categoryJSONImpl, jSONObject);
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

    public String getSlug() {
        return this.slug;
    }

    public int getSize() {
        return this.size;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CategoryJSONImpl categoryJSONImpl = (CategoryJSONImpl) obj;
        if (this.size != categoryJSONImpl.size) {
            return false;
        }
        if (this.name == null ? categoryJSONImpl.name != null : !this.name.equals(categoryJSONImpl.name)) {
            return false;
        }
        if (this.slug != null) {
            if (this.slug.equals(categoryJSONImpl.slug)) {
                return true;
            }
        } else if (categoryJSONImpl.slug == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.name != null) {
            hashCode = this.name.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode *= 31;
        if (this.slug != null) {
            i = this.slug.hashCode();
        }
        return ((hashCode + i) * 31) + this.size;
    }

    public String toString() {
        return "CategoryJSONImpl{name='" + this.name + '\'' + ", slug='" + this.slug + '\'' + ", size=" + this.size + '}';
    }
}
