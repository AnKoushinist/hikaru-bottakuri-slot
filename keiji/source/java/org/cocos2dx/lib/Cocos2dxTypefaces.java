package org.cocos2dx.lib;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;

public class Cocos2dxTypefaces {
    private static final HashMap<String, Typeface> sTypefaceCache = new HashMap();

    public static synchronized Typeface get(Context context, String str) {
        Typeface typeface;
        synchronized (Cocos2dxTypefaces.class) {
            if (!sTypefaceCache.containsKey(str)) {
                Object createFromFile;
                if (str.startsWith("/")) {
                    createFromFile = Typeface.createFromFile(str);
                } else {
                    createFromFile = Typeface.createFromAsset(context.getAssets(), str);
                }
                sTypefaceCache.put(str, createFromFile);
            }
            typeface = (Typeface) sTypefaceCache.get(str);
        }
        return typeface;
    }
}
