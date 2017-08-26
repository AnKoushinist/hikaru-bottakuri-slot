package b.a.a.a.a.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import b.a.a.a.c;
import b.a.a.a.i;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TapjoyConstants;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;
import jp.co.vaz.creator.hikaru.R;
import org.cocos2dx.lib.BuildConfig;
import twitter4j.MediaEntity.Size;

/* compiled from: IdManager */
public class o {
    private static final Pattern d = Pattern.compile("[^\\p{Alnum}]");
    private static final String e = Pattern.quote(Operation.DIVISION);
    c a;
    b b;
    boolean c;
    private final ReentrantLock f = new ReentrantLock();
    private final p g;
    private final boolean h;
    private final boolean i;
    private final Context j;
    private final String k;
    private final String l;
    private final Collection<i> m;

    /* compiled from: IdManager */
    public enum a {
        WIFI_MAC_ADDRESS(1),
        BLUETOOTH_MAC_ADDRESS(2),
        FONT_TOKEN(53),
        ANDROID_ID(100),
        ANDROID_DEVICE_ID(Size.CROP),
        ANDROID_SERIAL(R.styleable.AppCompatTheme_buttonStyle),
        ANDROID_ADVERTISING_ID(R.styleable.AppCompatTheme_buttonStyleSmall);
        
        public final int h;

        private a(int i) {
            this.h = i;
        }
    }

    public o(Context context, String str, String str2, Collection<i> collection) {
        if (context == null) {
            throw new IllegalArgumentException("appContext must not be null");
        } else if (str == null) {
            throw new IllegalArgumentException("appIdentifier must not be null");
        } else if (collection == null) {
            throw new IllegalArgumentException("kits must not be null");
        } else {
            this.j = context;
            this.k = str;
            this.l = str2;
            this.m = collection;
            this.g = new p();
            this.a = new c(context);
            this.h = i.a(context, "com.crashlytics.CollectDeviceIdentifiers", true);
            if (!this.h) {
                c.h().a("Fabric", "Device ID collection disabled for " + context.getPackageName());
            }
            this.i = i.a(context, "com.crashlytics.CollectUserIdentifiers", true);
            if (!this.i) {
                c.h().a("Fabric", "User information collection disabled for " + context.getPackageName());
            }
        }
    }

    public boolean a() {
        return this.i;
    }

    private String a(String str) {
        return str == null ? null : d.matcher(str).replaceAll(BuildConfig.FLAVOR).toLowerCase(Locale.US);
    }

    public String b() {
        String str = this.l;
        if (str != null) {
            return str;
        }
        SharedPreferences a = i.a(this.j);
        str = a.getString("crashlytics.installation.id", null);
        if (str == null) {
            return a(a);
        }
        return str;
    }

    public String c() {
        return this.k;
    }

    public String d() {
        return e() + Operation.DIVISION + f();
    }

    public String e() {
        return b(VERSION.RELEASE);
    }

    public String f() {
        return b(VERSION.INCREMENTAL);
    }

    public String g() {
        return String.format(Locale.US, "%s/%s", new Object[]{b(Build.MANUFACTURER), b(Build.MODEL)});
    }

    private String b(String str) {
        return str.replaceAll(e, BuildConfig.FLAVOR);
    }

    public String h() {
        String str = BuildConfig.FLAVOR;
        if (!this.h) {
            return str;
        }
        str = n();
        if (str != null) {
            return str;
        }
        SharedPreferences a = i.a(this.j);
        str = a.getString("crashlytics.installation.id", null);
        if (str == null) {
            return a(a);
        }
        return str;
    }

    private String a(SharedPreferences sharedPreferences) {
        this.f.lock();
        try {
            String string = sharedPreferences.getString("crashlytics.installation.id", null);
            if (string == null) {
                string = a(UUID.randomUUID().toString());
                sharedPreferences.edit().putString("crashlytics.installation.id", string).commit();
            }
            this.f.unlock();
            return string;
        } catch (Throwable th) {
            this.f.unlock();
        }
    }

    public Map<a, String> i() {
        Map hashMap = new HashMap();
        for (i iVar : this.m) {
            if (iVar instanceof m) {
                for (Entry entry : ((m) iVar).f().entrySet()) {
                    a(hashMap, (a) entry.getKey(), (String) entry.getValue());
                }
            }
        }
        a(hashMap, a.ANDROID_ID, n());
        a(hashMap, a.ANDROID_ADVERTISING_ID, m());
        return Collections.unmodifiableMap(hashMap);
    }

    public String j() {
        return this.g.a(this.j);
    }

    synchronized b k() {
        if (!this.c) {
            this.b = this.a.a();
            this.c = true;
        }
        return this.b;
    }

    public Boolean l() {
        if (!this.h) {
            return null;
        }
        b k = k();
        if (k != null) {
            return Boolean.valueOf(k.b);
        }
        return null;
    }

    public String m() {
        if (!this.h) {
            return null;
        }
        b k = k();
        if (k != null) {
            return k.a;
        }
        return null;
    }

    private void a(Map<a, String> map, a aVar, String str) {
        if (str != null) {
            map.put(aVar, str);
        }
    }

    public String n() {
        if (!this.h) {
            return null;
        }
        String string = Secure.getString(this.j.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
        if ("9774d56d682e549c".equals(string)) {
            return null;
        }
        return a(string);
    }
}
