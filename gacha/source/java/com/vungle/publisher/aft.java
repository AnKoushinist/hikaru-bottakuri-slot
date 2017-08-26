package com.vungle.publisher;

import com.vungle.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

/* compiled from: vungle */
public final class aft {

    @Singleton
    /* compiled from: vungle */
    public static class a implements aii<Object, Boolean> {
        public final /* synthetic */ Object a(Object obj) {
            return Boolean.valueOf(obj != null);
        }

        @Inject
        a() {
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class b implements aii<adf, ahp<ade>> {
        @Inject
        com.vungle.publisher.adr.a a;
        @Inject
        com.vungle.publisher.adu.a b;
        @Inject
        com.vungle.publisher.aef.a c;

        public final /* synthetic */ Object a(Object obj) {
            Object obj2;
            adf com_vungle_publisher_adf = (adf) obj;
            boolean z = com_vungle_publisher_adf.k;
            j jVar = com_vungle_publisher_adf.e;
            if (z) {
                if ((com_vungle_publisher_adf.c.longValue() * 1000 < System.currentTimeMillis() ? 1 : null) != null) {
                    Logger.i(Logger.PREPARE_TAG, "received expired ad from server, tossing it and getting a new one");
                    throw new RuntimeException("ad is expired");
                } else if (jVar == null) {
                    Logger.w(Logger.PREPARE_TAG, "received null adType from server, tossing it and getting a new one");
                    throw new RuntimeException("adType is null");
                } else {
                    Logger.v(Logger.PREPARE_TAG, "received a valid ad, continue processing ad with type: " + jVar);
                    final JSONObject jSONObject = com_vungle_publisher_adf.l;
                    obj2 = (ade) new o<ade>(this) {
                        final /* synthetic */ b b;

                        protected final /* synthetic */ Object a() {
                            return e();
                        }

                        protected final /* synthetic */ Object b() {
                            Logger.i(Logger.PREPARE_TAG, "received invalid ad from server, tossing it and getting a new one");
                            throw new RuntimeException("received invalid ad from server, tossing it and getting a new one");
                        }

                        protected final /* synthetic */ Object c() {
                            return f();
                        }

                        protected final /* synthetic */ Object d() {
                            return g();
                        }

                        private ade e() {
                            try {
                                return this.b.a.b(jSONObject);
                            } catch (Throwable e) {
                                throw ahx.a(e);
                            }
                        }

                        private ade f() {
                            try {
                                return this.b.c.b(jSONObject);
                            } catch (Throwable e) {
                                throw ahx.a(e);
                            }
                        }

                        private ade g() {
                            try {
                                return this.b.b.b(jSONObject);
                            } catch (Throwable e) {
                                throw ahx.a(e);
                            }
                        }
                    }.a(jVar);
                }
            } else {
                obj2 = null;
            }
            return akc.a(obj2);
        }

        @Inject
        b() {
        }
    }
}
