package com.tapjoy;

import com.tapjoy.internal.bd;
import com.tapjoy.internal.ey;
import com.tapjoy.internal.ez;
import com.tapjoy.internal.fa;
import com.tapjoy.internal.fb;
import com.tapjoy.internal.fm;
import com.tapjoy.internal.fn;
import java.util.Hashtable;

public class FiveRocksIntegration {
    private static bd a = new bd();

    public static void a(Hashtable hashtable) {
        if (hashtable != null) {
            String valueOf = String.valueOf(hashtable.get(TapjoyConnectFlag.ENABLE_LOGGING));
            if (valueOf != null && valueOf.equalsIgnoreCase(TapjoyConstants.TJC_TRUE)) {
                fa.a(true);
            }
        }
        fm a = fm.a();
        if (!a.c) {
            a.c = true;
        }
        fb anonymousClass1 = new fb() {
            public final void a(String str) {
            }

            public final void b(String str) {
                synchronized (FiveRocksIntegration.a) {
                    TJPlacement tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                }
                if (tJPlacement != null && tJPlacement.c != null) {
                    tJPlacement.c.onContentReady(tJPlacement);
                }
            }

            public final void c(String str) {
                synchronized (FiveRocksIntegration.a) {
                    TJPlacement tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                }
                if (tJPlacement != null && tJPlacement.c != null) {
                    tJPlacement.c.onContentShow(tJPlacement);
                }
            }

            public final void d(String str) {
            }

            public final void a(String str, ey eyVar) {
                if (eyVar != null) {
                    eyVar.a(e(str));
                }
            }

            public final void b(String str, ey eyVar) {
                if (eyVar != null) {
                    eyVar.a(e(str));
                }
                synchronized (FiveRocksIntegration.a) {
                    TJPlacement tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                }
                if (tJPlacement != null) {
                    TapjoyConnectCore.viewDidClose(tJPlacement.getGUID());
                    if (tJPlacement.c != null) {
                        tJPlacement.c.onContentDismiss(tJPlacement);
                    }
                }
            }

            private ez e(final String str) {
                return new ez(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public final void a(final String str, String str2) {
                        synchronized (FiveRocksIntegration.a) {
                            TJPlacement tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                        }
                        if (tJPlacement != null && tJPlacement.c != null) {
                            tJPlacement.c.onPurchaseRequest(tJPlacement, new TJActionRequest(this) {
                                final /* synthetic */ AnonymousClass1 b;

                                public final String getRequestId() {
                                    return str;
                                }

                                public final String getToken() {
                                    return null;
                                }

                                public final void completed() {
                                }

                                public final void cancelled() {
                                }
                            }, str2);
                        }
                    }

                    public final void a(final String str, String str2, int i, final String str3) {
                        synchronized (FiveRocksIntegration.a) {
                            TJPlacement tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                        }
                        if (tJPlacement != null && tJPlacement.c != null) {
                            tJPlacement.c.onRewardRequest(tJPlacement, new TJActionRequest(this) {
                                final /* synthetic */ AnonymousClass1 c;

                                public final String getRequestId() {
                                    return str;
                                }

                                public final String getToken() {
                                    return str3;
                                }

                                public final void completed() {
                                }

                                public final void cancelled() {
                                }
                            }, str2, i);
                        }
                    }
                };
            }
        };
        fm.a().p = fn.a(anonymousClass1);
    }

    public static void addPlacementCallback(String str, TJPlacement tJPlacement) {
        synchronized (a) {
            a.put(str, tJPlacement);
        }
    }
}
