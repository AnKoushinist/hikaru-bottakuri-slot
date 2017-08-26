package twitter4j.auth;

import java.io.Serializable;
import twitter4j.HttpResponse;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;

public final class RequestToken extends OAuthToken implements Serializable {
    private static final long serialVersionUID = -8806439091674811734L;
    private final Configuration conf = ConfigurationContext.getInstance();
    private OAuthSupport oauth;

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ String getParameter(String str) {
        return super.getParameter(str);
    }

    public /* bridge */ /* synthetic */ String getToken() {
        return super.getToken();
    }

    public /* bridge */ /* synthetic */ String getTokenSecret() {
        return super.getTokenSecret();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    RequestToken(HttpResponse httpResponse, OAuthSupport oAuthSupport) {
        super(httpResponse);
        this.oauth = oAuthSupport;
    }

    public RequestToken(String str, String str2) {
        super(str, str2);
    }

    RequestToken(String str, String str2, OAuthSupport oAuthSupport) {
        super(str, str2);
        this.oauth = oAuthSupport;
    }

    public String getAuthorizationURL() {
        return this.conf.getOAuthAuthorizationURL() + "?oauth_token=" + getToken();
    }

    public String getAuthenticationURL() {
        return this.conf.getOAuthAuthenticationURL() + "?oauth_token=" + getToken();
    }
}
