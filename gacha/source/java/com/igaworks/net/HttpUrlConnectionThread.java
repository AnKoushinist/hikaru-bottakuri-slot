package com.igaworks.net;

import android.content.Context;
import android.os.Build.VERSION;
import com.d.a.a.c;
import com.igaworks.interfaces.HttpCallbackListener;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TapjoyConstants;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map.Entry;
import org.cocos2dx.lib.BuildConfig;

public class HttpUrlConnectionThread extends Thread {
    private HttpURLConnection conn;
    private Context context;
    private String httpResponseString;
    private boolean isEncode;
    private HttpCallbackListener listener;
    private int method;
    private HashMap<String, String> params;
    private String url;

    public void run() {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextEntry(HashMap.java:789)
	at java.util.HashMap$KeyIterator.next(HashMap.java:814)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
        /*
        r9 = this;
        r8 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r2 = new android.os.Handler;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.context;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.getMainLooper();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r2.<init>(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r1 = "";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.method;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        if (r0 != 0) goto L_0x019d;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x0013:
        r0 = r9.params;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.getPostDataString(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = r9.url;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = "?";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = r3.contains(r4);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        if (r3 != 0) goto L_0x00fa;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x0023:
        r3 = r9.url;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4.<init>(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = "?";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = r4.append(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r9.url = r3;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x003a:
        r3 = r9.isEncode;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        if (r3 == 0) goto L_0x014c;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x003e:
        r3 = r9.url;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4.<init>(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = "queryString=";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = r4.append(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = com.igaworks.util.IgawBase64.encodeString(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r9.url = r0;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x005d:
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = r9.url;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.<init>(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r9.conn = r0;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.setReadTimeout(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.setConnectTimeout(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = "GET";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.setRequestMethod(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = "Accept-Charset";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = "UTF-8";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.setRequestProperty(r3, r4);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = 1;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.setDoInput(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = 1;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.setDoOutput(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = 0;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.setInstanceFollowRedirects(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r9.disableConnectionReuseIfNecessary();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.context;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = "IGAW_QA";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r5 = "HttpUrlConnectionThread: getPromotionInfo > url = ";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4.<init>(r5);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r5 = r9.url;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r5 = 3;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r6 = 1;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        com.igaworks.core.IgawLogger.Logging(r0, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.getResponseCode();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        if (r0 != r8) goto L_0x0181;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x00c1:
        r3 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = r4.getInputStream();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.<init>(r4);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r1;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x00d2:
        r1 = r3.readLine();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        if (r1 != 0) goto L_0x016e;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x00d8:
        r9.httpResponseString = r0;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.httpResponseString;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        if (r0 == 0) goto L_0x02c3;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x00de:
        r0 = r9.httpResponseString;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r1 = "";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        if (r0 != 0) goto L_0x02c3;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x00e8:
        r0 = new com.igaworks.net.HttpUrlConnectionThread$1;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.<init>();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r2.post(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x00f0:
        r0 = r9.conn;
        if (r0 == 0) goto L_0x00f9;
    L_0x00f4:
        r0 = r9.conn;
        r0.disconnect();
    L_0x00f9:
        return;
    L_0x00fa:
        r3 = r9.url;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4.<init>(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = "&";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = r4.append(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r9.url = r3;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        goto L_0x003a;
    L_0x0113:
        r0 = move-exception;
        r1 = r9.context;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r2 = "IGAW_QA";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = "Exception : ";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = 0;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        com.igaworks.core.IgawLogger.Logging(r1, r2, r0, r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = new android.os.Handler;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r1 = r9.context;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r1 = r1.getMainLooper();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r1 = new com.igaworks.net.HttpUrlConnectionThread$3;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r1.<init>();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.post(r1);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;
        if (r0 == 0) goto L_0x00f9;
    L_0x0146:
        r0 = r9.conn;
        r0.disconnect();
        goto L_0x00f9;
    L_0x014c:
        r3 = r9.url;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4.<init>(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r4.append(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r9.url = r0;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        goto L_0x005d;
    L_0x0163:
        r0 = move-exception;
        r1 = r9.conn;
        if (r1 == 0) goto L_0x016d;
    L_0x0168:
        r1 = r9.conn;
        r1.disconnect();
    L_0x016d:
        throw r0;
    L_0x016e:
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4.<init>(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r4.append(r1);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        goto L_0x00d2;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x0181:
        r1 = r9.context;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = "IGAW_QA";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r5 = "HttpUrlConnectionThread: HTTP GET >> responseCode: ";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4.<init>(r5);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r4.append(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = 0;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r5 = 0;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        com.igaworks.core.IgawLogger.Logging(r1, r3, r0, r4, r5);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = "";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        goto L_0x00d8;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x019d:
        r3 = new java.net.URL;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.url;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r3.getPath();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = "";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = "/";	 Catch:{ Exception -> 0x02cd }
        r4 = r0.contains(r4);	 Catch:{ Exception -> 0x02cd }
        if (r4 == 0) goto L_0x01cc;	 Catch:{ Exception -> 0x02cd }
    L_0x01b2:
        r4 = "/";	 Catch:{ Exception -> 0x02cd }
        r4 = r0.lastIndexOf(r4);	 Catch:{ Exception -> 0x02cd }
        r5 = r0.length();	 Catch:{ Exception -> 0x02cd }
        r5 = r5 + -1;	 Catch:{ Exception -> 0x02cd }
        if (r4 >= r5) goto L_0x01cc;	 Catch:{ Exception -> 0x02cd }
    L_0x01c0:
        r4 = "/";	 Catch:{ Exception -> 0x02cd }
        r4 = r0.lastIndexOf(r4);	 Catch:{ Exception -> 0x02cd }
        r4 = r4 + 1;	 Catch:{ Exception -> 0x02cd }
        r0 = r0.substring(r4);	 Catch:{ Exception -> 0x02cd }
    L_0x01cc:
        r4 = r9.context;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r5 = "IGAW_QA";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r7 = "HttpUrlConnectionThread: HTTP POST > reqName : ";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r6.<init>(r7);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r6.append(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r6 = ", param size: ";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.append(r6);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r6 = r9.params;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r6 = r6.size();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.append(r6);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r6 = 3;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r7 = 1;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        com.igaworks.core.IgawLogger.Logging(r4, r5, r0, r6, r7);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.isEncode;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        if (r0 == 0) goto L_0x020e;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x01f8:
        r0 = r9.params;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.getPostDataString(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = r9.params;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4.clear();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = r9.params;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r5 = "queryString";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = com.igaworks.util.IgawBase64.encodeString(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4.put(r5, r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x020e:
        r0 = r3.openConnection();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r9.conn = r0;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.setReadTimeout(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.setConnectTimeout(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = "POST";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.setRequestMethod(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = 1;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.setDoInput(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = 1;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.setDoOutput(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = "Accept-Charset";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = "UTF-8";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.setRequestProperty(r3, r4);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = "Content-Type";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = "application/x-www-form-urlencoded; charset=utf-8";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.setRequestProperty(r3, r4);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = 0;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.setInstanceFollowRedirects(r3);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r9.disableConnectionReuseIfNecessary();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.getOutputStream();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = new java.io.BufferedWriter;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = new java.io.OutputStreamWriter;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r5 = "UTF-8";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4.<init>(r0, r5);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = r9.params;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = r9.getPostDataString(r4);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3.write(r4);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3.flush();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3.close();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.close();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.getResponseCode();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        if (r0 != r8) goto L_0x02a7;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x027e:
        r3 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = r9.conn;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = r4.getInputStream();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.<init>(r4);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r1;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x028f:
        r1 = r3.readLine();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        if (r1 == 0) goto L_0x00d8;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x0295:
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4.<init>(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r4.append(r1);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        goto L_0x028f;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x02a7:
        r1 = r9.context;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r3 = "IGAW_QA";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r5 = "HttpUrlConnectionThread: HTTP POST >> responseCode: ";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4.<init>(r5);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r4.append(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r4 = 0;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r5 = 0;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        com.igaworks.core.IgawLogger.Logging(r1, r3, r0, r4, r5);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0 = "";	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        goto L_0x00d8;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
    L_0x02c3:
        r0 = new com.igaworks.net.HttpUrlConnectionThread$2;	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r0.<init>();	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        r2.post(r0);	 Catch:{ Exception -> 0x0113, all -> 0x0163 }
        goto L_0x00f0;
    L_0x02cd:
        r4 = move-exception;
        goto L_0x01cc;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igaworks.net.HttpUrlConnectionThread.run():void");
    }

    public HttpUrlConnectionThread(Context context, int i, String str, HashMap<String, String> hashMap, HttpCallbackListener httpCallbackListener) {
        this(context, i, str, hashMap, httpCallbackListener, false);
    }

    public HttpUrlConnectionThread(Context context, int i, String str, HashMap<String, String> hashMap, HttpCallbackListener httpCallbackListener, boolean z) {
        this.url = BuildConfig.FLAVOR;
        this.httpResponseString = BuildConfig.FLAVOR;
        this.url = str;
        this.method = i;
        this.params = hashMap;
        this.listener = httpCallbackListener;
        this.context = context;
        this.isEncode = z;
    }

    private String getPostDataString(HashMap<String, String> hashMap) {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Entry entry : hashMap.entrySet()) {
            Object obj2;
            if (obj != null) {
                obj2 = null;
            } else {
                stringBuilder.append("&");
                obj2 = obj;
            }
            stringBuilder.append(URLEncoder.encode((String) entry.getKey(), c.DEFAULT_CHARSET));
            stringBuilder.append(Operation.EQUALS);
            String str = (String) entry.getValue();
            if (str == null) {
                str = BuildConfig.FLAVOR;
            }
            stringBuilder.append(URLEncoder.encode(str, c.DEFAULT_CHARSET));
            obj = obj2;
        }
        return stringBuilder.toString();
    }

    private void disableConnectionReuseIfNecessary() {
        if (Integer.parseInt(VERSION.SDK) < 8) {
            System.setProperty("http.keepAlive", TapjoyConstants.TJC_FALSE);
        }
    }
}
