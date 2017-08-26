package com.igaworks.adbrix.util;

import android.content.Context;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.cocos2dx.lib.BuildConfig;

public class ABLanguage {
    private static ABLanguage instance;
    private Map<String, String> lang = new HashMap();

    private ABLanguage(Context context) {
        setMessageByLocale();
    }

    public static ABLanguage getInstance(Context context) {
        if (instance == null) {
            instance = new ABLanguage(context);
        }
        return instance;
    }

    public void setMessageByLocale() {
        Locale locale = Locale.getDefault();
        String language = locale.getLanguage();
        if (language.contains("ko")) {
            this.lang.put("shareWith", "\uacf5\uc720");
            this.lang.put(String.CLOSE, "\ub2eb\uae30");
            this.lang.put("canNotParticipate", "\uc8c4\uc1a1\ud569\ub2c8\ub2e4.\n\uc7a0\uc2dc \ud6c4 \ub2e4\uc2dc \uc2dc\ub3c4\ud574\uc8fc\uc138\uc694.");
        } else if (language.contains(Constants.LOCALE_JA)) {
            this.lang.put("shareWith", "\u30b7\u30a7\u30a2");
            this.lang.put(String.CLOSE, "\u9589\u3058\u308b");
            this.lang.put("canNotParticipate", "Sorry.\nPlease try again later.");
        } else if (language.contains("zh")) {
            String toLowerCase = locale.getCountry().toLowerCase();
            if (toLowerCase.equals("cn")) {
                this.lang.put("shareWith", "\u5171\u4eab");
                this.lang.put(String.CLOSE, "\u5173\u95ed");
                this.lang.put("canNotParticipate", "Sorry.\nPlease try again later.");
            } else if (toLowerCase.equals("tw")) {
                this.lang.put("shareWith", "\u5171\u4eab");
                this.lang.put(String.CLOSE, "\u95dc\u9589");
                this.lang.put("canNotParticipate", "Sorry.\nPlease try again later.");
            }
        } else {
            this.lang.put("shareWith", "Share");
            this.lang.put(String.CLOSE, "Close");
            this.lang.put("canNotParticipate", "Sorry.\nPlease try again later.");
        }
    }

    public String getMessageByLocale(String str) {
        if (this.lang.containsKey(str)) {
            return (String) this.lang.get(str);
        }
        return BuildConfig.FLAVOR;
    }
}
