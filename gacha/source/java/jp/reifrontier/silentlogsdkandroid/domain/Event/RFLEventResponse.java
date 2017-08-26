package jp.reifrontier.silentlogsdkandroid.domain.Event;

import com.google.a.a.c;
import java.util.List;

public class RFLEventResponse {
    @c(a = "eventlist")
    private List<RFLEvent> events;
    @c(a = "schema")
    private String schema;

    public String getSchema() {
        return this.schema;
    }

    public List<RFLEvent> getEvents() {
        return this.events;
    }
}
