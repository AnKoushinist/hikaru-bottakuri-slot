package com.tapjoy.internal;

import android.graphics.Bitmap;
import java.io.InputStream;
import java.net.ContentHandler;
import java.net.URLConnection;

public final class u extends ContentHandler {
    public static final u a = new u();

    public final /* synthetic */ Object getContent(URLConnection uRLConnection) {
        return a(uRLConnection);
    }

    private u() {
    }

    public static Bitmap a(URLConnection uRLConnection) {
        InputStream inputStream = uRLConnection.getInputStream();
        try {
            Bitmap a = v.a.a(inputStream);
            return a;
        } finally {
            inputStream.close();
        }
    }
}
