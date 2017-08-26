package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.File;

public final class w {
    private static String a = "Android";
    private static String b = String.DATA;

    public static File a(Context context) {
        try {
            File externalFilesDir = context.getExternalFilesDir(null);
            int i = 0;
            while (externalFilesDir != null && i < 2) {
                i++;
                externalFilesDir = externalFilesDir.getParentFile();
            }
            return externalFilesDir;
        } catch (RuntimeException e) {
            return null;
        }
    }
}
