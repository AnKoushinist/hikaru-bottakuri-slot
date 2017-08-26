package okhttp3.internal.http;

import com.raizlabs.android.dbflow.sql.trigger.TriggerMethod;

public final class HttpMethod {
    public static boolean invalidatesCache(String str) {
        return str.equals("POST") || str.equals("PATCH") || str.equals("PUT") || str.equals(TriggerMethod.DELETE) || str.equals("MOVE");
    }

    public static boolean requiresRequestBody(String str) {
        return str.equals("POST") || str.equals("PUT") || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    public static boolean permitsRequestBody(String str) {
        return requiresRequestBody(str) || str.equals("OPTIONS") || str.equals(TriggerMethod.DELETE) || str.equals("PROPFIND") || str.equals("MKCOL") || str.equals("LOCK");
    }

    public static boolean redirectsToGet(String str) {
        return !str.equals("PROPFIND");
    }

    private HttpMethod() {
    }
}