package jp.co.vaz.creator.hikaru2;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.a.b;
import android.support.v4.app.ad.d;
import android.util.Log;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;

public class LocalNotificationReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.d(getClass().getSimpleName(), "push receive");
        int intExtra = intent.getIntExtra(TapjoyConstants.TJC_NOTIFICATION_ID, 0);
        CharSequence stringExtra = intent.getStringExtra(String.MESSAGE);
        Intent intent2 = new Intent(context, AppActivity.class);
        intent2.setFlags(131072);
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent2, 134217728);
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        d dVar = new d(context);
        dVar.a(context.getString(R.string.app_name));
        dVar.b(stringExtra);
        dVar.a(R.mipmap.ic_small);
        dVar.a(decodeResource);
        dVar.e(b.c(context, R.color.base));
        dVar.c(stringExtra);
        dVar.b(true);
        dVar.c(-1);
        dVar.a(activity);
        ((NotificationManager) context.getSystemService("notification")).notify(intExtra, dVar.a());
    }
}
