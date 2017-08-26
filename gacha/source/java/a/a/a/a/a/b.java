package a.a.a.a.a;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

/* compiled from: LogConfigurator */
public class b {
    private Level a = Level.DEBUG;
    private String b = "%d - [%p::%c::%C] - %m%n";
    private String c = "%m%n";
    private String d = "android-log4j.log";
    private int e = 5;
    private long f = 524288;
    private boolean g = true;
    private boolean h = true;
    private boolean i = true;

    public void a() {
        Logger rootLogger = Logger.getRootLogger();
        if (i()) {
            k();
        }
        if (j()) {
            l();
        }
        rootLogger.setLevel(b());
    }

    public void a(String str, Level level) {
        Logger.getLogger(str).setLevel(level);
    }

    private void k() {
        Logger rootLogger = Logger.getRootLogger();
        try {
            RollingFileAppender rollingFileAppender = new RollingFileAppender(new PatternLayout(c()), e());
            rollingFileAppender.setMaxBackupIndex(f());
            rollingFileAppender.setMaximumFileSize(g());
            rollingFileAppender.setImmediateFlush(h());
            rootLogger.addAppender(rollingFileAppender);
        } catch (Throwable e) {
            throw new RuntimeException("Exception configuring log system", e);
        }
    }

    private void l() {
        Logger.getRootLogger().addAppender(new a(new PatternLayout(d())));
    }

    public Level b() {
        return this.a;
    }

    public void a(Level level) {
        this.a = level;
    }

    public String c() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public String d() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public String e() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public int f() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public long g() {
        return this.f;
    }

    public void a(long j) {
        this.f = j;
    }

    public boolean h() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public boolean i() {
        return this.i;
    }

    public void b(boolean z) {
        this.i = z;
    }

    public boolean j() {
        return this.h;
    }

    public void c(boolean z) {
        this.h = z;
    }
}
