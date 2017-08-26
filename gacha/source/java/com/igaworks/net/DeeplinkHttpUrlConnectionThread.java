package com.igaworks.net;

import android.content.Context;
import android.os.Build.VERSION;
import com.igaworks.interfaces.HttpCallbackListener;
import com.tapjoy.TapjoyConstants;
import java.net.HttpURLConnection;
import org.cocos2dx.lib.BuildConfig;

public class DeeplinkHttpUrlConnectionThread extends Thread {
    private HttpURLConnection conn;
    private Context context;
    private String httpResponseString;
    private boolean isEncode;
    private HttpCallbackListener listener;
    private int method;
    private String queryString;
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
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:286)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:173)
*/
        /*
        r9 = this;
        r8 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r2 = new android.os.Handler;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.context;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.getMainLooper();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r2.<init>(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r1 = "";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.method;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        if (r0 != 0) goto L_0x019b;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x0013:
        r0 = r9.url;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = "?";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.contains(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        if (r0 != 0) goto L_0x00f6;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x001d:
        r0 = r9.url;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = "?";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r9.url = r0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x0034:
        r0 = r9.isEncode;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        if (r0 == 0) goto L_0x0148;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x0038:
        r0 = r9.url;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = "queryString=";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = r9.queryString;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = com.igaworks.util.IgawBase64.encodeString(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.append(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r9.url = r0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x0059:
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = r9.url;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.<init>(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r9.conn = r0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.setReadTimeout(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.setConnectTimeout(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = "GET";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.setRequestMethod(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = "Accept-Charset";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = "UTF-8";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.setRequestProperty(r3, r4);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = 1;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.setDoInput(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = 1;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.setDoOutput(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = 0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.setInstanceFollowRedirects(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r9.disableConnectionReuseIfNecessary();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.context;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = "IGAW_QA";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r5 = "DeeplinkHttpUrlConnectionThread>> url = ";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4.<init>(r5);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r5 = r9.url;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r5 = 3;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r6 = 1;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        com.igaworks.core.IgawLogger.Logging(r0, r3, r4, r5, r6);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.getResponseCode();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        if (r0 != r8) goto L_0x017f;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x00bd:
        r3 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = r4.getInputStream();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.<init>(r4);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r1;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x00ce:
        r1 = r3.readLine();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        if (r1 != 0) goto L_0x016c;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x00d4:
        r9.httpResponseString = r0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.httpResponseString;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        if (r0 == 0) goto L_0x0279;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x00da:
        r0 = r9.httpResponseString;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r1 = "";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        if (r0 != 0) goto L_0x0279;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x00e4:
        r0 = new com.igaworks.net.DeeplinkHttpUrlConnectionThread$1;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.<init>();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r2.post(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x00ec:
        r0 = r9.conn;
        if (r0 == 0) goto L_0x00f5;
    L_0x00f0:
        r0 = r9.conn;
        r0.disconnect();
    L_0x00f5:
        return;
    L_0x00f6:
        r0 = r9.url;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = "&";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r9.url = r0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        goto L_0x0034;
    L_0x010f:
        r0 = move-exception;
        r1 = r9.context;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r2 = "IGAW_QA";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = "Exception : ";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = 0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        com.igaworks.core.IgawLogger.Logging(r1, r2, r0, r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = new android.os.Handler;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r1 = r9.context;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r1 = r1.getMainLooper();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r1 = new com.igaworks.net.DeeplinkHttpUrlConnectionThread$3;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r1.<init>();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.post(r1);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;
        if (r0 == 0) goto L_0x00f5;
    L_0x0142:
        r0 = r9.conn;
        r0.disconnect();
        goto L_0x00f5;
    L_0x0148:
        r0 = r9.url;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.queryString;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r9.url = r0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        goto L_0x0059;
    L_0x0161:
        r0 = move-exception;
        r1 = r9.conn;
        if (r1 == 0) goto L_0x016b;
    L_0x0166:
        r1 = r9.conn;
        r1.disconnect();
    L_0x016b:
        throw r0;
    L_0x016c:
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4.<init>(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r4.append(r1);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        goto L_0x00ce;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x017f:
        r1 = r9.context;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = "IGAW_QA";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r5 = "DeeplinkHttpUrlConnectionThread: HTTP GET >> responseCode: ";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4.<init>(r5);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r4.append(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = 0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r5 = 0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        com.igaworks.core.IgawLogger.Logging(r1, r3, r0, r4, r5);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = "";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        goto L_0x00d4;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x019b:
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = r9.url;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.<init>(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = r9.context;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = "IGAW_QA";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r6 = "DeeplinkHttpUrlConnectionThread: HTTP POST > reqName : ";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r5.<init>(r6);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r6 = r9.url;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r6 = ", param : ";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r6 = r9.queryString;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r6 = 3;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r7 = 1;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        com.igaworks.core.IgawLogger.Logging(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r9.conn = r0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.setReadTimeout(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.setConnectTimeout(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = "POST";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.setRequestMethod(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = 1;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.setDoInput(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = 1;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.setDoOutput(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = "Accept-Charset";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = "UTF-8";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.setRequestProperty(r3, r4);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = "Content-Type";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = "application/json; charset=utf-8";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.setRequestProperty(r3, r4);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = 0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.setInstanceFollowRedirects(r3);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r9.disableConnectionReuseIfNecessary();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.getOutputStream();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = new java.io.BufferedWriter;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = new java.io.OutputStreamWriter;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r5 = "UTF-8";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4.<init>(r0, r5);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3.<init>(r4);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = r9.queryString;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3.write(r4);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3.flush();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3.close();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.close();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.getResponseCode();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        if (r0 != r8) goto L_0x025d;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x0234:
        r3 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = r9.conn;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = r4.getInputStream();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.<init>(r4);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3.<init>(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r1;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x0245:
        r1 = r3.readLine();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        if (r1 == 0) goto L_0x00d4;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x024b:
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4.<init>(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r4.append(r1);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        goto L_0x0245;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x025d:
        r1 = r9.context;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r3 = "IGAW_QA";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r5 = "DeeplinkHttpUrlConnectionThread: HTTP POST >> responseCode: ";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4.<init>(r5);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r4.append(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r4 = 0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r5 = 0;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        com.igaworks.core.IgawLogger.Logging(r1, r3, r0, r4, r5);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0 = "";	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        goto L_0x00d4;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
    L_0x0279:
        r0 = new com.igaworks.net.DeeplinkHttpUrlConnectionThread$2;	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r0.<init>();	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        r2.post(r0);	 Catch:{ Exception -> 0x010f, all -> 0x0161 }
        goto L_0x00ec;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igaworks.net.DeeplinkHttpUrlConnectionThread.run():void");
    }

    public DeeplinkHttpUrlConnectionThread(Context context, int i, String str, String str2, HttpCallbackListener httpCallbackListener) {
        this(context, i, str, str2, httpCallbackListener, false);
    }

    public DeeplinkHttpUrlConnectionThread(Context context, int i, String str, String str2, HttpCallbackListener httpCallbackListener, boolean z) {
        this.url = BuildConfig.FLAVOR;
        this.httpResponseString = BuildConfig.FLAVOR;
        this.url = str;
        this.method = i;
        this.queryString = str2;
        this.listener = httpCallbackListener;
        this.context = context;
        this.isEncode = z;
    }

    private void disableConnectionReuseIfNecessary() {
        if (Integer.parseInt(VERSION.SDK) < 8) {
            System.setProperty("http.keepAlive", TapjoyConstants.TJC_FALSE);
        }
    }
}
