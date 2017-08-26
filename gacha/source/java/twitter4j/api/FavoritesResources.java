package twitter4j.api;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;

public interface FavoritesResources {
    Status createFavorite(long j);

    Status destroyFavorite(long j);

    ResponseList<Status> getFavorites();

    ResponseList<Status> getFavorites(long j);

    ResponseList<Status> getFavorites(long j, Paging paging);

    ResponseList<Status> getFavorites(String str);

    ResponseList<Status> getFavorites(String str, Paging paging);

    ResponseList<Status> getFavorites(Paging paging);
}
