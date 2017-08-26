package jp.maio.sdk.android;

final class bx implements Runnable {
    final /* synthetic */ FailNotificationReason a;
    final /* synthetic */ String b;

    bx(FailNotificationReason failNotificationReason, String str) {
        this.a = failNotificationReason;
        this.b = str;
    }

    public void run() {
        bp.b.onFailed(this.a, this.b);
    }
}
