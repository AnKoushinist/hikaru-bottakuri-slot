package a.a.a.a.a.f;

import a.a.a.a.c;
import a.a.a.a.i;
import android.content.Context;
import java.io.File;

/* compiled from: FileStoreImpl */
public class b implements a {
    private final Context a;
    private final String b;
    private final String c;

    public b(i iVar) {
        if (iVar.E() == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
        }
        this.a = iVar.E();
        this.b = iVar.G();
        this.c = "Android/" + this.a.getPackageName();
    }

    public File a() {
        return a(this.a.getFilesDir());
    }

    File a(File file) {
        if (file == null) {
            c.h().a("Fabric", "Null File");
        } else if (file.exists() || file.mkdirs()) {
            return file;
        } else {
            c.h().d("Fabric", "Couldn't create file");
        }
        return null;
    }
}
