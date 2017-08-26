package jp.co.vaz.creator.hikaru2;

import android.app.AlarmManager;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.igaworks.IgawCommon;
import com.igaworks.adbrix.IgawAdbrix;
import com.onesignal.OneSignal;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import jp.co.geniee.a.a.a.a;
import jp.co.geniee.a.a.a.b;
import jp.co.geniee.a.a.a.c;
import jp.co.geniee.gnadsdk.rewardvideo.GNSRewardVideoAd;
import jp.co.geniee.gnadsdk.rewardvideo.GNSRewardVideoAdListener;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardData;
import jp.co.geniee.gnadsdk.rewardvideo.GNSVideoRewardException;
import jp.co.vaz.creator.hikaru2.InAppPurchase.util.IabBroadcastReceiver;
import jp.co.vaz.creator.hikaru2.InAppPurchase.util.IabBroadcastReceiver.IabBroadcastListener;
import jp.co.vaz.creator.hikaru2.InAppPurchase.util.IabException;
import jp.co.vaz.creator.hikaru2.InAppPurchase.util.IabHelper;
import jp.co.vaz.creator.hikaru2.InAppPurchase.util.IabHelper.OnIabPurchaseFinishedListener;
import jp.co.vaz.creator.hikaru2.InAppPurchase.util.IabHelper.OnIabSetupFinishedListener;
import jp.co.vaz.creator.hikaru2.InAppPurchase.util.IabHelper.QueryInventoryFinishedListener;
import jp.co.vaz.creator.hikaru2.InAppPurchase.util.IabResult;
import jp.co.vaz.creator.hikaru2.InAppPurchase.util.Inventory;
import jp.co.vaz.creator.hikaru2.InAppPurchase.util.Purchase;
import org.cocos2dx.lib.BuildConfig;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxHelper;
import org.cocos2dx.lib.GameControllerDelegate;
import org.json.JSONException;
import org.json.JSONObject;
import twitter4j.TwitterResponse;

public class AppActivity extends Cocos2dxActivity implements LocationListener, c, IabBroadcastListener {
    private static final int AD_CB_TYPE_ADSTIR_VIDEO_ON_FAILED = 101;
    private static final int AD_CB_TYPE_ADSTIR_VIDEO_ON_FINISHED = 104;
    private static final int AD_CB_TYPE_ADSTIR_VIDEO_ON_LOAD = 100;
    private static final int AD_CB_TYPE_ADSTIR_VIDEO_ON_REWARD = 105;
    private static final int AD_CB_TYPE_ADSTIR_VIDEO_ON_REWARD_CANCELED = 106;
    private static final int AD_CB_TYPE_ADSTIR_VIDEO_ON_START = 102;
    private static final int AD_CB_TYPE_ADSTIR_VIDEO_ON_START_FAILED = 103;
    private static final int AD_CB_TYPE_ADS_DID_COMPLETE_SHOW = 102;
    private static final int AD_CB_TYPE_ADS_DID_HIDE = 103;
    private static final int AD_CB_TYPE_ADS_DID_SHOW = 101;
    private static final int AD_CB_TYPE_ADS_FETCH_COMPLETED = 100;
    private static final int AD_CB_TYPE_ADS_PLAY_FAILED = 104;
    static final int RC_REQUEST = 10000;
    private static String _bannerAdId = null;
    private static String _curId = null;
    private static String _instiId = null;
    private static a _nativeAd = null;
    private static String _unitId = null;
    private static LocationManager locationManager = null;
    private static ProgressDialog mLoadingIndicator = null;
    static List<String> mProductIds = null;
    private static PublisherInterstitialAd mPublisherInterstitialAd = null;
    static Map<String, Boolean> mPurchasedProducts = null;
    private static GNSRewardVideoAd mReward = null;
    public static boolean mShareConFirst = false;
    private static AppActivity me = null;
    private static b nativeAdRequest = null;
    static String requestPurchaseId = null;
    public static final String shareIndicator = "\u6295\u7a3f\u51e6\u7406\u4e2d...";
    private PublisherAdView bannerView;
    private PublisherAdView interstitialView;
    private Builder mAlertDialogBuilder;
    IabBroadcastReceiver mBroadcastReceiver;
    private QueryInventoryFinishedListener mGotInventoryListener = new QueryInventoryFinishedListener(this) {
        final /* synthetic */ AppActivity a;

        {
            this.a = r1;
        }

        public void a(IabResult iabResult, Inventory inventory) {
            if (this.a.mPurchaseHelper != null && !iabResult.d()) {
                String str;
                for (Entry key : AppActivity.mPurchasedProducts.entrySet()) {
                    str = (String) key.getKey();
                    AppActivity.mPurchasedProducts.put(str, Boolean.valueOf(inventory.c(str)));
                }
                for (String str2 : AppActivity.mProductIds) {
                    if (inventory.c(str2)) {
                        AppActivity.execVerifyReceiptCB(str2, inventory.b(str2).b());
                        this.a.mPurchaseHelper.a(inventory.b(str2), null);
                    }
                }
            }
        }
    };
    private OnIabPurchaseFinishedListener mPurchaseFinishedListener = new OnIabPurchaseFinishedListener(this) {
        final /* synthetic */ AppActivity a;

        {
            this.a = r1;
        }

        public void a(IabResult iabResult, Purchase purchase) {
            int a = iabResult.a();
            if (this.a.mPurchaseHelper != null) {
                if (a == -1005) {
                    AppActivity.cbPurchaseFailed();
                } else if (a == 7) {
                } else {
                    if (iabResult.d()) {
                        AppActivity.showMsg("\u8cfc\u5165\u306b\u5931\u6557\u3057\u307e\u3057\u305f\n\u30a2\u30d7\u30ea\u3092\u518d\u8d77\u52d5\u3057\u3066\u304f\u3060\u3055\u3044" + iabResult.b());
                        AppActivity.cbPurchaseFailed();
                        return;
                    }
                    this.a.mPurchaseHelper.a(purchase, null);
                    AppActivity.cbPurchaseSuccess(purchase.b());
                }
            }
        }
    };
    IabHelper mPurchaseHelper;
    private a[] nativeAds;

    private static native void cbAd(int i);

    private static native void cbBeforeFullScreenAd();

    private static native void cbFullScreenAd();

    public static native void cbGPS(float f, float f2);

    public static native void cbNativeAd(String str, String str2, String str3);

    public static native void cbPurchaseFailed();

    public static native void cbPurchaseSuccess(String str);

    private static native void cbReview(int i);

    private static native void cbShare();

    public static native void duplicatePurchase(String str);

    public static native void execVerifyReceiptCB(String str, String str2);

    public static native void executeCpp(String str);

    public static native void getPrice(String[] strArr);

    public static native void restoreProduct(String str);

    protected void onCreate(Bundle bundle) {
        me = this;
        a.a.a.a.c.a((Context) this, new com.b.a.a());
        IgawCommon.startApplication(this);
        OneSignal.startInit(this).init();
        jp.co.geniee.gnadsdk.a.b.a(getApplicationContext()).a();
        mReward = new GNSRewardVideoAd("1195791", this);
        mReward.a(new GNSRewardVideoAdListener(this) {
            final /* synthetic */ AppActivity a;

            {
                this.a = r1;
            }

            public void a() {
                Log.d("GNSReward", "\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u6210\u529f");
                AppActivity.cbAd(AppActivity.AD_CB_TYPE_ADS_FETCH_COMPLETED);
            }

            public void a(GNSVideoRewardData gNSVideoRewardData) {
                Log.d("GNSReward", "\u52d5\u753b\u5e83\u544a\u518d\u751f\u958b\u59cb\u3002(" + gNSVideoRewardData.a + " )");
                AppActivity.cbAd(AppActivity.AD_CB_TYPE_ADS_DID_SHOW);
            }

            public void b(GNSVideoRewardData gNSVideoRewardData) {
                Log.d("GNSReward", "\u30e6\u30fc\u30b6\u306b\u30ea\u30ef\u30fc\u30c9\u3092\u4ed8\u4e0e\u3002(" + gNSVideoRewardData.a + "  " + gNSVideoRewardData.c + gNSVideoRewardData.b + ")");
            }

            public void c(GNSVideoRewardData gNSVideoRewardData) {
                Log.d("GNSReward", "\u52d5\u753b\u5e83\u544a\u304c\u9589\u3058\u3089\u308c\u305f\u3002(" + gNSVideoRewardData.a + " )");
                AppActivity.cbAd(AppActivity.AD_CB_TYPE_ADS_DID_COMPLETE_SHOW);
                AppActivity.cbAd(AppActivity.AD_CB_TYPE_ADS_DID_HIDE);
            }

            public void a(GNSVideoRewardException gNSVideoRewardException) {
                Log.d("GNSReward", "\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u5931\u6557\u3002(" + gNSVideoRewardException.a() + "  Code:" + gNSVideoRewardException.b() + "  " + gNSVideoRewardException.getMessage());
                AppActivity.cbAd(AppActivity.AD_CB_TYPE_ADS_PLAY_FAILED);
            }
        });
        super.onCreate(bundle);
    }

    public static void initFullScreen(String str) {
        _unitId = str;
        me.runOnUiThread(new Runnable() {
            public void run() {
                AppActivity.mPublisherInterstitialAd = new PublisherInterstitialAd(AppActivity.me);
                AppActivity.mPublisherInterstitialAd.setAdUnitId(AppActivity._unitId);
                AppActivity.mPublisherInterstitialAd.setAdListener(new AdListener(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void onAdClosed() {
                        AppActivity.requestNewInterstitial();
                    }
                });
                AppActivity.requestNewInterstitial();
            }
        });
    }

    private static void requestNewInterstitial() {
        PublisherAdRequest build = new PublisherAdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mPublisherInterstitialAd.setAdListener(new AdListener() {
            public void onAdOpened() {
                AppActivity.cbBeforeFullScreenAd();
            }

            public void onAdClosed() {
                AppActivity.cbFullScreenAd();
            }
        });
        mPublisherInterstitialAd.loadAd(build);
    }

    public void onResume() {
        super.onResume();
        if (mReward != null) {
            mReward.f();
        }
        IgawCommon.startSession(this);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mPurchaseHelper != null && !this.mPurchaseHelper.a(i, i2, intent)) {
            super.onActivityResult(i, i2, intent);
        }
    }

    public void onPause() {
        super.onPause();
        IgawCommon.endSession();
        if (mReward != null) {
            mReward.g();
        }
    }

    public void onStop() {
        super.onStop();
        if (mReward != null) {
            mReward.h();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (mReward != null) {
            mReward.i();
        }
    }

    protected void onStart() {
        super.onStart();
        if (mReward != null) {
            mReward.e();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public static void showMsgReview(String str, String str2, String str3, String str4, String str5) {
        me.mAlertDialogBuilder = new Builder(me);
        me.mAlertDialogBuilder.setTitle(str);
        me.mAlertDialogBuilder.setMessage(str2);
        me.mAlertDialogBuilder.setPositiveButton(str3, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AppActivity.cbReview(0);
            }
        });
        if (str5 == null || str5.length() <= 0) {
            me.mAlertDialogBuilder.setNegativeButton(str4, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    AppActivity.cbReview(1);
                }
            });
        } else {
            me.mAlertDialogBuilder.setNeutralButton(str4, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    AppActivity.cbReview(1);
                }
            });
            me.mAlertDialogBuilder.setNegativeButton(str5, new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    AppActivity.cbReview(2);
                }
            });
        }
        me.mAlertDialogBuilder.setCancelable(false);
        me.runOnUiThread(new Runnable() {
            public void run() {
                AppActivity.me.mAlertDialogBuilder.create().show();
            }
        });
    }

    public static void showMsg(String str) {
        me.mAlertDialogBuilder = new Builder(me);
        me.mAlertDialogBuilder.setMessage(str);
        me.mAlertDialogBuilder.setPositiveButton("OK", null);
        me.runOnUiThread(new Runnable() {
            public void run() {
                AppActivity.me.mAlertDialogBuilder.create().show();
            }
        });
    }

    private static String getVerString() {
        PackageManager packageManager = me.getPackageManager();
        String str = BuildConfig.FLAVOR;
        try {
            return packageManager.getPackageInfo(me.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static boolean isInstalledApp(String str) {
        for (ApplicationInfo applicationInfo : me.getPackageManager().getInstalledApplications(0)) {
            if (str.equals(applicationInfo.packageName)) {
                return true;
            }
        }
        return false;
    }

    public static void onShareFacebook(String str, String str2) {
    }

    public static void onShareTwitter(String str, String str2) {
        jp.co.vaz.creator.share.twitter.a.a(str, str2, me);
    }

    public static void onShareLine(String str, String str2) {
        if (isInstalledApp("jp.naver.line.android")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("line://msg/text/" + str));
            Log.d("LINE Share", str2);
            me.startActivity(intent);
            cbShare();
            return;
        }
        AppActivity appActivity = me;
        showToast("LINE\u3092\u30a4\u30f3\u30b9\u30c8\u30fc\u30eb\u3057\u3066\u304f\u3060\u3055\u3044");
    }

    public static void onSaveImageToPhotos(String str) {
        FileChannel channel;
        try {
            onShowIndicator("\u753b\u50cf\u4fdd\u5b58\u4e2d...");
            FileInputStream fileInputStream = new FileInputStream(str);
            channel = fileInputStream.getChannel();
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/vazgame/");
            if (!file.exists()) {
                file.mkdir();
            }
            Date date = new Date();
            String str2 = file.getAbsolutePath() + new SimpleDateFormat("yyyyMMdd_HHmmss").format(date) + ".png";
            OutputStream fileOutputStream = new FileOutputStream(str2);
            Object channel2 = fileOutputStream.getChannel();
            channel.transferTo(0, channel.size(), channel2);
            channel.close();
            BitmapFactory.decodeFile(str2).compress(CompressFormat.PNG, AD_CB_TYPE_ADS_FETCH_COMPLETED, fileOutputStream);
            fileOutputStream.flush();
            channel2.close();
            fileOutputStream.close();
            fileInputStream.close();
            registAndroidDB(str2);
            onHideIndicator();
            showToast("\u4fdd\u5b58\u6210\u529f");
        } catch (Exception e) {
            onHideIndicator();
            showToast("\u4fdd\u5b58\u5931\u6557");
        } catch (Throwable th) {
            channel.close();
        }
    }

    public static void registAndroidDB(String str) {
        ContentValues contentValues = new ContentValues();
        ContentResolver contentResolver = me.getContentResolver();
        contentValues.put("mime_type", "image/jpeg");
        contentValues.put("_data", str);
        contentResolver.insert(Media.EXTERNAL_CONTENT_URI, contentValues);
    }

    public static void onShareApp() {
        cbShare();
    }

    public static void trackInAppActivity(String str, String str2) {
        IgawAdbrix.retention(str, str2);
    }

    public static void trackInAppPurchasing(String str, String str2) {
        IgawAdbrix.buy(str, str2);
    }

    public static void trackNewUserFunnel(String str, String str2) {
        IgawAdbrix.firstTimeExperience(str, str2);
    }

    public static void onInitNotification() {
    }

    public static void onRegisterNotificationOnce(float f, String str, int i) {
        PendingIntent pendingIntent = getPendingIntent(str, i);
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        instance.add(13, (int) f);
        ((AlarmManager) me.getSystemService("alarm")).set(0, instance.getTimeInMillis(), pendingIntent);
    }

    private static PendingIntent getPendingIntent(String str, int i) {
        Intent intent = new Intent(me.getApplicationContext(), LocalNotificationReceiver.class);
        intent.putExtra(TapjoyConstants.TJC_NOTIFICATION_ID, i);
        intent.putExtra(String.MESSAGE, str);
        return PendingIntent.getBroadcast(me, i, intent, 134217728);
    }

    public static void onCancelAll(int i) {
        for (int i2 = 1; i2 < i; i2++) {
            ((AlarmManager) me.getSystemService("alarm")).cancel(getPendingIntent(null, i2));
        }
    }

    public static void onSetSubscription(boolean z) {
        OneSignal.setSubscription(z);
    }

    public static void execInitPurchase() {
        me.initPurchase();
    }

    private void initPurchase() {
        mProductIds = new ArrayList();
        mProductIds.add("jp.co.vaz.creator.hikaru2.gacha.single");
        mProductIds.add("jp.co.vaz.creator.hikaru2.gacha.eleven");
        mPurchasedProducts = new HashMap();
        for (String put : mProductIds) {
            mPurchasedProducts.put(put, Boolean.valueOf(false));
        }
        this.mPurchaseHelper = new IabHelper(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg3JWRlghcCDabM1sLRRYSK3KWKnirIYMdprbk1FAxdQPqTknOToZboqJNp7f+w0i3tg/ShRopRjS3EVRyXSqkHLLugsQX1papp4KO/6u8t8SqCJpKgP2RXI+hXO6wVNh83s8ZGOZX5BVmutmuFulUv7Fc3oB5azyW4zW1xRSWL7RfS46ry/gQ3zNWYp0lN7013hY7Z8F3y7+iTHowYUUsbwK3GMl4tKiuuI6cuyZE/eptz1H5zvbx2GPrr2aizGMEvJ+Q33Fbu3n7KTQkInrS9aIF5enThLiQL/euC9AEt9GoKSaUr0FFv1G4R2QpoC2wyiFjCXheOMXsiikKsaZTwIDAQAB");
        this.mPurchaseHelper.a(new OnIabSetupFinishedListener(this) {
            final /* synthetic */ AppActivity a;

            {
                this.a = r1;
            }

            public void a(IabResult iabResult) {
                if (iabResult.c() && this.a.mPurchaseHelper != null) {
                    this.a.mBroadcastReceiver = new IabBroadcastReceiver(this.a);
                    this.a.registerReceiver(this.a.mBroadcastReceiver, new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED"));
                    this.a.mPurchaseHelper.a(this.a.mGotInventoryListener);
                }
            }
        });
    }

    protected void requestBilling() {
        if (this.mPurchaseHelper != null) {
            try {
                this.mPurchaseHelper.a(this, requestPurchaseId, RC_REQUEST, this.mPurchaseFinishedListener, BuildConfig.FLAVOR);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    public static void requestPurchasing(String str) {
        requestPurchaseId = str;
        me.requestBilling();
    }

    public void receivedBroadcast() {
        this.mPurchaseHelper.a(this.mGotInventoryListener);
    }

    private void onPurchaseResult(int i, int i2, Intent intent) {
        if (i == GameControllerDelegate.THUMBSTICK_LEFT_Y) {
            intent.getIntExtra("RESPONSE_CODE", 0);
            String stringExtra = intent.getStringExtra("INAPP_PURCHASE_DATA");
            intent.getStringExtra("INAPP_DATA_SIGNATURE");
            if (i2 == -1) {
                try {
                    Log.d(getClass().getSimpleName(), "You have bought the " + new JSONObject(stringExtra).getString("productId") + ". Excellent choice, adventurer!");
                } catch (JSONException e) {
                    Log.d(getClass().getSimpleName(), "Failed to parse purchase data.");
                    e.printStackTrace();
                }
            }
        }
    }

    public static void getProductPrices() {
        int i = 0;
        AppActivity appActivity = me;
        String[] strArr = new String[(mProductIds.size() * 2)];
        try {
            IabHelper iabHelper = me.mPurchaseHelper;
            AppActivity appActivity2 = me;
            Inventory a = iabHelper.a(true, mProductIds);
            int i2 = 0;
            while (true) {
                appActivity = me;
                if (i2 < mProductIds.size()) {
                    appActivity = me;
                    String str = (String) mProductIds.get(i2);
                    String b = a.a(str).b();
                    strArr[i2 * 2] = str;
                    strArr[(i2 * 2) + 1] = b;
                    i2++;
                } else {
                    getPrice(strArr);
                    return;
                }
            }
        } catch (IabException e) {
            e.printStackTrace();
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
            while (true) {
                appActivity = me;
                if (i < mProductIds.size()) {
                    appActivity = me;
                    strArr[i * 2] = (String) mProductIds.get(i);
                    strArr[(i * 2) + 1] = "\uffe5980";
                    i++;
                } else {
                    getPrice(strArr);
                    return;
                }
            }
        } catch (NullPointerException e3) {
            e3.printStackTrace();
        }
    }

    public static void restorePurchase() {
        AppActivity appActivity = me;
        for (Entry entry : mPurchasedProducts.entrySet()) {
            if (((Boolean) entry.getValue()).booleanValue()) {
                restoreProduct((String) entry.getKey());
            }
        }
        Cocos2dxHelper.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                AppActivity.me.mPurchaseHelper.a(AppActivity.me.mGotInventoryListener);
            }
        });
    }

    public static void execute(String str) {
        System.out.print("fire");
        executeCpp(str);
    }

    public static void onShowIndicator(final String str) {
        Cocos2dxHelper.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                AppActivity.mLoadingIndicator = new ProgressDialog(AppActivity.me);
                AppActivity.mLoadingIndicator.setCancelable(false);
                AppActivity.mLoadingIndicator.setMessage(str);
                AppActivity.mLoadingIndicator.setProgressStyle(0);
                AppActivity.mLoadingIndicator.show();
            }
        });
    }

    public static void onHideIndicator() {
        Cocos2dxHelper.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                AppActivity.mLoadingIndicator.dismiss();
            }
        });
    }

    public static void showToast(final String str) {
        me.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(AppActivity.me, str, 0).show();
            }
        });
    }

    public static boolean isHans() {
        Locale locale = Locale.getDefault();
        locale.getLanguage();
        String country = locale.getCountry();
        if (country.equals("TW") || country.equals("HK")) {
            return false;
        }
        return true;
    }

    public static void initBannerAd(String str) {
        _bannerAdId = str;
        me.runOnUiThread(new Runnable() {
            public void run() {
                AppActivity.me.bannerView = new PublisherAdView(AppActivity.me);
                AppActivity.me.bannerView.setAdUnitId(AppActivity._bannerAdId);
                AppActivity.me.bannerView.setAdSizes(new AdSize[]{AdSize.BANNER});
                LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                layoutParams.gravity = 81;
                AppActivity.me.addContentView(AppActivity.me.bannerView, layoutParams);
                AppActivity.me.bannerView.loadAd(new PublisherAdRequest.Builder().build());
            }
        });
    }

    public static void showInterstitial(String str) {
        _instiId = str;
        me.runOnUiThread(new Runnable() {
            public void run() {
                if (AppActivity.me.interstitialView == null) {
                    AppActivity.me.interstitialView = new PublisherAdView(AppActivity.me);
                    AppActivity.me.interstitialView.setAdUnitId(AppActivity._instiId);
                    AppActivity.me.interstitialView.setAdSizes(new AdSize[]{AdSize.MEDIUM_RECTANGLE});
                    LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 17;
                    AppActivity.me.addContentView(AppActivity.me.interstitialView, layoutParams);
                }
                AppActivity.me.interstitialView.setVisibility(0);
                AppActivity.me.interstitialView.loadAd(new PublisherAdRequest.Builder().build());
            }
        });
    }

    public static void hideInterstitial() {
        me.runOnUiThread(new Runnable() {
            public void run() {
                if (AppActivity.me.interstitialView != null) {
                    AppActivity.me.interstitialView.setVisibility(4);
                }
            }
        });
    }

    public static void hideInterstitialGacha() {
        me.runOnUiThread(new Runnable() {
            public void run() {
                if (AppActivity.me.interstitialView != null) {
                    AppActivity.me.interstitialView.setVisibility(4);
                }
            }
        });
    }

    public static void loadNativeAd(String str) {
        _curId = str;
        me.runOnUiThread(new Runnable() {
            public void run() {
                AppActivity.nativeAdRequest = new b(AppActivity.me, AppActivity._curId);
                AppActivity.nativeAdRequest.a(AppActivity.me);
                AppActivity.nativeAdRequest.a(AppActivity.me);
            }
        });
    }

    public void onNativeAdsLoaded(a[] aVarArr) {
        me.nativeAds = aVarArr;
        me.runOnUiThread(new Runnable(this) {
            final /* synthetic */ AppActivity a;

            {
                this.a = r1;
            }

            public void run() {
                for (int i = 0; i < AppActivity.me.nativeAds.length; i++) {
                    if (AppActivity.me.nativeAds[i].a.equals(AppActivity._curId)) {
                        String str = AppActivity.me.nativeAds[i].g;
                        String str2 = AppActivity.me.nativeAds[i].k;
                        String str3 = AppActivity.me.nativeAds[i].c;
                        String str4 = AppActivity.me.nativeAds[i].d;
                        if (str.equals(BuildConfig.FLAVOR)) {
                            AppActivity.cbNativeAd(str2, str3, str4);
                        } else {
                            AppActivity.cbNativeAd(str, str3, str4);
                        }
                        AppActivity.me.nativeAds[i].a();
                        AppActivity._nativeAd = AppActivity.me.nativeAds[i];
                        return;
                    }
                }
            }
        });
    }

    public void onNativeAdsFailedToLoad() {
        Log.w("NativeAd", "onNativeAdsFailedToLoad");
    }

    public boolean onShouldStartInternalBrowserWithClick(String str) {
        Log.d("NativeAd", "onShouldStartInternalBrowserWithClick : " + str);
        return false;
    }

    public static void clickImp() {
        me.runOnUiThread(new Runnable() {
            public void run() {
                if (AppActivity._nativeAd != null) {
                    AppActivity._nativeAd.b();
                }
            }
        });
    }

    public static void showFullScreen() {
        me.runOnUiThread(new Runnable() {
            public void run() {
                if (AppActivity.mPublisherInterstitialAd == null || !AppActivity.mPublisherInterstitialAd.isLoaded()) {
                    Log.e(BuildConfig.FLAVOR, "\u30d5\u30eb\u30b9\u30af\u30ea\u30fc\u30f3\u5e83\u544a\u3092\u8868\u793a\u3067\u304d\u307e\u305b\u3093\u3067\u3057\u305f\u3002");
                } else {
                    AppActivity.mPublisherInterstitialAd.show();
                }
            }
        });
    }

    public static void loadRequestMovieAd() {
        if (mReward != null) {
            mReward.e();
            mReward.f();
            mReward.a();
            Log.d("GNSReward", "\u52d5\u753b\u30ed\u30fc\u30c9\u958b\u59cb");
        }
    }

    public static void showMovieAd() {
        Log.d(me.getClass().getSimpleName(), "showMovieAd called");
        if (mReward == null || !mReward.b()) {
            Log.d("GNSReward", "\u52d5\u753b\u5e83\u544a\u30ed\u30fc\u30c9\u4e2d\u3067\u3059\u3002");
        } else {
            mReward.c();
        }
    }

    public static void openHikaru1() {
        try {
            me.startActivity(me.getPackageManager().getLaunchIntentForPackage("jp.co.vaz.creator.hikaru"));
        } catch (Exception e) {
            me.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=jp.co.vaz.creator.hikaru&hl=ja")));
        }
    }

    public static void showAcceptPopGPS() {
        if (!(android.support.v4.app.a.a(me, "android.permission.ACCESS_FINE_LOCATION") == 0 || android.support.v4.app.a.a(me, "android.permission.ACCESS_COARSE_LOCATION") == 0)) {
            android.support.v4.app.a.a(me, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, GameControllerDelegate.THUMBSTICK_LEFT_X);
        }
        if (locationManager == null) {
            locationManager = (LocationManager) me.getSystemService("location");
        }
        if (!locationManager.isProviderEnabled("gps") && !locationManager.isProviderEnabled("network")) {
            showGPSAlertMsg();
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != GameControllerDelegate.THUMBSTICK_LEFT_X) {
            return;
        }
        if (iArr[0] == 0) {
            Log.d(TapjoyConstants.TJC_DEBUG, "checkSelfPermission true");
            startGPS();
            return;
        }
        Toast.makeText(this, "\u4f4d\u7f6e\u60c5\u5831\u306e\u5229\u7528\u304c\u8a31\u53ef\u3055\u308c\u307e\u305b\u3093\u3067\u3057\u305f", 0).show();
    }

    private static void startGPS() {
        Log.d(TapjoyConstants.TJC_DEBUG, "startGPS");
        if (locationManager == null) {
            locationManager = (LocationManager) me.getSystemService("location");
        }
        if (!locationManager.isProviderEnabled("gps")) {
            showGPSAlertMsg();
        }
        if (android.support.v4.app.a.a(me, "android.permission.ACCESS_FINE_LOCATION") == 0 || android.support.v4.app.a.a(me, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            me.runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        AppActivity.locationManager.requestLocationUpdates("gps", 1000, 10.0f, AppActivity.me);
                        Location lastKnownLocation = AppActivity.locationManager.getLastKnownLocation("network");
                        if (lastKnownLocation != null) {
                            AppActivity.cbGPS((float) lastKnownLocation.getLatitude(), (float) lastKnownLocation.getLongitude());
                        }
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
            });
            return;
        }
        android.support.v4.app.a.a(me, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, GameControllerDelegate.THUMBSTICK_LEFT_X);
    }

    public static void showGPSAlertMsg() {
        me.runOnUiThread(new Runnable() {
            public void run() {
                new Builder(AppActivity.me).setMessage("\u3053\u306e\u30a2\u30d7\u30ea\u306f\u4f4d\u7f6e\u60c5\u5831\u6a5f\u80fd\u3092\u5229\u7528\u3057\u307e\u3059\u3002\u8a2d\u5b9a\u3067\u4f4d\u7f6e\u60c5\u5831\u5229\u7528\u3092ON\u306b\u3057\u3066\u304f\u3060\u3055\u3044\u3002").setPositiveButton("\u958b\u304f", new OnClickListener(this) {
                    final /* synthetic */ AnonymousClass26 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        AppActivity.me.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                    }
                }).setOnCancelListener(new OnCancelListener(this) {
                    final /* synthetic */ AnonymousClass26 a;

                    {
                        this.a = r1;
                    }

                    public void onCancel(DialogInterface dialogInterface) {
                    }
                }).show();
            }
        });
    }

    public static void stopGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(me);
        }
    }

    public void onLocationChanged(Location location) {
        cbGPS((float) location.getLatitude(), (float) location.getLongitude());
    }

    public void onProviderDisabled(String str) {
        Log.d(TapjoyConstants.TJC_DEBUG, "onProviderDisabled");
    }

    public void onProviderEnabled(String str) {
        Log.d(TapjoyConstants.TJC_DEBUG, "onProviderEnabled");
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
        switch (i) {
            case TwitterResponse.NONE /*0*/:
                Log.d(TapjoyConstants.TJC_DEBUG, "LocationProvider.OUT_OF_SERVICE");
                return;
            case TwitterResponse.READ /*1*/:
                Log.d(TapjoyConstants.TJC_DEBUG, "LocationProvider.TEMPORARILY_UNAVAILABLE");
                return;
            case TwitterResponse.READ_WRITE /*2*/:
                Log.d(TapjoyConstants.TJC_DEBUG, "LocationProvider.AVAILABLE");
                return;
            default:
                return;
        }
    }
}
