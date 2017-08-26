package jp.reifrontier.silentlogsdkandroid.domain.Event;

import com.google.a.a.c;

public class RFLNotifiedEvent {
    @c(a = "event_id")
    private Integer eventId;
    @c(a = "notify")
    private Boolean notify;
    @c(a = "open")
    private Boolean open;
    @c(a = "time")
    private long time;

    public RFLNotifiedEvent(long j, Integer num, boolean z, boolean z2) {
        this.time = j;
        this.eventId = num;
    }

    public long getTime() {
        return this.time;
    }

    public Integer getEventId() {
        return this.eventId;
    }

    public Boolean isNotify() {
        return this.notify;
    }

    public Boolean isOpen() {
        return this.open;
    }
}
