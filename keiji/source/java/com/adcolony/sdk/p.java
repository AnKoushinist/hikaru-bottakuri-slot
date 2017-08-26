package com.adcolony.sdk;

import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import jp.co.geniee.gnsrewardadapter.GNSAdapterCARewardRewardVideoAd;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class p {
    private ArrayList<r> a = new ArrayList();
    private HashMap<Integer, r> b = new HashMap();
    private int c = 2;
    private HashMap<String, ArrayList<q>> d = new HashMap();
    private JSONArray e = bb.b();
    private int f = 1;

    p() {
    }

    void a(String str, q qVar) {
        ArrayList arrayList = (ArrayList) this.d.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.d.put(str, arrayList);
        }
        arrayList.add(qVar);
    }

    void b(String str, q qVar) {
        synchronized (this.d) {
            ArrayList arrayList = (ArrayList) this.d.get(str);
            if (arrayList != null) {
                arrayList.remove(qVar);
            }
        }
    }

    void a() {
        aq a = n.a();
        if (!a.c() && !a.d() && n.d()) {
            final ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            newSingleThreadExecutor.submit(new Runnable(this) {
                final /* synthetic */ p b;

                public void run() {
                    JSONObject e = n.a().a().e();
                    bb.a(e, "os_name", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
                    this.b.a(new ADCVMModule(n.c(), 1, n.a().j().g() + "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5", e, newSingleThreadExecutor));
                }
            });
        }
    }

    r a(r rVar) {
        synchronized (this.a) {
            this.a.add(rVar);
            this.b.put(Integer.valueOf(rVar.a()), rVar);
        }
        return rVar;
    }

    r a(int i) {
        r rVar;
        synchronized (this.a) {
            rVar = (r) this.b.get(Integer.valueOf(i));
            if (rVar == null) {
                rVar = null;
            } else {
                this.a.remove(rVar);
                this.b.remove(Integer.valueOf(i));
                rVar.b();
            }
        }
        return rVar;
    }

    synchronized void b() {
        JSONArray jSONArray;
        synchronized (this.a) {
            int size;
            for (size = this.a.size() - 1; size >= 0; size--) {
                ((r) this.a.get(size)).c();
            }
        }
        if (this.e.length() > 0) {
            JSONArray jSONArray2 = this.e;
            this.e = bb.b();
            jSONArray = jSONArray2;
        } else {
            jSONArray = null;
        }
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (size = 0; size < length; size++) {
                try {
                    final JSONObject jSONObject = jSONArray.getJSONObject(size);
                    final String string = jSONObject.getString("m_type");
                    if (jSONObject.getInt("m_origin") < 2) {
                        a(string, jSONObject);
                    } else if (n.d()) {
                        ab.a(new Runnable(this) {
                            final /* synthetic */ p c;

                            public void run() {
                                this.c.a(string, jSONObject);
                            }
                        });
                    }
                } catch (JSONException e) {
                    bd.h.a("JSON error from message dispatcher's update_modules(): ").b(e.toString());
                }
            }
        }
    }

    void a(String str, JSONObject jSONObject) {
        synchronized (this.d) {
            ArrayList arrayList = (ArrayList) this.d.get(str);
            if (arrayList != null) {
                o oVar = new o(jSONObject);
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    try {
                        ((q) arrayList.get(i)).a(oVar);
                        i++;
                    } catch (Object e) {
                        bd.h.b(e);
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    void a(JSONObject jSONObject) {
        try {
            if (!jSONObject.has(GNSAdapterCARewardRewardVideoAd.MEDIA_ID_COLUMN_NAME)) {
                String str = GNSAdapterCARewardRewardVideoAd.MEDIA_ID_COLUMN_NAME;
                int i = this.f;
                this.f = i + 1;
                jSONObject.put(str, i);
            }
            if (!jSONObject.has("m_origin")) {
                jSONObject.put("m_origin", 0);
            }
            int i2 = jSONObject.getInt("m_target");
            if (i2 == 0) {
                synchronized (this) {
                    this.e.put(jSONObject);
                }
                return;
            }
            r rVar = (r) this.b.get(Integer.valueOf(i2));
            if (rVar != null) {
                rVar.a(jSONObject);
            }
        } catch (JSONException e) {
            bd.h.a("JSON error in ADCMessageDispatcher's send_message(): ").b(e.toString());
        }
    }

    ArrayList<r> c() {
        return this.a;
    }

    int d() {
        int i = this.c;
        this.c = i + 1;
        return i;
    }
}
