package com.vungle.publisher;

import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.mraid.view.MraidView;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: vungle */
public enum tl {
    CLOSE(String.CLOSE),
    EXPAND {
    },
    USE_CUSTOM_CLOSE("useCustomClose"),
    OPEN {
    },
    RESIZE {
    },
    SET_ORIENTATION_PROPERTIES("setOrientationProperties"),
    PLAY_VIDEO {
    },
    STORE_PICTURE {
    },
    CREATE_CALENDAR_EVENT {
    },
    PROPERTIES_SET("propertiesChangeCompleted"),
    SUCCESSFUL_VIEW_EVENT("successfulView"),
    TPAT_EVENT("tpat"),
    USER_ACTION_EVENT(MraidView.ACTION_KEY),
    USER_VALUE_ACTION_EVENT("actionWithValue"),
    ERROR_EVENT(String.VIDEO_ERROR),
    PRIVACY_PAGE_EVENT("openPrivacy"),
    PLAY_HTML_VIDEO_EVENT("playHTML5Video"),
    USE_CUSTOM_PRIVACY("useCustomPrivacy"),
    THROW_INCENTIVIZED_DIALOG("throwIncentivizedDialog"),
    UNSPECIFIED(BuildConfig.FLAVOR);
    
    private final String u;

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        a() {
        }

        public static tl a(String str) {
            for (tl tlVar : tl.values()) {
                if (tlVar.u.equals(str)) {
                    return tlVar;
                }
            }
            return tl.UNSPECIFIED;
        }
    }

    private tl(String str) {
        this.u = str;
    }
}
