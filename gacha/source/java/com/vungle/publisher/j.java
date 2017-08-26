package com.vungle.publisher;

import android.database.Cursor;
import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public enum j {
    vungle_local,
    vungle_streaming,
    vungle_mraid,
    third_party_mraid;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        com.vungle.publisher.gm.a a;

        public final j a(String str) {
            if (str == null) {
                return null;
            }
            for (j jVar : j.values()) {
                if (jVar.toString().equals(str)) {
                    return jVar;
                }
            }
            this.a.a(Logger.PROTOCOL_TAG, "unknown AdType: " + str, new RuntimeException());
            return null;
        }

        public final j a(Cursor cursor, String str) {
            if (cursor != null && cursor.getCount() == 1 && cursor.moveToFirst()) {
                return a(cb.f(cursor, str));
            }
            this.a.a(Logger.PROTOCOL_TAG, "AdType not found", new RuntimeException());
            return null;
        }
    }
}
