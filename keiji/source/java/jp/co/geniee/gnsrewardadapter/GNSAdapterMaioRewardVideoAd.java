package jp.co.geniee.gnsrewardadapter;

import android.text.TextUtils;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee.GNSAdapteeStatus;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoMediator;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardData;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardException;
import jp.maio.sdk.android.FailNotificationReason;
import jp.maio.sdk.android.MaioAds;
import jp.maio.sdk.android.MaioAdsListener;

public class GNSAdapterMaioRewardVideoAd extends GNSAdaptee {
    public static final String AD_NAME = "Maio";
    public static final String MEDIA_ID_COLUMN_NAME = "media_id";
    public static final String SPOT_ID_COLUMN_NAME = "spot_id";
    public static final String TAG = "Maio";
    private static boolean initSdkFlag = true;
    private static String lastMediaId;
    private MaioAdsListener mMaioAdsListener;
    private String mSpotId;

    private MaioAdsListener getMaioAdsListener() {
        if (this.mMaioAdsListener == null) {
            this.mMaioAdsListener = new MaioAdsListener() {
                public void onInitialized() {
                    GNSAdapterMaioRewardVideoAd.this.mLog.d(GNSAdapterMaioRewardVideoAd.TAG, "onInitialized");
                }

                public void onChangedCanShow(String str, boolean z) {
                    GNSAdapterMaioRewardVideoAd.this.mLog.d(GNSAdapterMaioRewardVideoAd.TAG, "onChangedCanShow: zoneEid:" + str + ", value:" + z);
                    if (z) {
                        GNSAdapterMaioRewardVideoAd.this.adapterDidReceiveRewardVideoAd(GNSAdapterMaioRewardVideoAd.this, GNSAdapterMaioRewardVideoAd.this.mRewardData);
                    } else {
                        GNSAdapterMaioRewardVideoAd.this.didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(GNSAdapterMaioRewardVideoAd.TAG, 80011));
                    }
                }

                public void onOpenAd(String str) {
                    GNSAdapterMaioRewardVideoAd.this.mLog.d(GNSAdapterMaioRewardVideoAd.TAG, "onOpenAd: zoneEid:" + str);
                    GNSAdapterMaioRewardVideoAd.this.requestImp();
                }

                public void onStartedAd(String str) {
                    GNSAdapterMaioRewardVideoAd.this.mLog.d(GNSAdapterMaioRewardVideoAd.TAG, "onStartedAd: zoneEid:" + str);
                    GNSAdapterMaioRewardVideoAd.this.adapterDidStartPlayingRewardVideoAd(GNSAdapterMaioRewardVideoAd.this, GNSAdapterMaioRewardVideoAd.this.mRewardData);
                }

                public void onFinishedAd(int i, boolean z, int i2, String str) {
                    GNSAdapterMaioRewardVideoAd.this.mLog.d(GNSAdapterMaioRewardVideoAd.TAG, "onFinishedAd: zoneEid:" + str);
                    if (z) {
                        GNSAdapterMaioRewardVideoAd.this.mLog.d(GNSAdapterMaioRewardVideoAd.TAG, "onFinishedAd: zoneEid:" + str + " skipped");
                        return;
                    }
                    GNSAdapterMaioRewardVideoAd.this.requestFinished();
                    GNSAdapterMaioRewardVideoAd.this.didRewardUserWithReward(GNSAdapterMaioRewardVideoAd.this, GNSAdapterMaioRewardVideoAd.this.mRewardData);
                }

                public void onFailed(FailNotificationReason failNotificationReason, String str) {
                    GNSAdapterMaioRewardVideoAd.this.mLog.c(GNSAdapterMaioRewardVideoAd.TAG, "onFailed: reason:" + failNotificationReason.name() + " ,zoneEid:" + str);
                    GNSAdapterMaioRewardVideoAd.this.didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(GNSAdapterMaioRewardVideoAd.TAG, 80001, "MaioAdsListener.onFailed: reason:" + failNotificationReason.name() + " ,zoneEid:" + str));
                }

                public void onClickedAd(String str) {
                    GNSAdapterMaioRewardVideoAd.this.mLog.d(GNSAdapterMaioRewardVideoAd.TAG, "onClickedAd: zoneEid:" + str);
                }

                public void onClosedAd(String str) {
                    GNSAdapterMaioRewardVideoAd.this.mLog.d(GNSAdapterMaioRewardVideoAd.TAG, "onClosedAd: zoneEid:" + str);
                    GNSAdapterMaioRewardVideoAd.this.adapterDidCloseRewardVideoAd(GNSAdapterMaioRewardVideoAd.this, GNSAdapterMaioRewardVideoAd.this.mRewardData);
                }
            };
        }
        return this.mMaioAdsListener;
    }

    public boolean canShow() {
        boolean canShow = MaioAds.canShow(this.mSpotId);
        this.mLog.d(TAG, "canShow " + canShow);
        return canShow;
    }

    public void show(GNSVideoMediator gNSVideoMediator) {
        this.mLog.d(TAG, "show");
        if (canShow()) {
            MaioAds.show(this.mSpotId);
        }
    }

    protected void setUpWorker() {
        this.mLog.d(TAG, "setUp");
        this.mRewardData = new GNSVideoRewardData(TAG);
        this.mRewardData.b = this.mType;
        this.mRewardData.c = this.mAmount;
        this.mLog.d(TAG, "TestMode=" + this.mIsTestMode);
        MaioAds.setAdTestMode(this.mIsTestMode);
        String string = this.mOptions.getString(MEDIA_ID_COLUMN_NAME);
        this.mSpotId = this.mOptions.getString(SPOT_ID_COLUMN_NAME);
        if (this.mSpotId != null && TextUtils.isEmpty(this.mSpotId.trim())) {
            this.mSpotId = null;
        }
        this.mLog.d(TAG, "mediaId=" + string);
        this.mLog.d(TAG, "SpotId=" + this.mSpotId);
        if (initSdkFlag || !string.equals(lastMediaId)) {
            MaioAds.init(this.mActivity, string, getMaioAdsListener());
            initSdkFlag = false;
            lastMediaId = string;
            this.mLog.d(TAG, "init");
        }
        MaioAds.setMaioAdsListener(getMaioAdsListener());
    }

    public String getAdnetworkName() {
        return TAG;
    }

    public boolean isEnable() {
        try {
            boolean z = Class.forName("jp.maio.sdk.android.MaioAds") != null;
            if (z) {
                return z;
            }
            this.mLog.b(TAG, "sdk not enable.");
            return z;
        } catch (ClassNotFoundException e) {
            this.mLog.f(TAG, "ClassNotFoundException Maio");
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
