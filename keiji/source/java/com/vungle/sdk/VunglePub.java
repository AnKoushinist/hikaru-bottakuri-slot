package com.vungle.sdk;

import android.content.Context;
import com.vungle.log.Logger;
import com.vungle.publisher.AdConfig;
import com.vungle.publisher.Demographic;
import com.vungle.publisher.Orientation;
import com.vungle.publisher.VunglePubBase;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.re;

/* compiled from: vungle */
public final class VunglePub {
    private static final VunglePubBase a = com.vungle.publisher.VunglePub.getInstance();
    private static boolean b;
    private static boolean c;
    private static boolean d;

    /* compiled from: vungle */
    public interface EventListener {
        void onVungleAdEnd();

        void onVungleAdStart();

        void onVungleView(double d, double d2);
    }

    /* compiled from: vungle */
    public static final class Gender {
        public static final int FEMALE = 1;
        public static final int MALE = 0;
        public static final int UNKNOWN = -1;

        static com.vungle.publisher.Demographic.Gender a(int i) {
            switch (i) {
                case MALE /*0*/:
                    return com.vungle.publisher.Demographic.Gender.male;
                case FEMALE /*1*/:
                    return com.vungle.publisher.Demographic.Gender.female;
                default:
                    return null;
            }
        }

        public static String toString(int i) {
            switch (i) {
                case MALE /*0*/:
                    return "male";
                case FEMALE /*1*/:
                    return "female";
                default:
                    return "unknown";
            }
        }

        private Gender() {
        }
    }

    /* compiled from: vungle */
    static class a implements com.vungle.publisher.EventListener {
        private EventListener a;

        a(EventListener eventListener) {
            this.a = eventListener;
        }

        public final void onAdEnd(boolean z, boolean z2) {
            this.a.onVungleAdEnd();
        }

        public final void onAdStart() {
            this.a.onVungleAdStart();
        }

        public final void onAdUnavailable(String str) {
        }

        public final void onAdPlayableChanged(boolean z) {
        }

        public final void onVideoView(boolean z, int i, int i2) {
            this.a.onVungleView(((double) i) / 1000.0d, ((double) i2) / 1000.0d);
        }
    }

    private VunglePub() {
    }

    public static String getVersionString() {
        return com.vungle.publisher.VunglePub.VERSION;
    }

    public static void init(Context context, String str) {
        init(context, str, 0, -1);
    }

    public static void init(Context context, String str, int i, int i2) {
        boolean z = false;
        if (!b) {
            Injector instance = Injector.getInstance();
            Class cls = VungleAdvert.class;
            try {
                if (instance.a) {
                    Logger.d(Logger.INJECT_TAG, "full screen ad activity class in injector NOT set - already initialized");
                } else {
                    Logger.d(Logger.INJECT_TAG, "setting full screen ad activity class in injector " + cls);
                    re a = instance.a();
                    if (a.g) {
                        Logger.d(Logger.INJECT_TAG, "video full screen ad activity class in publisher module NOT set - already initialized");
                    } else {
                        Logger.d(Logger.INJECT_TAG, "setting video full screen ad activity class in publisher module: " + cls);
                        a.c = cls;
                    }
                }
            } catch (Throwable e) {
                Logger.e(Logger.INJECT_TAG, e);
            }
            cls = VungleAdvert.class;
            try {
                if (instance.a) {
                    Logger.d(Logger.INJECT_TAG, "full screen ad activity class in injector NOT set - already initialized");
                } else {
                    Logger.d(Logger.INJECT_TAG, "setting full screen ad activity class in injector " + cls);
                    re a2 = instance.a();
                    if (a2.g) {
                        Logger.d(Logger.INJECT_TAG, "mraid full screen ad activity class in publisher module NOT set - already initialized");
                    } else {
                        Logger.d(Logger.INJECT_TAG, "setting mraid full screen ad activity class in publisher module: " + cls);
                        a2.d = cls;
                    }
                }
            } catch (Throwable e2) {
                Logger.e(Logger.INJECT_TAG, e2);
            }
        }
        a.init(context, str);
        if (!b) {
            boolean z2;
            if (i > 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            com.vungle.publisher.Demographic.Gender a3 = Gender.a(i2);
            if (a3 != null) {
                z = true;
            }
            if (z2 || z) {
                Demographic demographic = a.getDemographic();
                if (z2) {
                    demographic.setAge(Integer.valueOf(i));
                }
                if (z) {
                    demographic.setGender(a3);
                }
                b = true;
            }
        }
    }

    public static void setAutoRotation(boolean z) {
        AdConfig globalAdConfig = a.getGlobalAdConfig();
        if (globalAdConfig != null) {
            globalAdConfig.setOrientation(z ? Orientation.autoRotate : Orientation.matchVideo);
        }
    }

    public static void setBackButtonEnabled(boolean z) {
        c = z;
    }

    public static void setEventListener(EventListener eventListener) {
        if (eventListener != null) {
            a.setEventListeners(new a(eventListener));
        }
    }

    public static void setIncentivizedBackButtonEnabled(boolean z) {
        d = z;
    }

    public static boolean getSoundEnabled() {
        AdConfig globalAdConfig = a.getGlobalAdConfig();
        if (globalAdConfig != null) {
            return globalAdConfig.isSoundEnabled();
        }
        return false;
    }

    public static void setSoundEnabled(boolean z) {
        AdConfig globalAdConfig = a.getGlobalAdConfig();
        if (globalAdConfig != null) {
            globalAdConfig.setSoundEnabled(z);
        }
    }

    public static boolean isVideoAvailable() {
        return isVideoAvailable(false);
    }

    public static boolean isVideoAvailable(boolean z) {
        return a.isAdPlayable();
    }

    public static boolean displayAdvert() {
        boolean isVideoAvailable = isVideoAvailable();
        AdConfig adConfig = new AdConfig();
        adConfig.setBackButtonImmediatelyEnabled(c);
        a.playAd(adConfig);
        return isVideoAvailable;
    }

    public static boolean displayIncentivizedAdvert(boolean z) {
        return displayIncentivizedAdvert(null, z);
    }

    public static boolean displayIncentivizedAdvert(String str, boolean z) {
        boolean isVideoAvailable = isVideoAvailable();
        AdConfig adConfig = new AdConfig();
        adConfig.setBackButtonImmediatelyEnabled(d);
        a.playAd(adConfig);
        return isVideoAvailable;
    }

    public static void onPause() {
        a.onPause();
    }

    public static void onResume() {
        a.onResume();
    }
}
