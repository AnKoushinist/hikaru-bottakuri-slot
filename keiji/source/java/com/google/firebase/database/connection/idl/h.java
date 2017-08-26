package com.google.firebase.database.connection.idl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public interface h extends IInterface {

    public static abstract class a extends Binder implements h {

        private static class a implements h {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public void a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(com.google.android.gms.b.a aVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    this.a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(List<String> list, com.google.android.gms.b.a aVar, boolean z, long j) {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(List<String> list, List<zzn> list2, com.google.android.gms.b.a aVar, long j) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
                    obtain.writeStringList(list);
                    obtain.writeTypedList(list2);
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    obtain.writeLong(j);
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(boolean z) {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.a;
            }

            public void b() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
        }

        public static h a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof h)) ? new a(iBinder) : (h) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            boolean z = false;
            switch (i) {
                case TwitterResponse.READ /*1*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
                    List createStringArrayList = parcel.createStringArrayList();
                    com.google.android.gms.b.a a = com.google.android.gms.b.a.a.a(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    a(createStringArrayList, a, z, parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case TwitterResponse.READ_WRITE /*2*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
                    a(parcel.createStringArrayList(), parcel.createTypedArrayList(zzn.CREATOR), com.google.android.gms.b.a.a.a(parcel.readStrongBinder()), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
                    a();
                    parcel2.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
                    b();
                    parcel2.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    a(z);
                    parcel2.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
                    a(com.google.android.gms.b.a.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.firebase.database.connection.idl.IPersistentConnectionDelegate");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void a();

    void a(com.google.android.gms.b.a aVar);

    void a(List<String> list, com.google.android.gms.b.a aVar, boolean z, long j);

    void a(List<String> list, List<zzn> list2, com.google.android.gms.b.a aVar, long j);

    void a(boolean z);

    void b();
}
