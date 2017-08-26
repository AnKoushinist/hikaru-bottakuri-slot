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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;

class n extends WebViewClient {
    final /* synthetic */ l a;

    n(l lVar) {
        this.a = lVar;
    }

    public void onPageFinished(WebView webView, String str) {
        if (str.startsWith("data:text/html")) {
            this.a.g.countDown();
        }
        super.onPageFinished(webView, str);
        this.a.a = true;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String str2 = "unknown";
        String str3 = BuildConfig.FLAVOR;
        boolean z = false;
        Object obj = null;
        str2 = BuildConfig.FLAVOR;
        ax.a("Api call args string.", "call url:" + str, null);
        String str4 = "native://";
        if (!str.startsWith("native://")) {
            return false;
        }
        Uri parse = Uri.parse(str);
        String host = parse.getHost();
        try {
            String string;
            Object decode = Uri.decode(parse.getLastPathSegment());
            JSONObject jSONObject = TextUtils.isEmpty(decode) ? null : new JSONObject(decode);
            if (host.equals("log")) {
                if (jSONObject.getString(String.MESSAGE).contains("close-ad.click")) {
                    z = true;
                    ax.a("API Call param", "apiName:" + host + " callbackFunctionName:" + str3 + " args:" + jSONObject + " close_ad:" + true, null);
                }
            }
            boolean z2 = z;
            if (host.equals("log")) {
                if (jSONObject.getString(String.MESSAGE).contains("onTouchStart")) {
                    return false;
                }
            }
            if (host.equals("log")) {
                if (jSONObject.getString(String.MESSAGE).contains("native_onUpdateTime")) {
                    return false;
                }
            }
            try {
                string = jSONObject.getString("__callbackId");
            } catch (Exception e) {
                string = str2;
            }
            ax.a("callback id", string, null);
            str2 = parse.getQueryParameter("callback");
            try {
                String h;
                Object obj2;
                String str5;
                if (host.equals("startVideo")) {
                    this.a.f.a();
                } else {
                    if (host.equals("pauseVideo")) {
                        this.a.f.c();
                    } else {
                        if (host.equals("getAdInfo")) {
                            Object obj3;
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("plt", x.c());
                            jSONObject2.put("appid", x.d());
                            jSONObject2.put("lang", x.e());
                            str2 = x.i();
                            h = x.h();
                            try {
                                str2 = URLEncoder.encode(str2, c.DEFAULT_CHARSET);
                                String encode = URLEncoder.encode(h, c.DEFAULT_CHARSET);
                                obj2 = str2;
                                obj3 = encode;
                            } catch (Exception e2) {
                                Object obj4 = obj2;
                                h = str2;
                                obj3 = obj4;
                            }
                            jSONObject2.put("dvbrnd", obj3);
                            jSONObject2.put("dvnm", obj2);
                            jSONObject2.put("dpw", x.k());
                            jSONObject2.put("dph", x.l());
                            jSONObject2.put("osv", x.g());
                            jSONObject2.put("dpr", (double) x.j());
                            jSONObject2.put("gaid", x.f());
                            jSONObject2.put("nws", x.m());
                            jSONObject2.put("sdkv", "1.0.3");
                            jSONObject2.put("appv", x.b());
                            obj = new JSONObject(new o(this, jSONObject2));
                            str2 = "native_callback";
                            ax.a("Api getZoneInfo Complete.", "args:" + obj, null);
                        } else {
                            if (host.equals("volume")) {
                                str2 = "native_callback";
                                if (jSONObject.isNull("value")) {
                                    obj = new JSONObject(new q(this));
                                } else {
                                    int i = jSONObject.getInt("value");
                                    obj = new JSONObject(new p(this, i));
                                    if (i == 0) {
                                        this.a.i.b();
                                    } else {
                                        this.a.i.c();
                                    }
                                }
                                ax.a("Set volume.", "args:" + obj, null);
                            }
                        }
                    }
                }
                if (host.equals("getCreativeAsBase64")) {
                    str2 = jSONObject.getString("creativeUrl");
                    byte[] bArr = new byte[12288];
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        InputStream fileInputStream = new FileInputStream(this.a.c.a(str2));
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e4) {
                    }
                    str2 = "native_callback";
                    obj = new String("data:application/octet-stream;base64," + Base64.encodeToString(bArr, 2));
                    ax.a("Api getCreativeAsBase64 Complete.", "args:", null);
                }
                if (host.equals("getVideoThumbnailAsBase64")) {
                    str2 = "native_callback";
                    MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                    Bitmap bitmap = null;
                    try {
                        mediaMetadataRetriever.setDataSource(this.a.c.a(this.a.c.c).getPath());
                        bitmap = mediaMetadataRetriever.getFrameAtTime((long) (jSONObject.getInt("position") * 1000000), 2);
                    } catch (Exception e5) {
                    }
                    if (bitmap == null) {
                        return false;
                    }
                    OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    bitmap.compress(CompressFormat.JPEG, 90, byteArrayOutputStream2);
                    str3 = Base64.encodeToString(byteArrayOutputStream2.toByteArray(), 2);
                    try {
                        byteArrayOutputStream2.close();
                        mediaMetadataRetriever.release();
                        bitmap.recycle();
                    } catch (IOException e6) {
                    }
                    obj = new String("data:image/jpeg;base64," + str3);
                    str5 = "native_callback";
                } else {
                    str5 = str2;
                }
                if (host.equals("replayVideo")) {
                    this.a.f.b();
                    return true;
                }
                if (host.equals("skipVideo")) {
                    this.a.f.d();
                    this.a.a(Boolean.valueOf(true));
                }
                if (host.equals("getDeviceInfo")) {
                    JSONObject jSONObject3 = new JSONObject(new r(this));
                    ax.a("Api getDeviceInfo Complete.", "args:" + jSONObject3, null);
                    obj2 = jSONObject3;
                } else {
                    Matcher matcher;
                    if (host.equals("sendViewLog")) {
                        ax.a("SendViewLog.", "args:" + obj, null);
                        str2 = jSONObject.getString("queryString");
                        if (str2 != null) {
                            obj2 = String.format("%s&vt=%s&ad_deliver_test=%s", new Object[]{str2, this.a.c(), this.a.d.d});
                        } else {
                            h = str2;
                        }
                        new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a").format(Calendar.getInstance().getTime());
                        matcher = Pattern.compile("ec=(.*?)&").matcher(obj2);
                        matcher.find();
                        str4 = matcher.group(1);
                        str2 = BuildConfig.FLAVOR;
                        try {
                            str2 = URLEncoder.encode(str4, c.DEFAULT_CHARSET);
                        } catch (Exception e7) {
                        }
                        h = this.a.a(obj2.replace("ec=" + str4, "ec=" + str2));
                        this.a.n.a(new aq(this.a.b.b, String.valueOf(this.a.c.f), String.valueOf(this.a.c.b), String.valueOf(this.a.l), Boolean.valueOf(this.a.m), this.a.k, String.format("%s&cd=%s", new Object[]{str2, h}), Boolean.valueOf(false), Calendar.getInstance().getTime()), this.a.d.b.f);
                        obj2 = obj;
                    } else {
                        if (host.equals("openClickUrl")) {
                            JSONObject jSONObject4 = jSONObject;
                            obj2 = String.format("%s&ad_deliver_test=%s&vt=%s", new Object[]{jSONObject4.getString(String.URL), this.a.d.d, this.a.c()});
                            matcher = Pattern.compile("redirect_url=(.*?)&ad_deliver_test").matcher(obj2);
                            matcher.find();
                            str4 = matcher.group(1);
                            str2 = BuildConfig.FLAVOR;
                            try {
                                str2 = URLEncoder.encode(str4, c.DEFAULT_CHARSET);
                            } catch (Exception e8) {
                            }
                            h = this.a.a(Uri.parse(obj2.replace("redirect_url=" + str4, "redirect_url=" + str2)).getEncodedQuery());
                            this.a.f.a(String.format("%s&cd=%s", new Object[]{str2, h}));
                            this.a.e.onClickedAd(this.a.b.b);
                            obj2 = obj;
                        } else {
                            if (host.equals("openToBeginConversionUrl")) {
                                obj2 = obj;
                            } else {
                                if (host.equals("openUrl")) {
                                    u.a(this.a.getContext(), Uri.parse(jSONObject.getString(String.URL)), 268435456);
                                    obj2 = obj;
                                } else {
                                    if (host.equals("onDeviceRotated")) {
                                        obj2 = obj;
                                    } else {
                                        Boolean valueOf;
                                        if (host.equals("isInstalledByUrlScheme")) {
                                            valueOf = Boolean.valueOf(x.a(jSONObject.getString("urlScheme")));
                                        } else {
                                            if (host.equals("isInstalledByApplicationId")) {
                                                valueOf = Boolean.valueOf(x.b(jSONObject.getString("appId")));
                                            } else {
                                                if (host.equals("startApplicationByUrlScheme")) {
                                                    obj2 = obj;
                                                } else {
                                                    if (host.equals("startApplicationByApplicationId")) {
                                                        obj2 = obj;
                                                    } else {
                                                        if (host.equals("notifyAdStockOut")) {
                                                            this.a.e.onFailed(FailNotificationReason.AD_STOCK_OUT, this.a.b.b);
                                                            obj2 = obj;
                                                        } else {
                                                            if (host.equals("Error")) {
                                                                this.a.e.onFailed(FailNotificationReason.RESPONSE, this.a.b.b);
                                                                ax.a("Ad View error.", "error:" + jSONObject, "ErroCode(script error)", null);
                                                            }
                                                            obj2 = obj;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (z2) {
                    this.a.f.d();
                }
                if (!TextUtils.isEmpty(str5)) {
                    if (obj2 != null) {
                        str2 = obj2.toString();
                        if (obj2 instanceof String) {
                            str2 = "'" + str2 + "'";
                        }
                        if (VERSION.SDK_INT >= 19) {
                            this.a.a(str5, string, str2);
                        } else {
                            super.loadUrl("javascript:" + str5 + "( " + string + ", " + str2 + ");");
                        }
                        ax.a("Callback result.", "result:" + str2, null);
                    } else if (VERSION.SDK_INT >= 19) {
                        this.a.b(str5);
                    } else {
                        super.loadUrl("javascript:" + str5 + "();");
                    }
                }
                return true;
            } catch (Throwable e9) {
                super.loadUrl("javascript:error(" + host + ");");
                this.a.e.onFailed(FailNotificationReason.RESPONSE, this.a.b.b);
                ax.a("Ad View error.", "error:" + jSONObject, "ErroCode(api error)", e9);
            } catch (Throwable e92) {
                ax.a("Out Of Memory error.", "error:" + jSONObject, "ErroCode(api error)", e92);
            }
        } catch (Throwable e922) {
            ax.a("Ad View Data Create Error.", "ErroCode(args)" + str + "Message:" + e922.getMessage(), "ErroCode(url)", e922);
            return false;
        }
    }
}
