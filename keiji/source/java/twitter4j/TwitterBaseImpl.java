package twitter4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import twitter4j.auth.AccessToken;
import twitter4j.auth.Authorization;
import twitter4j.auth.AuthorizationFactory;
import twitter4j.auth.BasicAuthorization;
import twitter4j.auth.NullAuthorization;
import twitter4j.auth.OAuth2Authorization;
import twitter4j.auth.OAuth2Support;
import twitter4j.auth.OAuth2Token;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.OAuthSupport;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;

abstract class TwitterBaseImpl implements Serializable, HttpResponseListener, TwitterBase, OAuth2Support, OAuthSupport {
    private static final String WWW_DETAILS = "See http://twitter4j.org/en/configuration.html for details";
    private static final long serialVersionUID = -7824361938865528554L;
    Authorization auth;
    Configuration conf;
    ObjectFactory factory;
    transient HttpClient http;
    private transient long id = 0;
    private List<RateLimitStatusListener> rateLimitStatusListeners = new ArrayList(0);
    private transient String screenName = null;

    TwitterBaseImpl(Configuration configuration, Authorization authorization) {
        this.conf = configuration;
        this.auth = authorization;
        init();
    }

    private void init() {
        if (this.auth == null) {
            String oAuthConsumerKey = this.conf.getOAuthConsumerKey();
            String oAuthConsumerSecret = this.conf.getOAuthConsumerSecret();
            if (oAuthConsumerKey == null || oAuthConsumerSecret == null) {
                this.auth = NullAuthorization.getInstance();
            } else if (this.conf.isApplicationOnlyAuthEnabled()) {
                r0 = new OAuth2Authorization(this.conf);
                oAuthConsumerSecret = this.conf.getOAuth2TokenType();
                r2 = this.conf.getOAuth2AccessToken();
                if (!(oAuthConsumerSecret == null || r2 == null)) {
                    r0.setOAuth2Token(new OAuth2Token(oAuthConsumerSecret, r2));
                }
                this.auth = r0;
            } else {
                r0 = new OAuthAuthorization(this.conf);
                oAuthConsumerSecret = this.conf.getOAuthAccessToken();
                r2 = this.conf.getOAuthAccessTokenSecret();
                if (!(oAuthConsumerSecret == null || r2 == null)) {
                    r0.setOAuthAccessToken(new AccessToken(oAuthConsumerSecret, r2));
                }
                this.auth = r0;
            }
        }
        this.http = HttpClientFactory.getInstance(this.conf.getHttpClientConfiguration());
        setFactory();
    }

    void setFactory() {
        this.factory = new JSONImplFactory(this.conf);
    }

    public String getScreenName() {
        if (this.auth.isEnabled()) {
            if (this.screenName == null) {
                if (this.auth instanceof BasicAuthorization) {
                    this.screenName = ((BasicAuthorization) this.auth).getUserId();
                    if (this.screenName.contains("@")) {
                        this.screenName = null;
                    }
                }
                if (this.screenName == null) {
                    fillInIDAndScreenName();
                }
            }
            return this.screenName;
        }
        throw new IllegalStateException("Neither user ID/password combination nor OAuth consumer key/secret combination supplied");
    }

    public long getId() {
        if (this.auth.isEnabled()) {
            if (0 == this.id) {
                fillInIDAndScreenName();
            }
            return this.id;
        }
        throw new IllegalStateException("Neither user ID/password combination nor OAuth consumer key/secret combination supplied");
    }

    User fillInIDAndScreenName() {
        ensureAuthorizationEnabled();
        User userJSONImpl = new UserJSONImpl(this.http.get(this.conf.getRestBaseURL() + "account/verify_credentials.json", null, this.auth, this), this.conf);
        this.screenName = userJSONImpl.getScreenName();
        this.id = userJSONImpl.getId();
        return userJSONImpl;
    }

    public void addRateLimitStatusListener(RateLimitStatusListener rateLimitStatusListener) {
        this.rateLimitStatusListeners.add(rateLimitStatusListener);
    }

    public void httpResponseReceived(HttpResponseEvent httpResponseEvent) {
        if (this.rateLimitStatusListeners.size() != 0) {
            RateLimitStatus rateLimitStatus;
            int statusCode;
            HttpResponse response = httpResponseEvent.getResponse();
            TwitterException twitterException = httpResponseEvent.getTwitterException();
            if (twitterException != null) {
                rateLimitStatus = twitterException.getRateLimitStatus();
                statusCode = twitterException.getStatusCode();
            } else {
                rateLimitStatus = JSONImplFactory.createRateLimitStatusFromResponseHeader(response);
                statusCode = response.getStatusCode();
            }
            if (rateLimitStatus != null) {
                RateLimitStatusEvent rateLimitStatusEvent = new RateLimitStatusEvent(this, rateLimitStatus, httpResponseEvent.isAuthenticated());
                if (statusCode == HttpResponseCode.ENHANCE_YOUR_CLAIM || statusCode == HttpResponseCode.SERVICE_UNAVAILABLE || statusCode == HttpResponseCode.TOO_MANY_REQUESTS) {
                    for (RateLimitStatusListener rateLimitStatusListener : this.rateLimitStatusListeners) {
                        rateLimitStatusListener.onRateLimitStatus(rateLimitStatusEvent);
                        rateLimitStatusListener.onRateLimitReached(rateLimitStatusEvent);
                    }
                    return;
                }
                for (RateLimitStatusListener rateLimitStatusListener2 : this.rateLimitStatusListeners) {
                    rateLimitStatusListener2.onRateLimitStatus(rateLimitStatusEvent);
                }
            }
        }
    }

    public final Authorization getAuthorization() {
        return this.auth;
    }

    public Configuration getConfiguration() {
        return this.conf;
    }

    final void ensureAuthorizationEnabled() {
        if (!this.auth.isEnabled()) {
            throw new IllegalStateException("Authentication credentials are missing. See http://twitter4j.org/en/configuration.html for details");
        }
    }

    final void ensureOAuthEnabled() {
        if (!(this.auth instanceof OAuthAuthorization)) {
            throw new IllegalStateException("OAuth required. Authentication credentials are missing. See http://twitter4j.org/en/configuration.html for details");
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.putFields();
        objectOutputStream.writeFields();
        objectOutputStream.writeObject(this.conf);
        objectOutputStream.writeObject(this.auth);
        List arrayList = new ArrayList(0);
        for (RateLimitStatusListener rateLimitStatusListener : this.rateLimitStatusListeners) {
            if (rateLimitStatusListener instanceof Serializable) {
                arrayList.add(rateLimitStatusListener);
            }
        }
        objectOutputStream.writeObject(arrayList);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.readFields();
        this.conf = (Configuration) objectInputStream.readObject();
        this.auth = (Authorization) objectInputStream.readObject();
        this.rateLimitStatusListeners = (List) objectInputStream.readObject();
        this.http = HttpClientFactory.getInstance(this.conf.getHttpClientConfiguration());
        setFactory();
    }

    public synchronized void setOAuthConsumer(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("consumer key is null");
        } else if (str2 == null) {
            throw new NullPointerException("consumer secret is null");
        } else if (this.auth instanceof NullAuthorization) {
            Authorization oAuth2Authorization;
            if (this.conf.isApplicationOnlyAuthEnabled()) {
                oAuth2Authorization = new OAuth2Authorization(this.conf);
                oAuth2Authorization.setOAuthConsumer(str, str2);
                this.auth = oAuth2Authorization;
            } else {
                oAuth2Authorization = new OAuthAuthorization(this.conf);
                oAuth2Authorization.setOAuthConsumer(str, str2);
                this.auth = oAuth2Authorization;
            }
        } else if (this.auth instanceof BasicAuthorization) {
            Authorization xAuthAuthorization = new XAuthAuthorization((BasicAuthorization) this.auth);
            xAuthAuthorization.setOAuthConsumer(str, str2);
            this.auth = xAuthAuthorization;
        } else if ((this.auth instanceof OAuthAuthorization) || (this.auth instanceof OAuth2Authorization)) {
            throw new IllegalStateException("consumer key/secret pair already set.");
        }
    }

    public RequestToken getOAuthRequestToken() {
        return getOAuthRequestToken(null);
    }

    public RequestToken getOAuthRequestToken(String str) {
        return getOAuth().getOAuthRequestToken(str);
    }

    public RequestToken getOAuthRequestToken(String str, String str2) {
        return getOAuth().getOAuthRequestToken(str, str2);
    }

    public synchronized AccessToken getOAuthAccessToken() {
        AccessToken oAuthAccessToken;
        Authorization authorization = getAuthorization();
        if (authorization instanceof BasicAuthorization) {
            BasicAuthorization basicAuthorization = (BasicAuthorization) authorization;
            Authorization instance = AuthorizationFactory.getInstance(this.conf);
            if (instance instanceof OAuthAuthorization) {
                this.auth = instance;
                oAuthAccessToken = ((OAuthAuthorization) instance).getOAuthAccessToken(basicAuthorization.getUserId(), basicAuthorization.getPassword());
            } else {
                throw new IllegalStateException("consumer key / secret combination not supplied.");
            }
        } else if (authorization instanceof XAuthAuthorization) {
            XAuthAuthorization xAuthAuthorization = (XAuthAuthorization) authorization;
            this.auth = xAuthAuthorization;
            OAuthAuthorization oAuthAuthorization = new OAuthAuthorization(this.conf);
            oAuthAuthorization.setOAuthConsumer(xAuthAuthorization.getConsumerKey(), xAuthAuthorization.getConsumerSecret());
            oAuthAccessToken = oAuthAuthorization.getOAuthAccessToken(xAuthAuthorization.getUserId(), xAuthAuthorization.getPassword());
        } else {
            oAuthAccessToken = getOAuth().getOAuthAccessToken();
        }
        this.screenName = oAuthAccessToken.getScreenName();
        this.id = oAuthAccessToken.getUserId();
        return oAuthAccessToken;
    }

    public synchronized AccessToken getOAuthAccessToken(String str) {
        AccessToken oAuthAccessToken;
        oAuthAccessToken = getOAuth().getOAuthAccessToken(str);
        this.screenName = oAuthAccessToken.getScreenName();
        return oAuthAccessToken;
    }

    public synchronized AccessToken getOAuthAccessToken(RequestToken requestToken) {
        AccessToken oAuthAccessToken;
        oAuthAccessToken = getOAuth().getOAuthAccessToken(requestToken);
        this.screenName = oAuthAccessToken.getScreenName();
        return oAuthAccessToken;
    }

    public synchronized AccessToken getOAuthAccessToken(RequestToken requestToken, String str) {
        return getOAuth().getOAuthAccessToken(requestToken, str);
    }

    public synchronized void setOAuthAccessToken(AccessToken accessToken) {
        getOAuth().setOAuthAccessToken(accessToken);
    }

    public synchronized AccessToken getOAuthAccessToken(String str, String str2) {
        return getOAuth().getOAuthAccessToken(str, str2);
    }

    private OAuthSupport getOAuth() {
        if (this.auth instanceof OAuthSupport) {
            return (OAuthSupport) this.auth;
        }
        throw new IllegalStateException("OAuth consumer key/secret combination not supplied");
    }

    public synchronized OAuth2Token getOAuth2Token() {
        return getOAuth2().getOAuth2Token();
    }

    public void setOAuth2Token(OAuth2Token oAuth2Token) {
        getOAuth2().setOAuth2Token(oAuth2Token);
    }

    public synchronized void invalidateOAuth2Token() {
        getOAuth2().invalidateOAuth2Token();
    }

    private OAuth2Support getOAuth2() {
        if (this.auth instanceof OAuth2Support) {
            return (OAuth2Support) this.auth;
        }
        throw new IllegalStateException("OAuth consumer key/secret combination not supplied");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TwitterBaseImpl)) {
            return false;
        }
        TwitterBaseImpl twitterBaseImpl = (TwitterBaseImpl) obj;
        if (this.auth == null ? twitterBaseImpl.auth != null : !this.auth.equals(twitterBaseImpl.auth)) {
            return false;
        }
        if (!this.conf.equals(twitterBaseImpl.conf)) {
            return false;
        }
        if (this.http == null ? twitterBaseImpl.http != null : !this.http.equals(twitterBaseImpl.http)) {
            return false;
        }
        if (this.rateLimitStatusListeners.equals(twitterBaseImpl.rateLimitStatusListeners)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = this.conf.hashCode() * 31;
        if (this.http != null) {
            hashCode = this.http.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (((hashCode + hashCode2) * 31) + this.rateLimitStatusListeners.hashCode()) * 31;
        if (this.auth != null) {
            i = this.auth.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "TwitterBase{conf=" + this.conf + ", http=" + this.http + ", rateLimitStatusListeners=" + this.rateLimitStatusListeners + ", auth=" + this.auth + '}';
    }
}
