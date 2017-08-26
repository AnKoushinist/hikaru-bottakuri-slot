package twitter4j;

import java.util.Map;
import twitter4j.api.HelpResources.Language;
import twitter4j.conf.Configuration;

class JSONImplFactory implements ObjectFactory {
    private static final long serialVersionUID = -1853541456182663343L;
    private final Configuration conf;

    public JSONImplFactory(Configuration configuration) {
        this.conf = configuration;
    }

    public Status createStatus(JSONObject jSONObject) {
        return new StatusJSONImpl(jSONObject);
    }

    public User createUser(JSONObject jSONObject) {
        return new UserJSONImpl(jSONObject);
    }

    public UserList createAUserList(JSONObject jSONObject) {
        return new UserListJSONImpl(jSONObject);
    }

    public Map<String, RateLimitStatus> createRateLimitStatuses(HttpResponse httpResponse) {
        return RateLimitStatusJSONImpl.createRateLimitStatuses(httpResponse, this.conf);
    }

    public Status createStatus(HttpResponse httpResponse) {
        return new StatusJSONImpl(httpResponse, this.conf);
    }

    public ResponseList<Status> createStatusList(HttpResponse httpResponse) {
        return StatusJSONImpl.createStatusList(httpResponse, this.conf);
    }

    static GeoLocation createGeoLocation(JSONObject jSONObject) {
        try {
            if (jSONObject.isNull("coordinates")) {
                return null;
            }
            String string = jSONObject.getJSONObject("coordinates").getString("coordinates");
            String[] split = string.substring(1, string.length() - 1).split(",");
            return new GeoLocation(Double.parseDouble(split[1]), Double.parseDouble(split[0]));
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    static GeoLocation[][] coordinatesAsGeoLocationArray(JSONArray jSONArray) {
        try {
            GeoLocation[][] geoLocationArr = new GeoLocation[jSONArray.length()][];
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                geoLocationArr[i] = new GeoLocation[jSONArray2.length()];
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    JSONArray jSONArray3 = jSONArray2.getJSONArray(i2);
                    geoLocationArr[i][i2] = new GeoLocation(jSONArray3.getDouble(1), jSONArray3.getDouble(0));
                }
            }
            return geoLocationArr;
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public static RateLimitStatus createRateLimitStatusFromResponseHeader(HttpResponse httpResponse) {
        return RateLimitStatusJSONImpl.createFromResponseHeader(httpResponse);
    }

    public Trends createTrends(HttpResponse httpResponse) {
        return new TrendsJSONImpl(httpResponse, this.conf);
    }

    public User createUser(HttpResponse httpResponse) {
        return new UserJSONImpl(httpResponse, this.conf);
    }

    public ResponseList<User> createUserList(HttpResponse httpResponse) {
        return UserJSONImpl.createUserList(httpResponse, this.conf);
    }

    public ResponseList<User> createUserListFromJSONArray(HttpResponse httpResponse) {
        return UserJSONImpl.createUserList(httpResponse.asJSONArray(), httpResponse, this.conf);
    }

    public ResponseList<User> createUserListFromJSONArray_Users(HttpResponse httpResponse) {
        try {
            return UserJSONImpl.createUserList(httpResponse.asJSONObject().getJSONArray("users"), httpResponse, this.conf);
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public QueryResult createQueryResult(HttpResponse httpResponse, Query query) {
        try {
            return new QueryResultJSONImpl(httpResponse, this.conf);
        } catch (TwitterException e) {
            if (HttpResponseCode.NOT_FOUND == e.getStatusCode()) {
                return new QueryResultJSONImpl(query);
            }
            throw e;
        }
    }

    public IDs createIDs(HttpResponse httpResponse) {
        return new IDsJSONImpl(httpResponse, this.conf);
    }

    public PagableResponseList<User> createPagableUserList(HttpResponse httpResponse) {
        return UserJSONImpl.createPagableUserList(httpResponse, this.conf);
    }

    public UserList createAUserList(HttpResponse httpResponse) {
        return new UserListJSONImpl(httpResponse, this.conf);
    }

    public PagableResponseList<UserList> createPagableUserListList(HttpResponse httpResponse) {
        return UserListJSONImpl.createPagableUserListList(httpResponse, this.conf);
    }

    public ResponseList<UserList> createUserListList(HttpResponse httpResponse) {
        return UserListJSONImpl.createUserListList(httpResponse, this.conf);
    }

    public ResponseList<Category> createCategoryList(HttpResponse httpResponse) {
        return CategoryJSONImpl.createCategoriesList(httpResponse, this.conf);
    }

    public DirectMessage createDirectMessage(HttpResponse httpResponse) {
        return new DirectMessageJSONImpl(httpResponse, this.conf);
    }

    public ResponseList<DirectMessage> createDirectMessageList(HttpResponse httpResponse) {
        return DirectMessageJSONImpl.createDirectMessageList(httpResponse, this.conf);
    }

    public Relationship createRelationship(HttpResponse httpResponse) {
        return new RelationshipJSONImpl(httpResponse, this.conf);
    }

    public ResponseList<Friendship> createFriendshipList(HttpResponse httpResponse) {
        return FriendshipJSONImpl.createFriendshipList(httpResponse, this.conf);
    }

    public AccountTotals createAccountTotals(HttpResponse httpResponse) {
        return new AccountTotalsJSONImpl(httpResponse, this.conf);
    }

    public AccountSettings createAccountSettings(HttpResponse httpResponse) {
        return new AccountSettingsJSONImpl(httpResponse, this.conf);
    }

    public SavedSearch createSavedSearch(HttpResponse httpResponse) {
        return new SavedSearchJSONImpl(httpResponse, this.conf);
    }

    public ResponseList<SavedSearch> createSavedSearchList(HttpResponse httpResponse) {
        return SavedSearchJSONImpl.createSavedSearchList(httpResponse, this.conf);
    }

    public ResponseList<Location> createLocationList(HttpResponse httpResponse) {
        return LocationJSONImpl.createLocationList(httpResponse, this.conf);
    }

    public Place createPlace(HttpResponse httpResponse) {
        return new PlaceJSONImpl(httpResponse, this.conf);
    }

    public ResponseList<Place> createPlaceList(HttpResponse httpResponse) {
        try {
            return PlaceJSONImpl.createPlaceList(httpResponse, this.conf);
        } catch (TwitterException e) {
            if (e.getStatusCode() == HttpResponseCode.NOT_FOUND) {
                return new ResponseListImpl(0, null);
            }
            throw e;
        }
    }

    public TwitterAPIConfiguration createTwitterAPIConfiguration(HttpResponse httpResponse) {
        return new TwitterAPIConfigurationJSONImpl(httpResponse, this.conf);
    }

    public ResponseList<Language> createLanguageList(HttpResponse httpResponse) {
        return LanguageJSONImpl.createLanguageList(httpResponse, this.conf);
    }

    public <T> ResponseList<T> createEmptyResponseList() {
        return new ResponseListImpl(0, null);
    }

    public OEmbed createOEmbed(HttpResponse httpResponse) {
        return new OEmbedJSONImpl(httpResponse, this.conf);
    }

    public static HashtagEntity createHashtagEntity(int i, int i2, String str) {
        return new HashtagEntityJSONImpl(i, i2, str);
    }

    public static UserMentionEntity createUserMentionEntity(int i, int i2, String str, String str2, long j) {
        return new UserMentionEntityJSONImpl(i, i2, str, str2, j);
    }

    public static URLEntity createUrlEntity(int i, int i2, String str, String str2, String str3) {
        return new URLEntityJSONImpl(i, i2, str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JSONImplFactory)) {
            return false;
        }
        JSONImplFactory jSONImplFactory = (JSONImplFactory) obj;
        if (this.conf != null) {
            if (this.conf.equals(jSONImplFactory.conf)) {
                return true;
            }
        } else if (jSONImplFactory.conf == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.conf != null ? this.conf.hashCode() : 0;
    }

    public String toString() {
        return "JSONImplFactory{conf=" + this.conf + '}';
    }
}
