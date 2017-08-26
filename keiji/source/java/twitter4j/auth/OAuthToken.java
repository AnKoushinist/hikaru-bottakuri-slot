package twitter4j.auth;

import java.io.Serializable;
import javax.crypto.spec.SecretKeySpec;
import twitter4j.HttpResponse;

abstract class OAuthToken implements Serializable {
    private static final long serialVersionUID = -7841506492508140600L;
    private String[] responseStr;
    private transient SecretKeySpec secretKeySpec;
    private final String token;
    private final String tokenSecret;

    public OAuthToken(String str, String str2) {
        this.responseStr = null;
        if (str == null) {
            throw new IllegalArgumentException("Token can't be null");
        } else if (str2 == null) {
            throw new IllegalArgumentException("TokenSecret can't be null");
        } else {
            this.token = str;
            this.tokenSecret = str2;
        }
    }

    OAuthToken(HttpResponse httpResponse) {
        this(httpResponse.asString());
    }

    OAuthToken(String str) {
        this.responseStr = null;
        this.responseStr = str.split("&");
        this.tokenSecret = getParameter("oauth_token_secret");
        this.token = getParameter("oauth_token");
    }

    public String getToken() {
        return this.token;
    }

    public String getTokenSecret() {
        return this.tokenSecret;
    }

    void setSecretKeySpec(SecretKeySpec secretKeySpec) {
        this.secretKeySpec = secretKeySpec;
    }

    SecretKeySpec getSecretKeySpec() {
        return this.secretKeySpec;
    }

    public String getParameter(String str) {
        for (String str2 : this.responseStr) {
            if (str2.startsWith(str + '=')) {
                return str2.split("=")[1].trim();
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OAuthToken)) {
            return false;
        }
        OAuthToken oAuthToken = (OAuthToken) obj;
        if (!this.token.equals(oAuthToken.token)) {
            return false;
        }
        if (this.tokenSecret.equals(oAuthToken.tokenSecret)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.token.hashCode() * 31) + this.tokenSecret.hashCode();
    }

    public String toString() {
        return "OAuthToken{token='" + this.token + '\'' + ", tokenSecret='" + this.tokenSecret + '\'' + ", secretKeySpec=" + this.secretKeySpec + '}';
    }
}
