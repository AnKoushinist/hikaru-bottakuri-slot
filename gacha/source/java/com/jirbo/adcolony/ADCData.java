package com.jirbo.adcolony;

import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import jp.co.vaz.creator.hikaru.R;
import org.cocos2dx.lib.BuildConfig;

public class ADCData {
    static i a = new h();
    static i b = new a();
    static i c = new d();

    static class i {
        i() {
        }

        boolean m() {
            return false;
        }

        boolean f() {
            return false;
        }

        boolean k() {
            return false;
        }

        boolean p() {
            return b_() || c();
        }

        boolean b_() {
            return false;
        }

        boolean c() {
            return false;
        }

        boolean a() {
            return false;
        }

        boolean c_() {
            return false;
        }

        boolean g() {
            return true;
        }

        g n() {
            return null;
        }

        c h() {
            return null;
        }

        String b() {
            return q();
        }

        double d() {
            return 0.0d;
        }

        int e() {
            return 0;
        }

        boolean l() {
            return false;
        }

        public String toString() {
            return q();
        }

        String q() {
            ae yVar = new y();
            a(yVar);
            return yVar.toString();
        }

        void a(ae aeVar) {
        }

        void a(ae aeVar, String str) {
            if (str != null) {
                aeVar.b('\"');
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    char charAt = str.charAt(i);
                    switch (charAt) {
                        case AdInfo.BANNER_KIND_BANNER8 /*8*/:
                            aeVar.a("\\b");
                            break;
                        case AdInfo.BANNER_KIND_INTERSTITIAL1 /*9*/:
                            aeVar.a("\\t");
                            break;
                        case AdInfo.BANNER_KIND_WALL1 /*10*/:
                            aeVar.a("\\n");
                            break;
                        case Constants.MOVIE_REWARD_TYPE /*12*/:
                            aeVar.a("\\f");
                            break;
                        case R.styleable.Toolbar_subtitleTextAppearance /*13*/:
                            aeVar.a("\\r");
                            break;
                        case R.styleable.AppCompatTheme_actionModePasteDrawable /*34*/:
                            aeVar.a("\\\"");
                            break;
                        case R.styleable.AppCompatTheme_dropdownListPreferredItemHeight /*47*/:
                            aeVar.a("\\/");
                            break;
                        case R.styleable.AppCompatTheme_colorBackgroundFloating /*92*/:
                            aeVar.a("\\\\");
                            break;
                        default:
                            if (charAt >= ' ' && charAt <= '~') {
                                aeVar.b(charAt);
                                break;
                            }
                            aeVar.a("\\u");
                            int i2 = charAt;
                            for (int i3 = 0; i3 < 4; i3++) {
                                int i4 = (i2 >> 12) & 15;
                                i2 <<= 4;
                                if (i4 <= 9) {
                                    aeVar.a((long) i4);
                                } else {
                                    aeVar.b((char) ((i4 - 10) + 97));
                                }
                            }
                            break;
                    }
                }
                aeVar.b('\"');
            }
        }
    }

    static class a extends i {
        a() {
        }

        boolean a() {
            return true;
        }

        String b() {
            return TapjoyConstants.TJC_FALSE;
        }

        void a(ae aeVar) {
            aeVar.a(TapjoyConstants.TJC_FALSE);
        }
    }

    static class b extends i {
        int a;

        b(int i) {
            this.a = i;
        }

        boolean c() {
            return true;
        }

        double d() {
            return (double) this.a;
        }

        int e() {
            return this.a;
        }

        void a(ae aeVar) {
            aeVar.a((long) this.a);
        }
    }

    static class c extends i {
        ArrayList<i> a = new ArrayList();

        c() {
        }

        boolean f() {
            return true;
        }

        boolean g() {
            return this.a.size() == 0 || (this.a.size() == 1 && ((i) this.a.get(0)).g());
        }

        c h() {
            return this;
        }

        void a(ae aeVar) {
            int size = this.a.size();
            if (size == 0) {
                aeVar.a("[]");
            } else if (size == 1 && ((i) this.a.get(0)).g()) {
                aeVar.a("[");
                ((i) this.a.get(0)).a(aeVar);
                aeVar.a("]");
            } else {
                aeVar.b("[");
                aeVar.i += 2;
                int i = 0;
                int i2 = 1;
                while (i < size) {
                    int i3;
                    if (i2 != 0) {
                        i3 = 0;
                    } else {
                        aeVar.c(',');
                        i3 = i2;
                    }
                    ((i) this.a.get(i)).a(aeVar);
                    i++;
                    i2 = i3;
                }
                aeVar.d();
                aeVar.i -= 2;
                aeVar.a("]");
            }
        }

        int i() {
            return this.a.size();
        }

        void j() {
            this.a.clear();
        }

        c a(i iVar) {
            this.a.add(iVar);
            return this;
        }

        c a(String str) {
            a(new f(str));
            return this;
        }

        c a(double d) {
            a(new e(d));
            return this;
        }

        c a(int i) {
            a(new b(i));
            return this;
        }

        c a(boolean z) {
            a(z ? ADCData.a : ADCData.b);
            return this;
        }

        c a(c cVar) {
            for (int i = 0; i < cVar.i(); i++) {
                a((i) cVar.a.get(i));
            }
            return this;
        }

        g a(int i, g gVar) {
            i iVar = (i) this.a.get(i);
            if (iVar == null || !iVar.m()) {
                return gVar;
            }
            return iVar.n();
        }

        c a(int i, c cVar) {
            i iVar = (i) this.a.get(i);
            if (iVar == null || !iVar.f()) {
                return cVar;
            }
            return iVar.h();
        }

        String a(int i, String str) {
            i iVar = (i) this.a.get(i);
            if (iVar == null || !iVar.k()) {
                return str;
            }
            return iVar.b();
        }

        double a(int i, double d) {
            i iVar = (i) this.a.get(i);
            if (iVar == null || !iVar.p()) {
                return d;
            }
            return iVar.d();
        }

        int a(int i, int i2) {
            i iVar = (i) this.a.get(i);
            if (iVar == null || !iVar.p()) {
                return i2;
            }
            return iVar.e();
        }

        boolean a(int i, boolean z) {
            i iVar = (i) this.a.get(i);
            if (iVar == null) {
                return z;
            }
            if (iVar.a() || iVar.k()) {
                return iVar.l();
            }
            return z;
        }

        g b(int i) {
            g a = a(i, null);
            return a != null ? a : new g();
        }

        c c(int i) {
            c a = a(i, null);
            return a != null ? a : new c();
        }

        String d(int i) {
            return a(i, BuildConfig.FLAVOR);
        }

        double e(int i) {
            return a(i, 0.0d);
        }

        int f(int i) {
            return a(i, 0);
        }

        boolean g(int i) {
            return a(i, false);
        }

        i a_() {
            return (i) this.a.remove(this.a.size() - 1);
        }
    }

    static class d extends i {
        d() {
        }

        boolean c_() {
            return true;
        }

        String b() {
            return "null";
        }

        void a(ae aeVar) {
            aeVar.a("null");
        }
    }

    static class e extends i {
        double a;

        e(double d) {
            this.a = d;
        }

        boolean b_() {
            return true;
        }

        double d() {
            return this.a;
        }

        int e() {
            return (int) this.a;
        }

        void a(ae aeVar) {
            aeVar.a(this.a);
        }
    }

    static class f extends i implements Serializable {
        String a;

        f(String str) {
            this.a = str;
        }

        boolean k() {
            return true;
        }

        String b() {
            return this.a;
        }

        double d() {
            try {
                return Double.parseDouble(this.a);
            } catch (NumberFormatException e) {
                return 0.0d;
            }
        }

        int e() {
            return (int) d();
        }

        boolean l() {
            String toLowerCase = this.a.toLowerCase();
            if (toLowerCase.equals(TapjoyConstants.TJC_TRUE) || toLowerCase.equals("yes")) {
                return true;
            }
            return false;
        }

        void a(ae aeVar) {
            a(aeVar, this.a);
        }
    }

    static class g extends i implements Serializable {
        HashMap<String, i> a = new HashMap();
        ArrayList<String> b = new ArrayList();

        g() {
        }

        boolean m() {
            return true;
        }

        boolean g() {
            return this.a.size() < 0 || (this.a.size() == 1 && ((i) this.a.get(this.b.get(0))).g());
        }

        g n() {
            return this;
        }

        void a(ae aeVar) {
            int size = this.b.size();
            if (size == 0) {
                aeVar.a("{}");
            } else if (size == 1 && ((i) this.a.get(this.b.get(0))).g()) {
                aeVar.a("{");
                r0 = (String) this.b.get(0);
                r1 = (i) this.a.get(r0);
                a(aeVar, r0);
                aeVar.b(':');
                r1.a(aeVar);
                aeVar.a("}");
            } else {
                aeVar.b("{");
                aeVar.i += 2;
                int i = 0;
                int i2 = 1;
                while (i < size) {
                    int i3;
                    if (i2 != 0) {
                        i3 = 0;
                    } else {
                        aeVar.c(',');
                        i3 = i2;
                    }
                    r0 = (String) this.b.get(i);
                    r1 = (i) this.a.get(r0);
                    a(aeVar, r0);
                    aeVar.b(':');
                    if (!r1.g()) {
                        aeVar.d();
                    }
                    r1.a(aeVar);
                    i++;
                    i2 = i3;
                }
                aeVar.d();
                aeVar.i -= 2;
                aeVar.a("}");
            }
        }

        int o() {
            return this.b.size();
        }

        String a(int i) {
            return (String) this.b.get(i);
        }

        boolean a(String str) {
            return this.a.containsKey(str);
        }

        g a(String str, g gVar) {
            i iVar = (i) this.a.get(str);
            if (iVar == null || !iVar.m()) {
                return gVar;
            }
            return iVar.n();
        }

        c a(String str, c cVar) {
            i iVar = (i) this.a.get(str);
            if (iVar == null || !iVar.f()) {
                return cVar;
            }
            return iVar.h();
        }

        ArrayList<String> a(String str, ArrayList<String> arrayList) {
            c c = c(str);
            if (c != null) {
                arrayList = new ArrayList();
                for (int i = 0; i < c.i(); i++) {
                    String d = c.d(i);
                    if (d != null) {
                        arrayList.add(d);
                    }
                }
            }
            return arrayList;
        }

        String a(String str, String str2) {
            i iVar = (i) this.a.get(str);
            if (iVar == null || !iVar.k()) {
                return str2;
            }
            return iVar.b();
        }

        double a(String str, double d) {
            i iVar = (i) this.a.get(str);
            if (iVar == null || !iVar.p()) {
                return d;
            }
            return iVar.d();
        }

        int a(String str, int i) {
            i iVar = (i) this.a.get(str);
            if (iVar == null || !iVar.p()) {
                return i;
            }
            return iVar.e();
        }

        boolean a(String str, boolean z) {
            i iVar = (i) this.a.get(str);
            if (iVar == null) {
                return z;
            }
            if (iVar.a() || iVar.k()) {
                return iVar.l();
            }
            return z;
        }

        g b(String str) {
            g a = a(str, null);
            return a != null ? a : new g();
        }

        c c(String str) {
            c a = a(str, null);
            return a != null ? a : new c();
        }

        ArrayList<String> d(String str) {
            ArrayList<String> a = a(str, null);
            if (a == null) {
                return new ArrayList();
            }
            return a;
        }

        String e(String str) {
            return a(str, BuildConfig.FLAVOR);
        }

        double f(String str) {
            return a(str, 0.0d);
        }

        int g(String str) {
            return a(str, 0);
        }

        boolean h(String str) {
            return a(str, false);
        }

        void a(String str, i iVar) {
            if (!this.a.containsKey(str)) {
                this.b.add(str);
            }
            this.a.put(str, iVar);
        }

        void b(String str, String str2) {
            a(str, new f(str2));
        }

        void b(String str, double d) {
            a(str, new e(d));
        }

        void b(String str, int i) {
            a(str, new b(i));
        }

        void b(String str, boolean z) {
            a(str, z ? ADCData.a : ADCData.b);
        }
    }

    static class h extends i {
        h() {
        }

        boolean a() {
            return true;
        }

        String b() {
            return TapjoyConstants.TJC_TRUE;
        }

        double d() {
            return 1.0d;
        }

        int e() {
            return 1;
        }

        boolean l() {
            return true;
        }

        void a(ae aeVar) {
            aeVar.a(TapjoyConstants.TJC_TRUE);
        }
    }

    public static void main(String[] strArr) {
        System.out.println("==== ADCData Test ====");
        g gVar = new g();
        gVar.b("one", 1);
        gVar.b("pi", 3.14d);
        gVar.b(MediationMetaData.KEY_NAME, "\"Abe Pralle\"");
        gVar.a("list", new c());
        gVar.a("subtable", new g());
        gVar.b("subtable").b("five", 5);
        System.out.println("LIST:" + gVar.c("list"));
        gVar.c("list").a(3);
        System.out.println(gVar);
        System.out.println(gVar.g("one"));
        System.out.println(gVar.f("one"));
        System.out.println(gVar.g("pi"));
        System.out.println(gVar.f("pi"));
        System.out.println(gVar.e(MediationMetaData.KEY_NAME));
        System.out.println(gVar.f(MediationMetaData.KEY_NAME));
        System.out.println(gVar.g(MediationMetaData.KEY_NAME));
        System.out.println(gVar.c("list"));
        System.out.println(gVar.c("list2"));
        System.out.println(gVar.c("subtable"));
        System.out.println(gVar.b("subtable"));
        System.out.println(gVar.b("subtable2"));
        System.out.println(gVar.b("list"));
    }
}
