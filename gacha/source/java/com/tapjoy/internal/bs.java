package com.tapjoy.internal;

import com.applovin.sdk.AppLovinTargetingData;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.internal.br.a;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import jp.co.vaz.creator.hikaru.R;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.TwitterResponse;

public final class bs extends br {
    public static final a a = new a() {
        public final br a(Reader reader) {
            return new bs(reader);
        }

        public final br a(String str) {
            return new bs(new StringReader(str));
        }
    };
    private final cn b = new cn();
    private final Reader c;
    private boolean d = false;
    private final char[] e = new char[1024];
    private int f = 0;
    private int g = 0;
    private int h = 1;
    private int i = 1;
    private final List j = new ArrayList();
    private bw k;
    private String l;
    private String m;
    private int n;
    private int o;
    private boolean p;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[bu.values().length];

        static {
            try {
                a[bu.EMPTY_DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[bu.EMPTY_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[bu.NONEMPTY_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[bu.EMPTY_OBJECT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[bu.DANGLING_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[bu.NONEMPTY_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[bu.NONEMPTY_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[bu.CLOSED.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public bs(Reader reader) {
        a(bu.EMPTY_DOCUMENT);
        this.p = false;
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.c = reader;
    }

    public final void f() {
        a(bw.BEGIN_ARRAY);
    }

    public final void g() {
        a(bw.END_ARRAY);
    }

    public final void h() {
        a(bw.BEGIN_OBJECT);
    }

    public final void i() {
        a(bw.END_OBJECT);
    }

    private void a(bw bwVar) {
        k();
        if (this.k != bwVar) {
            throw new IllegalStateException("Expected " + bwVar + " but was " + k());
        }
        t();
    }

    public final boolean j() {
        k();
        return (this.k == bw.END_OBJECT || this.k == bw.END_ARRAY) ? false : true;
    }

    public final bw k() {
        bw v;
        if (this.k != null) {
            return this.k;
        }
        switch (AnonymousClass2.a[((bu) this.j.get(this.j.size() - 1)).ordinal()]) {
            case TwitterResponse.READ /*1*/:
                b(bu.NONEMPTY_DOCUMENT);
                v = v();
                if (this.d || this.k == bw.BEGIN_ARRAY || this.k == bw.BEGIN_OBJECT) {
                    return v;
                }
                throw new IOException("Expected JSON document to start with '[' or '{' but was " + this.k);
            case TwitterResponse.READ_WRITE /*2*/:
                return a(true);
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return a(false);
            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                return b(true);
            case AdInfo.BANNER_KIND_BANNER5 /*5*/:
                switch (y()) {
                    case R.styleable.AppCompatTheme_activityChooserViewStyle /*58*/:
                        break;
                    case R.styleable.AppCompatTheme_popupMenuStyle /*61*/:
                        z();
                        if ((this.f < this.g || a(1)) && this.e[this.f] == '>') {
                            this.f++;
                            break;
                        }
                    default:
                        throw d("Expected ':'");
                }
                b(bu.NONEMPTY_OBJECT);
                return v();
            case AdInfo.BANNER_KIND_BANNER6 /*6*/:
                return b(false);
            case AdInfo.BANNER_KIND_BANNER7 /*7*/:
                try {
                    v = v();
                    if (this.d) {
                        return v;
                    }
                    throw d("Expected EOF");
                } catch (EOFException e) {
                    v = bw.END_DOCUMENT;
                    this.k = v;
                    return v;
                }
            case AdInfo.BANNER_KIND_BANNER8 /*8*/:
                throw new IllegalStateException("JsonReader is closed");
            default:
                throw new AssertionError();
        }
    }

    private bw t() {
        k();
        bw bwVar = this.k;
        this.k = null;
        this.m = null;
        this.l = null;
        return bwVar;
    }

    public final String l() {
        k();
        if (this.k != bw.NAME) {
            throw new IllegalStateException("Expected a name but was " + k());
        }
        String str = this.l;
        t();
        return str;
    }

    public final String m() {
        k();
        if (this.k == bw.STRING || this.k == bw.NUMBER) {
            String str = this.m;
            t();
            return str;
        }
        throw new IllegalStateException("Expected a string but was " + k());
    }

    public final boolean n() {
        k();
        if (this.k != bw.BOOLEAN) {
            throw new IllegalStateException("Expected a boolean but was " + this.k);
        }
        boolean z = this.m == TapjoyConstants.TJC_TRUE;
        t();
        return z;
    }

    public final void o() {
        k();
        if (this.k != bw.NULL) {
            throw new IllegalStateException("Expected null but was " + this.k);
        }
        t();
    }

    public final double p() {
        k();
        if (this.k == bw.STRING || this.k == bw.NUMBER) {
            double parseDouble = Double.parseDouble(this.m);
            t();
            return parseDouble;
        }
        throw new IllegalStateException("Expected a double but was " + this.k);
    }

    public final long q() {
        k();
        if (this.k == bw.STRING || this.k == bw.NUMBER) {
            long parseLong;
            try {
                parseLong = Long.parseLong(this.m);
            } catch (NumberFormatException e) {
                double parseDouble = Double.parseDouble(this.m);
                parseLong = (long) parseDouble;
                if (((double) parseLong) != parseDouble) {
                    throw new NumberFormatException(this.m);
                }
            }
            t();
            return parseLong;
        }
        throw new IllegalStateException("Expected a long but was " + this.k);
    }

    public final int r() {
        k();
        if (this.k == bw.STRING || this.k == bw.NUMBER) {
            int parseInt;
            try {
                parseInt = Integer.parseInt(this.m);
            } catch (NumberFormatException e) {
                double parseDouble = Double.parseDouble(this.m);
                parseInt = (int) parseDouble;
                if (((double) parseInt) != parseDouble) {
                    throw new NumberFormatException(this.m);
                }
            }
            t();
            return parseInt;
        }
        throw new IllegalStateException("Expected an int but was " + this.k);
    }

    public final void close() {
        this.m = null;
        this.k = null;
        this.j.clear();
        this.j.add(bu.CLOSED);
        this.c.close();
    }

    public final void s() {
        k();
        if (this.k == bw.END_ARRAY || this.k == bw.END_OBJECT) {
            throw new IllegalStateException("Expected a value but was " + this.k);
        }
        this.p = true;
        int i = 0;
        while (true) {
            bw t = t();
            if (t == bw.BEGIN_ARRAY || t == bw.BEGIN_OBJECT) {
                i++;
                continue;
            } else {
                try {
                    if (t == bw.END_ARRAY || t == bw.END_OBJECT) {
                        i--;
                        continue;
                    }
                } finally {
                    this.p = false;
                }
            }
            if (i == 0) {
                break;
            }
        }
    }

    private bu u() {
        return (bu) this.j.remove(this.j.size() - 1);
    }

    private void a(bu buVar) {
        this.j.add(buVar);
    }

    private void b(bu buVar) {
        this.j.set(this.j.size() - 1, buVar);
    }

    private bw a(boolean z) {
        bw bwVar;
        if (z) {
            b(bu.NONEMPTY_ARRAY);
        } else {
            switch (y()) {
                case R.styleable.AppCompatTheme_dialogPreferredPadding /*44*/:
                    break;
                case R.styleable.AppCompatTheme_toolbarStyle /*59*/:
                    z();
                    break;
                case R.styleable.AppCompatTheme_alertDialogStyle /*93*/:
                    u();
                    bwVar = bw.END_ARRAY;
                    this.k = bwVar;
                    return bwVar;
                default:
                    throw d("Unterminated array");
            }
        }
        switch (y()) {
            case R.styleable.AppCompatTheme_dialogPreferredPadding /*44*/:
            case R.styleable.AppCompatTheme_toolbarStyle /*59*/:
                break;
            case R.styleable.AppCompatTheme_alertDialogStyle /*93*/:
                if (z) {
                    u();
                    bwVar = bw.END_ARRAY;
                    this.k = bwVar;
                    return bwVar;
                }
                break;
            default:
                this.f--;
                return v();
        }
        z();
        this.f--;
        this.m = "null";
        bwVar = bw.NULL;
        this.k = bwVar;
        return bwVar;
    }

    private bw b(boolean z) {
        bw bwVar;
        if (z) {
            switch (y()) {
                case 125:
                    u();
                    bwVar = bw.END_OBJECT;
                    this.k = bwVar;
                    return bwVar;
                default:
                    this.f--;
                    break;
            }
        }
        switch (y()) {
            case R.styleable.AppCompatTheme_dialogPreferredPadding /*44*/:
            case R.styleable.AppCompatTheme_toolbarStyle /*59*/:
                break;
            case 125:
                u();
                bwVar = bw.END_OBJECT;
                this.k = bwVar;
                return bwVar;
            default:
                throw d("Unterminated object");
        }
        int y = y();
        switch (y) {
            case R.styleable.AppCompatTheme_actionModePasteDrawable /*34*/:
                break;
            case R.styleable.AppCompatTheme_actionModePopupWindowStyle /*39*/:
                z();
                break;
            default:
                z();
                this.f--;
                this.l = c(false);
                if (this.l.length() == 0) {
                    throw d("Expected name");
                }
                break;
        }
        this.l = a((char) y);
        b(bu.DANGLING_NAME);
        bwVar = bw.NAME;
        this.k = bwVar;
        return bwVar;
    }

    private bw v() {
        bw bwVar;
        int y = y();
        switch (y) {
            case R.styleable.AppCompatTheme_actionModePasteDrawable /*34*/:
                break;
            case R.styleable.AppCompatTheme_actionModePopupWindowStyle /*39*/:
                z();
                break;
            case R.styleable.AppCompatTheme_controlBackground /*91*/:
                a(bu.EMPTY_ARRAY);
                bwVar = bw.BEGIN_ARRAY;
                this.k = bwVar;
                return bwVar;
            case 123:
                a(bu.EMPTY_OBJECT);
                bwVar = bw.BEGIN_OBJECT;
                this.k = bwVar;
                return bwVar;
            default:
                this.f--;
                this.m = c(true);
                if (this.o == 0) {
                    throw d("Expected literal value");
                }
                if (this.n != -1) {
                    if (this.o == 4 && (('n' == this.e[this.n] || 'N' == this.e[this.n]) && (('u' == this.e[this.n + 1] || 'U' == this.e[this.n + 1]) && (('l' == this.e[this.n + 2] || 'L' == this.e[this.n + 2]) && ('l' == this.e[this.n + 3] || 'L' == this.e[this.n + 3]))))) {
                        this.m = "null";
                        bwVar = bw.NULL;
                        this.k = bwVar;
                        if (this.k == bw.STRING) {
                            z();
                        }
                        return this.k;
                    } else if (this.o == 4 && (('t' == this.e[this.n] || 'T' == this.e[this.n]) && (('r' == this.e[this.n + 1] || 'R' == this.e[this.n + 1]) && (('u' == this.e[this.n + 2] || 'U' == this.e[this.n + 2]) && ('e' == this.e[this.n + 3] || 'E' == this.e[this.n + 3]))))) {
                        this.m = TapjoyConstants.TJC_TRUE;
                        bwVar = bw.BOOLEAN;
                        this.k = bwVar;
                        if (this.k == bw.STRING) {
                            z();
                        }
                        return this.k;
                    } else if (this.o == 5 && ((AppLovinTargetingData.GENDER_FEMALE == this.e[this.n] || 'F' == this.e[this.n]) && (('a' == this.e[this.n + 1] || 'A' == this.e[this.n + 1]) && (('l' == this.e[this.n + 2] || 'L' == this.e[this.n + 2]) && (('s' == this.e[this.n + 3] || 'S' == this.e[this.n + 3]) && ('e' == this.e[this.n + 4] || 'E' == this.e[this.n + 4])))))) {
                        this.m = TapjoyConstants.TJC_FALSE;
                        bwVar = bw.BOOLEAN;
                        this.k = bwVar;
                        if (this.k == bw.STRING) {
                            z();
                        }
                        return this.k;
                    } else {
                        int i;
                        this.m = this.b.a(this.e, this.n, this.o);
                        char[] cArr = this.e;
                        int i2 = this.n;
                        int i3 = this.o;
                        char c = cArr[i2];
                        if (c == '-') {
                            i = i2 + 1;
                            c = cArr[i];
                        } else {
                            i = i2;
                        }
                        if (c == '0') {
                            i++;
                            c = cArr[i];
                        } else if (c < '1' || c > '9') {
                            bwVar = bw.STRING;
                            this.k = bwVar;
                            if (this.k == bw.STRING) {
                                z();
                            }
                            return this.k;
                        } else {
                            i++;
                            c = cArr[i];
                            while (c >= '0' && c <= '9') {
                                i++;
                                c = cArr[i];
                            }
                        }
                        if (c == '.') {
                            i++;
                            c = cArr[i];
                            while (c >= '0' && c <= '9') {
                                i++;
                                c = cArr[i];
                            }
                        }
                        char c2 = c;
                        y = i;
                        char c3 = c2;
                        if (c3 == 'e' || c3 == 'E') {
                            i = y + 1;
                            c = cArr[i];
                            if (c == '+' || c == '-') {
                                i++;
                                c = cArr[i];
                            }
                            if (c < '0' || c > '9') {
                                bwVar = bw.STRING;
                                this.k = bwVar;
                                if (this.k == bw.STRING) {
                                    z();
                                }
                                return this.k;
                            }
                            i++;
                            y = i;
                            c3 = cArr[i];
                            while (c3 >= '0' && c3 <= '9') {
                                i = y + 1;
                                y = i;
                                c3 = cArr[i];
                            }
                        }
                        if (y == i2 + i3) {
                            bwVar = bw.NUMBER;
                            this.k = bwVar;
                            if (this.k == bw.STRING) {
                                z();
                            }
                            return this.k;
                        }
                    }
                }
                bwVar = bw.STRING;
                this.k = bwVar;
                if (this.k == bw.STRING) {
                    z();
                }
                return this.k;
        }
        this.m = a((char) y);
        bwVar = bw.STRING;
        this.k = bwVar;
        return bwVar;
    }

    private boolean a(int i) {
        int i2;
        for (i2 = 0; i2 < this.f; i2++) {
            if (this.e[i2] == '\n') {
                this.h++;
                this.i = 1;
            } else {
                this.i++;
            }
        }
        if (this.g != this.f) {
            this.g -= this.f;
            System.arraycopy(this.e, this.f, this.e, 0, this.g);
        } else {
            this.g = 0;
        }
        this.f = 0;
        do {
            i2 = this.c.read(this.e, this.g, this.e.length - this.g);
            if (i2 == -1) {
                return false;
            }
            this.g = i2 + this.g;
            if (this.h == 1 && this.i == 1 && this.g > 0 && this.e[0] == '\ufeff') {
                this.f++;
                this.i--;
            }
        } while (this.g < i);
        return true;
    }

    private int w() {
        int i = this.h;
        for (int i2 = 0; i2 < this.f; i2++) {
            if (this.e[i2] == '\n') {
                i++;
            }
        }
        return i;
    }

    private int x() {
        int i = this.i;
        for (int i2 = 0; i2 < this.f; i2++) {
            if (this.e[i2] == '\n') {
                i = 1;
            } else {
                i++;
            }
        }
        return i;
    }

    private int y() {
        while (true) {
            if (this.f < this.g || a(1)) {
                char[] cArr = this.e;
                int i = this.f;
                this.f = i + 1;
                char c = cArr[i];
                switch (c) {
                    case AdInfo.BANNER_KIND_INTERSTITIAL1 /*9*/:
                    case AdInfo.BANNER_KIND_WALL1 /*10*/:
                    case R.styleable.Toolbar_subtitleTextAppearance /*13*/:
                    case R.styleable.AppCompatTheme_actionModeCutDrawable /*32*/:
                        break;
                    case R.styleable.AppCompatTheme_actionModeSelectAllDrawable /*35*/:
                        z();
                        A();
                        continue;
                    case R.styleable.AppCompatTheme_dropdownListPreferredItemHeight /*47*/:
                        if (this.f == this.g && !a(1)) {
                            break;
                        }
                        z();
                        switch (this.e[this.f]) {
                            case R.styleable.AppCompatTheme_textAppearancePopupMenuHeader /*42*/:
                                this.f++;
                                String str = "*/";
                                while (true) {
                                    int i2;
                                    if (this.f + str.length() <= this.g || a(str.length())) {
                                        i2 = 0;
                                        while (i2 < str.length()) {
                                            if (this.e[this.f + i2] == str.charAt(i2)) {
                                                i2++;
                                            } else {
                                                this.f++;
                                            }
                                        }
                                        i2 = 1;
                                    } else {
                                        i2 = 0;
                                    }
                                    if (i2 == 0) {
                                        throw d("Unterminated comment");
                                    }
                                    this.f += 2;
                                    continue;
                                    continue;
                                }
                            case R.styleable.AppCompatTheme_dropdownListPreferredItemHeight /*47*/:
                                this.f++;
                                A();
                                continue;
                            default:
                                break;
                        }
                    default:
                        break;
                }
                return c;
            }
            throw new EOFException("End of input");
        }
    }

    private void z() {
        if (!this.d) {
            throw d("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void A() {
        char c;
        do {
            if (this.f < this.g || a(1)) {
                char[] cArr = this.e;
                int i = this.f;
                this.f = i + 1;
                c = cArr[i];
                if (c == '\r') {
                    return;
                }
            } else {
                return;
            }
        } while (c != '\n');
    }

    private String a(char c) {
        StringBuilder stringBuilder = null;
        do {
            int i = this.f;
            while (this.f < this.g) {
                char[] cArr = this.e;
                int i2 = this.f;
                this.f = i2 + 1;
                char c2 = cArr[i2];
                if (c2 != c) {
                    StringBuilder stringBuilder2;
                    int i3;
                    int i4;
                    if (c2 == '\\') {
                        if (stringBuilder == null) {
                            stringBuilder = new StringBuilder();
                        }
                        stringBuilder.append(this.e, i, (this.f - i) - 1);
                        if (this.f != this.g || a(1)) {
                            char[] cArr2 = this.e;
                            int i5 = this.f;
                            this.f = i5 + 1;
                            char c3 = cArr2[i5];
                            switch (c3) {
                                case R.styleable.AppCompatTheme_buttonBarPositiveButtonStyle /*98*/:
                                    c3 = '\b';
                                    break;
                                case R.styleable.AppCompatTheme_buttonStyle /*102*/:
                                    c3 = '\f';
                                    break;
                                case R.styleable.AppCompatTheme_ratingBarStyleSmall /*110*/:
                                    c3 = '\n';
                                    break;
                                case R.styleable.AppCompatTheme_listMenuViewStyle /*114*/:
                                    c3 = '\r';
                                    break;
                                case 't':
                                    c3 = '\t';
                                    break;
                                case 'u':
                                    if (this.f + 4 <= this.g || a(4)) {
                                        String a = this.b.a(this.e, this.f, 4);
                                        this.f += 4;
                                        c3 = (char) Integer.parseInt(a, 16);
                                        break;
                                    }
                                    throw d("Unterminated escape sequence");
                            }
                            stringBuilder.append(c3);
                            stringBuilder2 = stringBuilder;
                            i3 = this.f;
                        } else {
                            throw d("Unterminated escape sequence");
                        }
                    }
                    i4 = i;
                    stringBuilder2 = stringBuilder;
                    i3 = i4;
                    i4 = i3;
                    stringBuilder = stringBuilder2;
                    i = i4;
                } else if (this.p) {
                    return "skipped!";
                } else {
                    if (stringBuilder == null) {
                        return this.b.a(this.e, i, (this.f - i) - 1);
                    }
                    stringBuilder.append(this.e, i, (this.f - i) - 1);
                    return stringBuilder.toString();
                }
            }
            if (stringBuilder == null) {
                stringBuilder = new StringBuilder();
            }
            stringBuilder.append(this.e, i, this.f - i);
        } while (a(1));
        throw d("Unterminated string");
    }

    private String c(boolean z) {
        String str = null;
        this.n = -1;
        this.o = 0;
        int i = 0;
        StringBuilder stringBuilder = null;
        while (true) {
            if (this.f + i < this.g) {
                switch (this.e[this.f + i]) {
                    case AdInfo.BANNER_KIND_INTERSTITIAL1 /*9*/:
                    case AdInfo.BANNER_KIND_WALL1 /*10*/:
                    case Constants.MOVIE_REWARD_TYPE /*12*/:
                    case R.styleable.Toolbar_subtitleTextAppearance /*13*/:
                    case R.styleable.AppCompatTheme_actionModeCutDrawable /*32*/:
                    case R.styleable.AppCompatTheme_dialogPreferredPadding /*44*/:
                    case R.styleable.AppCompatTheme_activityChooserViewStyle /*58*/:
                    case R.styleable.AppCompatTheme_controlBackground /*91*/:
                    case R.styleable.AppCompatTheme_alertDialogStyle /*93*/:
                    case '{':
                    case '}':
                        break;
                    case R.styleable.AppCompatTheme_actionModeSelectAllDrawable /*35*/:
                    case R.styleable.AppCompatTheme_dropdownListPreferredItemHeight /*47*/:
                    case R.styleable.AppCompatTheme_toolbarStyle /*59*/:
                    case R.styleable.AppCompatTheme_popupMenuStyle /*61*/:
                    case R.styleable.AppCompatTheme_colorBackgroundFloating /*92*/:
                        z();
                        break;
                    default:
                        i++;
                        continue;
                }
            } else if (i >= this.e.length) {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder();
                }
                stringBuilder.append(this.e, this.f, i);
                this.o += i;
                this.f = i + this.f;
                if (a(1)) {
                    i = 0;
                } else {
                    i = 0;
                }
            } else if (!a(i + 1)) {
                this.e[this.g] = '\u0000';
            }
            if (z && stringBuilder == null) {
                this.n = this.f;
            } else if (this.p) {
                str = "skipped!";
            } else if (stringBuilder == null) {
                str = this.b.a(this.e, this.f, i);
            } else {
                stringBuilder.append(this.e, this.f, i);
                str = stringBuilder.toString();
            }
            this.o += i;
            this.f += i;
            return str;
        }
    }

    public final String toString() {
        StringBuilder append = new StringBuilder().append(getClass().getSimpleName()).append(" near ");
        StringBuilder stringBuilder = new StringBuilder();
        int min = Math.min(this.f, 20);
        stringBuilder.append(this.e, this.f - min, min);
        stringBuilder.append(this.e, this.f, Math.min(this.g - this.f, 20));
        return append.append(stringBuilder).toString();
    }

    private IOException d(String str) {
        throw new by(str + " at line " + w() + " column " + x());
    }
}
