package com.igaworks.adbrix.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class StepRewardModel implements Parcelable {
    public static final Creator CREATOR = new Creator() {
        public StepRewardModel createFromParcel(Parcel parcel) {
            return new StepRewardModel(parcel);
        }

        public StepRewardModel[] newArray(int i) {
            return new StepRewardModel[i];
        }
    };
    private int ConversionKey;
    private String Name;
    private int Reward;
    private boolean isComplete = false;

    public int getConversionKey() {
        return this.ConversionKey;
    }

    public String getName() {
        return this.Name;
    }

    public int getReward() {
        return this.Reward;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public void setComplete(boolean z) {
        this.isComplete = z;
    }

    public StepRewardModel(Parcel parcel) {
        this.ConversionKey = parcel.readInt();
        this.Name = parcel.readString();
        this.Reward = parcel.readInt();
        this.isComplete = Boolean.parseBoolean(parcel.readString());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.ConversionKey);
        parcel.writeString(this.Name);
        parcel.writeInt(this.Reward);
        parcel.writeString(new StringBuilder(String.valueOf(this.isComplete)).toString());
    }
}
