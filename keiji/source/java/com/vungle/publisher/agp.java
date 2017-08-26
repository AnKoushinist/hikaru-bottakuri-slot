package com.vungle.publisher;

import android.content.Intent;
import android.net.Uri;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class agp {
    @Inject
    agp() {
    }

    public static Intent a(String str, Uri uri) {
        return new Intent(str, uri);
    }
}
