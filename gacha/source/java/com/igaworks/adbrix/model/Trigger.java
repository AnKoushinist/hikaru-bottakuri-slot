package com.igaworks.adbrix.model;

public class Trigger {
    private String Activity;
    private boolean Continue;
    private int Count;
    private String Group;
    private long Intervalmsec;
    private int ResetData;
    private String ResetType;

    public String getResetType() {
        return this.ResetType;
    }

    public int getResetData() {
        return this.ResetData;
    }

    public String getGroup() {
        return this.Group;
    }

    public String getActivity() {
        return this.Activity;
    }

    public int getCount() {
        return this.Count;
    }

    public long getIntervalMSec() {
        return this.Intervalmsec;
    }

    public boolean isContinuous() {
        return this.Continue;
    }
}
