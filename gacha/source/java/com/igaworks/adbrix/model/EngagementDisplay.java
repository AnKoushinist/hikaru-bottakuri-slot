package com.igaworks.adbrix.model;

public class EngagementDisplay {
    private String CompleteMessage;
    private int CompleteToastmsec;
    private boolean IsProgressShow;
    private String ProgressMessage;
    private String ProgressTitle;

    public boolean isProgressShow() {
        return this.IsProgressShow;
    }

    public String getProgressTitle() {
        return this.ProgressTitle;
    }

    public String getProgressMessage() {
        return this.ProgressMessage;
    }

    public String getCompleteMessage() {
        return this.CompleteMessage;
    }

    public int getCompleteToastMSec() {
        return this.CompleteToastmsec;
    }
}
