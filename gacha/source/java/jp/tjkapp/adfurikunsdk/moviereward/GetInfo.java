package jp.tjkapp.adfurikunsdk.moviereward;

import android.content.Context;
import android.text.TextUtils;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.util.Date;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil.WebAPIResult;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;
import twitter4j.HttpResponseCode;

class GetInfo {
    private Context a;
    private String b;
    private AdInfo c;
    private GetInfoUpdateTask d;
    private GetInfoListener e;
    private LogUtil f = LogUtil.getInstance(this.a);
    private String g;

    public interface GetInfoListener {
        void updateFail(int i, String str, Exception exception);

        void updateSuccess(AdInfo adInfo);
    }

    private class GetInfoUpdateTask extends Thread {
        final /* synthetic */ GetInfo a;
        private int b;
        private String c;
        private Exception d;
        private boolean e = false;

        public GetInfoUpdateTask(GetInfo getInfo) {
            this.a = getInfo;
        }

        public void cancelTask() {
            this.e = true;
        }

        public void run() {
            try {
                a();
            } finally {
                this.a.d = null;
            }
        }

        private void a() {
            for (int i = 0; i < 10; i++) {
                if (this.e) {
                    break;
                }
                boolean a;
                WebAPIResult info = ApiAccessUtil.getInfo(this.a.b, this.a.f, this.a.g, true);
                this.b = info.returnCode;
                WebAPIResult webAPIResult;
                WebAPIResult webAPIResult2;
                if (this.b == HttpResponseCode.OK) {
                    webAPIResult = info;
                    a = a(info);
                    webAPIResult2 = webAPIResult;
                } else {
                    try {
                        if (info.returnCode == HttpResponseCode.BAD_REQUEST) {
                            webAPIResult = info;
                            a = b(info);
                            webAPIResult2 = webAPIResult;
                        } else {
                            info = ApiAccessUtil.getInfo(this.a.b, this.a.f, this.a.g, false);
                            this.b = info.returnCode;
                            if (this.b == HttpResponseCode.OK) {
                                webAPIResult = info;
                                a = a(info);
                                webAPIResult2 = webAPIResult;
                            } else if (info.returnCode == HttpResponseCode.BAD_REQUEST) {
                                webAPIResult = info;
                                a = b(info);
                                webAPIResult2 = webAPIResult;
                            } else {
                                webAPIResult2 = info;
                                a = false;
                            }
                        }
                    } catch (Exception e) {
                        this.d = e;
                    }
                }
                this.a.f.detail(Constants.TAG, "StatusCode:" + this.b + ", Message:" + this.c);
                if (a) {
                    break;
                }
                synchronized (r1) {
                    try {
                        this.a.f.debug("GetInfo", "GetInfo\u304c\u53d6\u308c\u306a\u3044\u30021\u79d2\u5f85\u6a5f");
                        wait(1000);
                    } catch (InterruptedException e2) {
                    }
                }
            }
            if (!this.e) {
                if (this.a.c == null || this.a.c.isOverExpiration()) {
                    if (this.a.e != null) {
                        this.a.e.updateFail(this.b, this.c, this.d);
                    }
                } else if (this.a.e != null) {
                    this.a.e.updateSuccess(this.a.c);
                }
            }
        }

        private boolean a(WebAPIResult webAPIResult) {
            if (a(webAPIResult.message)) {
                AdInfo conversionToAdInfo = AdInfo.conversionToAdInfo(this.a.a, this.a.b, webAPIResult.message, false);
                if (conversionToAdInfo != null) {
                    long time = new Date().getTime();
                    if (!a(webAPIResult.message, time)) {
                        return false;
                    }
                    this.c = "getInfo is saved";
                    this.a.c = conversionToAdInfo;
                    this.a.c.setExpirationMs(time + TapjoyConstants.PAID_APP_TIME);
                    return true;
                }
                this.c = "getInfo failed because invalid format1";
                return false;
            }
            this.c = "getInfo failed because invalid format2";
            return false;
        }

        private boolean b(WebAPIResult webAPIResult) {
            this.c = "getInfo failed because HttpStatus.SC_BAD_REQUEST(sc400)";
            return false;
        }

        private boolean a(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                String str2 = BuildConfig.FLAVOR;
                if (!jSONObject.has("result")) {
                    return false;
                }
                str2 = jSONObject.getString("result");
                if ("ok".equals(str2)) {
                    return true;
                }
                if (!String.VIDEO_ERROR.equals(str2) || !jSONObject.has("values")) {
                    return false;
                }
                JSONObject jSONObject2 = new JSONObject(jSONObject.getString("values"));
                if (!jSONObject2.has(String.MESSAGE)) {
                    return false;
                }
                this.a.f.debug_e(Constants.TAG, "error=" + jSONObject2.getString(String.MESSAGE));
                return false;
            } catch (Exception e) {
                this.a.f.debug_e(Constants.TAG, "JSONException");
                this.a.f.debug_e(Constants.TAG, e);
                return false;
            }
        }

        private boolean a(String str, long j) {
            String getInfoFilePath = FileUtil.getGetInfoFilePath(this.a.a, this.a.b);
            long getInfoUpdateTime = FileUtil.getGetInfoUpdateTime(this.a.a, this.a.b);
            String loadStringFile = FileUtil.loadStringFile(getInfoFilePath);
            try {
                FileUtil.saveGetInfoUpdateTime(this.a.a, this.a.b, j);
                FileUtil.deleteFile(getInfoFilePath);
                FileUtil.saveStringFile(getInfoFilePath, str);
                return true;
            } catch (Exception e) {
                FileUtil.saveGetInfoUpdateTime(this.a.a, this.a.b, getInfoUpdateTime);
                FileUtil.saveStringFile(getInfoFilePath, loadStringFile);
                return false;
            }
        }
    }

    public GetInfo(Context context, String str, String str2) {
        this.a = context;
        this.b = str;
        this.g = str2;
    }

    public void destroy() {
        if (this.d != null && this.d.isAlive()) {
            this.d.cancelTask();
            this.d = null;
        }
    }

    public void setGetInfoListener(GetInfoListener getInfoListener) {
        this.e = getInfoListener;
    }

    public AdInfo getAdInfo() {
        if (this.c == null) {
            this.c = AdInfo.conversionToAdInfo(this.a, this.b, FileUtil.loadStringFile(FileUtil.getGetInfoFilePath(this.a, this.b)), true);
            if (this.c == null) {
                a(false);
                return null;
            }
        }
        this.c.setExpirationMs(FileUtil.getGetInfoUpdateTime(this.a, this.b) + TapjoyConstants.PAID_APP_TIME);
        if (!this.c.isOverExpiration()) {
            return this.c;
        }
        a(false);
        return null;
    }

    public void forceUpdate() {
        a(true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void a(boolean r2) {
        /*
        r1 = this;
        monitor-enter(r1);
        if (r2 != 0) goto L_0x0011;
    L_0x0003:
        r0 = r1.d;	 Catch:{ all -> 0x001e }
        if (r0 == 0) goto L_0x0011;
    L_0x0007:
        r0 = r1.d;	 Catch:{ all -> 0x001e }
        r0 = r0.isAlive();	 Catch:{ all -> 0x001e }
        if (r0 == 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r1);
        return;
    L_0x0011:
        r0 = new jp.tjkapp.adfurikunsdk.moviereward.GetInfo$GetInfoUpdateTask;	 Catch:{ all -> 0x001e }
        r0.<init>(r1);	 Catch:{ all -> 0x001e }
        r1.d = r0;	 Catch:{ all -> 0x001e }
        r0 = r1.d;	 Catch:{ all -> 0x001e }
        r0.start();	 Catch:{ all -> 0x001e }
        goto L_0x000f;
    L_0x001e:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.tjkapp.adfurikunsdk.moviereward.GetInfo.a(boolean):void");
    }
}
