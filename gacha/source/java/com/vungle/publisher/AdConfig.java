package com.vungle.publisher;

import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AdConfig implements n {
    private final m a = new m();

    @Inject
    public AdConfig() {
        setTransitionAnimationEnabled(true);
    }

    public m getDelegateAdConfig() {
        return this.a;
    }

    public boolean isBackButtonImmediatelyEnabled() {
        return this.a.isBackButtonImmediatelyEnabled();
    }

    public void setBackButtonImmediatelyEnabled(boolean z) {
        this.a.a(z);
    }

    public String getExtra1() {
        return a(1);
    }

    public void setExtra1(String str) {
        a(1, str);
    }

    public String getExtra2() {
        return a(2);
    }

    public void setExtra2(String str) {
        a(2, str);
    }

    public String getExtra3() {
        return a(3);
    }

    public void setExtra3(String str) {
        a(3, str);
    }

    public String getExtra4() {
        return a(4);
    }

    public void setExtra4(String str) {
        a(4, str);
    }

    public String getExtra5() {
        return a(5);
    }

    public void setExtra5(String str) {
        a(5, str);
    }

    public String getExtra6() {
        return a(6);
    }

    public void setExtra6(String str) {
        a(6, str);
    }

    public String getExtra7() {
        return a(7);
    }

    public void setExtra7(String str) {
        a(7, str);
    }

    public String getExtra8() {
        return a(8);
    }

    public void setExtra8(String str) {
        a(8, str);
    }

    private String a(int i) {
        return this.a.a("extra" + i);
    }

    private void a(int i, String str) {
        this.a.a("extra" + i, str);
    }

    public Map<String, String> getExtras() {
        return this.a.getExtras();
    }

    public boolean isImmersiveMode() {
        return this.a.isImmersiveMode();
    }

    public void setImmersiveMode(boolean z) {
        this.a.b(z);
    }

    public boolean isIncentivized() {
        return this.a.isIncentivized();
    }

    public void setIncentivized(boolean z) {
        this.a.c(z);
    }

    public String getIncentivizedCancelDialogBodyText() {
        return this.a.getIncentivizedCancelDialogBodyText();
    }

    public void setIncentivizedCancelDialogBodyText(String str) {
        this.a.b(str);
    }

    public String getIncentivizedCancelDialogCloseButtonText() {
        return this.a.getIncentivizedCancelDialogCloseButtonText();
    }

    public void setIncentivizedCancelDialogCloseButtonText(String str) {
        this.a.c(str);
    }

    public String getIncentivizedCancelDialogKeepWatchingButtonText() {
        return this.a.getIncentivizedCancelDialogKeepWatchingButtonText();
    }

    public void setIncentivizedCancelDialogKeepWatchingButtonText(String str) {
        this.a.d(str);
    }

    public String getIncentivizedCancelDialogTitle() {
        return this.a.getIncentivizedCancelDialogTitle();
    }

    public void setIncentivizedCancelDialogTitle(String str) {
        this.a.e(str);
    }

    public String getIncentivizedUserId() {
        return this.a.getIncentivizedUserId();
    }

    public void setIncentivizedUserId(String str) {
        this.a.f(str);
    }

    public Orientation getOrientation() {
        return this.a.getOrientation();
    }

    public void setOrientation(Orientation orientation) {
        this.a.a(orientation);
    }

    public String getPlacement() {
        return this.a.getPlacement();
    }

    public void setPlacement(String str) {
        this.a.g(str);
    }

    public boolean isSoundEnabled() {
        return this.a.isSoundEnabled();
    }

    public void setSoundEnabled(boolean z) {
        this.a.d(z);
    }

    public boolean isTransitionAnimationEnabled() {
        return this.a.isTransitionAnimationEnabled();
    }

    public void setTransitionAnimationEnabled(boolean z) {
        this.a.e(z);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public boolean equals(Object obj) {
        return this.a.equals(obj);
    }

    public String toString() {
        return this.a.toString();
    }
}
