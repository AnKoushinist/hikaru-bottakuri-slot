package com.igaworks.dao.tracking;

public class TrackingActivityModel {
    private int id;
    private int isDirty = 0;
    private String key;
    private String value;

    public TrackingActivityModel(int i, String str, String str2) {
        this.id = i;
        this.key = str;
        this.value = str2;
    }

    public int getId() {
        return this.id;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }
}
