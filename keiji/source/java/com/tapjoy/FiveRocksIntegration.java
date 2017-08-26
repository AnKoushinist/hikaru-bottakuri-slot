package com.tapjoy;

import com.tapjoy.internal.be;
import com.tapjoy.internal.fl;
import com.tapjoy.internal.fm;
import com.tapjoy.internal.fn;
import com.tapjoy.internal.fo;
import com.tapjoy.internal.fz;
import com.tapjoy.internal.ga;
import java.util.Hashtable;

public class FiveRocksIntegration {
    private static be a = new be();

    public static void a(Hashtable hashtable) {
        if (hashtable != null) {
            String valueOf = String.valueOf(hashtable.get(TapjoyConnectFlag.ENABLE_LOGGING));
            if (valueOf != null && valueOf.equalsIgnoreCase(TapjoyConstants.TJC_TRUE)) {
                fn.a(true);
            }
        }
        fz a = fz.a();
        if (!a.c) {
            a.c = true;
        }
        fo anonymousClass1 = new fo() {
            public final void a(String str) {
            }

            public final void b(String str) {
                synchronized (FiveRocksIntegration.a) {
                    TJPlacement tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                }
                if (tJPlacement != null && tJPlacement.a != null) {
                    tJPlacement.a.onContentReady(tJPlacement);
                }
            }

            public final void c(String str) {
                synchronized (FiveRocksIntegration.a) {
                    TJPlacement tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                }
                if (tJPlacement != null && tJPlacement.a != null) {
                    tJPlacement.a.onContentShow(tJPlacement);
                }
            }

            public final void d(String str) {
            }

            public final void a(String str, fl flVar) {
                if (flVar != null) {
                    flVar.a(e(str));
                }
            }

            public final void a(String str, String str2, fl flVar) {
                if (flVar != null) {
                    flVar.a(e(str));
                }
                synchronized (FiveRocksIntegration.a) {
                    TJPlacement tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                }
                if (tJPlacement != null) {
                    TapjoyConnectCore.viewDidClose(str2);
                    if (tJPlacement.a != null) {
                        tJPlacement.a.onContentDismiss(tJPlacement);
                    }
                }
            }

            private fm e(final String str) {
                return new fm(this) {
                    final /* synthetic */ AnonymousClass1 b;

                    public final void a(final String str, String str2) {
                        synchronized (FiveRocksIntegration.a) {
                            TJPlacement tJPlacement = (TJPlacement) FiveRocksIntegration.a.get(str);
                        }
                        if (tJPlacement != null && tJPlacement.a != null) {
                            tJPlacement.a.onPurchaseRequest(tJPlacement, new TJActionRequest(this) {
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
                        if (tJPlacement != null && tJPlacement.a != null) {
                            tJPlacement.a.onRewardRequest(tJPlacement, new TJActionRequest(this) {
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
        fz.a().p = ga.a(anonymousClass1);
    }

    public static void addPlacementCallback(String str, TJPlacement tJPlacement) {
        synchronized (a) {
            a.put(str, tJPlacement);
        }
    }
}
