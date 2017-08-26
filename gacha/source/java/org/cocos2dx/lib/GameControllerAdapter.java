package org.cocos2dx.lib;

import java.util.ArrayList;

public class GameControllerAdapter {
    private static ArrayList<Runnable> sRunnableFrameStartList = null;

    private static native void nativeControllerAxisEvent(String str, int i, int i2, float f, boolean z);

    private static native void nativeControllerButtonEvent(String str, int i, int i2, boolean z, float f, boolean z2);

    private static native void nativeControllerConnected(String str, int i);

    private static native void nativeControllerDisconnected(String str, int i);

    public static void addRunnableToFrameStartList(Runnable runnable) {
        if (sRunnableFrameStartList == null) {
            sRunnableFrameStartList = new ArrayList();
        }
        sRunnableFrameStartList.add(runnable);
    }

    public static void removeRunnableFromFrameStartList(Runnable runnable) {
        if (sRunnableFrameStartList != null) {
            sRunnableFrameStartList.remove(runnable);
        }
    }

    public static void onDrawFrameStart() {
        if (sRunnableFrameStartList != null) {
            int size = sRunnableFrameStartList.size();
            for (int i = 0; i < size; i++) {
                ((Runnable) sRunnableFrameStartList.get(i)).run();
            }
        }
    }

    public static void onConnected(final String str, final int i) {
        Cocos2dxHelper.runOnGLThread(new Runnable() {
            public void run() {
                GameControllerAdapter.nativeControllerConnected(str, i);
            }
        });
    }

    public static void onDisconnected(final String str, final int i) {
        Cocos2dxHelper.runOnGLThread(new Runnable() {
            public void run() {
                GameControllerAdapter.nativeControllerDisconnected(str, i);
            }
        });
    }

    public static void onButtonEvent(String str, int i, int i2, boolean z, float f, boolean z2) {
        final String str2 = str;
        final int i3 = i;
        final int i4 = i2;
        final boolean z3 = z;
        final float f2 = f;
        final boolean z4 = z2;
        Cocos2dxHelper.runOnGLThread(new Runnable() {
            public void run() {
                GameControllerAdapter.nativeControllerButtonEvent(str2, i3, i4, z3, f2, z4);
            }
        });
    }

    public static void onAxisEvent(String str, int i, int i2, float f, boolean z) {
        final String str2 = str;
        final int i3 = i;
        final int i4 = i2;
        final float f2 = f;
        final boolean z2 = z;
        Cocos2dxHelper.runOnGLThread(new Runnable() {
            public void run() {
                GameControllerAdapter.nativeControllerAxisEvent(str2, i3, i4, f2, z2);
            }
        });
    }
}
