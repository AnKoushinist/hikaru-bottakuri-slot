package com.vungle.publisher;

import com.vungle.log.Logger;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class qu {
    @Inject
    public Provider<String> a;
    @Inject
    public Provider<String> b;

    @Inject
    qu() {
    }

    public static void a(String str) {
        try {
            if (new File(str).exists()) {
                qx.a(str);
            } else {
                Logger.v(Logger.FILE_TAG, "ad temp directory does not exist " + str);
            }
        } catch (Exception e) {
            Logger.d(Logger.FILE_TAG, "error deleting ad temp directory " + str);
        }
    }
}
