package com.tapjoy.internal;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyUtil;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;

public final class eo {
    private static final eo a = new eo();
    private Application b;
    private ActivityLifecycleCallbacks c;

    public static void a(Context context) {
        if (VERSION.SDK_INT >= 14 && context != null) {
            eo eoVar = a;
            Context applicationContext = context.getApplicationContext();
            if (eoVar.b == null) {
                try {
                    if (applicationContext instanceof Application) {
                        eoVar.b = (Application) applicationContext;
                    } else {
                        final CountDownLatch countDownLatch = new CountDownLatch(1);
                        TapjoyUtil.runOnMainThread(new Runnable(eoVar) {
                            final /* synthetic */ eo b;

                            public final void run() {
                                try {
                                    this.b.b = ((Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, null));
                                } catch (Throwable e) {
                                    TapjoyLog.w("Tapjoy.ActivityLifecycleTracker", Log.getStackTraceString(e));
                                } finally {
                                    countDownLatch.countDown();
                                }
                            }
                        });
                        countDownLatch.await();
                    }
                } catch (Throwable e) {
                    TapjoyLog.w("Tapjoy.ActivityLifecycleTracker", Log.getStackTraceString(e));
                }
                if (eoVar.b == null) {
                    return;
                }
            }
            synchronized (eoVar) {
                if (eoVar.c == null) {
                    eoVar.c = new ActivityLifecycleCallbacks(eoVar) {
                        final /* synthetic */ eo a;
                        private final HashSet b = new HashSet();

                        {
                            this.a = r2;
                        }

                        public final void onActivityCreated(Activity activity, Bundle bundle) {
                        }

                        public final void onActivityStarted(Activity activity) {
                            this.b.add(AnonymousClass2.a(activity));
                            if (this.b.size() == 1) {
                                fn.a();
                            }
                            d.a(activity);
                        }

                        public final void onActivityResumed(Activity activity) {
                        }

                        public final void onActivityPaused(Activity activity) {
                        }

                        public final void onActivityStopped(Activity activity) {
                            this.b.remove(AnonymousClass2.a(activity));
                            if (this.b.size() <= 0) {
                                fn.b();
                            }
                        }

                        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                        }

                        public final void onActivityDestroyed(Activity activity) {
                        }

                        private static String a(Activity activity) {
                            return activity.getClass().getName() + "@" + System.identityHashCode(activity);
                        }
                    };
                    eoVar.b.registerActivityLifecycleCallbacks(eoVar.c);
                    fn.a();
                }
            }
        }
    }

    public static void a() {
        if (VERSION.SDK_INT >= 14) {
            eo eoVar = a;
            if (eoVar.b != null) {
                synchronized (eoVar) {
                    if (eoVar.c != null) {
                        eoVar.b.unregisterActivityLifecycleCallbacks(eoVar.c);
                        eoVar.c = null;
                    }
                }
            }
        }
    }
}
