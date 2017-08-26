package twitter4j.api;

import java.io.InputStream;
import twitter4j.DirectMessage;
import twitter4j.Paging;
import twitter4j.ResponseList;

public interface DirectMessagesResources {
    DirectMessage destroyDirectMessage(long j);

    InputStream getDMImageAsStream(String str);

    ResponseList<DirectMessage> getDirectMessages();

    ResponseList<DirectMessage> getDirectMessages(Paging paging);

    ResponseList<DirectMessage> getSentDirectMessages();

    ResponseList<DirectMessage> getSentDirectMessages(Paging paging);

    DirectMessage sendDirectMessage(long j, String str);

    DirectMessage sendDirectMessage(String str, String str2);

    DirectMessage showDirectMessage(long j);
}
