package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.vungle.log.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: vungle */
public abstract class ed extends dl<Integer> {
    String a;
    String b;
    ji c;

    /* compiled from: vungle */
    static abstract class a<E extends ed, T extends aeo, R extends ade> extends com.vungle.publisher.dl.a<E, Integer> {
        abstract Map<ji, List<E>> a(String str, T t);

        a() {
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        final Map<ji, List<E>> a(R r) {
            String str = r.f;
            Logger.v(Logger.DATABASE_TAG, "deleted " + this.d.getWritableDatabase().delete("event_tracking", "ad_id = ?", new String[]{str}) + " expired event_tracking records for adId: " + str);
            Map a = a(str, r.d);
            a(a);
            return a;
        }

        private static void a(Map<ji, List<E>> map) {
            if (map != null) {
                for (List<ed> list : map.values()) {
                    if (list != null) {
                        for (ed v : list) {
                            v.v();
                        }
                    }
                }
            }
        }

        final Map<ji, List<E>> a(String str) {
            Throwable th;
            Cursor cursor = null;
            if (str == null) {
                Logger.w(Logger.DATABASE_TAG, "failed to fetch event_tracking records by ad_id: " + str);
                return null;
            }
            try {
                Logger.d(Logger.DATABASE_TAG, "fetching event_tracking records by ad_id: " + str);
                Cursor query = this.d.getReadableDatabase().query("event_tracking", null, "ad_id = ?", new String[]{str}, null, null, null);
                try {
                    Map<ji, List<E>> map;
                    int count = query.getCount();
                    Logger.v(Logger.DATABASE_TAG, count + " event_tracking for ad_id: " + str);
                    if (count > 0) {
                        Map<ji, List<E>> hashMap = new HashMap();
                        while (query.moveToNext()) {
                            dl dlVar = (ed) c_();
                            b(dlVar, query);
                            if (dlVar != null) {
                                ji jiVar = dlVar.c;
                                List list = (List) hashMap.get(jiVar);
                                if (list == null) {
                                    list = new ArrayList();
                                    hashMap.put(jiVar, list);
                                }
                                list.add(dlVar);
                            }
                        }
                        map = hashMap;
                    } else {
                        map = null;
                    }
                    if (query == null) {
                        return map;
                    }
                    query.close();
                    return map;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }

        final void a(String str, Map<ji, List<E>> map, ji jiVar, List<String> list) {
            if (list != null && list.size() > 0) {
                List list2;
                if (list == null || list.size() <= 0) {
                    list2 = null;
                } else {
                    List arrayList = new ArrayList();
                    for (String str2 : list) {
                        Object obj;
                        if (jiVar == null || str2 == null) {
                            obj = null;
                        } else {
                            obj = (ed) c_();
                            obj.b = str;
                            obj.c = jiVar;
                            obj.a = str2;
                        }
                        if (obj != null) {
                            arrayList.add(obj);
                        }
                    }
                    list2 = arrayList;
                }
                if (list2 == null || list2.isEmpty()) {
                    list2 = null;
                }
                if (list2 != null && !list2.isEmpty()) {
                    map.put(jiVar, list2);
                }
            }
        }

        protected E a(E e, Cursor cursor, boolean z) {
            e.t = cb.d(cursor, "id");
            e.b = cb.f(cursor, "ad_id");
            e.a = cb.f(cursor, String.URL);
            return e;
        }

        protected final String c() {
            return "event_tracking";
        }
    }

    protected final String c() {
        return "event_tracking";
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", (Integer) this.t);
        contentValues.put("ad_id", this.b);
        contentValues.put(TapjoyConstants.TJC_SDK_TYPE_DEFAULT, this.c.toString());
        contentValues.put(String.URL, this.a);
        return contentValues;
    }

    public final StringBuilder m() {
        StringBuilder m = super.m();
        dl.a(m, "ad_id", this.b, false);
        dl.a(m, TapjoyConstants.TJC_SDK_TYPE_DEFAULT, this.c, false);
        dl.a(m, String.URL, this.a, false);
        return m;
    }
}
