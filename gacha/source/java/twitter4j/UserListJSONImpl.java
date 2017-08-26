package twitter4j;

import com.unity3d.ads.metadata.MediationMetaData;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import twitter4j.conf.Configuration;

class UserListJSONImpl extends TwitterResponseImpl implements Serializable, UserList {
    private static final long serialVersionUID = 449418980060197008L;
    private Date createdAt;
    private String description;
    private boolean following;
    private String fullName;
    private long id;
    private int memberCount;
    private boolean mode;
    private String name;
    private String slug;
    private int subscriberCount;
    private String uri;
    private User user;

    UserListJSONImpl(HttpResponse httpResponse, Configuration configuration) {
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

    UserListJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    private void init(JSONObject jSONObject) {
        this.id = ParseUtil.getLong("id", jSONObject);
        this.name = ParseUtil.getRawString(MediationMetaData.KEY_NAME, jSONObject);
        this.fullName = ParseUtil.getRawString("full_name", jSONObject);
        this.slug = ParseUtil.getRawString("slug", jSONObject);
        this.description = ParseUtil.getRawString("description", jSONObject);
        this.subscriberCount = ParseUtil.getInt("subscriber_count", jSONObject);
        this.memberCount = ParseUtil.getInt("member_count", jSONObject);
        this.uri = ParseUtil.getRawString("uri", jSONObject);
        this.mode = "public".equals(ParseUtil.getRawString("mode", jSONObject));
        this.following = ParseUtil.getBoolean("following", jSONObject);
        this.createdAt = ParseUtil.getDate("created_at", jSONObject);
        try {
            if (!jSONObject.isNull("user")) {
                this.user = new UserJSONImpl(jSONObject.getJSONObject("user"));
            }
        } catch (Throwable e) {
            throw new TwitterException(e.getMessage() + ":" + jSONObject.toString(), e);
        }
    }

    public int compareTo(UserList userList) {
        long id = this.id - userList.getId();
        if (id < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        if (id > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) id;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getSlug() {
        return this.slug;
    }

    public String getDescription() {
        return this.description;
    }

    public int getSubscriberCount() {
        return this.subscriberCount;
    }

    public int getMemberCount() {
        return this.memberCount;
    }

    public URI getURI() {
        try {
            return new URI(this.uri);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    public boolean isPublic() {
        return this.mode;
    }

    public boolean isFollowing() {
        return this.following;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public User getUser() {
        return this.user;
    }

    static PagableResponseList<UserList> createPagableUserListList(HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
            }
            JSONObject asJSONObject = httpResponse.asJSONObject();
            JSONArray jSONArray = asJSONObject.getJSONArray("lists");
            int length = jSONArray.length();
            PagableResponseList<UserList> pagableResponseListImpl = new PagableResponseListImpl(length, asJSONObject, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                UserListJSONImpl userListJSONImpl = new UserListJSONImpl(jSONObject);
                pagableResponseListImpl.add(userListJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(userListJSONImpl, jSONObject);
                }
            }
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.registerJSONObject(pagableResponseListImpl, asJSONObject);
            }
            return pagableResponseListImpl;
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    static ResponseList<UserList> createUserListList(HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
            }
            JSONArray asJSONArray = httpResponse.asJSONArray();
            int length = asJSONArray.length();
            ResponseList<UserList> responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = asJSONArray.getJSONObject(i);
                UserListJSONImpl userListJSONImpl = new UserListJSONImpl(jSONObject);
                responseListImpl.add(userListJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(userListJSONImpl, jSONObject);
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
        if ((obj instanceof UserList) && ((UserList) obj).getId() == this.id) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "UserListJSONImpl{id=" + this.id + ", name='" + this.name + '\'' + ", fullName='" + this.fullName + '\'' + ", slug='" + this.slug + '\'' + ", description='" + this.description + '\'' + ", subscriberCount=" + this.subscriberCount + ", memberCount=" + this.memberCount + ", uri='" + this.uri + '\'' + ", mode=" + this.mode + ", user=" + this.user + ", following=" + this.following + '}';
    }
}
