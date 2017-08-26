package com.igaworks.adbrix.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class IconModel implements Parcelable {
    public static final Creator CREATOR = new Creator() {
        public IconModel createFromParcel(Parcel parcel) {
            return new IconModel(parcel);
        }

        public IconModel[] newArray(int i) {
            return new IconModel[i];
        }
    };
    private String Resource;
    private int ResourceKey;

    public String getResource() {
        return this.Resource;
    }

    public IconModel(Parcel parcel) {
        this.Resource = parcel.readString();
        this.ResourceKey = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.Resource);
        parcel.writeInt(this.ResourceKey);
    }
}
