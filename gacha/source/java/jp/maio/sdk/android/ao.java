package jp.maio.sdk.android;

final class ao implements Runnable {
    final /* synthetic */ av a;

    ao(av avVar) {
        this.a = avVar;
    }

    public void run() {
        av avVar = this.a;
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
            s b = bjVar.b();
            if (b != null) {
                v c = b.c();
                if (c != null) {
                    b.i = this.a.b.c;
                    b.j = this.a.b.e;
                    c.b();
                }
            }
        }
    }
}
