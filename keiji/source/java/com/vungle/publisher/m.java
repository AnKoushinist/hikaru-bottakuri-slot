package com.vungle.publisher;

import android.os.Bundle;
import com.unity3d.ads.adunit.AdUnitActivity;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

/* compiled from: vungle */
public class m implements n {
    protected Bundle a = new Bundle();
    protected Bundle b = new Bundle();

    @Inject
    public m() {
        e(true);
    }

    public boolean isBackButtonImmediatelyEnabled() {
        return this.a.getBoolean("isBackButtonEnabled");
    }

    public final void a(boolean z) {
        this.a.putBoolean("isBackButtonEnabled", z);
    }

    public final String a(String str) {
        return this.b.getString(str);
    }

    public final void a(String str, String str2) {
        this.b.putString(str, str2);
    }

    public Map<String, String> getExtras() {
        Map<String, String> hashMap = new HashMap();
        for (String str : this.b.keySet()) {
            hashMap.put(str, this.b.getString(str));
        }
        return hashMap;
    }

    public boolean isImmersiveMode() {
        return this.a.getBoolean("isImmersiveMode", true);
    }

    public final void b(boolean z) {
        this.a.putBoolean("isImmersiveMode", z);
    }

    public boolean isIncentivized() {
        return this.a.getBoolean("isIncentivized");
    }

    public final void c(boolean z) {
        this.a.putBoolean("isIncentivized", z);
    }

    public String getIncentivizedCancelDialogBodyText() {
        return this.a.getString("incentivizedCancelDialogBodyText");
    }

    public final void b(String str) {
        this.a.putString("incentivizedCancelDialogBodyText", str);
    }

    public String getIncentivizedCancelDialogCloseButtonText() {
        return this.a.getString("incentivizedCancelDialogNegativeButtonText");
    }

    public final void c(String str) {
        this.a.putString("incentivizedCancelDialogNegativeButtonText", str);
    }

    public String getIncentivizedCancelDialogKeepWatchingButtonText() {
        return this.a.getString("incentivizedCancelDialogPositiveButtonText");
    }

    public final void d(String str) {
        this.a.putString("incentivizedCancelDialogPositiveButtonText", str);
    }

    public String getIncentivizedCancelDialogTitle() {
        return this.a.getString("incentivizedCancelDialogTitle");
    }

    public final void e(String str) {
        this.a.putString("incentivizedCancelDialogTitle", str);
    }

    public final void f(String str) {
        this.a.putString("incentivizedUserId", str);
    }

    public String getIncentivizedUserId() {
        return this.a.getString("incentivizedUserId");
    }

    public Orientation getOrientation() {
        return (Orientation) this.a.getParcelable(AdUnitActivity.EXTRA_ORIENTATION);
    }

    public final void a(Orientation orientation) {
        this.a.putParcelable(AdUnitActivity.EXTRA_ORIENTATION, orientation);
    }

    public String getPlacement() {
        return this.a.getString("placement");
    }

    public final void g(String str) {
        this.a.putString("placement", str);
    }

    public boolean isSoundEnabled() {
        return this.a.getBoolean("isSoundEnabled");
    }

    public final void d(boolean z) {
        this.a.putBoolean("isSoundEnabled", z);
    }

    public boolean isTransitionAnimationEnabled() {
        return this.a.getBoolean("isTransitionAnimationEnabled");
    }

    public final void e(boolean z) {
        this.a.putBoolean("isTransitionAnimationEnabled", z);
    }

    public int hashCode() {
        return this.a.hashCode() ^ this.b.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof m)) {
            m mVar = (m) obj;
            boolean z = mVar != null && mVar.a.equals(this.a) && mVar.b.equals(this.b);
            if (z) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(123);
        a(stringBuilder, this.a);
        a(stringBuilder, this.b);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    private static void a(StringBuilder stringBuilder, Bundle bundle) {
        Object obj = 1;
        for (String str : bundle.keySet()) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(", ");
            }
            stringBuilder.append(str).append(" = ").append(bundle.get(str));
        }
    }
}
