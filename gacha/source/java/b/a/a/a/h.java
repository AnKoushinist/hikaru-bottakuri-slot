package b.a.a.a;

import b.a.a.a.a.b.t;
import b.a.a.a.a.c.e;
import b.a.a.a.a.c.f;
import b.a.a.a.a.c.m;

/* compiled from: InitializationTask */
class h<Result> extends f<Void, Void, Result> {
    final i<Result> a;

    public h(i<Result> iVar) {
        this.a = iVar;
    }

    protected void a() {
        super.a();
        t a = a("onPreExecute");
        try {
            boolean l_ = this.a.l_();
            a.b();
            if (!l_) {
                a(true);
            }
        } catch (m e) {
            throw e;
        } catch (Throwable e2) {
            c.h().e("Fabric", "Failure onPreExecute()", e2);
            a.b();
            a(true);
        } catch (Throwable th) {
            a.b();
            a(true);
        }
    }

    protected Result a(Void... voidArr) {
        t a = a("doInBackground");
        Result result = null;
        if (!e()) {
            result = this.a.e();
        }
        a.b();
        return result;
    }

    protected void a(Result result) {
        this.a.a((Object) result);
        this.a.h.a((Object) result);
    }

    protected void b(Result result) {
        this.a.b((Object) result);
        this.a.h.a(new g(this.a.b() + " Initialization was cancelled"));
    }

    public e b() {
        return e.HIGH;
    }

    private t a(String str) {
        t tVar = new t(this.a.b() + "." + str, "KitInitialization");
        tVar.a();
        return tVar;
    }
}
