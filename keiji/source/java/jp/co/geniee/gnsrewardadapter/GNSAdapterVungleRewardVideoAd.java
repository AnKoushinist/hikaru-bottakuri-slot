package jp.co.geniee.gnsrewardadapter;

import android.app.Activity;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.VunglePub;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee.GNSAdapteeStatus;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoMediator;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardData;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardException;

public class GNSAdapterVungleRewardVideoAd extends GNSAdaptee {
    public static final String AD_NAME = "Vungle";
    public static final String APP_ID_COLUMN_NAME = "app_id";
    public static final String TAG = "Vungle";
    private String mAppId;
    private EventListener mVungleEventListener;
    final VunglePub vunglePub = VunglePub.getInstance();

    protected void setUpWorker() {
        this.mLog.d(TAG, "setUp");
        this.mRewardData = new GNSVideoRewardData(TAG);
        this.mRewardData.b = this.mType;
        this.mRewardData.c = this.mAmount;
        this.mAppId = this.mOptions.getString(APP_ID_COLUMN_NAME);
        this.mLog.d(TAG, "AppId=" + this.mAppId);
        this.vunglePub.init(this.mActivity, this.mAppId);
        this.vunglePub.clearEventListeners();
        this.vunglePub.addEventListeners(getEventListener());
    }

    public boolean isEnable() {
        try {
            boolean z = Class.forName("com.vungle.publisher.VunglePub") != null;
            if (z) {
                return z;
            }
            this.mLog.b(TAG, "sdk not enable.");
            return z;
        } catch (ClassNotFoundException e) {
            this.mLog.f(TAG, "ClassNotFoundException Vungle");
            this.mLog.f(TAG, e.getMessage());
            return false;
        }
    }

    public String getAdnetworkName() {
        return TAG;
    }

    public boolean canShow() {
        return this.vunglePub.isAdPlayable();
    }

    public void show(GNSVideoMediator gNSVideoMediator) {
        this.vunglePub.playAd();
    }

    public GNSVideoRewardData getVideoRewardData() {
        return this.mRewardData;
    }

    public void resume(Activity activity) {
        super.resume(activity);
        this.vunglePub.onResume();
    }

    public void pause() {
        super.pause();
        this.vunglePub.onPause();
    }

    private EventListener getEventListener() {
        if (this.mVungleEventListener == null) {
            this.mVungleEventListener = new EventListener() {
                public void onAdEnd(boolean z, boolean z2) {
                    GNSAdapterVungleRewardVideoAd.this.mLog.e(GNSAdapterVungleRewardVideoAd.TAG, "onAdEnd wasSuccessfulView=" + z);
                    if (z) {
                        GNSAdapterVungleRewardVideoAd.this.didRewardUserWithReward(GNSAdapterVungleRewardVideoAd.this, GNSAdapterVungleRewardVideoAd.this.mRewardData);
                        GNSAdapterVungleRewardVideoAd.this.requestFinished();
                    }
                    GNSAdapterVungleRewardVideoAd.this.adapterDidCloseRewardVideoAd(GNSAdapterVungleRewardVideoAd.this, GNSAdapterVungleRewardVideoAd.this.mRewardData);
                }

                public void onAdStart() {
                    GNSAdapterVungleRewardVideoAd.this.mLog.e(GNSAdapterVungleRewardVideoAd.TAG, "onAdStart");
                    GNSAdapterVungleRewardVideoAd.this.requestImp();
                    GNSAdapterVungleRewardVideoAd.this.adapterDidStartPlayingRewardVideoAd(GNSAdapterVungleRewardVideoAd.this, GNSAdapterVungleRewardVideoAd.this.mRewardData);
                }

                public void onAdUnavailable(String str) {
                    GNSAdapterVungleRewardVideoAd.this.mLog.e(GNSAdapterVungleRewardVideoAd.TAG, "onAdUnavailable reason= " + str);
                    GNSAdapterVungleRewardVideoAd.this.didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(GNSAdapterVungleRewardVideoAd.TAG, 80011, "There is no ad to play yet"));
                }

                public void onAdPlayableChanged(boolean z) {
                    GNSAdapterVungleRewardVideoAd.this.mLog.d(GNSAdapterVungleRewardVideoAd.TAG, "onAdPlayableChanged changed=" + z);
                    if (z) {
                        GNSAdapterVungleRewardVideoAd.this.adapterDidReceiveRewardVideoAd(GNSAdapterVungleRewardVideoAd.this, GNSAdapterVungleRewardVideoAd.this.mRewardData);
                    } else {
                        GNSAdapterVungleRewardVideoAd.this.didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(GNSAdapterVungleRewardVideoAd.TAG, 80011));
                    }
                }

                public void onVideoView(boolean z, int i, int i2) {
                }
            };
        }
        return this.mVungleEventListener;
    }

    public GNSAdapteeStatus getStatus() {
        if (canShow()) {
            this.mStatus = GNSAdapteeStatus.EXISTS;
        }
        return this.mStatus;
    }
}
