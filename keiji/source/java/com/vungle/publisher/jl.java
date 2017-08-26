package com.vungle.publisher;

import android.database.Cursor;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyConstants;
import com.vungle.log.Logger;
import java.util.HashMap;
import java.util.Map;

/* compiled from: vungle */
public abstract class jl extends ed {

    /* compiled from: vungle */
    static abstract class b<R extends aed> extends a<jl, aep, R> {
        b() {
        }

        final /* synthetic */ Map a(String str, aeo com_vungle_publisher_aeo) {
            aep com_vungle_publisher_aep = (aep) com_vungle_publisher_aeo;
            if (com_vungle_publisher_aep == null) {
                return null;
            }
            Map hashMap = new HashMap();
            a(str, hashMap, a.error, com_vungle_publisher_aep.g());
            a(str, hashMap, a.mute, com_vungle_publisher_aep.h());
            acc[] j = com_vungle_publisher_aep.j();
            if (j != null && j.length > 0) {
                for (acc com_vungle_publisher_acc : j) {
                    Float f = com_vungle_publisher_acc.a;
                    if (f != null) {
                        ji jiVar;
                        float floatValue = f.floatValue();
                        if (floatValue == 0.0f) {
                            jiVar = a.play_percentage_0;
                        } else if (((double) floatValue) == 0.25d) {
                            jiVar = a.play_percentage_25;
                        } else if (((double) floatValue) == 0.5d) {
                            jiVar = a.play_percentage_50;
                        } else if (((double) floatValue) == 0.75d) {
                            jiVar = a.play_percentage_75;
                        } else if (floatValue == TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER) {
                            jiVar = a.play_percentage_100;
                        } else {
                            Logger.w(Logger.DATABASE_TAG, "invalid play percent: " + floatValue);
                            jiVar = null;
                        }
                        if (jiVar != null) {
                            a(str, hashMap, jiVar, com_vungle_publisher_acc.b);
                        }
                    }
                }
            }
            a(str, hashMap, a.postroll_click, com_vungle_publisher_aep.c());
            a(str, hashMap, a.postroll_view, com_vungle_publisher_aep.k());
            a(str, hashMap, a.video_click, com_vungle_publisher_aep.e());
            a(str, hashMap, a.video_close, com_vungle_publisher_aep.f());
            a(str, hashMap, a.video_pause, com_vungle_publisher_aep.i());
            a(str, hashMap, a.video_resume, com_vungle_publisher_aep.l());
            a(str, hashMap, a.unmute, com_vungle_publisher_aep.m());
            return hashMap;
        }

        protected final /* bridge */ /* synthetic */ dl[] a(int i) {
            return new jl[i];
        }

        private jl a(jl jlVar, Cursor cursor, boolean z) {
            super.a(jlVar, cursor, z);
            jlVar.c = (ji) cb.a(cursor, TapjoyConstants.TJC_SDK_TYPE_DEFAULT, a.class);
            return jlVar;
        }
    }

    /* compiled from: vungle */
    public enum a implements ji {
        error,
        mute,
        play_percentage_0((String) 0.0f),
        play_percentage_25((String) 0.25f),
        play_percentage_50((String) 0.5f),
        play_percentage_75((String) 0.75f),
        play_percentage_80((String) 0.8f),
        play_percentage_100((String) 0.99f),
        postroll_click((String) (byte) 0),
        postroll_view,
        unmute,
        video_click((String) (byte) 0),
        video_close,
        video_pause,
        video_resume;
        
        public final float p;
        private final boolean q;

        private a(float f) {
            this(r2, r3, f, false);
        }

        private a(byte b) {
            this(r3, r4, -1.0f, true);
        }

        private a(float f, boolean z) {
            this.p = f;
            this.q = z;
        }

        public final boolean a() {
            return this.q;
        }
    }

    protected jl() {
    }
}
