package com.igaworks.commerce.impl;

import android.content.Context;
import com.igaworks.commerce.db.CommerceEventDAO;
import com.igaworks.commerce.db.PurchaseRetryDAO;
import com.igaworks.commerce.interfaces.CommerceInterface;
import com.igaworks.commerce.net.CommerceHttpManager;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import com.igaworks.impl.CommonFrameworkImpl;
import com.igaworks.interfaces.ExtendedCommonActivityListener;
import java.util.List;

public class CommerceImpl implements CommerceInterface, ExtendedCommonActivityListener {
    private static Context context;
    private static CommerceHttpManager httpManager = new CommerceHttpManager();

    protected CommerceImpl() {
        CommonFrameworkImpl.addExtendedActivityListener("Commerce", this);
    }

    public void onStartSession(final Context context, final RequestParameter requestParameter, final boolean z) {
        if (context == null) {
            context = CommonFrameworkImpl.getContext();
        }
        new Thread(new Runnable() {
            public void run() {
                try {
                    if (!z) {
                        List retryPurchase;
                        try {
                            retryPurchase = PurchaseRetryDAO.getDAO(context).getRetryPurchase();
                            if (retryPurchase != null && retryPurchase.size() > 0) {
                                IgawLogger.Logging(context, "IGAW_QA", "Retry Purchase - count : " + retryPurchase.size(), 2, true);
                                CommerceImpl.httpManager.purchaseForCommerce(requestParameter, context, retryPurchase);
                            }
                        } catch (Exception e) {
                            IgawLogger.Logging(context, "IGAW_QA", "Retry Purchase error : " + e.toString(), 0, false);
                        }
                        retryPurchase = CommerceEventDAO.getEvents(context);
                        if (retryPurchase != null && retryPurchase.size() > 0) {
                            CommerceImpl.httpManager.customEventForCommerce(requestParameter, context, retryPurchase);
                        }
                    }
                } catch (Exception e2) {
                    IgawLogger.Logging(context, "IGAW_QA", e2.toString(), 0, false);
                }
            }
        }).start();
    }

    public void onActivityCalled(Context context, String str, String str2, RequestParameter requestParameter) {
    }

    public void onGetReferralResponse(Context context, String str) {
    }

    public void onEndSession(final Context context, final int i) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    if (i == 0) {
                        List events = CommerceEventDAO.getEvents(context);
                        if (events != null && events.size() > 0) {
                            CommerceImpl.httpManager.customEventForCommerce(RequestParameter.getATRequestParameter(context), context, events);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
