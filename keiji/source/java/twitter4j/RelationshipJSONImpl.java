package twitter4j;

import java.io.Serializable;
import twitter4j.conf.Configuration;

class RelationshipJSONImpl extends TwitterResponseImpl implements Serializable, Relationship {
    private static final long serialVersionUID = -2001484553401916448L;
    private final boolean sourceBlockingTarget;
    private final boolean sourceCanDm;
    private final boolean sourceFollowedByTarget;
    private final boolean sourceFollowingTarget;
    private final boolean sourceMutingTarget;
    private final boolean sourceNotificationsEnabled;
    private final long sourceUserId;
    private final String sourceUserScreenName;
    private final long targetUserId;
    private final String targetUserScreenName;
    private boolean wantRetweets;

    RelationshipJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        this(httpResponse, httpResponse.asJSONObject());
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(this, httpResponse.asJSONObject());
        }
    }

    RelationshipJSONImpl(JSONObject jSONObject) {
        this(null, jSONObject);
    }

    RelationshipJSONImpl(HttpResponse httpResponse, JSONObject jSONObject) {
        super(httpResponse);
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("relationship");
            JSONObject jSONObject3 = jSONObject2.getJSONObject("source");
            jSONObject2 = jSONObject2.getJSONObject("target");
            this.sourceUserId = ParseUtil.getLong("id", jSONObject3);
            this.targetUserId = ParseUtil.getLong("id", jSONObject2);
            this.sourceUserScreenName = ParseUtil.getUnescapedString("screen_name", jSONObject3);
            this.targetUserScreenName = ParseUtil.getUnescapedString("screen_name", jSONObject2);
            this.sourceBlockingTarget = ParseUtil.getBoolean("blocking", jSONObject3);
            this.sourceFollowingTarget = ParseUtil.getBoolean("following", jSONObject3);
            this.sourceFollowedByTarget = ParseUtil.getBoolean("followed_by", jSONObject3);
            this.sourceCanDm = ParseUtil.getBoolean("can_dm", jSONObject3);
            this.sourceMutingTarget = ParseUtil.getBoolean("muting", jSONObject3);
            this.sourceNotificationsEnabled = ParseUtil.getBoolean("notifications_enabled", jSONObject3);
            this.wantRetweets = ParseUtil.getBoolean("want_retweets", jSONObject3);
        } catch (Throwable e) {
            throw new TwitterException(e.getMessage() + ":" + jSONObject.toString(), e);
        }
    }

    static ResponseList<Relationship> createRelationshipList(HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
            }
            JSONArray asJSONArray = httpResponse.asJSONArray();
            int length = asJSONArray.length();
            ResponseList<Relationship> responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = asJSONArray.getJSONObject(i);
                RelationshipJSONImpl relationshipJSONImpl = new RelationshipJSONImpl(jSONObject);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(relationshipJSONImpl, jSONObject);
                }
                responseListImpl.add(relationshipJSONImpl);
            }
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.registerJSONObject(responseListImpl, asJSONArray);
            }
            return responseListImpl;
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public long getSourceUserId() {
        return this.sourceUserId;
    }

    public long getTargetUserId() {
        return this.targetUserId;
    }

    public boolean isSourceBlockingTarget() {
        return this.sourceBlockingTarget;
    }

    public String getSourceUserScreenName() {
        return this.sourceUserScreenName;
    }

    public String getTargetUserScreenName() {
        return this.targetUserScreenName;
    }

    public boolean isSourceFollowingTarget() {
        return this.sourceFollowingTarget;
    }

    public boolean isTargetFollowingSource() {
        return this.sourceFollowedByTarget;
    }

    public boolean isSourceFollowedByTarget() {
        return this.sourceFollowedByTarget;
    }

    public boolean isTargetFollowedBySource() {
        return this.sourceFollowingTarget;
    }

    public boolean canSourceDm() {
        return this.sourceCanDm;
    }

    public boolean isSourceMutingTarget() {
        return this.sourceMutingTarget;
    }

    public boolean isSourceNotificationsEnabled() {
        return this.sourceNotificationsEnabled;
    }

    public boolean isSourceWantRetweets() {
        return this.wantRetweets;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RelationshipJSONImpl relationshipJSONImpl = (RelationshipJSONImpl) obj;
        if (this.sourceBlockingTarget != relationshipJSONImpl.sourceBlockingTarget) {
            return false;
        }
        if (this.sourceCanDm != relationshipJSONImpl.sourceCanDm) {
            return false;
        }
        if (this.sourceFollowedByTarget != relationshipJSONImpl.sourceFollowedByTarget) {
            return false;
        }
        if (this.sourceFollowingTarget != relationshipJSONImpl.sourceFollowingTarget) {
            return false;
        }
        if (this.sourceMutingTarget != relationshipJSONImpl.sourceMutingTarget) {
            return false;
        }
        if (this.sourceNotificationsEnabled != relationshipJSONImpl.sourceNotificationsEnabled) {
            return false;
        }
        if (this.sourceUserId != relationshipJSONImpl.sourceUserId) {
            return false;
        }
        if (this.targetUserId != relationshipJSONImpl.targetUserId) {
            return false;
        }
        if (this.wantRetweets != relationshipJSONImpl.wantRetweets) {
            return false;
        }
        if (this.sourceUserScreenName == null ? relationshipJSONImpl.sourceUserScreenName != null : !this.sourceUserScreenName.equals(relationshipJSONImpl.sourceUserScreenName)) {
            return false;
        }
        if (this.targetUserScreenName != null) {
            if (this.targetUserScreenName.equals(relationshipJSONImpl.targetUserScreenName)) {
                return true;
            }
        } else if (relationshipJSONImpl.targetUserScreenName == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 1;
        int hashCode = ((this.targetUserScreenName != null ? this.targetUserScreenName.hashCode() : 0) + (((int) (this.targetUserId ^ (this.targetUserId >>> 32))) * 31)) * 31;
        if (this.sourceBlockingTarget) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.sourceNotificationsEnabled) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.sourceFollowingTarget) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.sourceFollowedByTarget) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.sourceCanDm) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (i + hashCode) * 31;
        if (this.sourceMutingTarget) {
            i = 1;
        } else {
            i = 0;
        }
        hashCode = (((i + hashCode) * 31) + ((int) (this.sourceUserId ^ (this.sourceUserId >>> 32)))) * 31;
        if (this.sourceUserScreenName != null) {
            i = this.sourceUserScreenName.hashCode();
        } else {
            i = 0;
        }
        i = (i + hashCode) * 31;
        if (!this.wantRetweets) {
            i2 = 0;
        }
        return i + i2;
    }

    public String toString() {
        return "RelationshipJSONImpl{targetUserId=" + this.targetUserId + ", targetUserScreenName='" + this.targetUserScreenName + '\'' + ", sourceBlockingTarget=" + this.sourceBlockingTarget + ", sourceNotificationsEnabled=" + this.sourceNotificationsEnabled + ", sourceFollowingTarget=" + this.sourceFollowingTarget + ", sourceFollowedByTarget=" + this.sourceFollowedByTarget + ", sourceCanDm=" + this.sourceCanDm + ", sourceMutingTarget=" + this.sourceMutingTarget + ", sourceUserId=" + this.sourceUserId + ", sourceUserScreenName='" + this.sourceUserScreenName + '\'' + ", wantRetweets=" + this.wantRetweets + '}';
    }
}
