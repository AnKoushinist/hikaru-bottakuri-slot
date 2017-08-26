package com.igaworks.impl;

import com.igaworks.interfaces.CommonInterface;

public class CommonFrameworkFactory {
    public static boolean isHasAdbrixSDK = false;
    private static CommonInterface singleton;

    public static CommonInterface getCommonFramework() {
        if (singleton == null) {
            singleton = new CommonFrameworkImpl() {
            };
        }
        try {
            Class.forName("com.igaworks.adbrix.IgawAdbrix");
            isHasAdbrixSDK = true;
        } catch (Exception e) {
            isHasAdbrixSDK = false;
        }
        try {
            Class.forName("com.igaworks.adbrix.impl.ADBrixFrameworkFactory");
            isHasAdbrixSDK = true;
        } catch (Exception e2) {
            isHasAdbrixSDK = false;
        }
        try {
            Class.forName("com.igaworks.liveops.pushservice.LiveOpsPushService");
        } catch (Exception e3) {
        }
        try {
            Class.forName("com.igaworks.commerce.impl.CommerceFrameworkFactory");
        } catch (Exception e4) {
        }
        return singleton;
    }
}
