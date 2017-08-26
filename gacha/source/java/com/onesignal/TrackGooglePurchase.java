package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.IBinder;
import com.applovin.sdk.AppLovinEventParameters;
import com.onesignal.OneSignal.IdsAvailableHandler;
import com.onesignal.OneSignal.LOG_LEVEL;
import com.tapjoy.TapjoyConstants;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import jp.co.vaz.creator.hikaru.InAppPurchase.util.IabHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class TrackGooglePurchase {
    private static Class<?> IInAppBillingServiceClass;
    private static int iapEnabled = -99;
    private Context appContext;
    private Method getPurchasesMethod;
    private Method getSkuDetailsMethod;
    private boolean isWaitingForPurchasesRequest = false;
    private Object mIInAppBillingService;
    private ServiceConnection mServiceConn;
    private boolean newAsExisting = true;
    private Editor prefsEditor;
    private ArrayList<String> purchaseTokens;

    TrackGooglePurchase(Context context) {
        boolean z = true;
        this.appContext = context;
        SharedPreferences sharedPreferences = this.appContext.getSharedPreferences("GTPlayerPurchases", 0);
        this.prefsEditor = sharedPreferences.edit();
        this.purchaseTokens = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(sharedPreferences.getString("purchaseTokens", "[]"));
            for (int i = 0; i < jSONArray.length(); i++) {
                this.purchaseTokens.add(jSONArray.get(i).toString());
            }
            if (jSONArray.length() != 0) {
                z = false;
            }
            this.newAsExisting = z;
            if (this.newAsExisting) {
                this.newAsExisting = sharedPreferences.getBoolean("ExistingPurchases", true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        trackIAP();
    }

    static boolean CanTrack(Context context) {
        if (iapEnabled == -99) {
            iapEnabled = context.checkCallingOrSelfPermission("com.android.vending.BILLING");
        }
        try {
            if (iapEnabled == 0) {
                IInAppBillingServiceClass = Class.forName("com.a.a.a.a");
            }
            if (iapEnabled == 0) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            iapEnabled = 0;
            return false;
        }
    }

    void trackIAP() {
        if (this.mServiceConn == null) {
            this.mServiceConn = new ServiceConnection() {
                public void onServiceDisconnected(ComponentName componentName) {
                    TrackGooglePurchase.iapEnabled = -99;
                    TrackGooglePurchase.this.mIInAppBillingService = null;
                }

                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    try {
                        Method access$200 = TrackGooglePurchase.getAsInterfaceMethod(Class.forName("com.a.a.a.a$a"));
                        access$200.setAccessible(true);
                        TrackGooglePurchase.this.mIInAppBillingService = access$200.invoke(null, new Object[]{iBinder});
                        TrackGooglePurchase.this.QueryBoughtItems();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            };
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            this.appContext.bindService(intent, this.mServiceConn, 1);
        } else if (this.mIInAppBillingService != null) {
            QueryBoughtItems();
        }
    }

    private void QueryBoughtItems() {
        if (!this.isWaitingForPurchasesRequest) {
            new Thread(new Runnable() {
                public void run() {
                    TrackGooglePurchase.this.isWaitingForPurchasesRequest = true;
                    try {
                        if (TrackGooglePurchase.this.getPurchasesMethod == null) {
                            TrackGooglePurchase.this.getPurchasesMethod = TrackGooglePurchase.getGetPurchasesMethod(TrackGooglePurchase.IInAppBillingServiceClass);
                            TrackGooglePurchase.this.getPurchasesMethod.setAccessible(true);
                        }
                        Bundle bundle = (Bundle) TrackGooglePurchase.this.getPurchasesMethod.invoke(TrackGooglePurchase.this.mIInAppBillingService, new Object[]{Integer.valueOf(3), TrackGooglePurchase.this.appContext.getPackageName(), IabHelper.ITEM_TYPE_INAPP, null});
                        if (bundle.getInt(IabHelper.RESPONSE_CODE) == 0) {
                            ArrayList arrayList = new ArrayList();
                            ArrayList arrayList2 = new ArrayList();
                            ArrayList stringArrayList = bundle.getStringArrayList(IabHelper.RESPONSE_INAPP_ITEM_LIST);
                            ArrayList stringArrayList2 = bundle.getStringArrayList(IabHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST);
                            for (int i = 0; i < stringArrayList2.size(); i++) {
                                String str = (String) stringArrayList.get(i);
                                String string = new JSONObject((String) stringArrayList2.get(i)).getString("purchaseToken");
                                if (!(TrackGooglePurchase.this.purchaseTokens.contains(string) || arrayList2.contains(string))) {
                                    arrayList2.add(string);
                                    arrayList.add(str);
                                }
                            }
                            if (arrayList.size() > 0) {
                                TrackGooglePurchase.this.sendPurchases(arrayList, arrayList2);
                            } else if (stringArrayList2.size() == 0) {
                                TrackGooglePurchase.this.newAsExisting = false;
                                TrackGooglePurchase.this.prefsEditor.putBoolean("ExistingPurchases", false);
                                TrackGooglePurchase.this.prefsEditor.commit();
                            }
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    TrackGooglePurchase.this.isWaitingForPurchasesRequest = false;
                }
            }).start();
        }
    }

    private void sendPurchases(ArrayList<String> arrayList, final ArrayList<String> arrayList2) {
        try {
            if (this.getSkuDetailsMethod == null) {
                this.getSkuDetailsMethod = getGetSkuDetailsMethod(IInAppBillingServiceClass);
                this.getSkuDetailsMethod.setAccessible(true);
            }
            new Bundle().putStringArrayList(IabHelper.GET_SKU_DETAILS_ITEM_LIST, arrayList);
            Bundle bundle = (Bundle) this.getSkuDetailsMethod.invoke(this.mIInAppBillingService, new Object[]{Integer.valueOf(3), this.appContext.getPackageName(), IabHelper.ITEM_TYPE_INAPP, bundle});
            if (bundle.getInt(IabHelper.RESPONSE_CODE) == 0) {
                String string;
                ArrayList stringArrayList = bundle.getStringArrayList(IabHelper.RESPONSE_GET_SKU_DETAILS_LIST);
                Map hashMap = new HashMap();
                Iterator it = stringArrayList.iterator();
                while (it.hasNext()) {
                    JSONObject jSONObject = new JSONObject((String) it.next());
                    string = jSONObject.getString("productId");
                    BigDecimal divide = new BigDecimal(jSONObject.getString("price_amount_micros")).divide(new BigDecimal(1000000));
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(AppLovinEventParameters.PRODUCT_IDENTIFIER, string);
                    jSONObject2.put("iso", jSONObject.getString("price_currency_code"));
                    jSONObject2.put(TapjoyConstants.TJC_AMOUNT, divide.toString());
                    hashMap.put(string, jSONObject2);
                }
                final JSONArray jSONArray = new JSONArray();
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    string = (String) it2.next();
                    if (hashMap.containsKey(string)) {
                        jSONArray.put(hashMap.get(string));
                    }
                }
                if (jSONArray.length() > 0) {
                    OneSignal.idsAvailable(new IdsAvailableHandler() {
                        public void idsAvailable(String str, String str2) {
                            OneSignal.sendPurchases(jSONArray, TrackGooglePurchase.this.newAsExisting, new ResponseHandler() {
                                public void onSuccess(String str) {
                                    TrackGooglePurchase.this.purchaseTokens.addAll(arrayList2);
                                    TrackGooglePurchase.this.prefsEditor.putString("purchaseTokens", TrackGooglePurchase.this.purchaseTokens.toString());
                                    TrackGooglePurchase.this.prefsEditor.remove("ExistingPurchases");
                                    TrackGooglePurchase.this.prefsEditor.commit();
                                    TrackGooglePurchase.this.newAsExisting = false;
                                    TrackGooglePurchase.this.isWaitingForPurchasesRequest = false;
                                }
                            });
                        }
                    });
                }
            }
        } catch (Throwable th) {
            OneSignal.Log(LOG_LEVEL.WARN, "Failed to track IAP purchases", th);
        }
    }

    private static Method getAsInterfaceMethod(Class cls) {
        for (Method method : cls.getMethods()) {
            Class[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 1 && parameterTypes[0] == IBinder.class) {
                return method;
            }
        }
        return null;
    }

    private static Method getGetPurchasesMethod(Class cls) {
        for (Method method : cls.getMethods()) {
            Class[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 4 && parameterTypes[0] == Integer.TYPE && parameterTypes[1] == String.class && parameterTypes[2] == String.class && parameterTypes[3] == String.class) {
                return method;
            }
        }
        return null;
    }

    private static Method getGetSkuDetailsMethod(Class cls) {
        for (Method method : cls.getMethods()) {
            Class[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 4 && parameterTypes[0] == Integer.TYPE && parameterTypes[1] == String.class && parameterTypes[2] == String.class && parameterTypes[3] == Bundle.class) {
                return method;
            }
        }
        return null;
    }
}
