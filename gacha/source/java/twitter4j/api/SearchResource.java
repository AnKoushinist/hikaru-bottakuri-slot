package twitter4j.api;

import twitter4j.Query;
import twitter4j.QueryResult;

public interface SearchResource {
    QueryResult search(Query query);
}
