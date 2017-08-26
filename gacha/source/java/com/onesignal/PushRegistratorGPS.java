package com.onesignal;

import android.app.AlertDialog.Builder;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.b.a;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.onesignal.OneSignal.LOG_LEVEL;
import com.onesignal.PushRegistrator.RegisteredHandler;

public class PushRegistratorGPS implements PushRegistrator {
    private static int GCM_RETRY_COUNT = 5;
    private Context appContext;
    private RegisteredHandler registeredHandler;

    public void registerForPush(Context context, String str, RegisteredHandler registeredHandler) {
        this.appContext = context;
        this.registeredHandler = registeredHandler;
        try {
            if (isGMSInstalledAndEnabled()) {
                registerInBackground(str);
                return;
            }
            OneSignal.Log(LOG_LEVEL.ERROR, "'Google Play services' app not installed or disabled on the device.");
            this.registeredHandler.complete(null, -7);
        } catch (Throwable th) {
            OneSignal.Log(LOG_LEVEL.ERROR, "Could not register with GCM due to an error with the AndroidManifest.xml file or with 'Google Play services'.", th);
            this.registeredHandler.complete(null, -8);
        }
    }

    private boolean isGooglePlayStoreInstalled() {
        try {
            PackageManager packageManager = this.appContext.getPackageManager();
            String str = (String) packageManager.getPackageInfo("com.android.vending", 1).applicationInfo.loadLabel(packageManager);
            if (str == null || str.equals("Market")) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private boolean isGMSInstalledAndEnabled() {
        boolean z = false;
        try {
            PackageInfo packageInfo = this.appContext.getPackageManager().getPackageInfo("com.google.android.gms", 1);
            if (!packageInfo.applicationInfo.enabled && isGooglePlayStoreInstalled()) {
                if (!OneSignal.getGcmPreferences(this.appContext).getBoolean("GT_DO_NOT_SHOW_MISSING_GPS", false)) {
                    try {
                        ShowUpdateGPSDialog();
                    } catch (Throwable th) {
                    }
                }
                return z;
            }
            z = packageInfo.applicationInfo.enabled;
        } catch (NameNotFoundException e) {
        }
        return z;
    }

    private void ShowUpdateGPSDialog() {
        OneSignal.runOnUiThread(new Runnable() {
            public void run() {
                final Context context = ActivityLifecycleHandler.curActivity;
                if (context != null && !OneSignal.mInitBuilder.mDisableGmsMissingPrompt) {
                    CharSequence resourceString = OSUtils.getResourceString(context, "onesignal_gms_missing_alert_text", "To receive push notifications please press 'Update' to enable 'Google Play services'.");
                    CharSequence resourceString2 = OSUtils.getResourceString(context, "onesignal_gms_missing_alert_button_update", "Update");
                    CharSequence resourceString3 = OSUtils.getResourceString(context, "onesignal_gms_missing_alert_button_skip", "Skip");
                    new Builder(context).setMessage(resourceString).setPositiveButton(resourceString2, new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                GooglePlayServicesUtil.getErrorPendingIntent(GooglePlayServicesUtil.isGooglePlayServicesAvailable(PushRegistratorGPS.this.appContext), context, 0).send();
                            } catch (CanceledException e) {
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    }).setNegativeButton(resourceString3, new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Editor edit = OneSignal.getGcmPreferences(context).edit();
                            edit.putBoolean("GT_DO_NOT_SHOW_MISSING_GPS", true);
                            edit.commit();
                        }
                    }).setNeutralButton(OSUtils.getResourceString(context, "onesignal_gms_missing_alert_button_close", "Close"), null).create().show();
                }
            }
        });
    }

    private void registerInBackground(final String str) {
        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                int i2 = 0;
                while (i < PushRegistratorGPS.GCM_RETRY_COUNT) {
                    try {
                        String a = a.a(PushRegistratorGPS.this.appContext).a(new String[]{str});
                        OneSignal.Log(LOG_LEVEL.INFO, "Device registered, Google Registration ID = " + a);
                        PushRegistratorGPS.this.registeredHandler.complete(a, 1);
                        return;
                    } catch (Throwable e) {
                        if (!"SERVICE_NOT_AVAILABLE".equals(e.getMessage())) {
                            OneSignal.Log(LOG_LEVEL.ERROR, "Error Getting Google Registration ID", e);
                            if (i2 == 0) {
                                PushRegistratorGPS.this.registeredHandler.complete(null, -11);
                                return;
                            }
                            return;
                        } else if (i >= PushRegistratorGPS.GCM_RETRY_COUNT - 1) {
                            OneSignal.Log(LOG_LEVEL.ERROR, "GCM_RETRY_COUNT of " + PushRegistratorGPS.GCM_RETRY_COUNT + " exceed! Could not get a Google Registration Id", e);
                        } else {
                            OneSignal.Log(LOG_LEVEL.INFO, "Google Play services returned SERVICE_NOT_AVAILABLE error. Current retry count: " + i, e);
                            if (i == 2) {
                                PushRegistratorGPS.this.registeredHandler.complete(null, -9);
                                i2 = 1;
                            }
                            Thread.sleep((long) ((i + 1) * 10000));
                        }
                    } catch (Throwable th) {
                    }
                }
                return;
                i++;
            }
        }).start();
    }
}
