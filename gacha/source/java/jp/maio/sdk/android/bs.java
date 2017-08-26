package jp.maio.sdk.android;

final class bs implements Runnable {
    final /* synthetic */ FailNotificationReason a;
    final /* synthetic */ String b;

    bs(FailNotificationReason failNotificationReason, String str) {
        this.a = failNotificationReason;
        this.b = str;
    }

    public void run() {
        bk.b.onFailed(this.a, this.b);
    }
}
