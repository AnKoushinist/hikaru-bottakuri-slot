package com.adcolony.sdk;

import android.util.Log;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.unity3d.ads.metadata.MediationMetaData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class u {
    bc a;
    ba b;
    ScheduledExecutorService c;
    List<be> d;
    HashMap<String, Object> e;

    u(bc bcVar, ba baVar, ScheduledExecutorService scheduledExecutorService, ArrayList<be> arrayList, HashMap<String, Object> hashMap) {
        this.a = bcVar;
        this.b = baVar;
        this.c = scheduledExecutorService;
        this.d = arrayList;
        this.e = hashMap;
    }

    void a(String str) {
        if (bf.d != null) {
            bf.d.e.put("controllerVersion", str);
        }
    }

    void b(String str) {
        if (bf.d != null) {
            bf.d.e.put("sessionId", str);
        }
    }

    void a(long j, TimeUnit timeUnit) {
        this.c.scheduleAtFixedRate(new Runnable(this) {
            final /* synthetic */ u a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    this.a.b();
                } catch (Throwable e) {
                    Log.e("ADCLogPOC", "RuntimeException thrown from {}#report. Exception was suppressed.", e);
                }
            }
        }, j, j, timeUnit);
    }

    void a() {
        this.c.shutdown();
        try {
            if (!this.c.awaitTermination(1, TimeUnit.SECONDS)) {
                this.c.shutdownNow();
                if (!this.c.awaitTermination(1, TimeUnit.SECONDS)) {
                    System.err.println(getClass().getSimpleName() + ": ScheduledExecutorService did not terminate");
                }
            }
        } catch (InterruptedException e) {
            this.c.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    void b() {
        synchronized (this) {
            try {
                if (this.d.size() > 0) {
                    this.a.a(a(this.b, this.d));
                    this.d.clear();
                }
            } catch (IOException e) {
            } catch (JSONException e2) {
            }
        }
    }

    void c(String str) {
        a(new be(new Date(), 3, this.b.c(), str));
    }

    void d(String str) {
        a(new be(new Date(), 4, this.b.c(), str));
    }

    void e(String str) {
        a(new be(new Date(), 5, this.b.c(), str));
    }

    void f(String str) {
        a(new be(new Date(), 6, this.b.c(), str));
    }

    synchronized void a(be beVar) {
        this.d.add(beVar);
    }

    String a(ba baVar, List<be> list) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("index", baVar.a());
        jSONObject.put("environment", baVar.c());
        jSONObject.put(MediationMetaData.KEY_VERSION, baVar.b());
        JSONArray jSONArray = new JSONArray();
        for (be b : list) {
            jSONArray.put(b(b));
        }
        jSONObject.put("logs", jSONArray);
        return jSONObject.toString();
    }

    JSONObject b(be beVar) {
        JSONObject jSONObject = new JSONObject(this.e);
        jSONObject.put("index", this.b.a());
        jSONObject.put("environment", this.b.c());
        jSONObject.put(MediationMetaData.KEY_VERSION, this.b.b());
        jSONObject.put("level", beVar.a());
        jSONObject.put(TapjoyConstants.TJC_TIMESTAMP, beVar.d().toString());
        jSONObject.put("level", beVar.a());
        jSONObject.put("tag", beVar.c());
        jSONObject.put(String.MESSAGE, beVar.b());
        return jSONObject;
    }
}
