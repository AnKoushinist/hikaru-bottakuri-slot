package com.d.a.a;

import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import org.apache.http.client.methods.HttpUriRequest;

/* compiled from: RangeFileAsyncHttpResponseHandler */
public abstract class k extends e {
    private long a;
    private boolean b;

    public void a(HttpUriRequest httpUriRequest) {
        if (this.file.exists() && this.file.canWrite()) {
            this.a = this.file.length();
        }
        if (this.a > 0) {
            this.b = true;
            httpUriRequest.setHeader("Range", "bytes=" + this.a + Operation.MINUS);
        }
    }
}
