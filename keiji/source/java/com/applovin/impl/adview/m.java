package com.applovin.impl.adview;

import android.util.AttributeSet;
import com.applovin.sdk.AppLovinAdSize;

class m {
    static AppLovinAdSize a(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue("http://schemas.applovin.com/android/1.0", "size");
        return attributeValue != null ? AppLovinAdSize.a(attributeValue) : null;
    }

    static boolean b(AttributeSet attributeSet) {
        return attributeSet != null && attributeSet.getAttributeBooleanValue("http://schemas.applovin.com/android/1.0", "loadAdOnCreate", false);
    }
}
