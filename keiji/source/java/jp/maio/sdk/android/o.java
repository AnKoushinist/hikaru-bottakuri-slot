package jp.maio.sdk.android;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.d.a.a.c;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;

class o extends WebViewClient {
    final /* synthetic */ l a;

    o(l lVar) {
        this.a = lVar;
    }

    public void onPageFinished(WebView webView, String str) {
        if (str.startsWith("data:text/html")) {
            this.a.h.countDown();
        }
        super.onPageFinished(webView, str);
        this.a.a = true;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String str2 = "unknown";
        str2 = BuildConfig.FLAVOR;
        Object obj = null;
        str2 = BuildConfig.FLAVOR;
        bc.a("Api call args string.", "call url:" + str, null);
        String str3 = "native://";
        if (!str.startsWith("native://")) {
            return false;
        }
        Uri parse = Uri.parse(str);
        String host = parse.getHost();
        try {
            Object obj2;
            String string;
            int i;
            JSONObject jSONObject;
            String h;
            String encode;
            Object obj3;
            Object obj4;
            byte[] bArr;
            ByteArrayOutputStream byteArrayOutputStream;
            InputStream fileInputStream;
            int read;
            String str4;
            MediaMetadataRetriever mediaMetadataRetriever;
            Bitmap bitmap;
            OutputStream byteArrayOutputStream2;
            String encodeToString;
            Boolean valueOf;
            JSONObject jSONObject2;
            JSONObject jSONObject3;
            Object lastPathSegment = parse.getLastPathSegment();
            JSONObject jSONObject4 = TextUtils.isEmpty(lastPathSegment) ? null : new JSONObject(lastPathSegment);
            if (host.equals("log")) {
                if (jSONObject4.getString(String.MESSAGE).contains("close-ad.click")) {
                    obj2 = 1;
                    if (host.equals("log")) {
                        if (jSONObject4.getString(String.MESSAGE).contains("onTouchStart")) {
                            return false;
                        }
                    }
                    if (host.equals("log")) {
                        if (jSONObject4.getString(String.MESSAGE).contains("native_onUpdateTime")) {
                            return false;
                        }
                    }
                    string = jSONObject4.getString("__callbackId");
                    bc.a("callback id", string, null);
                    str2 = parse.getQueryParameter("callback");
                    if (host.equals("startVideo")) {
                        if (host.equals("pauseVideo")) {
                            if (host.equals("getAdInfo")) {
                                if (host.equals("volume")) {
                                    str2 = "native_callback";
                                    if (jSONObject4.isNull("value")) {
                                        i = jSONObject4.getInt("value");
                                        obj = new JSONObject(new q(this, i));
                                        if (i != 0) {
                                            this.a.j.b();
                                        } else {
                                            this.a.j.c();
                                        }
                                    } else {
                                        obj = new JSONObject(new r(this));
                                    }
                                    bc.a("Set volume.", "args:" + obj, null);
                                }
                            } else {
                                jSONObject = new JSONObject();
                                jSONObject.put("plt", aa.c());
                                jSONObject.put("appid", aa.d());
                                jSONObject.put("lang", aa.e());
                                str2 = aa.i();
                                h = aa.h();
                                try {
                                    str2 = URLEncoder.encode(str2, c.DEFAULT_CHARSET);
                                    encode = URLEncoder.encode(h, c.DEFAULT_CHARSET);
                                    obj3 = str2;
                                    obj4 = encode;
                                } catch (Exception e) {
                                    Object obj5 = obj3;
                                    h = str2;
                                    obj4 = obj5;
                                }
                                jSONObject.put("dvbrnd", obj4);
                                jSONObject.put("dvnm", obj3);
                                jSONObject.put("dpw", aa.k());
                                jSONObject.put("dph", aa.l());
                                jSONObject.put("osv", aa.g());
                                jSONObject.put("dpr", (double) aa.j());
                                jSONObject.put("gaid", aa.f());
                                jSONObject.put("nws", aa.m());
                                jSONObject.put("sdkv", "1.0.5");
                                jSONObject.put("appv", aa.b());
                                obj = new JSONObject(new p(this, jSONObject));
                                str2 = "native_callback";
                            }
                        } else {
                            this.a.g.c();
                        }
                    } else {
                        this.a.g.a();
                    }
                    if (host.equals("getCreativeAsBase64")) {
                        str2 = jSONObject4.getString("creativeUrl");
                        bArr = new byte[12288];
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            fileInputStream = new FileInputStream(this.a.c.a(str2));
                            while (true) {
                                read = fileInputStream.read(bArr);
                                if (read > 0) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            }
                            fileInputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        bArr = byteArrayOutputStream.toByteArray();
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e3) {
                        }
                        str2 = "native_callback";
                        obj = new String("data:application/octet-stream;base64," + Base64.encodeToString(bArr, 2));
                        bc.a("Api getCreativeAsBase64 Complete.", "args:", null);
                    }
                    if (host.equals("getVideoThumbnailAsBase64")) {
                        str4 = str2;
                    } else {
                        str2 = "native_callback";
                        mediaMetadataRetriever = new MediaMetadataRetriever();
                        bitmap = null;
                        try {
                            mediaMetadataRetriever.setDataSource(this.a.c.a(this.a.c.c).getPath());
                            bitmap = mediaMetadataRetriever.getFrameAtTime((long) (jSONObject4.getInt("position") * 1000000), 2);
                        } catch (Exception e4) {
                        }
                        if (bitmap == null) {
                            return false;
                        }
                        byteArrayOutputStream2 = new ByteArrayOutputStream();
                        bitmap.compress(CompressFormat.JPEG, 90, byteArrayOutputStream2);
                        encodeToString = Base64.encodeToString(byteArrayOutputStream2.toByteArray(), 2);
                        try {
                            byteArrayOutputStream2.close();
                            mediaMetadataRetriever.release();
                            bitmap.recycle();
                        } catch (IOException e5) {
                        }
                        obj = new String("data:image/jpeg;base64," + encodeToString);
                        str4 = "native_callback";
                    }
                    if (host.equals("replayVideo")) {
                        if (host.equals("skipVideo")) {
                            this.a.a(Boolean.valueOf(true));
                            this.a.g.d();
                        }
                        if (host.equals("getDeviceInfo")) {
                            if (host.equals("sendViewLog")) {
                                if (host.equals("openClickUrl")) {
                                    if (host.equals("openToBeginConversionUrl")) {
                                        if (host.equals("openUrl")) {
                                            if (host.equals("onDeviceRotated")) {
                                                if (host.equals("isInstalledByUrlScheme")) {
                                                    if (host.equals("isInstalledByApplicationId")) {
                                                        if (host.equals("startApplicationByUrlScheme")) {
                                                            if (host.equals("startApplicationByApplicationId")) {
                                                                if (host.equals("notifyAdStockOut")) {
                                                                    if (host.equals("Error")) {
                                                                        this.a.f.onFailed(FailNotificationReason.RESPONSE, this.a.b.b);
                                                                        bc.a("Ad View error.", "error:" + jSONObject4, "ErroCode(script error)", null);
                                                                    }
                                                                    obj3 = obj;
                                                                } else {
                                                                    this.a.f.onFailed(FailNotificationReason.AD_STOCK_OUT, this.a.b.b);
                                                                    obj3 = obj;
                                                                }
                                                            } else {
                                                                obj3 = obj;
                                                            }
                                                        } else {
                                                            obj3 = obj;
                                                        }
                                                    } else {
                                                        valueOf = Boolean.valueOf(aa.b(jSONObject4.getString("appId")));
                                                    }
                                                } else {
                                                    valueOf = Boolean.valueOf(aa.a(jSONObject4.getString("urlScheme")));
                                                }
                                            } else {
                                                obj3 = obj;
                                            }
                                        } else {
                                            w.a(this.a.getContext(), Uri.parse(jSONObject4.getString(String.URL)), 268435456);
                                            obj3 = obj;
                                        }
                                    } else {
                                        obj3 = obj;
                                    }
                                } else if (this.a.q == null && this.a.q.isAlive()) {
                                    return true;
                                } else {
                                    jSONObject2 = jSONObject4;
                                    h = this.a.b(Uri.parse(String.format("%s&ad_deliver_test=%s&vt=%s", new Object[]{jSONObject2.getString(String.URL), this.a.e.c, this.a.c()})).getEncodedQuery());
                                    str2 = String.format("%s&cd=%s", new Object[]{str2, h});
                                    if (this.a.d.k != null || this.a.d.k.equals(BuildConfig.FLAVOR)) {
                                        this.a.g.a(str2);
                                    } else {
                                        try {
                                            this.a.q = new Thread(new t(this, str2));
                                            this.a.q.start();
                                        } catch (Exception e6) {
                                        }
                                    }
                                    if (this.a.b != null) {
                                        this.a.f.onClickedAd(this.a.b.b);
                                    }
                                    obj3 = obj;
                                }
                            } else {
                                bc.a("SendViewLog.", "args:" + obj, null);
                                str2 = jSONObject4.getString("queryString");
                                if (str2 != null) {
                                    str2 = String.format("%s&vt=%s&ad_deliver_test=%s", new Object[]{str2, this.a.c(), this.a.e.c});
                                }
                                new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a").format(Calendar.getInstance().getTime());
                                h = this.a.b(str2);
                                this.a.o.a(new au(this.a.b.b, String.valueOf(this.a.c.f), String.valueOf(this.a.c.b), String.valueOf(this.a.m), Boolean.valueOf(this.a.n), this.a.l, String.format("%s&cd=%s", new Object[]{str2, h}), Boolean.valueOf(false), Calendar.getInstance().getTime()), this.a.e.a.g);
                                obj3 = obj;
                            }
                        } else {
                            jSONObject3 = new JSONObject(new s(this));
                            bc.a("Api getDeviceInfo Complete.", "args:" + jSONObject3, null);
                            obj3 = jSONObject3;
                        }
                        if (obj2 != null) {
                            this.a.g.d();
                        }
                        if (!TextUtils.isEmpty(str4)) {
                            if (obj3 == null) {
                                str2 = obj3.toString();
                                if (obj3 instanceof String) {
                                    str2 = "'" + str2 + "'";
                                }
                                if (VERSION.SDK_INT < 19) {
                                    this.a.a(str4, string, str2);
                                } else {
                                    super.loadUrl("javascript:" + str4 + "( " + string + ", " + str2 + ");");
                                }
                            } else if (VERSION.SDK_INT < 19) {
                                this.a.c(str4);
                            } else {
                                super.loadUrl("javascript:" + str4 + "();");
                            }
                        }
                        return true;
                    }
                    this.a.g.b();
                    return true;
                }
            }
            obj2 = null;
            if (host.equals("log")) {
                if (jSONObject4.getString(String.MESSAGE).contains("onTouchStart")) {
                    return false;
                }
            }
            if (host.equals("log")) {
                if (jSONObject4.getString(String.MESSAGE).contains("native_onUpdateTime")) {
                    return false;
                }
            }
            try {
                string = jSONObject4.getString("__callbackId");
            } catch (Exception e7) {
                string = str2;
            }
            bc.a("callback id", string, null);
            str2 = parse.getQueryParameter("callback");
            try {
                if (host.equals("startVideo")) {
                    if (host.equals("pauseVideo")) {
                        if (host.equals("getAdInfo")) {
                            if (host.equals("volume")) {
                                str2 = "native_callback";
                                if (jSONObject4.isNull("value")) {
                                    obj = new JSONObject(new r(this));
                                } else {
                                    i = jSONObject4.getInt("value");
                                    obj = new JSONObject(new q(this, i));
                                    if (i != 0) {
                                        this.a.j.c();
                                    } else {
                                        this.a.j.b();
                                    }
                                }
                                bc.a("Set volume.", "args:" + obj, null);
                            }
                        } else {
                            jSONObject = new JSONObject();
                            jSONObject.put("plt", aa.c());
                            jSONObject.put("appid", aa.d());
                            jSONObject.put("lang", aa.e());
                            str2 = aa.i();
                            h = aa.h();
                            str2 = URLEncoder.encode(str2, c.DEFAULT_CHARSET);
                            encode = URLEncoder.encode(h, c.DEFAULT_CHARSET);
                            obj3 = str2;
                            obj4 = encode;
                            jSONObject.put("dvbrnd", obj4);
                            jSONObject.put("dvnm", obj3);
                            jSONObject.put("dpw", aa.k());
                            jSONObject.put("dph", aa.l());
                            jSONObject.put("osv", aa.g());
                            jSONObject.put("dpr", (double) aa.j());
                            jSONObject.put("gaid", aa.f());
                            jSONObject.put("nws", aa.m());
                            jSONObject.put("sdkv", "1.0.5");
                            jSONObject.put("appv", aa.b());
                            obj = new JSONObject(new p(this, jSONObject));
                            str2 = "native_callback";
                        }
                    } else {
                        this.a.g.c();
                    }
                } else {
                    this.a.g.a();
                }
                if (host.equals("getCreativeAsBase64")) {
                    str2 = jSONObject4.getString("creativeUrl");
                    bArr = new byte[12288];
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    fileInputStream = new FileInputStream(this.a.c.a(str2));
                    while (true) {
                        read = fileInputStream.read(bArr);
                        if (read > 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    fileInputStream.close();
                    bArr = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    str2 = "native_callback";
                    obj = new String("data:application/octet-stream;base64," + Base64.encodeToString(bArr, 2));
                    bc.a("Api getCreativeAsBase64 Complete.", "args:", null);
                }
                if (host.equals("getVideoThumbnailAsBase64")) {
                    str4 = str2;
                } else {
                    str2 = "native_callback";
                    mediaMetadataRetriever = new MediaMetadataRetriever();
                    bitmap = null;
                    mediaMetadataRetriever.setDataSource(this.a.c.a(this.a.c.c).getPath());
                    bitmap = mediaMetadataRetriever.getFrameAtTime((long) (jSONObject4.getInt("position") * 1000000), 2);
                    if (bitmap == null) {
                        return false;
                    }
                    byteArrayOutputStream2 = new ByteArrayOutputStream();
                    bitmap.compress(CompressFormat.JPEG, 90, byteArrayOutputStream2);
                    encodeToString = Base64.encodeToString(byteArrayOutputStream2.toByteArray(), 2);
                    byteArrayOutputStream2.close();
                    mediaMetadataRetriever.release();
                    bitmap.recycle();
                    obj = new String("data:image/jpeg;base64," + encodeToString);
                    str4 = "native_callback";
                }
                if (host.equals("replayVideo")) {
                    if (host.equals("skipVideo")) {
                        this.a.a(Boolean.valueOf(true));
                        this.a.g.d();
                    }
                    if (host.equals("getDeviceInfo")) {
                        if (host.equals("sendViewLog")) {
                            if (host.equals("openClickUrl")) {
                                if (host.equals("openToBeginConversionUrl")) {
                                    if (host.equals("openUrl")) {
                                        if (host.equals("onDeviceRotated")) {
                                            if (host.equals("isInstalledByUrlScheme")) {
                                                if (host.equals("isInstalledByApplicationId")) {
                                                    if (host.equals("startApplicationByUrlScheme")) {
                                                        if (host.equals("startApplicationByApplicationId")) {
                                                            if (host.equals("notifyAdStockOut")) {
                                                                if (host.equals("Error")) {
                                                                    this.a.f.onFailed(FailNotificationReason.RESPONSE, this.a.b.b);
                                                                    bc.a("Ad View error.", "error:" + jSONObject4, "ErroCode(script error)", null);
                                                                }
                                                                obj3 = obj;
                                                            } else {
                                                                this.a.f.onFailed(FailNotificationReason.AD_STOCK_OUT, this.a.b.b);
                                                                obj3 = obj;
                                                            }
                                                        } else {
                                                            obj3 = obj;
                                                        }
                                                    } else {
                                                        obj3 = obj;
                                                    }
                                                } else {
                                                    valueOf = Boolean.valueOf(aa.b(jSONObject4.getString("appId")));
                                                }
                                            } else {
                                                valueOf = Boolean.valueOf(aa.a(jSONObject4.getString("urlScheme")));
                                            }
                                        } else {
                                            obj3 = obj;
                                        }
                                    } else {
                                        w.a(this.a.getContext(), Uri.parse(jSONObject4.getString(String.URL)), 268435456);
                                        obj3 = obj;
                                    }
                                } else {
                                    obj3 = obj;
                                }
                            } else {
                                if (this.a.q == null) {
                                }
                                jSONObject2 = jSONObject4;
                                h = this.a.b(Uri.parse(String.format("%s&ad_deliver_test=%s&vt=%s", new Object[]{jSONObject2.getString(String.URL), this.a.e.c, this.a.c()})).getEncodedQuery());
                                str2 = String.format("%s&cd=%s", new Object[]{str2, h});
                                if (this.a.d.k != null) {
                                }
                                this.a.g.a(str2);
                                if (this.a.b != null) {
                                    this.a.f.onClickedAd(this.a.b.b);
                                }
                                obj3 = obj;
                            }
                        } else {
                            bc.a("SendViewLog.", "args:" + obj, null);
                            str2 = jSONObject4.getString("queryString");
                            if (str2 != null) {
                                str2 = String.format("%s&vt=%s&ad_deliver_test=%s", new Object[]{str2, this.a.c(), this.a.e.c});
                            }
                            new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a").format(Calendar.getInstance().getTime());
                            h = this.a.b(str2);
                            this.a.o.a(new au(this.a.b.b, String.valueOf(this.a.c.f), String.valueOf(this.a.c.b), String.valueOf(this.a.m), Boolean.valueOf(this.a.n), this.a.l, String.format("%s&cd=%s", new Object[]{str2, h}), Boolean.valueOf(false), Calendar.getInstance().getTime()), this.a.e.a.g);
                            obj3 = obj;
                        }
                    } else {
                        jSONObject3 = new JSONObject(new s(this));
                        bc.a("Api getDeviceInfo Complete.", "args:" + jSONObject3, null);
                        obj3 = jSONObject3;
                    }
                    if (obj2 != null) {
                        this.a.g.d();
                    }
                    if (TextUtils.isEmpty(str4)) {
                        if (obj3 == null) {
                            str2 = obj3.toString();
                            if (obj3 instanceof String) {
                                str2 = "'" + str2 + "'";
                            }
                            if (VERSION.SDK_INT < 19) {
                                super.loadUrl("javascript:" + str4 + "( " + string + ", " + str2 + ");");
                            } else {
                                this.a.a(str4, string, str2);
                            }
                        } else if (VERSION.SDK_INT < 19) {
                            super.loadUrl("javascript:" + str4 + "();");
                        } else {
                            this.a.c(str4);
                        }
                    }
                    return true;
                }
                this.a.g.b();
                return true;
            } catch (Throwable e8) {
                super.loadUrl("javascript:error(" + host + ");");
                this.a.f.onFailed(FailNotificationReason.RESPONSE, this.a.b.b);
                bc.a("Ad View error.", "error:" + jSONObject4, "ErroCode(api error)", e8);
            } catch (Throwable e82) {
                bc.a("Out Of Memory error.", "error:" + jSONObject4, "ErroCode(api error)", e82);
            }
        } catch (Throwable e822) {
            bc.a("Ad View Data Create Error.", "ErroCode(args)" + str + "Message:" + e822.getMessage(), "ErroCode(url)", e822);
            return false;
        }
    }
}
