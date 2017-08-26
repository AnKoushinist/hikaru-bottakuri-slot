package com.b.a.b;

import a.a.a.a.a;
import android.annotation.TargetApi;
import android.app.Activity;
import java.util.concurrent.ExecutorService;

@TargetApi(14)
/* compiled from: ActivityLifecycleCheckForUpdatesController */
class b extends a {
    private final a.a.a.a.a.b a = new a.a.a.a.a.b(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void a(Activity activity) {
            if (this.a.a()) {
                this.a.b.submit(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.c();
                    }
                });
            }
        }
    };
    private final ExecutorService b;

    public b(a aVar, ExecutorService executorService) {
        this.b = executorService;
        aVar.a(this.a);
    }
}
