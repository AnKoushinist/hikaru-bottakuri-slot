package com.vungle.publisher;

import com.vungle.publisher.ahp.a;
import com.vungle.publisher.ahp.b;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: vungle */
public final class aiw<T> implements a<T> {
    static final aii<ahp<? extends aho<?>>, ahp<?>> d = new aii<ahp<? extends aho<?>>, ahp<?>>() {
        public final /* synthetic */ Object a(Object obj) {
            return ((ahp) obj).b(new aii<aho<?>, aho<?>>(this) {
                final /* synthetic */ AnonymousClass1 a;

                {
                    this.a = r1;
                }

                public final /* bridge */ /* synthetic */ Object a(Object obj) {
                    return aho.a();
                }
            });
        }
    };
    final ahp<T> a;
    final boolean b = true;
    final boolean c = false;
    private final aii<? super ahp<? extends aho<?>>, ? extends ahp<?>> e;
    private final ahs f;

    public final /* synthetic */ void a(Object obj) {
        final ahu com_vungle_publisher_ahu = (ahu) obj;
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        final AtomicLong atomicLong = new AtomicLong();
        final ahv a = this.f.a();
        com_vungle_publisher_ahu.a(a);
        final ahv com_vungle_publisher_amd = new amd();
        com_vungle_publisher_ahu.a(com_vungle_publisher_amd);
        alz b = alx.b();
        final ahp com_vungle_publisher_aly = b.getClass() == aly.class ? (aly) b : new aly(b);
        ahp.a(new com.vungle.publisher.alm.AnonymousClass1(alj.a()), com_vungle_publisher_aly);
        final ajf com_vungle_publisher_ajf = new ajf();
        AnonymousClass2 anonymousClass2 = new aie(this) {
            final /* synthetic */ aiw f;

            public final void d() {
                if (!com_vungle_publisher_ahu.a.b) {
                    ahv com_vungle_publisher_ahv;
                    ahu anonymousClass1 = new ahu<T>(this) {
                        boolean b;
                        final /* synthetic */ AnonymousClass2 c;

                        {
                            this.c = r1;
                        }

                        public final void a() {
                            if (!this.b) {
                                this.b = true;
                                this.a.b();
                                com_vungle_publisher_aly.a((Object) aho.b());
                            }
                        }

                        public final void a(Throwable th) {
                            if (!this.b) {
                                this.b = true;
                                this.a.b();
                                com_vungle_publisher_aly.a((Object) aho.a(th));
                            }
                        }

                        /* JADX WARNING: inconsistent code. */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public final void a(T r13) {
                            /*
                            r12 = this;
                            r10 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
                            r8 = 0;
                            r6 = 1;
                            r0 = r12.b;
                            if (r0 != 0) goto L_0x0047;
                        L_0x000d:
                            r0 = r12.c;
                            r0 = r2;
                            r0.a(r13);
                        L_0x0014:
                            r0 = r12.c;
                            r0 = r5;
                            r0 = r0.get();
                            r2 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1));
                            if (r2 == 0) goto L_0x002c;
                        L_0x0020:
                            r2 = r12.c;
                            r2 = r5;
                            r4 = r0 - r6;
                            r0 = r2.compareAndSet(r0, r4);
                            if (r0 == 0) goto L_0x0014;
                        L_0x002c:
                            r0 = r12.c;
                            r1 = r4;
                            r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
                            if (r0 > 0) goto L_0x003c;
                        L_0x0034:
                            r0 = new java.lang.IllegalArgumentException;
                            r1 = "n > 0 required";
                            r0.<init>(r1);
                            throw r0;
                        L_0x003c:
                            monitor-enter(r1);
                            r0 = r1.c;	 Catch:{ all -> 0x0066 }
                            if (r0 == 0) goto L_0x0048;
                        L_0x0041:
                            r2 = r1.e;	 Catch:{ all -> 0x0066 }
                            r2 = r2 + r6;
                            r1.e = r2;	 Catch:{ all -> 0x0066 }
                            monitor-exit(r1);	 Catch:{ all -> 0x0066 }
                        L_0x0047:
                            return;
                        L_0x0048:
                            r0 = 1;
                            r1.c = r0;	 Catch:{ all -> 0x0066 }
                            monitor-exit(r1);	 Catch:{ all -> 0x0066 }
                            r2 = r1.a;	 Catch:{ all -> 0x005f }
                            r0 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1));
                            if (r0 == 0) goto L_0x006b;
                        L_0x0052:
                            r2 = r2 - r6;
                            r0 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
                            if (r0 >= 0) goto L_0x0069;
                        L_0x0057:
                            r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x005f }
                            r2 = "more items arrived than were requested";
                            r0.<init>(r2);	 Catch:{ all -> 0x005f }
                            throw r0;	 Catch:{ all -> 0x005f }
                        L_0x005f:
                            r0 = move-exception;
                            monitor-enter(r1);
                            r2 = 0;
                            r1.c = r2;	 Catch:{ all -> 0x006f }
                            monitor-exit(r1);	 Catch:{ all -> 0x006f }
                            throw r0;
                        L_0x0066:
                            r0 = move-exception;
                            monitor-exit(r1);	 Catch:{ all -> 0x0066 }
                            throw r0;
                        L_0x0069:
                            r1.a = r2;	 Catch:{ all -> 0x005f }
                        L_0x006b:
                            r1.a();	 Catch:{ all -> 0x005f }
                            goto L_0x0047;
                        L_0x006f:
                            r0 = move-exception;
                            monitor-exit(r1);	 Catch:{ all -> 0x006f }
                            throw r0;
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.aiw.2.1.a(java.lang.Object):void");
                        }

                        /* JADX WARNING: inconsistent code. */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public final void a(com.vungle.publisher.ahr r5) {
                            /*
                            r4 = this;
                            r0 = r4.c;
                            r1 = r4;
                            monitor-enter(r1);
                            r0 = r1.c;	 Catch:{ all -> 0x0029 }
                            if (r0 == 0) goto L_0x0011;
                        L_0x0009:
                            if (r5 != 0) goto L_0x000d;
                        L_0x000b:
                            r5 = com.vungle.publisher.ajf.g;	 Catch:{ all -> 0x0029 }
                        L_0x000d:
                            r1.f = r5;	 Catch:{ all -> 0x0029 }
                            monitor-exit(r1);	 Catch:{ all -> 0x0029 }
                        L_0x0010:
                            return;
                        L_0x0011:
                            r0 = 1;
                            r1.c = r0;	 Catch:{ all -> 0x0029 }
                            monitor-exit(r1);	 Catch:{ all -> 0x0029 }
                            r1.b = r5;	 Catch:{ all -> 0x0022 }
                            if (r5 == 0) goto L_0x001e;
                        L_0x0019:
                            r2 = r1.a;	 Catch:{ all -> 0x0022 }
                            r5.a(r2);	 Catch:{ all -> 0x0022 }
                        L_0x001e:
                            r1.a();	 Catch:{ all -> 0x0022 }
                            goto L_0x0010;
                        L_0x0022:
                            r0 = move-exception;
                            monitor-enter(r1);
                            r2 = 0;
                            r1.c = r2;	 Catch:{ all -> 0x002c }
                            monitor-exit(r1);	 Catch:{ all -> 0x002c }
                            throw r0;
                        L_0x0029:
                            r0 = move-exception;
                            monitor-exit(r1);	 Catch:{ all -> 0x0029 }
                            throw r0;
                        L_0x002c:
                            r0 = move-exception;
                            monitor-exit(r1);	 Catch:{ all -> 0x002c }
                            throw r0;
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.aiw.2.1.a(com.vungle.publisher.ahr):void");
                        }
                    };
                    ajt com_vungle_publisher_ajt = com_vungle_publisher_amd.a;
                    do {
                        com_vungle_publisher_ahv = (ahv) com_vungle_publisher_ajt.get();
                        if (com_vungle_publisher_ahv == aju.a) {
                            anonymousClass1.b();
                            break;
                        }
                    } while (!com_vungle_publisher_ajt.compareAndSet(com_vungle_publisher_ahv, anonymousClass1));
                    if (com_vungle_publisher_ahv != null) {
                        com_vungle_publisher_ahv.b();
                    }
                    this.f.a.a(anonymousClass1);
                }
            }
        };
        final ahp com_vungle_publisher_ahp = (ahp) this.e.a(com_vungle_publisher_aly.a(new b<aho<?>, aho<?>>(this) {
            final /* synthetic */ aiw a;

            {
                this.a = r1;
            }

            public final /* synthetic */ Object a(Object obj) {
                final ahu com_vungle_publisher_ahu = (ahu) obj;
                return new ahu<aho<?>>(this, com_vungle_publisher_ahu) {
                    final /* synthetic */ AnonymousClass3 c;

                    public final /* synthetic */ void a(Object obj) {
                        aho com_vungle_publisher_aho = (aho) obj;
                        if ((com_vungle_publisher_aho.a == aho.a.OnCompleted ? 1 : null) != null && this.c.a.b) {
                            com_vungle_publisher_ahu.a();
                        } else if (com_vungle_publisher_aho.c() && this.c.a.c) {
                            com_vungle_publisher_ahu.a(com_vungle_publisher_aho.b);
                        } else {
                            com_vungle_publisher_ahu.a((Object) com_vungle_publisher_aho);
                        }
                    }

                    public final void a() {
                        com_vungle_publisher_ahu.a();
                    }

                    public final void a(Throwable th) {
                        com_vungle_publisher_ahu.a(th);
                    }

                    public final void a(ahr com_vungle_publisher_ahr) {
                        com_vungle_publisher_ahr.a(Long.MAX_VALUE);
                    }
                };
            }
        }));
        final ahu com_vungle_publisher_ahu2 = com_vungle_publisher_ahu;
        final AtomicLong atomicLong2 = atomicLong;
        final AnonymousClass2 anonymousClass22 = anonymousClass2;
        a.a(new aie(this) {
            final /* synthetic */ aiw g;

            public final void d() {
                com_vungle_publisher_ahp.a(new ahu<Object>(this, com_vungle_publisher_ahu2) {
                    final /* synthetic */ AnonymousClass4 b;

                    public final void a() {
                        com_vungle_publisher_ahu2.a();
                    }

                    public final void a(Throwable th) {
                        com_vungle_publisher_ahu2.a(th);
                    }

                    public final void a(Object obj) {
                        if (!com_vungle_publisher_ahu2.a.b) {
                            if (atomicLong2.get() > 0) {
                                a.a(anonymousClass22);
                            } else {
                                atomicBoolean.compareAndSet(false, true);
                            }
                        }
                    }

                    public final void a(ahr com_vungle_publisher_ahr) {
                        com_vungle_publisher_ahr.a(Long.MAX_VALUE);
                    }
                });
            }
        });
        final AtomicLong atomicLong3 = atomicLong;
        final ajf com_vungle_publisher_ajf2 = com_vungle_publisher_ajf;
        final AtomicBoolean atomicBoolean2 = atomicBoolean;
        anonymousClass22 = anonymousClass2;
        com_vungle_publisher_ahu.a(new ahr(this) {
            final /* synthetic */ aiw f;

            public final void a(long j) {
                if (j > 0) {
                    aim.a(atomicLong3, j);
                    com_vungle_publisher_ajf2.a(j);
                    if (atomicBoolean2.compareAndSet(true, false)) {
                        a.a(anonymousClass22);
                    }
                }
            }
        });
    }

    public static <T> ahp<T> a(ahp<T> com_vungle_publisher_ahp_T, aii<? super ahp<? extends aho<?>>, ? extends ahp<?>> com_vungle_publisher_aii__super_com_vungle_publisher_ahp__extends_com_vungle_publisher_aho____extends_com_vungle_publisher_ahp_) {
        return ahp.a(new aiw(com_vungle_publisher_ahp_T, com_vungle_publisher_aii__super_com_vungle_publisher_ahp__extends_com_vungle_publisher_aho____extends_com_vungle_publisher_ahp_, alw.a()));
    }

    private aiw(ahp<T> com_vungle_publisher_ahp_T, aii<? super ahp<? extends aho<?>>, ? extends ahp<?>> com_vungle_publisher_aii__super_com_vungle_publisher_ahp__extends_com_vungle_publisher_aho____extends_com_vungle_publisher_ahp_, ahs com_vungle_publisher_ahs) {
        this.a = com_vungle_publisher_ahp_T;
        this.e = com_vungle_publisher_aii__super_com_vungle_publisher_ahp__extends_com_vungle_publisher_aho____extends_com_vungle_publisher_ahp_;
        this.f = com_vungle_publisher_ahs;
    }
}
