package com.c.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

/* compiled from: IGameTuningService */
public interface a extends IInterface {

    /* compiled from: IGameTuningService */
    public static abstract class a extends Binder implements a {

        /* compiled from: IGameTuningService */
        private static class a implements a {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public int a(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
                    obtain.writeInt(i);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int b(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
                    obtain.writeInt(i);
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int c(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
                    obtain.writeInt(i);
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int a(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.enhance.gameservice.IGameTuningService");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    i = obtain2.readInt();
                    return i;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.enhance.gameservice.IGameTuningService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof a)) {
                return new a(iBinder);
            }
            return (a) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            int a;
            switch (i) {
                case TwitterResponse.READ /*1*/:
                    parcel.enforceInterface("com.enhance.gameservice.IGameTuningService");
                    a = a(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case TwitterResponse.READ_WRITE /*2*/:
                    parcel.enforceInterface("com.enhance.gameservice.IGameTuningService");
                    a = b(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    parcel.enforceInterface("com.enhance.gameservice.IGameTuningService");
                    a = c(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                    parcel.enforceInterface("com.enhance.gameservice.IGameTuningService");
                    a = a();
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                    parcel.enforceInterface("com.enhance.gameservice.IGameTuningService");
                    a = a(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(a);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.enhance.gameservice.IGameTuningService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    int a();

    int a(int i);

    int a(boolean z);

    int b(int i);

    int c(int i);
}
