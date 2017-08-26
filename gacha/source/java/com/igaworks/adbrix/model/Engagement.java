package com.igaworks.adbrix.model;

import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;

public class Engagement {
    private int ConversionKey;
    private EngagementDisplay Display;
    private int ParentConversionKey;
    private List<Segment> Segments;
    private Trigger Trigger;

    public List<Segment> getSegments() {
        return this.Segments;
    }

    public Trigger getTrigger() {
        return this.Trigger;
    }

    public EngagementDisplay getDisplayData() {
        return this.Display;
    }

    public int getConversionKey() {
        return this.ConversionKey;
    }

    public int getParentConversionKey() {
        return this.ParentConversionKey;
    }

    public String toString() {
        String str = BuildConfig.FLAVOR;
        if (this.Trigger != null) {
            str = new StringBuilder(String.valueOf(this.Trigger.getGroup())).append(Operation.DIVISION).append(this.Trigger.getActivity()).toString();
        }
        return String.format("ParentConversionKey : %d, ConversionKey : %d, Trigger : %s", new Object[]{Integer.valueOf(this.ParentConversionKey), Integer.valueOf(this.ConversionKey), str});
    }
}
