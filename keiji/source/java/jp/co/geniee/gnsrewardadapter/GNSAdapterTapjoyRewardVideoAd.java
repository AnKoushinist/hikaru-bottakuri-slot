package jp.co.geniee.gnsrewardadapter;

import android.app.Activity;
import com.tapjoy.TJActionRequest;
import com.tapjoy.TJConnectListener;
import com.tapjoy.TJError;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementVideoListener;
import com.tapjoy.Tapjoy;
import com.tapjoy.TapjoyConnectFlag;
import com.tapjoy.TapjoyConstants;
import java.util.Hashtable;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee.GNSAdapteeStatus;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoMediator;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardData;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardException;

public class GNSAdapterTapjoyRewardVideoAd extends GNSAdaptee implements TJConnectListener, TJPlacementListener, TJPlacementVideoListener {
    public static final String AD_NAME = "Tapjoy";
    public static final String PLACEMENT_ID_COLUMN_NAME = "placement_id";
    public static final String SDK_KEY_COLUMN_NAME = "sdk_key";
    public static final String TAG = "Tapjoy";
    Hashtable<String, Object> connectFlags = new Hashtable();
    boolean isRequested = false;
    private String mPlacementId;
    private String mSDKKey;
    private TJPlacement p = null;

    protected void setUpWorker() {
        this.mLog.d(TAG, "setUp");
        this.mRewardData = new GNSVideoRewardData(TAG);
        this.mRewardData.b = this.mType;
        this.mRewardData.c = this.mAmount;
        this.mPlacementId = this.mOptions.getString(PLACEMENT_ID_COLUMN_NAME);
        this.mSDKKey = this.mOptions.getString(SDK_KEY_COLUMN_NAME);
        this.mLog.d(TAG, "PlacementId=" + this.mPlacementId);
        this.mLog.d(TAG, "SDKKey=" + this.mSDKKey);
    }

    public boolean isEnable() {
        try {
            boolean z = Class.forName("com.tapjoy.Tapjoy") != null;
            if (z) {
                return z;
            }
            this.mLog.b(TAG, "sdk not enable.");
            return z;
        } catch (ClassNotFoundException e) {
            this.mLog.f(TAG, "ClassNotFoundException Tapjoy");
            this.mLog.f(TAG, e.getMessage());
            return false;
        }
    }

    public String getAdnetworkName() {
        return TAG;
    }

    public boolean canShow() {
        boolean z = false;
        this.mLog.d(TAG, "isRequested =" + this.isRequested);
        if (this.isRequested) {
            z = this.p.isContentReady();
        }
        this.mLog.d(TAG, "canShow =" + z);
        return z;
    }

    public void show(GNSVideoMediator gNSVideoMediator) {
        this.mLog.d(TAG, "show video");
        if (this.p.isContentReady()) {
            this.p.showContent();
        }
    }

    public GNSVideoRewardData getVideoRewardData() {
        return this.mRewardData;
    }

    public void preload() {
        super.preload();
        if (Tapjoy.isConnected()) {
            this.mLog.d(TAG, "already connected");
            requestAd();
            return;
        }
        this.mLog.d(TAG, "not connected");
        this.mLog.d(TAG, "TestMode=" + this.mIsTestMode);
        if (this.mIsTestMode) {
            Tapjoy.setDebugEnabled(true);
            this.connectFlags.put(TapjoyConnectFlag.ENABLE_LOGGING, TapjoyConstants.TJC_TRUE);
        }
        Tapjoy.setActivity(this.mActivity);
        Tapjoy.connect(this.mActivity.getApplicationContext(), this.mSDKKey, this.connectFlags, this);
    }

    public void resume(Activity activity) {
        super.resume(activity);
        if (!Tapjoy.isConnected()) {
            Tapjoy.setActivity(this.mActivity);
            Tapjoy.connect(this.mActivity.getApplicationContext(), this.mSDKKey, this.connectFlags, this);
        }
    }

    public void onConnectSuccess() {
        this.mLog.d(TAG, "onConnectSuccess");
        requestAd();
    }

    public void onConnectFailure() {
        this.mLog.c(TAG, "onConnectFailure");
        didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(TAG, 80001));
    }

    public void onRequestSuccess(TJPlacement tJPlacement) {
        this.mLog.d(TAG, "onRequestSuccess");
    }

    public void onRequestFailure(TJPlacement tJPlacement, TJError tJError) {
        this.mLog.c(TAG, "onRequestFailure");
        didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(TAG, 89001, tJError.message));
    }

    public void onContentReady(TJPlacement tJPlacement) {
        this.mLog.d(TAG, "content is ready");
        if (this.p.isContentAvailable()) {
            this.mLog.d(TAG, "There is some content for this placement");
            adapterDidReceiveRewardVideoAd(this, this.mRewardData);
            return;
        }
        this.mLog.d(TAG, "There is no content for this placement");
        this.isRequested = false;
        didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(TAG, 80011));
    }

    public void onContentShow(TJPlacement tJPlacement) {
        this.mLog.d(TAG, "content is showing");
    }

    public void onContentDismiss(TJPlacement tJPlacement) {
        this.mLog.d(TAG, "onContentDismiss");
        adapterDidCloseRewardVideoAd(this, this.mRewardData);
    }

    public void onPurchaseRequest(TJPlacement tJPlacement, TJActionRequest tJActionRequest, String str) {
    }

    public void onRewardRequest(TJPlacement tJPlacement, TJActionRequest tJActionRequest, String str, int i) {
        this.mLog.d(TAG, "onRewardRequest type=" + str + " amount=" + i);
    }

    public void onVideoStart(TJPlacement tJPlacement) {
        this.mLog.d(TAG, "onVideoStart");
        requestImp();
        adapterDidStartPlayingRewardVideoAd(this, this.mRewardData);
        this.isRequested = false;
    }

    public void onVideoError(TJPlacement tJPlacement, String str) {
        this.mLog.c(TAG, "onVideoError: " + str);
    }

    public void onVideoComplete(TJPlacement tJPlacement) {
        this.mLog.d(TAG, "onVideoComplete");
        didRewardUserWithReward(this, this.mRewardData);
        requestFinished();
        this.mRewardData.c = 0.0d;
    }

    private void requestAd() {
        if (this.p == null) {
            this.mLog.d(TAG, "requestAd PlacementId=" + this.mPlacementId);
            this.p = Tapjoy.getPlacement(this.mPlacementId, this);
            this.p.setVideoListener(this);
            this.p.setMediationName("geniee");
            this.p.setAdapterVersion("1.0.0");
        }
        this.isRequested = true;
        this.p.requestContent();
    }

    public GNSAdapteeStatus getStatus() {
        if (canShow()) {
            this.mStatus = GNSAdapteeStatus.EXISTS;
        }
        return this.mStatus;
    }
}
