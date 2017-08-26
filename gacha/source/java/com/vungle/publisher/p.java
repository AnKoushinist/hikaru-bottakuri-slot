package com.vungle.publisher;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.unity3d.ads.adunit.AdUnitActivity;

/* compiled from: vungle */
public class p extends m implements Parcelable {
    public static final Creator<p> CREATOR = new Creator<p>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new p(new m[0]).a(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new p[i];
        }
    };
    static final Orientation c = Orientation.matchVideo;

    public p(m... mVarArr) {
        for (m mVar : mVarArr) {
            if (mVar != null) {
                this.a.putAll(mVar.a);
                this.b.putAll(mVar.b);
            }
        }
    }

    public boolean isBackButtonImmediatelyEnabled() {
        return this.a.getBoolean("isBackButtonEnabled", false);
    }

    public boolean isImmersiveMode() {
        return this.a.getBoolean("isImmersiveMode", false);
    }

    public boolean isIncentivized() {
        return this.a.getBoolean("isIncentivized", false);
    }

    public String getIncentivizedCancelDialogBodyText() {
        String string = this.a.getString("incentivizedCancelDialogBodyText");
        if (string == null) {
            return "Closing this video early will prevent you from earning your reward. Are you sure?";
        }
        return string;
    }

    public String getIncentivizedCancelDialogCloseButtonText() {
        String string = this.a.getString("incentivizedCancelDialogNegativeButtonText");
        if (string == null) {
            return "Close video";
        }
        return string;
    }

    public String getIncentivizedCancelDialogKeepWatchingButtonText() {
        String string = this.a.getString("incentivizedCancelDialogPositiveButtonText");
        if (string == null) {
            return "Keep watching";
        }
        return string;
    }

    public String getIncentivizedCancelDialogTitle() {
        String string = this.a.getString("incentivizedCancelDialogTitle");
        if (string == null) {
            return "Close video?";
        }
        return string;
    }

    public Orientation getOrientation() {
        Orientation orientation = (Orientation) this.a.getParcelable(AdUnitActivity.EXTRA_ORIENTATION);
        return orientation == null ? c : orientation;
    }

    public boolean isSoundEnabled() {
        return this.a.getBoolean("isSoundEnabled", true);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.a);
        parcel.writeBundle(this.b);
    }

    protected final p a(Parcel parcel) {
        ClassLoader classLoader = p.class.getClassLoader();
        this.a = parcel.readBundle(classLoader);
        this.b = parcel.readBundle(classLoader);
        return this;
    }
}
