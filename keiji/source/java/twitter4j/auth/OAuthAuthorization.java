package twitter4j.auth;

import com.d.a.a.c;
import com.tapjoy.TapjoyConstants;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.BASE64Encoder;
import twitter4j.HttpClient;
import twitter4j.HttpClientFactory;
import twitter4j.HttpParameter;
import twitter4j.HttpRequest;
import twitter4j.Logger;
import twitter4j.TwitterException;
import twitter4j.conf.Configuration;

public class OAuthAuthorization implements Serializable, Authorization, OAuthSupport {
    private static final String HMAC_SHA1 = "HmacSHA1";
    private static final HttpParameter OAUTH_SIGNATURE_METHOD = new HttpParameter(TapjoyConstants.TJC_NOTIFICATION_OAUTH_SIGNATURE_METHOD, "HMAC-SHA1");
    private static final Random RAND = new Random();
    private static transient HttpClient http = null;
    private static final Logger logger = Logger.getLogger(OAuthAuthorization.class);
    private static final long serialVersionUID = -886869424811858868L;
    private final Configuration conf;
    private String consumerKey = BuildConfig.FLAVOR;
    private String consumerSecret;
    private OAuthToken oauthToken = null;
    private String realm = null;

    public OAuthAuthorization(Configuration configuration) {
        this.conf = configuration;
        http = HttpClientFactory.getInstance(configuration.getHttpClientConfiguration());
        setOAuthConsumer(configuration.getOAuthConsumerKey(), configuration.getOAuthConsumerSecret());
        if (configuration.getOAuthAccessToken() != null && configuration.getOAuthAccessTokenSecret() != null) {
            setOAuthAccessToken(new AccessToken(configuration.getOAuthAccessToken(), configuration.getOAuthAccessTokenSecret()));
        }
    }

    public String getAuthorizationHeader(HttpRequest httpRequest) {
        return generateAuthorizationHeader(httpRequest.getMethod().name(), httpRequest.getURL(), httpRequest.getParameters(), this.oauthToken);
    }

    private void ensureTokenIsAvailable() {
        if (this.oauthToken == null) {
            throw new IllegalStateException("No Token available.");
        }
    }

    public boolean isEnabled() {
        return this.oauthToken != null && (this.oauthToken instanceof AccessToken);
    }

    public RequestToken getOAuthRequestToken() {
        return getOAuthRequestToken(null, null);
    }

    public RequestToken getOAuthRequestToken(String str) {
        return getOAuthRequestToken(str, null);
    }

    public RequestToken getOAuthRequestToken(String str, String str2) {
        if (this.oauthToken instanceof AccessToken) {
            throw new IllegalStateException("Access token already available.");
        }
        List arrayList = new ArrayList();
        if (str != null) {
            arrayList.add(new HttpParameter("oauth_callback", str));
        }
        if (str2 != null) {
            arrayList.add(new HttpParameter("x_auth_access_type", str2));
        }
        this.oauthToken = new RequestToken(http.post(this.conf.getOAuthRequestTokenURL(), (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()]), this, null), (OAuthSupport) this);
        return (RequestToken) this.oauthToken;
    }

    public AccessToken getOAuthAccessToken() {
        ensureTokenIsAvailable();
        if (this.oauthToken instanceof AccessToken) {
            return (AccessToken) this.oauthToken;
        }
        this.oauthToken = new AccessToken(http.post(this.conf.getOAuthAccessTokenURL(), null, this, null));
        return (AccessToken) this.oauthToken;
    }

    public AccessToken getOAuthAccessToken(String str) {
        ensureTokenIsAvailable();
        this.oauthToken = new AccessToken(http.post(this.conf.getOAuthAccessTokenURL(), new HttpParameter[]{new HttpParameter("oauth_verifier", str)}, this, null));
        return (AccessToken) this.oauthToken;
    }

    public AccessToken getOAuthAccessToken(RequestToken requestToken) {
        this.oauthToken = requestToken;
        return getOAuthAccessToken();
    }

    public AccessToken getOAuthAccessToken(RequestToken requestToken, String str) {
        this.oauthToken = requestToken;
        return getOAuthAccessToken(str);
    }

    public AccessToken getOAuthAccessToken(String str, String str2) {
        try {
            String oAuthAccessTokenURL = this.conf.getOAuthAccessTokenURL();
            if (oAuthAccessTokenURL.indexOf("http://") == 0) {
                oAuthAccessTokenURL = "https://" + oAuthAccessTokenURL.substring(7);
            }
            this.oauthToken = new AccessToken(http.post(oAuthAccessTokenURL, new HttpParameter[]{new HttpParameter("x_auth_username", str), new HttpParameter("x_auth_password", str2), new HttpParameter("x_auth_mode", "client_auth")}, this, null));
            return (AccessToken) this.oauthToken;
        } catch (Exception e) {
            throw new TwitterException("The screen name / password combination seems to be invalid.", e, e.getStatusCode());
        }
    }

    public void setOAuthAccessToken(AccessToken accessToken) {
        this.oauthToken = accessToken;
    }

    public void setOAuthRealm(String str) {
        this.realm = str;
    }

    String generateAuthorizationHeader(String str, String str2, HttpParameter[] httpParameterArr, String str3, String str4, OAuthToken oAuthToken) {
        if (httpParameterArr == null) {
            httpParameterArr = new HttpParameter[0];
        }
        List arrayList = new ArrayList(5);
        arrayList.add(new HttpParameter("oauth_consumer_key", this.consumerKey));
        arrayList.add(OAUTH_SIGNATURE_METHOD);
        arrayList.add(new HttpParameter("oauth_timestamp", str4));
        arrayList.add(new HttpParameter("oauth_nonce", str3));
        arrayList.add(new HttpParameter("oauth_version", BuildConfig.VERSION_NAME));
        if (oAuthToken != null) {
            arrayList.add(new HttpParameter("oauth_token", oAuthToken.getToken()));
        }
        List arrayList2 = new ArrayList(arrayList.size() + httpParameterArr.length);
        arrayList2.addAll(arrayList);
        if (!HttpParameter.containsFile(httpParameterArr)) {
            arrayList2.addAll(toParamList(httpParameterArr));
        }
        parseGetParameters(str2, arrayList2);
        StringBuilder append = new StringBuilder(str).append("&").append(HttpParameter.encode(constructRequestURL(str2))).append("&");
        append.append(HttpParameter.encode(normalizeRequestParameters(arrayList2)));
        String stringBuilder = append.toString();
        logger.debug("OAuth base string: ", stringBuilder);
        stringBuilder = generateSignature(stringBuilder, oAuthToken);
        logger.debug("OAuth signature: ", stringBuilder);
        arrayList.add(new HttpParameter(TapjoyConstants.TJC_NOTIFICATION_OAUTH_SIGNATURE, stringBuilder));
        if (this.realm != null) {
            arrayList.add(new HttpParameter("realm", this.realm));
        }
        return "OAuth " + encodeParameters(arrayList, ",", true);
    }

    private void parseGetParameters(String str, List<HttpParameter> list) {
        int indexOf = str.indexOf("?");
        if (-1 != indexOf) {
            str.split("&");
            try {
                for (String split : str.substring(indexOf + 1).split("&")) {
                    String[] split2 = split.split("=");
                    if (split2.length == 2) {
                        list.add(new HttpParameter(URLDecoder.decode(split2[0], c.DEFAULT_CHARSET), URLDecoder.decode(split2[1], c.DEFAULT_CHARSET)));
                    } else {
                        list.add(new HttpParameter(URLDecoder.decode(split2[0], c.DEFAULT_CHARSET), BuildConfig.FLAVOR));
                    }
                }
            } catch (UnsupportedEncodingException e) {
            }
        }
    }

    String generateAuthorizationHeader(String str, String str2, HttpParameter[] httpParameterArr, OAuthToken oAuthToken) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        return generateAuthorizationHeader(str, str2, httpParameterArr, String.valueOf(((long) RAND.nextInt()) + currentTimeMillis), String.valueOf(currentTimeMillis), oAuthToken);
    }

    public List<HttpParameter> generateOAuthSignatureHttpParams(String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long nextInt = ((long) RAND.nextInt()) + currentTimeMillis;
        List<HttpParameter> arrayList = new ArrayList(5);
        arrayList.add(new HttpParameter("oauth_consumer_key", this.consumerKey));
        arrayList.add(OAUTH_SIGNATURE_METHOD);
        arrayList.add(new HttpParameter("oauth_timestamp", currentTimeMillis));
        arrayList.add(new HttpParameter("oauth_nonce", nextInt));
        arrayList.add(new HttpParameter("oauth_version", BuildConfig.VERSION_NAME));
        if (this.oauthToken != null) {
            arrayList.add(new HttpParameter("oauth_token", this.oauthToken.getToken()));
        }
        List arrayList2 = new ArrayList(arrayList.size());
        arrayList2.addAll(arrayList);
        parseGetParameters(str2, arrayList2);
        StringBuilder append = new StringBuilder(str).append("&").append(HttpParameter.encode(constructRequestURL(str2))).append("&");
        append.append(HttpParameter.encode(normalizeRequestParameters(arrayList2)));
        arrayList.add(new HttpParameter(TapjoyConstants.TJC_NOTIFICATION_OAUTH_SIGNATURE, generateSignature(append.toString(), this.oauthToken)));
        return arrayList;
    }

    String generateSignature(String str, OAuthToken oAuthToken) {
        try {
            Key secretKeySpec;
            Mac instance = Mac.getInstance(HMAC_SHA1);
            if (oAuthToken == null) {
                secretKeySpec = new SecretKeySpec((HttpParameter.encode(this.consumerSecret) + "&").getBytes(), HMAC_SHA1);
            } else {
                secretKeySpec = oAuthToken.getSecretKeySpec();
                if (secretKeySpec == null) {
                    secretKeySpec = new SecretKeySpec((HttpParameter.encode(this.consumerSecret) + "&" + HttpParameter.encode(oAuthToken.getTokenSecret())).getBytes(), HMAC_SHA1);
                    oAuthToken.setSecretKeySpec(secretKeySpec);
                }
            }
            instance.init(secretKeySpec);
            return BASE64Encoder.encode(instance.doFinal(str.getBytes()));
        } catch (Throwable e) {
            logger.error("Failed initialize \"Message Authentication Code\" (MAC)", e);
            throw new AssertionError(e);
        } catch (Throwable e2) {
            logger.error("Failed to get HmacSHA1 \"Message Authentication Code\" (MAC)", e2);
            throw new AssertionError(e2);
        }
    }

    String generateSignature(String str) {
        return generateSignature(str, null);
    }

    static String normalizeRequestParameters(HttpParameter[] httpParameterArr) {
        return normalizeRequestParameters(toParamList(httpParameterArr));
    }

    private static String normalizeRequestParameters(List<HttpParameter> list) {
        Collections.sort(list);
        return encodeParameters(list);
    }

    private static List<HttpParameter> toParamList(HttpParameter[] httpParameterArr) {
        List<HttpParameter> arrayList = new ArrayList(httpParameterArr.length);
        arrayList.addAll(Arrays.asList(httpParameterArr));
        return arrayList;
    }

    public static String encodeParameters(List<HttpParameter> list) {
        return encodeParameters(list, "&", false);
    }

    public static String encodeParameters(List<HttpParameter> list, String str, boolean z) {
        StringBuilder stringBuilder = new StringBuilder();
        for (HttpParameter httpParameter : list) {
            if (!httpParameter.isFile()) {
                if (stringBuilder.length() != 0) {
                    if (z) {
                        stringBuilder.append("\"");
                    }
                    stringBuilder.append(str);
                }
                stringBuilder.append(HttpParameter.encode(httpParameter.getName())).append("=");
                if (z) {
                    stringBuilder.append("\"");
                }
                stringBuilder.append(HttpParameter.encode(httpParameter.getValue()));
            }
        }
        if (stringBuilder.length() != 0 && z) {
            stringBuilder.append("\"");
        }
        return stringBuilder.toString();
    }

    static String constructRequestURL(String str) {
        int indexOf = str.indexOf("?");
        if (-1 != indexOf) {
            str = str.substring(0, indexOf);
        }
        int indexOf2 = str.indexOf("/", 8);
        String toLowerCase = str.substring(0, indexOf2).toLowerCase();
        int indexOf3 = toLowerCase.indexOf(":", 8);
        if (-1 != indexOf3) {
            if (toLowerCase.startsWith("http://") && toLowerCase.endsWith(":80")) {
                toLowerCase = toLowerCase.substring(0, indexOf3);
            } else if (toLowerCase.startsWith("https://") && toLowerCase.endsWith(":443")) {
                toLowerCase = toLowerCase.substring(0, indexOf3);
            }
        }
        return toLowerCase + str.substring(indexOf2);
    }

    public void setOAuthConsumer(String str, String str2) {
        if (str == null) {
            str = BuildConfig.FLAVOR;
        }
        this.consumerKey = str;
        if (str2 == null) {
            str2 = BuildConfig.FLAVOR;
        }
        this.consumerSecret = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OAuthSupport)) {
            return false;
        }
        OAuthAuthorization oAuthAuthorization = (OAuthAuthorization) obj;
        if (this.consumerKey == null ? oAuthAuthorization.consumerKey != null : !this.consumerKey.equals(oAuthAuthorization.consumerKey)) {
            return false;
        }
        if (this.consumerSecret == null ? oAuthAuthorization.consumerSecret != null : !this.consumerSecret.equals(oAuthAuthorization.consumerSecret)) {
            return false;
        }
        if (this.oauthToken != null) {
            if (this.oauthToken.equals(oAuthAuthorization.oauthToken)) {
                return true;
            }
        } else if (oAuthAuthorization.oauthToken == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.consumerKey != null) {
            hashCode = this.consumerKey.hashCode();
        } else {
            hashCode = 0;
        }
        int i2 = hashCode * 31;
        if (this.consumerSecret != null) {
            hashCode = this.consumerSecret.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i2) * 31;
        if (this.oauthToken != null) {
            i = this.oauthToken.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "OAuthAuthorization{consumerKey='" + this.consumerKey + '\'' + ", consumerSecret='******************************************'" + ", oauthToken=" + this.oauthToken + '}';
    }
}
