package com.tapjoy.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.internal.fj.a;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public final class ez {
    private static final ez b;
    private static ez c;
    public final ex a = new ex();
    private Context d;

    static {
        ez ezVar = new ez();
        b = ezVar;
        c = ezVar;
    }

    public static ez a() {
        return c;
    }

    public static ex b() {
        return c.a;
    }

    ez() {
    }

    public final synchronized void a(Context context) {
        bs b;
        if (context != null) {
            if (this.d == null) {
                this.d = context;
                SharedPreferences c = c();
                String string = c().getString(TapjoyConstants.PREF_SERVER_PROVIDED_CONFIGURATIONS, null);
                if (string != null) {
                    try {
                        b = bs.b(string);
                        Map d = b.d();
                        b.close();
                        this.a.a(d);
                    } catch (Exception e) {
                        c.edit().remove(TapjoyConstants.PREF_SERVER_PROVIDED_CONFIGURATIONS).commit();
                    } catch (Throwable th) {
                        b.close();
                    }
                }
                Observer anonymousClass1 = new Observer(this) {
                    final /* synthetic */ ez a;

                    {
                        this.a = r1;
                    }

                    public final void update(Observable observable, Object obj) {
                        Object a;
                        ff.a(this.a.a.a("usage_tracking_enabled", false));
                        String str = "usage_tracking_exclude";
                        Class cls = List.class;
                        for (a a2 : this.a.a.b) {
                            a = a2.a(str);
                            if (a != null && cls.isInstance(a)) {
                                a = cls.cast(a);
                                break;
                            }
                        }
                        a = null;
                        ff.a((Collection) a);
                    }
                };
                this.a.addObserver(anonymousClass1);
                anonymousClass1.update(this.a, null);
            }
        }
    }

    public final SharedPreferences c() {
        return this.d.getSharedPreferences(TapjoyConstants.TJC_PREFERENCE, 0);
    }
}
