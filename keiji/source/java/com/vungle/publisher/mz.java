package com.vungle.publisher;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.view.ContextThemeWrapper;
import com.vungle.log.Logger;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class mz {

    /* compiled from: vungle */
    public interface a {
        void a();

        void b();

        void c();
    }

    public final AlertDialog a(Context context, n nVar, final a aVar) {
        Builder builder = new Builder(new ContextThemeWrapper(context, context.getApplicationInfo().theme));
        builder.setTitle(nVar.getIncentivizedCancelDialogTitle());
        builder.setMessage(nVar.getIncentivizedCancelDialogBodyText());
        builder.setPositiveButton(nVar.getIncentivizedCancelDialogKeepWatchingButtonText(), new OnClickListener(this) {
            final /* synthetic */ mz b;

            public final void onClick(DialogInterface dialogInterface, int i) {
                Logger.d(Logger.AD_TAG, "positive click");
                aVar.a();
            }
        });
        builder.setNegativeButton(nVar.getIncentivizedCancelDialogCloseButtonText(), new OnClickListener(this) {
            final /* synthetic */ mz b;

            public final void onClick(DialogInterface dialogInterface, int i) {
                Logger.d(Logger.AD_TAG, "negative click");
                aVar.b();
            }
        });
        builder.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ mz b;

            public final void onCancel(DialogInterface dialogInterface) {
                Logger.d(Logger.AD_TAG, "cancel click");
                aVar.c();
            }
        });
        return builder.create();
    }
}
