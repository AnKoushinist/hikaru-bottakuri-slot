package com.adcolony.sdk;

import android.util.Log;
import com.tapjoy.TJAdUnitConstants.String;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class bf {
    static boolean a = false;
    static int b = 3;
    static int c = 1;
    static u d;

    bf() {
    }

    static void a(int i, String str, boolean z) {
        a(str, i);
        if ((!z || a) && b != 4) {
            for (int i2 = 0; i2 <= str.length() / 4000; i2++) {
                int i3 = i2 * 4000;
                int i4 = (i2 + 1) * 4000;
                if (i4 > str.length()) {
                    i4 = str.length();
                }
                if (i == 3 && b >= 3) {
                    Log.d("AdColony [TRACE]", str.substring(i3, i4));
                } else if (i == 2 && b >= 2) {
                    Log.i("AdColony [INFO]", str.substring(i3, i4));
                } else if (i == 1 && b >= 1) {
                    Log.w("AdColony [WARNING]", str.substring(i3, i4));
                } else if (i == 0 && b >= 0) {
                    Log.e("AdColony [ERROR]", str.substring(i3, i4));
                }
            }
        }
    }

    static void a(HashMap<String, Object> hashMap) {
        try {
            d = new u(new bc(new URL("https://wd.adcolony.com/logs")), new ba("adcolony_android", "3.1.2", "Development"), Executors.newSingleThreadScheduledExecutor(), new ArrayList(), hashMap);
            d.a(5, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    static void a(String str, int i) {
        if (d != null && c != 4) {
            if (i == 3 && c >= 3) {
                d.c(str);
            } else if (i == 2 && c >= 2) {
                d.d(str);
            } else if (i == 1 && c >= 1) {
                d.e(str);
            } else if (i == 0 && c >= 0) {
                d.f(str);
            }
        }
    }

    static void a() {
        n.a("Log.set_log_level", new q() {
            public void a(o oVar) {
                bf.b = bb.b(oVar.b(), "level");
            }
        });
        n.a("Log.public.trace", new q() {
            public void a(o oVar) {
                bf.a(3, bb.a(oVar.b(), String.MESSAGE), false);
            }
        });
        n.a("Log.private.trace", new q() {
            public void a(o oVar) {
                bf.a(3, bb.a(oVar.b(), String.MESSAGE), true);
            }
        });
        n.a("Log.public.info", new q() {
            public void a(o oVar) {
                bf.a(2, bb.a(oVar.b(), String.MESSAGE), false);
            }
        });
        n.a("Log.private.info", new q() {
            public void a(o oVar) {
                bf.a(2, bb.a(oVar.b(), String.MESSAGE), true);
            }
        });
        n.a("Log.public.warning", new q() {
            public void a(o oVar) {
                bf.a(1, bb.a(oVar.b(), String.MESSAGE), false);
            }
        });
        n.a("Log.private.warning", new q() {
            public void a(o oVar) {
                bf.a(1, bb.a(oVar.b(), String.MESSAGE), true);
            }
        });
        n.a("Log.public.error", new q() {
            public void a(o oVar) {
                bf.a(0, bb.a(oVar.b(), String.MESSAGE), false);
            }
        });
        n.a("Log.private.error", new q() {
            public void a(o oVar) {
                bf.a(0, bb.a(oVar.b(), String.MESSAGE), true);
            }
        });
    }
}
