package jp.maio.sdk.android;

final class br implements Runnable {
    final /* synthetic */ FailNotificationReason a;
    final /* synthetic */ String b;

    br(FailNotificationReason failNotificationReason, String str) {
        this.a = failNotificationReason;
        this.b = str;
    }

    public void run() {
        bk.a.onFailed(this.a, this.b);
    }
}
