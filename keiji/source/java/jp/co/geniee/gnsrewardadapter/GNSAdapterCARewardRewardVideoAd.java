package jp.co.geniee.gnsrewardadapter;

import android.app.Activity;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.LinkedHashMap;
import java.util.Map;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee;
import jp.co.geniee.gnadsdk.rewardvideo.GNSAdaptee.GNSAdapteeStatus;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoMediator;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardData;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardException;
import jp.co.mediasdk.a;
import jp.co.mediasdk.d;
import jp.co.mediasdk.mscore.listener.pva.MSPVAListener;

public class GNSAdapterCARewardRewardVideoAd extends GNSAdaptee implements MSPVAListener {
    public static final String AD_NAME = "CAReward";
    public static final String MEDIA_ID_COLUMN_NAME = "m_id";
    public static final String SDK_KEY_COLUMN_NAME = "sdk_key";
    public static final String TAG = "CAReward";
    private boolean canShow = false;
    private boolean initialized = false;
    private String mMediaId;
    private String mSDKKey;

    protected void setUpWorker() {
        this.mLog.d(TAG, "setUp");
        this.mRewardData = new GNSVideoRewardData(TAG);
        this.mRewardData.b = this.mType;
        this.mRewardData.c = this.mAmount;
        this.mMediaId = this.mOptions.getString(MEDIA_ID_COLUMN_NAME);
        this.mSDKKey = this.mOptions.getString(SDK_KEY_COLUMN_NAME);
        this.mLog.d(TAG, "MediaId=" + this.mMediaId);
        this.mLog.d(TAG, "SDKKey=" + this.mSDKKey);
        initialize();
    }

    public boolean isEnable() {
        try {
            boolean z = Class.forName("jp.co.mediasdk.a") != null;
            if (z) {
                return z;
            }
            this.mLog.b(TAG, "sdk not enable.");
            return z;
        } catch (ClassNotFoundException e) {
            this.mLog.f(TAG, "ClassNotFoundException CAReward");
            this.mLog.f(TAG, e.getMessage());
            return false;
        }
    }

    public String getAdnetworkName() {
        return TAG;
    }

    public boolean canShow() {
        return this.canShow;
    }

    public void show(GNSVideoMediator gNSVideoMediator) {
        if (this.canShow) {
            d.a(this.mActivity);
        }
    }

    public GNSVideoRewardData getVideoRewardData() {
        return this.mRewardData;
    }

    public void preload() {
        super.preload();
        initialize();
        d.d();
    }

    public void resume(Activity activity) {
        super.resume(activity);
        d.b();
    }

    public void pause() {
        super.pause();
        d.c();
    }

    public void onPVAMessage(String str) {
        this.mLog.d(TAG, "onPVAMessage msg=" + str);
        Map parseParameter = parseParameter(str);
        if (parseParameter.size() > 0 && parseParameter.containsKey(MoatAdEvent.EVENT_TYPE)) {
            String str2 = (String) parseParameter.get(MoatAdEvent.EVENT_TYPE);
            if (str2.equals("ad_available")) {
                if (((String) parseParameter.get("status")).equals("ok")) {
                    this.mLog.d(TAG, "Ad was availabe");
                    this.canShow = true;
                    adapterDidReceiveRewardVideoAd(this, this.mRewardData);
                    return;
                }
                this.mLog.d(TAG, "No Ad was availabe");
                this.canShow = false;
                didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(TAG, 80011));
            } else if (str2.equals("video_start")) {
                str2 = (String) parseParameter.get("status");
                requestImp();
                if (str2.equals("ok")) {
                    adapterDidStartPlayingRewardVideoAd(this, this.mRewardData);
                    return;
                }
                this.mLog.c(TAG, "Video start failed");
                this.canShow = false;
                didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(TAG, 80021));
            } else if (str2.equals("video_end")) {
                if (((String) parseParameter.get("status")).equals("ok")) {
                    this.mLog.d(TAG, "Video end ok");
                    return;
                }
                this.mLog.c(TAG, "Video end failed");
                this.canShow = false;
                didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(TAG, 80031));
            } else if (str2.equals("incentive")) {
                if (((String) parseParameter.get("status")).equals("ok")) {
                    didRewardUserWithReward(this, this.mRewardData);
                    requestFinished();
                    this.mRewardData.c = 0.0d;
                } else {
                    this.mLog.c(TAG, "Not receive reward??");
                    didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(TAG, 80041));
                }
                this.canShow = false;
            } else if (str2.equals(String.CLOSE)) {
                adapterDidCloseRewardVideoAd(this, this.mRewardData);
                this.canShow = false;
            }
        }
    }

    private void initialize() {
        if (!this.initialized) {
            a.a(this.mActivity.getApplicationContext());
            a.a(MEDIA_ID_COLUMN_NAME, this.mMediaId);
            a.a("sdk_token", this.mSDKKey);
            a.a("media_user_id", d.e());
            a.a("placement", "placement_1");
            d.a((MSPVAListener) this);
            this.initialized = true;
            this.mLog.d(TAG, "TestMode=" + this.mIsTestMode);
            if (this.mIsTestMode) {
                a.a();
            }
            this.mLog.d(TAG, "init");
        }
    }

    private Map<String, String> parseParameter(String str) {
        Map<String, String> linkedHashMap = new LinkedHashMap();
        for (String str2 : str.split("&")) {
            int indexOf = str2.indexOf("=");
            if (indexOf > 0) {
                linkedHashMap.put(str2.substring(0, indexOf), str2.substring(indexOf + 1));
            }
        }
        return linkedHashMap;
    }

    public GNSAdapteeStatus getStatus() {
        if (canShow()) {
            this.mStatus = GNSAdapteeStatus.EXISTS;
        }
        return this.mStatus;
    }
}
