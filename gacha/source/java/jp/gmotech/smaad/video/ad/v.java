package jp.gmotech.smaad.video.ad;

class v {
    final /* synthetic */ q a;
    private boolean b = false;

    v(q qVar) {
        this.a = qVar;
    }

    public void a() {
        if (!this.b) {
            this.b = true;
            new Thread(new w(this)).start();
        }
    }
}
