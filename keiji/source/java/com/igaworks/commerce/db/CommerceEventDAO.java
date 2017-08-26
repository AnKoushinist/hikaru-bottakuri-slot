package com.igaworks.commerce.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.List;

public class CommerceEventDAO {
    public static SharedPreferences getCommerceEventsSP(Context context) {
        return context.getSharedPreferences("CommerceEvents", 0);
    }

    public static void addEvents(Context context, List<String> list) {
        Editor edit = getCommerceEventsSP(context).edit();
        for (String str : list) {
            edit.putString(str.toString(), str.toString());
        }
        edit.commit();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<java.lang.String> getEvents(android.content.Context r2) {
        /*
        r0 = getCommerceEventsSP(r2);	 Catch:{ Exception -> 0x0025 }
        r0 = r0.getAll();	 Catch:{ Exception -> 0x0025 }
        r1 = r0.values();	 Catch:{ Exception -> 0x0025 }
        r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x0025 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x0025 }
        r1 = getCommerceEventsSP(r2);	 Catch:{ Exception -> 0x0020 }
        r1 = r1.edit();	 Catch:{ Exception -> 0x0020 }
        r1.clear();	 Catch:{ Exception -> 0x0020 }
        r1.commit();	 Catch:{ Exception -> 0x0020 }
    L_0x001f:
        return r0;
    L_0x0020:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001f;
    L_0x0025:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x003e }
        r0 = getCommerceEventsSP(r2);	 Catch:{ Exception -> 0x0039 }
        r0 = r0.edit();	 Catch:{ Exception -> 0x0039 }
        r0.clear();	 Catch:{ Exception -> 0x0039 }
        r0.commit();	 Catch:{ Exception -> 0x0039 }
    L_0x0037:
        r0 = 0;
        goto L_0x001f;
    L_0x0039:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0037;
    L_0x003e:
        r0 = move-exception;
        r1 = getCommerceEventsSP(r2);	 Catch:{ Exception -> 0x004e }
        r1 = r1.edit();	 Catch:{ Exception -> 0x004e }
        r1.clear();	 Catch:{ Exception -> 0x004e }
        r1.commit();	 Catch:{ Exception -> 0x004e }
    L_0x004d:
        throw r0;
    L_0x004e:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igaworks.commerce.db.CommerceEventDAO.getEvents(android.content.Context):java.util.List<java.lang.String>");
    }
}
