package com.igaworks.commerce.net;

import android.content.Context;
import com.igaworks.commerce.core.CommerceRequestParameter;
import com.igaworks.commerce.db.CommerceEventDAO;
import com.igaworks.commerce.db.DemographicDAO;
import com.igaworks.commerce.db.PurchaseRetryDAO;
import com.igaworks.commerce.model.PurchaseItem;
import com.igaworks.core.AESGetTrackParam;
import com.igaworks.core.AdvertisingIdClient.ADIDCallbackListener;
import com.igaworks.core.AdvertisingIdClient.AdInfo;
import com.igaworks.core.DeviceIDManger;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import com.igaworks.interfaces.HttpCallbackListener;
import com.igaworks.net.CommonHttpManager;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommerceHttpManager extends CommonHttpManager {
    public static String cmc_domain = "http://commerce.ad-brix.com/v1/";
    public String EVENT_REQUEST_URL_FOR_Commerce = (cmc_domain + "tracking/customEvents");
    public String PURCHASE_REQUEST_URL_FOR_Commerce = (cmc_domain + "tracking/purchases");

    public void purchaseForCommerce(final RequestParameter requestParameter, final Context context, final List<PurchaseItem> list) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    IgawLogger.Logging(context, "IGAW_QA", "purchaseForCommerce", 3);
                    DeviceIDManger instance = DeviceIDManger.getInstance(context);
                    Context context = context;
                    final List list = list;
                    final RequestParameter requestParameter = requestParameter;
                    final Context context2 = context;
                    instance.getAndroidADID(context, new ADIDCallbackListener() {
                        public void onResult(AdInfo adInfo) {
                            String str = CommerceHttpManager.this.PURCHASE_REQUEST_URL_FOR_Commerce;
                            JSONArray jSONArray = new JSONArray();
                            for (PurchaseItem purchaseItem : list) {
                                try {
                                    String[] strArr = new String[5];
                                    String[] split = purchaseItem.getCategory() != null ? purchaseItem.getCategory().split("\\.") : new String[0];
                                    for (int i = 0; i < split.length; i++) {
                                        strArr[i] = split[i];
                                    }
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put("ak", requestParameter.getAppkey());
                                    jSONObject.put("adid", adInfo.getId());
                                    jSONObject.put("usn", DemographicDAO.getDemographic(context2, "userId"));
                                    jSONObject.put("emailhash", DemographicDAO.getDemographic(context2, "email"));
                                    jSONObject.put("orderId", purchaseItem.getOrderID());
                                    jSONObject.put("productId", purchaseItem.getProductID());
                                    jSONObject.put("price", purchaseItem.getPrice());
                                    jSONObject.put("currency", purchaseItem.getCurrency());
                                    jSONObject.put("category1", strArr[0]);
                                    jSONObject.put("category2", strArr[1]);
                                    jSONObject.put("category3", strArr[2]);
                                    jSONObject.put("category4", strArr[3]);
                                    jSONObject.put("category5", strArr[4]);
                                    jSONObject.put("quantity", purchaseItem.getQuantity());
                                    jSONObject.put("productName", purchaseItem.getProductName());
                                    jSONObject.put("mtime", new Date().getTime());
                                    jSONArray.put(jSONObject);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            try {
                                Context context = context2;
                                String jSONArray2 = jSONArray.toString();
                                final Context context2 = context2;
                                final List list = list;
                                WeakReference weakReference = new WeakReference(new CommerceHttpThread(context, 1, str, jSONArray2, new HttpCallbackListener() {
                                    public void callback(String str) {
                                        if (str != null) {
                                            try {
                                                if (!str.equals(BuildConfig.FLAVOR)) {
                                                    IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, commerce purchase response result : " + str, 3, false);
                                                    JSONObject jSONObject = new JSONObject(str);
                                                    if (jSONObject.has("errMsg") && jSONObject.isNull("errMsg")) {
                                                        PurchaseRetryDAO.getDAO(context2).clearRetryItems();
                                                        return;
                                                    } else {
                                                        CommerceHttpManager.this.restorePurchaseInfo(context2, list);
                                                        return;
                                                    }
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                CommerceHttpManager.this.restorePurchaseInfo(context2, list);
                                                new Throwable().getStackTrace();
                                                IgawLogger.Logging(context2, "IGAW_QA", e.getMessage(), 0);
                                                return;
                                            }
                                        }
                                        CommerceHttpManager.this.restorePurchaseInfo(context2, list);
                                        throw new Exception("responseResult null Error");
                                    }
                                }));
                                ((Thread) weakReference.get()).setDaemon(true);
                                ((Thread) weakReference.get()).start();
                            } catch (Exception e2) {
                                CommerceHttpManager.this.restorePurchaseInfo(context2, list);
                                e2.printStackTrace();
                                IgawLogger.Logging(context2, "IGAW_QA", e2.toString(), 0);
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    IgawLogger.Logging(context, "IGAW_QA", e.toString(), 0);
                }
            }
        }).start();
    }

    public void customEventForCommerce(RequestParameter requestParameter, final Context context, final List<String> list) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    IgawLogger.Logging(context, "IGAW_QA", "customEventForCommerce", 3);
                    DeviceIDManger instance = DeviceIDManger.getInstance(context);
                    Context context = context;
                    final Context context2 = context;
                    final List list = list;
                    instance.getAndroidADID(context, new ADIDCallbackListener() {
                        public void onResult(AdInfo adInfo) {
                            String str;
                            String str2 = CommerceHttpManager.this.EVENT_REQUEST_URL_FOR_Commerce;
                            if (adInfo == null) {
                                try {
                                    str = BuildConfig.FLAVOR;
                                } catch (Exception e) {
                                    CommerceHttpManager.this.restoreCEventInfo(context2, list);
                                    e.printStackTrace();
                                    IgawLogger.Logging(context2, "IGAW_QA", e.toString(), 0);
                                    return;
                                }
                            }
                            str = adInfo.getId();
                            String encrypt = AESGetTrackParam.encrypt(CommerceRequestParameter.getCommerceEventParameter(context2, str, adInfo == null ? false : adInfo.isLimitAdTrackingEnabled(), list), BuildConfig.FLAVOR);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("j", encrypt);
                            Context context = context2;
                            String jSONObject2 = jSONObject.toString();
                            final Context context2 = context2;
                            final List list = list;
                            WeakReference weakReference = new WeakReference(new CommerceHttpThread(context, 1, str2, jSONObject2, new HttpCallbackListener() {
                                public void callback(String str) {
                                    if (str != null) {
                                        try {
                                            if (!str.equals(BuildConfig.FLAVOR)) {
                                                IgawLogger.Logging(context2, "IGAW_QA", "ADBrixTracer, commerce event response result : " + str, 3, false);
                                                JSONObject jSONObject = new JSONObject(str);
                                                if (!jSONObject.has("errMsg") || !jSONObject.isNull("errMsg")) {
                                                    CommerceHttpManager.this.restoreCEventInfo(context2, list);
                                                    return;
                                                }
                                                return;
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            CommerceHttpManager.this.restoreCEventInfo(context2, list);
                                            new Throwable().getStackTrace();
                                            IgawLogger.Logging(context2, "IGAW_QA", e.getMessage(), 0);
                                            return;
                                        }
                                    }
                                    CommerceHttpManager.this.restoreCEventInfo(context2, list);
                                    throw new Exception("responseResult null Error");
                                }
                            }));
                            ((Thread) weakReference.get()).setDaemon(true);
                            ((Thread) weakReference.get()).start();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    CommerceHttpManager.this.restoreCEventInfo(context, list);
                    IgawLogger.Logging(context, "IGAW_QA", e.toString(), 0);
                }
            }
        }).start();
    }

    private void restorePurchaseInfo(Context context, List<PurchaseItem> list) {
        PurchaseRetryDAO dao = PurchaseRetryDAO.getDAO(context);
        for (PurchaseItem purchaseItem : list) {
            if (purchaseItem.getRetryCnt() > 2) {
                dao.removeRetryCount(purchaseItem.getKey());
            } else {
                dao.updateOrInsertConversionForRetry(purchaseItem.getKey(), purchaseItem.getOrderID(), purchaseItem.getProductID(), purchaseItem.getProductName(), purchaseItem.getPrice(), purchaseItem.getQuantity(), purchaseItem.getCurrency(), purchaseItem.getCategory(), purchaseItem.getCreatedAt());
            }
        }
    }

    private void restoreCEventInfo(Context context, List<String> list) {
        CommerceEventDAO.addEvents(context, list);
    }
}
