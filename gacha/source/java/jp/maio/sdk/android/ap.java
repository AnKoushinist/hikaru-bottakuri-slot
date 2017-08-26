package jp.maio.sdk.android;

final class ap implements Runnable {
    final /* synthetic */ av a;
    final /* synthetic */ int b;

    ap(av avVar, int i) {
        this.a = avVar;
        this.b = i;
    }

    public void run() {
        for (bj bjVar : this.a.e.values()) {
            int i = 0;
            while (i > 0) {
                try {
                    Thread.sleep(20000);
                    break;
                } catch (ay e) {
                    i++;
                    if (i == 10) {
                        break;
                    }
                } catch (InterruptedException e2) {
                    i++;
                    if (i == 10) {
                        break;
                    }
                }
            }
            s a = bjVar.a(this.b);
            if (a != null) {
                v c = a.c();
                if (c != null) {
                    a.i = this.a.b.c;
                    a.j = this.a.b.e;
                    c.b();
                }
            }
        }
    }
}
