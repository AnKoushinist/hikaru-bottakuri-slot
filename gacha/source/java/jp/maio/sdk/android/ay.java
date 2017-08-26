package jp.maio.sdk.android;

class ay extends Exception {
    public final FailNotificationReason a;

    ay(FailNotificationReason failNotificationReason) {
        this.a = failNotificationReason;
    }
}
