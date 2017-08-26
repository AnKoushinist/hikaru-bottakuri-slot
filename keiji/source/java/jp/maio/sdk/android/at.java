package jp.maio.sdk.android;

final class at implements Runnable {
    final /* synthetic */ az a;
    final /* synthetic */ int b;

    at(az azVar, int i) {
        this.a = azVar;
        this.b = i;
    }

    public void run() {
        for (bo boVar : this.a.f.values()) {
            int i = 0;
            while (i > 0) {
                try {
                    Thread.sleep(20000);
                    break;
                } catch (bd e) {
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
            u a = boVar.a(this.b);
            if (a != null) {
                x c = a.c();
                if (c != null) {
                    a.i = this.a.b.d;
                    a.j = this.a.b.f;
                    c.b();
                }
            }
        }
    }
}
