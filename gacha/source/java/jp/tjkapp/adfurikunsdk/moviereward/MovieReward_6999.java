package jp.tjkapp.adfurikunsdk.moviereward;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import com.glossomads.GlossomAds;
import com.glossomads.Listener.GlossomAdsAdListener;
import com.glossomads.Listener.GlossomAdsAdRewardListener;
import com.glossomads.Model.GlossomAdsAdReward;
import com.tapjoy.TapjoyConstants;
import jp.tjkapp.adfurikunsdk.moviereward.AdnetworkWorker.AdnetworkWorkerListener;
import org.cocos2dx.lib.BuildConfig;

public class MovieReward_6999 extends AdnetworkWorker {
    public static final String ADNETWORK_KEY = "6999";
    public static final String ADNETWORK_NAME = "Sugar";
    private static final MovieRewardData n = new MovieRewardData(ADNETWORK_KEY, ADNETWORK_NAME);
    private static boolean q = false;
    GlossomAdsAdListener l;
    GlossomAdsAdRewardListener m;
    private String o;
    private String p;

    public /* bridge */ /* synthetic */ void destroy() {
        super.destroy();
    }

    public /* bridge */ /* synthetic */ void init(Activity activity, String str, AdInfoDetail adInfoDetail, String str2, Handler handler) {
        super.init(activity, str, adInfoDetail, str2, handler);
    }

    public /* bridge */ /* synthetic */ void preload() {
        super.preload();
    }

    public /* bridge */ /* synthetic */ void setAdfurikunMovieListener(AdfurikunMovieListener adfurikunMovieListener) {
        super.setAdfurikunMovieListener(adfurikunMovieListener);
    }

    public /* bridge */ /* synthetic */ void setAdnetworkWorkerListener(AdnetworkWorkerListener adnetworkWorkerListener) {
        super.setAdnetworkWorkerListener(adnetworkWorkerListener);
    }

    public /* bridge */ /* synthetic */ void start() {
        super.start();
    }

    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    private GlossomAdsAdListener d() {
        if (this.l == null) {
            this.l = new GlossomAdsAdListener(this) {
                final /* synthetic */ MovieReward_6999 a;

                {
                    this.a = r1;
                }

                public void onGlossomAdsVideoStart(String str) {
                    this.a.j.detail(Constants.TAG, "6999: adListener.onGlossomAdsVideoStarted");
                }

                public void onGlossomAdsVideoPause(String str) {
                }

                public void onGlossomAdsVideoResume(String str) {
                }

                public void onGlossomAdsVideoFinish(String str, boolean z) {
                    this.a.j.detail(Constants.TAG, "6999: adListener.onGlossomAdsVideoFinished");
                    if (z) {
                        this.a.j.detail(Constants.TAG, "6999: adListener.onGlossomAdsVideoFinished.shown");
                    } else {
                        this.a.j.detail(Constants.TAG, "6999: adListener.onGlossomAdsVideoFinished.notShown");
                    }
                }

                public void onGlossomAdsVideoClose(String str) {
                    this.a.j.detail(Constants.TAG, "6999: adListener.onGlossomAdsVideoClosed");
                    this.a.d(MovieReward_6999.n);
                    this.a.a(this.a, MovieReward_6999.n);
                }
            };
        }
        return this.l;
    }

    private GlossomAdsAdRewardListener e() {
        if (this.m == null) {
            this.m = new GlossomAdsAdRewardListener(this) {
                final /* synthetic */ MovieReward_6999 a;

                {
                    this.a = r1;
                }

                public void onGlossomAdsAdReward(GlossomAdsAdReward glossomAdsAdReward) {
                    if (glossomAdsAdReward.success()) {
                        this.a.a();
                        this.a.c(MovieReward_6999.n);
                        return;
                    }
                    this.a.b(MovieReward_6999.n);
                }
            };
        }
        return this.m;
    }

    public void initWorker() {
        this.j.debug(Constants.TAG, "6999: Sugar init");
        setTestDevice(this.g);
        this.o = this.c.getString(TapjoyConstants.TJC_APP_ID);
        this.p = this.c.getString("zone_id");
        String[] stringArray = this.c.getStringArray("all_zones");
        if (stringArray == null || stringArray.length == 0) {
            stringArray = new String[]{this.p};
        }
        if (!q) {
            String str = "adfully_ver:2.5.2";
            GlossomAds.configure(this.a, "adfully_ver:2.5.2", this.o, stringArray);
            GlossomAds.setSugarAdRewardListener(e());
            q = true;
        }
    }

    public boolean isEnable() {
        try {
            boolean z = Class.forName("com.glossomads.GlossomAds") != null;
            if (z) {
                return z;
            }
            this.j.debug_w(Constants.TAG, "6999: sdk not found.");
            return z;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public String getAdnetworkKey() {
        return ADNETWORK_KEY;
    }

    public boolean isPrepared() {
        return GlossomAds.isReady(this.p);
    }

    public void play(MovieMediater movieMediater) {
        this.j.debug(Constants.TAG, "6999: play");
        if (isPrepared()) {
            b();
            a(n);
            GlossomAds.showRewardVideo(this.p, d());
        }
    }

    public boolean isProvideTestMode() {
        return true;
    }

    public MovieRewardData getMovieData() {
        return n;
    }

    public void resume(Activity activity) {
    }

    public void pause() {
    }

    public void setTestDevice(boolean z) {
        if (z && this.a != null) {
            Object str;
            String string = this.a.getApplicationContext().getSharedPreferences(Constants.PREF_FILE, 0).getString(Constants.PREFKEY_GAID, BuildConfig.FLAVOR);
            try {
                str = new String(Base64.decode(string, 2));
            } catch (Exception e) {
                String str2 = string;
            }
            if (!TextUtils.isEmpty(str)) {
                GlossomAds.addTestDevice(str);
            }
        }
    }
}
