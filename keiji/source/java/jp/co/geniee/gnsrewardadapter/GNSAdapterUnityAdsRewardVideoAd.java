package jp.co.geniee.gnsrewardadapter;

import android.app.Activity;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.UnityAds.FinishState;
import com.unity3d.ads.UnityAds.UnityAdsError;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee.GNSAdapteeStatus;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoMediator;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardData;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardException;

public class GNSAdapterUnityAdsRewardVideoAd extends GNSAdaptee {
    public static final String AD_NAME = "UnityAds";
    public static final String GAME_ID_COLUMN_NAME = "game_id";
    public static final String PLACEMENT_ID_COLUMN_NAME = "placement_id";
    public static final String TAG = "UnityAds";
    private static boolean initSdkFlag = true;
    private static String lastGameId;
    private IUnityAdsListener mAdsListener;
    private String mGameId;
    private String mPlacementId;

    private IUnityAdsListener getAdsListener() {
        if (this.mAdsListener == null) {
            this.mAdsListener = new IUnityAdsListener() {
                public void onUnityAdsReady(String str) {
                    GNSAdapterUnityAdsRewardVideoAd.this.mLog.d(GNSAdapterUnityAdsRewardVideoAd.TAG, "onUnityAdsReady placementId=" + str);
                    if (GNSAdapterUnityAdsRewardVideoAd.this.mPlacementId.equals(str)) {
                        GNSAdapterUnityAdsRewardVideoAd.this.adapterDidReceiveRewardVideoAd(GNSAdapterUnityAdsRewardVideoAd.this, GNSAdapterUnityAdsRewardVideoAd.this.mRewardData);
                    } else {
                        GNSAdapterUnityAdsRewardVideoAd.this.mLog.d(GNSAdapterUnityAdsRewardVideoAd.TAG, "onUnityAdsReady placement Mismatch");
                    }
                }

                public void onUnityAdsStart(String str) {
                    GNSAdapterUnityAdsRewardVideoAd.this.mLog.d(GNSAdapterUnityAdsRewardVideoAd.TAG, "onUnityAdsStart placementId=" + str);
                    GNSAdapterUnityAdsRewardVideoAd.this.requestImp();
                }

                public void onUnityAdsFinish(String str, FinishState finishState) {
                    if (finishState.equals(FinishState.COMPLETED)) {
                        GNSAdapterUnityAdsRewardVideoAd.this.mLog.d(GNSAdapterUnityAdsRewardVideoAd.TAG, "onUnityAdsFinish: placementId=" + str + " FinishState.COMPLETED");
                        GNSAdapterUnityAdsRewardVideoAd.this.requestFinished();
                        GNSAdapterUnityAdsRewardVideoAd.this.didRewardUserWithReward(GNSAdapterUnityAdsRewardVideoAd.this, GNSAdapterUnityAdsRewardVideoAd.this.mRewardData);
                        GNSAdapterUnityAdsRewardVideoAd.this.adapterDidCloseRewardVideoAd(GNSAdapterUnityAdsRewardVideoAd.this, GNSAdapterUnityAdsRewardVideoAd.this.mRewardData);
                    } else if (finishState.equals(FinishState.SKIPPED)) {
                        GNSAdapterUnityAdsRewardVideoAd.this.mLog.d(GNSAdapterUnityAdsRewardVideoAd.TAG, "onUnityAdsFinish: placementId=" + str + " FinishState.SKIPPED");
                    } else if (finishState.equals(FinishState.ERROR)) {
                        GNSAdapterUnityAdsRewardVideoAd.this.mLog.c(GNSAdapterUnityAdsRewardVideoAd.TAG, "onUnityAdsFinish: placementId=" + str + " FinishState.ERROR");
                        GNSAdapterUnityAdsRewardVideoAd.this.didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(GNSAdapterUnityAdsRewardVideoAd.TAG, 80031, "onUnityAdsFinish: placementId=" + str + " FinishState.ERROR"));
                    }
                }

                public void onUnityAdsError(UnityAdsError unityAdsError, String str) {
                    GNSAdapterUnityAdsRewardVideoAd.this.mLog.c(GNSAdapterUnityAdsRewardVideoAd.TAG, "onUnityAdsError: message=" + str);
                    GNSAdapterUnityAdsRewardVideoAd.this.didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(GNSAdapterUnityAdsRewardVideoAd.TAG, 80001, str));
                }
            };
        }
        return this.mAdsListener;
    }

    public boolean canShow() {
        boolean isReady = UnityAds.isReady();
        this.mLog.d(TAG, "canShow isReady=" + isReady);
        return isReady;
    }

    public void show(GNSVideoMediator gNSVideoMediator) {
        this.mLog.d(TAG, "show");
        if (canShow() && UnityAds.isReady()) {
            if (this.mPlacementId != null) {
                UnityAds.show(this.mActivity, this.mPlacementId);
            } else {
                UnityAds.show(this.mActivity);
            }
            adapterDidStartPlayingRewardVideoAd(this, this.mRewardData);
        }
    }

    protected void setUpWorker() {
        this.mLog.d(TAG, "setUp");
        this.mRewardData = new GNSVideoRewardData(TAG);
        this.mRewardData.b = this.mType;
        this.mRewardData.c = this.mAmount;
        this.mGameId = this.mOptions.getString(GAME_ID_COLUMN_NAME);
        this.mPlacementId = this.mOptions.getString(PLACEMENT_ID_COLUMN_NAME);
        this.mLog.d(TAG, "GameId=" + this.mGameId);
        this.mLog.d(TAG, "PlacementId=" + this.mPlacementId);
        if (initSdkFlag || !lastGameId.equals(this.mGameId)) {
            this.mLog.d(TAG, "TestMode=" + this.mIsTestMode);
            UnityAds.initialize(this.mActivity, this.mGameId, getAdsListener(), this.mIsTestMode);
            initSdkFlag = false;
            lastGameId = this.mGameId;
            this.mLog.d(TAG, "init");
        }
    }

    public String getAdnetworkName() {
        return TAG;
    }

    public void resume(Activity activity) {
        UnityAds.setListener(getAdsListener());
    }

    public void pause() {
    }

    public void destroy() {
    }

    public boolean isEnable() {
        try {
            boolean z = Class.forName("com.unity3d.ads.UnityAds") != null;
            if (z) {
                return z;
            }
            this.mLog.b(TAG, "sdk not enable.");
            return z;
        } catch (ClassNotFoundException e) {
            this.mLog.f(TAG, "ClassNotFoundException UnityAds");
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
