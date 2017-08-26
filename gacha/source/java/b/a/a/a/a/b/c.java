package b.a.a.a.a.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import b.a.a.a.a.f.d;
import com.tapjoy.TapjoyConstants;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: AdvertisingInfoProvider */
class c {
    private final Context a;
    private final b.a.a.a.a.f.c b;

    public c(Context context) {
        this.a = context.getApplicationContext();
        this.b = new d(context, "TwitterAdvertisingInfoPreferences");
    }

    public b a() {
        b b = b();
        if (c(b)) {
            b.a.a.a.c.h().a("Fabric", "Using AdvertisingInfo from Preference Store");
            a(b);
            return b;
        }
        b = e();
        b(b);
        return b;
    }

    private void a(final b bVar) {
        new Thread(new h(this) {
            final /* synthetic */ c b;

            public void a() {
                b a = this.b.e();
                if (!bVar.equals(a)) {
                    b.a.a.a.c.h().a("Fabric", "Asychronously getting Advertising Info and storing it to preferences");
                    this.b.b(a);
                }
            }
        }).start();
    }

    @SuppressLint({"CommitPrefEdits"})
    private void b(b bVar) {
        if (c(bVar)) {
            this.b.a(this.b.b().putString(TapjoyConstants.TJC_ADVERTISING_ID, bVar.a).putBoolean("limit_ad_tracking_enabled", bVar.b));
        } else {
            this.b.a(this.b.b().remove(TapjoyConstants.TJC_ADVERTISING_ID).remove("limit_ad_tracking_enabled"));
        }
    }

    protected b b() {
        return new b(this.b.a().getString(TapjoyConstants.TJC_ADVERTISING_ID, BuildConfig.FLAVOR), this.b.a().getBoolean("limit_ad_tracking_enabled", false));
    }

    public f c() {
        return new d(this.a);
    }

    public f d() {
        return new e(this.a);
    }

    private boolean c(b bVar) {
        return (bVar == null || TextUtils.isEmpty(bVar.a)) ? false : true;
    }

    private b e() {
        b a = c().a();
        if (c(a)) {
            b.a.a.a.c.h().a("Fabric", "Using AdvertisingInfo from Reflection Provider");
        } else {
            a = d().a();
            if (c(a)) {
                b.a.a.a.c.h().a("Fabric", "Using AdvertisingInfo from Service Provider");
            } else {
                b.a.a.a.c.h().a("Fabric", "AdvertisingInfo not present");
            }
        }
        return a;
    }
}
