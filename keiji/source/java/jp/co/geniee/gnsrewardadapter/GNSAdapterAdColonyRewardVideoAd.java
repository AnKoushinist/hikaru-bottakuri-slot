package jp.co.geniee.gnsrewardadapter;

import android.app.Activity;
import com.adcolony.sdk.b;
import com.adcolony.sdk.g;
import com.adcolony.sdk.h;
import com.adcolony.sdk.k;
import com.adcolony.sdk.l;
import com.adcolony.sdk.m;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee.GNSAdapteeStatus;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoMediator;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardData;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardException;

public class GNSAdapterAdColonyRewardVideoAd extends GNSAdaptee {
    public static final String AD_NAME = "AdColony";
    public static final String APP_ID_COLUMN_NAME = "app_id";
    public static final String TAG = "AdColony";
    public static final String ZONE_ID_COLUMN_NAME = "zone_id";
    private static boolean initSdkFlag = true;
    private static String lastAppId;
    private static String lastZoneId;
    private g ad;
    private boolean canshow_ad;
    private h listener;
    private String mAppId;
    private String mZoneId;

    public boolean canShow() {
        this.mLog.d(TAG, "canShow: " + this.canshow_ad);
        return this.canshow_ad;
    }

    public void show(GNSVideoMediator gNSVideoMediator) {
        this.mLog.d(TAG, "show");
        if (canShow()) {
            adapterDidStartPlayingRewardVideoAd(this, this.mRewardData);
            this.ad.a();
        }
    }

    protected void setUpWorker() {
        this.canshow_ad = false;
        this.mLog.d(TAG, "setUp");
        this.mRewardData = new GNSVideoRewardData(TAG);
        this.mRewardData.b = this.mType;
        this.mRewardData.c = this.mAmount;
        this.mAppId = this.mOptions.getString(APP_ID_COLUMN_NAME);
        this.mZoneId = this.mOptions.getString(ZONE_ID_COLUMN_NAME);
        this.mLog.d(TAG, "AppId=" + this.mAppId);
        this.mLog.d(TAG, "ZoneId=" + this.mZoneId);
        if (initSdkFlag || !this.mAppId.equals(lastAppId) || !this.mZoneId.equals(lastZoneId)) {
            b.a(this.mActivity, this.mAppId, this.mZoneId);
            b.a(new l() {
                public void onReward(k kVar) {
                    GNSAdapterAdColonyRewardVideoAd.this.mLog.d(GNSAdapterAdColonyRewardVideoAd.TAG, "onReward");
                    if (kVar.a()) {
                        GNSAdapterAdColonyRewardVideoAd.this.mLog.d(GNSAdapterAdColonyRewardVideoAd.TAG, "onReward success");
                        GNSAdapterAdColonyRewardVideoAd.this.requestFinished();
                        GNSAdapterAdColonyRewardVideoAd.this.didRewardUserWithReward(GNSAdapterAdColonyRewardVideoAd.this, GNSAdapterAdColonyRewardVideoAd.this.mRewardData);
                        GNSAdapterAdColonyRewardVideoAd.this.adapterDidCloseRewardVideoAd(GNSAdapterAdColonyRewardVideoAd.this, GNSAdapterAdColonyRewardVideoAd.this.mRewardData);
                        return;
                    }
                    GNSAdapterAdColonyRewardVideoAd.this.mLog.c(GNSAdapterAdColonyRewardVideoAd.TAG, "onReward fail");
                    GNSAdapterAdColonyRewardVideoAd.this.didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(GNSAdapterAdColonyRewardVideoAd.TAG, 80001));
                }
            });
            this.listener = new h() {
                public void onRequestFilled(g gVar) {
                    GNSAdapterAdColonyRewardVideoAd.this.mLog.d(GNSAdapterAdColonyRewardVideoAd.TAG, "onRequestFilled");
                    GNSAdapterAdColonyRewardVideoAd.this.ad = gVar;
                    GNSAdapterAdColonyRewardVideoAd.this.canshow_ad = true;
                    GNSAdapterAdColonyRewardVideoAd.this.adapterDidReceiveRewardVideoAd(GNSAdapterAdColonyRewardVideoAd.this, GNSAdapterAdColonyRewardVideoAd.this.mRewardData);
                }

                public void onRequestNotFilled(m mVar) {
                    GNSAdapterAdColonyRewardVideoAd.this.mLog.d(GNSAdapterAdColonyRewardVideoAd.TAG, "onRequestNotFilled");
                    GNSAdapterAdColonyRewardVideoAd.this.didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(GNSAdapterAdColonyRewardVideoAd.TAG, 80011));
                }

                public void onOpened(g gVar) {
                    GNSAdapterAdColonyRewardVideoAd.this.mLog.d(GNSAdapterAdColonyRewardVideoAd.TAG, "onOpened");
                    GNSAdapterAdColonyRewardVideoAd.this.requestImp();
                }

                public void onExpiring(g gVar) {
                    GNSAdapterAdColonyRewardVideoAd.this.mLog.d(GNSAdapterAdColonyRewardVideoAd.TAG, "onExpiring");
                    b.a(GNSAdapterAdColonyRewardVideoAd.this.mZoneId, GNSAdapterAdColonyRewardVideoAd.this.listener);
                }
            };
            lastAppId = this.mAppId;
            lastZoneId = this.mZoneId;
            initSdkFlag = false;
            b.a(this.mZoneId, this.listener);
            this.mLog.d(TAG, "init");
        }
    }

    public String getAdnetworkName() {
        return TAG;
    }

    public void resume(Activity activity) {
        this.mLog.d(TAG, "resume");
    }

    public void pause() {
        this.mLog.d(TAG, "pause");
    }

    public void destroy() {
        this.mLog.d(TAG, "destroy");
    }

    public boolean isEnable() {
        try {
            boolean z = Class.forName("com.adcolony.sdk.b") != null;
            if (z) {
                return z;
            }
            this.mLog.b(TAG, "sdk not enable.");
            return z;
        } catch (ClassNotFoundException e) {
            this.mLog.f(TAG, "ClassNotFoundException AdColony");
            this.mLog.f(TAG, e.getMessage());
            return false;
        }
    }

    public GNSVideoRewardData getVideoRewardData() {
        return this.mRewardData;
    }

    public GNSAdapteeStatus getStatus() {
        if (canShow()) {
            this.mStatus = GNSAdapteeStatus.EXISTS;
        }
        return this.mStatus;
    }
}
