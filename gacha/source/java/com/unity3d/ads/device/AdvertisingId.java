package com.unity3d.ads.device;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.unity3d.ads.log.DeviceLog;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import twitter4j.TwitterResponse;

@TargetApi(9)
public class AdvertisingId {
    private static final String ADVERTISING_ID_SERVICE_NAME = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";
    private static AdvertisingId instance = null;
    private String advertisingIdentifier = null;
    private boolean limitedAdvertisingTracking = false;

    private interface GoogleAdvertisingInfo extends IInterface {

        public static abstract class GoogleAdvertisingInfoBinder extends Binder implements GoogleAdvertisingInfo {

            private static class GoogleAdvertisingInfoImplementation implements GoogleAdvertisingInfo {
                private final IBinder _binder;

                GoogleAdvertisingInfoImplementation(IBinder iBinder) {
                    this._binder = iBinder;
                }

                public IBinder asBinder() {
                    return this._binder;
                }

                public String getId() {
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken(AdvertisingId.ADVERTISING_ID_SERVICE_NAME);
                        this._binder.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        String readString = obtain2.readString();
                        return readString;
                    } finally {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                }

                public boolean getEnabled(boolean z) {
                    boolean z2 = true;
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken(AdvertisingId.ADVERTISING_ID_SERVICE_NAME);
                        obtain.writeInt(z ? 1 : 0);
                        this._binder.transact(2, obtain, obtain2, 0);
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

            public static GoogleAdvertisingInfo create(IBinder iBinder) {
                if (iBinder == null) {
                    return null;
                }
                IInterface queryLocalInterface = iBinder.queryLocalInterface(AdvertisingId.ADVERTISING_ID_SERVICE_NAME);
                if (queryLocalInterface == null || !(queryLocalInterface instanceof GoogleAdvertisingInfo)) {
                    return new GoogleAdvertisingInfoImplementation(iBinder);
                }
                return (GoogleAdvertisingInfo) queryLocalInterface;
            }

            public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
                int i3 = 0;
                switch (i) {
                    case TwitterResponse.READ /*1*/:
                        parcel.enforceInterface(AdvertisingId.ADVERTISING_ID_SERVICE_NAME);
                        String id = getId();
                        parcel2.writeNoException();
                        parcel2.writeString(id);
                        return true;
                    case TwitterResponse.READ_WRITE /*2*/:
                        boolean z;
                        parcel.enforceInterface(AdvertisingId.ADVERTISING_ID_SERVICE_NAME);
                        if (parcel.readInt() != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z = getEnabled(z);
                        parcel2.writeNoException();
                        if (z) {
                            i3 = 1;
                        }
                        parcel2.writeInt(i3);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
        }

        boolean getEnabled(boolean z);

        String getId();
    }

    private class GoogleAdvertisingServiceConnection implements ServiceConnection {
        private final BlockingQueue<IBinder> _binderQueue;
        boolean _consumed;

        private GoogleAdvertisingServiceConnection() {
            this._consumed = false;
            this._binderQueue = new LinkedBlockingQueue();
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this._binderQueue.put(iBinder);
            } catch (InterruptedException e) {
                DeviceLog.debug("Couldn't put service to binder que");
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }

        public IBinder getBinder() {
            if (this._consumed) {
                throw new IllegalStateException();
            }
            this._consumed = true;
            return (IBinder) this._binderQueue.take();
        }
    }

    private static AdvertisingId getInstance() {
        if (instance == null) {
            instance = new AdvertisingId();
        }
        return instance;
    }

    public static void init(Context context) {
        getInstance().fetchAdvertisingId(context);
    }

    public static String getAdvertisingTrackingId() {
        return getInstance().advertisingIdentifier;
    }

    public static boolean getLimitedAdTracking() {
        return getInstance().limitedAdvertisingTracking;
    }

    private void fetchAdvertisingId(Context context) {
        ServiceConnection googleAdvertisingServiceConnection = new GoogleAdvertisingServiceConnection();
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, googleAdvertisingServiceConnection, 1)) {
            try {
                GoogleAdvertisingInfo create = GoogleAdvertisingInfoBinder.create(googleAdvertisingServiceConnection.getBinder());
                this.advertisingIdentifier = create.getId();
                this.limitedAdvertisingTracking = create.getEnabled(true);
            } catch (Exception e) {
                DeviceLog.exception("Couldn't get advertising info", e);
            } finally {
                context.unbindService(googleAdvertisingServiceConnection);
            }
        }
    }
}
