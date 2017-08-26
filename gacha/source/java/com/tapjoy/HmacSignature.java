package com.tapjoy;

import android.net.Uri;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.security.Key;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacSignature {
    private String a;
    private String b;

    public HmacSignature(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String sign(String str, Map map) {
        int i = 0;
        try {
            Key secretKeySpec = new SecretKeySpec(this.b.getBytes(), this.a);
            Mac instance = Mac.getInstance(this.a);
            instance.init(secretKeySpec);
            Uri parse = Uri.parse(str);
            String str2 = parse.getScheme() + "://" + parse.getHost();
            int i2 = ((parse.getScheme().equals("http") && parse.getPort() == 80) || (parse.getScheme().equals("https") && parse.getPort() == 443)) ? 1 : 0;
            if (i2 == 0 && -1 != parse.getPort()) {
                str2 = str2 + ":" + parse.getPort();
            }
            str2 = "POST&" + Uri.encode(str2.toLowerCase() + parse.getPath()) + "&" + Uri.encode(a(map));
            TapjoyLog.v("HmacSignature", "Base Url: " + str2);
            byte[] doFinal = instance.doFinal(str2.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            int length = doFinal.length;
            while (i < length) {
                String toHexString = Integer.toHexString(doFinal[i] & 255);
                if (toHexString.length() == 1) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(toHexString);
                i++;
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean matches(String str, Map map, String str2) {
        return sign(str, map).equals(str2);
    }

    private static String a(Map map) {
        TreeSet treeSet = new TreeSet(map.keySet());
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            stringBuilder.append(str + Operation.EQUALS + ((String) map.get(str)) + "&");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("&"));
        TapjoyLog.v("HmacSignature", "Unhashed String: " + stringBuilder.toString());
        return stringBuilder.toString();
    }
}
