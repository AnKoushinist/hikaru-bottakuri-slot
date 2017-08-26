package com.igaworks.adbrix;

import com.igaworks.adbrix.core.ADBrixUpdateLog;
import com.igaworks.adbrix.impl.ADBrixFrameworkFactory;
import com.igaworks.adbrix.interfaces.ADBrixInterface;

public class IgawAdbrix {
    private static ADBrixInterface adbrixFrameWork;

    static {
        try {
            ADBrixUpdateLog.updateVersion();
        } catch (Exception e) {
        }
    }

    private static ADBrixInterface framework() {
        if (adbrixFrameWork == null) {
            adbrixFrameWork = ADBrixFrameworkFactory.getFramework();
        }
        return adbrixFrameWork;
    }

    public static void firstTimeExperience(String str, String str2) {
        framework().firstTimeExperience(str, str2);
    }

    public static void buy(String str, String str2) {
        framework().buy(str, str2);
    }

    public static void retention(String str, String str2) {
        framework().retention(str, str2);
    }
}
