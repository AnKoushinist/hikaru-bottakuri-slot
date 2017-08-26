package twitter4j;

import com.unity3d.ads.metadata.MediationMetaData;
import twitter4j.conf.Configuration;

class FriendshipJSONImpl implements Friendship {
    private static final long serialVersionUID = 6847273186993125826L;
    private boolean followedBy = false;
    private boolean following = false;
    private final long id;
    private final String name;
    private final String screenName;

    FriendshipJSONImpl(JSONObject jSONObject) {
        int i = 0;
        try {
            this.id = ParseUtil.getLong("id", jSONObject);
            this.name = jSONObject.getString(MediationMetaData.KEY_NAME);
            this.screenName = jSONObject.getString("screen_name");
            JSONArray jSONArray = jSONObject.getJSONArray("connections");
            while (i < jSONArray.length()) {
                String string = jSONArray.getString(i);
                if ("following".equals(string)) {
                    this.following = true;
                } else if ("followed_by".equals(string)) {
                    this.followedBy = true;
                }
                i++;
            }
        } catch (Throwable e) {
            throw new TwitterException(e.getMessage() + ":" + jSONObject.toString(), e);
        }
    }

    static ResponseList<Friendship> createFriendshipList(HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
            }
            JSONArray asJSONArray = httpResponse.asJSONArray();
            int length = asJSONArray.length();
            ResponseList<Friendship> responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = asJSONArray.getJSONObject(i);
                FriendshipJSONImpl friendshipJSONImpl = new FriendshipJSONImpl(jSONObject);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(friendshipJSONImpl, jSONObject);
                }
                responseListImpl.add(friendshipJSONImpl);
            }
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.registerJSONObject(responseListImpl, asJSONArray);
            }
            return responseListImpl;
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getScreenName() {
        return this.screenName;
    }

    public boolean isFollowing() {
        return this.following;
    }

    public boolean isFollowedBy() {
        return this.followedBy;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FriendshipJSONImpl friendshipJSONImpl = (FriendshipJSONImpl) obj;
        if (this.followedBy != friendshipJSONImpl.followedBy) {
            return false;
        }
        if (this.following != friendshipJSONImpl.following) {
            return false;
        }
        if (this.id != friendshipJSONImpl.id) {
            return false;
        }
        if (!this.name.equals(friendshipJSONImpl.name)) {
            return false;
        }
        if (this.screenName.equals(friendshipJSONImpl.screenName)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 1;
        int i2 = ((int) (this.id ^ (this.id >>> 32))) * 31;
        if (this.name != null) {
            hashCode = this.name.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.screenName != null) {
            hashCode = this.screenName.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.following) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (!this.followedBy) {
            i = 0;
        }
        return hashCode + i;
    }

    public String toString() {
        return "FriendshipJSONImpl{id=" + this.id + ", name='" + this.name + '\'' + ", screenName='" + this.screenName + '\'' + ", following=" + this.following + ", followedBy=" + this.followedBy + '}';
    }
}
