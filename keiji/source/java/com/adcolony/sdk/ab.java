package com.adcolony.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.widget.Toast;
import com.tapjoy.TapjoyConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.CRC32;
import jp.co.vaz.creator.hikaru2.R;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONObject;
import twitter4j.TwitterResponse;

class ab {
    static ExecutorService a = Executors.newSingleThreadExecutor();

    static class a {
        double a;
        double b = ((double) System.currentTimeMillis());

        a(double d) {
            a(d);
        }

        void a(double d) {
            this.a = d;
            this.b = (((double) System.currentTimeMillis()) / 1000.0d) + this.a;
        }

        boolean a() {
            return b() == 0.0d;
        }

        double b() {
            double currentTimeMillis = this.b - (((double) System.currentTimeMillis()) / 1000.0d);
            if (currentTimeMillis <= 0.0d) {
                return 0.0d;
            }
            return currentTimeMillis;
        }

        public String toString() {
            return ab.a(b(), 2);
        }
    }

    static boolean a(String str) {
        if (!n.d()) {
            return false;
        }
        try {
            n.c().getApplication().getPackageManager().getApplicationInfo(str, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static String a() {
        if (!n.d()) {
            return BuildConfig.VERSION_NAME;
        }
        try {
            return n.c().getPackageManager().getPackageInfo(n.c().getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            bd.h.b((Object) "Failed to retrieve package info.");
            return BuildConfig.VERSION_NAME;
        }
    }

    static String b() {
        if (!n.d()) {
            return BuildConfig.FLAVOR;
        }
        Activity c = n.c();
        PackageManager packageManager = c.getApplication().getPackageManager();
        try {
            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(c.getPackageName(), 0));
            if (applicationLabel == null) {
                return BuildConfig.FLAVOR;
            }
            return applicationLabel.toString();
        } catch (NameNotFoundException e) {
            bd.h.b((Object) "Failed to retrieve application label.");
            return BuildConfig.FLAVOR;
        }
    }

    static int b(String str) {
        CRC32 crc32 = new CRC32();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            crc32.update(str.charAt(i));
        }
        return (int) crc32.getValue();
    }

    static String c(String str) {
        try {
            return ai.a(str);
        } catch (Exception e) {
            return null;
        }
    }

    static String c() {
        return UUID.randomUUID().toString();
    }

    static JSONArray a(int i) {
        JSONArray b = bb.b();
        for (int i2 = 0; i2 < i; i2++) {
            bb.a(b, c());
        }
        return b;
    }

    static boolean a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        Arrays.sort(strArr);
        Arrays.sort(strArr2);
        return Arrays.equals(strArr, strArr2);
    }

    static boolean a(Runnable runnable) {
        if (!n.d()) {
            return false;
        }
        n.c().runOnUiThread(runnable);
        return true;
    }

    static boolean d(String str) {
        if (str != null && str.length() <= 128) {
            return true;
        }
        bd.e.b((Object) "String must be non-null and the max length is 128 characters.");
        return false;
    }

    static boolean a(AudioManager audioManager) {
        if (audioManager == null) {
            bd.h.b((Object) "isAudioEnabled() called with a null AudioManager");
            return false;
        } else if (audioManager.getStreamVolume(3) > 0) {
            return true;
        } else {
            return false;
        }
    }

    static double b(AudioManager audioManager) {
        if (audioManager == null) {
            bd.h.b((Object) "getAudioVolume() called with a null AudioManager");
            return 0.0d;
        }
        double streamVolume = (double) audioManager.getStreamVolume(3);
        double streamMaxVolume = (double) audioManager.getStreamMaxVolume(3);
        if (streamMaxVolume != 0.0d) {
            return streamVolume / streamMaxVolume;
        }
        return 0.0d;
    }

    static AudioManager a(Context context) {
        if (context != null) {
            return (AudioManager) context.getSystemService("audio");
        }
        bd.h.b((Object) "getAudioManager called with a null Context");
        return null;
    }

    static void e(String str) {
        File[] listFiles = new File(str).listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    bd.b.a(">").b(file.getAbsolutePath());
                    e(file.getAbsolutePath());
                } else {
                    bd.b.b(file.getAbsolutePath());
                }
            }
        }
    }

    static String a(double d, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        a(d, i, stringBuilder);
        return stringBuilder.toString();
    }

    static void a(double d, int i, StringBuilder stringBuilder) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            stringBuilder.append(d);
            return;
        }
        if (d < 0.0d) {
            d = -d;
            stringBuilder.append('-');
        }
        if (i == 0) {
            stringBuilder.append(Math.round(d));
            return;
        }
        long pow = (long) Math.pow(10.0d, (double) i);
        long round = Math.round(((double) pow) * d);
        stringBuilder.append(round / pow);
        stringBuilder.append('.');
        long j = round % pow;
        if (j == 0) {
            for (int i2 = 0; i2 < i; i2++) {
                stringBuilder.append('0');
            }
            return;
        }
        for (round = j * 10; round < pow; round *= 10) {
            stringBuilder.append('0');
        }
        stringBuilder.append(j);
    }

    static String a(Activity activity) {
        try {
            return activity.getPackageName();
        } catch (Exception e) {
            return "unknown";
        }
    }

    static String a(Exception exception) {
        Writer stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    static int f(String str) {
        try {
            return (int) Long.parseLong(str, 16);
        } catch (NumberFormatException e) {
            bd.f.b("Unable to parse '" + str + "' as a color.");
            return -16777216;
        }
    }

    static int b(Activity activity) {
        if (activity == null) {
            return 0;
        }
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        if (identifier > 0) {
            return activity.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    static boolean d() {
        return n.d() && VERSION.SDK_INT >= 24 && n.c().isInMultiWindowMode();
    }

    static boolean a(String str, File file) {
        boolean z = false;
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            try {
                InputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[8192];
                while (true) {
                    try {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        instance.update(bArr, 0, read);
                    } catch (Throwable e) {
                        throw new RuntimeException("Unable to process file for MD5", e);
                    } catch (Throwable th) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            bd.h.b((Object) "Exception on closing MD5 input stream");
                        }
                    }
                }
                String bigInteger = new BigInteger(1, instance.digest()).toString(16);
                z = str.equals(String.format("%40s", new Object[]{bigInteger}).replace(' ', '0'));
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    bd.h.b((Object) "Exception on closing MD5 input stream");
                }
            } catch (FileNotFoundException e4) {
                bd.h.b((Object) "Exception while getting FileInputStream");
            }
        } catch (NoSuchAlgorithmException e5) {
            bd.h.b((Object) "Exception while getting Digest");
        }
        return z;
    }

    static Date g(String str) {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
        DateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        try {
            return simpleDateFormat.parse(str);
        } catch (Exception e) {
            try {
                return simpleDateFormat2.parse(str);
            } catch (Exception e2) {
                try {
                    return new SimpleDateFormat("yyyy-MM-dd").parse(str);
                } catch (Exception e3) {
                    return null;
                }
            }
        }
    }

    static String a(JSONArray jSONArray) {
        String str = BuildConfig.FLAVOR;
        for (int i = 0; i < jSONArray.length(); i++) {
            if (i > 0) {
                str = str + ",";
            }
            switch (jSONArray.getInt(i)) {
                case TwitterResponse.READ /*1*/:
                    str = str + "MO";
                    break;
                case TwitterResponse.READ_WRITE /*2*/:
                    str = str + "TU";
                    break;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    str = str + "WE";
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                    str = str + "TH";
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                    str = str + "FR";
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    str = str + "SA";
                    break;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    str = str + "SU";
                    break;
                default:
                    break;
            }
        }
        return str;
    }

    static String b(JSONArray jSONArray) {
        String str = BuildConfig.FLAVOR;
        int i = 0;
        while (i < jSONArray.length()) {
            if (i > 0) {
                str = str + ",";
            }
            String str2 = str + jSONArray.getInt(i);
            i++;
            str = str2;
        }
        return str;
    }

    static boolean a(Intent intent, boolean z) {
        if (z) {
            try {
                n.c().startActivity(Intent.createChooser(intent, "Handle this via..."));
            } catch (Exception e) {
                bd.f.b(e.toString());
                return false;
            }
        }
        n.c().startActivity(intent);
        return true;
    }

    static boolean a(Intent intent) {
        return a(intent, false);
    }

    static boolean a(final String str, final int i) {
        if (!n.d()) {
            return false;
        }
        a(new Runnable() {
            public void run() {
                Toast.makeText(n.c(), str, i).show();
            }
        });
        return true;
    }

    static int a(x xVar) {
        int i = 1;
        int i2 = 0;
        try {
            if (n.d()) {
                int i3 = (int) (n.c().getPackageManager().getPackageInfo(n.c().getPackageName(), 0).lastUpdateTime / 1000);
                if (!new File(xVar.g() + "AppVersion").exists()) {
                    i2 = 2;
                } else if (bb.b(bb.c(xVar.g() + "AppVersion"), "last_update") != i3) {
                    i2 = 1;
                } else {
                    i = 0;
                }
                if (i != 0) {
                    JSONObject a = bb.a();
                    bb.b(a, "last_update", i3);
                    bb.g(a, xVar.g() + "AppVersion");
                }
            }
        } catch (NameNotFoundException e) {
        }
        return i2;
    }

    static JSONArray b(Context context) {
        JSONArray b = bb.b();
        if (context != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
                if (packageInfo.requestedPermissions != null) {
                    b = bb.b();
                    for (Object put : packageInfo.requestedPermissions) {
                        b.put(put);
                    }
                }
            } catch (Exception e) {
            }
        }
        return b;
    }
}
