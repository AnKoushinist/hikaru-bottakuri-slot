package com.tapjoy.internal;

import java.io.IOException;
import java.io.Serializable;

public abstract class dl implements Serializable {
    transient int a = 0;
    public transient int b = 0;
    private final transient dn c;
    private final transient hu d;

    public static abstract class a {
        hr a;
        dp b;

        public final a a(hu huVar) {
            if (huVar.c() > 0) {
                if (this.b == null) {
                    this.a = new hr();
                    this.b = new dp(this.a);
                }
                try {
                    this.b.a(huVar);
                } catch (IOException e) {
                    throw new AssertionError();
                }
            }
            return this;
        }

        public final a a(int i, dk dkVar, Object obj) {
            if (this.b == null) {
                this.a = new hr();
                this.b = new dp(this.a);
            }
            try {
                dkVar.a().a(this.b, i, obj);
                return this;
            } catch (IOException e) {
                throw new AssertionError();
            }
        }

        public final hu a() {
            return this.a != null ? new hu(this.a.h().g()) : hu.b;
        }
    }

    public dl(dn dnVar, hu huVar) {
        if (dnVar == null) {
            throw new NullPointerException("adapter == null");
        } else if (huVar == null) {
            throw new NullPointerException("unknownFields == null");
        } else {
            this.c = dnVar;
            this.d = huVar;
        }
    }

    public final hu a() {
        hu huVar = this.d;
        return huVar != null ? huVar : hu.b;
    }

    public String toString() {
        return dn.c(this);
    }
}
