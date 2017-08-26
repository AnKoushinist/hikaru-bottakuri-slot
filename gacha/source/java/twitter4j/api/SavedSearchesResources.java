package twitter4j.api;

import twitter4j.ResponseList;
import twitter4j.SavedSearch;

public interface SavedSearchesResources {
    SavedSearch createSavedSearch(String str);

    SavedSearch destroySavedSearch(int i);

    ResponseList<SavedSearch> getSavedSearches();

    SavedSearch showSavedSearch(int i);
}
