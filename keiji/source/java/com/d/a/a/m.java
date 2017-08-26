package com.d.a.a;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* compiled from: RequestParams */
public class m implements Serializable {
    protected final ConcurrentHashMap<String, String> a;
    protected final ConcurrentHashMap<String, Object> b;
    protected final ConcurrentHashMap<String, Object> c;
    protected final ConcurrentHashMap<String, List<Object>> d;
    protected final ConcurrentHashMap<String, Object> e;
    protected String f;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : this.a.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append((String) entry.getValue());
        }
        for (Entry entry2 : this.b.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry2.getKey());
            stringBuilder.append("=");
            stringBuilder.append("STREAM");
        }
        for (Entry entry22 : this.c.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry22.getKey());
            stringBuilder.append("=");
            stringBuilder.append("FILE");
        }
        for (Entry entry222 : this.d.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry222.getKey());
            stringBuilder.append("=");
            stringBuilder.append("FILES(SIZE=").append(((List) entry222.getValue()).size()).append(")");
        }
        for (BasicNameValuePair basicNameValuePair : a(null, this.e)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(basicNameValuePair.getName());
            stringBuilder.append("=");
            stringBuilder.append(basicNameValuePair.getValue());
        }
        return stringBuilder.toString();
    }

    protected List<BasicNameValuePair> a() {
        List<BasicNameValuePair> linkedList = new LinkedList();
        for (Entry entry : this.a.entrySet()) {
            linkedList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        linkedList.addAll(a(null, this.e));
        return linkedList;
    }

    private List<BasicNameValuePair> a(String str, Object obj) {
        List<BasicNameValuePair> linkedList = new LinkedList();
        if (obj instanceof Map) {
            Map map = (Map) obj;
            List arrayList = new ArrayList(map.keySet());
            if (arrayList.size() > 0 && (arrayList.get(0) instanceof Comparable)) {
                Collections.sort(arrayList);
            }
            for (Object next : arrayList) {
                if (next instanceof String) {
                    Object obj2 = map.get(next);
                    if (obj2 != null) {
                        String str2;
                        if (str == null) {
                            str2 = (String) next;
                        } else {
                            str2 = String.format(Locale.US, "%s[%s]", new Object[]{str, next});
                        }
                        linkedList.addAll(a(str2, obj2));
                    }
                }
            }
        } else if (obj instanceof List) {
            List list = (List) obj;
            r3 = list.size();
            for (r0 = 0; r0 < r3; r0++) {
                linkedList.addAll(a(String.format(Locale.US, "%s[%d]", new Object[]{str, Integer.valueOf(r0)}), list.get(r0)));
            }
        } else if (obj instanceof Object[]) {
            for (Object a : (Object[]) obj) {
                linkedList.addAll(a(String.format(Locale.US, "%s[%d]", new Object[]{str, Integer.valueOf(r0)}), a));
            }
        } else if (obj instanceof Set) {
            for (Object a2 : (Set) obj) {
                linkedList.addAll(a(str, a2));
            }
        } else {
            linkedList.add(new BasicNameValuePair(str, obj.toString()));
        }
        return linkedList;
    }

    protected String b() {
        return URLEncodedUtils.format(a(), this.f);
    }
}
