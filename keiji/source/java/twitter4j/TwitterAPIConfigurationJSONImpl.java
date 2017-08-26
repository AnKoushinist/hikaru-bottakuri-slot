package twitter4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import twitter4j.MediaEntity.Size;
import twitter4j.conf.Configuration;

class TwitterAPIConfigurationJSONImpl extends TwitterResponseImpl implements TwitterAPIConfiguration {
    private static final long serialVersionUID = -3588904550808591686L;
    private int charactersReservedPerMedia;
    private int maxMediaPerUpload;
    private String[] nonUsernamePaths;
    private int photoSizeLimit;
    private Map<Integer, Size> photoSizes;
    private int shortURLLength;
    private int shortURLLengthHttps;

    TwitterAPIConfigurationJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        try {
            JSONObject jSONObject;
            JSONObject asJSONObject = httpResponse.asJSONObject();
            this.photoSizeLimit = ParseUtil.getInt("photo_size_limit", asJSONObject);
            this.shortURLLength = ParseUtil.getInt("short_url_length", asJSONObject);
            this.shortURLLengthHttps = ParseUtil.getInt("short_url_length_https", asJSONObject);
            this.charactersReservedPerMedia = ParseUtil.getInt("characters_reserved_per_media", asJSONObject);
            JSONObject jSONObject2 = asJSONObject.getJSONObject("photo_sizes");
            this.photoSizes = new HashMap(4);
            this.photoSizes.put(Size.LARGE, new Size(jSONObject2.getJSONObject("large")));
            if (jSONObject2.isNull("med")) {
                jSONObject = jSONObject2.getJSONObject("medium");
            } else {
                jSONObject = jSONObject2.getJSONObject("med");
            }
            this.photoSizes.put(Size.MEDIUM, new Size(jSONObject));
            this.photoSizes.put(Size.SMALL, new Size(jSONObject2.getJSONObject("small")));
            this.photoSizes.put(Size.THUMB, new Size(jSONObject2.getJSONObject("thumb")));
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
                TwitterObjectFactory.registerJSONObject(this, httpResponse.asJSONObject());
            }
            JSONArray jSONArray = asJSONObject.getJSONArray("non_username_paths");
            this.nonUsernamePaths = new String[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                this.nonUsernamePaths[i] = jSONArray.getString(i);
            }
            this.maxMediaPerUpload = ParseUtil.getInt("max_media_per_upload", asJSONObject);
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public int getPhotoSizeLimit() {
        return this.photoSizeLimit;
    }

    public int getShortURLLength() {
        return this.shortURLLength;
    }

    public int getShortURLLengthHttps() {
        return this.shortURLLengthHttps;
    }

    public int getCharactersReservedPerMedia() {
        return this.charactersReservedPerMedia;
    }

    public Map<Integer, Size> getPhotoSizes() {
        return this.photoSizes;
    }

    public String[] getNonUsernamePaths() {
        return this.nonUsernamePaths;
    }

    public int getMaxMediaPerUpload() {
        return this.maxMediaPerUpload;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TwitterAPIConfigurationJSONImpl)) {
            return false;
        }
        TwitterAPIConfigurationJSONImpl twitterAPIConfigurationJSONImpl = (TwitterAPIConfigurationJSONImpl) obj;
        if (this.charactersReservedPerMedia != twitterAPIConfigurationJSONImpl.charactersReservedPerMedia) {
            return false;
        }
        if (this.maxMediaPerUpload != twitterAPIConfigurationJSONImpl.maxMediaPerUpload) {
            return false;
        }
        if (this.photoSizeLimit != twitterAPIConfigurationJSONImpl.photoSizeLimit) {
            return false;
        }
        if (this.shortURLLength != twitterAPIConfigurationJSONImpl.shortURLLength) {
            return false;
        }
        if (this.shortURLLengthHttps != twitterAPIConfigurationJSONImpl.shortURLLengthHttps) {
            return false;
        }
        if (!Arrays.equals(this.nonUsernamePaths, twitterAPIConfigurationJSONImpl.nonUsernamePaths)) {
            return false;
        }
        if (this.photoSizes != null) {
            if (this.photoSizes.equals(twitterAPIConfigurationJSONImpl.photoSizes)) {
                return true;
            }
        } else if (twitterAPIConfigurationJSONImpl.photoSizes == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int i2 = ((((((this.photoSizeLimit * 31) + this.shortURLLength) * 31) + this.shortURLLengthHttps) * 31) + this.charactersReservedPerMedia) * 31;
        if (this.photoSizes != null) {
            hashCode = this.photoSizes.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.nonUsernamePaths != null) {
            i = Arrays.hashCode(this.nonUsernamePaths);
        }
        return ((hashCode + i) * 31) + this.maxMediaPerUpload;
    }

    public String toString() {
        Object obj;
        StringBuilder append = new StringBuilder().append("TwitterAPIConfigurationJSONImpl{photoSizeLimit=").append(this.photoSizeLimit).append(", shortURLLength=").append(this.shortURLLength).append(", shortURLLengthHttps=").append(this.shortURLLengthHttps).append(", charactersReservedPerMedia=").append(this.charactersReservedPerMedia).append(", photoSizes=").append(this.photoSizes).append(", nonUsernamePaths=");
        if (this.nonUsernamePaths == null) {
            obj = null;
        } else {
            obj = Arrays.asList(this.nonUsernamePaths);
        }
        return append.append(obj).append(", maxMediaPerUpload=").append(this.maxMediaPerUpload).append('}').toString();
    }
}
