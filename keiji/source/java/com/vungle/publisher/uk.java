package com.vungle.publisher;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.vungle.log.Logger;
import com.vungle.publisher.inject.Injector;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

@Singleton
/* compiled from: vungle */
public final class uk implements uq {
    @Inject
    ConnectivityManager a;
    @Inject
    Provider<ur> b;
    @Inject
    TelephonyManager c;

    @Inject
    uk() {
        Injector.b().a(this);
    }

    public final un a() {
        try {
            NetworkInfo activeNetworkInfo = this.a.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            int type = activeNetworkInfo.getType();
            switch (type) {
                case TwitterResponse.NONE /*0*/:
                    return un.mobile;
                case TwitterResponse.READ /*1*/:
                case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                    return un.wifi;
                default:
                    Logger.d(Logger.NETWORK_TAG, "unknown connectivity type: " + type);
                    return null;
            }
        } catch (Throwable e) {
            Logger.d(Logger.NETWORK_TAG, "error getting connectivity type", e);
            return null;
        }
    }

    public final String b() {
        String str = null;
        try {
            str = this.c.getNetworkOperatorName();
        } catch (Throwable e) {
            Logger.d(Logger.NETWORK_TAG, "error getting network operator", e);
        }
        return str;
    }

    public final up c() {
        up upVar = up.unknown;
        if (this.a == null || !agl.a(pj.NOUGAT)) {
            return upVar;
        }
        if (!this.a.isActiveNetworkMetered()) {
            return up.not_applicable;
        }
        switch (this.a.getRestrictBackgroundStatus()) {
            case TwitterResponse.READ /*1*/:
                return up.disabled;
            case TwitterResponse.READ_WRITE /*2*/:
                return up.whitelisted;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return up.enabled;
            default:
                return up.unknown;
        }
    }

    public final boolean d() {
        try {
            if (this.a != null && agl.a(pj.JELLY_BEAN)) {
                return this.a.isActiveNetworkMetered();
            }
        } catch (Throwable e) {
            Logger.d(Logger.NETWORK_TAG, "error getting network details", e);
        }
        return false;
    }

    public final uo e() {
        try {
            NetworkInfo activeNetworkInfo = this.a != null ? this.a.getActiveNetworkInfo() : null;
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                int type = activeNetworkInfo.getType();
                int subtype = activeNetworkInfo.getSubtype();
                if (type == 1 || type == 6) {
                    return uo.wifi;
                }
                if (type == 0) {
                    if (agl.a(pj.HONEYCOMB_MR2) && subtype == 15) {
                        return uo.hspap;
                    }
                    switch (subtype) {
                        case TwitterResponse.READ /*1*/:
                            return uo.gprs;
                        case TwitterResponse.READ_WRITE /*2*/:
                            return uo.edge;
                        case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                            return uo.umts;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                            return uo.cdma;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
                            return uo.evdo0;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                            return uo.evdoA;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                            return uo.rtt1x;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                            return uo.hsdpa;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                            return uo.hsupa;
                        case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                            return uo.hspa;
                        case R.styleable.Toolbar_popupTheme /*11*/:
                            return uo.iden;
                        case R.styleable.Toolbar_titleTextAppearance /*12*/:
                            return uo.evdoB;
                        case R.styleable.Toolbar_subtitleTextAppearance /*13*/:
                            return uo.lte;
                        case R.styleable.Toolbar_titleMargin /*14*/:
                            return uo.ehrpd;
                        default:
                            return uo.unknown;
                    }
                }
            }
        } catch (Throwable e) {
            Logger.d(Logger.NETWORK_TAG, "error getting connectivity details", e);
        }
        return uo.unknown;
    }
}
