package jp.maio.sdk.android;

class bd extends Exception {
    public final FailNotificationReason a;

    bd(FailNotificationReason failNotificationReason) {
        this.a = failNotificationReason;
    }
}
