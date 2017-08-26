package a.a.a.a.a.b;

import a.a.a.a.c;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

/* compiled from: ApiKey */
public class g {
    public String a(Context context) {
        Object b = b(context);
        if (TextUtils.isEmpty(b)) {
            b = c(context);
        }
        if (TextUtils.isEmpty(b)) {
            d(context);
        }
        return b;
    }

    protected String b(Context context) {
        String str = null;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                str = bundle.getString("io.fabric.ApiKey");
                if (str == null) {
                    c.h().a("Fabric", "Falling back to Crashlytics key lookup from Manifest");
                    str = bundle.getString("com.crashlytics.ApiKey");
                }
            }
        } catch (Exception e) {
            c.h().a("Fabric", "Caught non-fatal exception while retrieving apiKey: " + e);
        }
        return str;
    }

    protected String c(Context context) {
        int a = i.a(context, "io.fabric.ApiKey", "string");
        if (a == 0) {
            c.h().a("Fabric", "Falling back to Crashlytics key lookup from Strings");
            a = i.a(context, "com.crashlytics.ApiKey", "string");
        }
        if (a != 0) {
            return context.getResources().getString(a);
        }
        return null;
    }

    protected void d(Context context) {
        if (c.i() || i.i(context)) {
            throw new IllegalArgumentException(a());
        }
        c.h().e("Fabric", a());
    }

    protected String a() {
        return "Fabric could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"io.fabric.ApiKey\" android:value=\"YOUR_API_KEY\"/>";
    }
}
