package jp.co.mediasdk.mscore.ui.pva;

import android.os.AsyncTask;
import android.util.Xml;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import jp.co.geniee.gnsrewardadapter.GNSAdapterCARewardRewardVideoAd;
import jp.co.mediasdk.android.DateUtil;
import jp.co.mediasdk.android.HandlerManager;
import jp.co.mediasdk.android.HashMapEX;
import jp.co.mediasdk.android.LoggerBase;
import jp.co.mediasdk.android.NetUtil;
import jp.co.mediasdk.android.NetUtil.NetUtilJSONCallback;
import jp.co.mediasdk.android.NetUtil.NetUtilStringCallback;
import jp.co.mediasdk.android.StringUtilEmptySupport;
import jp.co.mediasdk.android.StringUtilEqualsSupport;
import jp.co.mediasdk.android.StringUtilRegexpSupport;
import jp.co.mediasdk.android.TimeStringUtil;
import jp.co.mediasdk.mscore.ui.common.MSParameterSupport;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MSPVAVast {
    private static HashMapEX a;
    private static ArrayList<Integer> b;
    private static HashMapEX c;
    private static ArrayList<String> d;
    private static ArrayList<String> e;

    static class AnonymousClass2 implements NetUtilJSONCallback {
        final /* synthetic */ MSPVANotifyListener a;

        public void a(NetUtil netUtil, HashMapEX hashMapEX) {
            if (HashMapEX.b(hashMapEX) || hashMapEX.d("view_limit")) {
                this.a.a("type=view_limit&status=ng");
                return;
            }
            HashMapEX e = hashMapEX.e("view_limit");
            String str = BuildConfig.FLAVOR;
            String str2 = BuildConfig.FLAVOR;
            if (e.a("limit")) {
                str = e.j("limit");
            }
            if (e.a("reset")) {
                str2 = e.j("reset");
            }
            str = "type=view_limit&status=ok&limit=" + str + "&reset=" + str2;
            str2 = hashMapEX.j("code");
            if (!StringUtilEmptySupport.c(str2)) {
                str = str + "&code=" + str2;
            }
            this.a.a(str);
        }
    }

    public static HashMapEX a() {
        if (a != null) {
            return a;
        }
        a = new HashMapEX();
        return a;
    }

    public static ArrayList<Integer> b() {
        if (b != null) {
            return b;
        }
        b = new ArrayList();
        return b;
    }

    public static ArrayList<String> c() {
        if (d != null) {
            return d;
        }
        d = new ArrayList();
        return d;
    }

    public static ArrayList<String> d() {
        if (e != null) {
            return e;
        }
        e = new ArrayList();
        return e;
    }

    public static HashMapEX e() {
        if (c != null) {
            return c;
        }
        c = new HashMapEX();
        return c;
    }

    private static void c(final MSPVANotifyListener mSPVANotifyListener) {
        HandlerManager.a();
        String str = "https://deliverer.gratefulvideo.jp/ads";
        if (StringUtilEqualsSupport.a("1", MSParameterSupport.a("Debug"))) {
            str = "https://deliverer-pre.gratefulvideo.jp/ads";
            LoggerBase.b("****[MediaSDK]Preview\u74b0\u5883\u3067\u306e\u52d5\u4f5c\u306b\u306a\u308a\u307e\u3059****");
        }
        NetUtil netUtil = new NetUtil();
        netUtil.b(GNSAdapterCARewardRewardVideoAd.MEDIA_ID_COLUMN_NAME, MSParameterSupport.a(GNSAdapterCARewardRewardVideoAd.MEDIA_ID_COLUMN_NAME));
        netUtil.b("media_user_id", MSPVAParamater.a());
        netUtil.b("placement", MSParameterSupport.a("placement"));
        netUtil.b(TapjoyConstants.TJC_SDK_TYPE_DEFAULT, MSParameterSupport.a(TapjoyConstants.TJC_SDK_TYPE_DEFAULT));
        String valueOf = String.valueOf(DateUtil.a());
        netUtil.b(TapjoyConstants.TJC_TIMESTAMP, valueOf);
        netUtil.b(TapjoyConstants.TJC_VERIFIER, MSPVAParamater.a(MSPVAParamater.a(), valueOf));
        netUtil.b(TapjoyConstants.TJC_ADVERTISING_ID, MSPVAParamater.c());
        netUtil.a(str, new NetUtilStringCallback() {
            public void a(NetUtil netUtil, String str) {
                XmlPullParser newPullParser = Xml.newPullParser();
                if (str != null) {
                    try {
                        newPullParser.setInput(new StringReader(str));
                    } catch (XmlPullParserException e) {
                    }
                }
                MSPVAVast.h();
                MSPVAVast.a(str);
                MSPVAVast.j();
                if (!MSPVAVast.a().a("Ad")) {
                    MSPVAVast.g();
                    MSPVAVast.h();
                    if (mSPVANotifyListener != null) {
                        mSPVANotifyListener.a("type=ad_available&status=ng");
                    }
                } else if (!MSPVAVast.a().a("MediaFile") || ((String) MSPVAVast.a().get("MediaFile")).isEmpty()) {
                    MSPVAVast.g();
                } else {
                    MSPVAVast.f();
                    Collections.sort(MSPVAVast.b());
                    if (mSPVANotifyListener == null) {
                        return;
                    }
                    if (MSPVAVast.a().a("Ad")) {
                        mSPVANotifyListener.a("type=ad_available&status=ok");
                    } else {
                        mSPVANotifyListener.a("type=ad_available&status=ng");
                    }
                }
            }
        });
    }

    public static void a(final MSPVANotifyListener mSPVANotifyListener) {
        new AsyncTask<Void, Void, String>() {
            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                a((String) obj);
            }

            protected String a(Void... voidArr) {
                return MSPVAParamater.c();
            }

            protected void a(String str) {
                MSPVAVast.c(mSPVANotifyListener);
            }
        }.execute(new Void[0]);
    }

    public static void f() {
        long a = (long) TimeStringUtil.a(a().j("Duration"));
        JSONArray d = a().e("Tracking").d();
        for (int i = 0; i < d.length(); i++) {
            try {
                JSONObject jSONObject = d.getJSONObject(i);
                String string = jSONObject.getString("text");
                if (StringUtilEqualsSupport.a("progress", jSONObject.getString(TapjoyConstants.TJC_SDK_TYPE_DEFAULT))) {
                    String string2 = jSONObject.getString("offset");
                    if (StringUtilRegexpSupport.b(":", string2)) {
                        int a2 = TimeStringUtil.a(string2);
                        b().add(Integer.valueOf(a2));
                        e().c(String.valueOf(a2), string);
                    } else if (StringUtilRegexpSupport.b("%", string2)) {
                        a(a, Integer.valueOf(string2.replace("%", BuildConfig.FLAVOR)).intValue(), string);
                    }
                } else if (StringUtilEqualsSupport.a(String.VIDEO_FIRST_QUARTILE, jSONObject.getString(TapjoyConstants.TJC_SDK_TYPE_DEFAULT))) {
                    a(a, 25, string);
                } else if (StringUtilEqualsSupport.a(String.VIDEO_MIDPOINT, jSONObject.getString(TapjoyConstants.TJC_SDK_TYPE_DEFAULT))) {
                    a(a, 50, string);
                } else if (StringUtilEqualsSupport.a(String.VIDEO_THIRD_QUARTILE, jSONObject.getString(TapjoyConstants.TJC_SDK_TYPE_DEFAULT))) {
                    a(a, 75, string);
                } else if (StringUtilEqualsSupport.a(String.VIDEO_START, jSONObject.getString(TapjoyConstants.TJC_SDK_TYPE_DEFAULT))) {
                    c().add(string);
                } else if (StringUtilEqualsSupport.a(String.VIDEO_COMPLETE, jSONObject.getString(TapjoyConstants.TJC_SDK_TYPE_DEFAULT))) {
                    d().add(string);
                }
            } catch (JSONException e) {
            }
        }
    }

    public static void a(XmlPullParser xmlPullParser) {
        try {
            int eventType = xmlPullParser.getEventType();
            String str = BuildConfig.FLAVOR;
            String str2 = BuildConfig.FLAVOR;
            str2 = BuildConfig.FLAVOR;
            String attributeValue = xmlPullParser.getAttributeValue(null, TapjoyConstants.TJC_SDK_TYPE_DEFAULT);
            Object attributeValue2 = xmlPullParser.getAttributeValue(null, "offset");
            while (true) {
                if (eventType != 3 || !StringUtilEqualsSupport.a("Creative", str)) {
                    eventType = xmlPullParser.next();
                    String attributeValue3;
                    if (eventType == 2) {
                        str = xmlPullParser.getName().trim();
                        attributeValue = xmlPullParser.getAttributeValue(null, TapjoyConstants.TJC_SDK_TYPE_DEFAULT);
                        attributeValue2 = xmlPullParser.getAttributeValue(null, "offset");
                        if (StringUtilEqualsSupport.a(str, "MediaFile")) {
                            attributeValue3 = xmlPullParser.getAttributeValue(null, "width");
                            String attributeValue4 = xmlPullParser.getAttributeValue(null, "height");
                            if (!(StringUtilEmptySupport.c(attributeValue3) || StringUtilEmptySupport.c(attributeValue4) || Integer.parseInt(attributeValue4) <= Integer.parseInt(attributeValue3))) {
                                a().c("TateFull", "1");
                            }
                        }
                    } else if (eventType == 3) {
                        str = xmlPullParser.getName().trim();
                        attributeValue = BuildConfig.FLAVOR;
                        attributeValue2 = BuildConfig.FLAVOR;
                        if (StringUtilEqualsSupport.a("Creative", str)) {
                            a().c("Creative", "ok");
                        }
                    } else if (eventType == 4 && !a().a("Creative")) {
                        attributeValue3 = xmlPullParser.getText().trim();
                        if (StringUtilEqualsSupport.a("Duration", str)) {
                            a().c("Duration", attributeValue3);
                        } else if (StringUtilEqualsSupport.a("MediaFile", str)) {
                            if (!a().a("MediaFile")) {
                                a().c("MediaFile", attributeValue3);
                            }
                        } else if (StringUtilEqualsSupport.a("Tracking", str)) {
                            if (!a().a("Tracking")) {
                                a().a("Tracking", new JSONArray());
                            }
                            JSONObject jSONObject;
                            JSONArray d;
                            if (StringUtilEqualsSupport.a("progress", attributeValue)) {
                                jSONObject = new JSONObject();
                                jSONObject.put(TapjoyConstants.TJC_SDK_TYPE_DEFAULT, attributeValue);
                                jSONObject.put("offset", attributeValue2);
                                jSONObject.put("text", attributeValue3);
                                d = a().e("Tracking").d();
                                d.put(jSONObject);
                                a().a("Tracking", d);
                            } else {
                                jSONObject = new JSONObject();
                                jSONObject.put(TapjoyConstants.TJC_SDK_TYPE_DEFAULT, attributeValue);
                                jSONObject.put("text", attributeValue3);
                                d = a().e("Tracking").d();
                                d.put(jSONObject);
                                a().a("Tracking", d);
                            }
                        } else if (StringUtilEqualsSupport.a("ClickThrough", str)) {
                            a().c("ClickThrough", attributeValue3);
                        } else if (StringUtilEqualsSupport.a("ClickTracking", str)) {
                            a().c("ClickTracking", attributeValue3);
                        }
                    }
                } else {
                    return;
                }
            }
        } catch (Exception e) {
        }
    }

    public static void b(XmlPullParser xmlPullParser) {
        try {
            int eventType = xmlPullParser.getEventType();
            String str = BuildConfig.FLAVOR;
            while (true) {
                if (eventType != 3 || !StringUtilEqualsSupport.a("CurationFormat", str)) {
                    eventType = xmlPullParser.next();
                    if (eventType == 2) {
                        str = xmlPullParser.getName().trim();
                    } else if (eventType == 3) {
                        str = xmlPullParser.getName().trim();
                    } else if (eventType == 4) {
                        String trim = xmlPullParser.getText().trim();
                        if (StringUtilEqualsSupport.a("ID", str)) {
                            a().c("CurationFormat", trim);
                        }
                    }
                } else {
                    return;
                }
            }
        } catch (Exception e) {
        }
    }

    public static void c(XmlPullParser xmlPullParser) {
        try {
            int eventType = xmlPullParser.getEventType();
            String str = BuildConfig.FLAVOR;
            while (true) {
                if (eventType != 3 || !StringUtilEqualsSupport.a("Format", str)) {
                    eventType = xmlPullParser.next();
                    if (eventType == 2) {
                        str = xmlPullParser.getName().trim();
                        if (StringUtilEqualsSupport.a("ClickButton", str)) {
                            e(xmlPullParser);
                        } else if (StringUtilEqualsSupport.a("CloseButton", str)) {
                            f(xmlPullParser);
                        } else if (StringUtilEqualsSupport.a("SubContents", str)) {
                            g(xmlPullParser);
                        }
                    } else if (eventType == 3) {
                        str = xmlPullParser.getName().trim();
                    } else if (eventType == 4) {
                        String trim = xmlPullParser.getText().trim();
                        if (StringUtilEqualsSupport.a("Orientation", str)) {
                            a().c("Orientation", trim);
                        } else if (StringUtilEqualsSupport.a("ShowRepeat", str)) {
                            a().c("ShowRepeat", trim);
                        }
                    }
                } else {
                    return;
                }
            }
        } catch (Exception e) {
        }
    }

    private static void e(XmlPullParser xmlPullParser) {
        try {
            int eventType = xmlPullParser.getEventType();
            String str = BuildConfig.FLAVOR;
            while (true) {
                if (eventType != 3 || !StringUtilEqualsSupport.a("ClickButton", str)) {
                    eventType = xmlPullParser.next();
                    if (eventType == 2) {
                        str = xmlPullParser.getName().trim();
                    } else if (eventType == 3) {
                        str = xmlPullParser.getName().trim();
                    } else if (eventType == 4) {
                        String trim = xmlPullParser.getText().trim();
                        if (StringUtilEqualsSupport.a("Image", str)) {
                            a().c("ClickButtonImage", trim);
                        } else if (StringUtilEqualsSupport.a("Text", str)) {
                            a().c("ClickButtonText", trim);
                        }
                    }
                } else {
                    return;
                }
            }
        } catch (Exception e) {
        }
    }

    private static void f(XmlPullParser xmlPullParser) {
        try {
            int eventType = xmlPullParser.getEventType();
            String str = BuildConfig.FLAVOR;
            String str2 = BuildConfig.FLAVOR;
            while (true) {
                if (eventType != 3 || !StringUtilEqualsSupport.a("CloseButton", str)) {
                    eventType = xmlPullParser.next();
                    if (eventType == 2) {
                        str = xmlPullParser.getName().trim();
                        if (StringUtilEqualsSupport.a("Position", str)) {
                            str2 = xmlPullParser.getAttributeValue(null, "scene");
                        }
                    } else if (eventType == 3) {
                        str = xmlPullParser.getName().trim();
                    } else if (eventType == 4) {
                        String trim = xmlPullParser.getText().trim();
                        if (StringUtilEqualsSupport.a("Image", str)) {
                            a().c("CloseButtonImage", trim);
                        } else if (StringUtilEqualsSupport.a("Position", str)) {
                            if (StringUtilEqualsSupport.a(String.VIDEO_PLAYING, str2)) {
                                a().c("CloseButtonPosition", trim);
                            } else {
                                a().c("VideoEndCloseButtonPosition", trim);
                            }
                        } else if (StringUtilEqualsSupport.a("ShowPlaying", str)) {
                            a().c("CloseButtonShowPlaying", trim);
                        } else if (StringUtilEqualsSupport.a("Confirm", str)) {
                            a().c("CloseButtonConfirm", trim);
                        }
                    }
                } else {
                    return;
                }
            }
        } catch (Exception e) {
        }
    }

    private static void g(XmlPullParser xmlPullParser) {
        try {
            int eventType = xmlPullParser.getEventType();
            String str = BuildConfig.FLAVOR;
            String attributeValue = xmlPullParser.getAttributeValue(null, "scene");
            while (true) {
                if (eventType != 3 || !StringUtilEqualsSupport.a("SubContents", str)) {
                    eventType = xmlPullParser.next();
                    if (eventType == 2) {
                        str = xmlPullParser.getName().trim();
                    } else if (eventType == 3) {
                        str = xmlPullParser.getName().trim();
                    } else if (eventType == 4) {
                        String trim = xmlPullParser.getText().trim();
                        if (StringUtilEqualsSupport.a("SubContent", str)) {
                            if (StringUtilEqualsSupport.a(String.VIDEO_PLAYING, attributeValue)) {
                                if (StringUtilEqualsSupport.a("video", trim)) {
                                    a().c("playingVideo", "1");
                                } else if (StringUtilEqualsSupport.a("curation", trim)) {
                                    a().c("playingCuration", "1");
                                }
                            } else if (StringUtilEqualsSupport.a(String.VIDEO_COMPLETE, attributeValue)) {
                                if (StringUtilEqualsSupport.a("video", trim)) {
                                    a().c("completeVideo", "1");
                                } else if (StringUtilEqualsSupport.a("curation", trim)) {
                                    a().c("completeCuration", "1");
                                }
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        } catch (Exception e) {
        }
    }

    public static void d(XmlPullParser xmlPullParser) {
        try {
            int eventType = xmlPullParser.getEventType();
            String str = BuildConfig.FLAVOR;
            String str2 = BuildConfig.FLAVOR;
            str2 = BuildConfig.FLAVOR;
            xmlPullParser.getAttributeValue(null, TapjoyConstants.TJC_SDK_TYPE_DEFAULT);
            xmlPullParser.getAttributeValue(null, "offset");
            while (true) {
                if (eventType != 3 || !StringUtilEqualsSupport.a("Incentive", str)) {
                    eventType = xmlPullParser.next();
                    if (eventType == 2) {
                        str = xmlPullParser.getName().trim();
                        xmlPullParser.getAttributeValue(null, TapjoyConstants.TJC_SDK_TYPE_DEFAULT);
                        xmlPullParser.getAttributeValue(null, "offset");
                    } else if (eventType == 3) {
                        str = xmlPullParser.getName().trim();
                        str2 = BuildConfig.FLAVOR;
                        str2 = BuildConfig.FLAVOR;
                    } else if (eventType == 4) {
                        str2 = xmlPullParser.getText().trim();
                        if (StringUtilEqualsSupport.a("ID", str)) {
                            a().c("IncentiveId", str2);
                        } else if (StringUtilEqualsSupport.a("Amount", str)) {
                            a().c("IncentiveAmount", str2);
                        }
                    }
                } else {
                    return;
                }
            }
        } catch (Exception e) {
        }
    }

    public static void a(String str) {
        if (str != null && !str.isEmpty()) {
            XmlPullParser newPullParser = Xml.newPullParser();
            try {
                newPullParser.setInput(new StringReader(str));
            } catch (XmlPullParserException e) {
            }
            try {
                int eventType = newPullParser.getEventType();
                String str2 = BuildConfig.FLAVOR;
                String str3 = BuildConfig.FLAVOR;
                str3 = BuildConfig.FLAVOR;
                while (eventType != 1) {
                    if (!(eventType == 0 || eventType == 1)) {
                        if (eventType == 2) {
                            str2 = newPullParser.getName().trim();
                            newPullParser.getAttributeValue(null, TapjoyConstants.TJC_SDK_TYPE_DEFAULT);
                            newPullParser.getAttributeValue(null, "offset");
                            if (StringUtilEqualsSupport.a(str2, "Ad")) {
                                a().c("Ad", "ok");
                                a().c("Cid", newPullParser.getAttributeValue(null, "id"));
                            } else if (StringUtilEqualsSupport.a("Creative", str2)) {
                                a(newPullParser);
                            } else if (StringUtilEqualsSupport.a("Incentive", str2)) {
                                d(newPullParser);
                            } else if (StringUtilEqualsSupport.a("CurationFormat", str2)) {
                                b(newPullParser);
                            } else if (StringUtilEqualsSupport.a("Format", str2)) {
                                c(newPullParser);
                            } else if (StringUtilEqualsSupport.a("Impression", str2)) {
                                a().c("SessionId", newPullParser.getAttributeValue(null, "id"));
                            }
                        } else if (eventType == 3) {
                            str2 = BuildConfig.FLAVOR;
                            r1 = BuildConfig.FLAVOR;
                            r1 = BuildConfig.FLAVOR;
                        } else if (eventType == 4) {
                            r1 = newPullParser.getText().trim();
                            if (StringUtilEqualsSupport.a("Impression", str2)) {
                                a().c("Impression", r1);
                            } else if (StringUtilEqualsSupport.a("AdTitle", str2)) {
                                a().c("AdTitle", r1);
                            } else if (StringUtilEqualsSupport.a("Error", str2)) {
                                a().c("Error", r1);
                            } else if (StringUtilEqualsSupport.a("Icon", str2)) {
                                a().c("Icon", r1);
                            } else if (StringUtilEqualsSupport.a("Description", str2)) {
                                a().c("Description", r1);
                            }
                        }
                    }
                    eventType = newPullParser.next();
                }
            } catch (Exception e2) {
            }
        }
    }

    private static void j() {
        String j = a().j("playingVideo");
        String j2 = a().j("playingCuration");
        String j3 = a().j("completeVideo");
        String j4 = a().j("completeCuration");
        if (StringUtilEqualsSupport.a("1", j) && StringUtilEqualsSupport.a("1", j2) && StringUtilEqualsSupport.a("1", j3) && StringUtilEqualsSupport.a("1", j4)) {
            MSParameterSupport.a(MoatAdEvent.EVENT_TYPE, "webview");
        } else if (!StringUtilEqualsSupport.a("1", j) || StringUtilEqualsSupport.a("1", j2)) {
            MSParameterSupport.a(MoatAdEvent.EVENT_TYPE, "vc_c");
        } else {
            MSParameterSupport.a(MoatAdEvent.EVENT_TYPE, "video_only");
        }
    }

    public static void a(long j, int i, String str) {
        int i2 = (int) (((double) j) * (((double) i) / 100.0d));
        b().add(Integer.valueOf(i2));
        e().c(String.valueOf(i2), str);
    }

    public static void g() {
        String j = a().j("Error");
        if (j != null && !StringUtilEmptySupport.c(j)) {
            HandlerManager.a();
            new NetUtil().a(j, new NetUtilJSONCallback() {
                public void a(NetUtil netUtil, HashMapEX hashMapEX) {
                }
            });
        }
    }

    public static void h() {
        if (a != null) {
            a.clear();
            a = null;
        }
        if (b != null) {
            b.clear();
            b = null;
        }
        if (c != null) {
            c.clear();
            c = null;
        }
        if (d != null) {
            d.clear();
            d = null;
        }
        if (e != null) {
            e.clear();
            e = null;
        }
    }
}
