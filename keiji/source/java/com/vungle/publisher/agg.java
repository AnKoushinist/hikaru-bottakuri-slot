package com.vungle.publisher;

import com.vungle.log.Logger;
import java.util.Arrays;
import java.util.HashSet;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class agg extends afx<jk<?, ?, ?>> {
    private static final com.vungle.publisher.jl.a[] h = new com.vungle.publisher.jl.a[]{com.vungle.publisher.jl.a.play_percentage_0, com.vungle.publisher.jl.a.play_percentage_25, com.vungle.publisher.jl.a.play_percentage_50, com.vungle.publisher.jl.a.play_percentage_75, com.vungle.publisher.jl.a.play_percentage_80, com.vungle.publisher.jl.a.play_percentage_100};
    private int i;
    private final HashSet<com.vungle.publisher.jl.a> j = new HashSet();

    @Singleton
    /* compiled from: vungle */
    public static class a {
        @Inject
        public agg a;

        @Inject
        a() {
        }
    }

    @Inject
    agg() {
    }

    protected final void a() {
        this.i = 0;
        this.j.clear();
    }

    public final void onEvent(ba baVar) {
        float f = baVar.d;
        if ((baVar.b > baVar.a ? 1 : null) != null) {
            a(com.vungle.publisher.jo.a.volumeup, Float.valueOf(f));
        } else {
            a(com.vungle.publisher.jo.a.volumedown, Float.valueOf(f));
        }
    }

    public final void onEvent(t tVar) {
        a(com.vungle.publisher.jo.a.back);
    }

    public final void onEvent(az azVar) {
        a(com.vungle.publisher.jo.a.volume, Float.valueOf(azVar.a));
    }

    public final void onEvent(ay ayVar) {
        if (ayVar.a) {
            a(com.vungle.publisher.jo.a.muted);
            a(com.vungle.publisher.jl.a.mute);
            return;
        }
        a(com.vungle.publisher.jo.a.unmuted);
        a(com.vungle.publisher.jl.a.unmute);
    }

    public final void onEvent(aj ajVar) {
        a(com.vungle.publisher.jl.a.postroll_view);
    }

    public final void onEvent(av avVar) {
        a(com.vungle.publisher.jl.a.video_close);
        a(com.vungle.publisher.jo.a.close);
    }

    public final void onEvent(ax axVar) {
        a(com.vungle.publisher.jo.a.videoreset);
    }

    public final void onEvent(x<jk<?, ?, ?>> xVar) {
        jk jkVar = (jk) xVar.a();
        this.g.a(jkVar, xVar.a, null, Arrays.asList(new String[]{jkVar.t()}));
    }

    public final void onEvent(ac acVar) {
        try {
            this.c.a(Integer.valueOf(acVar.a));
        } catch (Throwable e) {
            this.e.a(Logger.REPORT_TAG, "error updating video duration millis", e);
        }
    }

    public final void onEvent(ar arVar) {
        try {
            this.b.c = Long.valueOf(arVar.e);
            this.b.b_();
        } catch (Throwable e) {
            this.e.a(Logger.REPORT_TAG, "error updating play start millis", e);
        }
    }

    public final void onEvent(al alVar) {
        Object obj = null;
        try {
            int i = alVar.a;
            Object obj2 = this.i < h.length ? 1 : null;
            boolean z = alVar instanceof ad;
            if (obj2 != null || z) {
                Integer p = this.c.p();
                if (p == null) {
                    Logger.d(Logger.REPORT_TAG, "null video duration millis for " + this.c.z());
                } else if (p.intValue() == 0) {
                    Logger.w(Logger.REPORT_TAG, "video duration millis 0 for " + this.c.z());
                } else {
                    if (obj2 != null) {
                        float intValue = ((float) i) / ((float) p.intValue());
                        com.vungle.publisher.jl.a aVar = h[this.i];
                        if (intValue >= aVar.p) {
                            obj = 1;
                        }
                        if (obj != null) {
                            if (aVar == com.vungle.publisher.jl.a.play_percentage_80) {
                                this.eventBus.a(new as());
                            }
                            this.i++;
                            a(aVar);
                        }
                    }
                    if (obj != null || z) {
                        try {
                            cp cpVar = this.b;
                            Integer valueOf = Integer.valueOf(alVar.a);
                            if (cpVar.b == null || (valueOf != null && valueOf.intValue() > cpVar.b.intValue())) {
                                Logger.d(Logger.AD_TAG, "setting watched millis " + valueOf);
                                cpVar.b = valueOf;
                            } else {
                                Logger.w(Logger.AD_TAG, "ignoring decreased watched millis " + valueOf);
                            }
                            this.b.b_();
                            this.c.b(Long.valueOf(alVar.e));
                        } catch (Throwable e) {
                            this.e.a(Logger.REPORT_TAG, "error updating video view progress", e);
                        }
                    }
                }
            }
        } catch (Throwable e2) {
            this.e.a(Logger.REPORT_TAG, "error handling video view progress", e2);
        }
    }

    public final void onEvent(w wVar) {
        com.vungle.publisher.jl.a aVar = wVar.a;
        if (aVar == com.vungle.publisher.jl.a.video_click) {
            a(com.vungle.publisher.jo.a.cta);
        } else if (aVar == com.vungle.publisher.jl.a.postroll_click) {
            a(com.vungle.publisher.jl.a.postroll_click);
        }
        a(com.vungle.publisher.jo.a.download);
    }

    public final void onEvent(ai aiVar) {
        try {
            a(com.vungle.publisher.jo.a.replay);
            this.b = this.c.s();
        } catch (Throwable e) {
            this.e.a(Logger.REPORT_TAG, "error reporting replay", e);
        }
    }

    public final void onEvent(bo<jk<?, ?, ?>> boVar) {
        try {
            Logger.d(Logger.REPORT_TAG, "received play ad end");
            a((y) boVar);
            a(boVar.b());
        } catch (Throwable e) {
            this.e.a(Logger.REPORT_TAG, "error processing ad end", e);
        }
    }

    private void a(com.vungle.publisher.jl.a aVar) {
        if (this.a == null) {
            Logger.w(Logger.REPORT_TAG, "null ad in AdReportingHandler - cannot track event " + aVar);
        } else if (!this.j.contains(aVar)) {
            Logger.v(Logger.REPORT_TAG, "tpat event " + aVar.name());
            this.g.a(this.a, aVar, ((jk) this.a).a((ji) aVar));
            this.j.add(aVar);
        }
    }
}
