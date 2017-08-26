package b.a.a.a.a.b;

import twitter4j.HttpResponseCode;

/* compiled from: ResponseParser */
public class r {
    public static int a(int i) {
        if (i >= HttpResponseCode.OK && i <= 299) {
            return 0;
        }
        if (i >= HttpResponseCode.MULTIPLE_CHOICES && i <= 399) {
            return 1;
        }
        if (i >= HttpResponseCode.BAD_REQUEST && i <= 499) {
            return 0;
        }
        if (i >= HttpResponseCode.INTERNAL_SERVER_ERROR) {
            return 1;
        }
        return 1;
    }
}
