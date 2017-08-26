package com.d.a.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URI;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.util.ByteArrayBuffer;
import twitter4j.HttpResponseCode;

/* compiled from: AsyncHttpResponseHandler */
public abstract class c implements n {
    protected static final int BUFFER_SIZE = 4096;
    protected static final int CANCEL_MESSAGE = 6;
    public static final String DEFAULT_CHARSET = "UTF-8";
    protected static final int FAILURE_MESSAGE = 1;
    protected static final int FINISH_MESSAGE = 3;
    private static final String LOG_TAG = "AsyncHttpRH";
    protected static final int PROGRESS_MESSAGE = 4;
    protected static final int RETRY_MESSAGE = 5;
    protected static final int START_MESSAGE = 2;
    protected static final int SUCCESS_MESSAGE = 0;
    public static final String UTF8_BOM = "\ufeff";
    private WeakReference<Object> TAG;
    private Handler handler;
    private Looper looper;
    private Header[] requestHeaders;
    private URI requestURI;
    private String responseCharset;
    private boolean usePoolThread;
    private boolean useSynchronousMode;

    /* compiled from: AsyncHttpResponseHandler */
    private static class a extends Handler {
        private final c a;

        a(c cVar, Looper looper) {
            super(looper);
            this.a = cVar;
        }

        public void handleMessage(Message message) {
            this.a.handleMessage(message);
        }
    }

    public abstract void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th);

    public abstract void onSuccess(int i, Header[] headerArr, byte[] bArr);

    public c() {
        this(null);
    }

    public c(Looper looper) {
        this.responseCharset = DEFAULT_CHARSET;
        this.requestURI = null;
        this.requestHeaders = null;
        this.looper = null;
        this.TAG = new WeakReference(null);
        if (looper == null) {
            looper = Looper.myLooper();
        }
        this.looper = looper;
        setUseSynchronousMode(false);
        setUsePoolThread(false);
    }

    public c(boolean z) {
        this.responseCharset = DEFAULT_CHARSET;
        this.requestURI = null;
        this.requestHeaders = null;
        this.looper = null;
        this.TAG = new WeakReference(null);
        setUsePoolThread(z);
        if (!getUsePoolThread()) {
            this.looper = Looper.myLooper();
            setUseSynchronousMode(false);
        }
    }

    public void setTag(Object obj) {
        this.TAG = new WeakReference(obj);
    }

    public Object getTag() {
        return this.TAG.get();
    }

    public URI getRequestURI() {
        return this.requestURI;
    }

    public Header[] getRequestHeaders() {
        return this.requestHeaders;
    }

    public void setRequestURI(URI uri) {
        this.requestURI = uri;
    }

    public void setRequestHeaders(Header[] headerArr) {
        this.requestHeaders = headerArr;
    }

    public boolean getUseSynchronousMode() {
        return this.useSynchronousMode;
    }

    public void setUseSynchronousMode(boolean z) {
        if (!z && this.looper == null) {
            z = true;
            a.a.c(LOG_TAG, "Current thread has not called Looper.prepare(). Forcing synchronous mode.");
        }
        if (!z && this.handler == null) {
            this.handler = new a(this, this.looper);
        } else if (z && this.handler != null) {
            this.handler = null;
        }
        this.useSynchronousMode = z;
    }

    public boolean getUsePoolThread() {
        return this.usePoolThread;
    }

    public void setUsePoolThread(boolean z) {
        if (z) {
            this.looper = null;
            this.handler = null;
        }
        this.usePoolThread = z;
    }

    public void setCharset(String str) {
        this.responseCharset = str;
    }

    public String getCharset() {
        return this.responseCharset == null ? DEFAULT_CHARSET : this.responseCharset;
    }

    public void onProgress(long j, long j2) {
        h hVar = a.a;
        String str = LOG_TAG;
        String str2 = "Progress %d from %d (%2.0f%%)";
        Object[] objArr = new Object[FINISH_MESSAGE];
        objArr[SUCCESS_MESSAGE] = Long.valueOf(j);
        objArr[FAILURE_MESSAGE] = Long.valueOf(j2);
        objArr[START_MESSAGE] = Double.valueOf(j2 > 0 ? ((((double) j) * 1.0d) / ((double) j2)) * 100.0d : -1.0d);
        hVar.a(str, String.format(str2, objArr));
    }

    public void onStart() {
    }

    public void onFinish() {
    }

    public void onPreProcessResponse(n nVar, HttpResponse httpResponse) {
    }

    public void onPostProcessResponse(n nVar, HttpResponse httpResponse) {
    }

    public void onRetry(int i) {
        h hVar = a.a;
        String str = LOG_TAG;
        Object[] objArr = new Object[FAILURE_MESSAGE];
        objArr[SUCCESS_MESSAGE] = Integer.valueOf(i);
        hVar.b(str, String.format("Request retry no. %d", objArr));
    }

    public void onCancel() {
        a.a.b(LOG_TAG, "Request got cancelled");
    }

    public void onUserException(Throwable th) {
        a.a.b(LOG_TAG, "User-space exception detected!", th);
        throw new RuntimeException(th);
    }

    public final void sendProgressMessage(long j, long j2) {
        Object obj = new Object[START_MESSAGE];
        obj[SUCCESS_MESSAGE] = Long.valueOf(j);
        obj[FAILURE_MESSAGE] = Long.valueOf(j2);
        sendMessage(obtainMessage(PROGRESS_MESSAGE, obj));
    }

    public final void sendSuccessMessage(int i, Header[] headerArr, byte[] bArr) {
        Object obj = new Object[FINISH_MESSAGE];
        obj[SUCCESS_MESSAGE] = Integer.valueOf(i);
        obj[FAILURE_MESSAGE] = headerArr;
        obj[START_MESSAGE] = bArr;
        sendMessage(obtainMessage(SUCCESS_MESSAGE, obj));
    }

    public final void sendFailureMessage(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        Object obj = new Object[PROGRESS_MESSAGE];
        obj[SUCCESS_MESSAGE] = Integer.valueOf(i);
        obj[FAILURE_MESSAGE] = headerArr;
        obj[START_MESSAGE] = bArr;
        obj[FINISH_MESSAGE] = th;
        sendMessage(obtainMessage(FAILURE_MESSAGE, obj));
    }

    public final void sendStartMessage() {
        sendMessage(obtainMessage(START_MESSAGE, null));
    }

    public final void sendFinishMessage() {
        sendMessage(obtainMessage(FINISH_MESSAGE, null));
    }

    public final void sendRetryMessage(int i) {
        Object obj = new Object[FAILURE_MESSAGE];
        obj[SUCCESS_MESSAGE] = Integer.valueOf(i);
        sendMessage(obtainMessage(RETRY_MESSAGE, obj));
    }

    public final void sendCancelMessage() {
        sendMessage(obtainMessage(CANCEL_MESSAGE, null));
    }

    protected void handleMessage(Message message) {
        try {
            Object[] objArr;
            switch (message.what) {
                case SUCCESS_MESSAGE /*0*/:
                    objArr = (Object[]) message.obj;
                    if (objArr == null || objArr.length < FINISH_MESSAGE) {
                        a.a.d(LOG_TAG, "SUCCESS_MESSAGE didn't got enough params");
                        return;
                    } else {
                        onSuccess(((Integer) objArr[SUCCESS_MESSAGE]).intValue(), (Header[]) objArr[FAILURE_MESSAGE], (byte[]) objArr[START_MESSAGE]);
                        return;
                    }
                case FAILURE_MESSAGE /*1*/:
                    objArr = (Object[]) message.obj;
                    if (objArr == null || objArr.length < PROGRESS_MESSAGE) {
                        a.a.d(LOG_TAG, "FAILURE_MESSAGE didn't got enough params");
                        return;
                    } else {
                        onFailure(((Integer) objArr[SUCCESS_MESSAGE]).intValue(), (Header[]) objArr[FAILURE_MESSAGE], (byte[]) objArr[START_MESSAGE], (Throwable) objArr[FINISH_MESSAGE]);
                        return;
                    }
                case START_MESSAGE /*2*/:
                    onStart();
                    return;
                case FINISH_MESSAGE /*3*/:
                    onFinish();
                    return;
                case PROGRESS_MESSAGE /*4*/:
                    objArr = (Object[]) message.obj;
                    if (objArr == null || objArr.length < START_MESSAGE) {
                        a.a.d(LOG_TAG, "PROGRESS_MESSAGE didn't got enough params");
                        return;
                    } else {
                        onProgress(((Long) objArr[SUCCESS_MESSAGE]).longValue(), ((Long) objArr[FAILURE_MESSAGE]).longValue());
                        return;
                    }
                case RETRY_MESSAGE /*5*/:
                    objArr = (Object[]) message.obj;
                    if (objArr == null || objArr.length != FAILURE_MESSAGE) {
                        a.a.d(LOG_TAG, "RETRY_MESSAGE didn't get enough params");
                        return;
                    } else {
                        onRetry(((Integer) objArr[SUCCESS_MESSAGE]).intValue());
                        return;
                    }
                case CANCEL_MESSAGE /*6*/:
                    onCancel();
                    return;
                default:
                    return;
            }
        } catch (Throwable th) {
            onUserException(th);
        }
        onUserException(th);
    }

    protected void sendMessage(Message message) {
        if (getUseSynchronousMode() || this.handler == null) {
            handleMessage(message);
        } else if (!Thread.currentThread().isInterrupted()) {
            p.a(this.handler != null, "handler should not be null!");
            this.handler.sendMessage(message);
        }
    }

    protected void postRunnable(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (getUseSynchronousMode() || this.handler == null) {
            runnable.run();
        } else {
            this.handler.post(runnable);
        }
    }

    protected Message obtainMessage(int i, Object obj) {
        return Message.obtain(this.handler, i, obj);
    }

    public void sendResponseMessage(HttpResponse httpResponse) {
        if (!Thread.currentThread().isInterrupted()) {
            StatusLine statusLine = httpResponse.getStatusLine();
            byte[] responseData = getResponseData(httpResponse.getEntity());
            if (!Thread.currentThread().isInterrupted()) {
                if (statusLine.getStatusCode() >= HttpResponseCode.MULTIPLE_CHOICES) {
                    sendFailureMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), responseData, new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()));
                } else {
                    sendSuccessMessage(statusLine.getStatusCode(), httpResponse.getAllHeaders(), responseData);
                }
            }
        }
    }

    byte[] getResponseData(HttpEntity httpEntity) {
        int i = BUFFER_SIZE;
        if (httpEntity != null) {
            InputStream content = httpEntity.getContent();
            if (content != null) {
                long contentLength = httpEntity.getContentLength();
                if (contentLength > 2147483647L) {
                    throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
                }
                if (contentLength > 0) {
                    i = (int) contentLength;
                }
                try {
                    ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(i);
                    byte[] bArr = new byte[BUFFER_SIZE];
                    long j = 0;
                    while (true) {
                        int read = content.read(bArr);
                        if (read == -1 || Thread.currentThread().isInterrupted()) {
                            break;
                        }
                        long j2 = ((long) read) + j;
                        byteArrayBuffer.append(bArr, SUCCESS_MESSAGE, read);
                        if (contentLength <= 0) {
                            j = 1;
                        } else {
                            j = contentLength;
                        }
                        sendProgressMessage(j2, j);
                        j = j2;
                    }
                    a.a(content);
                    a.a(httpEntity);
                    return byteArrayBuffer.toByteArray();
                } catch (OutOfMemoryError e) {
                    System.gc();
                    throw new IOException("File too large to fit into available memory");
                } catch (Throwable th) {
                    a.a(content);
                    a.a(httpEntity);
                }
            }
        }
        return null;
    }
}
