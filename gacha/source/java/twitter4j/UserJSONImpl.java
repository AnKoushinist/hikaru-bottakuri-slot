package twitter4j;

import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TJAdUnitConstants.String;
import com.unity3d.ads.metadata.MediationMetaData;
import java.io.Serializable;
import java.util.Date;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.conf.Configuration;

final class UserJSONImpl extends TwitterResponseImpl implements Serializable, User {
    private static final long serialVersionUID = -5448266606847617015L;
    private Date createdAt;
    private String description;
    private URLEntity[] descriptionURLEntities;
    private int favouritesCount;
    private int followersCount;
    private int friendsCount;
    private long id;
    private boolean isContributorsEnabled;
    private boolean isDefaultProfile;
    private boolean isDefaultProfileImage;
    private boolean isFollowRequestSent;
    private boolean isGeoEnabled;
    private boolean isProtected;
    private boolean isVerified;
    private String lang;
    private int listedCount;
    private String location;
    private String name;
    private String profileBackgroundColor;
    private String profileBackgroundImageUrl;
    private String profileBackgroundImageUrlHttps;
    private boolean profileBackgroundTiled;
    private String profileBannerImageUrl;
    private String profileImageUrl;
    private String profileImageUrlHttps;
    private String profileLinkColor;
    private String profileSidebarBorderColor;
    private String profileSidebarFillColor;
    private String profileTextColor;
    private boolean profileUseBackgroundImage;
    private String screenName;
    private boolean showAllInlineMedia;
    private Status status;
    private int statusesCount;
    private String timeZone;
    private boolean translator;
    private String url;
    private URLEntity urlEntity;
    private int utcOffset;

    UserJSONImpl(HttpResponse httpResponse, Configuration configuration) {
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

    UserJSONImpl(JSONObject jSONObject) {
        init(jSONObject);
    }

    UserJSONImpl() {
    }

    private void init(JSONObject jSONObject) {
        try {
            this.id = ParseUtil.getLong("id", jSONObject);
            this.name = ParseUtil.getRawString(MediationMetaData.KEY_NAME, jSONObject);
            this.screenName = ParseUtil.getRawString("screen_name", jSONObject);
            this.location = ParseUtil.getRawString("location", jSONObject);
            this.descriptionURLEntities = getURLEntitiesFromJSON(jSONObject, "description");
            this.descriptionURLEntities = this.descriptionURLEntities == null ? new URLEntity[0] : this.descriptionURLEntities;
            URLEntity[] uRLEntitiesFromJSON = getURLEntitiesFromJSON(jSONObject, String.URL);
            if (uRLEntitiesFromJSON != null && uRLEntitiesFromJSON.length > 0) {
                this.urlEntity = uRLEntitiesFromJSON[0];
            }
            this.description = ParseUtil.getRawString("description", jSONObject);
            if (this.description != null) {
                this.description = HTMLEntity.unescapeAndSlideEntityIncdices(this.description, null, this.descriptionURLEntities, null, null);
            }
            this.isContributorsEnabled = ParseUtil.getBoolean("contributors_enabled", jSONObject);
            this.profileImageUrl = ParseUtil.getRawString("profile_image_url", jSONObject);
            this.profileImageUrlHttps = ParseUtil.getRawString("profile_image_url_https", jSONObject);
            this.isDefaultProfileImage = ParseUtil.getBoolean("default_profile_image", jSONObject);
            this.url = ParseUtil.getRawString(String.URL, jSONObject);
            this.isProtected = ParseUtil.getBoolean("protected", jSONObject);
            this.isGeoEnabled = ParseUtil.getBoolean("geo_enabled", jSONObject);
            this.isVerified = ParseUtil.getBoolean("verified", jSONObject);
            this.translator = ParseUtil.getBoolean("is_translator", jSONObject);
            this.followersCount = ParseUtil.getInt("followers_count", jSONObject);
            this.profileBackgroundColor = ParseUtil.getRawString("profile_background_color", jSONObject);
            this.profileTextColor = ParseUtil.getRawString("profile_text_color", jSONObject);
            this.profileLinkColor = ParseUtil.getRawString("profile_link_color", jSONObject);
            this.profileSidebarFillColor = ParseUtil.getRawString("profile_sidebar_fill_color", jSONObject);
            this.profileSidebarBorderColor = ParseUtil.getRawString("profile_sidebar_border_color", jSONObject);
            this.profileUseBackgroundImage = ParseUtil.getBoolean("profile_use_background_image", jSONObject);
            this.isDefaultProfile = ParseUtil.getBoolean("default_profile", jSONObject);
            this.showAllInlineMedia = ParseUtil.getBoolean("show_all_inline_media", jSONObject);
            this.friendsCount = ParseUtil.getInt("friends_count", jSONObject);
            this.createdAt = ParseUtil.getDate("created_at", jSONObject, "EEE MMM dd HH:mm:ss z yyyy");
            this.favouritesCount = ParseUtil.getInt("favourites_count", jSONObject);
            this.utcOffset = ParseUtil.getInt("utc_offset", jSONObject);
            this.timeZone = ParseUtil.getRawString("time_zone", jSONObject);
            this.profileBackgroundImageUrl = ParseUtil.getRawString("profile_background_image_url", jSONObject);
            this.profileBackgroundImageUrlHttps = ParseUtil.getRawString("profile_background_image_url_https", jSONObject);
            this.profileBannerImageUrl = ParseUtil.getRawString("profile_banner_url", jSONObject);
            this.profileBackgroundTiled = ParseUtil.getBoolean("profile_background_tile", jSONObject);
            this.lang = ParseUtil.getRawString("lang", jSONObject);
            this.statusesCount = ParseUtil.getInt("statuses_count", jSONObject);
            this.listedCount = ParseUtil.getInt("listed_count", jSONObject);
            this.isFollowRequestSent = ParseUtil.getBoolean("follow_request_sent", jSONObject);
            if (!jSONObject.isNull("status")) {
                this.status = new StatusJSONImpl(jSONObject.getJSONObject("status"));
            }
        } catch (Throwable e) {
            throw new TwitterException(e.getMessage() + ":" + jSONObject.toString(), e);
        }
    }

    private static URLEntity[] getURLEntitiesFromJSON(JSONObject jSONObject, String str) {
        if (!jSONObject.isNull("entities")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("entities");
            if (!jSONObject2.isNull(str)) {
                jSONObject2 = jSONObject2.getJSONObject(str);
                if (!jSONObject2.isNull("urls")) {
                    JSONArray jSONArray = jSONObject2.getJSONArray("urls");
                    int length = jSONArray.length();
                    URLEntity[] uRLEntityArr = new URLEntity[length];
                    for (int i = 0; i < length; i++) {
                        uRLEntityArr[i] = new URLEntityJSONImpl(jSONArray.getJSONObject(i));
                    }
                    return uRLEntityArr;
                }
            }
        }
        return null;
    }

    public int compareTo(User user) {
        return (int) (this.id - user.getId());
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

    public String getLocation() {
        return this.location;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isContributorsEnabled() {
        return this.isContributorsEnabled;
    }

    public String getProfileImageURL() {
        return this.profileImageUrl;
    }

    public String getBiggerProfileImageURL() {
        return toResizedURL(this.profileImageUrl, "_bigger");
    }

    public String getMiniProfileImageURL() {
        return toResizedURL(this.profileImageUrl, "_mini");
    }

    public String getOriginalProfileImageURL() {
        return toResizedURL(this.profileImageUrl, BuildConfig.FLAVOR);
    }

    private String toResizedURL(String str, String str2) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf("_");
        int lastIndexOf2 = str.lastIndexOf(".");
        int lastIndexOf3 = str.lastIndexOf(Operation.DIVISION);
        String str3 = str.substring(0, lastIndexOf) + str2;
        if (lastIndexOf2 > lastIndexOf3) {
            return str3 + str.substring(lastIndexOf2);
        }
        return str3;
    }

    public String getProfileImageURLHttps() {
        return this.profileImageUrlHttps;
    }

    public String getBiggerProfileImageURLHttps() {
        return toResizedURL(this.profileImageUrlHttps, "_bigger");
    }

    public String getMiniProfileImageURLHttps() {
        return toResizedURL(this.profileImageUrlHttps, "_mini");
    }

    public String getOriginalProfileImageURLHttps() {
        return toResizedURL(this.profileImageUrlHttps, BuildConfig.FLAVOR);
    }

    public boolean isDefaultProfileImage() {
        return this.isDefaultProfileImage;
    }

    public String getURL() {
        return this.url;
    }

    public boolean isProtected() {
        return this.isProtected;
    }

    public int getFollowersCount() {
        return this.followersCount;
    }

    public String getProfileBackgroundColor() {
        return this.profileBackgroundColor;
    }

    public String getProfileTextColor() {
        return this.profileTextColor;
    }

    public String getProfileLinkColor() {
        return this.profileLinkColor;
    }

    public String getProfileSidebarFillColor() {
        return this.profileSidebarFillColor;
    }

    public String getProfileSidebarBorderColor() {
        return this.profileSidebarBorderColor;
    }

    public boolean isProfileUseBackgroundImage() {
        return this.profileUseBackgroundImage;
    }

    public boolean isDefaultProfile() {
        return this.isDefaultProfile;
    }

    public boolean isShowAllInlineMedia() {
        return this.showAllInlineMedia;
    }

    public int getFriendsCount() {
        return this.friendsCount;
    }

    public Status getStatus() {
        return this.status;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public int getFavouritesCount() {
        return this.favouritesCount;
    }

    public int getUtcOffset() {
        return this.utcOffset;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public String getProfileBackgroundImageURL() {
        return this.profileBackgroundImageUrl;
    }

    public String getProfileBackgroundImageUrlHttps() {
        return this.profileBackgroundImageUrlHttps;
    }

    public String getProfileBannerURL() {
        return this.profileBannerImageUrl != null ? this.profileBannerImageUrl + "/web" : null;
    }

    public String getProfileBannerRetinaURL() {
        return this.profileBannerImageUrl != null ? this.profileBannerImageUrl + "/web_retina" : null;
    }

    public String getProfileBannerIPadURL() {
        return this.profileBannerImageUrl != null ? this.profileBannerImageUrl + "/ipad" : null;
    }

    public String getProfileBannerIPadRetinaURL() {
        return this.profileBannerImageUrl != null ? this.profileBannerImageUrl + "/ipad_retina" : null;
    }

    public String getProfileBannerMobileURL() {
        return this.profileBannerImageUrl != null ? this.profileBannerImageUrl + "/mobile" : null;
    }

    public String getProfileBannerMobileRetinaURL() {
        return this.profileBannerImageUrl != null ? this.profileBannerImageUrl + "/mobile_retina" : null;
    }

    public boolean isProfileBackgroundTiled() {
        return this.profileBackgroundTiled;
    }

    public String getLang() {
        return this.lang;
    }

    public int getStatusesCount() {
        return this.statusesCount;
    }

    public boolean isGeoEnabled() {
        return this.isGeoEnabled;
    }

    public boolean isVerified() {
        return this.isVerified;
    }

    public boolean isTranslator() {
        return this.translator;
    }

    public int getListedCount() {
        return this.listedCount;
    }

    public boolean isFollowRequestSent() {
        return this.isFollowRequestSent;
    }

    public URLEntity[] getDescriptionURLEntities() {
        return this.descriptionURLEntities;
    }

    public URLEntity getURLEntity() {
        if (this.urlEntity == null) {
            String str = this.url == null ? BuildConfig.FLAVOR : this.url;
            this.urlEntity = new URLEntityJSONImpl(0, str.length(), str, str, str);
        }
        return this.urlEntity;
    }

    static PagableResponseList<User> createPagableUserList(HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
            }
            JSONObject asJSONObject = httpResponse.asJSONObject();
            JSONArray jSONArray = asJSONObject.getJSONArray("users");
            int length = jSONArray.length();
            PagableResponseList<User> pagableResponseListImpl = new PagableResponseListImpl(length, asJSONObject, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                UserJSONImpl userJSONImpl = new UserJSONImpl(jSONObject);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(userJSONImpl, jSONObject);
                }
                pagableResponseListImpl.add(userJSONImpl);
            }
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.registerJSONObject(pagableResponseListImpl, asJSONObject);
            }
            return pagableResponseListImpl;
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    static ResponseList<User> createUserList(HttpResponse httpResponse, Configuration configuration) {
        return createUserList(httpResponse.asJSONArray(), httpResponse, configuration);
    }

    static ResponseList<User> createUserList(JSONArray jSONArray, HttpResponse httpResponse, Configuration configuration) {
        try {
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
            }
            int length = jSONArray.length();
            ResponseList<User> responseListImpl = new ResponseListImpl(length, httpResponse);
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                UserJSONImpl userJSONImpl = new UserJSONImpl(jSONObject);
                responseListImpl.add(userJSONImpl);
                if (configuration.isJSONStoreEnabled()) {
                    TwitterObjectFactory.registerJSONObject(userJSONImpl, jSONObject);
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
        if ((obj instanceof User) && ((User) obj).getId() == this.id) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "UserJSONImpl{id=" + this.id + ", name='" + this.name + '\'' + ", screenName='" + this.screenName + '\'' + ", location='" + this.location + '\'' + ", description='" + this.description + '\'' + ", isContributorsEnabled=" + this.isContributorsEnabled + ", profileImageUrl='" + this.profileImageUrl + '\'' + ", profileImageUrlHttps='" + this.profileImageUrlHttps + '\'' + ", isDefaultProfileImage=" + this.isDefaultProfileImage + ", url='" + this.url + '\'' + ", isProtected=" + this.isProtected + ", followersCount=" + this.followersCount + ", status=" + this.status + ", profileBackgroundColor='" + this.profileBackgroundColor + '\'' + ", profileTextColor='" + this.profileTextColor + '\'' + ", profileLinkColor='" + this.profileLinkColor + '\'' + ", profileSidebarFillColor='" + this.profileSidebarFillColor + '\'' + ", profileSidebarBorderColor='" + this.profileSidebarBorderColor + '\'' + ", profileUseBackgroundImage=" + this.profileUseBackgroundImage + ", isDefaultProfile=" + this.isDefaultProfile + ", showAllInlineMedia=" + this.showAllInlineMedia + ", friendsCount=" + this.friendsCount + ", createdAt=" + this.createdAt + ", favouritesCount=" + this.favouritesCount + ", utcOffset=" + this.utcOffset + ", timeZone='" + this.timeZone + '\'' + ", profileBackgroundImageUrl='" + this.profileBackgroundImageUrl + '\'' + ", profileBackgroundImageUrlHttps='" + this.profileBackgroundImageUrlHttps + '\'' + ", profileBackgroundTiled=" + this.profileBackgroundTiled + ", lang='" + this.lang + '\'' + ", statusesCount=" + this.statusesCount + ", isGeoEnabled=" + this.isGeoEnabled + ", isVerified=" + this.isVerified + ", translator=" + this.translator + ", listedCount=" + this.listedCount + ", isFollowRequestSent=" + this.isFollowRequestSent + '}';
    }
}
