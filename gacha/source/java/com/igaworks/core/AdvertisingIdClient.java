package com.igaworks.core;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences.Editor;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class AdvertisingIdClient {
    private static AdInfo adInfo;
    private static List<ADIDCallbackListener> adidListeners;
    private static boolean onBind = false;

    public interface ADIDCallbackListener {
        void onResult(AdInfo adInfo);
    }

    class AnonymousClass1 implements Runnable {
        private final /* synthetic */ String val$adId;
        private final /* synthetic */ Context val$context;

        AnonymousClass1(Context context, String str) {
            this.val$context = context;
            this.val$adId = str;
        }

        public void run() {
            Editor edit = this.val$context.getSharedPreferences("adpopcorn_parameter", 0).edit();
            edit.putString("google_ad_id", this.val$adId);
            edit.commit();
        }
    }

    public static final class AdInfo {
        private final String advertisingId;
        private final boolean limitAdTrackingEnabled;

        AdInfo(String str, boolean z) {
            this.advertisingId = str;
            this.limitAdTrackingEnabled = z;
        }

        public String getId() {
            return this.advertisingId;
        }

        public boolean isLimitAdTrackingEnabled() {
            return this.limitAdTrackingEnabled;
        }
    }

    private static final class AdvertisingConnection implements ServiceConnection {
        private final LinkedBlockingQueue<IBinder> queue;
        boolean retrieved;

        private AdvertisingConnection() {
            this.retrieved = false;
            this.queue = new LinkedBlockingQueue(1);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.queue.put(iBinder);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public IBinder getBinder() {
            if (this.retrieved) {
                throw new IllegalStateException();
            }
            this.retrieved = true;
            return (IBinder) this.queue.take();
        }
    }

    private static final class AdvertisingInterface implements IInterface {
        private IBinder binder;

        public AdvertisingInterface(IBinder iBinder) {
            this.binder = iBinder;
        }

        public IBinder asBinder() {
            return this.binder;
        }

        public String getId() {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.binder.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                return readString;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }

        public boolean isLimitAdTrackingEnabled(boolean z) {
            boolean z2 = true;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                obtain.writeInt(z ? 1 : 0);
                this.binder.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() == 0) {
                    z2 = false;
                }
                obtain2.recycle();
                obtain.recycle();
                return z2;
            } catch (Throwable th) {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    public static void registADIDListener(ADIDCallbackListener aDIDCallbackListener) {
        if (adidListeners == null) {
            adidListeners = new ArrayList();
        }
        if (!adidListeners.contains(aDIDCallbackListener)) {
            adidListeners.add(aDIDCallbackListener);
        }
    }

    public static AdInfo getAdvertisingIdInfo(Context context, ADIDCallbackListener aDIDCallbackListener) {
        ServiceConnection advertisingConnection;
        List<ADIDCallbackListener> arrayList;
        try {
            if (adInfo != null) {
                if (aDIDCallbackListener != null) {
                    aDIDCallbackListener.onResult(adInfo);
                }
                return adInfo;
            } else if (Looper.myLooper() == Looper.getMainLooper()) {
                if (aDIDCallbackListener != null) {
                    aDIDCallbackListener.onResult(null);
                }
                throw new IllegalStateException("Cannot be called from the main thread");
            } else {
                try {
                    context.getPackageManager().getPackageInfo("com.android.vending", 0);
                    if (onBind) {
                        IgawLogger.Logging(context, "IGAW_QA", "onBind > com.google.android.gms", 3, true);
                        if (aDIDCallbackListener != null) {
                            IgawLogger.Logging(context, "IGAW_QA", "onBind > add to adidListener.", 3, true);
                            registADIDListener(aDIDCallbackListener);
                        }
                        return null;
                    }
                    onBind = true;
                    try {
                        advertisingConnection = new AdvertisingConnection();
                        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
                        intent.setPackage("com.google.android.gms");
                        if (context.bindService(intent, advertisingConnection, 1)) {
                            AdvertisingInterface advertisingInterface = new AdvertisingInterface(advertisingConnection.getBinder());
                            adInfo = new AdInfo(advertisingInterface.getId(), advertisingInterface.isLimitAdTrackingEnabled(true));
                            String id = adInfo.getId();
                            if (id != null && id.length() > 0) {
                                new Thread(new AnonymousClass1(context, id)).start();
                            }
                            AdInfo adInfo = adInfo;
                            context.unbindService(advertisingConnection);
                            onBind = false;
                            onBind = false;
                            IgawLogger.Logging(context, "IGAW_QA", "onBind > adid request complete, send callback request to listeners.", 3, true);
                            if (aDIDCallbackListener != null) {
                                try {
                                    aDIDCallbackListener.onResult(adInfo);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (adidListeners != null && adidListeners.size() > 0) {
                                arrayList = new ArrayList(adidListeners);
                                IgawLogger.Logging(context, "IGAW_QA", "onBind > adidListeners size : " + adidListeners.size(), 3, true);
                                adidListeners.clear();
                                IgawLogger.Logging(context, "IGAW_QA", "onBind > adidListeners size(after clear) : " + adidListeners.size(), 3, true);
                                IgawLogger.Logging(context, "IGAW_QA", "onBind > tList size : " + arrayList.size(), 3, true);
                                for (ADIDCallbackListener aDIDCallbackListener2 : arrayList) {
                                    IgawLogger.Logging(context, "IGAW_QA", "onBind > send adInfo to adidListeners", 3, true);
                                    aDIDCallbackListener2.onResult(adInfo);
                                }
                                arrayList.clear();
                            }
                            return adInfo;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        context.unbindService(advertisingConnection);
                        onBind = false;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        onBind = false;
                        IgawLogger.Logging(context, "IGAW_QA", "onBind > adid request complete, send callback request to listeners.", 3, true);
                        if (aDIDCallbackListener != null) {
                            try {
                                aDIDCallbackListener.onResult(adInfo);
                            } catch (Exception e22) {
                                e22.printStackTrace();
                            }
                        }
                        if (adidListeners != null && adidListeners.size() > 0) {
                            arrayList = new ArrayList(adidListeners);
                            IgawLogger.Logging(context, "IGAW_QA", "onBind > adidListeners size : " + adidListeners.size(), 3, true);
                            adidListeners.clear();
                            IgawLogger.Logging(context, "IGAW_QA", "onBind > adidListeners size(after clear) : " + adidListeners.size(), 3, true);
                            IgawLogger.Logging(context, "IGAW_QA", "onBind > tList size : " + arrayList.size(), 3, true);
                            for (ADIDCallbackListener aDIDCallbackListener22 : arrayList) {
                                IgawLogger.Logging(context, "IGAW_QA", "onBind > send adInfo to adidListeners", 3, true);
                                aDIDCallbackListener22.onResult(adInfo);
                            }
                            arrayList.clear();
                        }
                    }
                    throw new IOException("Google Play connection failed");
                } catch (Exception e3) {
                    if (aDIDCallbackListener != null) {
                        aDIDCallbackListener.onResult(null);
                    }
                    return null;
                }
            }
        } catch (Exception e222) {
            if (e222 != null) {
                IgawLogger.Logging(context, "IGAW_QA", e222.toString(), 0, true);
            }
            return null;
        }
    }
}
