package com.b.a.c;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import org.cocos2dx.lib.BuildConfig;

/* compiled from: ClsFileOutputStream */
class d extends FileOutputStream {
    public static final FilenameFilter a = new FilenameFilter() {
        public boolean accept(File file, String str) {
            return str.endsWith(".cls_temp");
        }
    };
    private final String b;
    private File c;
    private File d;
    private boolean e = false;

    public d(File file, String str) {
        super(new File(file, str + ".cls_temp"));
        this.b = file + File.separator + str;
        this.c = new File(this.b + ".cls_temp");
    }

    public synchronized void close() {
        if (!this.e) {
            this.e = true;
            super.flush();
            super.close();
            File file = new File(this.b + ".cls");
            if (this.c.renameTo(file)) {
                this.c = null;
                this.d = file;
            } else {
                String str = BuildConfig.FLAVOR;
                if (file.exists()) {
                    str = " (target already exists)";
                } else if (!this.c.exists()) {
                    str = " (source does not exist)";
                }
                throw new IOException("Could not rename temp file: " + this.c + " -> " + file + str);
            }
        }
    }

    public void a() {
        if (!this.e) {
            this.e = true;
            super.flush();
            super.close();
        }
    }
}
