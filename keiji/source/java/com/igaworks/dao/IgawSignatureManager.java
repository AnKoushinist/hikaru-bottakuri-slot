package com.igaworks.dao;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.util.Pair;
import com.igaworks.core.DeviceIDManger;
import com.igaworks.core.IgawLogger;
import com.igaworks.core.RequestParameter;
import com.igaworks.cpe.ConditionChecker;
import com.igaworks.util.CommonHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class IgawSignatureManager {

    class AnonymousClass1 implements Runnable {
        private final /* synthetic */ String val$adid;
        private final /* synthetic */ Context val$context;

        AnonymousClass1(Context context, String str) {
            this.val$context = context;
            this.val$adid = str;
        }

        public void run() {
            try {
                String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                String access$0 = IgawSignatureManager.getFD(this.val$context);
                String access$1 = IgawSignatureManager.getFL(this.val$context);
                List<Pair> signature = IgawSignatureManager.getSignature(this.val$context, this.val$adid);
                for (String str : IgawSignatureManager.getPathList()) {
                    File file = new File(new StringBuilder(String.valueOf(absolutePath)).append(str).append(access$0).toString());
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(file, access$1);
                    if (file2.exists()) {
                        IgawLogger.Logging(this.val$context, "IGAW_QA", "createSignature > already exist file : " + str, 3, true);
                    } else {
                        IgawLogger.Logging(this.val$context, "IGAW_QA", "createSignature > " + ((String) ((Pair) signature.get(0)).second) + "\n" + ((String) ((Pair) signature.get(1)).second), 3, true);
                        OutputStream fileOutputStream = new FileOutputStream(file2);
                        PrintWriter printWriter = new PrintWriter(fileOutputStream);
                        for (Pair pair : signature) {
                            printWriter.println((String) pair.second);
                        }
                        printWriter.flush();
                        printWriter.close();
                        fileOutputStream.close();
                    }
                }
            } catch (Exception e) {
                if (e != null) {
                    IgawLogger.Logging(this.val$context, "IGAW_QA", e.toString(), 0, true);
                }
            }
        }
    }

    public static void createSignature(Context context, String str) {
        if (CommonHelper.CheckPermissionForCommonSDK(context)) {
            new Thread(new AnonymousClass1(context, str)).start();
        } else {
            Log.e("IGAW_QA", "createSignature: Need Write SD permission!!");
        }
    }

    public static int checkSignAndPackage(Context context, String str) {
        int i = 0;
        if (!checkSignature(context, str)) {
            i = 1;
        }
        if (ConditionChecker.checkInstalled(context, "com.androVM.vmconfig") || ConditionChecker.checkInstalled(context, "me.onemobile.android") || ConditionChecker.checkInstalled(context, "com.android.noxpush")) {
            return i | 2;
        }
        return i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean checkSignature(android.content.Context r15, java.lang.String r16) {
        /*
        r0 = com.igaworks.util.CommonHelper.CheckPermissionForCommonSDK(r15);
        if (r0 != 0) goto L_0x000f;
    L_0x0006:
        r0 = "IGAW_QA";
        r1 = "checkSignature: Need Read SD permission!!";
        android.util.Log.e(r0, r1);
        r0 = 1;
    L_0x000e:
        return r0;
    L_0x000f:
        r1 = 1;
        r5 = getSignature(r15, r16);	 Catch:{ Exception -> 0x011f }
        r0 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x011f }
        r6 = r0.getAbsolutePath();	 Catch:{ Exception -> 0x011f }
        r7 = getFD(r15);	 Catch:{ Exception -> 0x011f }
        r8 = getFL(r15);	 Catch:{ Exception -> 0x011f }
        r0 = getPathList();	 Catch:{ Exception -> 0x011f }
        r9 = r0.iterator();	 Catch:{ Exception -> 0x011f }
    L_0x002c:
        r0 = r9.hasNext();	 Catch:{ Exception -> 0x011f }
        if (r0 != 0) goto L_0x0034;
    L_0x0032:
        r0 = r1;
        goto L_0x000e;
    L_0x0034:
        r0 = r9.next();	 Catch:{ Exception -> 0x011f }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x011f }
        r2 = new java.io.File;	 Catch:{ Exception -> 0x011f }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x011f }
        r4 = java.lang.String.valueOf(r6);	 Catch:{ Exception -> 0x011f }
        r3.<init>(r4);	 Catch:{ Exception -> 0x011f }
        r3 = r3.append(r0);	 Catch:{ Exception -> 0x011f }
        r3 = r3.append(r7);	 Catch:{ Exception -> 0x011f }
        r4 = "/";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x011f }
        r3 = r3.append(r8);	 Catch:{ Exception -> 0x011f }
        r3 = r3.toString();	 Catch:{ Exception -> 0x011f }
        r2.<init>(r3);	 Catch:{ Exception -> 0x011f }
        r3 = r2.exists();	 Catch:{ Exception -> 0x011f }
        if (r3 == 0) goto L_0x0170;
    L_0x0064:
        r10 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x011f }
        r10.<init>(r2);	 Catch:{ Exception -> 0x011f }
        r11 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x011f }
        r0 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x011f }
        r0.<init>(r10);	 Catch:{ Exception -> 0x011f }
        r11.<init>(r0);	 Catch:{ Exception -> 0x011f }
        r0 = "";
        r2 = r1;
    L_0x0076:
        r12 = r11.readLine();	 Catch:{ Exception -> 0x011f }
        if (r12 != 0) goto L_0x0085;
    L_0x007c:
        r0 = r2;
    L_0x007d:
        r10.close();	 Catch:{ Exception -> 0x011f }
        r11.close();	 Catch:{ Exception -> 0x011f }
        r1 = r0;
        goto L_0x002c;
    L_0x0085:
        r0 = 0;
        r1 = "build.serial=";
        r1 = r12.contains(r1);	 Catch:{ Exception -> 0x011f }
        if (r1 == 0) goto L_0x00ab;
    L_0x008e:
        r0 = "build.serial=";
        r4 = r0;
    L_0x0091:
        if (r4 != 0) goto L_0x00c3;
    L_0x0093:
        r0 = "IGAW_QA";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x011f }
        r2 = "not valid sig > myFile.exists() is true but not valid key : ";
        r1.<init>(r2);	 Catch:{ Exception -> 0x011f }
        r1 = r1.append(r12);	 Catch:{ Exception -> 0x011f }
        r1 = r1.toString();	 Catch:{ Exception -> 0x011f }
        r2 = 3;
        r3 = 1;
        com.igaworks.core.IgawLogger.Logging(r15, r0, r1, r2, r3);	 Catch:{ Exception -> 0x011f }
        r0 = 0;
        goto L_0x007d;
    L_0x00ab:
        r1 = "build.version=";
        r1 = r12.contains(r1);	 Catch:{ Exception -> 0x011f }
        if (r1 == 0) goto L_0x00b7;
    L_0x00b3:
        r0 = "build.version=";
        r4 = r0;
        goto L_0x0091;
    L_0x00b7:
        r1 = "build.sign=";
        r1 = r12.contains(r1);	 Catch:{ Exception -> 0x011f }
        if (r1 == 0) goto L_0x019d;
    L_0x00bf:
        r0 = "build.sign=";
        r4 = r0;
        goto L_0x0091;
    L_0x00c3:
        r13 = r5.iterator();	 Catch:{ Exception -> 0x011f }
    L_0x00c7:
        r0 = r13.hasNext();	 Catch:{ Exception -> 0x011f }
        if (r0 != 0) goto L_0x00d2;
    L_0x00cd:
        r0 = r2;
    L_0x00ce:
        if (r0 == 0) goto L_0x007d;
    L_0x00d0:
        r2 = r0;
        goto L_0x0076;
    L_0x00d2:
        r0 = r13.next();	 Catch:{ Exception -> 0x011f }
        r0 = (android.util.Pair) r0;	 Catch:{ Exception -> 0x011f }
        r1 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0126 }
        r14 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x0126 }
        r3.<init>(r14);	 Catch:{ Exception -> 0x0126 }
        r14 = "=";
        r3 = r3.append(r14);	 Catch:{ Exception -> 0x0126 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0126 }
        r14 = "";
        r1 = r12.replace(r3, r14);	 Catch:{ Exception -> 0x0126 }
        r3 = r1;
    L_0x00f3:
        r1 = "build.sign=";
        r1 = r4.equals(r1);	 Catch:{ Exception -> 0x011f }
        if (r1 == 0) goto L_0x0129;
    L_0x00fb:
        r1 = r0.second;	 Catch:{ Exception -> 0x011f }
        if (r1 == 0) goto L_0x0115;
    L_0x00ff:
        r1 = r0.second;	 Catch:{ Exception -> 0x011f }
        r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x011f }
        r14 = "";
        r1 = r1.equals(r14);	 Catch:{ Exception -> 0x011f }
        if (r1 != 0) goto L_0x0115;
    L_0x010b:
        if (r3 == 0) goto L_0x0115;
    L_0x010d:
        r1 = "";
        r1 = r3.equals(r1);	 Catch:{ Exception -> 0x011f }
        if (r1 == 0) goto L_0x0129;
    L_0x0115:
        r0 = "IGAW_QA";
        r1 = "cannot find sign value.";
        r3 = 3;
        r14 = 1;
        com.igaworks.core.IgawLogger.Logging(r15, r0, r1, r3, r14);	 Catch:{ Exception -> 0x011f }
        goto L_0x00c7;
    L_0x011f:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = 1;
        goto L_0x000e;
    L_0x0126:
        r3 = move-exception;
        r3 = r1;
        goto L_0x00f3;
    L_0x0129:
        r1 = r0.first;	 Catch:{ Exception -> 0x011f }
        r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x011f }
        r1 = r1.equals(r4);	 Catch:{ Exception -> 0x011f }
        if (r1 == 0) goto L_0x00c7;
    L_0x0133:
        r1 = r0.second;	 Catch:{ Exception -> 0x011f }
        r1 = r3.equals(r1);	 Catch:{ Exception -> 0x011f }
        if (r1 != 0) goto L_0x00c7;
    L_0x013b:
        r2 = "IGAW_QA";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x011f }
        r1 = "not valid sig > myFile.exists() is true but key/value : nvp.getName() = ";
        r4.<init>(r1);	 Catch:{ Exception -> 0x011f }
        r1 = r0.first;	 Catch:{ Exception -> 0x011f }
        r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x011f }
        r1 = r4.append(r1);	 Catch:{ Exception -> 0x011f }
        r4 = ", nvp.getValue() = ";
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x011f }
        r0 = r0.second;	 Catch:{ Exception -> 0x011f }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x011f }
        r0 = r1.append(r0);	 Catch:{ Exception -> 0x011f }
        r1 = ", stored = ";
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x011f }
        r0 = r0.append(r3);	 Catch:{ Exception -> 0x011f }
        r0 = r0.toString();	 Catch:{ Exception -> 0x011f }
        r1 = 3;
        r3 = 1;
        com.igaworks.core.IgawLogger.Logging(r15, r2, r0, r1, r3);	 Catch:{ Exception -> 0x011f }
        r0 = 0;
        goto L_0x00ce;
    L_0x0170:
        r2 = "IGAW_QA";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x011f }
        r4 = "myFile.exists() is false : ";
        r3.<init>(r4);	 Catch:{ Exception -> 0x011f }
        r3 = r3.append(r6);	 Catch:{ Exception -> 0x011f }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x011f }
        r0 = r0.append(r7);	 Catch:{ Exception -> 0x011f }
        r3 = "/";
        r0 = r0.append(r3);	 Catch:{ Exception -> 0x011f }
        r0 = r0.append(r8);	 Catch:{ Exception -> 0x011f }
        r0 = r0.toString();	 Catch:{ Exception -> 0x011f }
        r3 = 3;
        r4 = 1;
        com.igaworks.core.IgawLogger.Logging(r15, r2, r0, r3, r4);	 Catch:{ Exception -> 0x011f }
        createSignature(r15, r16);	 Catch:{ Exception -> 0x011f }
        goto L_0x002c;
    L_0x019d:
        r4 = r0;
        goto L_0x0091;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igaworks.dao.IgawSignatureManager.checkSignature(android.content.Context, java.lang.String):boolean");
    }

    public static List<String> getPathList() {
        List<String> arrayList = new ArrayList();
        arrayList.add("/Android/data/");
        arrayList.add("/System/data/");
        arrayList.add("/data/build/");
        return arrayList;
    }

    private static String getFD(Context context) {
        return "com." + DeviceIDManger.getMd5Value(RequestParameter.getATRequestParameter(context).getAppkey());
    }

    private static String getFL(Context context) {
        return "data_" + DeviceIDManger.getMd5Value(new StringBuilder(String.valueOf(RequestParameter.getATRequestParameter(context).getHashkey())).append(".dat").toString());
    }

    public static List<Pair<String, String>> getSignature(Context context, String str) {
        List<Pair<String, String>> arrayList = new ArrayList();
        DeviceIDManger.getInstance(context);
        String str2 = "build.serial=" + DeviceIDManger.getMd5Value(str);
        String str3 = "build.version=" + DeviceIDManger.getMd5Value(Build.PRODUCT);
        arrayList.add(new Pair("build.serial=", str2));
        arrayList.add(new Pair("build.version=", str3));
        try {
            arrayList.add(new Pair("build.sign=", "build.sign=" + DeviceIDManger.getMd5Value(str)));
        } catch (Exception e) {
            if (e != null) {
                IgawLogger.Logging(context, "IGAW_QA", e.toString(), 0, true);
            }
        }
        return arrayList;
    }
}
