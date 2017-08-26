package twitter4j;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import twitter4j.conf.Configuration;

final class StatusJSONImpl extends TwitterResponseImpl implements Serializable, Status {
    private static final Logger logger = Logger.getLogger(StatusJSONImpl.class);
    private static final long serialVersionUID = -6461195536943679985L;
    private long[] contributorsIDs;
    private Date createdAt;
    private long currentUserRetweetId = -1;
    private MediaEntity[] extendedMediaEntities;
    private int favoriteCount;
    private GeoLocation geoLocation = null;
    private HashtagEntity[] hashtagEntities;
    private long id;
    private String inReplyToScreenName;
    private long inReplyToStatusId;
    private long inReplyToUserId;
    private boolean isFavorited;
    private boolean isPossiblySensitive;
    private boolean isRetweeted;
    private boolean isTruncated;
    private String lang;
    private MediaEntity[] mediaEntities;
    private Place place = null;
    private long retweetCount;
    private Status retweetedStatus;
    private Scopes scopes;
    private String source;
    private SymbolEntity[] symbolEntities;
    private String text;
    private URLEntity[] urlEntities;
    private User user = null;
    private UserMentionEntity[] userMentionEntities;

    StatusJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        JSONObject asJSONObject = httpResponse.asJSONObject();
        init(asJSONObject);
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(this, asJSONObject);
        }
    }

    StatusJSONImpl(JSONObject jSONObject, Configuration configuration) {
        init(jSONObject);
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.registerJSONObject(this, jSONObject);
        }
    }

    StatusJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    StatusJSONImpl() {
    }

    private void init(JSONObject jSONObject) {
        int i = 0;
        this.id = ParseUtil.getLong("id", jSONObject);
        this.source = ParseUtil.getUnescapedString("source", jSONObject);
        this.createdAt = ParseUtil.getDate("created_at", jSONObject);
        this.isTruncated = ParseUtil.getBoolean("truncated", jSONObject);
        this.inReplyToStatusId = ParseUtil.getLong("in_reply_to_status_id", jSONObject);
        this.inReplyToUserId = ParseUtil.getLong("in_reply_to_user_id", jSONObject);
        this.isFavorited = ParseUtil.getBoolean("favorited", jSONObject);
        this.isRetweeted = ParseUtil.getBoolean("retweeted", jSONObject);
        this.inReplyToScreenName = ParseUtil.getUnescapedString("in_reply_to_screen_name", jSONObject);
        this.retweetCount = ParseUtil.getLong("retweet_count", jSONObject);
        this.favoriteCount = ParseUtil.getInt("favorite_count", jSONObject);
        this.isPossiblySensitive = ParseUtil.getBoolean("possibly_sensitive", jSONObject);
        try {
            JSONArray jSONArray;
            int i2;
            int length;
            JSONObject jSONObject2;
            if (!jSONObject.isNull("user")) {
                this.user = new UserJSONImpl(jSONObject.getJSONObject("user"));
            }
            this.geoLocation = JSONImplFactory.createGeoLocation(jSONObject);
            if (!jSONObject.isNull("place")) {
                this.place = new PlaceJSONImpl(jSONObject.getJSONObject("place"));
            }
            if (!jSONObject.isNull("retweeted_status")) {
                this.retweetedStatus = new StatusJSONImpl(jSONObject.getJSONObject("retweeted_status"));
            }
            if (jSONObject.isNull("contributors")) {
                this.contributorsIDs = new long[0];
            } else {
                jSONArray = jSONObject.getJSONArray("contributors");
                this.contributorsIDs = new long[jSONArray.length()];
                for (i2 = 0; i2 < jSONArray.length(); i2++) {
                    this.contributorsIDs[i2] = Long.parseLong(jSONArray.getString(i2));
                }
            }
            if (!jSONObject.isNull("entities")) {
                JSONArray jSONArray2;
                int length2;
                JSONObject jSONObject3 = jSONObject.getJSONObject("entities");
                if (!jSONObject3.isNull("user_mentions")) {
                    jSONArray2 = jSONObject3.getJSONArray("user_mentions");
                    length2 = jSONArray2.length();
                    this.userMentionEntities = new UserMentionEntity[length2];
                    for (i2 = 0; i2 < length2; i2++) {
                        this.userMentionEntities[i2] = new UserMentionEntityJSONImpl(jSONArray2.getJSONObject(i2));
                    }
                }
                if (!jSONObject3.isNull("urls")) {
                    jSONArray2 = jSONObject3.getJSONArray("urls");
                    length2 = jSONArray2.length();
                    this.urlEntities = new URLEntity[length2];
                    for (i2 = 0; i2 < length2; i2++) {
                        this.urlEntities[i2] = new URLEntityJSONImpl(jSONArray2.getJSONObject(i2));
                    }
                }
                if (!jSONObject3.isNull("hashtags")) {
                    jSONArray2 = jSONObject3.getJSONArray("hashtags");
                    length2 = jSONArray2.length();
                    this.hashtagEntities = new HashtagEntity[length2];
                    for (i2 = 0; i2 < length2; i2++) {
                        this.hashtagEntities[i2] = new HashtagEntityJSONImpl(jSONArray2.getJSONObject(i2));
                    }
                }
                if (!jSONObject3.isNull("symbols")) {
                    jSONArray2 = jSONObject3.getJSONArray("symbols");
                    length2 = jSONArray2.length();
                    this.symbolEntities = new SymbolEntity[length2];
                    for (i2 = 0; i2 < length2; i2++) {
                        this.symbolEntities[i2] = new HashtagEntityJSONImpl(jSONArray2.getJSONObject(i2));
                    }
                }
                if (!jSONObject3.isNull("media")) {
                    jSONArray = jSONObject3.getJSONArray("media");
                    length = jSONArray.length();
                    this.mediaEntities = new MediaEntity[length];
                    for (i2 = 0; i2 < length; i2++) {
                        this.mediaEntities[i2] = new MediaEntityJSONImpl(jSONArray.getJSONObject(i2));
                    }
                }
            }
            if (!jSONObject.isNull("extended_entities")) {
                jSONObject2 = jSONObject.getJSONObject("extended_entities");
                if (!jSONObject2.isNull("media")) {
                    jSONArray = jSONObject2.getJSONArray("media");
                    length = jSONArray.length();
                    this.extendedMediaEntities = new MediaEntity[length];
                    for (i2 = 0; i2 < length; i2++) {
                        this.extendedMediaEntities[i2] = new MediaEntityJSONImpl(jSONArray.getJSONObject(i2));
                    }
                }
            }
            this.userMentionEntities = this.userMentionEntities == null ? new UserMentionEntity[0] : this.userMentionEntities;
            this.urlEntities = this.urlEntities == null ? new URLEntity[0] : this.urlEntities;
            this.hashtagEntities = this.hashtagEntities == null ? new HashtagEntity[0] : this.hashtagEntities;
            this.symbolEntities = this.symbolEntities == null ? new SymbolEntity[0] : this.symbolEntities;
            this.mediaEntities = this.mediaEntities == null ? new MediaEntity[0] : this.mediaEntities;
            this.extendedMediaEntities = this.extendedMediaEntities == null ? new MediaEntity[0] : this.extendedMediaEntities;
            this.text = HTMLEntity.unescapeAndSlideEntityIncdices(jSONObject.getString("text"), this.userMentionEntities, this.urlEntities, this.hashtagEntities, this.mediaEntities);
            if (!jSONObject.isNull("current_user_retweet")) {
                this.currentUserRetweetId = jSONObject.getJSONObject("current_user_retweet").getLong("id");
            }
            if (!jSONObject.isNull("lang")) {
                this.lang = ParseUtil.getUnescapedString("lang", jSONObject);
            }
            if (!jSONObject.isNull("scopes")) {
                jSONObject2 = jSONObject.getJSONObject("scopes");
                if (!jSONObject2.isNull("place_ids")) {
                    JSONArray jSONArray3 = jSONObject2.getJSONArray("place_ids");
                    int length3 = jSONArray3.length();
                    String[] strArr = new String[length3];
                    while (i < length3) {
                        strArr[i] = jSONArray3.getString(i);
                        i++;
                    }
                    this.scopes = new ScopesImpl(strArr);
                }
            }
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public int compareTo(Status status) {
        long id = this.id - status.getId();
        if (id < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        if (id > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) id;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public long getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public String getSource() {
        return this.source;
    }

    public boolean isTruncated() {
        return this.isTruncated;
    }

    public long getInReplyToStatusId() {
        return this.inReplyToStatusId;
    }

    public long getInReplyToUserId() {
        return this.inReplyToUserId;
    }

    public String getInReplyToScreenName() {
        return this.inReplyToScreenName;
    }

    public GeoLocation getGeoLocation() {
        return this.geoLocation;
    }

    public Place getPlace() {
        return this.place;
    }

    public long[] getContributors() {
        return this.contributorsIDs;
    }

    public boolean isFavorited() {
        return this.isFavorited;
    }

    public boolean isRetweeted() {
        return this.isRetweeted;
    }

    public int getFavoriteCount() {
        return this.favoriteCount;
    }

    public User getUser() {
        return this.user;
    }

    public boolean isRetweet() {
        return this.retweetedStatus != null;
    }

    public Status getRetweetedStatus() {
        return this.retweetedStatus;
    }

    public int getRetweetCount() {
        return (int) this.retweetCount;
    }

    public boolean isRetweetedByMe() {
        return this.currentUserRetweetId != -1;
    }

    public long getCurrentUserRetweetId() {
        return this.currentUserRetweetId;
    }

    public boolean isPossiblySensitive() {
        return this.isPossiblySensitive;
    }

    public UserMentionEntity[] getUserMentionEntities() {
        return this.userMentionEntities;
    }

    public URLEntity[] getURLEntities() {
        return this.urlEntities;
    }

    public HashtagEntity[] getHashtagEntities() {
        return this.hashtagEntities;
    }

    public MediaEntity[] getMediaEntities() {
        return this.mediaEntities;
    }

    public MediaEntity[] getExtendedMediaEntities() {
        return this.extendedMediaEntities;
    }

    public SymbolEntity[] getSymbolEntities() {
        return this.symbolEntities;
    }

    public Scopes getScopes() {
        return this.scopes;
    }

    public String getLang() {
        return this.lang;
    }

    static ResponseList<Status> createStatusList(HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
            }
            JSONArray asJSONArray = httpResponse.asJSONArray();
            int length = asJSONArray.length();
            ResponseList<Status> responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = asJSONArray.getJSONObject(i);
                StatusJSONImpl statusJSONImpl = new StatusJSONImpl(jSONObject);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(statusJSONImpl, jSONObject);
                }
                responseListImpl.add(statusJSONImpl);
            }
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.registerJSONObject(responseListImpl, asJSONArray);
            }
            return responseListImpl;
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public int hashCode() {
        return (int) this.id;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Status) && ((Status) obj).getId() == this.id) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "StatusJSONImpl{createdAt=" + this.createdAt + ", id=" + this.id + ", text='" + this.text + '\'' + ", source='" + this.source + '\'' + ", isTruncated=" + this.isTruncated + ", inReplyToStatusId=" + this.inReplyToStatusId + ", inReplyToUserId=" + this.inReplyToUserId + ", isFavorited=" + this.isFavorited + ", isRetweeted=" + this.isRetweeted + ", favoriteCount=" + this.favoriteCount + ", inReplyToScreenName='" + this.inReplyToScreenName + '\'' + ", geoLocation=" + this.geoLocation + ", place=" + this.place + ", retweetCount=" + this.retweetCount + ", isPossiblySensitive=" + this.isPossiblySensitive + ", lang='" + this.lang + '\'' + ", contributorsIDs=" + Arrays.toString(this.contributorsIDs) + ", retweetedStatus=" + this.retweetedStatus + ", userMentionEntities=" + Arrays.toString(this.userMentionEntities) + ", urlEntities=" + Arrays.toString(this.urlEntities) + ", hashtagEntities=" + Arrays.toString(this.hashtagEntities) + ", mediaEntities=" + Arrays.toString(this.mediaEntities) + ", symbolEntities=" + Arrays.toString(this.symbolEntities) + ", currentUserRetweetId=" + this.currentUserRetweetId + ", user=" + this.user + '}';
    }
}
