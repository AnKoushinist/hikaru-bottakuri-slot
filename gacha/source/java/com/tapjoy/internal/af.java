package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;
import twitter4j.TwitterResponse;

public enum af {
    UNSPECIFIED,
    PORTRAIT,
    LANDSCAPE,
    SQUARE,
    NATURAL_PORTRAIT(PORTRAIT),
    RIGHT_LANDSCAPE(LANDSCAPE, NATURAL_PORTRAIT),
    REVERSE_PORTRAIT(PORTRAIT, NATURAL_PORTRAIT),
    LEFT_LANDSCAPE(LANDSCAPE, NATURAL_PORTRAIT),
    NATURAL_LANDSCAPE(LANDSCAPE),
    RIGHT_PORTRAIT(PORTRAIT, NATURAL_LANDSCAPE),
    REVERSE_LANDSCAPE(LANDSCAPE, NATURAL_LANDSCAPE),
    LEFT_PORTRAIT(PORTRAIT, NATURAL_LANDSCAPE),
    NATURAL_SQUARE(SQUARE),
    RIGHT_SQUARE(SQUARE, NATURAL_SQUARE),
    REVERSE_SQUARE(SQUARE, NATURAL_SQUARE),
    LEFT_SQUARE(SQUARE, NATURAL_SQUARE);
    
    private final af q;
    private final af r;

    private af(af afVar) {
        this.q = afVar;
        this.r = this;
    }

    private af(af afVar, af afVar2) {
        this.q = afVar;
        this.r = afVar2;
    }

    public final boolean a() {
        return this == PORTRAIT || this.q == PORTRAIT;
    }

    public final boolean b() {
        return this == LANDSCAPE || this.q == LANDSCAPE;
    }

    public final int c() {
        return this.r != null ? ordinal() - this.r.ordinal() : 0;
    }

    public static af a(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int rotation = defaultDisplay.getRotation();
        Point point = new Point();
        if (VERSION.SDK_INT >= 13) {
            defaultDisplay.getSize(point);
        } else {
            point.x = defaultDisplay.getWidth();
            point.y = defaultDisplay.getHeight();
        }
        if (point.x < point.y) {
            switch (rotation & 3) {
                case TwitterResponse.READ /*1*/:
                    return RIGHT_PORTRAIT;
                case TwitterResponse.READ_WRITE /*2*/:
                    return REVERSE_PORTRAIT;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    return LEFT_PORTRAIT;
                default:
                    return NATURAL_PORTRAIT;
            }
        } else if (point.x > point.y) {
            switch (rotation & 3) {
                case TwitterResponse.READ /*1*/:
                    return RIGHT_LANDSCAPE;
                case TwitterResponse.READ_WRITE /*2*/:
                    return REVERSE_LANDSCAPE;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    return LEFT_LANDSCAPE;
                default:
                    return NATURAL_LANDSCAPE;
            }
        } else {
            switch (rotation & 3) {
                case TwitterResponse.READ /*1*/:
                    return RIGHT_SQUARE;
                case TwitterResponse.READ_WRITE /*2*/:
                    return REVERSE_SQUARE;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                    return LEFT_SQUARE;
                default:
                    return NATURAL_SQUARE;
            }
        }
    }

    public static af b(Context context) {
        switch (context.getResources().getConfiguration().orientation) {
            case TwitterResponse.READ /*1*/:
                return PORTRAIT;
            case TwitterResponse.READ_WRITE /*2*/:
                return LANDSCAPE;
            default:
                return UNSPECIFIED;
        }
    }
}
