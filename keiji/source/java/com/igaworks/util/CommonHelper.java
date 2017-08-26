package com.igaworks.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.util.Log;
import com.igaworks.core.IgawLogger;
import com.igaworks.dao.AppImpressionDAO;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class CommonHelper {
    public static int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;

    class AnonymousClass1 implements Runnable {
        private final /* synthetic */ Activity val$openAct;
        private final /* synthetic */ List val$permissionsList;
        private final /* synthetic */ List val$permissionsNeeded;

        AnonymousClass1(List list, List list2, Activity activity) {
            this.val$permissionsList = list;
            this.val$permissionsNeeded = list2;
            this.val$openAct = activity;
        }

        public void run() {
            if (this.val$permissionsList.size() > 0 && this.val$permissionsNeeded.size() > 0) {
                this.val$openAct.requestPermissions((String[]) this.val$permissionsList.toArray(new String[this.val$permissionsList.size()]), CommonHelper.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            }
        }
    }

    class AnonymousClass2 implements Runnable {
        private final /* synthetic */ Activity val$openAct;
        private final /* synthetic */ List val$permissionsNeeded;

        AnonymousClass2(List list, Activity activity) {
            this.val$permissionsNeeded = list;
            this.val$openAct = activity;
        }

        public void run() {
            if (this.val$permissionsNeeded.size() > 0) {
                this.val$openAct.requestPermissions((String[]) this.val$permissionsNeeded.toArray(new String[this.val$permissionsNeeded.size()]), CommonHelper.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            }
        }
    }

    public static boolean checkInternetConnection(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkPermission(Context context, String str) {
        try {
            if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @TargetApi(23)
    public static boolean checkSelfPermission(Context context, String str) {
        boolean z = false;
        try {
            if (VERSION.SDK_INT < 23) {
                return checkPermission(context, str);
            }
            try {
                if (context.checkSelfPermission(str) == 0) {
                    return true;
                }
                return z;
            } catch (Exception e) {
                e.printStackTrace();
                IgawLogger.Logging(context, "IGAW_QA", "checkSelfPermission Error: " + e.getMessage(), 0);
                return z;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            IgawLogger.Logging(context, "IGAW_QA", "checkSelfPermission Error: " + e2.getMessage(), z);
            return z;
        }
    }

    public static boolean checkReceiver(Context context) {
        try {
            if (context.getPackageManager().getReceiverInfo(new ComponentName(context, "com.igaworks.IgawReceiver"), 128) != null) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        } catch (Exception e2) {
            IgawLogger.Logging(context, "IGAW_QA", "checkReceiver error : " + e2.toString(), 0);
            return false;
        }
    }

    public static String GetKSTCreateAtAsString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+9"));
        return simpleDateFormat.format(new Date());
    }

    public static String GetKSTServerTimeAsString(Context context) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+9"));
        return simpleDateFormat.format(new Date(AppImpressionDAO.getServerBaseTimeOffset(context) + System.currentTimeMillis()));
    }

    @TargetApi(23)
    public static boolean CheckandRequestPermissionForCommonSDK(Context context) {
        try {
            if (!(context instanceof Activity) || VERSION.SDK_INT < 23) {
                if (!(context instanceof Activity)) {
                    IgawLogger.Logging(context, "IGAW_QA", "context is not an activity context.", 3, false);
                }
                return checkSelfPermission(context, "android.permission.INTERNET") && checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE") && checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") && checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE");
            } else {
                Activity activity = (Activity) context;
                List arrayList = new ArrayList();
                List arrayList2 = new ArrayList();
                boolean checkSelfPermission = checkSelfPermission(context, "android.permission.INTERNET");
                if (!checkSelfPermission) {
                    arrayList.add("android.permission.INTERNET");
                    if (activity.shouldShowRequestPermissionRationale("android.permission.INTERNET")) {
                        arrayList2.add("android.permission.INTERNET");
                    }
                }
                boolean checkSelfPermission2 = checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE");
                if (!checkSelfPermission2) {
                    arrayList.add("android.permission.ACCESS_NETWORK_STATE");
                    if (activity.shouldShowRequestPermissionRationale("android.permission.ACCESS_NETWORK_STATE")) {
                        arrayList2.add("android.permission.ACCESS_NETWORK_STATE");
                    }
                }
                boolean checkSelfPermission3 = checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE");
                if (!checkSelfPermission3) {
                    arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
                    if (activity.shouldShowRequestPermissionRationale("android.permission.READ_EXTERNAL_STORAGE")) {
                        arrayList2.add("android.permission.READ_EXTERNAL_STORAGE");
                    }
                }
                boolean checkSelfPermission4 = checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE");
                if (!checkSelfPermission4) {
                    arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
                    if (activity.shouldShowRequestPermissionRationale("android.permission.WRITE_EXTERNAL_STORAGE")) {
                        arrayList2.add("android.permission.WRITE_EXTERNAL_STORAGE");
                    }
                }
                activity.runOnUiThread(new AnonymousClass1(arrayList2, arrayList, activity));
                if (checkSelfPermission && checkSelfPermission2 && checkSelfPermission3 && checkSelfPermission4) {
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
            Log.e("IGAW_QA", "GrantPermissionForCommonSDK Error: " + e.getMessage());
            return false;
        }
    }

    @TargetApi(23)
    public static boolean CheckPermissionForCommonSDK(Context context) {
        try {
            boolean checkSelfPermission = checkSelfPermission(context, "android.permission.INTERNET");
            boolean checkSelfPermission2 = checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE");
            boolean checkSelfPermission3;
            if (VERSION.SDK_INT >= 19) {
                checkSelfPermission3 = checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE");
            } else {
                checkSelfPermission3 = true;
            }
            boolean checkSelfPermission4 = checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE");
            if (checkSelfPermission && checkSelfPermission2 && r2 && checkSelfPermission4) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.e("IGAW_QA", "CheckPermissionForCommonSDK Error: " + e.getMessage());
            return false;
        }
    }

    @TargetApi(23)
    public static void RequestPermissionForCommonSDK(Context context) {
        try {
            if ((context instanceof Activity) && VERSION.SDK_INT >= 23) {
                Activity activity = (Activity) context;
                List arrayList = new ArrayList();
                if (!checkSelfPermission(context, "android.permission.INTERNET")) {
                    arrayList.add("android.permission.INTERNET");
                }
                if (!checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
                    arrayList.add("android.permission.ACCESS_NETWORK_STATE");
                }
                if (!checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE")) {
                    arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
                }
                if (!checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                    arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
                }
                activity.runOnUiThread(new AnonymousClass2(arrayList, activity));
            } else if (!(context instanceof Activity)) {
                IgawLogger.Logging(context, "IGAW_QA", "context is not an activity context.", 3, false);
            }
        } catch (Exception e) {
            Log.e("IGAW_QA", "RequestPermissionForCommonSDK Error: " + e.getMessage());
        }
    }

    public static Bitmap getBitmapFromURL(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
