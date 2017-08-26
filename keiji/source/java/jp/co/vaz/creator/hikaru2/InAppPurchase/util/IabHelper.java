package jp.co.vaz.creator.hikaru2.InAppPurchase.util;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONException;

public class IabHelper {
    boolean a = false;
    String b = "IabHelper";
    boolean c = false;
    boolean d = false;
    boolean e = false;
    boolean f = false;
    String g = BuildConfig.FLAVOR;
    Context h;
    a i;
    ServiceConnection j;
    int k;
    String l;
    String m = null;
    OnIabPurchaseFinishedListener n;

    public interface OnIabSetupFinishedListener {
        void a(IabResult iabResult);
    }

    public interface QueryInventoryFinishedListener {
        void a(IabResult iabResult, Inventory inventory);
    }

    public interface OnIabPurchaseFinishedListener {
        void a(IabResult iabResult, Purchase purchase);
    }

    public interface OnConsumeFinishedListener {
        void a(Purchase purchase, IabResult iabResult);
    }

    public interface OnConsumeMultiFinishedListener {
        void a(List<Purchase> list, List<IabResult> list2);
    }

    public IabHelper(Context context, String str) {
        this.h = context.getApplicationContext();
        this.m = str;
        c("IAB helper created.");
    }

    public void a(final OnIabSetupFinishedListener onIabSetupFinishedListener) {
        b();
        if (this.c) {
            throw new IllegalStateException("IAB helper is already set up.");
        }
        c("Starting in-app billing setup.");
        this.j = new ServiceConnection(this) {
            final /* synthetic */ IabHelper b;

            public void onServiceDisconnected(ComponentName componentName) {
                this.b.c("Billing service disconnected.");
                this.b.i = null;
            }

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                if (!this.b.d) {
                    this.b.c("Billing service connected.");
                    this.b.i = a.a.a(iBinder);
                    String packageName = this.b.h.getPackageName();
                    try {
                        this.b.c("Checking for in-app billing 3 support.");
                        int a = this.b.i.a(3, packageName, "inapp");
                        if (a != 0) {
                            if (onIabSetupFinishedListener != null) {
                                onIabSetupFinishedListener.a(new IabResult(a, "Error checking for billing v3 support."));
                            }
                            this.b.e = false;
                            return;
                        }
                        this.b.c("In-app billing version 3 supported for " + packageName);
                        int a2 = this.b.i.a(3, packageName, "subs");
                        if (a2 == 0) {
                            this.b.c("Subscriptions AVAILABLE.");
                            this.b.e = true;
                        } else {
                            this.b.c("Subscriptions NOT AVAILABLE. Response: " + a2);
                        }
                        this.b.c = true;
                        if (onIabSetupFinishedListener != null) {
                            onIabSetupFinishedListener.a(new IabResult(0, "Setup successful."));
                        }
                    } catch (RemoteException e) {
                        if (onIabSetupFinishedListener != null) {
                            onIabSetupFinishedListener.a(new IabResult(-1001, "RemoteException while setting up in-app billing."));
                        }
                        e.printStackTrace();
                    }
                }
            }
        };
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        if (!this.h.getPackageManager().queryIntentServices(intent, 0).isEmpty()) {
            this.h.bindService(intent, this.j, 1);
        } else if (onIabSetupFinishedListener != null) {
            onIabSetupFinishedListener.a(new IabResult(3, "Billing service unavailable on device."));
        }
    }

    private void b() {
        if (this.d) {
            throw new IllegalStateException("IabHelper was disposed of, so it cannot be used.");
        }
    }

    public void a(Activity activity, String str, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String str2) {
        a(activity, str, "inapp", i, onIabPurchaseFinishedListener, str2);
    }

    public void a(Activity activity, String str, String str2, int i, OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, String str3) {
        b();
        a("launchPurchaseFlow");
        b("launchPurchaseFlow");
        IabResult iabResult;
        if (!str2.equals("subs") || this.e) {
            try {
                c("Constructing buy intent for " + str + ", item type: " + str2);
                Bundle a = this.i.a(3, this.h.getPackageName(), str, str2, str3);
                int a2 = a(a);
                if (a2 != 0) {
                    d("Unable to buy item, Error response: " + a(a2));
                    a();
                    iabResult = new IabResult(a2, "Unable to buy item");
                    if (onIabPurchaseFinishedListener != null) {
                        onIabPurchaseFinishedListener.a(iabResult, null);
                        return;
                    }
                    return;
                }
                PendingIntent pendingIntent = (PendingIntent) a.getParcelable("BUY_INTENT");
                c("Launching buy intent for " + str + ". Request code: " + i);
                this.k = i;
                this.n = onIabPurchaseFinishedListener;
                this.l = str2;
                activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue());
                return;
            } catch (SendIntentException e) {
                d("SendIntentException while launching purchase flow for sku " + str);
                e.printStackTrace();
                a();
                iabResult = new IabResult(-1004, "Failed to send intent.");
                if (onIabPurchaseFinishedListener != null) {
                    onIabPurchaseFinishedListener.a(iabResult, null);
                    return;
                }
                return;
            } catch (RemoteException e2) {
                d("RemoteException while launching purchase flow for sku " + str);
                e2.printStackTrace();
                a();
                iabResult = new IabResult(-1001, "Remote exception while starting purchase flow");
                if (onIabPurchaseFinishedListener != null) {
                    onIabPurchaseFinishedListener.a(iabResult, null);
                    return;
                }
                return;
            }
        }
        iabResult = new IabResult(-1009, "Subscriptions are not available.");
        a();
        if (onIabPurchaseFinishedListener != null) {
            onIabPurchaseFinishedListener.a(iabResult, null);
        }
    }

    public boolean a(int i, int i2, Intent intent) {
        if (i != this.k) {
            return false;
        }
        b();
        a("handleActivityResult");
        a();
        IabResult iabResult;
        if (intent == null) {
            d("Null data in IAB activity result.");
            iabResult = new IabResult(-1002, "Null data in IAB result");
            if (this.n != null) {
                this.n.a(iabResult, null);
            }
            return true;
        }
        int a = a(intent);
        String stringExtra = intent.getStringExtra("INAPP_PURCHASE_DATA");
        String stringExtra2 = intent.getStringExtra("INAPP_DATA_SIGNATURE");
        if (i2 == -1 && a == 0) {
            c("Successful resultcode from purchase activity.");
            c("Purchase data: " + stringExtra);
            c("Data signature: " + stringExtra2);
            c("Extras: " + intent.getExtras());
            c("Expected item type: " + this.l);
            if (stringExtra == null || stringExtra2 == null) {
                d("BUG: either purchaseData or dataSignature is null.");
                c("Extras: " + intent.getExtras().toString());
                iabResult = new IabResult(-1008, "IAB returned null purchaseData or dataSignature");
                if (this.n != null) {
                    this.n.a(iabResult, null);
                }
                return true;
            }
            try {
                Purchase purchase = new Purchase(this.l, stringExtra, stringExtra2);
                String c = purchase.c();
                if (Security.a(this.m, stringExtra, stringExtra2)) {
                    c("Purchase signature successfully verified.");
                    if (this.n != null) {
                        this.n.a(new IabResult(0, "Success"), purchase);
                    }
                } else {
                    d("Purchase signature verification FAILED for sku " + c);
                    iabResult = new IabResult(-1003, "Signature verification failed for sku " + c);
                    if (this.n != null) {
                        this.n.a(iabResult, purchase);
                    }
                    return true;
                }
            } catch (JSONException e) {
                d("Failed to parse purchase data.");
                e.printStackTrace();
                iabResult = new IabResult(-1002, "Failed to parse purchase data.");
                if (this.n != null) {
                    this.n.a(iabResult, null);
                }
                return true;
            }
        } else if (i2 == -1) {
            c("Result code was OK but in-app billing response was not OK: " + a(a));
            if (this.n != null) {
                this.n.a(new IabResult(a, "Problem purchashing item."), null);
            }
        } else if (i2 == 0) {
            c("Purchase canceled - Response: " + a(a));
            iabResult = new IabResult(-1005, "User canceled.");
            if (this.n != null) {
                this.n.a(iabResult, null);
            }
        } else {
            d("Purchase failed. Result code: " + Integer.toString(i2) + ". Response: " + a(a));
            iabResult = new IabResult(-1006, "Unknown purchase response.");
            if (this.n != null) {
                this.n.a(iabResult, null);
            }
        }
        return true;
    }

    public Inventory a(boolean z, List<String> list) {
        return a(z, (List) list, null);
    }

    public Inventory a(boolean z, List<String> list, List<String> list2) {
        b();
        a("queryInventory");
        try {
            Inventory inventory = new Inventory();
            int a = a(inventory, "inapp");
            if (a != 0) {
                throw new IabException(a, "Error refreshing inventory (querying owned items).");
            }
            if (z) {
                a = a("inapp", inventory, (List) list);
                if (a != 0) {
                    throw new IabException(a, "Error refreshing inventory (querying prices of items).");
                }
            }
            if (this.e) {
                a = a(inventory, "subs");
                if (a != 0) {
                    throw new IabException(a, "Error refreshing inventory (querying owned subscriptions).");
                } else if (z) {
                    a = a("subs", inventory, (List) list);
                    if (a != 0) {
                        throw new IabException(a, "Error refreshing inventory (querying prices of subscriptions).");
                    }
                }
            }
            return inventory;
        } catch (Exception e) {
            throw new IabException(-1001, "Remote exception while refreshing inventory.", e);
        } catch (Exception e2) {
            throw new IabException(-1002, "Error parsing JSON response while refreshing inventory.", e2);
        }
    }

    public void a(boolean z, List<String> list, QueryInventoryFinishedListener queryInventoryFinishedListener) {
        final Handler handler = new Handler();
        b();
        a("queryInventory");
        b("refresh inventory");
        final boolean z2 = z;
        final List<String> list2 = list;
        final QueryInventoryFinishedListener queryInventoryFinishedListener2 = queryInventoryFinishedListener;
        new Thread(new Runnable(this) {
            final /* synthetic */ IabHelper e;

            public void run() {
                IabResult iabResult = new IabResult(0, "Inventory refresh successful.");
                Inventory inventory = null;
                try {
                    inventory = this.e.a(z2, list2);
                } catch (IabException e) {
                    iabResult = e.a();
                }
                this.e.a();
                if (!this.e.d && queryInventoryFinishedListener2 != null) {
                    handler.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 c;

                        public void run() {
                            queryInventoryFinishedListener2.a(iabResult, inventory);
                        }
                    });
                }
            }
        }).start();
    }

    public void a(QueryInventoryFinishedListener queryInventoryFinishedListener) {
        a(true, null, queryInventoryFinishedListener);
    }

    void a(Purchase purchase) {
        b();
        a("consume");
        if (purchase.a.equals("inapp")) {
            try {
                String d = purchase.d();
                String c = purchase.c();
                if (d == null || d.equals(BuildConfig.FLAVOR)) {
                    d("Can't consume " + c + ". No token.");
                    throw new IabException(-1007, "PurchaseInfo is missing token for sku: " + c + " " + purchase);
                }
                c("Consuming sku: " + c + ", token: " + d);
                int b = this.i.b(3, this.h.getPackageName(), d);
                if (b == 0) {
                    c("Successfully consumed sku: " + c);
                    return;
                } else {
                    c("Error consuming consuming sku " + c + ". " + a(b));
                    throw new IabException(b, "Error consuming sku " + c);
                }
            } catch (Exception e) {
                throw new IabException(-1001, "Remote exception while consuming. PurchaseInfo: " + purchase, e);
            }
        }
        throw new IabException(-1010, "Items of type '" + purchase.a + "' can't be consumed.");
    }

    public void a(Purchase purchase, OnConsumeFinishedListener onConsumeFinishedListener) {
        b();
        a("consume");
        List arrayList = new ArrayList();
        arrayList.add(purchase);
        a(arrayList, onConsumeFinishedListener, null);
    }

    public static String a(int i) {
        String[] split = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split("/");
        String[] split2 = "0:OK/-1001:Remote exception during initialization/-1002:Bad response received/-1003:Purchase signature verification failed/-1004:Send intent failed/-1005:User cancelled/-1006:Unknown purchase response/-1007:Missing token/-1008:Unknown error/-1009:Subscriptions not available/-1010:Invalid consumption attempt".split("/");
        if (i <= -1000) {
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

    void a(String str) {
        if (!this.c) {
            d("Illegal state for operation (" + str + "): IAB helper is not set up.");
            throw new IllegalStateException("IAB helper is not set up. Can't perform operation: " + str);
        }
    }

    int a(Bundle bundle) {
        Object obj = bundle.get("RESPONSE_CODE");
        if (obj == null) {
            c("Bundle with null response code, assuming OK (known issue)");
            return 0;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return (int) ((Long) obj).longValue();
            }
            d("Unexpected type for bundle response code.");
            d(obj.getClass().getName());
            throw new RuntimeException("Unexpected type for bundle response code: " + obj.getClass().getName());
        }
    }

    int a(Intent intent) {
        Object obj = intent.getExtras().get("RESPONSE_CODE");
        if (obj == null) {
            d("Intent with no response code, assuming OK (known issue)");
            return 0;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return (int) ((Long) obj).longValue();
            }
            d("Unexpected type for intent response code.");
            d(obj.getClass().getName());
            throw new RuntimeException("Unexpected type for intent response code: " + obj.getClass().getName());
        }
    }

    void b(String str) {
        if (this.f) {
            a();
        }
        if (this.f) {
            throw new IllegalStateException("Can't start async operation (" + str + ") because another async operation(" + this.g + ") is in progress.");
        }
        this.g = str;
        this.f = true;
        c("Starting async operation: " + str);
    }

    void a() {
        c("Ending async operation: " + this.g);
        this.g = BuildConfig.FLAVOR;
        this.f = false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    int a(jp.co.vaz.creator.hikaru2.InAppPurchase.util.Inventory r13, java.lang.String r14) {
        /*
        r12 = this;
        r3 = 0;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Querying owned items, item type: ";
        r0 = r0.append(r1);
        r0 = r0.append(r14);
        r0 = r0.toString();
        r12.c(r0);
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Package name: ";
        r0 = r0.append(r1);
        r1 = r12.h;
        r1 = r1.getPackageName();
        r0 = r0.append(r1);
        r0 = r0.toString();
        r12.c(r0);
        r0 = 0;
        r1 = r3;
    L_0x0035:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = "Calling getPurchases with continuation token: ";
        r2 = r2.append(r4);
        r2 = r2.append(r0);
        r2 = r2.toString();
        r12.c(r2);
        r2 = r12.i;
        r4 = 3;
        r5 = r12.h;
        r5 = r5.getPackageName();
        r6 = r2.a(r4, r5, r14, r0);
        r0 = r12.a(r6);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = "Owned items response: ";
        r2 = r2.append(r4);
        r4 = java.lang.String.valueOf(r0);
        r2 = r2.append(r4);
        r2 = r2.toString();
        r12.c(r2);
        if (r0 == 0) goto L_0x0094;
    L_0x0078:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "getPurchases() failed: ";
        r1 = r1.append(r2);
        r2 = a(r0);
        r1 = r1.append(r2);
        r1 = r1.toString();
        r12.c(r1);
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
        r12.d(r0);
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
        r10 = r12.m;
        r10 = jp.co.vaz.creator.hikaru2.InAppPurchase.util.Security.a(r10, r0, r1);
        if (r10 == 0) goto L_0x012f;
    L_0x00e8:
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r11 = "Sku is owned: ";
        r10 = r10.append(r11);
        r2 = r10.append(r2);
        r2 = r2.toString();
        r12.c(r2);
        r2 = new jp.co.vaz.creator.hikaru2.InAppPurchase.util.Purchase;
        r2.<init>(r14, r0, r1);
        r1 = r2.d();
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 == 0) goto L_0x0128;
    L_0x010d:
        r1 = "BUG: empty/null token!";
        r12.e(r1);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r10 = "Purchase data: ";
        r1 = r1.append(r10);
        r0 = r1.append(r0);
        r0 = r0.toString();
        r12.c(r0);
    L_0x0128:
        r13.a(r2);
    L_0x012b:
        r0 = r5 + 1;
        r5 = r0;
        goto L_0x00c8;
    L_0x012f:
        r2 = "Purchase signature verification **FAILED**. Not adding item.";
        r12.e(r2);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = "   Purchase data: ";
        r2 = r2.append(r4);
        r0 = r2.append(r0);
        r0 = r0.toString();
        r12.c(r0);
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = "   Signature: ";
        r0 = r0.append(r2);
        r0 = r0.append(r1);
        r0 = r0.toString();
        r12.c(r0);
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
        r12.c(r1);
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
        throw new UnsupportedOperationException("Method not decompiled: jp.co.vaz.creator.hikaru2.InAppPurchase.util.IabHelper.a(jp.co.vaz.creator.hikaru2.InAppPurchase.util.Inventory, java.lang.String):int");
    }

    int a(String str, Inventory inventory, List<String> list) {
        c("Querying SKU details.");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(inventory.d(str));
        if (list != null) {
            for (String str2 : list) {
                if (!arrayList.contains(str2)) {
                    arrayList.add(str2);
                }
            }
        }
        if (arrayList.size() == 0) {
            c("queryPrices: nothing to do because there are no SKUs.");
            return 0;
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("ITEM_ID_LIST", arrayList);
        bundle = this.i.a(3, this.h.getPackageName(), str, bundle);
        if (bundle.containsKey("DETAILS_LIST")) {
            Iterator it = bundle.getStringArrayList("DETAILS_LIST").iterator();
            while (it.hasNext()) {
                SkuDetails skuDetails = new SkuDetails(str, (String) it.next());
                c("Got sku details: " + skuDetails);
                inventory.a(skuDetails);
            }
            return 0;
        }
        int a = a(bundle);
        if (a != 0) {
            c("getSkuDetails() failed: " + a(a));
            return a;
        }
        d("getSkuDetails() returned a bundle with neither an error nor a detail list.");
        return -1002;
    }

    void a(List<Purchase> list, OnConsumeFinishedListener onConsumeFinishedListener, OnConsumeMultiFinishedListener onConsumeMultiFinishedListener) {
        final Handler handler = new Handler();
        b("consume");
        final List<Purchase> list2 = list;
        final OnConsumeFinishedListener onConsumeFinishedListener2 = onConsumeFinishedListener;
        final OnConsumeMultiFinishedListener onConsumeMultiFinishedListener2 = onConsumeMultiFinishedListener;
        new Thread(new Runnable(this) {
            final /* synthetic */ IabHelper e;

            public void run() {
                final List arrayList = new ArrayList();
                for (Purchase purchase : list2) {
                    try {
                        this.e.a(purchase);
                        arrayList.add(new IabResult(0, "Successful consume of sku " + purchase.c()));
                    } catch (IabException e) {
                        arrayList.add(e.a());
                    }
                }
                this.e.a();
                if (!(this.e.d || onConsumeFinishedListener2 == null)) {
                    handler.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass3 b;

                        public void run() {
                            onConsumeFinishedListener2.a((Purchase) list2.get(0), (IabResult) arrayList.get(0));
                        }
                    });
                }
                if (!this.e.d && onConsumeMultiFinishedListener2 != null) {
                    handler.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass3 b;

                        public void run() {
                            onConsumeMultiFinishedListener2.a(list2, arrayList);
                        }
                    });
                }
            }
        }).start();
    }

    void c(String str) {
        if (this.a) {
            Log.d(this.b, str);
        }
    }

    void d(String str) {
        Log.e(this.b, "In-app billing error: " + str);
    }

    void e(String str) {
        Log.w(this.b, "In-app billing warning: " + str);
    }
}
