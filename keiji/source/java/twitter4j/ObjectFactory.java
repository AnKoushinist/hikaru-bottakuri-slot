package twitter4j;

import java.io.Serializable;
import java.util.Map;
import twitter4j.api.HelpResources.Language;

interface ObjectFactory extends Serializable {
    UserList createAUserList(HttpResponse httpResponse);

    UserList createAUserList(JSONObject jSONObject);

    AccountSettings createAccountSettings(HttpResponse httpResponse);

    AccountTotals createAccountTotals(HttpResponse httpResponse);

    ResponseList<Category> createCategoryList(HttpResponse httpResponse);

    DirectMessage createDirectMessage(HttpResponse httpResponse);

    ResponseList<DirectMessage> createDirectMessageList(HttpResponse httpResponse);

    <T> ResponseList<T> createEmptyResponseList();

    ResponseList<Friendship> createFriendshipList(HttpResponse httpResponse);

    IDs createIDs(HttpResponse httpResponse);

    ResponseList<Language> createLanguageList(HttpResponse httpResponse);

    ResponseList<Location> createLocationList(HttpResponse httpResponse);

    OEmbed createOEmbed(HttpResponse httpResponse);

    PagableResponseList<User> createPagableUserList(HttpResponse httpResponse);

    PagableResponseList<UserList> createPagableUserListList(HttpResponse httpResponse);

    Place createPlace(HttpResponse httpResponse);

    ResponseList<Place> createPlaceList(HttpResponse httpResponse);

    QueryResult createQueryResult(HttpResponse httpResponse, Query query);

    Map<String, RateLimitStatus> createRateLimitStatuses(HttpResponse httpResponse);

    Relationship createRelationship(HttpResponse httpResponse);

    SavedSearch createSavedSearch(HttpResponse httpResponse);

    ResponseList<SavedSearch> createSavedSearchList(HttpResponse httpResponse);

    Status createStatus(HttpResponse httpResponse);

    Status createStatus(JSONObject jSONObject);

    ResponseList<Status> createStatusList(HttpResponse httpResponse);

    Trends createTrends(HttpResponse httpResponse);

    TwitterAPIConfiguration createTwitterAPIConfiguration(HttpResponse httpResponse);

    User createUser(HttpResponse httpResponse);

    User createUser(JSONObject jSONObject);

    ResponseList<User> createUserList(HttpResponse httpResponse);

    ResponseList<User> createUserListFromJSONArray(HttpResponse httpResponse);

    ResponseList<User> createUserListFromJSONArray_Users(HttpResponse httpResponse);

    ResponseList<UserList> createUserListList(HttpResponse httpResponse);
}
