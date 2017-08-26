package jp.reifrontier.silentlogsdkandroid.util;

import android.content.Context;
import android.util.Log;
import c.a.a.a;
import java.util.regex.Pattern;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;

public class RFLLogTree extends a {
    private static final Pattern ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");
    private static final int CALL_STACK_INDEX = 5;
    private static final int MAX_LOG_LENGTH = 4000;
    private Context mContext;
    private String sLogName;

    public RFLLogTree(String str, Context context) {
        this.sLogName = str;
        this.mContext = context;
    }

    protected void log(int i, String str, String str2, Throwable th) {
        RFLLogger instance = RFLLogger.getInstance(this.sLogName, this.mContext);
        if (str2.length() < MAX_LOG_LENGTH) {
            switch (i) {
                case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                    Log.println(i, str, str2);
                    instance.info(str2);
                    return;
                case AdInfo.BANNER_KIND_BANNER6 /*6*/:
                    Log.println(i, str, str2);
                    instance.error(str2);
                    return;
                case AdInfo.BANNER_KIND_BANNER7 /*7*/:
                    Log.wtf(str, str2);
                    return;
                default:
                    Log.println(i, str, str2);
                    return;
            }
        }
        int i2 = 0;
        int length = str2.length();
        while (i2 < length) {
            int indexOf = str2.indexOf(10, i2);
            if (indexOf == -1) {
                indexOf = length;
            }
            while (true) {
                int min = Math.min(indexOf, i2 + MAX_LOG_LENGTH);
                String substring = str2.substring(i2, min);
                switch (i) {
                    case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                        Log.println(i, str, substring);
                        instance.info(substring);
                        break;
                    case AdInfo.BANNER_KIND_BANNER6 /*6*/:
                        Log.println(i, str, substring);
                        instance.error(substring);
                        break;
                    case AdInfo.BANNER_KIND_BANNER7 /*7*/:
                        Log.wtf(str, substring);
                        break;
                    default:
                        Log.println(i, str, substring);
                        break;
                }
                if (min >= indexOf) {
                    i2 = min + 1;
                } else {
                    i2 = min;
                }
            }
        }
    }
}
