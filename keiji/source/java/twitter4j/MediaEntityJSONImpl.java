package twitter4j;

import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.HashMap;
import java.util.Map;

public class MediaEntityJSONImpl extends EntityIndex implements MediaEntity {
    private static final long serialVersionUID = 3609683338035442290L;
    private String displayURL;
    private String expandedURL;
    private long id;
    private String mediaURL;
    private String mediaURLHttps;
    private Map<Integer, twitter4j.MediaEntity.Size> sizes;
    private String type;
    private String url;

    static class Size implements twitter4j.MediaEntity.Size {
        private static final long serialVersionUID = -2515842281909325169L;
        int height;
        int resize;
        int width;

        Size(JSONObject jSONObject) {
            this.width = jSONObject.getInt("w");
            this.height = jSONObject.getInt("h");
            this.resize = "fit".equals(jSONObject.getString("resize")) ? 100 : twitter4j.MediaEntity.Size.CROP;
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

        public int getResize() {
            return this.resize;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Size)) {
                return false;
            }
            Size size = (Size) obj;
            if (this.height != size.height) {
                return false;
            }
            if (this.resize != size.resize) {
                return false;
            }
            if (this.width != size.width) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.width * 31) + this.height) * 31) + this.resize;
        }

        public String toString() {
            return "Size{width=" + this.width + ", height=" + this.height + ", resize=" + this.resize + '}';
        }
    }

    public /* bridge */ /* synthetic */ int compareTo(EntityIndex entityIndex) {
        return super.compareTo(entityIndex);
    }

    MediaEntityJSONImpl(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("indices");
            setStart(jSONArray.getInt(0));
            setEnd(jSONArray.getInt(1));
            this.id = ParseUtil.getLong("id", jSONObject);
            this.url = jSONObject.getString(String.URL);
            this.expandedURL = jSONObject.getString("expanded_url");
            this.mediaURL = jSONObject.getString("media_url");
            this.mediaURLHttps = jSONObject.getString("media_url_https");
            this.displayURL = jSONObject.getString("display_url");
            JSONObject jSONObject2 = jSONObject.getJSONObject("sizes");
            this.sizes = new HashMap(4);
            addMediaEntitySizeIfNotNull(this.sizes, jSONObject2, twitter4j.MediaEntity.Size.LARGE, "large");
            addMediaEntitySizeIfNotNull(this.sizes, jSONObject2, twitter4j.MediaEntity.Size.MEDIUM, "medium");
            addMediaEntitySizeIfNotNull(this.sizes, jSONObject2, twitter4j.MediaEntity.Size.SMALL, "small");
            addMediaEntitySizeIfNotNull(this.sizes, jSONObject2, twitter4j.MediaEntity.Size.THUMB, "thumb");
            if (!jSONObject.isNull(MoatAdEvent.EVENT_TYPE)) {
                this.type = jSONObject.getString(MoatAdEvent.EVENT_TYPE);
            }
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    private void addMediaEntitySizeIfNotNull(Map<Integer, twitter4j.MediaEntity.Size> map, JSONObject jSONObject, Integer num, String str) {
        if (!jSONObject.isNull(str)) {
            map.put(num, new Size(jSONObject.getJSONObject(str)));
        }
    }

    MediaEntityJSONImpl() {
    }

    public long getId() {
        return this.id;
    }

    public String getMediaURL() {
        return this.mediaURL;
    }

    public String getMediaURLHttps() {
        return this.mediaURLHttps;
    }

    public String getText() {
        return this.url;
    }

    public String getURL() {
        return this.url;
    }

    public String getDisplayURL() {
        return this.displayURL;
    }

    public String getExpandedURL() {
        return this.expandedURL;
    }

    public Map<Integer, twitter4j.MediaEntity.Size> getSizes() {
        return this.sizes;
    }

    public String getType() {
        return this.type;
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
        if (!(obj instanceof MediaEntityJSONImpl)) {
            return false;
        }
        if (this.id != ((MediaEntityJSONImpl) obj).id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.id ^ (this.id >>> 32));
    }

    public String toString() {
        return "MediaEntityJSONImpl{id=" + this.id + ", url=" + this.url + ", mediaURL=" + this.mediaURL + ", mediaURLHttps=" + this.mediaURLHttps + ", expandedURL=" + this.expandedURL + ", displayURL='" + this.displayURL + '\'' + ", sizes=" + this.sizes + ", type=" + this.type + '}';
    }
}
