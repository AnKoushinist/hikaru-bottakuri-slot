package twitter4j.auth;

import com.d.a.a.c;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.HttpResponse;
import twitter4j.JSONException;
import twitter4j.JSONObject;

public class OAuth2Token implements Serializable {
    private static final long serialVersionUID = -8985359441959903216L;
    private String accessToken;
    private String tokenType;

    OAuth2Token(HttpResponse httpResponse) {
        JSONObject asJSONObject = httpResponse.asJSONObject();
        this.tokenType = getRawString("token_type", asJSONObject);
        try {
            this.accessToken = URLDecoder.decode(getRawString("access_token", asJSONObject), c.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
        }
    }

    public OAuth2Token(String str, String str2) {
        this.tokenType = str;
        this.accessToken = str2;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    String generateAuthorizationHeader() {
        String str = BuildConfig.FLAVOR;
        try {
            str = URLEncoder.encode(this.accessToken, c.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
        }
        return "Bearer " + str;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof OAuth2Token)) {
            return false;
        }
        OAuth2Token oAuth2Token = (OAuth2Token) obj;
        if (this.tokenType != null) {
            if (!this.tokenType.equals(oAuth2Token.tokenType)) {
                return false;
            }
        } else if (oAuth2Token.tokenType != null) {
            return false;
        }
        if (this.accessToken != null) {
            if (!this.accessToken.equals(oAuth2Token.accessToken)) {
                return false;
            }
        } else if (oAuth2Token.accessToken != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.tokenType != null) {
            hashCode = this.tokenType.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode *= 31;
        if (this.accessToken != null) {
            i = this.accessToken.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "OAuth2Token{tokenType='" + this.tokenType + '\'' + ", accessToken='" + this.accessToken + '\'' + '}';
    }

    private static String getRawString(String str, JSONObject jSONObject) {
        String str2 = null;
        try {
            if (!jSONObject.isNull(str)) {
                str2 = jSONObject.getString(str);
            }
        } catch (JSONException e) {
        }
        return str2;
    }
}