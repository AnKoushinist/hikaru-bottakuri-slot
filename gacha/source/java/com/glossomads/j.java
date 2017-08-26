package com.glossomads;

import android.graphics.Bitmap;
import com.glossomads.Logger.SugarDebugLogger;
import com.glossomads.Model.e;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class j implements com.glossomads.i.a {
    private a<com.glossomads.Model.d> a;
    private ConcurrentHashMap<String, a> b;

    private class a {
        public String a;
        public String b;
        final /* synthetic */ j c;

        public a(j jVar, String str, String str2) {
            this.c = jVar;
            this.a = str;
            this.b = str2;
        }
    }

    private class b {
        public URL a;
        public e b;
        public boolean c = false;
        public long d = 0;
        final /* synthetic */ j e;

        public b(j jVar, URL url, e eVar) {
            this.e = jVar;
            this.a = url;
            this.b = eVar;
            this.d = eVar.i();
        }

        public boolean a() {
            if (System.currentTimeMillis() > this.d + 604800000) {
                return false;
            }
            return true;
        }
    }

    private class c implements Comparator<b> {
        final /* synthetic */ j a;

        private c(j jVar) {
            this.a = jVar;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((b) obj, (b) obj2);
        }

        public int a(b bVar, b bVar2) {
            return bVar.d < bVar2.d ? -1 : 1;
        }
    }

    private static class d {
        private static final j a = new j();
    }

    public static j a() {
        return d.a;
    }

    private j() {
        this.a = a.a("assets", s.a().d().getApplicationContext(), com.glossomads.Model.d.class);
        b();
        this.b = new ConcurrentHashMap();
    }

    public void b() {
        for (Entry entry : this.a.entrySet()) {
            com.glossomads.Model.d.a a = ((com.glossomads.Model.d) entry.getValue()).a();
            if (a == com.glossomads.Model.d.a.DOWNLOADING || a == com.glossomads.Model.d.a.WAIT) {
                this.a.a(entry.getKey());
            }
            if (a == com.glossomads.Model.d.a.READY && !new File(((com.glossomads.Model.d) entry.getValue()).b().a()).exists()) {
                this.a.a(entry.getKey());
            }
        }
    }

    public com.glossomads.Model.d a(String str) {
        return (com.glossomads.Model.d) this.a.get(str);
    }

    private boolean a(long j) {
        long j2 = 0;
        if (this.a.isEmpty()) {
            return b(j);
        }
        List m = v.m();
        List<b> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (String str : this.a.keySet()) {
            try {
                URL url = new URL(str);
                com.glossomads.Model.d a = a(str);
                if (!(m.contains(url) || a == null || !a.b().h())) {
                    arrayList.add(new b(this, url, a.b()));
                }
            } catch (Exception e) {
            }
        }
        if (b(j)) {
            Collections.sort(arrayList, new c());
            for (b bVar : arrayList) {
                if (!bVar.a()) {
                    bVar.c = true;
                }
            }
            m = a((List) arrayList);
            int size = m.size();
            if (size > 20) {
                int i = size - 20;
                for (int i2 = 0; i2 < i; i2++) {
                    ((b) m.get(i2)).c = true;
                }
            }
            m.clear();
            List<b> a2 = a((List) arrayList);
            long j3 = 0;
            for (b bVar2 : a2) {
                j3 = bVar2.b.j() + j3;
            }
            if (j3 > 524288000) {
                j3 -= 524288000;
                for (b bVar22 : a2) {
                    if (j2 >= j3) {
                        m = a2;
                        break;
                    }
                    bVar22.c = true;
                    j2 += bVar22.b.j();
                }
            }
            m = a2;
        } else {
            for (b bVar222 : arrayList) {
                bVar222.c = true;
            }
            m = arrayList2;
        }
        for (b bVar2222 : b((List) arrayList)) {
            SugarDebugLogger.d("delete expired file(filename = \"" + bVar2222.b.toString() + "\")");
            com.glossomads.Model.d dVar = (com.glossomads.Model.d) this.a.get(bVar2222.a.toString());
            dVar.a(com.glossomads.Model.d.a.DELETE);
            dVar.b().g();
            this.b.remove(bVar2222.a.toString());
            this.a.b();
        }
        m.clear();
        arrayList.clear();
        return b(j);
    }

    private boolean b(long j) {
        i a = i.a(s.a().d().getApplicationContext(), (com.glossomads.i.a) this);
        if (a == null || a.c().getFreeSpace() <= j) {
            return false;
        }
        return true;
    }

    private List<b> a(List<b> list) {
        List<b> arrayList = new ArrayList();
        for (b bVar : list) {
            if (!bVar.c) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    private List<b> b(List<b> list) {
        List<b> arrayList = new ArrayList();
        for (b bVar : list) {
            if (bVar.c) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    public boolean a(String str, URL url, long j, String str2, String str3) {
        i a = i.a(s.a().d().getApplicationContext(), (com.glossomads.i.a) this);
        if (a.c().getTotalSpace() == 0) {
            a.a(s.a().d().getApplicationContext());
            a(com.glossomads.Model.d.a.INIT);
        }
        String url2 = url.toString();
        if (a(j)) {
            c dVar = new com.glossomads.Model.d();
            dVar.a(url2);
            dVar.a(com.glossomads.Model.d.a.INIT);
            this.a.b(url2, dVar);
            com.glossomads.Model.d dVar2 = (com.glossomads.Model.d) this.a.get(url2);
            synchronized (dVar2) {
                com.glossomads.Model.d.a a2 = dVar2.a();
                if (a2 == com.glossomads.Model.d.a.INIT || a2 == com.glossomads.Model.d.a.DELETE || a2 == com.glossomads.Model.d.a.DOWNLOAD_ERROR) {
                    dVar2.a(com.glossomads.Model.d.a.WAIT);
                    this.a.b();
                    this.b.put(url2, new a(this, str2, str3));
                    a.a(url2, str);
                } else if (com.glossomads.Model.d.a.READY.equals(dVar2.a())) {
                    SugarDebugLogger.d("asset is already downloading or downloaded (url =\"" + url2 + "\")");
                    v.a(str, url2, true);
                }
            }
            return true;
        }
        com.glossomads.Logger.a.g(com.glossomads.Logger.a.a.assetDownloadFailed, url2, str);
        return false;
    }

    public boolean a(URL url) {
        com.glossomads.Model.d dVar = (com.glossomads.Model.d) this.a.get(url.toString());
        if (dVar == null) {
            return false;
        }
        return dVar.a().equals(com.glossomads.Model.d.a.READY);
    }

    public File a(String str, String str2) {
        return e.a(a(str), str, str2);
    }

    public Bitmap a(String str, boolean z) {
        return e.a(a(str), z);
    }

    public boolean b(String str) {
        com.glossomads.Model.d a = a(str);
        if (a == null) {
            return false;
        }
        File file = new File(a.b().d());
        File file2 = new File(a.b().e());
        if (file.exists() && file2.exists()) {
            return true;
        }
        return false;
    }

    public boolean c(String str) {
        com.glossomads.Model.d a = a(str);
        if (a == null) {
            return false;
        }
        return a.b().k();
    }

    public void a(URL url, String str, String str2) {
        com.glossomads.Model.d dVar = (com.glossomads.Model.d) this.a.get(url.toString());
        if (dVar != null) {
            synchronized (dVar) {
                dVar.a(url.toString());
                dVar.a(com.glossomads.Model.d.a.DOWNLOADING);
                dVar.b().b(str, str2);
                this.a.b();
                a aVar = (a) this.b.get(url.toString());
                if (aVar != null) {
                    o.a().a(aVar.a);
                }
            }
        }
    }

    public void a(com.glossomads.c.e eVar) {
        com.glossomads.Model.d dVar = (com.glossomads.Model.d) this.a.get(eVar.a());
        if (dVar != null) {
            synchronized (dVar) {
                dVar.a(com.glossomads.Model.d.a.DOWNLOAD_ERROR);
                dVar.b().g();
                this.a.b();
            }
        }
        v.a(eVar.b(), eVar.a(), false);
    }

    public void b(com.glossomads.c.e eVar) {
        com.glossomads.Model.d dVar = (com.glossomads.Model.d) this.a.get(eVar.a());
        if (dVar != null) {
            synchronized (dVar) {
                if (dVar.a().equals(com.glossomads.Model.d.a.DELETE)) {
                    return;
                }
                dVar.a(com.glossomads.Model.d.a.READY);
                this.a.b();
                dVar.b().k();
                a aVar = (a) this.b.get(eVar.a());
                if (aVar != null) {
                    o.a().a(aVar.b);
                }
                v.a(eVar.b(), eVar.a(), true);
            }
        }
    }

    public void b(URL url) {
        if (url != null) {
            com.glossomads.Model.d a = a(url.toString());
            if (a != null) {
                e b = a.b();
                if (b != null && b.h()) {
                    b.g();
                }
                this.a.a(url.toString());
                this.b.remove(url.toString());
            }
        }
    }

    private void a(com.glossomads.Model.d.a aVar) {
        for (String str : this.a.keySet()) {
            c a = a(str);
            a.a(aVar);
            this.a.a(str, a);
        }
    }
}
