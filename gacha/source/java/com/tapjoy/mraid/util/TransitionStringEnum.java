package com.tapjoy.mraid.util;

public enum TransitionStringEnum {
    DEFAULT("default"),
    DISSOLVE("dissolve"),
    FADE("fade"),
    ROLL("roll"),
    SLIDE("slide"),
    ZOOM("zoom"),
    NONE("none");
    
    private String a;

    private TransitionStringEnum(String str) {
        this.a = str;
    }

    public final String getText() {
        return this.a;
    }

    public static TransitionStringEnum fromString(String str) {
        if (str != null) {
            for (TransitionStringEnum transitionStringEnum : values()) {
                if (str.equalsIgnoreCase(transitionStringEnum.a)) {
                    return transitionStringEnum;
                }
            }
        }
        return null;
    }
}
