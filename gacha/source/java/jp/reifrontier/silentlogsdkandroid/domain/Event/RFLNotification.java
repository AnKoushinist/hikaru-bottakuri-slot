package jp.reifrontier.silentlogsdkandroid.domain.Event;

import com.google.a.a.c;
import java.util.List;

public class RFLNotification {
    @c(a = "message")
    private RFLNotificationMessage message;
    @c(a = "poi")
    private List<RFLPoi> poiList;

    private class RFLNotificationMessage {
        @c(a = "message_type")
        private String messageType;
        @c(a = "message_value")
        private RFLNotificationMessageValue messageValue;

        private RFLNotificationMessage() {
        }

        public String getMessageType() {
            return this.messageType;
        }

        RFLNotificationMessageValue getMessageValue() {
            return this.messageValue;
        }
    }

    private class RFLNotificationMessageValue {
        @c(a = "body")
        private RFLText body;
        @c(a = "title")
        private RFLText title;
        @c(a = "url")
        private String url;

        private RFLNotificationMessageValue() {
        }

        RFLText getBody() {
            return this.body;
        }

        RFLText getTitle() {
            return this.title;
        }

        String getUrl() {
            return this.url;
        }
    }

    public List<RFLPoi> getPoiList() {
        return this.poiList;
    }

    public String getMessageType() {
        return this.message.getMessageType();
    }

    public String getTitleText() {
        return this.message.getMessageValue().getTitle().getText();
    }

    public String getBodyText() {
        return this.message.getMessageValue().getBody().getText();
    }

    public String getUrl() {
        return this.message.getMessageValue().getUrl();
    }
}
