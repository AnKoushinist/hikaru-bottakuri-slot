package jp.reifrontier.silentlogsdkandroid.util;

import a.a.a.a.a.b;
import android.app.Application;
import android.content.Context;
import java.io.File;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public final class RFLLogger {
    private static Context mContext;
    private static RFLLogger rflLogger;
    private static Logger sLog;
    private static String slogName;

    public static RFLLogger getInstance(String str, Context context) {
        if (rflLogger == null) {
            slogName = str;
            mContext = context;
            rflLogger = new RFLLogger();
        }
        return rflLogger;
    }

    private RFLLogger() {
        b bVar = new b();
        bVar.c(mContext.getFilesDir() + File.separator + slogName + "-sdk.log");
        bVar.a(Level.DEBUG);
        bVar.a(5);
        bVar.b(true);
        bVar.a("org.apache", Level.INFO);
        bVar.a("%d{yyyy-MM-dd HH:mm:ss} %p:%r:%c:%m%n");
        bVar.a(524288);
        bVar.a(true);
        bVar.b("%d{yyyy-MM-dd HH:mm:ss} %p:%r:%c:%m%n");
        bVar.c(false);
        bVar.a();
        sLog = Logger.getLogger(Application.class);
    }

    public void info(String str) {
        if (mContext != null) {
            sLog.info(str);
        }
    }

    public void error(String str) {
        if (mContext != null) {
            sLog.error(str);
        }
    }
}
