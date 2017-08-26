package twitter4j.auth;

import twitter4j.conf.Configuration;

public final class AuthorizationFactory {
    public static Authorization getInstance(Configuration configuration) {
        Authorization authorization = null;
        String oAuthConsumerKey = configuration.getOAuthConsumerKey();
        String oAuthConsumerSecret = configuration.getOAuthConsumerSecret();
        if (oAuthConsumerKey == null || oAuthConsumerSecret == null) {
            oAuthConsumerKey = configuration.getUser();
            oAuthConsumerSecret = configuration.getPassword();
            if (!(oAuthConsumerKey == null || oAuthConsumerSecret == null)) {
                authorization = new BasicAuthorization(oAuthConsumerKey, oAuthConsumerSecret);
            }
        } else if (configuration.isApplicationOnlyAuthEnabled()) {
            authorization = new OAuth2Authorization(configuration);
            oAuthConsumerKey = configuration.getOAuth2TokenType();
            oAuthConsumerSecret = configuration.getOAuth2AccessToken();
            if (!(oAuthConsumerKey == null || oAuthConsumerSecret == null)) {
                authorization.setOAuth2Token(new OAuth2Token(oAuthConsumerKey, oAuthConsumerSecret));
            }
        } else {
            authorization = new OAuthAuthorization(configuration);
            oAuthConsumerKey = configuration.getOAuthAccessToken();
            oAuthConsumerSecret = configuration.getOAuthAccessTokenSecret();
            if (!(oAuthConsumerKey == null || oAuthConsumerSecret == null)) {
                authorization.setOAuthAccessToken(new AccessToken(oAuthConsumerKey, oAuthConsumerSecret));
            }
        }
        if (authorization == null) {
            return NullAuthorization.getInstance();
        }
        return authorization;
    }
}
