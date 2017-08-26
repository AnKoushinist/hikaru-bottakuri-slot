package jp.gmotech.smaad.video.ad;

import java.util.ArrayList;
import jp.gmotech.smaad.video.ad.b.a;

class p {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private ArrayList m;
    private b n;
    private String o;

    p() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.m = new ArrayList();
    }

    private String o(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.replace("&amp;", "&"));
        if (a.a(this.o).b() != null) {
            stringBuilder.append("&advertising_id=").append(a.a(this.o).b());
        }
        stringBuilder.append("&u=").append(a.a(this.o).c());
        return stringBuilder.toString();
    }

    String a() {
        y f = a.a(this.o).f();
        String str = a.a(this.o).e() ? f == y.LAND ? this.d : this.f : f == y.LAND ? this.c : this.e;
        a.a("SmaAdVideoInfo", "[getEndcardUrl] mOrientation : " + f + ", endcardUrl : " + str);
        return str;
    }

    String a(int i) {
        if (i <= this.m.size() - 1) {
            String str = (String) this.m.get(i);
            a.a("SmaAdVideoInfo", "[getVideoUrl] bitrate : " + (i == 0 ? "high" : "low") + ", videoUrl : " + str);
            return str;
        }
        str = (String) this.m.get(0);
        a.a("SmaAdVideoInfo", "[getVideoUrl] videoUrl : " + str);
        return str;
    }

    public void a(String str) {
        this.o = str;
    }

    void a(b bVar) {
        a.a("SmaAdVideoInfo", "[setPlayListener] playListener : " + bVar);
        this.n = bVar;
    }

    String b() {
        a.a("SmaAdVideoInfo", "[getVideoPath] videoPath : " + this.b);
        return this.b;
    }

    void b(String str) {
        a.a("SmaAdVideoInfo", "[setAdid] adid : " + str);
        this.a = str;
    }

    String c() {
        a.a("SmaAdVideoInfo", "[getStartUrl] startUrl : " + this.h);
        return this.h;
    }

    void c(String str) {
        a.a("SmaAdVideoInfo", "[setLandscapeUrl] landscapeUrl : " + str);
        this.c = str.replace("&amp;", "&");
    }

    String d() {
        a.a("SmaAdVideoInfo", "[getCompleteUrl] completeUrl : " + this.i);
        return this.i;
    }

    void d(String str) {
        a.a("SmaAdVideoInfo", "[setLandscape43Url] landscape43Url : " + str);
        this.d = str.replace("&amp;", "&");
    }

    String e() {
        a.a("SmaAdVideoInfo", "[getMarketUrl] mMarketUrl : " + this.g);
        return this.g;
    }

    void e(String str) {
        a.a("SmaAdVideoInfo", "[setPortraitUrl] portraitUrl : " + str);
        this.e = str.replace("&amp;", "&");
    }

    String f() {
        a.a("SmaAdVideoInfo", "[getClickThrough] clickThrough : " + this.j);
        return this.j;
    }

    void f(String str) {
        a.a("SmaAdVideoInfo", "[setPortrait43Url] portrait43Url : " + str);
        this.f = str.replace("&amp;", "&");
    }

    String g() {
        a.a("SmaAdVideoInfo", "[getPay] pay : " + this.l);
        return this.l;
    }

    void g(String str) {
        a.a("SmaAdVideoInfo", "[setVideoUrl] videoUrl : " + str);
        if (str != null) {
            if (str.contains("high")) {
                this.m.add(0, str);
            } else {
                this.m.add(1, str);
            }
        }
    }

    b h() {
        a.a("SmaAdVideoInfo", "[getPlayListener] playListener : " + this.n);
        return this.n;
    }

    void h(String str) {
        a.a("SmaAdVideoInfo", "[setVideoPath] videoPath : " + str);
        this.b = str;
    }

    void i(String str) {
        a.a("SmaAdVideoInfo", "[setStartUrl] startUrl : " + str);
        this.h = o(str);
    }

    void j(String str) {
        a.a("SmaAdVideoInfo", "[setCompleteUrl] completeUrl : " + str);
        this.i = o(str);
    }

    void k(String str) {
        a.a("SmaAdVideoInfo", "[setMarketUrl] marketUrl : " + str);
        this.g = str;
    }

    void l(String str) {
        a.a("SmaAdVideoInfo", "[setClickThrough] clickThrough : " + str);
        this.j = o(str);
    }

    void m(String str) {
        a.a("SmaAdVideoInfo", "[setUserPay] userPay : " + str);
        this.k = str;
    }

    void n(String str) {
        a.a("SmaAdVideoInfo", "[setPay] pay : " + str);
        this.l = str;
    }
}
