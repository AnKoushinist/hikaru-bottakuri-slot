package jp.maio.sdk.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

class u {
    public static void a(Context context, Uri uri, int i) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setFlags(i);
        context.startActivity(intent);
    }
}
