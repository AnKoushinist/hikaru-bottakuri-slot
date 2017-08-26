package com.igaworks;

import android.content.Context;
import com.igaworks.impl.CommonFrameworkFactory;
import com.igaworks.interfaces.CommonInterface;

public final class IgawCommon {
    private static CommonInterface commonFrameWork;

    private static CommonInterface framework() {
        if (commonFrameWork == null) {
            synchronized (IgawCommon.class) {
                if (commonFrameWork == null) {
                    commonFrameWork = CommonFrameworkFactory.getCommonFramework();
                }
            }
        }
        return commonFrameWork;
    }

    public static void startSession(Context context) {
        framework().startSession(context);
    }

    public static void endSession() {
        framework().endSession();
    }

    public static void startApplication(Context context) {
        framework().startApplication(context);
    }

    public static void onReceiveReferral(Context context, String str) {
        framework().onReceiveReferral(context, str);
    }
}
