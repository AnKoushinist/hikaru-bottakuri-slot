package twitter4j.api;

import twitter4j.PagableResponseList;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.User;
import twitter4j.UserList;

public interface ListsResources {
    UserList createUserList(String str, boolean z, String str2);

    UserList createUserListMember(long j, long j2);

    UserList createUserListMember(long j, String str, long j2);

    UserList createUserListMember(String str, String str2, long j);

    UserList createUserListMembers(long j, String str, long[] jArr);

    UserList createUserListMembers(long j, String str, String[] strArr);

    UserList createUserListMembers(long j, long[] jArr);

    UserList createUserListMembers(long j, String[] strArr);

    UserList createUserListMembers(String str, String str2, long[] jArr);

    UserList createUserListMembers(String str, String str2, String[] strArr);

    UserList createUserListSubscription(long j);

    UserList createUserListSubscription(long j, String str);

    UserList createUserListSubscription(String str, String str2);

    UserList destroyUserList(long j);

    UserList destroyUserList(long j, String str);

    UserList destroyUserList(String str, String str2);

    UserList destroyUserListMember(long j, long j2);

    UserList destroyUserListMember(long j, String str);

    UserList destroyUserListMember(long j, String str, long j2);

    UserList destroyUserListMember(String str, String str2, long j);

    UserList destroyUserListMembers(long j, long[] jArr);

    UserList destroyUserListMembers(long j, String[] strArr);

    UserList destroyUserListMembers(String str, String str2, String[] strArr);

    UserList destroyUserListSubscription(long j);

    UserList destroyUserListSubscription(long j, String str);

    UserList destroyUserListSubscription(String str, String str2);

    PagableResponseList<User> getUserListMembers(long j, long j2);

    PagableResponseList<User> getUserListMembers(long j, String str, long j2);

    PagableResponseList<User> getUserListMembers(String str, String str2, long j);

    PagableResponseList<UserList> getUserListMemberships(long j);

    PagableResponseList<UserList> getUserListMemberships(long j, long j2);

    PagableResponseList<UserList> getUserListMemberships(long j, long j2, boolean z);

    PagableResponseList<UserList> getUserListMemberships(String str, long j);

    PagableResponseList<UserList> getUserListMemberships(String str, long j, boolean z);

    ResponseList<Status> getUserListStatuses(long j, String str, Paging paging);

    ResponseList<Status> getUserListStatuses(long j, Paging paging);

    ResponseList<Status> getUserListStatuses(String str, String str2, Paging paging);

    PagableResponseList<User> getUserListSubscribers(long j, long j2);

    PagableResponseList<User> getUserListSubscribers(long j, String str, long j2);

    PagableResponseList<User> getUserListSubscribers(String str, String str2, long j);

    PagableResponseList<UserList> getUserListSubscriptions(String str, long j);

    ResponseList<UserList> getUserLists(long j);

    ResponseList<UserList> getUserLists(String str);

    PagableResponseList<UserList> getUserListsOwnerships(long j, int i, long j2);

    PagableResponseList<UserList> getUserListsOwnerships(String str, int i, long j);

    UserList showUserList(long j);

    UserList showUserList(long j, String str);

    UserList showUserList(String str, String str2);

    User showUserListMembership(long j, long j2);

    User showUserListMembership(long j, String str, long j2);

    User showUserListMembership(String str, String str2, long j);

    User showUserListSubscription(long j, long j2);

    User showUserListSubscription(long j, String str, long j2);

    User showUserListSubscription(String str, String str2, long j);

    UserList updateUserList(long j, String str, String str2, boolean z, String str3);

    UserList updateUserList(long j, String str, boolean z, String str2);

    UserList updateUserList(String str, String str2, String str3, boolean z, String str4);
}
