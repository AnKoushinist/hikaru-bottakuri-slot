package twitter4j;

import java.util.HashMap;
import java.util.Map;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.JSONObjectType.Type;

public final class TwitterObjectFactory {
    private static final ThreadLocal<Map> rawJsonMap = new ThreadLocal<Map>() {
        protected Map initialValue() {
            return new HashMap();
        }
    };
    private static boolean registeredAtleastOnce = false;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$twitter4j$JSONObjectType$Type = new int[Type.values().length];

        static {
            try {
                $SwitchMap$twitter4j$JSONObjectType$Type[Type.SENDER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$twitter4j$JSONObjectType$Type[Type.STATUS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$twitter4j$JSONObjectType$Type[Type.DIRECT_MESSAGE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$twitter4j$JSONObjectType$Type[Type.DELETE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$twitter4j$JSONObjectType$Type[Type.LIMIT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$twitter4j$JSONObjectType$Type[Type.SCRUB_GEO.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    private TwitterObjectFactory() {
        throw new AssertionError("not intended to be instantiated.");
    }

    public static String getRawJSON(Object obj) {
        if (registeredAtleastOnce) {
            Object obj2 = ((Map) rawJsonMap.get()).get(obj);
            if (obj2 instanceof String) {
                return (String) obj2;
            }
            if (obj2 != null) {
                return obj2.toString();
            }
            return null;
        }
        throw new IllegalStateException("Apparently jsonStoreEnabled is not set to true.");
    }

    public static Status createStatus(String str) {
        try {
            return new StatusJSONImpl(new JSONObject(str));
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public static User createUser(String str) {
        try {
            return new UserJSONImpl(new JSONObject(str));
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public static AccountTotals createAccountTotals(String str) {
        try {
            return new AccountTotalsJSONImpl(new JSONObject(str));
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public static Relationship createRelationship(String str) {
        try {
            return new RelationshipJSONImpl(new JSONObject(str));
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public static Place createPlace(String str) {
        try {
            return new PlaceJSONImpl(new JSONObject(str));
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public static SavedSearch createSavedSearch(String str) {
        try {
            return new SavedSearchJSONImpl(new JSONObject(str));
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public static Trend createTrend(String str) {
        try {
            return new TrendJSONImpl(new JSONObject(str));
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public static Trends createTrends(String str) {
        return new TrendsJSONImpl(str);
    }

    public static IDs createIDs(String str) {
        return new IDsJSONImpl(str);
    }

    public static Map<String, RateLimitStatus> createRateLimitStatus(String str) {
        try {
            return RateLimitStatusJSONImpl.createRateLimitStatuses(new JSONObject(str));
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public static Category createCategory(String str) {
        try {
            return new CategoryJSONImpl(new JSONObject(str));
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public static DirectMessage createDirectMessage(String str) {
        try {
            return new DirectMessageJSONImpl(new JSONObject(str));
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public static Location createLocation(String str) {
        try {
            return new LocationJSONImpl(new JSONObject(str));
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public static UserList createUserList(String str) {
        try {
            return new UserListJSONImpl(new JSONObject(str));
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public static OEmbed createOEmbed(String str) {
        try {
            return new OEmbedJSONImpl(new JSONObject(str));
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    public static Object createObject(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            switch (AnonymousClass2.$SwitchMap$twitter4j$JSONObjectType$Type[JSONObjectType.determine(jSONObject).ordinal()]) {
                case TwitterResponse.READ /*1*/:
                    return registerJSONObject(new DirectMessageJSONImpl(jSONObject.getJSONObject("direct_message")), jSONObject);
                case TwitterResponse.READ_WRITE /*2*/:
                    return registerJSONObject(new StatusJSONImpl(jSONObject), jSONObject);
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    return registerJSONObject(new DirectMessageJSONImpl(jSONObject.getJSONObject("direct_message")), jSONObject);
                case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                    return registerJSONObject(new StatusDeletionNoticeImpl(jSONObject.getJSONObject("delete").getJSONObject("status")), jSONObject);
                default:
                    return jSONObject;
            }
        } catch (Exception e) {
            throw new TwitterException(e);
        }
    }

    static void clearThreadLocalMap() {
        ((Map) rawJsonMap.get()).clear();
    }

    static <T> T registerJSONObject(T t, Object obj) {
        registeredAtleastOnce = true;
        ((Map) rawJsonMap.get()).put(t, obj);
        return t;
    }
}
