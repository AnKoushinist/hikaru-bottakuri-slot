package jp.reifrontier.silentlogsdkandroid.data.api;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.a.a.c;
import com.google.a.f;
import java.io.IOException;
import java.util.Locale;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import jp.reifrontier.silentlogsdkandroid.util.RFLDateUtils;
import jp.reifrontier.silentlogsdkandroid.util.RxBus;
import org.cocos2dx.lib.BuildConfig;

public class RFLAPIManager {
    private SharedPreferences sharedPreferences;

    public static class RFLExtraKey {
        @c(a = "extra_user_id")
        String extraUserId;

        public RFLExtraKey(String str) {
            this.extraUserId = str;
        }
    }

    public static class RFLPrivateKey {
        @c(a = "last_read_date")
        String last_read_date;
        @c(a = "premium_limit")
        String premium_limit;
        @c(a = "regist_user")
        Boolean regist_user;

        public RFLPrivateKey(String str, Boolean bool, String str2) {
            this.premium_limit = str;
            this.regist_user = bool;
            this.last_read_date = str2;
        }
    }

    public RFLAPIManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(String.format(Locale.JAPAN, "%s%s", new Object[]{RFLConstants.PREF_NAME, context.getPackageName()}), 0);
    }

    public void createAccount(RxBus rxBus, String str, String str2, String str3, String str4, String str5, Boolean bool) {
        String string = this.sharedPreferences.getString(RFLConstants.KEY_FOR_UUID, RFLConstants.kRFLAPI_H1_Parameter_Platform_AND);
        String aPIDateLong = RFLDateUtils.getAPIDateLong(1600527600000L, RFLConstants.kRFLDateTimeFormat_API_Model_DateTime);
        Object rFLPrivateKey = new RFLPrivateKey(aPIDateLong, bool, aPIDateLong);
        Object rFLExtraKey = new RFLExtraKey(str);
        f fVar = new f();
        String a = fVar.a(rFLPrivateKey);
        String a2 = fVar.a(rFLExtraKey);
        String aPIDateLong2 = RFLDateUtils.getAPIDateLong(this.sharedPreferences.getLong(RFLConstants.KEY_FOR_INSTALL_DATE, 0), RFLConstants.kRFLDateTimeFormat_API_Model_DateTime);
        String string2 = this.sharedPreferences.getString(RFLConstants.KEY_FOR_CLIENT_ID, "client_id");
        String string3 = this.sharedPreferences.getString(RFLConstants.KEY_FOR_CLIENT_SECRET, "client_secret");
        boolean z = this.sharedPreferences.getBoolean(RFLConstants.KEY_FOR_DEBUG_URL, false);
        if (!string2.equals("client_id") || !string3.equals("client_secret")) {
            try {
                new RFLAccountCreateCommand(rxBus, string, str2, str3, str4, str5, aPIDateLong2, a, a2, string2, string3, z).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void authAccount(RxBus rxBus) {
        String string = this.sharedPreferences.getString(RFLConstants.KEY_FOR_UUID, RFLConstants.kRFLAPI_H1_Parameter_Platform_AND);
        String string2 = this.sharedPreferences.getString(RFLConstants.KEY_FOR_USER_NAME, RFLConstants.kRFLAPI_H1_Parameter_Platform_AND);
        String string3 = this.sharedPreferences.getString(RFLConstants.KEY_FOR_PASSWORD, RFLConstants.kRFLAPI_H1_Parameter_Platform_AND);
        String string4 = this.sharedPreferences.getString(RFLConstants.KEY_FOR_CLIENT_ID, "client_id");
        String string5 = this.sharedPreferences.getString(RFLConstants.KEY_FOR_CLIENT_SECRET, "client_secret");
        boolean z = this.sharedPreferences.getBoolean(RFLConstants.KEY_FOR_DEBUG_URL, false);
        if (!string4.equals("client_id") || !string5.equals("client_secret")) {
            try {
                new RFLAuthCommand(rxBus, string, string4, string5, string2, string3, z).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getProfile(RxBus rxBus) {
        String string = this.sharedPreferences.getString(RFLConstants.KEY_FOR_ACCESS_TOKEN, BuildConfig.FLAVOR);
        boolean z = this.sharedPreferences.getBoolean(RFLConstants.KEY_FOR_DEBUG_URL, false);
        if (!string.equals(BuildConfig.FLAVOR)) {
            try {
                new RFLGetProfileCommand(rxBus, string, z).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void postDaily(RxBus rxBus, String str, String str2, String str3, String str4) {
        try {
            new RFLPostDailyCommand(rxBus, this.sharedPreferences.getString(RFLConstants.KEY_FOR_ACCESS_TOKEN, "token"), str3, str4, str, str2, this.sharedPreferences.getBoolean(RFLConstants.KEY_FOR_DEBUG_URL, false)).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getEvent(RxBus rxBus) {
        String string = this.sharedPreferences.getString(RFLConstants.KEY_FOR_ACCESS_TOKEN, BuildConfig.FLAVOR);
        boolean z = this.sharedPreferences.getBoolean(RFLConstants.KEY_FOR_DEBUG_URL, false);
        if (!string.equals(BuildConfig.FLAVOR)) {
            try {
                new RFLEventCommand(rxBus, string, z).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void postResponse(RxBus rxBus, String str, Integer num, Integer num2, Integer num3, String str2, Integer num4, Integer num5, float f, float f2, String str3, float f3) {
        String string = this.sharedPreferences.getString(RFLConstants.KEY_FOR_ACCESS_TOKEN, BuildConfig.FLAVOR);
        boolean z = this.sharedPreferences.getBoolean(RFLConstants.KEY_FOR_DEBUG_URL, false);
        if (!string.equals(BuildConfig.FLAVOR)) {
            try {
                new RFLResponseCommand(rxBus, string, str, num, num2, num3, str2, num4, num5, f, f2, str3, f3, z).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
