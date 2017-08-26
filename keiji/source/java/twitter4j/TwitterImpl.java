package twitter4j;

import com.d.a.a.c;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.api.DirectMessagesResources;
import twitter4j.api.FavoritesResources;
import twitter4j.api.FriendsFollowersResources;
import twitter4j.api.HelpResources;
import twitter4j.api.HelpResources.Language;
import twitter4j.api.ListsResources;
import twitter4j.api.PlacesGeoResources;
import twitter4j.api.SavedSearchesResources;
import twitter4j.api.SearchResource;
import twitter4j.api.SpamReportingResource;
import twitter4j.api.SuggestedUsersResources;
import twitter4j.api.TimelinesResources;
import twitter4j.api.TrendsResources;
import twitter4j.api.TweetsResources;
import twitter4j.api.UsersResources;
import twitter4j.auth.Authorization;
import twitter4j.conf.Configuration;

class TwitterImpl extends TwitterBaseImpl implements Twitter {
    private static final ConcurrentHashMap<Configuration, HttpParameter[]> implicitParamsMap = new ConcurrentHashMap();
    private static final ConcurrentHashMap<Configuration, String> implicitParamsStrMap = new ConcurrentHashMap();
    private static final long serialVersionUID = 9170943084096085770L;
    private final HttpParameter[] IMPLICIT_PARAMS;
    private final String IMPLICIT_PARAMS_STR;
    private final HttpParameter INCLUDE_MY_RETWEET;

    TwitterImpl(Configuration configuration, Authorization authorization) {
        super(configuration, authorization);
        this.INCLUDE_MY_RETWEET = new HttpParameter("include_my_retweet", configuration.isIncludeMyRetweetEnabled());
        if (implicitParamsMap.containsKey(configuration)) {
            this.IMPLICIT_PARAMS = (HttpParameter[]) implicitParamsMap.get(configuration);
            this.IMPLICIT_PARAMS_STR = (String) implicitParamsStrMap.get(configuration);
            return;
        }
        String str;
        String str2 = configuration.isIncludeEntitiesEnabled() ? "include_entities=true" : BuildConfig.FLAVOR;
        Object obj = configuration.getContributingTo() != -1 ? 1 : null;
        if (obj != null) {
            if (!BuildConfig.FLAVOR.equals(str2)) {
                str2 = str2 + "?";
            }
            str = str2 + "contributingto=" + configuration.getContributingTo();
        } else {
            str = str2;
        }
        List arrayList = new ArrayList(3);
        if (configuration.isIncludeEntitiesEnabled()) {
            arrayList.add(new HttpParameter("include_entities", TapjoyConstants.TJC_TRUE));
        }
        if (obj != null) {
            arrayList.add(new HttpParameter("contributingto", configuration.getContributingTo()));
        }
        if (configuration.isTrimUserEnabled()) {
            arrayList.add(new HttpParameter("trim_user", "1"));
        }
        HttpParameter[] httpParameterArr = (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()]);
        implicitParamsStrMap.putIfAbsent(configuration, str);
        implicitParamsMap.putIfAbsent(configuration, httpParameterArr);
        this.IMPLICIT_PARAMS = httpParameterArr;
        this.IMPLICIT_PARAMS_STR = str;
    }

    public ResponseList<Status> getMentionsTimeline() {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/mentions_timeline.json"));
    }

    public ResponseList<Status> getMentionsTimeline(Paging paging) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/mentions_timeline.json", paging.asPostParameterArray()));
    }

    public ResponseList<Status> getHomeTimeline() {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/home_timeline.json", new HttpParameter[]{this.INCLUDE_MY_RETWEET}));
    }

    public ResponseList<Status> getHomeTimeline(Paging paging) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/home_timeline.json", mergeParameters(paging.asPostParameterArray(), new HttpParameter[]{this.INCLUDE_MY_RETWEET})));
    }

    public ResponseList<Status> getRetweetsOfMe() {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/retweets_of_me.json"));
    }

    public ResponseList<Status> getRetweetsOfMe(Paging paging) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/retweets_of_me.json", paging.asPostParameterArray()));
    }

    public ResponseList<Status> getUserTimeline(String str, Paging paging) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/user_timeline.json", mergeParameters(new HttpParameter[]{new HttpParameter("screen_name", str), this.INCLUDE_MY_RETWEET}, paging.asPostParameterArray())));
    }

    public ResponseList<Status> getUserTimeline(long j, Paging paging) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/user_timeline.json", mergeParameters(new HttpParameter[]{new HttpParameter("user_id", j), this.INCLUDE_MY_RETWEET}, paging.asPostParameterArray())));
    }

    public ResponseList<Status> getUserTimeline(String str) {
        return getUserTimeline(str, new Paging());
    }

    public ResponseList<Status> getUserTimeline(long j) {
        return getUserTimeline(j, new Paging());
    }

    public ResponseList<Status> getUserTimeline() {
        return getUserTimeline(new Paging());
    }

    public ResponseList<Status> getUserTimeline(Paging paging) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/user_timeline.json", mergeParameters(new HttpParameter[]{this.INCLUDE_MY_RETWEET}, paging.asPostParameterArray())));
    }

    public ResponseList<Status> getRetweets(long j) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/retweets/" + j + ".json?count=100"));
    }

    public IDs getRetweeterIds(long j, long j2) {
        return getRetweeterIds(j, 100, j2);
    }

    public IDs getRetweeterIds(long j, int i, long j2) {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "statuses/retweeters/ids.json?id=" + j + "&cursor=" + j2 + "&count=" + i));
    }

    public Status showStatus(long j) {
        return this.factory.createStatus(get(this.conf.getRestBaseURL() + "statuses/show/" + j + ".json", new HttpParameter[]{this.INCLUDE_MY_RETWEET}));
    }

    public Status destroyStatus(long j) {
        return this.factory.createStatus(post(this.conf.getRestBaseURL() + "statuses/destroy/" + j + ".json"));
    }

    public Status updateStatus(String str) {
        return this.factory.createStatus(post(this.conf.getRestBaseURL() + "statuses/update.json", new HttpParameter[]{new HttpParameter("status", str)}));
    }

    public Status updateStatus(StatusUpdate statusUpdate) {
        return this.factory.createStatus(post(this.conf.getRestBaseURL() + (statusUpdate.isForUpdateWithMedia() ? "statuses/update_with_media.json" : "statuses/update.json"), statusUpdate.asHttpParameterArray()));
    }

    public Status retweetStatus(long j) {
        return this.factory.createStatus(post(this.conf.getRestBaseURL() + "statuses/retweet/" + j + ".json"));
    }

    public OEmbed getOEmbed(OEmbedRequest oEmbedRequest) {
        return this.factory.createOEmbed(get(this.conf.getRestBaseURL() + "statuses/oembed.json", oEmbedRequest.asHttpParameterArray()));
    }

    public ResponseList<Status> lookup(long[] jArr) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "statuses/lookup.json?id=" + StringUtil.join(jArr)));
    }

    public UploadedMedia uploadMedia(File file) {
        checkFileValidity(file);
        return new UploadedMedia(post(this.conf.getUploadBaseURL() + "media/upload.json", new HttpParameter[]{new HttpParameter("media", file)}).asJSONObject());
    }

    public QueryResult search(Query query) {
        if (query.nextPage() != null) {
            return this.factory.createQueryResult(get(this.conf.getRestBaseURL() + "search/tweets.json" + query.nextPage()), query);
        }
        return this.factory.createQueryResult(get(this.conf.getRestBaseURL() + "search/tweets.json", query.asHttpParameterArray()), query);
    }

    public ResponseList<DirectMessage> getDirectMessages() {
        return this.factory.createDirectMessageList(get(this.conf.getRestBaseURL() + "direct_messages.json"));
    }

    public ResponseList<DirectMessage> getDirectMessages(Paging paging) {
        return this.factory.createDirectMessageList(get(this.conf.getRestBaseURL() + "direct_messages.json", paging.asPostParameterArray()));
    }

    public ResponseList<DirectMessage> getSentDirectMessages() {
        return this.factory.createDirectMessageList(get(this.conf.getRestBaseURL() + "direct_messages/sent.json"));
    }

    public ResponseList<DirectMessage> getSentDirectMessages(Paging paging) {
        return this.factory.createDirectMessageList(get(this.conf.getRestBaseURL() + "direct_messages/sent.json", paging.asPostParameterArray()));
    }

    public DirectMessage showDirectMessage(long j) {
        return this.factory.createDirectMessage(get(this.conf.getRestBaseURL() + "direct_messages/show.json?id=" + j));
    }

    public DirectMessage destroyDirectMessage(long j) {
        return this.factory.createDirectMessage(post(this.conf.getRestBaseURL() + "direct_messages/destroy.json?id=" + j));
    }

    public DirectMessage sendDirectMessage(long j, String str) {
        return this.factory.createDirectMessage(post(this.conf.getRestBaseURL() + "direct_messages/new.json", new HttpParameter[]{new HttpParameter("user_id", j), new HttpParameter("text", str)}));
    }

    public DirectMessage sendDirectMessage(String str, String str2) {
        return this.factory.createDirectMessage(post(this.conf.getRestBaseURL() + "direct_messages/new.json", new HttpParameter[]{new HttpParameter("screen_name", str), new HttpParameter("text", str2)}));
    }

    public InputStream getDMImageAsStream(String str) {
        return get(str).asStream();
    }

    public IDs getNoRetweetsFriendships() {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friendships/no_retweets/ids.json"));
    }

    public IDs getFriendsIDs(long j) {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friends/ids.json?cursor=" + j));
    }

    public IDs getFriendsIDs(long j, long j2) {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friends/ids.json?user_id=" + j + "&cursor=" + j2));
    }

    public IDs getFriendsIDs(long j, long j2, int i) {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friends/ids.json?user_id=" + j + "&cursor=" + j2 + "&count=" + i));
    }

    public IDs getFriendsIDs(String str, long j) {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friends/ids.json?screen_name=" + str + "&cursor=" + j));
    }

    public IDs getFriendsIDs(String str, long j, int i) {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friends/ids.json?screen_name=" + str + "&cursor=" + j + "&count=" + i));
    }

    public IDs getFollowersIDs(long j) {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "followers/ids.json?cursor=" + j));
    }

    public IDs getFollowersIDs(long j, long j2) {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "followers/ids.json?user_id=" + j + "&cursor=" + j2));
    }

    public IDs getFollowersIDs(long j, long j2, int i) {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "followers/ids.json?user_id=" + j + "&cursor=" + j2 + "&count=" + i));
    }

    public IDs getFollowersIDs(String str, long j) {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "followers/ids.json?screen_name=" + str + "&cursor=" + j));
    }

    public IDs getFollowersIDs(String str, long j, int i) {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "followers/ids.json?screen_name=" + str + "&cursor=" + j + "&count=" + i));
    }

    public ResponseList<Friendship> lookupFriendships(long[] jArr) {
        return this.factory.createFriendshipList(get(this.conf.getRestBaseURL() + "friendships/lookup.json?user_id=" + StringUtil.join(jArr)));
    }

    public ResponseList<Friendship> lookupFriendships(String[] strArr) {
        return this.factory.createFriendshipList(get(this.conf.getRestBaseURL() + "friendships/lookup.json?screen_name=" + StringUtil.join(strArr)));
    }

    public IDs getIncomingFriendships(long j) {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friendships/incoming.json?cursor=" + j));
    }

    public IDs getOutgoingFriendships(long j) {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "friendships/outgoing.json?cursor=" + j));
    }

    public User createFriendship(long j) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "friendships/create.json?user_id=" + j));
    }

    public User createFriendship(String str) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "friendships/create.json?screen_name=" + str));
    }

    public User createFriendship(long j, boolean z) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "friendships/create.json?user_id=" + j + "&follow=" + z));
    }

    public User createFriendship(String str, boolean z) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "friendships/create.json?screen_name=" + str + "&follow=" + z));
    }

    public User destroyFriendship(long j) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "friendships/destroy.json?user_id=" + j));
    }

    public User destroyFriendship(String str) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "friendships/destroy.json?screen_name=" + str));
    }

    public Relationship updateFriendship(long j, boolean z, boolean z2) {
        return this.factory.createRelationship(post(this.conf.getRestBaseURL() + "friendships/update.json", new HttpParameter[]{new HttpParameter("user_id", j), new HttpParameter(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, z), new HttpParameter("retweets", z2)}));
    }

    public Relationship updateFriendship(String str, boolean z, boolean z2) {
        return this.factory.createRelationship(post(this.conf.getRestBaseURL() + "friendships/update.json", new HttpParameter[]{new HttpParameter("screen_name", str), new HttpParameter(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX, z), new HttpParameter("retweets", z2)}));
    }

    public Relationship showFriendship(long j, long j2) {
        return this.factory.createRelationship(get(this.conf.getRestBaseURL() + "friendships/show.json", new HttpParameter[]{new HttpParameter("source_id", j), new HttpParameter("target_id", j2)}));
    }

    public Relationship showFriendship(String str, String str2) {
        return this.factory.createRelationship(get(this.conf.getRestBaseURL() + "friendships/show.json", HttpParameter.getParameterArray("source_screen_name", str, "target_screen_name", str2)));
    }

    public PagableResponseList<User> getFriendsList(long j, long j2) {
        return getFriendsList(j, j2, 20);
    }

    public PagableResponseList<User> getFriendsList(long j, long j2, int i) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "friends/list.json?user_id=" + j + "&cursor=" + j2 + "&count=" + i));
    }

    public PagableResponseList<User> getFriendsList(String str, long j) {
        return getFriendsList(str, j, 20);
    }

    public PagableResponseList<User> getFriendsList(String str, long j, int i) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "friends/list.json?screen_name=" + str + "&cursor=" + j + "&count=" + i));
    }

    public PagableResponseList<User> getFriendsList(long j, long j2, int i, boolean z, boolean z2) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "friends/list.json?user_id=" + j + "&cursor=" + j2 + "&count=" + i + "&skip_status=" + z + "&include_user_entities=" + z2));
    }

    public PagableResponseList<User> getFriendsList(String str, long j, int i, boolean z, boolean z2) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "friends/list.json?screen_name=" + str + "&cursor=" + j + "&count=" + i + "&skip_status=" + z + "&include_user_entities=" + z2));
    }

    public PagableResponseList<User> getFollowersList(long j, long j2) {
        return getFollowersList(j, j2, 20);
    }

    public PagableResponseList<User> getFollowersList(String str, long j) {
        return getFollowersList(str, j, 20);
    }

    public PagableResponseList<User> getFollowersList(long j, long j2, int i) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "followers/list.json?user_id=" + j + "&cursor=" + j2 + "&count=" + i));
    }

    public PagableResponseList<User> getFollowersList(String str, long j, int i) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "followers/list.json?screen_name=" + str + "&cursor=" + j + "&count=" + i));
    }

    public PagableResponseList<User> getFollowersList(long j, long j2, int i, boolean z, boolean z2) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "followers/list.json?user_id=" + j + "&cursor=" + j2 + "&count=" + i + "&skip_status=" + z + "&include_user_entities=" + z2));
    }

    public PagableResponseList<User> getFollowersList(String str, long j, int i, boolean z, boolean z2) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "followers/list.json?screen_name=" + str + "&cursor=" + j + "&count=" + i + "&skip_status=" + z + "&include_user_entities=" + z2));
    }

    public AccountSettings getAccountSettings() {
        return this.factory.createAccountSettings(get(this.conf.getRestBaseURL() + "account/settings.json"));
    }

    public User verifyCredentials() {
        return super.fillInIDAndScreenName();
    }

    public AccountSettings updateAccountSettings(Integer num, Boolean bool, String str, String str2, String str3, String str4) {
        List arrayList = new ArrayList(6);
        if (num != null) {
            arrayList.add(new HttpParameter("trend_location_woeid", num.intValue()));
        }
        if (bool != null) {
            arrayList.add(new HttpParameter("sleep_time_enabled", bool.toString()));
        }
        if (str != null) {
            arrayList.add(new HttpParameter("start_sleep_time", str));
        }
        if (str2 != null) {
            arrayList.add(new HttpParameter("end_sleep_time", str2));
        }
        if (str3 != null) {
            arrayList.add(new HttpParameter("time_zone", str3));
        }
        if (str4 != null) {
            arrayList.add(new HttpParameter("lang", str4));
        }
        return this.factory.createAccountSettings(post(this.conf.getRestBaseURL() + "account/settings.json", (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()])));
    }

    public User updateProfile(String str, String str2, String str3, String str4) {
        List arrayList = new ArrayList(4);
        addParameterToList(arrayList, MediationMetaData.KEY_NAME, str);
        addParameterToList(arrayList, String.URL, str2);
        addParameterToList(arrayList, "location", str3);
        addParameterToList(arrayList, "description", str4);
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "account/update_profile.json", (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()])));
    }

    public User updateProfileBackgroundImage(File file, boolean z) {
        checkFileValidity(file);
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "account/update_profile_background_image.json", new HttpParameter[]{new HttpParameter("image", file), new HttpParameter("tile", z)}));
    }

    public User updateProfileBackgroundImage(InputStream inputStream, boolean z) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "account/update_profile_background_image.json", new HttpParameter[]{new HttpParameter("image", "image", inputStream), new HttpParameter("tile", z)}));
    }

    public User updateProfileColors(String str, String str2, String str3, String str4, String str5) {
        List arrayList = new ArrayList(6);
        addParameterToList(arrayList, "profile_background_color", str);
        addParameterToList(arrayList, "profile_text_color", str2);
        addParameterToList(arrayList, "profile_link_color", str3);
        addParameterToList(arrayList, "profile_sidebar_fill_color", str4);
        addParameterToList(arrayList, "profile_sidebar_border_color", str5);
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "account/update_profile_colors.json", (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()])));
    }

    private void addParameterToList(List<HttpParameter> list, String str, String str2) {
        if (str2 != null) {
            list.add(new HttpParameter(str, str2));
        }
    }

    public User updateProfileImage(File file) {
        checkFileValidity(file);
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "account/update_profile_image.json", new HttpParameter[]{new HttpParameter("image", file)}));
    }

    public User updateProfileImage(InputStream inputStream) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "account/update_profile_image.json", new HttpParameter[]{new HttpParameter("image", "image", inputStream)}));
    }

    private void checkFileValidity(File file) {
        if (!file.exists()) {
            throw new TwitterException(new FileNotFoundException(file + " is not found."));
        } else if (!file.isFile()) {
            throw new TwitterException(new IOException(file + " is not a file."));
        }
    }

    public PagableResponseList<User> getBlocksList() {
        return getBlocksList(-1);
    }

    public PagableResponseList<User> getBlocksList(long j) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "blocks/list.json?cursor=" + j));
    }

    public IDs getBlocksIDs() {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "blocks/ids.json"));
    }

    public IDs getBlocksIDs(long j) {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "blocks/ids.json?cursor=" + j));
    }

    public User createBlock(long j) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "blocks/create.json?user_id=" + j));
    }

    public User createBlock(String str) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "blocks/create.json?screen_name=" + str));
    }

    public User destroyBlock(long j) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "blocks/destroy.json?user_id=" + j));
    }

    public User destroyBlock(String str) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "blocks/destroy.json?screen_name=" + str));
    }

    public PagableResponseList<User> getMutesList(long j) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "mutes/users/list.json?cursor=" + j));
    }

    public IDs getMutesIDs(long j) {
        return this.factory.createIDs(get(this.conf.getRestBaseURL() + "mutes/users/ids.json?cursor=" + j));
    }

    public User createMute(long j) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "mutes/users/create.json?user_id=" + j));
    }

    public User createMute(String str) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "mutes/users/create.json?screen_name=" + str));
    }

    public User destroyMute(long j) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "mutes/users/destroy.json?user_id=" + j));
    }

    public User destroyMute(String str) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "mutes/users/destroy.json?screen_name=" + str));
    }

    public ResponseList<User> lookupUsers(long[] jArr) {
        return this.factory.createUserList(get(this.conf.getRestBaseURL() + "users/lookup.json", new HttpParameter[]{new HttpParameter("user_id", StringUtil.join(jArr))}));
    }

    public ResponseList<User> lookupUsers(String[] strArr) {
        return this.factory.createUserList(get(this.conf.getRestBaseURL() + "users/lookup.json", new HttpParameter[]{new HttpParameter("screen_name", StringUtil.join(strArr))}));
    }

    public User showUser(long j) {
        return this.factory.createUser(get(this.conf.getRestBaseURL() + "users/show.json?user_id=" + j));
    }

    public User showUser(String str) {
        return this.factory.createUser(get(this.conf.getRestBaseURL() + "users/show.json?screen_name=" + str));
    }

    public ResponseList<User> searchUsers(String str, int i) {
        return this.factory.createUserList(get(this.conf.getRestBaseURL() + "users/search.json", new HttpParameter[]{new HttpParameter("q", str), new HttpParameter("per_page", 20), new HttpParameter("page", i)}));
    }

    public ResponseList<User> getContributees(long j) {
        return this.factory.createUserList(get(this.conf.getRestBaseURL() + "users/contributees.json?user_id=" + j));
    }

    public ResponseList<User> getContributees(String str) {
        return this.factory.createUserList(get(this.conf.getRestBaseURL() + "users/contributees.json?screen_name=" + str));
    }

    public ResponseList<User> getContributors(long j) {
        return this.factory.createUserList(get(this.conf.getRestBaseURL() + "users/contributors.json?user_id=" + j));
    }

    public ResponseList<User> getContributors(String str) {
        return this.factory.createUserList(get(this.conf.getRestBaseURL() + "users/contributors.json?screen_name=" + str));
    }

    public void removeProfileBanner() {
        post(this.conf.getRestBaseURL() + "account/remove_profile_banner.json");
    }

    public void updateProfileBanner(File file) {
        checkFileValidity(file);
        post(this.conf.getRestBaseURL() + "account/update_profile_banner.json", new HttpParameter[]{new HttpParameter("banner", file)});
    }

    public void updateProfileBanner(InputStream inputStream) {
        post(this.conf.getRestBaseURL() + "account/update_profile_banner.json", new HttpParameter[]{new HttpParameter("banner", "banner", inputStream)});
    }

    public ResponseList<User> getUserSuggestions(String str) {
        try {
            return this.factory.createUserListFromJSONArray_Users(get(this.conf.getRestBaseURL() + "users/suggestions/" + URLEncoder.encode(str, c.DEFAULT_CHARSET) + ".json"));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseList<Category> getSuggestedUserCategories() {
        return this.factory.createCategoryList(get(this.conf.getRestBaseURL() + "users/suggestions.json"));
    }

    public ResponseList<User> getMemberSuggestions(String str) {
        try {
            return this.factory.createUserListFromJSONArray(get(this.conf.getRestBaseURL() + "users/suggestions/" + URLEncoder.encode(str, c.DEFAULT_CHARSET) + "/members.json"));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseList<Status> getFavorites() {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "favorites/list.json"));
    }

    public ResponseList<Status> getFavorites(long j) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "favorites/list.json?user_id=" + j));
    }

    public ResponseList<Status> getFavorites(String str) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "favorites/list.json?screen_name=" + str));
    }

    public ResponseList<Status> getFavorites(Paging paging) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "favorites/list.json", paging.asPostParameterArray()));
    }

    public ResponseList<Status> getFavorites(long j, Paging paging) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "favorites/list.json", mergeParameters(new HttpParameter[]{new HttpParameter("user_id", j)}, paging.asPostParameterArray())));
    }

    public ResponseList<Status> getFavorites(String str, Paging paging) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "favorites/list.json", mergeParameters(new HttpParameter[]{new HttpParameter("screen_name", str)}, paging.asPostParameterArray())));
    }

    public Status destroyFavorite(long j) {
        return this.factory.createStatus(post(this.conf.getRestBaseURL() + "favorites/destroy.json?id=" + j));
    }

    public Status createFavorite(long j) {
        return this.factory.createStatus(post(this.conf.getRestBaseURL() + "favorites/create.json?id=" + j));
    }

    public ResponseList<UserList> getUserLists(String str) {
        return this.factory.createUserListList(get(this.conf.getRestBaseURL() + "lists/list.json?screen_name=" + str));
    }

    public ResponseList<UserList> getUserLists(long j) {
        return this.factory.createUserListList(get(this.conf.getRestBaseURL() + "lists/list.json?user_id=" + j));
    }

    public ResponseList<Status> getUserListStatuses(long j, Paging paging) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "lists/statuses.json", mergeParameters(paging.asPostParameterArray(Paging.SMCP, "count"), new HttpParameter("list_id", j))));
    }

    public ResponseList<Status> getUserListStatuses(long j, String str, Paging paging) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "lists/statuses.json", mergeParameters(paging.asPostParameterArray(Paging.SMCP, "count"), new HttpParameter[]{new HttpParameter("owner_id", j), new HttpParameter("slug", str)})));
    }

    public ResponseList<Status> getUserListStatuses(String str, String str2, Paging paging) {
        return this.factory.createStatusList(get(this.conf.getRestBaseURL() + "lists/statuses.json", mergeParameters(paging.asPostParameterArray(Paging.SMCP, "count"), new HttpParameter[]{new HttpParameter("owner_screen_name", str), new HttpParameter("slug", str2)})));
    }

    public UserList destroyUserListMember(long j, long j2) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/destroy.json", new HttpParameter[]{new HttpParameter("list_id", j), new HttpParameter("user_id", j2)}));
    }

    public UserList destroyUserListMember(long j, String str, long j2) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/destroy.json", new HttpParameter[]{new HttpParameter("owner_id", j), new HttpParameter("slug", str), new HttpParameter("user_id", j2)}));
    }

    public UserList destroyUserListMember(long j, String str) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/destroy.json", new HttpParameter[]{new HttpParameter("list_id", j), new HttpParameter("screen_name", str)}));
    }

    public UserList destroyUserListMember(String str, String str2, long j) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/destroy.json", new HttpParameter[]{new HttpParameter("owner_screen_name", str), new HttpParameter("slug", str2), new HttpParameter("user_id", j)}));
    }

    public UserList destroyUserListMembers(long j, String[] strArr) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/destroy_all.json", new HttpParameter[]{new HttpParameter("list_id", j), new HttpParameter("screen_name", StringUtil.join(strArr))}));
    }

    public UserList destroyUserListMembers(long j, long[] jArr) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/destroy_all.json", new HttpParameter[]{new HttpParameter("list_id", j), new HttpParameter("user_id", StringUtil.join(jArr))}));
    }

    public UserList destroyUserListMembers(String str, String str2, String[] strArr) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/destroy_all.json", new HttpParameter[]{new HttpParameter("owner_screen_name", str), new HttpParameter("slug", str2), new HttpParameter("screen_name", StringUtil.join(strArr))}));
    }

    public PagableResponseList<UserList> getUserListMemberships(long j) {
        return this.factory.createPagableUserListList(get(this.conf.getRestBaseURL() + "lists/memberships.json?cursor=" + j));
    }

    public PagableResponseList<UserList> getUserListMemberships(String str, long j) {
        return getUserListMemberships(str, j, false);
    }

    public PagableResponseList<UserList> getUserListMemberships(long j, long j2) {
        return getUserListMemberships(j, j2, false);
    }

    public PagableResponseList<UserList> getUserListMemberships(long j, long j2, boolean z) {
        return this.factory.createPagableUserListList(get(this.conf.getRestBaseURL() + "lists/memberships.json?user_id=" + j + "&cursor=" + j2 + "&filter_to_owned_lists=" + z));
    }

    public PagableResponseList<UserList> getUserListMemberships(String str, long j, boolean z) {
        return this.factory.createPagableUserListList(get(this.conf.getRestBaseURL() + "lists/memberships.json?screen_name=" + str + "&cursor=" + j + "&filter_to_owned_lists=" + z));
    }

    public PagableResponseList<User> getUserListSubscribers(long j, long j2) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "lists/subscribers.json?list_id=" + j + "&cursor=" + j2));
    }

    public PagableResponseList<User> getUserListSubscribers(long j, String str, long j2) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "lists/subscribers.json?owner_id=" + j + "&slug=" + str + "&cursor=" + j2));
    }

    public PagableResponseList<User> getUserListSubscribers(String str, String str2, long j) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "lists/subscribers.json?owner_screen_name=" + str + "&slug=" + str2 + "&cursor=" + j));
    }

    public UserList createUserListSubscription(long j) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/subscribers/create.json", new HttpParameter[]{new HttpParameter("list_id", j)}));
    }

    public UserList createUserListSubscription(long j, String str) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/subscribers/create.json", new HttpParameter[]{new HttpParameter("owner_id", j), new HttpParameter("slug", str)}));
    }

    public UserList createUserListSubscription(String str, String str2) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/subscribers/create.json", new HttpParameter[]{new HttpParameter("owner_screen_name", str), new HttpParameter("slug", str2)}));
    }

    public User showUserListSubscription(long j, long j2) {
        return this.factory.createUser(get(this.conf.getRestBaseURL() + "lists/subscribers/show.json?list_id=" + j + "&user_id=" + j2));
    }

    public User showUserListSubscription(long j, String str, long j2) {
        return this.factory.createUser(get(this.conf.getRestBaseURL() + "lists/subscribers/show.json?owner_id=" + j + "&slug=" + str + "&user_id=" + j2));
    }

    public User showUserListSubscription(String str, String str2, long j) {
        return this.factory.createUser(get(this.conf.getRestBaseURL() + "lists/subscribers/show.json?owner_screen_name=" + str + "&slug=" + str2 + "&user_id=" + j));
    }

    public UserList destroyUserListSubscription(long j) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/subscribers/destroy.json", new HttpParameter[]{new HttpParameter("list_id", j)}));
    }

    public UserList destroyUserListSubscription(long j, String str) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/subscribers/destroy.json", new HttpParameter[]{new HttpParameter("owner_id", j), new HttpParameter("slug", str)}));
    }

    public UserList destroyUserListSubscription(String str, String str2) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/subscribers/destroy.json", new HttpParameter[]{new HttpParameter("owner_screen_name", str), new HttpParameter("slug", str2)}));
    }

    public UserList createUserListMembers(long j, long[] jArr) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create_all.json", new HttpParameter[]{new HttpParameter("list_id", j), new HttpParameter("user_id", StringUtil.join(jArr))}));
    }

    public UserList createUserListMembers(long j, String str, long[] jArr) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create_all.json", new HttpParameter[]{new HttpParameter("owner_id", j), new HttpParameter("slug", str), new HttpParameter("user_id", StringUtil.join(jArr))}));
    }

    public UserList createUserListMembers(String str, String str2, long[] jArr) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create_all.json", new HttpParameter[]{new HttpParameter("owner_screen_name", str), new HttpParameter("slug", str2), new HttpParameter("user_id", StringUtil.join(jArr))}));
    }

    public UserList createUserListMembers(long j, String[] strArr) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create_all.json", new HttpParameter[]{new HttpParameter("list_id", j), new HttpParameter("screen_name", StringUtil.join(strArr))}));
    }

    public UserList createUserListMembers(long j, String str, String[] strArr) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create_all.json", new HttpParameter[]{new HttpParameter("owner_id", j), new HttpParameter("slug", str), new HttpParameter("screen_name", StringUtil.join(strArr))}));
    }

    public UserList createUserListMembers(String str, String str2, String[] strArr) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create_all.json", new HttpParameter[]{new HttpParameter("owner_screen_name", str), new HttpParameter("slug", str2), new HttpParameter("screen_name", StringUtil.join(strArr))}));
    }

    public User showUserListMembership(long j, long j2) {
        return this.factory.createUser(get(this.conf.getRestBaseURL() + "lists/members/show.json?list_id=" + j + "&user_id=" + j2));
    }

    public User showUserListMembership(long j, String str, long j2) {
        return this.factory.createUser(get(this.conf.getRestBaseURL() + "lists/members/show.json?owner_id=" + j + "&slug=" + str + "&user_id=" + j2));
    }

    public User showUserListMembership(String str, String str2, long j) {
        return this.factory.createUser(get(this.conf.getRestBaseURL() + "lists/members/show.json?owner_screen_name=" + str + "&slug=" + str2 + "&user_id=" + j));
    }

    public PagableResponseList<User> getUserListMembers(long j, long j2) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "lists/members.json?list_id=" + j + "&cursor=" + j2));
    }

    public PagableResponseList<User> getUserListMembers(long j, String str, long j2) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "lists/members.json?owner_id=" + j + "&slug=" + str + "&cursor=" + j2));
    }

    public PagableResponseList<User> getUserListMembers(String str, String str2, long j) {
        return this.factory.createPagableUserList(get(this.conf.getRestBaseURL() + "lists/members.json?owner_screen_name=" + str + "&slug=" + str2 + "&cursor=" + j));
    }

    public UserList createUserListMember(long j, long j2) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create.json", new HttpParameter[]{new HttpParameter("user_id", j2), new HttpParameter("list_id", j)}));
    }

    public UserList createUserListMember(long j, String str, long j2) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create.json", new HttpParameter[]{new HttpParameter("user_id", j2), new HttpParameter("owner_id", j), new HttpParameter("slug", str)}));
    }

    public UserList createUserListMember(String str, String str2, long j) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/members/create.json", new HttpParameter[]{new HttpParameter("user_id", j), new HttpParameter("owner_screen_name", str), new HttpParameter("slug", str2)}));
    }

    public UserList destroyUserList(long j) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/destroy.json", new HttpParameter[]{new HttpParameter("list_id", j)}));
    }

    public UserList destroyUserList(long j, String str) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/destroy.json", new HttpParameter[]{new HttpParameter("owner_id", j), new HttpParameter("slug", str)}));
    }

    public UserList destroyUserList(String str, String str2) {
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/destroy.json", new HttpParameter[]{new HttpParameter("owner_screen_name", str), new HttpParameter("slug", str2)}));
    }

    public UserList updateUserList(long j, String str, boolean z, String str2) {
        return updateUserList(str, z, str2, new HttpParameter("list_id", j));
    }

    public UserList updateUserList(long j, String str, String str2, boolean z, String str3) {
        return updateUserList(str2, z, str3, new HttpParameter("owner_id", j), new HttpParameter("slug", str));
    }

    public UserList updateUserList(String str, String str2, String str3, boolean z, String str4) {
        return updateUserList(str3, z, str4, new HttpParameter("owner_screen_name", str), new HttpParameter("slug", str2));
    }

    private UserList updateUserList(String str, boolean z, String str2, HttpParameter... httpParameterArr) {
        List arrayList = new ArrayList();
        Collections.addAll(arrayList, httpParameterArr);
        if (str != null) {
            arrayList.add(new HttpParameter(MediationMetaData.KEY_NAME, str));
        }
        arrayList.add(new HttpParameter("mode", z ? "public" : "private"));
        if (str2 != null) {
            arrayList.add(new HttpParameter("description", str2));
        }
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/update.json", (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()])));
    }

    public UserList createUserList(String str, boolean z, String str2) {
        List arrayList = new ArrayList();
        arrayList.add(new HttpParameter(MediationMetaData.KEY_NAME, str));
        arrayList.add(new HttpParameter("mode", z ? "public" : "private"));
        if (str2 != null) {
            arrayList.add(new HttpParameter("description", str2));
        }
        return this.factory.createAUserList(post(this.conf.getRestBaseURL() + "lists/create.json", (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()])));
    }

    public UserList showUserList(long j) {
        return this.factory.createAUserList(get(this.conf.getRestBaseURL() + "lists/show.json?list_id=" + j));
    }

    public UserList showUserList(long j, String str) {
        return this.factory.createAUserList(get(this.conf.getRestBaseURL() + "lists/show.json?owner_id=" + j + "&slug=" + str));
    }

    public UserList showUserList(String str, String str2) {
        return this.factory.createAUserList(get(this.conf.getRestBaseURL() + "lists/show.json?owner_screen_name=" + str + "&slug=" + str2));
    }

    public PagableResponseList<UserList> getUserListSubscriptions(String str, long j) {
        return this.factory.createPagableUserListList(get(this.conf.getRestBaseURL() + "lists/subscriptions.json?screen_name=" + str + "&cursor=" + j));
    }

    public PagableResponseList<UserList> getUserListsOwnerships(String str, int i, long j) {
        return this.factory.createPagableUserListList(get(this.conf.getRestBaseURL() + "lists/ownerships.json", new HttpParameter[]{new HttpParameter("screen_name", str), new HttpParameter("count", i), new HttpParameter("cursor", j)}));
    }

    public PagableResponseList<UserList> getUserListsOwnerships(long j, int i, long j2) {
        return this.factory.createPagableUserListList(get(this.conf.getRestBaseURL() + "lists/ownerships.json", new HttpParameter[]{new HttpParameter("user_id", j), new HttpParameter("count", i), new HttpParameter("cursor", j2)}));
    }

    public ResponseList<SavedSearch> getSavedSearches() {
        return this.factory.createSavedSearchList(get(this.conf.getRestBaseURL() + "saved_searches/list.json"));
    }

    public SavedSearch showSavedSearch(int i) {
        return this.factory.createSavedSearch(get(this.conf.getRestBaseURL() + "saved_searches/show/" + i + ".json"));
    }

    public SavedSearch createSavedSearch(String str) {
        return this.factory.createSavedSearch(post(this.conf.getRestBaseURL() + "saved_searches/create.json", new HttpParameter[]{new HttpParameter("query", str)}));
    }

    public SavedSearch destroySavedSearch(int i) {
        return this.factory.createSavedSearch(post(this.conf.getRestBaseURL() + "saved_searches/destroy/" + i + ".json"));
    }

    public Place getGeoDetails(String str) {
        return this.factory.createPlace(get(this.conf.getRestBaseURL() + "geo/id/" + str + ".json"));
    }

    public ResponseList<Place> reverseGeoCode(GeoQuery geoQuery) {
        try {
            return this.factory.createPlaceList(get(this.conf.getRestBaseURL() + "geo/reverse_geocode.json", geoQuery.asHttpParameterArray()));
        } catch (TwitterException e) {
            if (e.getStatusCode() == HttpResponseCode.NOT_FOUND) {
                return this.factory.createEmptyResponseList();
            }
            throw e;
        }
    }

    public ResponseList<Place> searchPlaces(GeoQuery geoQuery) {
        return this.factory.createPlaceList(get(this.conf.getRestBaseURL() + "geo/search.json", geoQuery.asHttpParameterArray()));
    }

    public ResponseList<Place> getSimilarPlaces(GeoLocation geoLocation, String str, String str2, String str3) {
        List arrayList = new ArrayList(3);
        arrayList.add(new HttpParameter(String.LAT, geoLocation.getLatitude()));
        arrayList.add(new HttpParameter(String.LONG, geoLocation.getLongitude()));
        arrayList.add(new HttpParameter(MediationMetaData.KEY_NAME, str));
        if (str2 != null) {
            arrayList.add(new HttpParameter("contained_within", str2));
        }
        if (str3 != null) {
            arrayList.add(new HttpParameter("attribute:street_address", str3));
        }
        return this.factory.createPlaceList(get(this.conf.getRestBaseURL() + "geo/similar_places.json", (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()])));
    }

    public Trends getPlaceTrends(int i) {
        return this.factory.createTrends(get(this.conf.getRestBaseURL() + "trends/place.json?id=" + i));
    }

    public ResponseList<Location> getAvailableTrends() {
        return this.factory.createLocationList(get(this.conf.getRestBaseURL() + "trends/available.json"));
    }

    public ResponseList<Location> getClosestTrends(GeoLocation geoLocation) {
        return this.factory.createLocationList(get(this.conf.getRestBaseURL() + "trends/closest.json", new HttpParameter[]{new HttpParameter(String.LAT, geoLocation.getLatitude()), new HttpParameter(String.LONG, geoLocation.getLongitude())}));
    }

    public User reportSpam(long j) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "users/report_spam.json?user_id=" + j));
    }

    public User reportSpam(String str) {
        return this.factory.createUser(post(this.conf.getRestBaseURL() + "users/report_spam.json?screen_name=" + str));
    }

    public TwitterAPIConfiguration getAPIConfiguration() {
        return this.factory.createTwitterAPIConfiguration(get(this.conf.getRestBaseURL() + "help/configuration.json"));
    }

    public ResponseList<Language> getLanguages() {
        return this.factory.createLanguageList(get(this.conf.getRestBaseURL() + "help/languages.json"));
    }

    public String getPrivacyPolicy() {
        try {
            return get(this.conf.getRestBaseURL() + "help/privacy.json").asJSONObject().getString("privacy");
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public String getTermsOfService() {
        try {
            return get(this.conf.getRestBaseURL() + "help/tos.json").asJSONObject().getString("tos");
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public Map<String, RateLimitStatus> getRateLimitStatus() {
        return this.factory.createRateLimitStatuses(get(this.conf.getRestBaseURL() + "application/rate_limit_status.json"));
    }

    public Map<String, RateLimitStatus> getRateLimitStatus(String... strArr) {
        return this.factory.createRateLimitStatuses(get(this.conf.getRestBaseURL() + "application/rate_limit_status.json?resources=" + StringUtil.join(strArr)));
    }

    public TimelinesResources timelines() {
        return this;
    }

    public TweetsResources tweets() {
        return this;
    }

    public SearchResource search() {
        return this;
    }

    public DirectMessagesResources directMessages() {
        return this;
    }

    public FriendsFollowersResources friendsFollowers() {
        return this;
    }

    public UsersResources users() {
        return this;
    }

    public SuggestedUsersResources suggestedUsers() {
        return this;
    }

    public FavoritesResources favorites() {
        return this;
    }

    public ListsResources list() {
        return this;
    }

    public SavedSearchesResources savedSearches() {
        return this;
    }

    public PlacesGeoResources placesGeo() {
        return this;
    }

    public TrendsResources trends() {
        return this;
    }

    public SpamReportingResource spamReporting() {
        return this;
    }

    public HelpResources help() {
        return this;
    }

    private HttpResponse get(String str) {
        HttpResponse httpResponse = null;
        ensureAuthorizationEnabled();
        if (this.IMPLICIT_PARAMS_STR.length() > 0) {
            if (str.contains("?")) {
                str = str + "&" + this.IMPLICIT_PARAMS_STR;
            } else {
                str = str + "?" + this.IMPLICIT_PARAMS_STR;
            }
        }
        if (!this.conf.isMBeanEnabled()) {
            return this.http.get(str, null, this.auth, this);
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            httpResponse = this.http.get(str, null, this.auth, this);
        } finally {
            TwitterAPIMonitor.getInstance().methodCalled(str, System.currentTimeMillis() - currentTimeMillis, isOk(httpResponse));
        }
        return httpResponse;
    }

    private twitter4j.HttpResponse get(java.lang.String r7, twitter4j.HttpParameter[] r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Assign predecessor not found for B:5:? from B:13:?
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMerge(EliminatePhiNodes.java:102)
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMergeInstructions(EliminatePhiNodes.java:68)
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.visit(EliminatePhiNodes.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
        /*
        r6 = this;
        r6.ensureAuthorizationEnabled();
        r0 = r6.conf;
        r0 = r0.isMBeanEnabled();
        if (r0 != 0) goto L_0x0018;
    L_0x000b:
        r0 = r6.http;
        r1 = r6.mergeImplicitParams(r8);
        r2 = r6.auth;
        r0 = r0.get(r7, r1, r2, r6);
    L_0x0017:
        return r0;
    L_0x0018:
        r1 = 0;
        r2 = java.lang.System.currentTimeMillis();
        r0 = r6.http;	 Catch:{ all -> 0x003b }
        r4 = r6.mergeImplicitParams(r8);	 Catch:{ all -> 0x003b }
        r5 = r6.auth;	 Catch:{ all -> 0x003b }
        r0 = r0.get(r7, r4, r5, r6);	 Catch:{ all -> 0x003b }
        r4 = java.lang.System.currentTimeMillis();
        r2 = r4 - r2;
        r1 = twitter4j.TwitterAPIMonitor.getInstance();
        r4 = r6.isOk(r0);
        r1.methodCalled(r7, r2, r4);
        goto L_0x0017;
    L_0x003b:
        r0 = move-exception;
        r4 = java.lang.System.currentTimeMillis();
        r2 = r4 - r2;
        r4 = twitter4j.TwitterAPIMonitor.getInstance();
        r1 = r6.isOk(r1);
        r4.methodCalled(r7, r2, r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: twitter4j.TwitterImpl.get(java.lang.String, twitter4j.HttpParameter[]):twitter4j.HttpResponse");
    }

    private twitter4j.HttpResponse post(java.lang.String r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Assign predecessor not found for B:5:? from B:13:?
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMerge(EliminatePhiNodes.java:102)
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMergeInstructions(EliminatePhiNodes.java:68)
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.visit(EliminatePhiNodes.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
        /*
        r6 = this;
        r6.ensureAuthorizationEnabled();
        r0 = r6.conf;
        r0 = r0.isMBeanEnabled();
        if (r0 != 0) goto L_0x0016;
    L_0x000b:
        r0 = r6.http;
        r1 = r6.IMPLICIT_PARAMS;
        r2 = r6.auth;
        r0 = r0.post(r7, r1, r2, r6);
    L_0x0015:
        return r0;
    L_0x0016:
        r1 = 0;
        r2 = java.lang.System.currentTimeMillis();
        r0 = r6.http;	 Catch:{ all -> 0x0037 }
        r4 = r6.IMPLICIT_PARAMS;	 Catch:{ all -> 0x0037 }
        r5 = r6.auth;	 Catch:{ all -> 0x0037 }
        r0 = r0.post(r7, r4, r5, r6);	 Catch:{ all -> 0x0037 }
        r4 = java.lang.System.currentTimeMillis();
        r2 = r4 - r2;
        r1 = twitter4j.TwitterAPIMonitor.getInstance();
        r4 = r6.isOk(r0);
        r1.methodCalled(r7, r2, r4);
        goto L_0x0015;
    L_0x0037:
        r0 = move-exception;
        r4 = java.lang.System.currentTimeMillis();
        r2 = r4 - r2;
        r4 = twitter4j.TwitterAPIMonitor.getInstance();
        r1 = r6.isOk(r1);
        r4.methodCalled(r7, r2, r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: twitter4j.TwitterImpl.post(java.lang.String):twitter4j.HttpResponse");
    }

    private twitter4j.HttpResponse post(java.lang.String r7, twitter4j.HttpParameter[] r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Assign predecessor not found for B:5:? from B:13:?
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMerge(EliminatePhiNodes.java:102)
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.replaceMergeInstructions(EliminatePhiNodes.java:68)
	at jadx.core.dex.visitors.ssa.EliminatePhiNodes.visit(EliminatePhiNodes.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
        /*
        r6 = this;
        r6.ensureAuthorizationEnabled();
        r0 = r6.conf;
        r0 = r0.isMBeanEnabled();
        if (r0 != 0) goto L_0x0018;
    L_0x000b:
        r0 = r6.http;
        r1 = r6.mergeImplicitParams(r8);
        r2 = r6.auth;
        r0 = r0.post(r7, r1, r2, r6);
    L_0x0017:
        return r0;
    L_0x0018:
        r1 = 0;
        r2 = java.lang.System.currentTimeMillis();
        r0 = r6.http;	 Catch:{ all -> 0x003b }
        r4 = r6.mergeImplicitParams(r8);	 Catch:{ all -> 0x003b }
        r5 = r6.auth;	 Catch:{ all -> 0x003b }
        r0 = r0.post(r7, r4, r5, r6);	 Catch:{ all -> 0x003b }
        r4 = java.lang.System.currentTimeMillis();
        r2 = r4 - r2;
        r1 = twitter4j.TwitterAPIMonitor.getInstance();
        r4 = r6.isOk(r0);
        r1.methodCalled(r7, r2, r4);
        goto L_0x0017;
    L_0x003b:
        r0 = move-exception;
        r4 = java.lang.System.currentTimeMillis();
        r2 = r4 - r2;
        r4 = twitter4j.TwitterAPIMonitor.getInstance();
        r1 = r6.isOk(r1);
        r4.methodCalled(r7, r2, r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: twitter4j.TwitterImpl.post(java.lang.String, twitter4j.HttpParameter[]):twitter4j.HttpResponse");
    }

    private HttpParameter[] mergeParameters(HttpParameter[] httpParameterArr, HttpParameter[] httpParameterArr2) {
        if (httpParameterArr != null && httpParameterArr2 != null) {
            Object obj = new HttpParameter[(httpParameterArr.length + httpParameterArr2.length)];
            System.arraycopy(httpParameterArr, 0, obj, 0, httpParameterArr.length);
            System.arraycopy(httpParameterArr2, 0, obj, httpParameterArr.length, httpParameterArr2.length);
            return obj;
        } else if (httpParameterArr == null && httpParameterArr2 == null) {
            return new HttpParameter[0];
        } else {
            if (httpParameterArr == null) {
                return httpParameterArr2;
            }
            return httpParameterArr;
        }
    }

    private HttpParameter[] mergeParameters(HttpParameter[] httpParameterArr, HttpParameter httpParameter) {
        if (httpParameterArr != null && httpParameter != null) {
            Object obj = new HttpParameter[(httpParameterArr.length + 1)];
            System.arraycopy(httpParameterArr, 0, obj, 0, httpParameterArr.length);
            obj[obj.length - 1] = httpParameter;
            return obj;
        } else if (httpParameterArr == null && httpParameter == null) {
            return new HttpParameter[0];
        } else {
            if (httpParameterArr != null) {
                return httpParameterArr;
            }
            return new HttpParameter[]{httpParameter};
        }
    }

    private HttpParameter[] mergeImplicitParams(HttpParameter[] httpParameterArr) {
        return mergeParameters(httpParameterArr, this.IMPLICIT_PARAMS);
    }

    private boolean isOk(HttpResponse httpResponse) {
        return httpResponse != null && httpResponse.getStatusCode() < HttpResponseCode.MULTIPLE_CHOICES;
    }

    public String toString() {
        return "TwitterImpl{INCLUDE_MY_RETWEET=" + this.INCLUDE_MY_RETWEET + '}';
    }
}
