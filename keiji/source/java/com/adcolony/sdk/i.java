package com.adcolony.sdk;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;

public class i extends ae {
    private a c;
    private boolean d;
    private String e;
    private String f;
    private String g;

    public class a extends Button {
        boolean a;
        OnClickListener b;
        final /* synthetic */ i c;

        a(i iVar, Context context) {
            this.c = iVar;
            super(context);
        }

        public void setOnClickListener(OnClickListener onClickListener) {
            if (!this.a) {
                super.setOnClickListener(onClickListener);
                this.b = onClickListener;
                this.a = true;
            }
        }

        public OnClickListener getOnClickListener() {
            return this.b;
        }
    }

    public /* bridge */ /* synthetic */ String getZoneID() {
        return super.getZoneID();
    }

    i(Context context, o oVar, an anVar) {
        super(context, oVar, anVar);
        JSONObject b = oVar.b();
        setNative(true);
        this.d = bb.c(b, "engagement_enabled");
        this.e = bb.a(b, "engagement_click_action");
        this.f = bb.a(b, "engagement_click_action_type");
        this.g = bb.a(b, "engagement_text");
        if (this.d) {
            this.c = new a(this, context);
            this.c.setText(this.g);
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ i a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.c()) {
                        bd.e.b((Object) "Ignoring engagement click as view has been destroyed.");
                        return;
                    }
                    JSONObject a = bb.a();
                    bb.a(a, "id", this.a.getAdSessionId());
                    new o("AdSession.on_native_engagement", this.a.getContainer().b(), a).a();
                }
            });
        }
    }

    public String getAdvertiserName() {
        if (!c()) {
            return super.getAdvertiserName();
        }
        bd.e.b((Object) "Ignoring call to getAdvertiserName() as view has been destroyed");
        return BuildConfig.FLAVOR;
    }

    public ImageView getIcon() {
        ImageView icon = super.getIcon();
        if (icon == null) {
            return null;
        }
        if (!c()) {
            return icon;
        }
        bd.e.b((Object) "Ignoring call to getIcon() as view has been destroyed");
        return null;
    }

    public String getTitle() {
        if (!c()) {
            return super.getTitle();
        }
        bd.e.b((Object) "Ignoring call to getTitle() as view has been destroyed");
        return BuildConfig.FLAVOR;
    }

    public String getDescription() {
        if (!c()) {
            return super.getDescription();
        }
        bd.e.b((Object) "Ignoring call to getDescription() as view has been destroyed");
        return BuildConfig.FLAVOR;
    }

    public a getEngagementButton() {
        if (!c()) {
            return this.c;
        }
        bd.e.b((Object) "Ignoring call to getEngagementButton() as view has been destroyed");
        return null;
    }
}
