package com.vungle.publisher;

import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: vungle */
public final class als {
    static final alo a = new alo() {
    };
    private static final als b = new als();
    private final AtomicReference<alo> c = new AtomicReference();
    private final AtomicReference<alq> d = new AtomicReference();
    private final AtomicReference<alu> e = new AtomicReference();
    private final AtomicReference<aln> f = new AtomicReference();
    private final AtomicReference<alt> g = new AtomicReference();

    @Deprecated
    public static als a() {
        return b;
    }

    als() {
    }

    public final alo b() {
        if (this.c.get() == null) {
            Object a = a(alo.class, System.getProperties());
            if (a == null) {
                this.c.compareAndSet(null, a);
            } else {
                this.c.compareAndSet(null, (alo) a);
            }
        }
        return (alo) this.c.get();
    }

    public final alq c() {
        if (this.d.get() == null) {
            Object a = a(alq.class, System.getProperties());
            if (a == null) {
                this.d.compareAndSet(null, alr.a());
            } else {
                this.d.compareAndSet(null, (alq) a);
            }
        }
        return (alq) this.d.get();
    }

    public final alu d() {
        if (this.e.get() == null) {
            Object a = a(alu.class, System.getProperties());
            if (a == null) {
                this.e.compareAndSet(null, alv.a());
            } else {
                this.e.compareAndSet(null, (alu) a);
            }
        }
        return (alu) this.e.get();
    }

    public final aln e() {
        if (this.f.get() == null) {
            Object a = a(aln.class, System.getProperties());
            if (a == null) {
                this.f.compareAndSet(null, new aln(this) {
                    final /* synthetic */ als a;

                    {
                        this.a = r1;
                    }
                });
            } else {
                this.f.compareAndSet(null, (aln) a);
            }
        }
        return (aln) this.f.get();
    }

    private static Object a(Class<?> cls, Properties properties) {
        String str;
        Properties properties2 = (Properties) properties.clone();
        String simpleName = cls.getSimpleName();
        String str2 = "rxjava.plugin.";
        String property = properties2.getProperty(str2 + simpleName + ".implementation");
        if (property == null) {
            String str3 = ".class";
            String str4 = ".impl";
            for (Entry entry : properties2.entrySet()) {
                String obj = entry.getKey().toString();
                if (obj.startsWith(str2) && obj.endsWith(str3) && simpleName.equals(entry.getValue().toString())) {
                    str = str2 + obj.substring(0, obj.length() - str3.length()).substring(str2.length()) + str4;
                    String property2 = properties2.getProperty(str);
                    if (property2 == null) {
                        throw new IllegalStateException("Implementing class declaration for " + simpleName + " missing: " + str);
                    }
                    str = property2;
                    if (str != null) {
                        return null;
                    }
                    try {
                        return Class.forName(str).asSubclass(cls).newInstance();
                    } catch (Throwable e) {
                        throw new IllegalStateException(simpleName + " implementation is not an instance of " + simpleName + ": " + str, e);
                    } catch (Throwable e2) {
                        throw new IllegalStateException(simpleName + " implementation class not found: " + str, e2);
                    } catch (Throwable e22) {
                        throw new IllegalStateException(simpleName + " implementation not able to be instantiated: " + str, e22);
                    } catch (Throwable e222) {
                        throw new IllegalStateException(simpleName + " implementation not able to be accessed: " + str, e222);
                    }
                }
            }
        }
        str = property;
        if (str != null) {
            return null;
        }
        return Class.forName(str).asSubclass(cls).newInstance();
    }

    public final alt f() {
        if (this.g.get() == null) {
            Object a = a(alt.class, System.getProperties());
            if (a == null) {
                this.g.compareAndSet(null, alt.g());
            } else {
                this.g.compareAndSet(null, (alt) a);
            }
        }
        return (alt) this.g.get();
    }
}
