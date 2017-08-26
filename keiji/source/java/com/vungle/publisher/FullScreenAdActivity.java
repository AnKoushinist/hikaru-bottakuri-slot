package com.vungle.publisher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.WindowInsets;
import com.vungle.log.Logger;
import com.vungle.publisher.cj.b;
import com.vungle.publisher.gm.a;
import com.vungle.publisher.inject.Injector;
import javax.inject.Inject;

/* compiled from: vungle */
public class FullScreenAdActivity extends Activity {
    public static final String AD_ID_EXTRA_KEY = "adId";
    public static final String AD_TYPE_EXTRA_KEY = "adType";
    ne<cj> a;
    @Inject
    ql b;
    @Inject
    bw c;
    @Inject
    py d;
    @Inject
    b e;
    @Inject
    lr f;
    @Inject
    a g;
    @Inject
    ne.a h;
    @Inject
    q i;
    private View j;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Logger.d(Logger.AD_TAG, "interstitial ad");
            Injector.b().a(this);
            getWindow().setFlags(16777216, 16777216);
            Intent intent = getIntent();
            n nVar = (n) intent.getBundleExtra("adConfig").getParcelable("adConfig");
            String stringExtra = intent.getStringExtra(AD_ID_EXTRA_KEY);
            cj cjVar = (cj) new com.vungle.publisher.cj.b.AnonymousClass2(this.e, stringExtra).a((j) intent.getSerializableExtra(AD_TYPE_EXTRA_KEY));
            if (cjVar == null) {
                Logger.w(Logger.AD_TAG, "no ad in activity");
                this.b.a(new bl());
                finish();
                return;
            }
            Logger.d(Logger.AD_TAG, "creating ad activity with status: " + cjVar.g());
            this.a = (ne) new o<P>(this.h) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                protected final /* synthetic */ Object a() {
                    return (ne) this.a.a.get();
                }

                protected final /* synthetic */ Object b() {
                    return (ne) this.a.a.get();
                }

                protected final /* synthetic */ Object c() {
                    return (ne) this.a.b.get();
                }

                protected final /* synthetic */ Object d() {
                    return (ne) this.a.b.get();
                }
            }.a(cjVar);
            this.j = getWindow().getDecorView();
            a(nVar);
            if (agl.a(pj.NOUGAT)) {
                this.j.setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener(this) {
                    final /* synthetic */ FullScreenAdActivity a;

                    {
                        this.a = r1;
                    }

                    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                        int i = 0;
                        try {
                            int stableInsetLeft;
                            int stableInsetTop;
                            int stableInsetRight;
                            if (this.a.isInMultiWindowMode() && windowInsets.hasStableInsets()) {
                                stableInsetLeft = windowInsets.getStableInsetLeft();
                                stableInsetTop = windowInsets.getStableInsetTop();
                                stableInsetRight = windowInsets.getStableInsetRight();
                                i = windowInsets.getStableInsetBottom();
                            } else {
                                stableInsetRight = 0;
                                stableInsetTop = 0;
                                stableInsetLeft = 0;
                            }
                            this.a.j.getRootView().setPadding(stableInsetLeft, stableInsetTop, stableInsetRight, i);
                        } catch (Throwable e) {
                            Logger.e(Logger.AD_TAG, "Exception setting root view padding to avoid system controls overlap", e);
                        }
                        return windowInsets;
                    }
                });
            }
            this.a.a(this, cjVar, nVar, bundle);
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error playing ad", e);
            finish();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
            ne neVar = this.a;
            try {
                bundle.putString("currentFragment", neVar.c.b());
            } catch (Throwable e) {
                neVar.h.a(Logger.AD_TAG, "error in onSaveInstanceState", e);
            }
        } catch (Throwable e2) {
            this.g.a(Logger.AD_TAG, "error in onSaveInstanceState", e2);
        }
    }

    final void a(final n nVar) {
        if (agl.a(pj.KITKAT) && nVar.isImmersiveMode()) {
            this.j.setSystemUiVisibility(5894);
            this.j.setOnSystemUiVisibilityChangeListener(new OnSystemUiVisibilityChangeListener(this) {
                final /* synthetic */ FullScreenAdActivity b;

                public final void onSystemUiVisibilityChange(int i) {
                    if ((i & 4) == 0) {
                        this.b.a(nVar);
                    }
                }
            });
        }
    }

    protected void onResume() {
        try {
            super.onResume();
            py pyVar = this.d;
            Logger.d(Logger.AD_TAG, "onAdActivityResume()");
            pyVar.a(false);
            pyVar.i.e = 0;
        } catch (Throwable e) {
            this.g.a(Logger.AD_TAG, "error in onResume()", e);
        }
    }

    protected void onPause() {
        try {
            super.onPause();
            py pyVar = this.d;
            Logger.d(Logger.AD_TAG, "onAdActivityPause()");
            pyVar.i.e = pyVar.b();
            this.c.a.removeCallbacksAndMessages(null);
        } catch (Throwable e) {
            this.g.a(Logger.AD_TAG, "error in onPause()", e);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        try {
            this.a.a(this);
            py pyVar = this.d;
            Logger.d(Logger.AD_TAG, "onAdActivityDestroy()");
            pyVar.i.a(false);
        } catch (Throwable e) {
            Logger.e(Logger.AD_TAG, "error in onDestroy()", e);
        }
        if (!isFinishing()) {
            Logger.i(Logger.AD_TAG, "finishing ad that is being destroyed");
            finish();
        }
    }

    public void onBackPressed() {
        try {
            Logger.v(Logger.AD_TAG, "back button pressed");
            this.b.a(new t());
            this.a.c.a();
        } catch (Throwable e) {
            this.g.a(Logger.AD_TAG, "error in onBackPressed", e);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        this.a.c.a(i);
        return super.onKeyDown(i, keyEvent);
    }

    public void onWindowFocusChanged(boolean z) {
        try {
            super.onWindowFocusChanged(z);
            this.a.c.a(z);
        } catch (Throwable e) {
            this.g.a(Logger.AD_TAG, "error in onWindowFocusChanged", e);
        }
    }
}
