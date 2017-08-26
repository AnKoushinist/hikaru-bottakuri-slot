package com.tapjoy;

import java.util.List;
import java.util.Map;
import org.cocos2dx.lib.BuildConfig;

public class TapjoyHttpURLResponse {
    public int contentLength;
    public long date;
    public long expires;
    public Map headerFields;
    public String redirectURL;
    public String response;
    public int statusCode;

    public String getHeaderFieldAsString(String str) {
        String str2 = BuildConfig.FLAVOR;
        if (this.headerFields != null) {
            List list = (List) this.headerFields.get(str);
            if (!(list == null || list.get(0) == null)) {
                return (String) list.get(0);
            }
        }
        return str2;
    }
}
