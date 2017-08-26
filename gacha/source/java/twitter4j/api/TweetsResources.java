package twitter4j.api;

import java.io.File;
import twitter4j.IDs;
import twitter4j.OEmbed;
import twitter4j.OEmbedRequest;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.UploadedMedia;

public interface TweetsResources {
    Status destroyStatus(long j);

    OEmbed getOEmbed(OEmbedRequest oEmbedRequest);

    IDs getRetweeterIds(long j, int i, long j2);

    IDs getRetweeterIds(long j, long j2);

    ResponseList<Status> getRetweets(long j);

    ResponseList<Status> lookup(long[] jArr);

    Status retweetStatus(long j);

    Status showStatus(long j);

    Status updateStatus(String str);

    Status updateStatus(StatusUpdate statusUpdate);

    UploadedMedia uploadMedia(File file);
}
