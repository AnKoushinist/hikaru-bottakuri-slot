package jp.co.geniee.gnadsdk.rewardvideo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Iterator;
import org.cocos2dx.lib.BuildConfig;

public abstract class GNSAdaptee {
    public static final String TAG = "Adaptee";
    private static final long TIMEOUT_MS = 5000;
    private static final String WORKER_NAME_PREFIX = "GNSAdapter";
    private static final String WORKER_NAME_SUFFIX = "RewardVideoAd";
    protected Activity mActivity;
    private GNSAdapteeListener mAdapteeListener;
    protected double mAmount;
    protected String mAsid;
    protected String mGaid;
    protected ArrayList<String> mImpUrls;
    protected boolean mIsTestMode;
    protected GNSRewardVideoAdListener mListener;
    protected GNSLogger mLog;
    protected Bundle mOptions;
    protected GNSVideoRewardData mRewardData;
    protected GNSAdapteeStatus mStatus = GNSAdapteeStatus.INIT;
    private Handler mTimeoutHandler = new Handler();
    protected String mType;
    protected String mUserAgent;
    protected String mZoneId;

    public interface GNSAdapteeListener {
        void a(GNSAdaptee gNSAdaptee, GNSVideoRewardData gNSVideoRewardData);

        void a(GNSVideoRewardException gNSVideoRewardException);

        void b(GNSAdaptee gNSAdaptee, GNSVideoRewardData gNSVideoRewardData);

        void c(GNSAdaptee gNSAdaptee, GNSVideoRewardData gNSVideoRewardData);

        void d(GNSAdaptee gNSAdaptee, GNSVideoRewardData gNSVideoRewardData);
    }

    public enum GNSAdapteeStatus {
        INIT,
        EXISTS,
        TIMEOUT
    }

    public abstract boolean canShow();

    public abstract String getAdnetworkName();

    public abstract GNSAdapteeStatus getStatus();

    public abstract GNSVideoRewardData getVideoRewardData();

    public abstract boolean isEnable();

    protected abstract void setUpWorker();

    public abstract void show(GNSVideoMediator gNSVideoMediator);

    static GNSAdaptee createWorker(String str) {
        String str2 = BuildConfig.FLAVOR;
        GNSLogger a = GNSLogger.a();
        String str3;
        try {
            str2 = "jp.co.geniee.gnsrewardadapter.GNSAdapter" + str + WORKER_NAME_SUFFIX;
            Object newInstance = Class.forName(str2).newInstance();
            if (newInstance instanceof GNSAdaptee) {
                return (GNSAdaptee) newInstance;
            }
            str3 = str2;
            a.c(TAG, str + ":" + str3 + "\u306e\u8aad\u307f\u8fbc\u307f\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002");
            return null;
        } catch (Exception e) {
            Exception exception = e;
            str3 = str2;
            a.c(TAG, str + ":adaptee:" + exception.getMessage());
        }
    }

    public void setAdapterWorkerListener(GNSAdapteeListener gNSAdapteeListener) {
        this.mAdapteeListener = gNSAdapteeListener;
    }

    public void setRewardVideoAdListener(GNSRewardVideoAdListener gNSRewardVideoAdListener) {
        this.mListener = gNSRewardVideoAdListener;
    }

    public void setUp(Activity activity, String str, GNSZoneInfo gNSZoneInfo, GNSZoneInfoSource gNSZoneInfoSource, String str2) {
        this.mActivity = activity;
        this.mZoneId = str;
        this.mOptions = gNSZoneInfoSource.a();
        this.mUserAgent = str2;
        this.mAsid = gNSZoneInfoSource.j;
        this.mGaid = GNSPrefUtil.d(this.mActivity);
        this.mIsTestMode = GNSEnv.a().d();
        this.mImpUrls = gNSZoneInfoSource.m;
        this.mType = gNSZoneInfo.c;
        this.mAmount = gNSZoneInfo.d;
        this.mLog = GNSLogger.a();
        setUpWorker();
        timeoutSetHandler();
        this.mStatus = GNSAdapteeStatus.INIT;
    }

    private void timeoutSetHandler() {
        this.mLog.d(TAG, getVideoRewardData().a + ":timeoutSetHandler\u8a2d\u5b9a " + 5 + "\u79d2\u5f8c\u306btimeout");
        this.mTimeoutHandler.postDelayed(new Runnable(this) {
            final /* synthetic */ GNSAdaptee a;

            {
                this.a = r1;
            }

            public void run() {
                GNSAdapteeStatus status = this.a.getStatus();
                this.a.mLog.d(GNSAdaptee.TAG, this.a.getVideoRewardData().a + ":timeout\u5224\u5b9a status=" + status);
                if (status == GNSAdapteeStatus.INIT) {
                    this.a.notifyTimeout();
                } else {
                    this.a.mLog.d(GNSAdaptee.TAG, this.a.getVideoRewardData().a + ":timeout\u51e6\u7406\u306a\u3057");
                }
            }
        }, TIMEOUT_MS);
    }

    private void notifyTimeout() {
        this.mLog.d(TAG, getVideoRewardData().a + ":timeout\u51e6\u7406");
        this.mStatus = GNSAdapteeStatus.TIMEOUT;
        didFailToLoadRewardVideoAdwithError(new GNSVideoRewardException(getVideoRewardData().a, 81001));
    }

    protected void requestFinished() {
        new Thread(new Runnable(this) {
            final /* synthetic */ GNSAdaptee a;

            {
                this.a = r1;
            }

            public void run() {
                GNSAladdinApiUtil.a(this.a.mZoneId, this.a.mAsid, this.a.mLog, this.a.mUserAgent, this.a.mGaid, BuildConfig.FLAVOR);
            }
        }).start();
    }

    protected void requestImp() {
        Iterator it = this.mImpUrls.iterator();
        while (it.hasNext()) {
            final String str = (String) it.next();
            new Thread(new Runnable(this) {
                final /* synthetic */ GNSAdaptee b;

                public void run() {
                    GNSAladdinApiUtil.a(this.b.mActivity, this.b.mZoneId, this.b.mAsid, this.b.mLog, this.b.mUserAgent, this.b.mGaid, BuildConfig.FLAVOR, BuildConfig.FLAVOR, str);
                }
            }).start();
        }
        GNSVideoStartCnt.a(this.mActivity, ((long) GNSVideoTerm.c()) * 60000);
    }

    protected void adapterDidReceiveRewardVideoAd(GNSAdaptee gNSAdaptee, GNSVideoRewardData gNSVideoRewardData) {
        if (this.mAdapteeListener != null) {
            this.mLog.d(TAG, getVideoRewardData().a + ":\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u6210\u529f");
            this.mAdapteeListener.a(gNSAdaptee, gNSVideoRewardData);
        }
    }

    protected void didFailToLoadRewardVideoAdwithError(GNSVideoRewardException gNSVideoRewardException) {
        if (this.mAdapteeListener != null) {
            this.mLog.d(TAG, getVideoRewardData().a + ":\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u5931\u6557\u3057\u305f " + gNSVideoRewardException.b() + ":" + gNSVideoRewardException.getMessage());
            this.mAdapteeListener.a(gNSVideoRewardException);
        }
    }

    protected void adapterDidStartPlayingRewardVideoAd(final GNSAdaptee gNSAdaptee, final GNSVideoRewardData gNSVideoRewardData) {
        if (this.mActivity != null && this.mAdapteeListener != null) {
            this.mActivity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ GNSAdaptee c;

                public void run() {
                    if (this.c.mAdapteeListener != null) {
                        this.c.mAdapteeListener.b(gNSAdaptee, gNSVideoRewardData);
                    }
                }
            });
        }
    }

    protected void didRewardUserWithReward(final GNSAdaptee gNSAdaptee, final GNSVideoRewardData gNSVideoRewardData) {
        if (this.mActivity != null && this.mAdapteeListener != null) {
            this.mActivity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ GNSAdaptee c;

                public void run() {
                    if (this.c.mAdapteeListener != null) {
                        this.c.mAdapteeListener.c(gNSAdaptee, gNSVideoRewardData);
                    }
                }
            });
        }
    }

    protected void adapterDidCloseRewardVideoAd(final GNSAdaptee gNSAdaptee, final GNSVideoRewardData gNSVideoRewardData) {
        if (this.mActivity != null && this.mAdapteeListener != null) {
            this.mActivity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ GNSAdaptee c;

                public void run() {
                    if (this.c.mAdapteeListener != null) {
                        this.c.mAdapteeListener.d(gNSAdaptee, gNSVideoRewardData);
                    }
                }
            });
        }
    }

    public void preload() {
    }

    public void start() {
    }

    public void resume(Activity activity) {
    }

    public void pause() {
    }

    public void stop() {
    }

    public void destroy() {
    }
}
