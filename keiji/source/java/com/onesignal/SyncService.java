package com.onesignal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.onesignal.OneSignal.LOG_LEVEL;

public class SyncService extends Service {
    private void checkOnFocusSync() {
        long GetUnsentActiveTime = OneSignal.GetUnsentActiveTime();
        if (GetUnsentActiveTime >= 60) {
            OneSignal.sendOnFocus(GetUnsentActiveTime, true);
        }
    }

    public void onCreate() {
        if (!OneSignal.startedSyncService) {
            OneSignal.appContext = getApplicationContext();
            new Thread(new Runnable() {
                public void run() {
                    if (OneSignal.getUserId() == null) {
                        SyncService.this.stopSelf();
                        return;
                    }
                    OneSignal.appId = OneSignal.getSavedAppId();
                    OneSignalStateSynchronizer.initUserState(OneSignal.appContext);
                    OneSignalStateSynchronizer.syncUserState(true);
                    SyncService.this.checkOnFocusSync();
                    SyncService.this.stopSelf();
                }
            }).start();
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return OneSignal.startedSyncService ? 1 : 2;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        onTaskRemoved();
    }

    static void onTaskRemoved() {
        OneSignal.Log(LOG_LEVEL.VERBOSE, "Starting SyncService:onTaskRemoved.");
        ActivityLifecycleHandler.focusHandlerThread.stopScheduledRunnable();
        OneSignalStateSynchronizer.stopAndPersist();
        OneSignal.onAppLostFocus(true);
        OneSignal.Log(LOG_LEVEL.VERBOSE, "Completed SyncService:onTaskRemoved.");
    }
}
