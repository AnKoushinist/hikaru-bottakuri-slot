package com.onesignal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class NotificationOpenedActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        NotificationOpenedProcessor.processFromActivity(this, getIntent());
        finish();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        NotificationOpenedProcessor.processFromActivity(this, getIntent());
        finish();
    }
}
