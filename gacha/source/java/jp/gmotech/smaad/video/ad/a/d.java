package jp.gmotech.smaad.video.ad.a;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import jp.gmotech.smaad.video.ad.c.b;
import org.json.JSONObject;
import twitter4j.TwitterResponse;

public class d {
    private static d b = null;
    final ExecutorService a;
    private HashMap c;

    private d() {
        this.c = null;
        this.a = new f(this);
        this.c = new HashMap();
    }

    public static d a() {
        if (b == null) {
            b = new d();
        }
        return b;
    }

    private synchronized void a(int i, String str, i iVar) {
        if (this.c != null) {
            switch (i) {
                case TwitterResponse.READ /*1*/:
                    this.c.put(str, iVar);
                    break;
                case TwitterResponse.READ_WRITE /*2*/:
                    this.c.remove(str);
                    break;
                default:
                    break;
            }
        }
    }

    private void a(i iVar) {
        iVar.a(this.a.submit(new e(this, iVar)));
        a(1, iVar.b(), iVar);
    }

    public String a(String str, h hVar) {
        i iVar = new i();
        iVar.a(hVar);
        iVar.a(new a(str, null));
        a(iVar);
        return iVar.b();
    }

    public String a(JSONObject jSONObject, h hVar) {
        i iVar = new i();
        iVar.a(hVar);
        iVar.a(new a(((b) jSONObject.opt("object")).a(), jSONObject.optJSONObject("argument")));
        a(iVar);
        return iVar.b();
    }
}
