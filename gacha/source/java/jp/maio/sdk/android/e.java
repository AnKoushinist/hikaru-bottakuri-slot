package jp.maio.sdk.android;

class e implements Runnable {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public void run() {
        if (this.a.a.h != null && this.a.a.h.isPlaying()) {
            int duration = this.a.a.h.a.getDuration();
            this.a.a.g.a(this.a.a.h.a.getCurrentPosition(), duration);
        }
    }
}
