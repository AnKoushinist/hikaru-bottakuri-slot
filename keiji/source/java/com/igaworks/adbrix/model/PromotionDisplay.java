package com.igaworks.adbrix.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class PromotionDisplay implements Parcelable {
    public static final Creator CREATOR = new Creator() {
        public PromotionDisplay createFromParcel(Parcel parcel) {
            return new PromotionDisplay(parcel);
        }

        public PromotionDisplay[] newArray(int i) {
            return new PromotionDisplay[i];
        }
    };
    private String ClickUrl;
    private IconModel Icon;
    private boolean IsMarketUrl;
    private SlideModel Slides;
    private List<StepRewardModel> StepReward;
    private String Title;
    private int Type;

    public String getTitle() {
        return this.Title;
    }

    public IconModel getIcon() {
        return this.Icon;
    }

    public SlideModel getSlide() {
        return this.Slides;
    }

    public String getClickUrl() {
        return this.ClickUrl;
    }

    public List<StepRewardModel> getStepReward() {
        return this.StepReward;
    }

    public boolean isIsMarketUrl() {
        return this.IsMarketUrl;
    }

    public PromotionDisplay(Parcel parcel) {
        this.Title = parcel.readString();
        this.Icon = (IconModel) parcel.readParcelable(IconModel.class.getClassLoader());
        this.Slides = (SlideModel) parcel.readParcelable(SlideModel.class.getClassLoader());
        this.Type = parcel.readInt();
        this.ClickUrl = parcel.readString();
        this.IsMarketUrl = Boolean.parseBoolean(parcel.readString());
        this.StepReward = new ArrayList();
        parcel.readTypedList(this.StepReward, StepRewardModel.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.Title);
        parcel.writeParcelable(this.Icon, i);
        parcel.writeParcelable(this.Slides, i);
        parcel.writeInt(this.Type);
        parcel.writeString(this.ClickUrl);
        parcel.writeString(new StringBuilder(String.valueOf(this.IsMarketUrl)).toString());
        parcel.writeTypedList(this.StepReward);
    }
}
