package com.adcolony.sdk;

import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPOutputStream;

class bc {
    URL a;

    public bc(URL url) {
        this.a = url;
    }

    public int a(String str) {
        DataOutputStream dataOutputStream;
        DataOutputStream dataOutputStream2;
        FilterOutputStream filterOutputStream;
        IOException iOException;
        Object obj;
        IOException iOException2;
        Throwable th;
        GZIPOutputStream gZIPOutputStream;
        HttpURLConnection httpURLConnection;
        DataOutputStream dataOutputStream3;
        Throwable th2;
        DataOutputStream dataOutputStream4 = null;
        Object obj2 = null;
        try {
            FilterOutputStream gZIPOutputStream2;
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) this.a.openConnection();
            try {
                httpURLConnection2.setRequestMethod("POST");
                httpURLConnection2.setRequestProperty("Content-Encoding", "gzip");
                httpURLConnection2.setRequestProperty("Content-Type", "application/json");
                httpURLConnection2.setDoInput(true);
                gZIPOutputStream2 = new GZIPOutputStream(httpURLConnection2.getOutputStream());
                try {
                    dataOutputStream = new DataOutputStream(gZIPOutputStream2);
                } catch (IOException e) {
                    dataOutputStream2 = null;
                    filterOutputStream = gZIPOutputStream2;
                    iOException = e;
                    obj = null;
                    obj2 = httpURLConnection2;
                    iOException2 = iOException;
                    try {
                        throw iOException2;
                    } catch (Throwable th3) {
                        th = th3;
                        gZIPOutputStream = filterOutputStream;
                        dataOutputStream4 = dataOutputStream2;
                        Object obj3 = obj;
                        httpURLConnection = dataOutputStream3;
                        obj2 = obj3;
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    httpURLConnection = httpURLConnection2;
                    th = th2;
                    dataOutputStream4.close();
                    if (gZIPOutputStream != null) {
                        gZIPOutputStream.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (IOException e2) {
                dataOutputStream2 = null;
                obj2 = httpURLConnection2;
                iOException2 = e2;
                obj = null;
                throw iOException2;
            } catch (Throwable th42) {
                gZIPOutputStream = null;
                th2 = th42;
                httpURLConnection = httpURLConnection2;
                th = th2;
                dataOutputStream4.close();
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
            try {
                dataOutputStream.writeBytes(str);
                dataOutputStream.close();
                int responseCode;
                try {
                    responseCode = httpURLConnection2.getResponseCode();
                    if (dataOutputStream != null) {
                        if (gZIPOutputStream2 != null) {
                            gZIPOutputStream2.close();
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        return responseCode;
                    }
                    if (gZIPOutputStream2 != null) {
                        gZIPOutputStream2.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    return responseCode;
                } catch (IOException e22) {
                    filterOutputStream = gZIPOutputStream2;
                    obj2 = httpURLConnection2;
                    iOException2 = e22;
                    responseCode = 1;
                    dataOutputStream2 = dataOutputStream;
                    throw iOException2;
                } catch (Throwable th422) {
                    int i = 1;
                    dataOutputStream4 = dataOutputStream;
                    th2 = th422;
                    httpURLConnection = httpURLConnection2;
                    th = th2;
                    if (dataOutputStream4 != null && r3 == null) {
                        dataOutputStream4.close();
                    }
                    if (gZIPOutputStream != null) {
                        gZIPOutputStream.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (IOException e222) {
                dataOutputStream2 = dataOutputStream;
                filterOutputStream = gZIPOutputStream2;
                iOException = e222;
                obj = null;
                obj2 = httpURLConnection2;
                iOException2 = iOException;
                throw iOException2;
            } catch (Throwable th4222) {
                dataOutputStream4 = dataOutputStream;
                th2 = th4222;
                httpURLConnection = httpURLConnection2;
                th = th2;
                dataOutputStream4.close();
                if (gZIPOutputStream != null) {
                    gZIPOutputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (IOException e3) {
            iOException2 = e3;
            obj = null;
            dataOutputStream2 = null;
            dataOutputStream3 = null;
            throw iOException2;
        } catch (Throwable th5) {
            th = th5;
            gZIPOutputStream = null;
            httpURLConnection = null;
            dataOutputStream4.close();
            if (gZIPOutputStream != null) {
                gZIPOutputStream.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }
}
