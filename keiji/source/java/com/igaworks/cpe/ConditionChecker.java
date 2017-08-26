package com.igaworks.cpe;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

public class ConditionChecker {
    public static boolean checkInstalled(Context context, String str) {
        try {
            context.getPackageManager().getApplicationInfo(str, 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
