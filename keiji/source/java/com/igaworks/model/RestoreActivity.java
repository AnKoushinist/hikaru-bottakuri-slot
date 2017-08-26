package com.igaworks.model;

import java.util.Calendar;

public class RestoreActivity {
    private String activity;
    private String group;
    private int no;
    private Calendar registDatetime;

    public RestoreActivity(int i, String str, String str2, Calendar calendar) {
        this.no = i;
        this.group = str;
        this.activity = str2;
        this.registDatetime = calendar;
    }

    public String getGroup() {
        return this.group;
    }

    public String getActivity() {
        return this.activity;
    }

    public Calendar getRegistDatetime() {
        return this.registDatetime;
    }
}
