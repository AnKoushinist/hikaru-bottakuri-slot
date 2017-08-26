package com.onesignal;

import android.content.Context;
import com.amazon.device.iap.PurchasingListener;
import com.amazon.device.iap.PurchasingService;
import com.amazon.device.iap.model.ProductDataResponse.RequestStatus;
import java.lang.reflect.Field;

class TrackAmazonPurchase {
    private boolean canTrack = false;
    private Context context;
    private OSPurchasingListener gtPurchasingListener;
    private Class<?> listnerHandlerClass;
    private Field listnerHandlerField;
    private Object listnerHandlerObject;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$iap$model$ProductDataResponse$RequestStatus = new int[RequestStatus.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$iap$model$ProductDataResponse$RequestStatus[RequestStatus.SUCCESSFUL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    private class OSPurchasingListener implements PurchasingListener {
        public PurchasingListener orgPurchasingListener;

        private OSPurchasingListener() {
        }
    }

    TrackAmazonPurchase(Context context) {
        this.context = context;
        try {
            this.listnerHandlerClass = Class.forName("com.amazon.device.iap.internal.d");
            this.listnerHandlerObject = this.listnerHandlerClass.getMethod("d", new Class[0]).invoke(null, new Object[0]);
            this.listnerHandlerField = this.listnerHandlerClass.getDeclaredField("f");
            this.listnerHandlerField.setAccessible(true);
            this.gtPurchasingListener = new OSPurchasingListener();
            this.gtPurchasingListener.orgPurchasingListener = (PurchasingListener) this.listnerHandlerField.get(this.listnerHandlerObject);
            this.canTrack = true;
            setListener();
        } catch (Throwable th) {
        }
    }

    private void setListener() {
        PurchasingService.registerListener(this.context, this.gtPurchasingListener);
    }

    public void checkListener() {
        if (this.canTrack) {
            try {
                PurchasingListener purchasingListener = (PurchasingListener) this.listnerHandlerField.get(this.listnerHandlerObject);
                if (purchasingListener != this.gtPurchasingListener) {
                    this.gtPurchasingListener.orgPurchasingListener = purchasingListener;
                    setListener();
                }
            } catch (Throwable th) {
            }
        }
    }
}
