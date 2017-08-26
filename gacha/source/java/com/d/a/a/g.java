package com.d.a.a;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.util.Log;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.TwitterResponse;

/* compiled from: LogHandler */
public class g implements h {
    boolean a = true;
    int b = 2;

    public boolean a() {
        return this.a;
    }

    public boolean a(int i) {
        return i >= this.b;
    }

    public void a(int i, String str, String str2) {
        a(i, str, str2, null);
    }

    public void a(int i, String str, String str2, Throwable th) {
        if (a() && a(i)) {
            switch (i) {
                case TwitterResponse.READ_WRITE /*2*/:
                    Log.v(str, str2, th);
                    return;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    Log.d(str, str2, th);
                    return;
                case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                    Log.i(str, str2, th);
                    return;
                case AdInfo.BANNER_KIND_BANNER5 /*5*/:
                    Log.w(str, str2, th);
                    return;
                case AdInfo.BANNER_KIND_BANNER6 /*6*/:
                    Log.e(str, str2, th);
                    return;
                case AdInfo.BANNER_KIND_BANNER8 /*8*/:
                    if (Integer.valueOf(VERSION.SDK).intValue() > 8) {
                        c(str, str2, th);
                        return;
                    } else {
                        Log.e(str, str2, th);
                        return;
                    }
                default:
                    return;
            }
        }
    }

    @TargetApi(8)
    private void c(String str, String str2, Throwable th) {
        Log.wtf(str, str2, th);
    }

    public void a(String str, String str2) {
        a(2, str, str2);
    }

    public void b(String str, String str2) {
        a(2, str, str2);
    }

    public void c(String str, String str2) {
        a(5, str, str2);
    }

    public void a(String str, String str2, Throwable th) {
        a(5, str, str2, th);
    }

    public void d(String str, String str2) {
        a(6, str, str2);
    }

    public void b(String str, String str2, Throwable th) {
        a(6, str, str2, th);
    }
}
