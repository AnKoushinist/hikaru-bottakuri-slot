package com.vungle.publisher;

/* compiled from: vungle */
public enum up {
    disabled("DISABLED"),
    whitelisted("WHITELISTED"),
    enabled("ENABLED"),
    not_applicable("NOT_APPLICABLE"),
    unknown("UNKNOWN");
    
    public final String f;

    private up(String str) {
        this.f = str;
    }
}
