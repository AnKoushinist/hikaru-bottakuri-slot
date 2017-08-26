package com.igaworks.core;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class DisplaySetter {
    private static int density;
    private static DisplayMetrics displayXY = new DisplayMetrics();
    private static boolean isInit = false;
    public static boolean isPortrait;
    private static double scale;

    private static void initScale(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            density = displayMetrics.densityDpi;
            scale = 240.0d / ((double) density);
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayXY);
            isInit = true;
        }
        if (context.getResources().getConfiguration().orientation == 2) {
            isPortrait = false;
        } else {
            isPortrait = true;
        }
    }

    public static DisplayMetrics getDisplayXY(Context context) {
        if (!isInit) {
            initScale(context);
        }
        return displayXY;
    }
}
