package com.vungle.publisher;

import android.app.AlertDialog;
import android.app.Fragment;
import com.vungle.log.Logger;
import com.vungle.publisher.gm.a;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class ms extends Fragment {
    AlertDialog a;
    n b;
    @Inject
    a c;
    @Inject
    mz d;

    public abstract void a();

    public abstract String b();

    public boolean a(int i) {
        return false;
    }

    public void a(boolean z) {
        Logger.v(Logger.AD_TAG, (z ? "gained" : "lost") + " window focus");
    }
}
