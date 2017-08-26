package com.applovin.impl.sdk;

class v implements co {
    final /* synthetic */ bx a;
    final /* synthetic */ EventServiceImpl b;

    v(EventServiceImpl eventServiceImpl, bx bxVar) {
        this.b = eventServiceImpl;
        this.a = bxVar;
    }

    public void a(s sVar) {
        try {
            this.b.a.u().a(this.b.a(this.a, sVar).toString(), this.a.b());
        } catch (Throwable e) {
            this.b.a.h().b("EventServiceImpl", "Unable to track event due to failure to convert event parameters into JSONObject for event: " + this.a, e);
        }
    }
}
