package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.bt.b;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: vungle */
public final class vl extends vr {
    @Inject
    xo a;
    @Inject
    bt b;

    @Inject
    vl() {
    }

    public final void a(cj cjVar, ji jiVar, List<String> list) {
        Map hashMap = new HashMap();
        hashMap.put("%timestamp%", String.valueOf(System.currentTimeMillis()));
        a(cjVar, jiVar, hashMap, list);
    }

    public final void a(cj cjVar, ji jiVar, Map<String, String> map, List<String> list) {
        if (list != null) {
            for (final String str : list) {
                if (str != null) {
                    final cj cjVar2 = cjVar;
                    final ji jiVar2 = jiVar;
                    final Map<String, String> map2 = map;
                    this.b.a(new Runnable(this) {
                        final /* synthetic */ vl e;

                        public final void run() {
                            try {
                                xo xoVar = this.e.a;
                                cj cjVar = cjVar2;
                                ji jiVar = jiVar2;
                                String str = str;
                                Map map = map2;
                                if (map != null) {
                                    StringBuilder stringBuilder = new StringBuilder(str);
                                    for (Entry entry : map.entrySet()) {
                                        str = (String) entry.getKey();
                                        int i = -1;
                                        while (true) {
                                            int indexOf = stringBuilder.indexOf(str, i);
                                            if (indexOf > 0) {
                                                stringBuilder.replace(indexOf, str.length() + indexOf, entry.getValue() == null ? BuildConfig.FLAVOR : (String) entry.getValue());
                                                i = indexOf;
                                            }
                                        }
                                    }
                                    str = stringBuilder.toString();
                                }
                                xoVar.a(cjVar, jiVar, str).a();
                            } catch (Throwable e) {
                                Logger.w(Logger.NETWORK_TAG, "error sending " + jiVar2 + " event", e);
                            }
                        }
                    }, b.externalNetworkRequest);
                }
            }
        }
    }
}
