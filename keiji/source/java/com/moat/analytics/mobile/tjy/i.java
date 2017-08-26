package com.moat.analytics.mobile.tjy;

import org.json.JSONObject;

abstract class i extends f {
    protected k j = k.UNINITIALIZED;
    protected int k = Integer.MIN_VALUE;
    protected double l = Double.NaN;
    protected int m = Integer.MIN_VALUE;
    protected int n = Integer.MIN_VALUE;
    private int o = 0;

    public i(String str, a aVar, ap apVar) {
        super(str, aVar, apVar);
    }

    protected JSONObject a(MoatAdEvent moatAdEvent) {
        Integer f;
        if (moatAdEvent.adPlayhead.equals(MoatAdEvent.TIME_UNAVAILABLE)) {
            try {
                f = f();
            } catch (Exception e) {
                f = Integer.valueOf(this.k);
            }
            moatAdEvent.adPlayhead = f;
        } else {
            f = moatAdEvent.adPlayhead;
        }
        if (moatAdEvent.adPlayhead.intValue() < 0) {
            f = Integer.valueOf(this.k);
            moatAdEvent.adPlayhead = f;
        }
        if (moatAdEvent.eventType == MoatAdEventType.AD_EVT_COMPLETE) {
            if (f.intValue() == Integer.MIN_VALUE || this.n == Integer.MIN_VALUE || !a(f, Integer.valueOf(this.n))) {
                this.j = k.STOPPED;
                moatAdEvent.eventType = MoatAdEventType.AD_EVT_STOPPED;
            } else {
                this.j = k.COMPLETED;
            }
        }
        return super.a(moatAdEvent);
    }

    protected void b() {
        super.b();
        this.d.postDelayed(new j(this), 200);
    }

    protected abstract Integer f();

    protected abstract boolean g();

    protected abstract Integer h();

    protected boolean i() {
        int intValue;
        if (this.f.get() == null || e()) {
            return false;
        }
        try {
            int intValue2 = f().intValue();
            if (this.k >= 0 && intValue2 < 0) {
                return false;
            }
            this.k = intValue2;
            if (intValue2 == 0) {
                return true;
            }
            intValue = h().intValue();
            boolean g = g();
            double d = ((double) intValue) / 4.0d;
            double d2 = d();
            MoatAdEventType moatAdEventType = null;
            if (intValue2 > this.m) {
                this.m = intValue2;
            }
            if (this.n == Integer.MIN_VALUE) {
                this.n = intValue;
            }
            if (g) {
                if (this.j == k.UNINITIALIZED) {
                    moatAdEventType = MoatAdEventType.AD_EVT_START;
                    this.j = k.PLAYING;
                } else if (this.j == k.PAUSED) {
                    moatAdEventType = MoatAdEventType.AD_EVT_PLAYING;
                    this.j = k.PLAYING;
                } else {
                    MoatAdEventType moatAdEventType2;
                    intValue = ((int) Math.floor(((double) intValue2) / d)) - 1;
                    if (intValue >= 0 && intValue < 3) {
                        moatAdEventType2 = b[intValue];
                        if (!this.c.containsKey(moatAdEventType2)) {
                            this.c.put(moatAdEventType2, Integer.valueOf(1));
                            moatAdEventType = moatAdEventType2;
                        }
                    }
                    moatAdEventType2 = null;
                    moatAdEventType = moatAdEventType2;
                }
            } else if (this.j != k.PAUSED) {
                moatAdEventType = MoatAdEventType.AD_EVT_PAUSED;
                this.j = k.PAUSED;
            }
            Object obj = moatAdEventType != null ? 1 : null;
            if (obj == null && !Double.isNaN(this.l) && Math.abs(this.l - d2) > 0.05d) {
                moatAdEventType = MoatAdEventType.AD_EVT_VOLUME_CHANGE;
                obj = 1;
            }
            if (obj != null) {
                dispatchEvent(new MoatAdEvent(moatAdEventType, Integer.valueOf(intValue2), Double.valueOf(d2)));
            }
            this.l = d2;
            this.o = 0;
            return true;
        } catch (Exception e) {
            intValue = this.o;
            this.o = intValue + 1;
            return intValue < 5;
        }
    }
}
