package twitter4j;

import java.io.Serializable;

public final class UploadedMedia implements Serializable {
    private static final long serialVersionUID = 5393092535610604718L;
    private int imageHeight;
    private String imageType;
    private int imageWidth;
    private long mediaId;
    private long size;

    UploadedMedia(JSONObject jSONObject) {
        init(jSONObject);
    }

    public int getImageWidth() {
        return this.imageWidth;
    }

    public int getImageHeight() {
        return this.imageHeight;
    }

    public String getImageType() {
        return this.imageType;
    }

    public long getMediaId() {
        return this.mediaId;
    }

    public long getSize() {
        return this.size;
    }

    private void init(JSONObject jSONObject) {
        this.mediaId = ParseUtil.getLong("media_id", jSONObject);
        this.size = ParseUtil.getLong("size", jSONObject);
        try {
            if (!jSONObject.isNull("image")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("image");
                this.imageWidth = ParseUtil.getInt("w", jSONObject2);
                this.imageHeight = ParseUtil.getInt("h", jSONObject2);
                this.imageType = ParseUtil.getUnescapedString("image_type", jSONObject2);
            }
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UploadedMedia uploadedMedia = (UploadedMedia) obj;
        if (this.imageWidth != uploadedMedia.imageWidth) {
            return false;
        }
        if (this.imageHeight != uploadedMedia.imageHeight) {
            return false;
        }
        if (this.imageType != uploadedMedia.imageType) {
            return false;
        }
        if (this.mediaId != uploadedMedia.mediaId) {
            return false;
        }
        if (this.size != uploadedMedia.size) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((this.imageType != null ? this.imageType.hashCode() : 0) + (((((((int) (this.mediaId ^ (this.mediaId >>> 32))) * 31) + this.imageWidth) * 31) + this.imageHeight) * 31)) * 31) + ((int) (this.size ^ (this.size >>> 32)));
    }

    public String toString() {
        return "UploadedMedia{mediaId=" + this.mediaId + ", imageWidth=" + this.imageWidth + ", imageHeight=" + this.imageHeight + ", imageType='" + this.imageType + '\'' + ", size=" + this.size + '}';
    }
}
