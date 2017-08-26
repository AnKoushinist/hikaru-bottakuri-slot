package com.igaworks.core;

import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.igaworks.core.AdvertisingIdClient.ADIDCallbackListener;
import com.igaworks.core.AdvertisingIdClient.AdInfo;
import com.igaworks.dao.CoreIDDAO;
import com.tapjoy.TapjoyConstants;
import java.math.BigInteger;
import java.security.MessageDigest;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.MediaEntity.Size;

public class DeviceIDManger {
    private static DeviceIDManger singleton;
    public final int MD5_TYPE = 100;
    public final int SHA1_TYPE = Size.CROP;
    private AdInfo adidInfo;
    private Context context;

    private DeviceIDManger(Context context) {
        this.context = context;
    }

    public static DeviceIDManger getInstance(Context context) {
        if (singleton == null) {
            singleton = new DeviceIDManger(context);
        }
        return singleton;
    }

    public AdInfo getAndroidADID(Context context, ADIDCallbackListener aDIDCallbackListener) {
        try {
            AdInfo advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context, aDIDCallbackListener);
            if (advertisingIdInfo != null) {
                this.adidInfo = advertisingIdInfo;
            }
        } catch (Exception e) {
            if (e != null) {
                IgawLogger.Logging(context, "IGAW_QA", "getAndroidADID error : " + e.toString(), 3, true);
            }
        }
        return this.adidInfo;
    }

    public String getAESPuid(Context context) {
        String imei = CoreIDDAO.getInstance().getIMEI(context);
        String str = BuildConfig.FLAVOR;
        if (imei.equals(BuildConfig.FLAVOR)) {
            return str;
        }
        try {
            return AESGetPuid.encrypt(imei);
        } catch (Exception e) {
            IgawLogger.Logging(this.context, "IGAW_QA", "get AES puid ERROR : ", 0);
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public static String getAndroidId(Context context) {
        try {
            return Secure.getString(context.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
        } catch (Exception e) {
            Exception exception = e;
            String str = "unKnown";
            exception.printStackTrace();
            return str;
        }
    }

    public static String getMd5Value(String str) {
        try {
            String bigInteger = new BigInteger(1, MessageDigest.getInstance("MD5").digest(str.getBytes())).toString(16);
            while (bigInteger.length() < 32) {
                bigInteger = "0" + bigInteger;
            }
            return bigInteger;
        } catch (Exception e) {
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public String getOpenUDID() {
        String str = BuildConfig.FLAVOR;
        try {
            return OpenUDID_manager.getOpenUDID();
        } catch (Exception e) {
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public String getSha1Value(String str) {
        String str2 = BuildConfig.FLAVOR;
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toString((b & 255) + 256, 16).substring(1));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public String getAndroidId(Context context, int i) {
        String str = BuildConfig.FLAVOR;
        this.context = context;
        try {
            str = Secure.getString(this.context.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
            if (i == 100) {
                return getMd5Value(str);
            }
            if (i == Size.CROP) {
                return getSha1Value(str);
            }
            return str;
        } catch (Exception e) {
            Exception exception = e;
            str = BuildConfig.FLAVOR;
            exception.printStackTrace();
            return str;
        }
    }

    public String getDeviceID(Context context, int i) {
        String imei;
        String str = BuildConfig.FLAVOR;
        this.context = context;
        try {
            if (((TelephonyManager) this.context.getSystemService("phone")) == null || CoreIDDAO.getInstance().getIMEI(context).equals(BuildConfig.FLAVOR)) {
                return null;
            }
            imei = CoreIDDAO.getInstance().getIMEI(context);
            if (i == 100) {
                return getMd5Value(imei);
            }
            if (i == Size.CROP) {
                return getSha1Value(imei);
            }
            return str;
        } catch (Exception e) {
            Exception exception = e;
            imei = BuildConfig.FLAVOR;
            exception.printStackTrace();
            return imei;
        }
    }

    public String getODIN1(Context context) {
        String str = BuildConfig.FLAVOR;
        try {
            return getSha1Value(Secure.getString(context.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID));
        } catch (Exception e) {
            Exception exception = e;
            str = BuildConfig.FLAVOR;
            exception.printStackTrace();
            return str;
        }
    }

    public String getOPENUDID(String str, int i) {
        String str2 = BuildConfig.FLAVOR;
        if (i == 100) {
            return getMd5Value(str);
        }
        if (i == Size.CROP) {
            return getSha1Value(str);
        }
        return str2;
    }
}
