package b.a.a.a.a.b;

import android.content.Context;
import b.a.a.a.a.a.b;
import b.a.a.a.a.a.d;
import b.a.a.a.c;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: InstallerPackageNameProvider */
public class p {
    private final d<String> a = new d<String>(this) {
        final /* synthetic */ p a;

        {
            this.a = r1;
        }

        public /* synthetic */ Object c(Context context) {
            return a(context);
        }

        public String a(Context context) {
            String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            return installerPackageName == null ? BuildConfig.FLAVOR : installerPackageName;
        }
    };
    private final b<String> b = new b();

    public String a(Context context) {
        try {
            String str = (String) this.b.a(context, this.a);
            if (BuildConfig.FLAVOR.equals(str)) {
                return null;
            }
            return str;
        } catch (Throwable e) {
            c.h().e("Fabric", "Failed to determine installer package name", e);
            return null;
        }
    }
}
