package com.adcolony.sdk;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import com.tapjoy.TJAdUnitConstants.String;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;

class at {
    private o a;
    private AlertDialog b;
    private boolean c;

    at() {
        n.a("Alert.show", new q(this) {
            final /* synthetic */ at a;

            {
                this.a = r1;
            }

            public void a(o oVar) {
                if (!n.d()) {
                    bd.g.b((Object) "Null Activity reference, can't build AlertDialog.");
                } else if (bb.c(oVar.b(), "on_resume")) {
                    this.a.a = oVar;
                } else {
                    this.a.a(oVar);
                }
            }
        });
    }

    void a(final o oVar) {
        if (n.d()) {
            final Builder builder = n.a().i().n() >= 21 ? new Builder(n.c(), 16974374) : new Builder(n.c(), 16974126);
            JSONObject b = oVar.b();
            CharSequence a = bb.a(b, String.MESSAGE);
            CharSequence a2 = bb.a(b, String.TITLE);
            CharSequence a3 = bb.a(b, "positive");
            CharSequence a4 = bb.a(b, "negative");
            builder.setMessage(a);
            builder.setTitle(a2);
            builder.setPositiveButton(a3, new OnClickListener(this) {
                final /* synthetic */ at b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.b.b = null;
                    dialogInterface.dismiss();
                    JSONObject a = bb.a();
                    bb.a(a, "positive", true);
                    this.b.c = false;
                    oVar.a(a).a();
                }
            });
            if (!a4.equals(BuildConfig.FLAVOR)) {
                builder.setNegativeButton(a4, new OnClickListener(this) {
                    final /* synthetic */ at b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.b.b = null;
                        dialogInterface.dismiss();
                        JSONObject a = bb.a();
                        bb.a(a, "positive", false);
                        this.b.c = false;
                        oVar.a(a).a();
                    }
                });
            }
            builder.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ at a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    this.a.b = null;
                    this.a.c = false;
                }
            });
            ab.a(new Runnable(this) {
                final /* synthetic */ at b;

                public void run() {
                    this.b.c = true;
                    this.b.b = builder.show();
                }
            });
        }
    }

    void a() {
        if (this.a != null) {
            a(this.a);
            this.a = null;
        }
    }

    AlertDialog b() {
        return this.b;
    }

    void a(AlertDialog alertDialog) {
        this.b = alertDialog;
    }

    boolean c() {
        return this.c;
    }
}
