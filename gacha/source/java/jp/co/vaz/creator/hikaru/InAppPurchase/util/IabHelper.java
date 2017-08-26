package jp.co.vaz.creator.hikaru.InAppPurchase.util;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.a.a.a.a;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONException;

public class IabHelper {
    public static final int BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE = 3;
    public static final int BILLING_RESPONSE_RESULT_DEVELOPER_ERROR = 5;
    public static final int BILLING_RESPONSE_RESULT_ERROR = 6;
    public static final int BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED = 7;
    public static final int BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED = 8;
    public static final int BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE = 4;
    public static final int BILLING_RESPONSE_RESULT_OK = 0;
    public static final int BILLING_RESPONSE_RESULT_USER_CANCELED = 1;
    public static final String GET_SKU_DETAILS_ITEM_LIST = "ITEM_ID_LIST";
    public static final String GET_SKU_DETAILS_ITEM_TYPE_LIST = "ITEM_TYPE_LIST";
    public static final int IABHELPER_BAD_RESPONSE = -1002;
    public static final int IABHELPER_ERROR_BASE = -1000;
    public static final int IABHELPER_INVALID_CONSUMPTION = -1010;
    public static final int IABHELPER_MISSING_TOKEN = -1007;
    public static final int IABHELPER_REMOTE_EXCEPTION = -1001;
    public static final int IABHELPER_SEND_INTENT_FAILED = -1004;
    public static final int IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE = -1009;
    public static final int IABHELPER_UNKNOWN_ERROR = -1008;
    public static final int IABHELPER_UNKNOWN_PURCHASE_RESPONSE = -1006;
    public static final int IABHELPER_USER_CANCELLED = -1005;
    public static final int IABHELPER_VERIFICATION_FAILED = -1003;
    public static final String INAPP_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
    public static final String ITEM_TYPE_INAPP = "inapp";
    public static final String ITEM_TYPE_SUBS = "subs";
    public static final String RESPONSE_BUY_INTENT = "BUY_INTENT";
    public static final String RESPONSE_CODE = "RESPONSE_CODE";
    public static final String RESPONSE_GET_SKU_DETAILS_LIST = "DETAILS_LIST";
    public static final String RESPONSE_INAPP_ITEM_LIST = "INAPP_PURCHASE_ITEM_LIST";
    public static final String RESPONSE_INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
    public static final String RESPONSE_INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
    public static final String RESPONSE_INAPP_SIGNATURE = "INAPP_DATA_SIGNATURE";
    public static final String RESPONSE_INAPP_SIGNATURE_LIST = "INAPP_DATA_SIGNATURE_LIST";
    boolean mAsyncInProgress = false;
    String mAsyncOperation = BuildConfig.FLAVOR;
    Context mContext;
    boolean mDebugLog = false;
    String mDebugTag = "IabHelper";
    boolean mDisposed = false;
    OnIabPurchaseFinishedListener mPurchaseListener;
    String mPurchasingItemType;
    int mRequestCode;
    a mService;
    ServiceConnection mServiceConn;
    boolean mSetupDone = false;
    String mSignatureBase64 = null;
    boolean mSubscriptionsSupported = false;

    public interface OnIabPurchaseFinishedListener {
        void onIabPurchaseFinished(IabResult iabResult, Purchase purchase);
    }

    public interface OnIabSetupFinishedListener {
        void onIabSetupFinished(IabResult iabResult);
    }

    public interface QueryInventoryFinishedListener {
        void onQueryInventoryFinished(IabResult iabResult, Inventory inventory);
    }

    public interface OnConsumeFinishedListener {
        void onConsumeFinished(Purchase purchase, IabResult iabResult);
    }

    public interface OnConsumeMultiFinishedListener {
        void onConsumeMultiFinished(List<Purchase> list, List<IabResult> list2);
    }

    public IabHelper(Context context, String str) {
        this.mContext = context.getApplicationContext();
        this.mSignatureBase64 = str;
        logDebug("IAB helper created.");
    }

    public void enableDebugLogging(boolean z, String str) {
        checkNotDisposed();
        this.mDebugLog = z;
        this.mDebugTag = str;
    }

    public void enableDebugLogging(boolean z) {
        checkNotDisposed();
        this.mDebugLog = z;
    }

    public void startSetup(final OnIabSetupFinishedListener onIabSetupFinishedListener) {
        checkNotDisposed();
        if (this.mSetupDone) {
            throw new IllegalStateException("IAB helper is already set up.");
        }
        logDebug("Starting in-app billing setup.");
        this.mServiceConn = new ServiceConnection() {
            public void onServiceDisconnected(ComponentName componentName) {
                IabHelper.this.logDebug("Billing service disconnected.");
                IabHelper.this.mService = null;
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                if (!IabHelper.this.mDisposed) {
                    IabHelper.this.logDebug("Billing service connected.");
                    IabHelper.this.mService = a.a.a(iBinder);
                    String packageName = IabHelper.this.mContext.getPackageName();
                    try {
                        IabHelper.this.logDebug("Checking for in-app billing 3 support.");
                        int a = IabHelper.this.mService.a(IabHelper.BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE, packageName, IabHelper.ITEM_TYPE_INAPP);
                        if (a != 0) {
                            if (onIabSetupFinishedListener != null) {
                                onIabSetupFinishedListener.onIabSetupFinished(new IabResult(a, "Error checking for billing v3 support."));
                            }
                            IabHelper.this.mSubscriptionsSupported = false;
                            return;
                        }
                        IabHelper.this.logDebug("In-app billing version 3 supported for " + packageName);
                        int a2 = IabHelper.this.mService.a(IabHelper.BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE, packageName, IabHelper.ITEM_TYPE_SUBS);
                        if (a2 == 0) {
                            IabHelper.this.logDebug("Subscriptions AVAILABLE.");
                            IabHelper.this.mSubscriptionsSupported = true;
                        } else {
                            IabHelper.this.logDebug("Subscriptions NOT AVAILABLE. Response: " + a2);
                        }
                        IabHelper.this.mSetupDone = true;
                        if (onIabSetupFinishedListener != null) {
                            onIabSetupFinishedListener.onIabSetupFinished(new IabResult(IabHelper.BILLING_RESPONSE_RESULT_OK, "Setup successful."));
                        }
                    } catch (RemoteException e) {
                        if (onIabSetupFinishedListener != null) {
                            onIabSetupFinishedListener.onIabSetupFinished(new IabResult(IabHelper.IABHELPER_REMOTE_EXCEPTION, "RemoteException while setting up in-app billing."));
                        }
                        e.printStackTrace();
                    }
                }
            }
        };
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        if (!this.mContext.getPackageManager().queryIntentServices(intent, BILLING_RESPONSE_RESULT_OK).isEmpty()) {
            this.mContext.bindService(intent, this.mServiceConn, BILLING_RESPONSE_RESULT_USER_CANCELED);
        } else if (onIabSetupFinishedListener != null) {
            onIabSetupFinishedListener.onIabSetupFinished(new IabResult(BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE, "Billing service unavailable on device."));
        }
    }

    public void dispose() {
        logDebug("Disposing.");
        this.mSetupDone = false;
        if (this.mServiceConn != null) {
            logDebug("Unbinding from service.");
            if (this.mContext != null) {
                this.mContext.unbindService(this.mServiceConn);
            }
        }
        this.mDisposed = true;
        this.mContext = null;
        this.mServiceConn = null;
        this.mService = null;
        this.mPurchaseListener = null;
    }

    private void checkNotDisposed() {
        if (this.mDisposed) {
            throw new IllegalStateException("IabHelper was disposed of, so it cannot be used.");
        }
    }

    public boolean subscriptionsSupported() {
        checkNotDisposed();
        return this.mSubscriptionsSupported;
    }

    public void launchPurchaseFlow(Activity activity, String str, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener) {
        launchPurchaseFlow(activity, str, i, onIabPurchaseFinishedListener, BuildConfig.FLAVOR);
    }

    public void launchPurchaseFlow(Activity activity, String str, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String str2) {
        launchPurchaseFlow(activity, str, ITEM_TYPE_INAPP, i, onIabPurchaseFinishedListener, str2);
    }

    public void launchSubscriptionPurchaseFlow(Activity activity, String str, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener) {
        launchSubscriptionPurchaseFlow(activity, str, i, onIabPurchaseFinishedListener, BuildConfig.FLAVOR);
    }

    public void launchSubscriptionPurchaseFlow(Activity activity, String str, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String str2) {
        launchPurchaseFlow(activity, str, ITEM_TYPE_SUBS, i, onIabPurchaseFinishedListener, str2);
    }

    public void launchPurchaseFlow(Activity activity, String str, String str2, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String str3) {
        IabResult iabResult;
        checkNotDisposed();
        checkSetupDone("launchPurchaseFlow");
        flagStartAsync("launchPurchaseFlow");
        if (!str2.equals(ITEM_TYPE_SUBS) || this.mSubscriptionsSupported) {
            try {
                logDebug("Constructing buy intent for " + str + ", item type: " + str2);
                Bundle a = this.mService.a(BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE, this.mContext.getPackageName(), str, str2, str3);
                int responseCodeFromBundle = getResponseCodeFromBundle(a);
                if (responseCodeFromBundle != 0) {
                    logError("Unable to buy item, Error response: " + getResponseDesc(responseCodeFromBundle));
                    flagEndAsync();
                    iabResult = new IabResult(responseCodeFromBundle, "Unable to buy item");
                    if (onIabPurchaseFinishedListener != null) {
                        onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult, null);
                        return;
                    }
                    return;
                }
                PendingIntent pendingIntent = (PendingIntent) a.getParcelable(RESPONSE_BUY_INTENT);
                logDebug("Launching buy intent for " + str + ". Request code: " + i);
                this.mRequestCode = i;
                this.mPurchaseListener = onIabPurchaseFinishedListener;
                this.mPurchasingItemType = str2;
                activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i, new Intent(), Integer.valueOf(BILLING_RESPONSE_RESULT_OK).intValue(), Integer.valueOf(BILLING_RESPONSE_RESULT_OK).intValue(), Integer.valueOf(BILLING_RESPONSE_RESULT_OK).intValue());
                return;
            } catch (SendIntentException e) {
                logError("SendIntentException while launching purchase flow for sku " + str);
                e.printStackTrace();
                flagEndAsync();
                iabResult = new IabResult(IABHELPER_SEND_INTENT_FAILED, "Failed to send intent.");
                if (onIabPurchaseFinishedListener != null) {
                    onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult, null);
                    return;
                }
                return;
            } catch (RemoteException e2) {
                logError("RemoteException while launching purchase flow for sku " + str);
                e2.printStackTrace();
                flagEndAsync();
                iabResult = new IabResult(IABHELPER_REMOTE_EXCEPTION, "Remote exception while starting purchase flow");
                if (onIabPurchaseFinishedListener != null) {
                    onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult, null);
                    return;
                }
                return;
            }
        }
        iabResult = new IabResult(IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE, "Subscriptions are not available.");
        flagEndAsync();
        if (onIabPurchaseFinishedListener != null) {
            onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult, null);
        }
    }

    public boolean handleActivityResult(int i, int i2, Intent intent) {
        if (i != this.mRequestCode) {
            return false;
        }
        checkNotDisposed();
        checkSetupDone("handleActivityResult");
        flagEndAsync();
        IabResult iabResult;
        if (intent == null) {
            logError("Null data in IAB activity result.");
            iabResult = new IabResult(IABHELPER_BAD_RESPONSE, "Null data in IAB result");
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(iabResult, null);
            }
            return true;
        }
        int responseCodeFromIntent = getResponseCodeFromIntent(intent);
        String stringExtra = intent.getStringExtra(RESPONSE_INAPP_PURCHASE_DATA);
        String stringExtra2 = intent.getStringExtra(RESPONSE_INAPP_SIGNATURE);
        if (i2 == -1 && responseCodeFromIntent == 0) {
            logDebug("Successful resultcode from purchase activity.");
            logDebug("Purchase data: " + stringExtra);
            logDebug("Data signature: " + stringExtra2);
            logDebug("Extras: " + intent.getExtras());
            logDebug("Expected item type: " + this.mPurchasingItemType);
            if (stringExtra == null || stringExtra2 == null) {
                logError("BUG: either purchaseData or dataSignature is null.");
                logDebug("Extras: " + intent.getExtras().toString());
                iabResult = new IabResult(IABHELPER_UNKNOWN_ERROR, "IAB returned null purchaseData or dataSignature");
                if (this.mPurchaseListener != null) {
                    this.mPurchaseListener.onIabPurchaseFinished(iabResult, null);
                }
                return true;
            }
            try {
                Purchase purchase = new Purchase(this.mPurchasingItemType, stringExtra, stringExtra2);
                String sku = purchase.getSku();
                if (Security.verifyPurchase(this.mSignatureBase64, stringExtra, stringExtra2)) {
                    logDebug("Purchase signature successfully verified.");
                    if (this.mPurchaseListener != null) {
                        this.mPurchaseListener.onIabPurchaseFinished(new IabResult(BILLING_RESPONSE_RESULT_OK, "Success"), purchase);
                    }
                } else {
                    logError("Purchase signature verification FAILED for sku " + sku);
                    iabResult = new IabResult(IABHELPER_VERIFICATION_FAILED, "Signature verification failed for sku " + sku);
                    if (this.mPurchaseListener != null) {
                        this.mPurchaseListener.onIabPurchaseFinished(iabResult, purchase);
                    }
                    return true;
                }
            } catch (JSONException e) {
                logError("Failed to parse purchase data.");
                e.printStackTrace();
                iabResult = new IabResult(IABHELPER_BAD_RESPONSE, "Failed to parse purchase data.");
                if (this.mPurchaseListener != null) {
                    this.mPurchaseListener.onIabPurchaseFinished(iabResult, null);
                }
                return true;
            }
        } else if (i2 == -1) {
            logDebug("Result code was OK but in-app billing response was not OK: " + getResponseDesc(responseCodeFromIntent));
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(new IabResult(responseCodeFromIntent, "Problem purchashing item."), null);
            }
        } else if (i2 == 0) {
            logDebug("Purchase canceled - Response: " + getResponseDesc(responseCodeFromIntent));
            iabResult = new IabResult(IABHELPER_USER_CANCELLED, "User canceled.");
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(iabResult, null);
            }
        } else {
            logError("Purchase failed. Result code: " + Integer.toString(i2) + ". Response: " + getResponseDesc(responseCodeFromIntent));
            iabResult = new IabResult(IABHELPER_UNKNOWN_PURCHASE_RESPONSE, "Unknown purchase response.");
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(iabResult, null);
            }
        }
        return true;
    }

    public Inventory queryInventory(boolean z, List<String> list) {
        return queryInventory(z, list, null);
    }

    public Inventory queryInventory(boolean z, List<String> list, List<String> list2) {
        checkNotDisposed();
        checkSetupDone("queryInventory");
        try {
            Inventory inventory = new Inventory();
            int queryPurchases = queryPurchases(inventory, ITEM_TYPE_INAPP);
            if (queryPurchases != 0) {
                throw new IabException(queryPurchases, "Error refreshing inventory (querying owned items).");
            }
            if (z) {
                queryPurchases = querySkuDetails(ITEM_TYPE_INAPP, inventory, list);
                if (queryPurchases != 0) {
                    throw new IabException(queryPurchases, "Error refreshing inventory (querying prices of items).");
                }
            }
            if (this.mSubscriptionsSupported) {
                queryPurchases = queryPurchases(inventory, ITEM_TYPE_SUBS);
                if (queryPurchases != 0) {
                    throw new IabException(queryPurchases, "Error refreshing inventory (querying owned subscriptions).");
                } else if (z) {
                    queryPurchases = querySkuDetails(ITEM_TYPE_SUBS, inventory, list);
                    if (queryPurchases != 0) {
                        throw new IabException(queryPurchases, "Error refreshing inventory (querying prices of subscriptions).");
                    }
                }
            }
            return inventory;
        } catch (Exception e) {
            throw new IabException(IABHELPER_REMOTE_EXCEPTION, "Remote exception while refreshing inventory.", e);
        } catch (Exception e2) {
            throw new IabException(IABHELPER_BAD_RESPONSE, "Error parsing JSON response while refreshing inventory.", e2);
        }
    }

    public void queryInventoryAsync(boolean z, List<String> list, QueryInventoryFinishedListener queryInventoryFinishedListener) {
        final Handler handler = new Handler();
        checkNotDisposed();
        checkSetupDone("queryInventory");
        flagStartAsync("refresh inventory");
        final boolean z2 = z;
        final List<String> list2 = list;
        final QueryInventoryFinishedListener queryInventoryFinishedListener2 = queryInventoryFinishedListener;
        new Thread(new Runnable() {
            public void run() {
                IabResult iabResult = new IabResult(IabHelper.BILLING_RESPONSE_RESULT_OK, "Inventory refresh successful.");
                Inventory inventory = null;
                try {
                    inventory = IabHelper.this.queryInventory(z2, list2);
                } catch (IabException e) {
                    iabResult = e.getResult();
                }
                IabHelper.this.flagEndAsync();
                if (!IabHelper.this.mDisposed && queryInventoryFinishedListener2 != null) {
                    handler.post(new Runnable() {
                        public void run() {
                            queryInventoryFinishedListener2.onQueryInventoryFinished(iabResult, inventory);
                        }
                    });
                }
            }
        }).start();
    }

    public void queryInventoryAsync(QueryInventoryFinishedListener queryInventoryFinishedListener) {
        queryInventoryAsync(true, null, queryInventoryFinishedListener);
    }

    public void queryInventoryAsync(boolean z, QueryInventoryFinishedListener queryInventoryFinishedListener) {
        queryInventoryAsync(z, null, queryInventoryFinishedListener);
    }

    void consume(Purchase purchase) {
        checkNotDisposed();
        checkSetupDone("consume");
        if (purchase.mItemType.equals(ITEM_TYPE_INAPP)) {
            try {
                String token = purchase.getToken();
                String sku = purchase.getSku();
                if (token == null || token.equals(BuildConfig.FLAVOR)) {
                    logError("Can't consume " + sku + ". No token.");
                    throw new IabException((int) IABHELPER_MISSING_TOKEN, "PurchaseInfo is missing token for sku: " + sku + " " + purchase);
                }
                logDebug("Consuming sku: " + sku + ", token: " + token);
                int b = this.mService.b(BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE, this.mContext.getPackageName(), token);
                if (b == 0) {
                    logDebug("Successfully consumed sku: " + sku);
                    return;
                } else {
                    logDebug("Error consuming consuming sku " + sku + ". " + getResponseDesc(b));
                    throw new IabException(b, "Error consuming sku " + sku);
                }
            } catch (Exception e) {
                throw new IabException(IABHELPER_REMOTE_EXCEPTION, "Remote exception while consuming. PurchaseInfo: " + purchase, e);
            }
        }
        throw new IabException((int) IABHELPER_INVALID_CONSUMPTION, "Items of type '" + purchase.mItemType + "' can't be consumed.");
    }

    public void consumeAsync(Purchase purchase, OnConsumeFinishedListener onConsumeFinishedListener) {
        checkNotDisposed();
        checkSetupDone("consume");
        List arrayList = new ArrayList();
        arrayList.add(purchase);
        consumeAsyncInternal(arrayList, onConsumeFinishedListener, null);
    }

    public void consumeAsync(List<Purchase> list, OnConsumeMultiFinishedListener onConsumeMultiFinishedListener) {
        checkNotDisposed();
        checkSetupDone("consume");
        consumeAsyncInternal(list, null, onConsumeMultiFinishedListener);
    }

    public static String getResponseDesc(int i) {
        String[] split = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split(Operation.DIVISION);
        String[] split2 = "0:OK/-1001:Remote exception during initialization/-1002:Bad response received/-1003:Purchase signature verification failed/-1004:Send intent failed/-1005:User cancelled/-1006:Unknown purchase response/-1007:Missing token/-1008:Unknown error/-1009:Subscriptions not available/-1010:Invalid consumption attempt".split(Operation.DIVISION);
        if (i <= IABHELPER_ERROR_BASE) {
            int i2 = -1000 - i;
            if (i2 < 0 || i2 >= split2.length) {
                return String.valueOf(i) + ":Unknown IAB Helper Error";
            }
            return split2[i2];
        } else if (i < 0 || i >= split.length) {
            return String.valueOf(i) + ":Unknown";
        } else {
            return split[i];
        }
    }

    void checkSetupDone(String str) {
        if (!this.mSetupDone) {
            logError("Illegal state for operation (" + str + "): IAB helper is not set up.");
            throw new IllegalStateException("IAB helper is not set up. Can't perform operation: " + str);
        }
    }

    int getResponseCodeFromBundle(Bundle bundle) {
        Object obj = bundle.get(RESPONSE_CODE);
        if (obj == null) {
            logDebug("Bundle with null response code, assuming OK (known issue)");
            return BILLING_RESPONSE_RESULT_OK;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return (int) ((Long) obj).longValue();
            }
            logError("Unexpected type for bundle response code.");
            logError(obj.getClass().getName());
            throw new RuntimeException("Unexpected type for bundle response code: " + obj.getClass().getName());
        }
    }

    int getResponseCodeFromIntent(Intent intent) {
        Object obj = intent.getExtras().get(RESPONSE_CODE);
        if (obj == null) {
            logError("Intent with no response code, assuming OK (known issue)");
            return BILLING_RESPONSE_RESULT_OK;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return (int) ((Long) obj).longValue();
            }
            logError("Unexpected type for intent response code.");
            logError(obj.getClass().getName());
            throw new RuntimeException("Unexpected type for intent response code: " + obj.getClass().getName());
        }
    }

    void flagStartAsync(String str) {
        if (this.mAsyncInProgress) {
            flagEndAsync();
        }
        if (this.mAsyncInProgress) {
            throw new IllegalStateException("Can't start async operation (" + str + ") because another async operation(" + this.mAsyncOperation + ") is in progress.");
        }
        this.mAsyncOperation = str;
        this.mAsyncInProgress = true;
        logDebug("Starting async operation: " + str);
    }

    void flagEndAsync() {
        logDebug("Ending async operation: " + this.mAsyncOperation);
        this.mAsyncOperation = BuildConfig.FLAVOR;
        this.mAsyncInProgress = false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    int queryPurchases(jp.co.vaz.creator.hikaru.InAppPurchase.util.Inventory r13, java.lang.String r14) {
        /*
        r12 = this;
        r3 = 0;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Querying owned items, item type: ";
        r0 = r0.append(r1);
        r0 = r0.append(r14);
        r0 = r0.toString();
        r12.logDebug(r0);
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Package name: ";
        r0 = r0.append(r1);
        r1 = r12.mContext;
        r1 = r1.getPackageName();
        r0 = r0.append(r1);
        r0 = r0.toString();
        r12.logDebug(r0);
        r0 = 0;
        r1 = r3;
    L_0x0035:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = "Calling getPurchases with continuation token: ";
        r2 = r2.append(r4);
        r2 = r2.append(r0);
        r2 = r2.toString();
        r12.logDebug(r2);
        r2 = r12.mService;
        r4 = 3;
        r5 = r12.mContext;
        r5 = r5.getPackageName();
        r6 = r2.a(r4, r5, r14, r0);
        r0 = r12.getResponseCodeFromBundle(r6);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = "Owned items response: ";
        r2 = r2.append(r4);
        r4 = java.lang.String.valueOf(r0);
        r2 = r2.append(r4);
        r2 = r2.toString();
        r12.logDebug(r2);
        if (r0 == 0) goto L_0x0094;
    L_0x0078:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "getPurchases() failed: ";
        r1 = r1.append(r2);
        r2 = getResponseDesc(r0);
        r1 = r1.append(r2);
        r1 = r1.toString();
        r12.logDebug(r1);
        r3 = r0;
    L_0x0093:
        return r3;
    L_0x0094:
        r0 = "INAPP_PURCHASE_ITEM_LIST";
        r0 = r6.containsKey(r0);
        if (r0 == 0) goto L_0x00ac;
    L_0x009c:
        r0 = "INAPP_PURCHASE_DATA_LIST";
        r0 = r6.containsKey(r0);
        if (r0 == 0) goto L_0x00ac;
    L_0x00a4:
        r0 = "INAPP_DATA_SIGNATURE_LIST";
        r0 = r6.containsKey(r0);
        if (r0 != 0) goto L_0x00b4;
    L_0x00ac:
        r0 = "Bundle returned from getPurchases() doesn't contain required fields.";
        r12.logError(r0);
        r3 = -1002; // 0xfffffffffffffc16 float:NaN double:NaN;
        goto L_0x0093;
    L_0x00b4:
        r0 = "INAPP_PURCHASE_ITEM_LIST";
        r7 = r6.getStringArrayList(r0);
        r0 = "INAPP_PURCHASE_DATA_LIST";
        r8 = r6.getStringArrayList(r0);
        r0 = "INAPP_DATA_SIGNATURE_LIST";
        r9 = r6.getStringArrayList(r0);
        r5 = r3;
        r4 = r1;
    L_0x00c8:
        r0 = r8.size();
        if (r5 >= r0) goto L_0x0162;
    L_0x00ce:
        r0 = r8.get(r5);
        r0 = (java.lang.String) r0;
        r1 = r9.get(r5);
        r1 = (java.lang.String) r1;
        r2 = r7.get(r5);
        r2 = (java.lang.String) r2;
        r10 = r12.mSignatureBase64;
        r10 = jp.co.vaz.creator.hikaru.InAppPurchase.util.Security.verifyPurchase(r10, r0, r1);
        if (r10 == 0) goto L_0x012f;
    L_0x00e8:
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r11 = "Sku is owned: ";
        r10 = r10.append(r11);
        r2 = r10.append(r2);
        r2 = r2.toString();
        r12.logDebug(r2);
        r2 = new jp.co.vaz.creator.hikaru.InAppPurchase.util.Purchase;
        r2.<init>(r14, r0, r1);
        r1 = r2.getToken();
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 == 0) goto L_0x0128;
    L_0x010d:
        r1 = "BUG: empty/null token!";
        r12.logWarn(r1);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r10 = "Purchase data: ";
        r1 = r1.append(r10);
        r0 = r1.append(r0);
        r0 = r0.toString();
        r12.logDebug(r0);
    L_0x0128:
        r13.addPurchase(r2);
    L_0x012b:
        r0 = r5 + 1;
        r5 = r0;
        goto L_0x00c8;
    L_0x012f:
        r2 = "Purchase signature verification **FAILED**. Not adding item.";
        r12.logWarn(r2);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = "   Purchase data: ";
        r2 = r2.append(r4);
        r0 = r2.append(r0);
        r0 = r0.toString();
        r12.logDebug(r0);
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = "   Signature: ";
        r0 = r0.append(r2);
        r0 = r0.append(r1);
        r0 = r0.toString();
        r12.logDebug(r0);
        r4 = 1;
        goto L_0x012b;
    L_0x0162:
        r0 = "INAPP_CONTINUATION_TOKEN";
        r0 = r6.getString(r0);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Continuation token: ";
        r1 = r1.append(r2);
        r1 = r1.append(r0);
        r1 = r1.toString();
        r12.logDebug(r1);
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 == 0) goto L_0x018a;
    L_0x0184:
        if (r4 == 0) goto L_0x0093;
    L_0x0186:
        r3 = -1003; // 0xfffffffffffffc15 float:NaN double:NaN;
        goto L_0x0093;
    L_0x018a:
        r1 = r4;
        goto L_0x0035;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.co.vaz.creator.hikaru.InAppPurchase.util.IabHelper.queryPurchases(jp.co.vaz.creator.hikaru.InAppPurchase.util.Inventory, java.lang.String):int");
    }

    int querySkuDetails(String str, Inventory inventory, List<String> list) {
        logDebug("Querying SKU details.");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(inventory.getAllOwnedSkus(str));
        if (list != null) {
            for (String str2 : list) {
                if (!arrayList.contains(str2)) {
                    arrayList.add(str2);
                }
            }
        }
        if (arrayList.size() == 0) {
            logDebug("queryPrices: nothing to do because there are no SKUs.");
            return BILLING_RESPONSE_RESULT_OK;
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(GET_SKU_DETAILS_ITEM_LIST, arrayList);
        bundle = this.mService.a((int) BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE, this.mContext.getPackageName(), str, bundle);
        if (bundle.containsKey(RESPONSE_GET_SKU_DETAILS_LIST)) {
            Iterator it = bundle.getStringArrayList(RESPONSE_GET_SKU_DETAILS_LIST).iterator();
            while (it.hasNext()) {
                SkuDetails skuDetails = new SkuDetails(str, (String) it.next());
                logDebug("Got sku details: " + skuDetails);
                inventory.addSkuDetails(skuDetails);
            }
            return BILLING_RESPONSE_RESULT_OK;
        }
        int responseCodeFromBundle = getResponseCodeFromBundle(bundle);
        if (responseCodeFromBundle != 0) {
            logDebug("getSkuDetails() failed: " + getResponseDesc(responseCodeFromBundle));
            return responseCodeFromBundle;
        }
        logError("getSkuDetails() returned a bundle with neither an error nor a detail list.");
        return IABHELPER_BAD_RESPONSE;
    }

    void consumeAsyncInternal(List<Purchase> list, OnConsumeFinishedListener onConsumeFinishedListener, OnConsumeMultiFinishedListener onConsumeMultiFinishedListener) {
        final Handler handler = new Handler();
        flagStartAsync("consume");
        final List<Purchase> list2 = list;
        final OnConsumeFinishedListener onConsumeFinishedListener2 = onConsumeFinishedListener;
        final OnConsumeMultiFinishedListener onConsumeMultiFinishedListener2 = onConsumeMultiFinishedListener;
        new Thread(new Runnable() {
            public void run() {
                final List arrayList = new ArrayList();
                for (Purchase purchase : list2) {
                    try {
                        IabHelper.this.consume(purchase);
                        arrayList.add(new IabResult(IabHelper.BILLING_RESPONSE_RESULT_OK, "Successful consume of sku " + purchase.getSku()));
                    } catch (IabException e) {
                        arrayList.add(e.getResult());
                    }
                }
                IabHelper.this.flagEndAsync();
                if (!(IabHelper.this.mDisposed || onConsumeFinishedListener2 == null)) {
                    handler.post(new Runnable() {
                        public void run() {
                            onConsumeFinishedListener2.onConsumeFinished((Purchase) list2.get(IabHelper.BILLING_RESPONSE_RESULT_OK), (IabResult) arrayList.get(IabHelper.BILLING_RESPONSE_RESULT_OK));
                        }
                    });
                }
                if (!IabHelper.this.mDisposed && onConsumeMultiFinishedListener2 != null) {
                    handler.post(new Runnable() {
                        public void run() {
                            onConsumeMultiFinishedListener2.onConsumeMultiFinished(list2, arrayList);
                        }
                    });
                }
            }
        }).start();
    }

    void logDebug(String str) {
        if (this.mDebugLog) {
            Log.d(this.mDebugTag, str);
        }
    }

    void logError(String str) {
        Log.e(this.mDebugTag, "In-app billing error: " + str);
    }

    void logWarn(String str) {
        Log.w(this.mDebugTag, "In-app billing warning: " + str);
    }
}
