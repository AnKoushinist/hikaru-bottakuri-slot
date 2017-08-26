package twitter4j.auth;

public interface OAuth2Support {
    OAuth2Token getOAuth2Token();

    void invalidateOAuth2Token();

    void setOAuth2Token(OAuth2Token oAuth2Token);

    void setOAuthConsumer(String str, String str2);
}
