package jp.co.geniee.gnsrewardadapter;

import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import java.util.Map;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee.GNSAdapteeStatus;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoMediator;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardData;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardException;

public class GNSAdapterAppLovinRewardVideoAd extends GNSAdaptee {
    public static final String AD_NAME = "AppLovin";
    public static final String PACKAGE_NAME_COLUMN_NAME = "package_name";
    public static final String TAG = "AppLovin";
    private static boolean initSdkFlag = true;
    private static AppLovinIncentivizedInterstitial myIncentStatic;
    AppLovinAdClickListener mAdClickListener;
    AppLovinAdLoadListener mAdLoadListener;
    AppLovinAdVideoPlaybackListener mAdPlaybackListener;
    AppLovinAdRewardListener mAdRewardListener;
    AppLovinAdDisplayListener mDisplayListener;
    private boolean mIsAdReceived;
    private boolean mIsShown;
    private AppLovinIncentivizedInterstitial myIncent;

    AppLovinAdLoadListener getAdLoadListener() {
        if (this.mAdLoadListener == null) {
            this.mAdLoadListener = new AppLovinAdLoadListener() {
                public void adReceived(AppLovinAd appLovinAd) {
                    GNSAdapterAppLovinRewardVideoAd.this.mLog.d(GNSAdapterAppLovinRewardVideoAd.TAG, "preload.adReceived");
                    GNSAdapterAppLovinRewardVideoAd.this.mIsAdReceived = true;
                    GNSAdapterAppLovinRewardVideoAd.this.adapterDidReceiveRewardVideoAd(GNSAdapterAppLovinRewardVideoAd.this, GNSAdapterAppLovinRewardVideoAd.this.mRewardData);
                }

                public void failedToReceiveAd(int i) {
                    GNSAdapterAppLovinRewardVideoAd.this.mLog.c(GNSAdapterAppLovinRewardVideoAd.TAG, "preload.failedToReceiveAd");
                    GNSAdapterAppLovinRewardVideoAd.this.mIsAdReceived = false;
                    GNSAdapterAppLovinRewardVideoAd.this.didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(GNSAdapterAppLovinRewardVideoAd.TAG, 80001));
                }
            };
        }
        return this.mAdLoadListener;
    }

    private AppLovinAdRewardListener getAdRewardListener() {
        if (this.mAdRewardListener == null) {
            this.mAdRewardListener = new AppLovinAdRewardListener() {
                public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
                    GNSAdapterAppLovinRewardVideoAd.this.mLog.d(GNSAdapterAppLovinRewardVideoAd.TAG, "rewardListener.validationRequestFailed");
                }

                public void userRewardVerified(AppLovinAd appLovinAd, Map map) {
                    GNSAdapterAppLovinRewardVideoAd.this.mLog.d(GNSAdapterAppLovinRewardVideoAd.TAG, "rewardListener.userRewardVerified");
                }

                public void userRewardRejected(AppLovinAd appLovinAd, Map map) {
                    GNSAdapterAppLovinRewardVideoAd.this.mLog.d(GNSAdapterAppLovinRewardVideoAd.TAG, "rewardListener.userRewardRejected");
                }

                public void userOverQuota(AppLovinAd appLovinAd, Map map) {
                    GNSAdapterAppLovinRewardVideoAd.this.mLog.d(GNSAdapterAppLovinRewardVideoAd.TAG, "rewardListener.userOverQuota");
                }

                public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
                    GNSAdapterAppLovinRewardVideoAd.this.mLog.d(GNSAdapterAppLovinRewardVideoAd.TAG, "rewardListener.userDeclinedToViewAd");
                }
            };
        }
        return this.mAdRewardListener;
    }

    private AppLovinAdVideoPlaybackListener getAdPlaybackListener() {
        if (this.mAdPlaybackListener == null) {
            this.mAdPlaybackListener = new AppLovinAdVideoPlaybackListener() {
                public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
                    GNSAdapterAppLovinRewardVideoAd.this.mLog.d(GNSAdapterAppLovinRewardVideoAd.TAG, "playbackListener.videoPlaybackEnded");
                    if (((int) d) == 100 && z) {
                        GNSAdapterAppLovinRewardVideoAd.this.requestFinished();
                        GNSAdapterAppLovinRewardVideoAd.this.didRewardUserWithReward(GNSAdapterAppLovinRewardVideoAd.this, GNSAdapterAppLovinRewardVideoAd.this.mRewardData);
                        return;
                    }
                    GNSAdapterAppLovinRewardVideoAd.this.mLog.c(GNSAdapterAppLovinRewardVideoAd.TAG, "playbackListener.videoPlaybackEnded");
                }

                public void videoPlaybackBegan(AppLovinAd appLovinAd) {
                    GNSAdapterAppLovinRewardVideoAd.this.mLog.d(GNSAdapterAppLovinRewardVideoAd.TAG, "playbackListener.videoPlaybackBegan");
                }
            };
        }
        return this.mAdPlaybackListener;
    }

    private AppLovinAdDisplayListener getDisplayListener() {
        if (this.mDisplayListener == null) {
            this.mDisplayListener = new AppLovinAdDisplayListener() {
                public void adHidden(AppLovinAd appLovinAd) {
                    if (GNSAdapterAppLovinRewardVideoAd.this.myIncent != null) {
                        GNSAdapterAppLovinRewardVideoAd.this.myIncent.a(GNSAdapterAppLovinRewardVideoAd.this.getAdLoadListener());
                        GNSAdapterAppLovinRewardVideoAd.this.mIsShown = false;
                        GNSAdapterAppLovinRewardVideoAd.this.mLog.d(GNSAdapterAppLovinRewardVideoAd.TAG, "displayListener.adHidden");
                        GNSAdapterAppLovinRewardVideoAd.this.adapterDidCloseRewardVideoAd(GNSAdapterAppLovinRewardVideoAd.this, GNSAdapterAppLovinRewardVideoAd.this.mRewardData);
                    }
                }

                public void adDisplayed(AppLovinAd appLovinAd) {
                    GNSAdapterAppLovinRewardVideoAd.this.mIsShown = true;
                    GNSAdapterAppLovinRewardVideoAd.this.mLog.d(GNSAdapterAppLovinRewardVideoAd.TAG, "displayListener.adDisplayed");
                    GNSAdapterAppLovinRewardVideoAd.this.requestImp();
                }
            };
        }
        return this.mDisplayListener;
    }

    private AppLovinAdClickListener getAdClickListener() {
        if (this.mAdClickListener == null) {
            this.mAdClickListener = new AppLovinAdClickListener() {
                public void adClicked(AppLovinAd appLovinAd) {
                    GNSAdapterAppLovinRewardVideoAd.this.mLog.d(GNSAdapterAppLovinRewardVideoAd.TAG, "clickListener.adClicked");
                }
            };
        }
        return this.mAdClickListener;
    }

    public void preload() {
        if (!this.mIsShown && this.myIncent != null) {
            this.myIncent.a(getAdLoadListener());
        }
    }

    public boolean canShow() {
        boolean z = this.myIncent != null && this.mIsAdReceived && this.myIncent.a();
        this.mLog.d(TAG, "canShow prepared=" + z);
        return z;
    }

    public void show(GNSVideoMediator gNSVideoMediator) {
        this.mLog.d(TAG, "show");
        if (canShow()) {
            adapterDidStartPlayingRewardVideoAd(this, this.mRewardData);
            this.myIncent.a(this.mActivity, getAdRewardListener(), getAdPlaybackListener(), getDisplayListener(), getAdClickListener());
            this.mIsAdReceived = false;
        }
    }

    protected void setUpWorker() {
        this.mLog.d(TAG, "setUp");
        this.mRewardData = new GNSVideoRewardData(TAG);
        this.mRewardData.b = this.mType;
        this.mRewardData.c = this.mAmount;
        this.mIsShown = false;
        if (initSdkFlag) {
            AppLovinSdk.b(this.mActivity);
            this.myIncent = AppLovinIncentivizedInterstitial.a(this.mActivity);
            this.myIncent.a(this.mAsid);
            myIncentStatic = this.myIncent;
            initSdkFlag = false;
            this.mLog.d(TAG, "init");
            return;
        }
        this.myIncent = myIncentStatic;
    }

    public String getAdnetworkName() {
        return TAG;
    }

    public void destroy() {
        if (this.myIncent != null) {
            this.myIncent.b();
            this.myIncent = null;
        }
        this.mActivity = null;
    }

    public boolean isEnable() {
        try {
            boolean z = Class.forName("com.applovin.sdk.AppLovinSdk") != null;
            if (z) {
                return z;
            }
            this.mLog.b(TAG, "sdk not enable.");
            return z;
        } catch (ClassNotFoundException e) {
            this.mLog.f(TAG, "ClassNotFoundException AppLovin");
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
