package twitter4j.api;

import twitter4j.User;

public interface SpamReportingResource {
    User reportSpam(long j);

    User reportSpam(String str);
}
