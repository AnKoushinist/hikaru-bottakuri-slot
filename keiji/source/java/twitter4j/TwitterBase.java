package twitter4j;

import twitter4j.auth.Authorization;
import twitter4j.conf.Configuration;

public interface TwitterBase {
    void addRateLimitStatusListener(RateLimitStatusListener rateLimitStatusListener);

    Authorization getAuthorization();

    Configuration getConfiguration();

    long getId();

    String getScreenName();
}
