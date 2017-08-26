package com.d.a.a;

import java.net.URI;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

/* compiled from: ResponseHandlerInterface */
public interface n {
    boolean getUsePoolThread();

    boolean getUseSynchronousMode();

    void onPostProcessResponse(n nVar, HttpResponse httpResponse);

    void onPreProcessResponse(n nVar, HttpResponse httpResponse);

    void sendCancelMessage();

    void sendFailureMessage(int i, Header[] headerArr, byte[] bArr, Throwable th);

    void sendFinishMessage();

    void sendResponseMessage(HttpResponse httpResponse);

    void sendRetryMessage(int i);

    void sendStartMessage();

    void setRequestHeaders(Header[] headerArr);

    void setRequestURI(URI uri);
}
