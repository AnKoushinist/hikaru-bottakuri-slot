package com.igaworks.adbrix.cpe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import com.igaworks.core.RequestParameter;
import com.igaworks.dao.AbstractCPEImpressionDAO;
import com.igaworks.dao.CPEImpressionDAOFactory;
import com.igaworks.dao.CounterDAOForAllActivity;
import com.tapjoy.TapjoyConstants;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import org.cocos2dx.lib.BuildConfig;

public class ConditionChecker {
    public static boolean isMatch(Context context, String str, Object obj, Object obj2, boolean z) {
        if (obj == null || obj2 == null) {
            return false;
        }
        if (z) {
            try {
                if (str.equals("contains")) {
                    return checkInstalled(context, (String) obj);
                }
                if (!str.equals("not_contains")) {
                    return false;
                }
                return !checkInstalled(context, (String) obj);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else if (str.equals("equal")) {
            try {
                if (obj2 instanceof String) {
                    r8 = Long.parseLong((String) obj2);
                } else if (obj2 instanceof Double) {
                    r8 = ((Double) obj2).longValue();
                } else if (obj2 instanceof Integer) {
                    r8 = ((Integer) obj2).longValue();
                } else {
                    r8 = ((Long) obj2).longValue();
                }
                if (obj instanceof String) {
                    r6 = Long.parseLong((String) obj);
                } else if (obj instanceof Double) {
                    r6 = ((Double) obj).longValue();
                } else if (obj instanceof Integer) {
                    r6 = ((Integer) obj).longValue();
                } else {
                    r6 = ((Long) obj).longValue();
                }
                if (r8 == r6) {
                    return true;
                }
                return false;
            } catch (Exception e2) {
                if ((obj2 instanceof Comparable) && (obj instanceof Comparable)) {
                    return ((Comparable) obj2).equals((Comparable) obj);
                }
                return false;
            }
        } else if (str.equals("contains")) {
            if ((obj2 instanceof Collection) && (obj instanceof Collection)) {
                return ((Collection) obj).containsAll((Collection) obj2);
            }
            return false;
        } else if (str.equals("not_equal")) {
            try {
                if (obj2 instanceof String) {
                    r8 = Long.parseLong((String) obj2);
                } else if (obj2 instanceof Double) {
                    r8 = ((Double) obj2).longValue();
                } else if (obj2 instanceof Integer) {
                    r8 = ((Integer) obj2).longValue();
                } else {
                    r8 = ((Long) obj2).longValue();
                }
                if (obj instanceof String) {
                    r6 = Long.parseLong((String) obj);
                } else if (obj instanceof Double) {
                    r6 = ((Double) obj).longValue();
                } else if (obj2 instanceof Integer) {
                    r6 = ((Integer) obj).longValue();
                } else {
                    r6 = ((Long) obj).longValue();
                }
                if (r8 != r6) {
                    return true;
                }
                return false;
            } catch (Exception e3) {
                if ((obj2 instanceof Comparable) && (obj instanceof Comparable) && !((Comparable) obj2).equals((Comparable) obj)) {
                    return true;
                }
                return false;
            }
        } else if (str.equals("not_contains")) {
            if ((obj2 instanceof Collection) && (obj instanceof Collection)) {
                return ((Collection) obj).containsAll((Collection) obj2);
            }
            return false;
        } else if (str.equals("prefix")) {
            if ((obj2 instanceof String) && (obj instanceof String)) {
                return ((String) obj2).toLowerCase().startsWith(((String) obj).toLowerCase());
            }
            return false;
        } else if (str.equals("postfix")) {
            if ((obj2 instanceof String) && (obj instanceof String)) {
                return ((String) obj2).toLowerCase().endsWith(((String) obj).toLowerCase());
            }
            return false;
        } else if (str.equals("infix")) {
            if ((obj2 instanceof String) && (obj instanceof String)) {
                return ((String) obj2).toLowerCase().contains(((String) obj).toLowerCase());
            }
            return false;
        } else if (str.equals("not_prefix")) {
            if ((obj2 instanceof String) && (obj instanceof String) && !((String) obj2).toLowerCase().contains(((String) obj).toLowerCase())) {
                return true;
            }
            return false;
        } else if (str.equals("not_postfix")) {
            if ((obj2 instanceof String) && (obj instanceof String)) {
                return ((String) obj2).toLowerCase().contains(((String) obj).toLowerCase());
            }
            return false;
        } else if (str.equals("not_infix")) {
            if ((obj2 instanceof String) && (obj instanceof String)) {
                return ((String) obj2).toLowerCase().contains(((String) obj).toLowerCase());
            }
            return false;
        } else if (str.equals("greater")) {
            try {
                if (obj2 instanceof String) {
                    r8 = Long.parseLong((String) obj2);
                } else if (obj2 instanceof Double) {
                    r8 = ((Double) obj2).longValue();
                } else if (obj2 instanceof Integer) {
                    r8 = ((Integer) obj2).longValue();
                } else {
                    r8 = ((Long) obj2).longValue();
                }
                if (obj instanceof String) {
                    r6 = Long.parseLong((String) obj);
                } else if (obj instanceof Double) {
                    r6 = ((Double) obj).longValue();
                } else if (obj instanceof Integer) {
                    r6 = ((Integer) obj).longValue();
                } else {
                    r6 = ((Long) obj).longValue();
                }
                if (r8 > r6) {
                    return true;
                }
                return false;
            } catch (Exception e4) {
                if ((obj2 instanceof Comparable) && (obj instanceof Comparable) && ((Comparable) obj2).compareTo((Comparable) obj) > 0) {
                    return true;
                }
                return false;
            }
        } else if (str.equals("less")) {
            try {
                if (obj2 instanceof String) {
                    r8 = Long.parseLong((String) obj2);
                } else if (obj2 instanceof Double) {
                    r8 = ((Double) obj2).longValue();
                } else if (obj2 instanceof Integer) {
                    r8 = ((Integer) obj2).longValue();
                } else {
                    r8 = ((Long) obj2).longValue();
                }
                if (obj instanceof String) {
                    r6 = Long.parseLong((String) obj);
                } else if (obj instanceof Double) {
                    r6 = ((Double) obj).longValue();
                } else if (obj instanceof Integer) {
                    r6 = ((Integer) obj).longValue();
                } else {
                    r6 = ((Long) obj).longValue();
                }
                if (r8 < r6) {
                    return true;
                }
                return false;
            } catch (Exception e5) {
                if ((obj2 instanceof Comparable) && (obj instanceof Comparable) && ((Comparable) obj2).compareTo((Comparable) obj) < 0) {
                    return true;
                }
                return false;
            }
        } else if (!str.equals("has")) {
            return false;
        } else {
            try {
                if (obj2 instanceof String) {
                    r8 = Long.parseLong((String) obj2);
                } else if (obj2 instanceof Double) {
                    r8 = ((Double) obj2).longValue();
                } else if (obj2 instanceof Integer) {
                    r8 = ((Integer) obj2).longValue();
                } else {
                    r8 = ((Long) obj2).longValue();
                }
                if (obj instanceof String) {
                    r6 = Long.parseLong((String) obj);
                } else if (obj instanceof Double) {
                    r6 = ((Double) obj).longValue();
                } else if (obj2 instanceof Integer) {
                    r6 = ((Integer) obj).longValue();
                } else {
                    r6 = ((Long) obj).longValue();
                }
                if ((r6 & r8) == r8) {
                    return true;
                }
                return false;
            } catch (Exception e6) {
                if ((obj2 instanceof Comparable) && (obj instanceof Comparable) && !((Comparable) obj2).equals((Comparable) obj)) {
                    return true;
                }
                return false;
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static Object getUserValue(Context context, RequestParameter requestParameter, int i, String str, String str2, String str3) {
        try {
            if (str2.equals(TapjoyConstants.TJC_NOTIFICATION_DEVICE_PREFIX)) {
                if (str3.equals("vendor")) {
                    return requestParameter.getMarketPlace();
                }
                if (str3.equals("model")) {
                    return requestParameter.getModel();
                }
                if (str3.equals("network")) {
                    return requestParameter.getCustomNetworkInfo(context);
                }
                if (str3.equals("os")) {
                    return "a_" + VERSION.RELEASE;
                }
                if (str3.equals("ptype")) {
                    return RFLConstants.kRFLAPI_H1_Parameter_Platform_AND;
                }
                Display defaultDisplay;
                Point point;
                if (str3.equals("width")) {
                    defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                    if (VERSION.SDK_INT < 13) {
                        return Integer.valueOf(defaultDisplay.getWidth());
                    }
                    point = new Point();
                    defaultDisplay.getSize(point);
                    return Integer.valueOf(point.x);
                } else if (!str3.equals("height")) {
                    return str3.equals("is_portrait") ? Integer.valueOf(context.getResources().getConfiguration().orientation) : null;
                } else {
                    defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
                    if (VERSION.SDK_INT < 13) {
                        return Integer.valueOf(defaultDisplay.getHeight());
                    }
                    point = new Point();
                    defaultDisplay.getSize(point);
                    return Integer.valueOf(point.y);
                }
            } else if (str2.equals("user")) {
                if (str3.equals(Constants.PREFKEY_CARR)) {
                    return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
                }
                if (str3.equals("country")) {
                    return Locale.getDefault().getCountry();
                }
                return str3.equals("language") ? Locale.getDefault().getLanguage() : null;
            } else if (str2.equals("adbrix")) {
                if (str3.equals("life_hour")) {
                    return Long.valueOf(requestParameter.calculateLifeHour());
                }
                if (str3.equals("app_launch_count")) {
                    return Long.valueOf(requestParameter.getappLaunchCount());
                }
                if (str3.equals("channel_type")) {
                    return Integer.valueOf(requestParameter.getChannelType());
                }
                return str3.equals("conversion_key") ? requestParameter.getConversionCache() : null;
            } else if (str2.equals("impression")) {
                AbstractCPEImpressionDAO impressionDAO = CPEImpressionDAOFactory.getImpressionDAO(str2, str3, i);
                if (impressionDAO == null) {
                    return Integer.valueOf(0);
                }
                try {
                    if (str3.equals("total_count")) {
                        return Integer.valueOf(Integer.parseInt(impressionDAO.getImpressionData(context, i, str, str3)) + 1);
                    }
                    if (str3.equals("session_count")) {
                        return Integer.valueOf(Integer.parseInt(impressionDAO.getImpressionData(context, i, str, str3)) + 1);
                    }
                    if (!str3.equals("last_imp_minute")) {
                        return Integer.valueOf(0);
                    }
                    return new StringBuilder(String.valueOf((((new Date().getTime() - Long.parseLong(impressionDAO.getImpressionData(context, i, str, str3))) / 1000) / 60) + 1)).toString();
                } catch (Exception e) {
                    return Integer.valueOf(0);
                }
            } else if (str2.equals("activity_count")) {
                String[] split = str3.split("::--::");
                if (split.length != 2) {
                    return null;
                }
                String str4 = split[0];
                String str5 = split[1];
                if (str4 == null && str5 == null) {
                    return null;
                }
                return Integer.valueOf(CounterDAOForAllActivity.getDAO(context).getCountInAllActivityByGroupAndActivity(str4, str5));
            } else if (str2.equals("group_count")) {
                return Integer.valueOf(CounterDAOForAllActivity.getDAO(context).getCountInAllActivityByGroup(str3));
            } else {
                if (!str2.equals("app")) {
                    return null;
                }
                try {
                    return str3.equals("package") ? BuildConfig.FLAVOR : null;
                } catch (Exception e2) {
                    return null;
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static boolean checkInstalled(Context context, String str) {
        try {
            context.getPackageManager().getApplicationInfo(str, 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
