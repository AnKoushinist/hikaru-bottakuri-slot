package twitter4j.conf;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import twitter4j.HttpClientConfiguration;
import twitter4j.Logger;

class ConfigurationBase implements Serializable, Configuration {
    private static final List<ConfigurationBase> instances = new ArrayList();
    private static final long serialVersionUID = 6175546394599249696L;
    private boolean applicationOnlyAuthEnabled = false;
    private int asyncNumThreads = 1;
    private long contributingTo = -1;
    private boolean daemonEnabled = true;
    private boolean debug = false;
    private String dispatcherImpl = "twitter4j.DispatcherImpl";
    private HttpClientConfiguration httpConf = new MyHttpClientConfiguration(null, null, null, -1, 20000, 120000, false, true);
    private int httpRetryCount = 0;
    private int httpRetryIntervalSeconds = 5;
    private int httpStreamingReadTimeout = 40000;
    private boolean includeEntitiesEnabled = true;
    private boolean includeMyRetweetEnabled = true;
    private boolean jsonStoreEnabled = false;
    private String loggerFactory = null;
    private boolean mbeanEnabled = false;
    private String mediaProvider = "TWITTER";
    private String mediaProviderAPIKey = null;
    private Properties mediaProviderParameters = null;
    private String oAuth2AccessToken;
    private String oAuth2InvalidateTokenURL = "https://api.twitter.com/oauth2/invalidate_token";
    private String oAuth2Scope;
    private String oAuth2TokenType;
    private String oAuth2TokenURL = "https://api.twitter.com/oauth2/token";
    private String oAuthAccessToken = null;
    private String oAuthAccessTokenSecret = null;
    private String oAuthAccessTokenURL = "https://api.twitter.com/oauth/access_token";
    private String oAuthAuthenticationURL = "https://api.twitter.com/oauth/authenticate";
    private String oAuthAuthorizationURL = "https://api.twitter.com/oauth/authorize";
    private String oAuthConsumerKey = null;
    private String oAuthConsumerSecret = null;
    private String oAuthRequestTokenURL = "https://api.twitter.com/oauth/request_token";
    private String password = null;
    private String restBaseURL = "https://api.twitter.com/1.1/";
    private String siteStreamBaseURL = "https://sitestream.twitter.com/1.1/";
    private boolean stallWarningsEnabled = true;
    private String streamBaseURL = "https://stream.twitter.com/1.1/";
    private boolean trimUserEnabled = false;
    private String uploadBaseURL = "https://upload.twitter.com/1.1/";
    private String user = null;
    private String userStreamBaseURL = "https://userstream.twitter.com/1.1/";
    private boolean userStreamRepliesAllEnabled = false;
    private boolean userStreamWithFollowingsEnabled = true;

    class MyHttpClientConfiguration implements Serializable, HttpClientConfiguration {
        private static final long serialVersionUID = 8226866124868861058L;
        private boolean gzipEnabled = true;
        private int httpConnectionTimeout = 20000;
        private String httpProxyHost = null;
        private String httpProxyPassword = null;
        private int httpProxyPort = -1;
        private String httpProxyUser = null;
        private int httpReadTimeout = 120000;
        private boolean prettyDebug = false;

        MyHttpClientConfiguration(String str, String str2, String str3, int i, int i2, int i3, boolean z, boolean z2) {
            this.httpProxyHost = str;
            this.httpProxyUser = str2;
            this.httpProxyPassword = str3;
            this.httpProxyPort = i;
            this.httpConnectionTimeout = i2;
            this.httpReadTimeout = i3;
            this.prettyDebug = z;
            this.gzipEnabled = z2;
        }

        public String getHttpProxyHost() {
            return this.httpProxyHost;
        }

        public int getHttpProxyPort() {
            return this.httpProxyPort;
        }

        public String getHttpProxyUser() {
            return this.httpProxyUser;
        }

        public String getHttpProxyPassword() {
            return this.httpProxyPassword;
        }

        public int getHttpConnectionTimeout() {
            return this.httpConnectionTimeout;
        }

        public int getHttpReadTimeout() {
            return this.httpReadTimeout;
        }

        public int getHttpRetryCount() {
            return ConfigurationBase.this.httpRetryCount;
        }

        public int getHttpRetryIntervalSeconds() {
            return ConfigurationBase.this.httpRetryIntervalSeconds;
        }

        public boolean isPrettyDebugEnabled() {
            return this.prettyDebug;
        }

        public boolean isGZIPEnabled() {
            return this.gzipEnabled;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            MyHttpClientConfiguration myHttpClientConfiguration = (MyHttpClientConfiguration) obj;
            if (this.gzipEnabled != myHttpClientConfiguration.gzipEnabled) {
                return false;
            }
            if (this.httpConnectionTimeout != myHttpClientConfiguration.httpConnectionTimeout) {
                return false;
            }
            if (this.httpProxyPort != myHttpClientConfiguration.httpProxyPort) {
                return false;
            }
            if (this.httpReadTimeout != myHttpClientConfiguration.httpReadTimeout) {
                return false;
            }
            if (this.prettyDebug != myHttpClientConfiguration.prettyDebug) {
                return false;
            }
            if (this.httpProxyHost == null ? myHttpClientConfiguration.httpProxyHost != null : !this.httpProxyHost.equals(myHttpClientConfiguration.httpProxyHost)) {
                return false;
            }
            if (this.httpProxyPassword == null ? myHttpClientConfiguration.httpProxyPassword != null : !this.httpProxyPassword.equals(myHttpClientConfiguration.httpProxyPassword)) {
                return false;
            }
            if (this.httpProxyUser != null) {
                if (this.httpProxyUser.equals(myHttpClientConfiguration.httpProxyUser)) {
                    return true;
                }
            } else if (myHttpClientConfiguration.httpProxyUser == null) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int hashCode;
            int i = 1;
            int hashCode2 = (this.httpProxyHost != null ? this.httpProxyHost.hashCode() : 0) * 31;
            if (this.httpProxyUser != null) {
                hashCode = this.httpProxyUser.hashCode();
            } else {
                hashCode = 0;
            }
            hashCode2 = (hashCode + hashCode2) * 31;
            if (this.httpProxyPassword != null) {
                hashCode = this.httpProxyPassword.hashCode();
            } else {
                hashCode = 0;
            }
            hashCode2 = (((((((hashCode + hashCode2) * 31) + this.httpProxyPort) * 31) + this.httpConnectionTimeout) * 31) + this.httpReadTimeout) * 31;
            if (this.prettyDebug) {
                hashCode = 1;
            } else {
                hashCode = 0;
            }
            hashCode = (hashCode + hashCode2) * 31;
            if (!this.gzipEnabled) {
                i = 0;
            }
            return hashCode + i;
        }

        public String toString() {
            return "MyHttpClientConfiguration{httpProxyHost='" + this.httpProxyHost + '\'' + ", httpProxyUser='" + this.httpProxyUser + '\'' + ", httpProxyPassword='" + this.httpProxyPassword + '\'' + ", httpProxyPort=" + this.httpProxyPort + ", httpConnectionTimeout=" + this.httpConnectionTimeout + ", httpReadTimeout=" + this.httpReadTimeout + ", prettyDebug=" + this.prettyDebug + ", gzipEnabled=" + this.gzipEnabled + '}';
        }
    }

    protected ConfigurationBase() {
    }

    public void dumpConfiguration() {
        Logger logger = Logger.getLogger(ConfigurationBase.class);
        if (this.debug) {
            for (Field field : ConfigurationBase.class.getDeclaredFields()) {
                try {
                    Object obj = field.get(this);
                    String valueOf = String.valueOf(obj);
                    if (obj != null && field.getName().matches("oAuthConsumerSecret|oAuthAccessTokenSecret|password")) {
                        valueOf = String.valueOf(obj).replaceAll(".", "*");
                    }
                    logger.debug(field.getName() + ": " + valueOf);
                } catch (IllegalAccessException e) {
                }
            }
        }
    }

    public final boolean isDebugEnabled() {
        return this.debug;
    }

    protected final void setDebug(boolean z) {
        this.debug = z;
    }

    public final String getUser() {
        return this.user;
    }

    protected final void setUser(String str) {
        this.user = str;
    }

    public final String getPassword() {
        return this.password;
    }

    public HttpClientConfiguration getHttpClientConfiguration() {
        return this.httpConf;
    }

    protected final void setPassword(String str) {
        this.password = str;
    }

    protected final void setPrettyDebugEnabled(boolean z) {
        this.httpConf = new MyHttpClientConfiguration(this.httpConf.getHttpProxyHost(), this.httpConf.getHttpProxyUser(), this.httpConf.getHttpProxyPassword(), this.httpConf.getHttpProxyPort(), this.httpConf.getHttpConnectionTimeout(), this.httpConf.getHttpReadTimeout(), z, this.httpConf.isGZIPEnabled());
    }

    protected final void setGZIPEnabled(boolean z) {
        this.httpConf = new MyHttpClientConfiguration(this.httpConf.getHttpProxyHost(), this.httpConf.getHttpProxyUser(), this.httpConf.getHttpProxyPassword(), this.httpConf.getHttpProxyPort(), this.httpConf.getHttpConnectionTimeout(), this.httpConf.getHttpReadTimeout(), this.httpConf.isPrettyDebugEnabled(), z);
    }

    protected final void setHttpProxyHost(String str) {
        this.httpConf = new MyHttpClientConfiguration(str, this.httpConf.getHttpProxyUser(), this.httpConf.getHttpProxyPassword(), this.httpConf.getHttpProxyPort(), this.httpConf.getHttpConnectionTimeout(), this.httpConf.getHttpReadTimeout(), this.httpConf.isPrettyDebugEnabled(), this.httpConf.isGZIPEnabled());
    }

    protected final void setHttpProxyUser(String str) {
        this.httpConf = new MyHttpClientConfiguration(this.httpConf.getHttpProxyHost(), str, this.httpConf.getHttpProxyPassword(), this.httpConf.getHttpProxyPort(), this.httpConf.getHttpConnectionTimeout(), this.httpConf.getHttpReadTimeout(), this.httpConf.isPrettyDebugEnabled(), this.httpConf.isGZIPEnabled());
    }

    protected final void setHttpProxyPassword(String str) {
        this.httpConf = new MyHttpClientConfiguration(this.httpConf.getHttpProxyHost(), this.httpConf.getHttpProxyUser(), str, this.httpConf.getHttpProxyPort(), this.httpConf.getHttpConnectionTimeout(), this.httpConf.getHttpReadTimeout(), this.httpConf.isPrettyDebugEnabled(), this.httpConf.isGZIPEnabled());
    }

    protected final void setHttpProxyPort(int i) {
        this.httpConf = new MyHttpClientConfiguration(this.httpConf.getHttpProxyHost(), this.httpConf.getHttpProxyUser(), this.httpConf.getHttpProxyPassword(), i, this.httpConf.getHttpConnectionTimeout(), this.httpConf.getHttpReadTimeout(), this.httpConf.isPrettyDebugEnabled(), this.httpConf.isGZIPEnabled());
    }

    protected final void setHttpConnectionTimeout(int i) {
        this.httpConf = new MyHttpClientConfiguration(this.httpConf.getHttpProxyHost(), this.httpConf.getHttpProxyUser(), this.httpConf.getHttpProxyPassword(), this.httpConf.getHttpProxyPort(), i, this.httpConf.getHttpReadTimeout(), this.httpConf.isPrettyDebugEnabled(), this.httpConf.isGZIPEnabled());
    }

    protected final void setHttpReadTimeout(int i) {
        this.httpConf = new MyHttpClientConfiguration(this.httpConf.getHttpProxyHost(), this.httpConf.getHttpProxyUser(), this.httpConf.getHttpProxyPassword(), this.httpConf.getHttpProxyPort(), this.httpConf.getHttpConnectionTimeout(), i, this.httpConf.isPrettyDebugEnabled(), this.httpConf.isGZIPEnabled());
    }

    public int getHttpStreamingReadTimeout() {
        return this.httpStreamingReadTimeout;
    }

    protected final void setHttpStreamingReadTimeout(int i) {
        this.httpStreamingReadTimeout = i;
    }

    protected final void setHttpRetryCount(int i) {
        this.httpRetryCount = i;
    }

    protected final void setHttpRetryIntervalSeconds(int i) {
        this.httpRetryIntervalSeconds = i;
    }

    public final String getOAuthConsumerKey() {
        return this.oAuthConsumerKey;
    }

    protected final void setOAuthConsumerKey(String str) {
        this.oAuthConsumerKey = str;
    }

    public final String getOAuthConsumerSecret() {
        return this.oAuthConsumerSecret;
    }

    protected final void setOAuthConsumerSecret(String str) {
        this.oAuthConsumerSecret = str;
    }

    public String getOAuthAccessToken() {
        return this.oAuthAccessToken;
    }

    protected final void setOAuthAccessToken(String str) {
        this.oAuthAccessToken = str;
    }

    public String getOAuthAccessTokenSecret() {
        return this.oAuthAccessTokenSecret;
    }

    protected final void setOAuthAccessTokenSecret(String str) {
        this.oAuthAccessTokenSecret = str;
    }

    public String getOAuth2TokenType() {
        return this.oAuth2TokenType;
    }

    protected final void setOAuth2TokenType(String str) {
        this.oAuth2TokenType = str;
    }

    public String getOAuth2AccessToken() {
        return this.oAuth2AccessToken;
    }

    public String getOAuth2Scope() {
        return this.oAuth2Scope;
    }

    protected final void setOAuth2AccessToken(String str) {
        this.oAuth2AccessToken = str;
    }

    protected final void setOAuth2Scope(String str) {
        this.oAuth2Scope = str;
    }

    public final int getAsyncNumThreads() {
        return this.asyncNumThreads;
    }

    protected final void setAsyncNumThreads(int i) {
        this.asyncNumThreads = i;
    }

    public final long getContributingTo() {
        return this.contributingTo;
    }

    protected final void setContributingTo(long j) {
        this.contributingTo = j;
    }

    public String getRestBaseURL() {
        return this.restBaseURL;
    }

    protected final void setRestBaseURL(String str) {
        this.restBaseURL = str;
    }

    public String getUploadBaseURL() {
        return this.uploadBaseURL;
    }

    protected final void setUploadBaseURL(String str) {
        this.uploadBaseURL = str;
    }

    public String getStreamBaseURL() {
        return this.streamBaseURL;
    }

    protected final void setStreamBaseURL(String str) {
        this.streamBaseURL = str;
    }

    public String getUserStreamBaseURL() {
        return this.userStreamBaseURL;
    }

    protected final void setUserStreamBaseURL(String str) {
        this.userStreamBaseURL = str;
    }

    public String getSiteStreamBaseURL() {
        return this.siteStreamBaseURL;
    }

    protected final void setSiteStreamBaseURL(String str) {
        this.siteStreamBaseURL = str;
    }

    public String getOAuthRequestTokenURL() {
        return this.oAuthRequestTokenURL;
    }

    protected final void setOAuthRequestTokenURL(String str) {
        this.oAuthRequestTokenURL = str;
    }

    public String getOAuthAuthorizationURL() {
        return this.oAuthAuthorizationURL;
    }

    protected final void setOAuthAuthorizationURL(String str) {
        this.oAuthAuthorizationURL = str;
    }

    public String getOAuthAccessTokenURL() {
        return this.oAuthAccessTokenURL;
    }

    protected final void setOAuthAccessTokenURL(String str) {
        this.oAuthAccessTokenURL = str;
    }

    public String getOAuthAuthenticationURL() {
        return this.oAuthAuthenticationURL;
    }

    protected final void setOAuthAuthenticationURL(String str) {
        this.oAuthAuthenticationURL = str;
    }

    public String getOAuth2TokenURL() {
        return this.oAuth2TokenURL;
    }

    protected final void setOAuth2TokenURL(String str) {
        this.oAuth2TokenURL = str;
    }

    public String getOAuth2InvalidateTokenURL() {
        return this.oAuth2InvalidateTokenURL;
    }

    protected final void setOAuth2InvalidateTokenURL(String str) {
        this.oAuth2InvalidateTokenURL = str;
    }

    public String getDispatcherImpl() {
        return this.dispatcherImpl;
    }

    protected final void setDispatcherImpl(String str) {
        this.dispatcherImpl = str;
    }

    public String getLoggerFactory() {
        return this.loggerFactory;
    }

    public boolean isIncludeEntitiesEnabled() {
        return this.includeEntitiesEnabled;
    }

    protected void setIncludeEntitiesEnabled(boolean z) {
        this.includeEntitiesEnabled = z;
    }

    protected final void setLoggerFactory(String str) {
        this.loggerFactory = str;
    }

    public boolean isIncludeMyRetweetEnabled() {
        return this.includeMyRetweetEnabled;
    }

    public void setIncludeMyRetweetEnabled(boolean z) {
        this.includeMyRetweetEnabled = z;
    }

    public boolean isTrimUserEnabled() {
        return this.trimUserEnabled;
    }

    public boolean isDaemonEnabled() {
        return this.daemonEnabled;
    }

    protected void setDaemonEnabled(boolean z) {
        this.daemonEnabled = z;
    }

    public void setTrimUserEnabled(boolean z) {
        this.trimUserEnabled = z;
    }

    public boolean isJSONStoreEnabled() {
        return this.jsonStoreEnabled;
    }

    protected final void setJSONStoreEnabled(boolean z) {
        this.jsonStoreEnabled = z;
    }

    public boolean isMBeanEnabled() {
        return this.mbeanEnabled;
    }

    protected final void setMBeanEnabled(boolean z) {
        this.mbeanEnabled = z;
    }

    public boolean isUserStreamRepliesAllEnabled() {
        return this.userStreamRepliesAllEnabled;
    }

    public boolean isUserStreamWithFollowingsEnabled() {
        return this.userStreamWithFollowingsEnabled;
    }

    protected final void setUserStreamRepliesAllEnabled(boolean z) {
        this.userStreamRepliesAllEnabled = z;
    }

    protected final void setUserStreamWithFollowingsEnabled(boolean z) {
        this.userStreamWithFollowingsEnabled = z;
    }

    public boolean isStallWarningsEnabled() {
        return this.stallWarningsEnabled;
    }

    protected final void setStallWarningsEnabled(boolean z) {
        this.stallWarningsEnabled = z;
    }

    public boolean isApplicationOnlyAuthEnabled() {
        return this.applicationOnlyAuthEnabled;
    }

    protected final void setApplicationOnlyAuthEnabled(boolean z) {
        this.applicationOnlyAuthEnabled = z;
    }

    public String getMediaProvider() {
        return this.mediaProvider;
    }

    protected final void setMediaProvider(String str) {
        this.mediaProvider = str;
    }

    public String getMediaProviderAPIKey() {
        return this.mediaProviderAPIKey;
    }

    protected final void setMediaProviderAPIKey(String str) {
        this.mediaProviderAPIKey = str;
    }

    public Properties getMediaProviderParameters() {
        return this.mediaProviderParameters;
    }

    protected final void setMediaProviderParameters(Properties properties) {
        this.mediaProviderParameters = properties;
    }

    static String fixURL(boolean z, String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf("://");
        if (-1 == indexOf) {
            throw new IllegalArgumentException("url should contain '://'");
        }
        String substring = str.substring(indexOf + 3);
        if (z) {
            return "https://" + substring;
        }
        return "http://" + substring;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ConfigurationBase configurationBase = (ConfigurationBase) obj;
        if (this.applicationOnlyAuthEnabled != configurationBase.applicationOnlyAuthEnabled) {
            return false;
        }
        if (this.asyncNumThreads != configurationBase.asyncNumThreads) {
            return false;
        }
        if (this.contributingTo != configurationBase.contributingTo) {
            return false;
        }
        if (this.daemonEnabled != configurationBase.daemonEnabled) {
            return false;
        }
        if (this.debug != configurationBase.debug) {
            return false;
        }
        if (this.httpRetryCount != configurationBase.httpRetryCount) {
            return false;
        }
        if (this.httpRetryIntervalSeconds != configurationBase.httpRetryIntervalSeconds) {
            return false;
        }
        if (this.httpStreamingReadTimeout != configurationBase.httpStreamingReadTimeout) {
            return false;
        }
        if (this.includeEntitiesEnabled != configurationBase.includeEntitiesEnabled) {
            return false;
        }
        if (this.includeMyRetweetEnabled != configurationBase.includeMyRetweetEnabled) {
            return false;
        }
        if (this.jsonStoreEnabled != configurationBase.jsonStoreEnabled) {
            return false;
        }
        if (this.mbeanEnabled != configurationBase.mbeanEnabled) {
            return false;
        }
        if (this.stallWarningsEnabled != configurationBase.stallWarningsEnabled) {
            return false;
        }
        if (this.trimUserEnabled != configurationBase.trimUserEnabled) {
            return false;
        }
        if (this.userStreamRepliesAllEnabled != configurationBase.userStreamRepliesAllEnabled) {
            return false;
        }
        if (this.userStreamWithFollowingsEnabled != configurationBase.userStreamWithFollowingsEnabled) {
            return false;
        }
        if (this.dispatcherImpl == null ? configurationBase.dispatcherImpl != null : !this.dispatcherImpl.equals(configurationBase.dispatcherImpl)) {
            return false;
        }
        if (this.httpConf == null ? configurationBase.httpConf != null : !this.httpConf.equals(configurationBase.httpConf)) {
            return false;
        }
        if (this.loggerFactory == null ? configurationBase.loggerFactory != null : !this.loggerFactory.equals(configurationBase.loggerFactory)) {
            return false;
        }
        if (this.mediaProvider == null ? configurationBase.mediaProvider != null : !this.mediaProvider.equals(configurationBase.mediaProvider)) {
            return false;
        }
        if (this.mediaProviderAPIKey == null ? configurationBase.mediaProviderAPIKey != null : !this.mediaProviderAPIKey.equals(configurationBase.mediaProviderAPIKey)) {
            return false;
        }
        if (this.mediaProviderParameters == null ? configurationBase.mediaProviderParameters != null : !this.mediaProviderParameters.equals(configurationBase.mediaProviderParameters)) {
            return false;
        }
        if (this.oAuth2AccessToken == null ? configurationBase.oAuth2AccessToken != null : !this.oAuth2AccessToken.equals(configurationBase.oAuth2AccessToken)) {
            return false;
        }
        if (this.oAuth2InvalidateTokenURL == null ? configurationBase.oAuth2InvalidateTokenURL != null : !this.oAuth2InvalidateTokenURL.equals(configurationBase.oAuth2InvalidateTokenURL)) {
            return false;
        }
        if (this.oAuth2TokenType == null ? configurationBase.oAuth2TokenType != null : !this.oAuth2TokenType.equals(configurationBase.oAuth2TokenType)) {
            return false;
        }
        if (this.oAuth2TokenURL == null ? configurationBase.oAuth2TokenURL != null : !this.oAuth2TokenURL.equals(configurationBase.oAuth2TokenURL)) {
            return false;
        }
        if (this.oAuth2Scope == null ? configurationBase.oAuth2Scope != null : !this.oAuth2Scope.equals(configurationBase.oAuth2Scope)) {
            return false;
        }
        if (this.oAuthAccessToken == null ? configurationBase.oAuthAccessToken != null : !this.oAuthAccessToken.equals(configurationBase.oAuthAccessToken)) {
            return false;
        }
        if (this.oAuthAccessTokenSecret == null ? configurationBase.oAuthAccessTokenSecret != null : !this.oAuthAccessTokenSecret.equals(configurationBase.oAuthAccessTokenSecret)) {
            return false;
        }
        if (this.oAuthAccessTokenURL == null ? configurationBase.oAuthAccessTokenURL != null : !this.oAuthAccessTokenURL.equals(configurationBase.oAuthAccessTokenURL)) {
            return false;
        }
        if (this.oAuthAuthenticationURL == null ? configurationBase.oAuthAuthenticationURL != null : !this.oAuthAuthenticationURL.equals(configurationBase.oAuthAuthenticationURL)) {
            return false;
        }
        if (this.oAuthAuthorizationURL == null ? configurationBase.oAuthAuthorizationURL != null : !this.oAuthAuthorizationURL.equals(configurationBase.oAuthAuthorizationURL)) {
            return false;
        }
        if (this.oAuthConsumerKey == null ? configurationBase.oAuthConsumerKey != null : !this.oAuthConsumerKey.equals(configurationBase.oAuthConsumerKey)) {
            return false;
        }
        if (this.oAuthConsumerSecret == null ? configurationBase.oAuthConsumerSecret != null : !this.oAuthConsumerSecret.equals(configurationBase.oAuthConsumerSecret)) {
            return false;
        }
        if (this.oAuthRequestTokenURL == null ? configurationBase.oAuthRequestTokenURL != null : !this.oAuthRequestTokenURL.equals(configurationBase.oAuthRequestTokenURL)) {
            return false;
        }
        if (this.password == null ? configurationBase.password != null : !this.password.equals(configurationBase.password)) {
            return false;
        }
        if (this.restBaseURL == null ? configurationBase.restBaseURL != null : !this.restBaseURL.equals(configurationBase.restBaseURL)) {
            return false;
        }
        if (this.uploadBaseURL == null ? configurationBase.uploadBaseURL != null : !this.uploadBaseURL.equals(configurationBase.uploadBaseURL)) {
            return false;
        }
        if (this.siteStreamBaseURL == null ? configurationBase.siteStreamBaseURL != null : !this.siteStreamBaseURL.equals(configurationBase.siteStreamBaseURL)) {
            return false;
        }
        if (this.streamBaseURL == null ? configurationBase.streamBaseURL != null : !this.streamBaseURL.equals(configurationBase.streamBaseURL)) {
            return false;
        }
        if (this.user == null ? configurationBase.user != null : !this.user.equals(configurationBase.user)) {
            return false;
        }
        if (this.userStreamBaseURL != null) {
            if (this.userStreamBaseURL.equals(configurationBase.userStreamBaseURL)) {
                return true;
            }
        } else if (configurationBase.userStreamBaseURL == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 1;
        int i2 = (this.debug ? 1 : 0) * 31;
        if (this.user != null) {
            hashCode = this.user.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.password != null) {
            hashCode = this.password.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.httpConf != null) {
            hashCode = this.httpConf.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (((((((hashCode + i2) * 31) + this.httpStreamingReadTimeout) * 31) + this.httpRetryCount) * 31) + this.httpRetryIntervalSeconds) * 31;
        if (this.oAuthConsumerKey != null) {
            hashCode = this.oAuthConsumerKey.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.oAuthConsumerSecret != null) {
            hashCode = this.oAuthConsumerSecret.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.oAuthAccessToken != null) {
            hashCode = this.oAuthAccessToken.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.oAuthAccessTokenSecret != null) {
            hashCode = this.oAuthAccessTokenSecret.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.oAuth2TokenType != null) {
            hashCode = this.oAuth2TokenType.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.oAuth2AccessToken != null) {
            hashCode = this.oAuth2AccessToken.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.oAuth2Scope != null) {
            hashCode = this.oAuth2Scope.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.oAuthRequestTokenURL != null) {
            hashCode = this.oAuthRequestTokenURL.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.oAuthAuthorizationURL != null) {
            hashCode = this.oAuthAuthorizationURL.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.oAuthAccessTokenURL != null) {
            hashCode = this.oAuthAccessTokenURL.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.oAuthAuthenticationURL != null) {
            hashCode = this.oAuthAuthenticationURL.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.oAuth2TokenURL != null) {
            hashCode = this.oAuth2TokenURL.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.oAuth2InvalidateTokenURL != null) {
            hashCode = this.oAuth2InvalidateTokenURL.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.restBaseURL != null) {
            hashCode = this.restBaseURL.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.uploadBaseURL != null) {
            hashCode = this.uploadBaseURL.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.streamBaseURL != null) {
            hashCode = this.streamBaseURL.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.userStreamBaseURL != null) {
            hashCode = this.userStreamBaseURL.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.siteStreamBaseURL != null) {
            hashCode = this.siteStreamBaseURL.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.dispatcherImpl != null) {
            hashCode = this.dispatcherImpl.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (((hashCode + i2) * 31) + this.asyncNumThreads) * 31;
        if (this.loggerFactory != null) {
            hashCode = this.loggerFactory.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (((hashCode + i2) * 31) + ((int) (this.contributingTo ^ (this.contributingTo >>> 32)))) * 31;
        if (this.includeMyRetweetEnabled) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.includeEntitiesEnabled) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.trimUserEnabled) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.jsonStoreEnabled) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.mbeanEnabled) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.userStreamRepliesAllEnabled) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.userStreamWithFollowingsEnabled) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.stallWarningsEnabled) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.applicationOnlyAuthEnabled) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.mediaProvider != null) {
            hashCode = this.mediaProvider.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.mediaProviderAPIKey != null) {
            hashCode = this.mediaProviderAPIKey.hashCode();
        } else {
            hashCode = 0;
        }
        i2 = (hashCode + i2) * 31;
        if (this.mediaProviderParameters != null) {
            hashCode = this.mediaProviderParameters.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (!this.daemonEnabled) {
            i = 0;
        }
        return hashCode + i;
    }

    public String toString() {
        return "ConfigurationBase{debug=" + this.debug + ", user='" + this.user + '\'' + ", password='" + this.password + '\'' + ", httpConf=" + this.httpConf + ", httpStreamingReadTimeout=" + this.httpStreamingReadTimeout + ", httpRetryCount=" + this.httpRetryCount + ", httpRetryIntervalSeconds=" + this.httpRetryIntervalSeconds + ", oAuthConsumerKey='" + this.oAuthConsumerKey + '\'' + ", oAuthConsumerSecret='" + this.oAuthConsumerSecret + '\'' + ", oAuthAccessToken='" + this.oAuthAccessToken + '\'' + ", oAuthAccessTokenSecret='" + this.oAuthAccessTokenSecret + '\'' + ", oAuth2TokenType='" + this.oAuth2TokenType + '\'' + ", oAuth2AccessToken='" + this.oAuth2AccessToken + '\'' + ", oAuth2Scope='" + this.oAuth2Scope + '\'' + ", oAuthRequestTokenURL='" + this.oAuthRequestTokenURL + '\'' + ", oAuthAuthorizationURL='" + this.oAuthAuthorizationURL + '\'' + ", oAuthAccessTokenURL='" + this.oAuthAccessTokenURL + '\'' + ", oAuthAuthenticationURL='" + this.oAuthAuthenticationURL + '\'' + ", oAuth2TokenURL='" + this.oAuth2TokenURL + '\'' + ", oAuth2InvalidateTokenURL='" + this.oAuth2InvalidateTokenURL + '\'' + ", restBaseURL='" + this.restBaseURL + '\'' + ", uploadBaseURL='" + this.uploadBaseURL + '\'' + ", streamBaseURL='" + this.streamBaseURL + '\'' + ", userStreamBaseURL='" + this.userStreamBaseURL + '\'' + ", siteStreamBaseURL='" + this.siteStreamBaseURL + '\'' + ", dispatcherImpl='" + this.dispatcherImpl + '\'' + ", asyncNumThreads=" + this.asyncNumThreads + ", loggerFactory='" + this.loggerFactory + '\'' + ", contributingTo=" + this.contributingTo + ", includeMyRetweetEnabled=" + this.includeMyRetweetEnabled + ", includeEntitiesEnabled=" + this.includeEntitiesEnabled + ", trimUserEnabled=" + this.trimUserEnabled + ", jsonStoreEnabled=" + this.jsonStoreEnabled + ", mbeanEnabled=" + this.mbeanEnabled + ", userStreamRepliesAllEnabled=" + this.userStreamRepliesAllEnabled + ", userStreamWithFollowingsEnabled=" + this.userStreamWithFollowingsEnabled + ", stallWarningsEnabled=" + this.stallWarningsEnabled + ", applicationOnlyAuthEnabled=" + this.applicationOnlyAuthEnabled + ", mediaProvider='" + this.mediaProvider + '\'' + ", mediaProviderAPIKey='" + this.mediaProviderAPIKey + '\'' + ", mediaProviderParameters=" + this.mediaProviderParameters + ", daemonEnabled=" + this.daemonEnabled + '}';
    }

    private static void cacheInstance(ConfigurationBase configurationBase) {
        if (!instances.contains(configurationBase)) {
            instances.add(configurationBase);
        }
    }

    protected void cacheInstance() {
        cacheInstance(this);
    }

    private static ConfigurationBase getInstance(ConfigurationBase configurationBase) {
        int indexOf = instances.indexOf(configurationBase);
        if (indexOf != -1) {
            return (ConfigurationBase) instances.get(indexOf);
        }
        instances.add(configurationBase);
        return configurationBase;
    }

    protected Object readResolve() {
        return getInstance(this);
    }
}
