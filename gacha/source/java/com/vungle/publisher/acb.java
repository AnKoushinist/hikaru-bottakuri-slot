package com.vungle.publisher;

import com.tapjoy.mraid.view.MraidView;
import org.json.JSONObject;

/* compiled from: vungle */
public class acb extends abe {
    protected Integer a;
    protected Long b;
    protected b[] c;

    /* compiled from: vungle */
    public static abstract class a<P extends cp<?, P, E>, E extends cr<P>, L extends acb> extends abw<L> {
        protected a() {
        }

        protected final L[] a(P[] pArr) {
            int i = 0;
            L[] lArr = null;
            int length = pArr == null ? 0 : pArr.length;
            if (length > 0) {
                lArr = (acb[]) a(length);
                int length2 = pArr.length;
                length = 0;
                while (i < length2) {
                    int i2 = length + 1;
                    lArr[length] = a(pArr[i]);
                    i++;
                    length = i2;
                }
            }
            return lArr;
        }

        protected L a(P p) {
            if (p == null) {
                return null;
            }
            acb com_vungle_publisher_acb = (acb) b();
            com_vungle_publisher_acb.c = a.a(p.e());
            com_vungle_publisher_acb.b = p.c;
            return com_vungle_publisher_acb;
        }
    }

    /* compiled from: vungle */
    public static class b extends abe {
        protected String a;
        protected Long b;
        protected String c;

        /* compiled from: vungle */
        public static abstract class a<E extends cr<?>> extends abw<b> {
            protected final /* synthetic */ Object b() {
                return new b();
            }

            protected a() {
            }

            protected static b[] a(E[] eArr) {
                int length = eArr == null ? 0 : eArr.length;
                if (length <= 0) {
                    return null;
                }
                b[] bVarArr = new b[length];
                int length2 = eArr.length;
                int i = 0;
                int i2 = 0;
                while (i < length2) {
                    b bVar;
                    cr crVar = eArr[i];
                    int i3 = i2 + 1;
                    if (crVar != null) {
                        bVar = new b();
                        bVar.a = String.valueOf(crVar.b);
                        bVar.b = Long.valueOf(crVar.c);
                        bVar.c = crVar.d;
                    } else {
                        bVar = null;
                    }
                    bVarArr[i2] = bVar;
                    i++;
                    i2 = i3;
                }
                return bVarArr;
            }
        }

        protected b() {
        }

        public final /* synthetic */ Object b() {
            return a();
        }

        public final JSONObject a() {
            JSONObject a = super.a();
            a.putOpt(MraidView.ACTION_KEY, this.a);
            a.putOpt("timestamp_millis", this.b);
            a.putOpt("value", this.c);
            return a;
        }
    }

    public /* synthetic */ Object b() {
        return a();
    }

    public JSONObject a() {
        JSONObject a = super.a();
        a.putOpt("userActions", se.a(this.c));
        a.putOpt("startTime", this.b);
        return a;
    }
}
