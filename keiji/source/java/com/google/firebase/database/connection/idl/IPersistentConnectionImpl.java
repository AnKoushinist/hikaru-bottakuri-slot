package com.google.firebase.database.connection.idl;

import android.content.Context;
import com.google.android.gms.b.b;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.pd;
import com.google.android.gms.internal.pf;
import com.google.android.gms.internal.pg;
import com.google.android.gms.internal.pj;
import com.google.android.gms.internal.pk;
import com.google.android.gms.internal.pl;
import com.google.android.gms.internal.pm;
import com.google.android.gms.internal.pn;
import com.google.android.gms.internal.pu;
import com.google.firebase.database.connection.idl.g.a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

@DynamiteApi
public class IPersistentConnectionImpl extends a {
    private pk a;

    class AnonymousClass2 implements pn {
        final /* synthetic */ i a;

        AnonymousClass2(i iVar) {
            this.a = iVar;
        }

        public void a(String str, String str2) {
            try {
                this.a.a(str, str2);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    class AnonymousClass3 extends h.a {
        final /* synthetic */ pk.a a;

        AnonymousClass3(pk.a aVar) {
            this.a = aVar;
        }

        public void a() {
            this.a.a();
        }

        public void a(com.google.android.gms.b.a aVar) {
            this.a.a((Map) b.a(aVar));
        }

        public void a(List<String> list, com.google.android.gms.b.a aVar, boolean z, long j) {
            this.a.a(list, b.a(aVar), z, IPersistentConnectionImpl.b(j));
        }

        public void a(List<String> list, List<zzn> list2, com.google.android.gms.b.a aVar, long j) {
            List list3 = (List) b.a(aVar);
            List arrayList = new ArrayList(list2.size());
            for (int i = 0; i < list2.size(); i++) {
                arrayList.add(zzn.a((zzn) list2.get(i), list3.get(i)));
            }
            this.a.a(list, arrayList, IPersistentConnectionImpl.b(j));
        }

        public void a(boolean z) {
            this.a.a(z);
        }

        public void b() {
            this.a.b();
        }
    }

    class AnonymousClass4 implements pk.a {
        final /* synthetic */ h a;

        AnonymousClass4(h hVar) {
            this.a = hVar;
        }

        public void a() {
            try {
                this.a.a();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public void a(List<String> list, Object obj, boolean z, Long l) {
            try {
                this.a.a((List) list, b.a(obj), z, IPersistentConnectionImpl.b(l));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public void a(List<String> list, List<pm> list2, Long l) {
            List arrayList = new ArrayList(list2.size());
            List arrayList2 = new ArrayList(list2.size());
            for (pm pmVar : list2) {
                arrayList.add(zzn.a(pmVar));
                arrayList2.add(pmVar.c());
            }
            try {
                this.a.a((List) list, arrayList, b.a(arrayList2), IPersistentConnectionImpl.b(l));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public void a(Map<String, Object> map) {
            try {
                this.a.a(b.a(map));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public void a(boolean z) {
            try {
                this.a.a(z);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public void b() {
            try {
                this.a.b();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    class AnonymousClass5 extends d.a {
        final /* synthetic */ pf a;

        AnonymousClass5(pf pfVar) {
            this.a = pfVar;
        }

        public void a(boolean z, final e eVar) {
            this.a.a(z, new pf.a(this) {
                public void a(String str) {
                    try {
                        eVar.a(str);
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                }

                public void b(String str) {
                    try {
                        eVar.b(str);
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    class AnonymousClass6 implements pf {
        final /* synthetic */ d a;

        AnonymousClass6(d dVar) {
            this.a = dVar;
        }

        public void a(boolean z, final pf.a aVar) {
            try {
                this.a.a(z, new e.a(this) {
                    public void a(String str) {
                        aVar.a(str);
                    }

                    public void b(String str) {
                        aVar.b(str);
                    }
                });
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static pf a(d dVar) {
        return new AnonymousClass6(dVar);
    }

    private static pk.a a(h hVar) {
        return new AnonymousClass4(hVar);
    }

    private static pn a(i iVar) {
        return new AnonymousClass2(iVar);
    }

    private static d a(pf pfVar) {
        return new AnonymousClass5(pfVar);
    }

    private static h a(pk.a aVar) {
        return new AnonymousClass3(aVar);
    }

    private static long b(Long l) {
        if (l == null) {
            return -1;
        }
        if (l.longValue() != -1) {
            return l.longValue();
        }
        throw new IllegalArgumentException("Tag parameter clashed with NO_TAG value");
    }

    private static Long b(long j) {
        return j == -1 ? null : Long.valueOf(j);
    }

    public static g loadDynamic(Context context, zzc com_google_firebase_database_connection_idl_zzc, pf pfVar, ScheduledExecutorService scheduledExecutorService, pk.a aVar) {
        try {
            g asInterface = a.asInterface(DynamiteModule.a(context, DynamiteModule.d, "com.google.android.gms.firebase_database").a("com.google.firebase.database.connection.idl.IPersistentConnectionImpl"));
            asInterface.setup(com_google_firebase_database_connection_idl_zzc, a(pfVar), b.a(scheduledExecutorService), a(aVar));
            return asInterface;
        } catch (DynamiteModule.a e) {
            throw new RuntimeException(e);
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    public void compareAndPut(List<String> list, com.google.android.gms.b.a aVar, String str, i iVar) {
        this.a.a(list, b.a(aVar), str, a(iVar));
    }

    public void initialize() {
        this.a.a();
    }

    public void interrupt(String str) {
        this.a.d(str);
    }

    public boolean isInterrupted(String str) {
        return this.a.f(str);
    }

    public void listen(List<String> list, com.google.android.gms.b.a aVar, final f fVar, long j, i iVar) {
        Long b = b(j);
        List<String> list2 = list;
        this.a.a(list2, (Map) b.a(aVar), new pj(this) {
            public String a() {
                try {
                    return fVar.a();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }

            public boolean b() {
                try {
                    return fVar.b();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }

            public pd c() {
                try {
                    return zza.a(fVar.c());
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }, b, a(iVar));
    }

    public void merge(List<String> list, com.google.android.gms.b.a aVar, i iVar) {
        this.a.a(list, (Map) b.a(aVar), a(iVar));
    }

    public void onDisconnectCancel(List<String> list, i iVar) {
        this.a.a(list, a(iVar));
    }

    public void onDisconnectMerge(List<String> list, com.google.android.gms.b.a aVar, i iVar) {
        this.a.b(list, (Map) b.a(aVar), a(iVar));
    }

    public void onDisconnectPut(List<String> list, com.google.android.gms.b.a aVar, i iVar) {
        this.a.b(list, b.a(aVar), a(iVar));
    }

    public void purgeOutstandingWrites() {
        this.a.d();
    }

    public void put(List<String> list, com.google.android.gms.b.a aVar, i iVar) {
        this.a.a(list, b.a(aVar), a(iVar));
    }

    public void refreshAuthToken() {
        this.a.c();
    }

    public void refreshAuthToken2(String str) {
        this.a.c(str);
    }

    public void resume(String str) {
        this.a.e(str);
    }

    public void setup(zzc com_google_firebase_database_connection_idl_zzc, d dVar, com.google.android.gms.b.a aVar, h hVar) {
        ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService) b.a(aVar);
        this.a = new pl(new pg(new pu(com_google_firebase_database_connection_idl_zzc.a(), com_google_firebase_database_connection_idl_zzc.b()), a(dVar), scheduledExecutorService, com_google_firebase_database_connection_idl_zzc.e, com_google_firebase_database_connection_idl_zzc.f, com_google_firebase_database_connection_idl_zzc.g), zzf.a(com_google_firebase_database_connection_idl_zzc.b), a(hVar));
    }

    public void shutdown() {
        this.a.b();
    }

    public void unlisten(List<String> list, com.google.android.gms.b.a aVar) {
        this.a.a(list, (Map) b.a(aVar));
    }
}
