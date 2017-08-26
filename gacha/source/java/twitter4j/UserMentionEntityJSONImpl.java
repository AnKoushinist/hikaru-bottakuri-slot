package twitter4j;

import com.unity3d.ads.metadata.MediationMetaData;

class UserMentionEntityJSONImpl extends EntityIndex implements UserMentionEntity {
    private static final long serialVersionUID = 6060510953676673013L;
    private long id;
    private String name;
    private String screenName;

    UserMentionEntityJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    UserMentionEntityJSONImpl(int i, int i2, String str, String str2, long j) {
        setStart(i);
        setEnd(i2);
        this.name = str;
        this.screenName = str2;
        this.id = j;
    }

    UserMentionEntityJSONImpl() {
    }

    private void init(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("indices");
            setStart(jSONArray.getInt(0));
            setEnd(jSONArray.getInt(1));
            if (!jSONObject.isNull(MediationMetaData.KEY_NAME)) {
                this.name = jSONObject.getString(MediationMetaData.KEY_NAME);
            }
            if (!jSONObject.isNull("screen_name")) {
                this.screenName = jSONObject.getString("screen_name");
            }
            this.id = ParseUtil.getLong("id", jSONObject);
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public String getText() {
        return this.screenName;
    }

    public String getName() {
        return this.name;
    }

    public String getScreenName() {
        return this.screenName;
    }

    public long getId() {
        return this.id;
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
        UserMentionEntityJSONImpl userMentionEntityJSONImpl = (UserMentionEntityJSONImpl) obj;
        if (this.id != userMentionEntityJSONImpl.id) {
            return false;
        }
        if (this.name == null ? userMentionEntityJSONImpl.name != null : !this.name.equals(userMentionEntityJSONImpl.name)) {
            return false;
        }
        if (this.screenName != null) {
            if (this.screenName.equals(userMentionEntityJSONImpl.screenName)) {
                return true;
            }
        } else if (userMentionEntityJSONImpl.screenName == null) {
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
        if (this.screenName != null) {
            i = this.screenName.hashCode();
        }
        return ((hashCode + i) * 31) + ((int) (this.id ^ (this.id >>> 32)));
    }

    public String toString() {
        return "UserMentionEntityJSONImpl{name='" + this.name + '\'' + ", screenName='" + this.screenName + '\'' + ", id=" + this.id + '}';
    }
}
