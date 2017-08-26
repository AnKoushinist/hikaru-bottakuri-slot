package com.vungle.publisher;

import android.location.Location;
import android.os.LocaleList;
import com.vungle.log.Logger;
import com.vungle.publisher.inject.Injector;
import java.util.Locale;
import java.util.TimeZone;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.cocos2dx.lib.BuildConfig;

@Singleton
/* compiled from: vungle */
public final class sf implements sr {
    @Inject
    sj a;

    @Inject
    sf() {
        Injector.b().a(this);
    }

    public final String a() {
        String str = BuildConfig.FLAVOR;
        try {
            if (!agl.a(pj.NOUGAT) || LocaleList.getDefault().size() <= 0) {
                return Locale.getDefault().getISO3Language();
            }
            return LocaleList.getDefault().get(0).toLanguageTag();
        } catch (Throwable e) {
            Logger.w(Logger.LOCATION_TAG, "error getting ISO 3-letter language code", e);
            return str;
        }
    }

    public final Location b() {
        Location location = null;
        if (this.a == null) {
            Logger.d(Logger.LOCATION_TAG, "cannot provide detailed location - null detailed location provider");
        } else {
            synchronized (this) {
                location = this.a.b();
            }
        }
        return location;
    }

    public final String c() {
        return TimeZone.getDefault().getID();
    }
}
