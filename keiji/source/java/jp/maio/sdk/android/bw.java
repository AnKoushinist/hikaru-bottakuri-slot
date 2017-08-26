package jp.maio.sdk.android;

final class bw implements Runnable {
    final /* synthetic */ FailNotificationReason a;
    final /* synthetic */ String b;

    bw(FailNotificationReason failNotificationReason, String str) {
        this.a = failNotificationReason;
        this.b = str;
    }

    public void run() {
        bp.a.onFailed(this.a, this.b);
    }
}
