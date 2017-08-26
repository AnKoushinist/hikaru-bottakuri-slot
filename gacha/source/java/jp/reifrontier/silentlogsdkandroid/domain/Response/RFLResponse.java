package jp.reifrontier.silentlogsdkandroid.domain.Response;

import com.google.a.a.c;

public class RFLResponse {

    public static class RFLResponseError {
        @c(a = "error")
        String error;

        public RFLResponseError(String str) {
            this.error = str;
        }

        public String getError() {
            return this.error;
        }
    }

    public static class RFLResponseSuccess {
        String date;
        String timezone;

        public RFLResponseSuccess(String str, String str2) {
            this.date = str;
            this.timezone = str2;
        }

        public String getDate() {
            return this.date;
        }

        public String getTimezone() {
            return this.timezone;
        }
    }
}
