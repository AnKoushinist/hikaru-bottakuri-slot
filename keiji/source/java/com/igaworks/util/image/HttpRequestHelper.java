package com.igaworks.util.image;

import android.util.Log;
import com.igaworks.impl.CommonFrameworkImpl;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import twitter4j.HttpResponseCode;

public class HttpRequestHelper {
    HttpURLConnection httpConn;

    public File download(String str, File file) {
        InputStream inputStream;
        try {
            this.httpConn = (HttpURLConnection) new URL(str).openConnection();
            this.httpConn.setReadTimeout(15000);
            this.httpConn.setConnectTimeout(15000);
            this.httpConn.setRequestMethod("GET");
            int responseCode = this.httpConn.getResponseCode();
            if (responseCode == HttpResponseCode.OK) {
                inputStream = this.httpConn.getInputStream();
                IOUtils.copy(inputStream, file);
                IOUtils.close(inputStream);
                this.httpConn.disconnect();
                return file;
            }
            Log.d("IGAW_QA", "No file to download. Server replied HTTP code: " + responseCode);
            throw new IOException("invalid response code:" + responseCode);
        } catch (SocketTimeoutException e) {
            try {
                if (CommonFrameworkImpl.isTest) {
                    Log.e("IGAW_QA", "HttpRequestHelper SocketTimeoutException: " + e.getMessage());
                }
                throw e;
            } catch (Throwable th) {
                this.httpConn.disconnect();
            }
        } catch (IOException e2) {
            if (CommonFrameworkImpl.isTest) {
                Log.e("IGAW_QA", "HttpRequestHelper IOException: " + e2.getMessage());
            }
            throw e2;
        } catch (Throwable th2) {
            IOUtils.close(inputStream);
        }
    }

    public static HttpRequestHelper getInstance() {
        return new HttpRequestHelper();
    }
}
