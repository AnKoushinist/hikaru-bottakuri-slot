package com.igaworks.core;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.provider.Settings.Secure;
import com.tapjoy.TapjoyConstants;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class OpenUDID_manager implements ServiceConnection {
    private static String OpenUDID = null;
    private static boolean mInitialized = false;
    private final Context mContext;
    private List<ResolveInfo> mMatchingIntents;
    private final SharedPreferences mPreferences;
    private final Random mRandom;
    private Map<String, Integer> mReceivedOpenUDIDs;

    class AnonymousClass2 implements Runnable {
        private final /* synthetic */ Context val$context;

        AnonymousClass2(Context context) {
            this.val$context = context;
        }

        public void run() {
            try {
                OpenUDID_manager openUDID_manager = new OpenUDID_manager(this.val$context);
                OpenUDID_manager.OpenUDID = openUDID_manager.mPreferences.getString("openudid", null);
                if (OpenUDID_manager.OpenUDID == null) {
                    openUDID_manager.mMatchingIntents = this.val$context.getPackageManager().queryIntentServices(new Intent("org.OpenUDID.GETUDID"), 0);
                    if (openUDID_manager.mMatchingIntents != null) {
                        openUDID_manager.startService();
                        return;
                    }
                    return;
                }
                OpenUDID_manager.mInitialized = true;
            } catch (Exception e) {
            }
        }
    }

    private class ValueComparator implements Comparator {
        private ValueComparator() {
        }

        public int compare(Object obj, Object obj2) {
            if (((Integer) OpenUDID_manager.this.mReceivedOpenUDIDs.get(obj)).intValue() < ((Integer) OpenUDID_manager.this.mReceivedOpenUDIDs.get(obj2)).intValue()) {
                return 1;
            }
            if (OpenUDID_manager.this.mReceivedOpenUDIDs.get(obj) == OpenUDID_manager.this.mReceivedOpenUDIDs.get(obj2)) {
                return 0;
            }
            return -1;
        }
    }

    private OpenUDID_manager(Context context) {
        this.mPreferences = context.getSharedPreferences("openudid_prefs", 0);
        this.mContext = context;
        this.mRandom = new Random();
        this.mReceivedOpenUDIDs = new HashMap();
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            Parcel obtain = Parcel.obtain();
            obtain.writeInt(this.mRandom.nextInt());
            Parcel obtain2 = Parcel.obtain();
            iBinder.transact(1, Parcel.obtain(), obtain2, 0);
            if (obtain.readInt() == obtain2.readInt()) {
                String readString = obtain2.readString();
                if (readString != null) {
                    if (this.mReceivedOpenUDIDs.containsKey(readString)) {
                        this.mReceivedOpenUDIDs.put(readString, Integer.valueOf(((Integer) this.mReceivedOpenUDIDs.get(readString)).intValue() + 1));
                    } else {
                        this.mReceivedOpenUDIDs.put(readString, Integer.valueOf(1));
                    }
                }
            }
        } catch (RemoteException e) {
        }
        try {
            this.mContext.unbindService(this);
            startService();
        } catch (Exception e2) {
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }

    private void storeOpenUDID() {
        new Thread(new Runnable() {
            public void run() {
                Editor edit = OpenUDID_manager.this.mPreferences.edit();
                edit.putString("openudid", OpenUDID_manager.OpenUDID);
                edit.commit();
            }
        }).start();
    }

    private void generateOpenUDID() {
        OpenUDID = Secure.getString(this.mContext.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
        if (OpenUDID == null || OpenUDID.equals("9774d56d682e549c") || OpenUDID.length() < 15) {
            OpenUDID = new BigInteger(64, new SecureRandom()).toString(16);
        }
    }

    private void startService() {
        try {
            if (this.mMatchingIntents.size() > 0) {
                ServiceInfo serviceInfo = ((ResolveInfo) this.mMatchingIntents.get(0)).serviceInfo;
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(serviceInfo.applicationInfo.packageName, serviceInfo.name));
                this.mMatchingIntents.clear();
                this.mContext.bindService(intent, this, 1);
                return;
            }
            getMostFrequentOpenUDID();
            if (OpenUDID == null) {
                generateOpenUDID();
            }
            storeOpenUDID();
            mInitialized = true;
        } catch (Exception e) {
            startService();
        }
    }

    private void getMostFrequentOpenUDID() {
        if (!this.mReceivedOpenUDIDs.isEmpty()) {
            TreeMap treeMap = new TreeMap(new ValueComparator());
            treeMap.putAll(this.mReceivedOpenUDIDs);
            OpenUDID = (String) treeMap.firstKey();
        }
    }

    public static String getOpenUDID() {
        return OpenUDID;
    }

    public static boolean isInitialized() {
        return mInitialized;
    }

    public static void sync(Context context) {
        Thread thread = new Thread(new AnonymousClass2(context));
    }
}
