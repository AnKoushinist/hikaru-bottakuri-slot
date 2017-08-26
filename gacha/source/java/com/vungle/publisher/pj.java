package com.vungle.publisher;

/* compiled from: vungle */
public enum pj {
    GINGERBREAD(9),
    HONEYCOMB(11),
    HONEYCOMB_MR2(13),
    JELLY_BEAN(16),
    JELLY_BEAN_MR1(17),
    JELLY_BEAN_MR2(18),
    KITKAT(19),
    LOLLIPOP(21),
    MARSHMALLOW(23),
    NOUGAT(24);
    
    public int k;

    private pj(int i) {
        this.k = i;
    }
}
