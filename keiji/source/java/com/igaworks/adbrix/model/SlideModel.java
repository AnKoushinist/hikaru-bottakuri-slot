package com.igaworks.adbrix.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public class SlideModel implements Parcelable {
    public static final Creator CREATOR = new Creator() {
        public SlideModel createFromParcel(Parcel parcel) {
            return new SlideModel(parcel);
        }

        public SlideModel[] newArray(int i) {
            return new SlideModel[i];
        }
    };
    private List<String> Resource;
    private int ResourceKey;

    public int getResourceKey() {
        return this.ResourceKey;
    }

    public List<String> getResource() {
        return this.Resource;
    }

    public SlideModel(Parcel parcel) {
        this.ResourceKey = parcel.readInt();
        parcel.readStringList(this.Resource);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.ResourceKey);
        parcel.writeStringList(this.Resource);
    }
}
