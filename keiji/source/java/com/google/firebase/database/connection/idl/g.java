package com.google.firebase.database.connection.idl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.List;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public interface g extends IInterface {

    public static abstract class a extends Binder implements g {

        private static class a implements g {
            private IBinder a;

            a(IBinder iBinder) {
                this.a = iBinder;
            }

            public IBinder asBinder() {
                return this.a;
            }

            public void compareAndPut(List<String> list, com.google.android.gms.b.a aVar, String str, i iVar) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    obtain.writeString(str);
                    if (iVar != null) {
                        iBinder = iVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void initialize() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    this.a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void interrupt(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    obtain.writeString(str);
                    this.a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isInterrupted(String str) {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    obtain.writeString(str);
                    this.a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void listen(List<String> list, com.google.android.gms.b.a aVar, f fVar, long j, i iVar) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    obtain.writeStrongBinder(fVar != null ? fVar.asBinder() : null);
                    obtain.writeLong(j);
                    if (iVar != null) {
                        iBinder = iVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void merge(List<String> list, com.google.android.gms.b.a aVar, i iVar) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (iVar != null) {
                        iBinder = iVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onDisconnectCancel(List<String> list, i iVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(iVar != null ? iVar.asBinder() : null);
                    this.a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onDisconnectMerge(List<String> list, com.google.android.gms.b.a aVar, i iVar) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (iVar != null) {
                        iBinder = iVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onDisconnectPut(List<String> list, com.google.android.gms.b.a aVar, i iVar) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (iVar != null) {
                        iBinder = iVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void purgeOutstandingWrites() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    this.a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void put(List<String> list, com.google.android.gms.b.a aVar, i iVar) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (iVar != null) {
                        iBinder = iVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void refreshAuthToken() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    this.a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void refreshAuthToken2(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    obtain.writeString(str);
                    this.a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resume(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    obtain.writeString(str);
                    this.a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setup(zzc com_google_firebase_database_connection_idl_zzc, d dVar, com.google.android.gms.b.a aVar, h hVar) {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    if (com_google_firebase_database_connection_idl_zzc != null) {
                        obtain.writeInt(1);
                        com_google_firebase_database_connection_idl_zzc.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (hVar != null) {
                        iBinder = hVar.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void shutdown() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    this.a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unlisten(List<String> list, com.google.android.gms.b.a aVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.firebase.database.connection.idl.IPersistentConnection");
                    obtain.writeStringList(list);
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    this.a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "com.google.firebase.database.connection.idl.IPersistentConnection");
        }

        public static g asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof g)) ? new a(iBinder) : (g) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case TwitterResponse.READ /*1*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    setup(parcel.readInt() != 0 ? (zzc) zzc.CREATOR.createFromParcel(parcel) : null, com.google.firebase.database.connection.idl.d.a.a(parcel.readStrongBinder()), com.google.android.gms.b.a.a.a(parcel.readStrongBinder()), com.google.firebase.database.connection.idl.h.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TwitterResponse.READ_WRITE /*2*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    initialize();
                    parcel2.writeNoException();
                    return true;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    shutdown();
                    parcel2.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    refreshAuthToken();
                    parcel2.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    listen(parcel.createStringArrayList(), com.google.android.gms.b.a.a.a(parcel.readStrongBinder()), com.google.firebase.database.connection.idl.f.a.a(parcel.readStrongBinder()), parcel.readLong(), com.google.firebase.database.connection.idl.i.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    unlisten(parcel.createStringArrayList(), com.google.android.gms.b.a.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    purgeOutstandingWrites();
                    parcel2.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    put(parcel.createStringArrayList(), com.google.android.gms.b.a.a.a(parcel.readStrongBinder()), com.google.firebase.database.connection.idl.i.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    compareAndPut(parcel.createStringArrayList(), com.google.android.gms.b.a.a.a(parcel.readStrongBinder()), parcel.readString(), com.google.firebase.database.connection.idl.i.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    merge(parcel.createStringArrayList(), com.google.android.gms.b.a.a.a(parcel.readStrongBinder()), com.google.firebase.database.connection.idl.i.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.styleable.Toolbar_popupTheme /*11*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    onDisconnectPut(parcel.createStringArrayList(), com.google.android.gms.b.a.a.a(parcel.readStrongBinder()), com.google.firebase.database.connection.idl.i.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.styleable.Toolbar_titleTextAppearance /*12*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    onDisconnectMerge(parcel.createStringArrayList(), com.google.android.gms.b.a.a.a(parcel.readStrongBinder()), com.google.firebase.database.connection.idl.i.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.styleable.Toolbar_subtitleTextAppearance /*13*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    onDisconnectCancel(parcel.createStringArrayList(), com.google.firebase.database.connection.idl.i.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case R.styleable.Toolbar_titleMargin /*14*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    interrupt(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case R.styleable.Toolbar_titleMarginStart /*15*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    resume(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case R.styleable.Toolbar_titleMarginEnd /*16*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    boolean isInterrupted = isInterrupted(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(isInterrupted ? 1 : 0);
                    return true;
                case R.styleable.Toolbar_titleMarginTop /*17*/:
                    parcel.enforceInterface("com.google.firebase.database.connection.idl.IPersistentConnection");
                    refreshAuthToken2(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.firebase.database.connection.idl.IPersistentConnection");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void compareAndPut(List<String> list, com.google.android.gms.b.a aVar, String str, i iVar);

    void initialize();

    void interrupt(String str);

    boolean isInterrupted(String str);

    void listen(List<String> list, com.google.android.gms.b.a aVar, f fVar, long j, i iVar);

    void merge(List<String> list, com.google.android.gms.b.a aVar, i iVar);

    void onDisconnectCancel(List<String> list, i iVar);

    void onDisconnectMerge(List<String> list, com.google.android.gms.b.a aVar, i iVar);

    void onDisconnectPut(List<String> list, com.google.android.gms.b.a aVar, i iVar);

    void purgeOutstandingWrites();

    void put(List<String> list, com.google.android.gms.b.a aVar, i iVar);

    void refreshAuthToken();

    void refreshAuthToken2(String str);

    void resume(String str);

    void setup(zzc com_google_firebase_database_connection_idl_zzc, d dVar, com.google.android.gms.b.a aVar, h hVar);

    void shutdown();

    void unlisten(List<String> list, com.google.android.gms.b.a aVar);
}
