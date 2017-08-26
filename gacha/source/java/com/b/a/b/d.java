package com.b.a.b;

import java.io.InputStream;
import java.util.Properties;

/* compiled from: BuildProperties */
class d {
    public final String a;
    public final String b;
    public final String c;
    public final String d;

    d(String str, String str2, String str3, String str4) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public static d a(Properties properties) {
        return new d(properties.getProperty("version_code"), properties.getProperty("version_name"), properties.getProperty("build_id"), properties.getProperty("package_name"));
    }

    public static d a(InputStream inputStream) {
        Properties properties = new Properties();
        properties.load(inputStream);
        return a(properties);
    }
}
