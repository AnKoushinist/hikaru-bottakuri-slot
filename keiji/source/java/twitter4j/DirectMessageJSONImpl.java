package twitter4j;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import twitter4j.conf.Configuration;

final class DirectMessageJSONImpl extends TwitterResponseImpl implements Serializable, DirectMessage {
    private static final long serialVersionUID = 7092906238192790921L;
    private Date createdAt;
    private MediaEntity[] extendedMediaEntities;
    private HashtagEntity[] hashtagEntities;
    private long id;
    private MediaEntity[] mediaEntities;
    private User recipient;
    private long recipientId;
    private String recipientScreenName;
    private User sender;
    private long senderId;
    private String senderScreenName;
    private SymbolEntity[] symbolEntities;
    private String text;
    private URLEntity[] urlEntities;
    private UserMentionEntity[] userMentionEntities;

    DirectMessageJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        JSONObject asJSONObject = httpResponse.asJSONObject();
        init(asJSONObject);
        if (configuration.isJSONStoreEnabled()) {
            TwitterObjectFactory.clearThreadLocalMap();
            TwitterObjectFactory.registerJSONObject(this, asJSONObject);
        }
    }

    DirectMessageJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    private void init(JSONObject jSONObject) {
        int i = 0;
        this.id = ParseUtil.getLong("id", jSONObject);
        this.senderId = ParseUtil.getLong("sender_id", jSONObject);
        this.recipientId = ParseUtil.getLong("recipient_id", jSONObject);
        this.createdAt = ParseUtil.getDate("created_at", jSONObject);
        this.senderScreenName = ParseUtil.getUnescapedString("sender_screen_name", jSONObject);
        this.recipientScreenName = ParseUtil.getUnescapedString("recipient_screen_name", jSONObject);
        try {
            this.sender = new UserJSONImpl(jSONObject.getJSONObject("sender"));
            this.recipient = new UserJSONImpl(jSONObject.getJSONObject("recipient"));
            if (!jSONObject.isNull("entities")) {
                JSONArray jSONArray;
                int length;
                int i2;
                JSONObject jSONObject2 = jSONObject.getJSONObject("entities");
                if (!jSONObject2.isNull("user_mentions")) {
                    jSONArray = jSONObject2.getJSONArray("user_mentions");
                    length = jSONArray.length();
                    this.userMentionEntities = new UserMentionEntity[length];
                    for (i2 = 0; i2 < length; i2++) {
                        this.userMentionEntities[i2] = new UserMentionEntityJSONImpl(jSONArray.getJSONObject(i2));
                    }
                }
                if (!jSONObject2.isNull("urls")) {
                    jSONArray = jSONObject2.getJSONArray("urls");
                    length = jSONArray.length();
                    this.urlEntities = new URLEntity[length];
                    for (i2 = 0; i2 < length; i2++) {
                        this.urlEntities[i2] = new URLEntityJSONImpl(jSONArray.getJSONObject(i2));
                    }
                }
                if (!jSONObject2.isNull("hashtags")) {
                    jSONArray = jSONObject2.getJSONArray("hashtags");
                    length = jSONArray.length();
                    this.hashtagEntities = new HashtagEntity[length];
                    for (i2 = 0; i2 < length; i2++) {
                        this.hashtagEntities[i2] = new HashtagEntityJSONImpl(jSONArray.getJSONObject(i2));
                    }
                }
                if (!jSONObject2.isNull("symbols")) {
                    jSONArray = jSONObject2.getJSONArray("symbols");
                    length = jSONArray.length();
                    this.symbolEntities = new SymbolEntity[length];
                    for (i2 = 0; i2 < length; i2++) {
                        this.symbolEntities[i2] = new HashtagEntityJSONImpl(jSONArray.getJSONObject(i2));
                    }
                }
                if (!jSONObject2.isNull("media")) {
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("media");
                    int length2 = jSONArray2.length();
                    this.mediaEntities = new MediaEntity[length2];
                    while (i < length2) {
                        this.mediaEntities[i] = new MediaEntityJSONImpl(jSONArray2.getJSONObject(i));
                        i++;
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
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public long getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public long getSenderId() {
        return this.senderId;
    }

    public long getRecipientId() {
        return this.recipientId;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public String getSenderScreenName() {
        return this.senderScreenName;
    }

    public String getRecipientScreenName() {
        return this.recipientScreenName;
    }

    public User getSender() {
        return this.sender;
    }

    public User getRecipient() {
        return this.recipient;
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

    static ResponseList<DirectMessage> createDirectMessageList(HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
            }
            JSONArray asJSONArray = httpResponse.asJSONArray();
            int length = asJSONArray.length();
            ResponseList<DirectMessage> responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = asJSONArray.getJSONObject(i);
                DirectMessageJSONImpl directMessageJSONImpl = new DirectMessageJSONImpl(jSONObject);
                responseListImpl.add(directMessageJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(directMessageJSONImpl, jSONObject);
                }
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
        if ((obj instanceof DirectMessage) && ((DirectMessage) obj).getId() == this.id) {
            return true;
        }
        return false;
    }

    public String toString() {
        Object obj;
        Object obj2 = null;
        StringBuilder append = new StringBuilder().append("DirectMessageJSONImpl{id=").append(this.id).append(", text='").append(this.text).append('\'').append(", sender_id=").append(this.senderId).append(", recipient_id=").append(this.recipientId).append(", created_at=").append(this.createdAt).append(", userMentionEntities=");
        if (this.userMentionEntities == null) {
            obj = null;
        } else {
            obj = Arrays.asList(this.userMentionEntities);
        }
        append = append.append(obj).append(", urlEntities=");
        if (this.urlEntities == null) {
            obj = null;
        } else {
            obj = Arrays.asList(this.urlEntities);
        }
        append = append.append(obj).append(", hashtagEntities=");
        if (this.hashtagEntities == null) {
            obj = null;
        } else {
            obj = Arrays.asList(this.hashtagEntities);
        }
        append = append.append(obj).append(", sender_screen_name='").append(this.senderScreenName).append('\'').append(", recipient_screen_name='").append(this.recipientScreenName).append('\'').append(", sender=").append(this.sender).append(", recipient=").append(this.recipient).append(", userMentionEntities=");
        if (this.userMentionEntities == null) {
            obj = null;
        } else {
            obj = Arrays.asList(this.userMentionEntities);
        }
        append = append.append(obj).append(", urlEntities=");
        if (this.urlEntities == null) {
            obj = null;
        } else {
            obj = Arrays.asList(this.urlEntities);
        }
        StringBuilder append2 = append.append(obj).append(", hashtagEntities=");
        if (this.hashtagEntities != null) {
            obj2 = Arrays.asList(this.hashtagEntities);
        }
        return append2.append(obj2).append('}').toString();
    }
}
