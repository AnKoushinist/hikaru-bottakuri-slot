package com.tapjoy.internal;

import com.tapjoy.TapjoyConstants;
import com.tapjoy.internal.bs.a;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public final class bt extends bs {
    public static final a a = new a() {
        public final bs a(Reader reader) {
            return new bt(reader);
        }

        public final bs a(String str) {
            return new bt(new StringReader(str));
        }
    };
    private final co b = new co();
    private final Reader c;
    private boolean d = false;
    private final char[] e = new char[1024];
    private int f = 0;
    private int g = 0;
    private int h = 1;
    private int i = 1;
    private final List j = new ArrayList();
    private bx k;
    private String l;
    private String m;
    private int n;
    private int o;
    private boolean p;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[bv.values().length];

        static {
            try {
                a[bv.EMPTY_DOCUMENT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[bv.EMPTY_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[bv.NONEMPTY_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[bv.EMPTY_OBJECT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[bv.DANGLING_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[bv.NONEMPTY_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[bv.NONEMPTY_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[bv.CLOSED.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public bt(Reader reader) {
        a(bv.EMPTY_DOCUMENT);
        this.p = false;
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.c = reader;
    }

    public final void f() {
        a(bx.BEGIN_ARRAY);
    }

    public final void g() {
        a(bx.END_ARRAY);
    }

    public final void h() {
        a(bx.BEGIN_OBJECT);
    }

    public final void i() {
        a(bx.END_OBJECT);
    }

    private void a(bx bxVar) {
        k();
        if (this.k != bxVar) {
            throw new IllegalStateException("Expected " + bxVar + " but was " + k());
        }
        t();
    }

    public final boolean j() {
        k();
        return (this.k == bx.END_OBJECT || this.k == bx.END_ARRAY) ? false : true;
    }

    public final bx k() {
        bx v;
        if (this.k != null) {
            return this.k;
        }
        switch (AnonymousClass2.a[((bv) this.j.get(this.j.size() - 1)).ordinal()]) {
            case TwitterResponse.READ /*1*/:
                b(bv.NONEMPTY_DOCUMENT);
                v = v();
                if (this.d || this.k == bx.BEGIN_ARRAY || this.k == bx.BEGIN_OBJECT) {
                    return v;
                }
                throw new IOException("Expected JSON document to start with '[' or '{' but was " + this.k);
            case TwitterResponse.READ_WRITE /*2*/:
                return a(true);
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return a(false);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsTextAppearance /*4*/:
                return b(true);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsHeaderTextAppearance /*5*/:
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
                b(bv.NONEMPTY_OBJECT);
                return v();
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return b(false);
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                try {
                    v = v();
                    if (this.d) {
                        return v;
                    }
                    throw d("Expected EOF");
                } catch (EOFException e) {
                    v = bx.END_DOCUMENT;
                    this.k = v;
                    return v;
                }
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                throw new IllegalStateException("JsonReader is closed");
            default:
                throw new AssertionError();
        }
    }

    private bx t() {
        k();
        bx bxVar = this.k;
        this.k = null;
        this.m = null;
        this.l = null;
        return bxVar;
    }

    public final String l() {
        k();
        if (this.k != bx.NAME) {
            throw new IllegalStateException("Expected a name but was " + k());
        }
        String str = this.l;
        t();
        return str;
    }

    public final String m() {
        k();
        if (this.k == bx.STRING || this.k == bx.NUMBER) {
            String str = this.m;
            t();
            return str;
        }
        throw new IllegalStateException("Expected a string but was " + k());
    }

    public final boolean n() {
        k();
        if (this.k != bx.BOOLEAN) {
            throw new IllegalStateException("Expected a boolean but was " + this.k);
        }
        boolean z = this.m == TapjoyConstants.TJC_TRUE;
        t();
        return z;
    }

    public final void o() {
        k();
        if (this.k != bx.NULL) {
            throw new IllegalStateException("Expected null but was " + this.k);
        }
        t();
    }

    public final double p() {
        k();
        if (this.k == bx.STRING || this.k == bx.NUMBER) {
            double parseDouble = Double.parseDouble(this.m);
            t();
            return parseDouble;
        }
        throw new IllegalStateException("Expected a double but was " + this.k);
    }

    public final long q() {
        long parseLong;
        k();
        if (this.k == bx.STRING || this.k == bx.NUMBER) {
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
        if (this.k == bx.STRING || this.k == bx.NUMBER) {
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
        this.j.add(bv.CLOSED);
        this.c.close();
    }

    public final void s() {
        k();
        if (this.k == bx.END_ARRAY || this.k == bx.END_OBJECT) {
            throw new IllegalStateException("Expected a value but was " + this.k);
        }
        this.p = true;
        int i = 0;
        while (true) {
            bx t = t();
            if (t == bx.BEGIN_ARRAY || t == bx.BEGIN_OBJECT) {
                i++;
                continue;
            } else {
                try {
                    if (t == bx.END_ARRAY || t == bx.END_OBJECT) {
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

    private bv u() {
        return (bv) this.j.remove(this.j.size() - 1);
    }

    private void a(bv bvVar) {
        this.j.add(bvVar);
    }

    private void b(bv bvVar) {
        this.j.set(this.j.size() - 1, bvVar);
    }

    private bx a(boolean z) {
        bx bxVar;
        if (z) {
            b(bv.NONEMPTY_ARRAY);
        } else {
            switch (y()) {
                case R.styleable.AppCompatTheme_dialogPreferredPadding /*44*/:
                    break;
                case R.styleable.AppCompatTheme_toolbarStyle /*59*/:
                    z();
                    break;
                case R.styleable.AppCompatTheme_alertDialogStyle /*93*/:
                    u();
                    bxVar = bx.END_ARRAY;
                    this.k = bxVar;
                    return bxVar;
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
                    bxVar = bx.END_ARRAY;
                    this.k = bxVar;
                    return bxVar;
                }
                break;
            default:
                this.f--;
                return v();
        }
        z();
        this.f--;
        this.m = "null";
        bxVar = bx.NULL;
        this.k = bxVar;
        return bxVar;
    }

    private bx b(boolean z) {
        bx bxVar;
        if (z) {
            switch (y()) {
                case 125:
                    u();
                    bxVar = bx.END_OBJECT;
                    this.k = bxVar;
                    return bxVar;
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
                bxVar = bx.END_OBJECT;
                this.k = bxVar;
                return bxVar;
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
        b(bv.DANGLING_NAME);
        bxVar = bx.NAME;
        this.k = bxVar;
        return bxVar;
    }

    private bx v() {
        bx bxVar;
        int y = y();
        switch (y) {
            case R.styleable.AppCompatTheme_actionModePasteDrawable /*34*/:
                break;
            case R.styleable.AppCompatTheme_actionModePopupWindowStyle /*39*/:
                z();
                break;
            case R.styleable.AppCompatTheme_controlBackground /*91*/:
                a(bv.EMPTY_ARRAY);
                bxVar = bx.BEGIN_ARRAY;
                this.k = bxVar;
                return bxVar;
            case 123:
                a(bv.EMPTY_OBJECT);
                bxVar = bx.BEGIN_OBJECT;
                this.k = bxVar;
                return bxVar;
            default:
                this.f--;
                this.m = c(true);
                if (this.o == 0) {
                    throw d("Expected literal value");
                }
                if (this.n != -1) {
                    if (this.o == 4 && (('n' == this.e[this.n] || 'N' == this.e[this.n]) && (('u' == this.e[this.n + 1] || 'U' == this.e[this.n + 1]) && (('l' == this.e[this.n + 2] || 'L' == this.e[this.n + 2]) && ('l' == this.e[this.n + 3] || 'L' == this.e[this.n + 3]))))) {
                        this.m = "null";
                        bxVar = bx.NULL;
                        this.k = bxVar;
                        if (this.k == bx.STRING) {
                            z();
                        }
                        return this.k;
                    } else if (this.o == 4 && (('t' == this.e[this.n] || 'T' == this.e[this.n]) && (('r' == this.e[this.n + 1] || 'R' == this.e[this.n + 1]) && (('u' == this.e[this.n + 2] || 'U' == this.e[this.n + 2]) && ('e' == this.e[this.n + 3] || 'E' == this.e[this.n + 3]))))) {
                        this.m = TapjoyConstants.TJC_TRUE;
                        bxVar = bx.BOOLEAN;
                        this.k = bxVar;
                        if (this.k == bx.STRING) {
                            z();
                        }
                        return this.k;
                    } else if (this.o == 5 && (('f' == this.e[this.n] || 'F' == this.e[this.n]) && (('a' == this.e[this.n + 1] || 'A' == this.e[this.n + 1]) && (('l' == this.e[this.n + 2] || 'L' == this.e[this.n + 2]) && (('s' == this.e[this.n + 3] || 'S' == this.e[this.n + 3]) && ('e' == this.e[this.n + 4] || 'E' == this.e[this.n + 4])))))) {
                        this.m = TapjoyConstants.TJC_FALSE;
                        bxVar = bx.BOOLEAN;
                        this.k = bxVar;
                        if (this.k == bx.STRING) {
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
                            bxVar = bx.STRING;
                            this.k = bxVar;
                            if (this.k == bx.STRING) {
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
                                bxVar = bx.STRING;
                                this.k = bxVar;
                                if (this.k == bx.STRING) {
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
                            bxVar = bx.NUMBER;
                            this.k = bxVar;
                            if (this.k == bx.STRING) {
                                z();
                            }
                            return this.k;
                        }
                    }
                }
                bxVar = bx.STRING;
                this.k = bxVar;
                if (this.k == bx.STRING) {
                    z();
                }
                return this.k;
        }
        this.m = a((char) y);
        bxVar = bx.STRING;
        this.k = bxVar;
        return bxVar;
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
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
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
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                    case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoImageType /*10*/:
                    case R.styleable.Toolbar_titleTextAppearance /*12*/:
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
        throw new bz(str + " at line " + w() + " column " + x());
    }
}
