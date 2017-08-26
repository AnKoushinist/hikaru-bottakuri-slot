package twitter4j.api;

import java.util.Map;
import twitter4j.RateLimitStatus;
import twitter4j.ResponseList;
import twitter4j.TwitterAPIConfiguration;

public interface HelpResources {

    public interface Language {
        String getCode();

        String getName();

        String getStatus();
    }

    TwitterAPIConfiguration getAPIConfiguration();

    ResponseList<Language> getLanguages();

    String getPrivacyPolicy();

    Map<String, RateLimitStatus> getRateLimitStatus();

    Map<String, RateLimitStatus> getRateLimitStatus(String... strArr);

    String getTermsOfService();
}
