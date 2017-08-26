package com.adcolony.sdk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Environment;
import android.os.Vibrator;
import android.view.View;
import com.tapjoy.TJAdUnitConstants.String;
import com.unity3d.ads.metadata.MediationMetaData;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;

class z {
    z() {
    }

    void a() {
        n.a("System.open_store", new q(this) {
            final /* synthetic */ z a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.a(oVar);
            }
        });
        n.a("System.save_screenshot", new q(this) {
            final /* synthetic */ z a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.b(oVar);
            }
        });
        n.a("System.telephone", new q(this) {
            final /* synthetic */ z a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.c(oVar);
            }
        });
        n.a("System.sms", new q(this) {
            final /* synthetic */ z a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.d(oVar);
            }
        });
        n.a("System.vibrate", new q(this) {
            final /* synthetic */ z a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.e(oVar);
            }
        });
        n.a("System.open_browser", new q(this) {
            final /* synthetic */ z a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.f(oVar);
            }
        });
        n.a("System.mail", new q(this) {
            final /* synthetic */ z a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.g(oVar);
            }
        });
        n.a("System.launch_app", new q(this) {
            final /* synthetic */ z a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.h(oVar);
            }
        });
        n.a("System.create_calendar_event", new q(this) {
            final /* synthetic */ z a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.i(oVar);
            }
        });
        n.a("System.check_app_presence", new q(this) {
            final /* synthetic */ z a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.j(oVar);
            }
        });
        n.a("System.check_social_presence", new q(this) {
            final /* synthetic */ z a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.k(oVar);
            }
        });
        n.a("System.social_post", new q(this) {
            final /* synthetic */ z a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.l(oVar);
            }
        });
        n.a("System.make_in_app_purchase", new q(this) {
            final /* synthetic */ z a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                this.a.m(oVar);
            }
        });
    }

    private boolean m(o oVar) {
        JSONObject b = oVar.b();
        am h = n.a().h();
        String a = bb.a(b, "ad_session_id");
        g gVar = (g) h.c().get(a);
        ae aeVar = (ae) h.d().get(a);
        if ((gVar == null || gVar.b() == null || gVar.e() == null) && (aeVar == null || aeVar.getListener() == null || aeVar.getExpandedContainer() == null)) {
            return false;
        }
        if (aeVar == null) {
            new o("AdUnit.make_in_app_purchase", gVar.e().b()).a();
        } else {
            new o("AdUnit.make_in_app_purchase", aeVar.getExpandedContainer().b()).a();
        }
        b(bb.a(b, "ad_session_id"));
        return true;
    }

    boolean a(o oVar) {
        JSONObject a = bb.a();
        JSONObject b = oVar.b();
        String a2 = bb.a(b, "product_id");
        if (a2.equals(BuildConfig.FLAVOR)) {
            a2 = bb.a(b, "handle");
        }
        if (ab.a(new Intent("android.intent.action.VIEW", Uri.parse(a2)))) {
            bb.a(a, "success", true);
            oVar.a(a).a();
            a(bb.a(b, "ad_session_id"));
            b(bb.a(b, "ad_session_id"));
            return true;
        }
        ab.a("Unable to open.", 0);
        bb.a(a, "success", false);
        oVar.a(a).a();
        return false;
    }

    boolean b(final o oVar) {
        if (!n.d()) {
            return false;
        }
        b(bb.a(oVar.b(), "ad_session_id"));
        final JSONObject a = bb.a();
        String str = Environment.getExternalStorageDirectory().toString() + "/Pictures/AdColony_Screenshots/AdColony_Screenshot_" + System.currentTimeMillis() + ".jpg";
        View rootView = n.c().getWindow().getDecorView().getRootView();
        rootView.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        try {
            File file = new File(Environment.getExternalStorageDirectory().toString() + "/Pictures");
            File file2 = new File(Environment.getExternalStorageDirectory().toString() + "/Pictures/AdColony_Screenshots");
            file.mkdir();
            file2.mkdir();
        } catch (Exception e) {
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(str));
            createBitmap.compress(CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            MediaScannerConnection.scanFile(n.c(), new String[]{str}, null, new OnScanCompletedListener(this) {
                final /* synthetic */ z c;

                public void onScanCompleted(String str, Uri uri) {
                    ab.a("Screenshot saved to Gallery!", 0);
                    bb.a(a, "success", true);
                    oVar.a(a).a();
                }
            });
            return true;
        } catch (FileNotFoundException e2) {
            ab.a("Error saving screenshot.", 0);
            bb.a(a, "success", false);
            oVar.a(a).a();
            return false;
        } catch (IOException e3) {
            ab.a("Error saving screenshot.", 0);
            bb.a(a, "success", false);
            oVar.a(a).a();
            return false;
        }
    }

    boolean c(o oVar) {
        JSONObject a = bb.a();
        JSONObject b = oVar.b();
        if (ab.a(new Intent("android.intent.action.DIAL").setData(Uri.parse("tel:" + bb.a(b, "phone_number"))))) {
            bb.a(a, "success", true);
            oVar.a(a).a();
            a(bb.a(b, "ad_session_id"));
            b(bb.a(b, "ad_session_id"));
            return true;
        }
        ab.a("Failed to dial number.", 0);
        bb.a(a, "success", false);
        oVar.a(a).a();
        return false;
    }

    boolean d(o oVar) {
        JSONObject b = oVar.b();
        JSONObject a = bb.a();
        JSONArray f = bb.f(b, "recipients");
        String str = BuildConfig.FLAVOR;
        int i = 0;
        while (i < f.length()) {
            if (i != 0) {
                str = str + ";";
            }
            String str2 = str + bb.b(f, i);
            i++;
            str = str2;
        }
        if (ab.a(new Intent("android.intent.action.VIEW", Uri.parse("smsto:" + str)).putExtra("sms_body", bb.a(b, "body")))) {
            bb.a(a, "success", true);
            oVar.a(a).a();
            a(bb.a(b, "ad_session_id"));
            b(bb.a(b, "ad_session_id"));
            return true;
        }
        ab.a("Failed to create sms.", 0);
        bb.a(a, "success", false);
        oVar.a(a).a();
        return false;
    }

    boolean e(o oVar) {
        if (!n.d()) {
            return false;
        }
        int a = bb.a(oVar.b(), "length_ms", (int) HttpResponseCode.INTERNAL_SERVER_ERROR);
        JSONObject a2 = bb.a();
        JSONArray v = n.a().i().v();
        boolean z = false;
        for (int i = 0; i < v.length(); i++) {
            if (bb.b(v, i).equals("android.permission.VIBRATE")) {
                z = true;
            }
        }
        if (z) {
            try {
                ((Vibrator) n.c().getSystemService("vibrator")).vibrate((long) a);
                bb.a(a2, "success", false);
                oVar.a(a2).a();
                return true;
            } catch (Exception e) {
                bd.e.b((Object) "Vibrate command failed.");
                bb.a(a2, "success", false);
                oVar.a(a2).a();
                return false;
            }
        }
        bd.e.b((Object) "No vibrate permission detected.");
        bb.a(a2, "success", false);
        oVar.a(a2).a();
        return false;
    }

    boolean f(o oVar) {
        JSONObject a = bb.a();
        JSONObject b = oVar.b();
        String a2 = bb.a(b, String.URL);
        if (a2.startsWith("browser")) {
            a2 = a2.replaceFirst("browser", "http");
        }
        if (a2.startsWith("safari")) {
            a2 = a2.replaceFirst("safari", "http");
        }
        if (ab.a(new Intent("android.intent.action.VIEW", Uri.parse(a2)))) {
            bb.a(a, "success", true);
            oVar.a(a).a();
            a(bb.a(b, "ad_session_id"));
            b(bb.a(b, "ad_session_id"));
            return true;
        }
        ab.a("Failed to launch browser.", 0);
        bb.a(a, "success", false);
        oVar.a(a).a();
        return false;
    }

    boolean g(o oVar) {
        JSONObject a = bb.a();
        JSONObject b = oVar.b();
        JSONArray f = bb.f(b, "recipients");
        boolean c = bb.c(b, String.HTML);
        String a2 = bb.a(b, "subject");
        String a3 = bb.a(b, "body");
        String[] strArr = new String[f.length()];
        for (int i = 0; i < f.length(); i++) {
            strArr[i] = bb.b(f, i);
        }
        Intent intent = new Intent("android.intent.action.SEND");
        if (!c) {
            intent.setType("plain/text");
        }
        intent.putExtra("android.intent.extra.SUBJECT", a2).putExtra("android.intent.extra.TEXT", a3).putExtra("android.intent.extra.EMAIL", strArr);
        if (ab.a(intent)) {
            bb.a(a, "success", true);
            oVar.a(a).a();
            a(bb.a(b, "ad_session_id"));
            b(bb.a(b, "ad_session_id"));
            return true;
        }
        ab.a("Failed to send email.", 0);
        bb.a(a, "success", false);
        oVar.a(a).a();
        return false;
    }

    boolean h(o oVar) {
        JSONObject a = bb.a();
        JSONObject b = oVar.b();
        if (bb.c(b, "deep_link")) {
            return a(oVar);
        }
        if (ab.a(n.c().getPackageManager().getLaunchIntentForPackage(bb.a(b, "handle")))) {
            bb.a(a, "success", true);
            oVar.a(a).a();
            a(bb.a(b, "ad_session_id"));
            b(bb.a(b, "ad_session_id"));
            return true;
        }
        ab.a("Failed to launch external application.", 0);
        bb.a(a, "success", false);
        oVar.a(a).a();
        return false;
    }

    boolean i(o oVar) {
        JSONObject a = bb.a();
        JSONObject b = oVar.b();
        String str = BuildConfig.FLAVOR;
        String str2 = BuildConfig.FLAVOR;
        JSONObject e = bb.e(b, "params");
        JSONObject e2 = bb.e(e, "recurrence");
        JSONArray b2 = bb.b();
        JSONArray b3 = bb.b();
        JSONArray b4 = bb.b();
        String a2 = bb.a(e, "description");
        bb.a(e, "location");
        String a3 = bb.a(e, String.VIDEO_START);
        String a4 = bb.a(e, "end");
        String a5 = bb.a(e, "summary");
        if (e2 != null && e2.length() > 0) {
            str2 = bb.a(e2, "expires");
            str = bb.a(e2, "frequency");
            b2 = bb.f(e2, "daysInWeek");
            b3 = bb.f(e2, "daysInMonth");
            b4 = bb.f(e2, "daysInYear");
        }
        if (a5.equals(BuildConfig.FLAVOR)) {
            a5 = a2;
        }
        Date g = ab.g(a3);
        Date g2 = ab.g(a4);
        Date g3 = ab.g(str2);
        if (g == null || g2 == null) {
            ab.a("Unable to create Calendar Event", 0);
            bb.a(a, "success", false);
            oVar.a(a).a();
            return false;
        }
        Intent putExtra;
        long time = g.getTime();
        long time2 = g2.getTime();
        long j = 0;
        if (g3 != null) {
            j = (g3.getTime() - g.getTime()) / 1000;
        }
        if (str.equals("DAILY")) {
            j = (j / 86400) + 1;
        } else if (str.equals("WEEKLY")) {
            j = (j / 604800) + 1;
        } else if (str.equals("MONTHLY")) {
            j = (j / 2629800) + 1;
        } else if (str.equals("YEARLY")) {
            j = (j / 31557600) + 1;
        } else {
            j = 0;
        }
        if (e2 == null || e2.length() <= 0) {
            putExtra = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra(String.TITLE, a5).putExtra("description", a2).putExtra("beginTime", time).putExtra("endTime", time2);
        } else {
            String str3;
            str2 = "FREQ=" + str + ";COUNT=" + j;
            try {
                String str4;
                if (b2.length() != 0) {
                    str4 = str2 + ";BYDAY=" + ab.a(b2);
                } else {
                    str4 = str2;
                }
                try {
                    String str5;
                    if (b3.length() != 0) {
                        str5 = str4 + ";BYMONTHDAY=" + ab.b(b3);
                    } else {
                        str5 = str4;
                    }
                    try {
                        if (b4.length() != 0) {
                            str3 = str5 + ";BYYEARDAY=" + ab.b(b4);
                        } else {
                            str3 = str5;
                        }
                    } catch (JSONException e3) {
                        str3 = str5;
                    }
                } catch (JSONException e4) {
                    str3 = str4;
                }
            } catch (JSONException e5) {
                str3 = str2;
            }
            putExtra = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra(String.TITLE, a5).putExtra("description", a2).putExtra("beginTime", time).putExtra("endTime", time2).putExtra("rrule", str3);
        }
        if (ab.a(putExtra)) {
            bb.a(a, "success", true);
            oVar.a(a).a();
            a(bb.a(b, "ad_session_id"));
            b(bb.a(b, "ad_session_id"));
            return true;
        }
        ab.a("Unable to create Calendar Event.", 0);
        bb.a(a, "success", false);
        oVar.a(a).a();
        return false;
    }

    boolean j(o oVar) {
        JSONObject a = bb.a();
        String a2 = bb.a(oVar.b(), MediationMetaData.KEY_NAME);
        boolean a3 = ab.a(a2);
        bb.a(a, "success", true);
        bb.a(a, "result", a3);
        bb.a(a, MediationMetaData.KEY_NAME, a2);
        bb.a(a, "service", a2);
        oVar.a(a).a();
        return true;
    }

    boolean k(o oVar) {
        return j(oVar);
    }

    boolean l(o oVar) {
        JSONObject a = bb.a();
        JSONObject b = oVar.b();
        if (ab.a(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", bb.a(b, "text") + " " + bb.a(b, String.URL)), true)) {
            bb.a(a, "success", true);
            oVar.a(a).a();
            a(bb.a(b, "ad_session_id"));
            b(bb.a(b, "ad_session_id"));
            return true;
        }
        ab.a("Unable to create social post.", 0);
        bb.a(a, "success", false);
        oVar.a(a).a();
        return false;
    }

    void a(String str) {
        am h = n.a().h();
        g gVar = (g) h.c().get(str);
        if (gVar == null || gVar.b() == null) {
            ae aeVar = (ae) h.d().get(str);
            an listener = aeVar != null ? aeVar.getListener() : null;
            if (aeVar != null && listener != null && (listener instanceof j)) {
                ((j) listener).d((i) aeVar);
                return;
            }
            return;
        }
        gVar.b().onLeftApplication(gVar);
    }

    void b(String str) {
        am h = n.a().h();
        g gVar = (g) h.c().get(str);
        if (gVar == null || gVar.b() == null) {
            ae aeVar = (ae) h.d().get(str);
            an listener = aeVar != null ? aeVar.getListener() : null;
            if (aeVar != null && listener != null && (listener instanceof j)) {
                ((j) listener).i((i) aeVar);
                return;
            }
            return;
        }
        gVar.b().onClicked(gVar);
    }
}
