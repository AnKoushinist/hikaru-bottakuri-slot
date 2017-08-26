package com.d.a.a;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

/* compiled from: AsyncHttpRequest */
public class b implements Runnable {
    private final AbstractHttpClient a;
    private final HttpContext b;
    private final HttpUriRequest c;
    private final n d;
    private int e;
    private final AtomicBoolean f = new AtomicBoolean();
    private boolean g;
    private volatile boolean h;
    private boolean i;

    public b(AbstractHttpClient abstractHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, n nVar) {
        this.a = (AbstractHttpClient) p.a((Object) abstractHttpClient, "client");
        this.b = (HttpContext) p.a((Object) httpContext, "context");
        this.c = (HttpUriRequest) p.a((Object) httpUriRequest, "request");
        this.d = (n) p.a((Object) nVar, "responseHandler");
    }

    public void a(b bVar) {
    }

    public void b(b bVar) {
    }

    public void run() {
        if (!a()) {
            if (!this.i) {
                this.i = true;
                a(this);
            }
            if (!a()) {
                this.d.sendStartMessage();
                if (!a()) {
                    try {
                        d();
                    } catch (Throwable e) {
                        if (a()) {
                            a.a.b("AsyncHttpRequest", "makeRequestWithRetries returned error", e);
                        } else {
                            this.d.sendFailureMessage(0, null, null, e);
                        }
                    }
                    if (!a()) {
                        this.d.sendFinishMessage();
                        if (!a()) {
                            b(this);
                            this.h = true;
                        }
                    }
                }
            }
        }
    }

    private void c() {
        if (!a()) {
            if (this.c.getURI().getScheme() == null) {
                throw new MalformedURLException("No valid URI scheme was provided");
            }
            if (this.d instanceof k) {
                ((k) this.d).a(this.c);
            }
            HttpResponse execute = this.a.execute(this.c, this.b);
            if (!a()) {
                this.d.onPreProcessResponse(this.d, execute);
                if (!a()) {
                    this.d.sendResponseMessage(execute);
                    if (!a()) {
                        this.d.onPostProcessResponse(this.d, execute);
                    }
                }
            }
        }
    }

    private void d() {
        int i;
        IOException iOException = null;
        HttpRequestRetryHandler httpRequestRetryHandler = this.a.getHttpRequestRetryHandler();
        boolean z = true;
        while (z) {
            try {
                c();
                return;
            } catch (IOException iOException2) {
                try {
                    boolean z2;
                    IOException iOException3;
                    IOException iOException4 = new IOException("UnknownHostException exception: " + iOException2.getMessage());
                    if (this.e > 0) {
                        int i2 = this.e + 1;
                        this.e = i2;
                        if (httpRequestRetryHandler.retryRequest(iOException2, i2, this.b)) {
                            z2 = true;
                            iOException3 = iOException4;
                            z = z2;
                            iOException2 = iOException3;
                        }
                    }
                    z2 = false;
                    iOException3 = iOException4;
                    z = z2;
                    iOException2 = iOException3;
                } catch (Throwable e) {
                    Throwable th = e;
                    a.a.b("AsyncHttpRequest", "Unhandled exception origin cause", th);
                    iOException2 = new IOException("Unhandled exception: " + th.getMessage());
                }
            } catch (NullPointerException e2) {
                iOException2 = new IOException("NPE in HttpClient: " + e2.getMessage());
                i = this.e + 1;
                this.e = i;
                z = httpRequestRetryHandler.retryRequest(iOException2, i, this.b);
            } catch (IOException e3) {
                iOException2 = e3;
                if (!a()) {
                    i = this.e + 1;
                    this.e = i;
                    z = httpRequestRetryHandler.retryRequest(iOException2, i, this.b);
                } else {
                    return;
                }
            }
        }
        throw iOException2;
        if (z) {
            this.d.sendRetryMessage(this.e);
        }
    }

    public boolean a() {
        boolean z = this.f.get();
        if (z) {
            e();
        }
        return z;
    }

    private synchronized void e() {
        if (!(this.h || !this.f.get() || this.g)) {
            this.g = true;
            this.d.sendCancelMessage();
        }
    }

    public boolean b() {
        return a() || this.h;
    }

    public boolean a(boolean z) {
        this.f.set(true);
        this.c.abort();
        return a();
    }
}
