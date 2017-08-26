package com.google.a.d;

import com.applovin.sdk.AppLovinTargetingData;
import com.google.a.b.e;
import com.tapjoy.TapjoyConstants;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import jp.co.vaz.creator.hikaru.R;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import twitter4j.MediaEntity.Size;
import twitter4j.TwitterResponse;

/* compiled from: JsonReader */
public class a implements Closeable {
    private static final char[] b = ")]}'\n".toCharArray();
    int a = 0;
    private final Reader c;
    private boolean d = false;
    private final char[] e = new char[1024];
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private long j;
    private int k;
    private String l;
    private int[] m = new int[32];
    private int n = 0;
    private String[] o;
    private int[] p;

    static {
        e.a = new e() {
            public void a(a aVar) {
                if (aVar instanceof com.google.a.b.a.e) {
                    ((com.google.a.b.a.e) aVar).o();
                    return;
                }
                int i = aVar.a;
                if (i == 0) {
                    i = aVar.q();
                }
                if (i == 13) {
                    aVar.a = 9;
                } else if (i == 12) {
                    aVar.a = 8;
                } else if (i == 14) {
                    aVar.a = 10;
                } else {
                    throw new IllegalStateException("Expected a name but was " + aVar.f() + " " + " at line " + aVar.r() + " column " + aVar.s() + " path " + aVar.t());
                }
            }
        };
    }

    public a(Reader reader) {
        int[] iArr = this.m;
        int i = this.n;
        this.n = i + 1;
        iArr[i] = 6;
        this.o = new String[32];
        this.p = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.c = reader;
    }

    public final void a(boolean z) {
        this.d = z;
    }

    public final boolean p() {
        return this.d;
    }

    public void a() {
        int i = this.a;
        if (i == 0) {
            i = q();
        }
        if (i == 3) {
            a(1);
            this.p[this.n - 1] = 0;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + f() + " at line " + r() + " column " + s() + " path " + t());
    }

    public void b() {
        int i = this.a;
        if (i == 0) {
            i = q();
        }
        if (i == 4) {
            this.n--;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + f() + " at line " + r() + " column " + s() + " path " + t());
    }

    public void c() {
        int i = this.a;
        if (i == 0) {
            i = q();
        }
        if (i == 1) {
            a(3);
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + f() + " at line " + r() + " column " + s() + " path " + t());
    }

    public void d() {
        int i = this.a;
        if (i == 0) {
            i = q();
        }
        if (i == 2) {
            this.n--;
            this.o[this.n] = null;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + f() + " at line " + r() + " column " + s() + " path " + t());
    }

    public boolean e() {
        int i = this.a;
        if (i == 0) {
            i = q();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    public b f() {
        int i = this.a;
        if (i == 0) {
            i = q();
        }
        switch (i) {
            case TwitterResponse.READ /*1*/:
                return b.BEGIN_OBJECT;
            case TwitterResponse.READ_WRITE /*2*/:
                return b.END_OBJECT;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                return b.BEGIN_ARRAY;
            case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                return b.END_ARRAY;
            case AdInfo.BANNER_KIND_BANNER5 /*5*/:
            case AdInfo.BANNER_KIND_BANNER6 /*6*/:
                return b.BOOLEAN;
            case AdInfo.BANNER_KIND_BANNER7 /*7*/:
                return b.NULL;
            case AdInfo.BANNER_KIND_BANNER8 /*8*/:
            case AdInfo.BANNER_KIND_INTERSTITIAL1 /*9*/:
            case AdInfo.BANNER_KIND_WALL1 /*10*/:
            case AdInfo.BANNER_KIND_NATIVE1 /*11*/:
                return b.STRING;
            case Constants.MOVIE_REWARD_TYPE /*12*/:
            case R.styleable.Toolbar_subtitleTextAppearance /*13*/:
            case Constants.MOVIE_INTER_TYPE /*14*/:
                return b.NAME;
            case R.styleable.Toolbar_titleMarginStart /*15*/:
            case R.styleable.Toolbar_titleMarginEnd /*16*/:
                return b.NUMBER;
            case R.styleable.Toolbar_titleMarginTop /*17*/:
                return b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    int q() {
        int b;
        int i = this.m[this.n - 1];
        if (i == 1) {
            this.m[this.n - 1] = 2;
        } else if (i == 2) {
            switch (b(true)) {
                case R.styleable.AppCompatTheme_dialogPreferredPadding /*44*/:
                    break;
                case R.styleable.AppCompatTheme_toolbarStyle /*59*/:
                    x();
                    break;
                case R.styleable.AppCompatTheme_alertDialogStyle /*93*/:
                    this.a = 4;
                    return 4;
                default:
                    throw b("Unterminated array");
            }
        } else if (i == 3 || i == 5) {
            this.m[this.n - 1] = 4;
            if (i == 5) {
                switch (b(true)) {
                    case R.styleable.AppCompatTheme_dialogPreferredPadding /*44*/:
                        break;
                    case R.styleable.AppCompatTheme_toolbarStyle /*59*/:
                        x();
                        break;
                    case 125:
                        this.a = 2;
                        return 2;
                    default:
                        throw b("Unterminated object");
                }
            }
            b = b(true);
            switch (b) {
                case R.styleable.AppCompatTheme_actionModePasteDrawable /*34*/:
                    this.a = 13;
                    return 13;
                case R.styleable.AppCompatTheme_actionModePopupWindowStyle /*39*/:
                    x();
                    this.a = 12;
                    return 12;
                case 125:
                    if (i != 5) {
                        this.a = 2;
                        return 2;
                    }
                    throw b("Expected name");
                default:
                    x();
                    this.f--;
                    if (a((char) b)) {
                        this.a = 14;
                        return 14;
                    }
                    throw b("Expected name");
            }
        } else if (i == 4) {
            this.m[this.n - 1] = 5;
            switch (b(true)) {
                case R.styleable.AppCompatTheme_activityChooserViewStyle /*58*/:
                    break;
                case R.styleable.AppCompatTheme_popupMenuStyle /*61*/:
                    x();
                    if ((this.f < this.g || b(1)) && this.e[this.f] == '>') {
                        this.f++;
                        break;
                    }
                default:
                    throw b("Expected ':'");
            }
        } else if (i == 6) {
            if (this.d) {
                A();
            }
            this.m[this.n - 1] = 7;
        } else if (i == 7) {
            if (b(false) == -1) {
                this.a = 17;
                return 17;
            }
            x();
            this.f--;
        } else if (i == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        switch (b(true)) {
            case R.styleable.AppCompatTheme_actionModePasteDrawable /*34*/:
                this.a = 9;
                return 9;
            case R.styleable.AppCompatTheme_actionModePopupWindowStyle /*39*/:
                x();
                this.a = 8;
                return 8;
            case R.styleable.AppCompatTheme_dialogPreferredPadding /*44*/:
            case R.styleable.AppCompatTheme_toolbarStyle /*59*/:
                break;
            case R.styleable.AppCompatTheme_controlBackground /*91*/:
                this.a = 3;
                return 3;
            case R.styleable.AppCompatTheme_alertDialogStyle /*93*/:
                if (i == 1) {
                    this.a = 4;
                    return 4;
                }
                break;
            case 123:
                this.a = 1;
                return 1;
            default:
                this.f--;
                b = o();
                if (b != 0) {
                    return b;
                }
                b = u();
                if (b != 0) {
                    return b;
                }
                if (a(this.e[this.f])) {
                    x();
                    this.a = 10;
                    return 10;
                }
                throw b("Expected value");
        }
        if (i == 1 || i == 2) {
            x();
            this.f--;
            this.a = 7;
            return 7;
        }
        throw b("Unexpected value");
    }

    private int o() {
        String str;
        int i;
        char c = this.e[this.f];
        String str2;
        if (c == 't' || c == 'T') {
            str = TapjoyConstants.TJC_TRUE;
            str2 = "TRUE";
            i = 5;
        } else if (c == AppLovinTargetingData.GENDER_FEMALE || c == 'F') {
            str = TapjoyConstants.TJC_FALSE;
            str2 = "FALSE";
            i = 6;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            str = "null";
            str2 = "NULL";
            i = 7;
        }
        int length = str.length();
        int i2 = 1;
        while (i2 < length) {
            if (this.f + i2 >= this.g && !b(i2 + 1)) {
                return 0;
            }
            char c2 = this.e[this.f + i2];
            if (c2 != str.charAt(i2) && c2 != r1.charAt(i2)) {
                return 0;
            }
            i2++;
        }
        if ((this.f + length < this.g || b(length + 1)) && a(this.e[this.f + length])) {
            return 0;
        }
        this.f += length;
        this.a = i;
        return i;
    }

    private int u() {
        char[] cArr = this.e;
        int i = this.f;
        long j = 0;
        Object obj = null;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        int i5 = this.g;
        int i6 = i;
        while (true) {
            Object obj2;
            if (i6 + i4 == i5) {
                if (i4 == cArr.length) {
                    return 0;
                }
                if (b(i4 + 1)) {
                    i6 = this.f;
                    i5 = this.g;
                } else if (i3 != 2 && i2 != 0 && (j != Long.MIN_VALUE || obj != null)) {
                    if (obj == null) {
                        j = -j;
                    }
                    this.j = j;
                    this.f += i4;
                    this.a = 15;
                    return 15;
                } else if (i3 == 2 && i3 != 4 && i3 != 7) {
                    return 0;
                } else {
                    this.k = i4;
                    this.a = 16;
                    return 16;
                }
            }
            char c = cArr[i6 + i4];
            int i7;
            switch (c) {
                case R.styleable.AppCompatTheme_dialogTheme /*43*/:
                    if (i3 != 5) {
                        return 0;
                    }
                    i = 6;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                case R.styleable.AppCompatTheme_listDividerAlertDialog /*45*/:
                    if (i3 == 0) {
                        i = 1;
                        i7 = i2;
                        obj2 = 1;
                        i3 = i7;
                        continue;
                    } else if (i3 == 5) {
                        i = 6;
                        i3 = i2;
                        obj2 = obj;
                        break;
                    } else {
                        return 0;
                    }
                case R.styleable.AppCompatTheme_actionDropDownStyle /*46*/:
                    if (i3 != 2) {
                        return 0;
                    }
                    i = 3;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                case R.styleable.AppCompatTheme_searchViewStyle /*69*/:
                case Size.CROP /*101*/:
                    if (i3 != 2 && i3 != 4) {
                        return 0;
                    }
                    i = 5;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                default:
                    if (c >= '0' && c <= '9') {
                        if (i3 != 1 && i3 != 0) {
                            if (i3 != 2) {
                                if (i3 != 3) {
                                    if (i3 != 5 && i3 != 6) {
                                        i = i3;
                                        i3 = i2;
                                        obj2 = obj;
                                        break;
                                    }
                                    i = 7;
                                    i3 = i2;
                                    obj2 = obj;
                                    break;
                                }
                                i = 4;
                                i3 = i2;
                                obj2 = obj;
                                break;
                            } else if (j != 0) {
                                long j2 = (10 * j) - ((long) (c - 48));
                                i = (j > -922337203685477580L || (j == -922337203685477580L && j2 < j)) ? 1 : 0;
                                i &= i2;
                                obj2 = obj;
                                j = j2;
                                i7 = i3;
                                i3 = i;
                                i = i7;
                                break;
                            } else {
                                return 0;
                            }
                        }
                        j = (long) (-(c - 48));
                        i = 2;
                        i3 = i2;
                        obj2 = obj;
                        continue;
                    } else if (a(c)) {
                        return 0;
                    }
                    break;
            }
            if (i3 != 2) {
            }
            if (i3 == 2) {
            }
            this.k = i4;
            this.a = 16;
            return 16;
            i4++;
            obj = obj2;
            i2 = i3;
            i3 = i;
        }
    }

    private boolean a(char c) {
        switch (c) {
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
                x();
                break;
            default:
                return true;
        }
        return false;
    }

    public String g() {
        String v;
        int i = this.a;
        if (i == 0) {
            i = q();
        }
        if (i == 14) {
            v = v();
        } else if (i == 12) {
            v = b('\'');
        } else if (i == 13) {
            v = b('\"');
        } else {
            throw new IllegalStateException("Expected a name but was " + f() + " at line " + r() + " column " + s() + " path " + t());
        }
        this.a = 0;
        this.o[this.n - 1] = v;
        return v;
    }

    public String h() {
        String v;
        int i = this.a;
        if (i == 0) {
            i = q();
        }
        if (i == 10) {
            v = v();
        } else if (i == 8) {
            v = b('\'');
        } else if (i == 9) {
            v = b('\"');
        } else if (i == 11) {
            v = this.l;
            this.l = null;
        } else if (i == 15) {
            v = Long.toString(this.j);
        } else if (i == 16) {
            v = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else {
            throw new IllegalStateException("Expected a string but was " + f() + " at line " + r() + " column " + s() + " path " + t());
        }
        this.a = 0;
        int[] iArr = this.p;
        int i2 = this.n - 1;
        iArr[i2] = iArr[i2] + 1;
        return v;
    }

    public boolean i() {
        int i = this.a;
        if (i == 0) {
            i = q();
        }
        if (i == 5) {
            this.a = 0;
            int[] iArr = this.p;
            i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        } else if (i == 6) {
            this.a = 0;
            int[] iArr2 = this.p;
            int i2 = this.n - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + f() + " at line " + r() + " column " + s() + " path " + t());
        }
    }

    public void j() {
        int i = this.a;
        if (i == 0) {
            i = q();
        }
        if (i == 7) {
            this.a = 0;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + f() + " at line " + r() + " column " + s() + " path " + t());
    }

    public double k() {
        int i = this.a;
        if (i == 0) {
            i = q();
        }
        if (i == 15) {
            this.a = 0;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return (double) this.j;
        }
        if (i == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i == 8 || i == 9) {
            this.l = b(i == 8 ? '\'' : '\"');
        } else if (i == 10) {
            this.l = v();
        } else if (i != 11) {
            throw new IllegalStateException("Expected a double but was " + f() + " at line " + r() + " column " + s() + " path " + t());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        if (this.d || !(Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            this.l = null;
            this.a = 0;
            int[] iArr2 = this.p;
            int i3 = this.n - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return parseDouble;
        }
        throw new d("JSON forbids NaN and infinities: " + parseDouble + " at line " + r() + " column " + s() + " path " + t());
    }

    public long l() {
        int i = this.a;
        if (i == 0) {
            i = q();
        }
        if (i == 15) {
            this.a = 0;
            int[] iArr = this.p;
            int i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.j;
        }
        long parseLong;
        if (i == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i == 8 || i == 9) {
            this.l = b(i == 8 ? '\'' : '\"');
            try {
                parseLong = Long.parseLong(this.l);
                this.a = 0;
                int[] iArr2 = this.p;
                int i3 = this.n - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException e) {
            }
        } else {
            throw new IllegalStateException("Expected a long but was " + f() + " at line " + r() + " column " + s() + " path " + t());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        parseLong = (long) parseDouble;
        if (((double) parseLong) != parseDouble) {
            throw new NumberFormatException("Expected a long but was " + this.l + " at line " + r() + " column " + s() + " path " + t());
        }
        this.l = null;
        this.a = 0;
        iArr2 = this.p;
        i3 = this.n - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return parseLong;
    }

    private String b(char c) {
        char[] cArr = this.e;
        StringBuilder stringBuilder = new StringBuilder();
        do {
            int i = this.f;
            int i2 = this.g;
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.f = i4;
                    stringBuilder.append(cArr, i, (i4 - i) - 1);
                    return stringBuilder.toString();
                }
                if (c2 == '\\') {
                    this.f = i4;
                    stringBuilder.append(cArr, i, (i4 - i) - 1);
                    stringBuilder.append(z());
                    i = this.f;
                    i2 = this.g;
                    i4 = i;
                } else if (c2 == '\n') {
                    this.h++;
                    this.i = i4;
                }
                i3 = i4;
            }
            stringBuilder.append(cArr, i, i3 - i);
            this.f = i3;
        } while (b(1));
        throw b("Unterminated string");
    }

    private String v() {
        StringBuilder stringBuilder = null;
        int i = 0;
        while (true) {
            String str;
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
                        x();
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
                this.f = i + this.f;
                if (b(1)) {
                    i = 0;
                } else {
                    i = 0;
                }
            } else if (b(i + 1)) {
            }
            if (stringBuilder == null) {
                str = new String(this.e, this.f, i);
            } else {
                stringBuilder.append(this.e, this.f, i);
                str = stringBuilder.toString();
            }
            this.f = i + this.f;
            return str;
        }
    }

    private void c(char c) {
        char[] cArr = this.e;
        do {
            int i = this.f;
            int i2 = this.g;
            int i3 = i;
            while (i3 < i2) {
                i = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.f = i;
                    return;
                }
                if (c2 == '\\') {
                    this.f = i;
                    z();
                    i = this.f;
                    i2 = this.g;
                } else if (c2 == '\n') {
                    this.h++;
                    this.i = i;
                }
                i3 = i;
            }
            this.f = i3;
        } while (b(1));
        throw b("Unterminated string");
    }

    private void w() {
        do {
            int i = 0;
            while (this.f + i < this.g) {
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
                        x();
                        break;
                    default:
                        i++;
                }
                this.f = i + this.f;
                return;
            }
            this.f = i + this.f;
        } while (b(1));
    }

    public int m() {
        int i = this.a;
        if (i == 0) {
            i = q();
        }
        int[] iArr;
        int i2;
        if (i == 15) {
            i = (int) this.j;
            if (this.j != ((long) i)) {
                throw new NumberFormatException("Expected an int but was " + this.j + " at line " + r() + " column " + s() + " path " + t());
            }
            this.a = 0;
            iArr = this.p;
            i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
        } else {
            if (i == 16) {
                this.l = new String(this.e, this.f, this.k);
                this.f += this.k;
            } else if (i == 8 || i == 9) {
                this.l = b(i == 8 ? '\'' : '\"');
                try {
                    i = Integer.parseInt(this.l);
                    this.a = 0;
                    iArr = this.p;
                    i2 = this.n - 1;
                    iArr[i2] = iArr[i2] + 1;
                } catch (NumberFormatException e) {
                }
            } else {
                throw new IllegalStateException("Expected an int but was " + f() + " at line " + r() + " column " + s() + " path " + t());
            }
            this.a = 11;
            double parseDouble = Double.parseDouble(this.l);
            i = (int) parseDouble;
            if (((double) i) != parseDouble) {
                throw new NumberFormatException("Expected an int but was " + this.l + " at line " + r() + " column " + s() + " path " + t());
            }
            this.l = null;
            this.a = 0;
            iArr = this.p;
            i2 = this.n - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return i;
    }

    public void close() {
        this.a = 0;
        this.m[0] = 8;
        this.n = 1;
        this.c.close();
    }

    public void n() {
        int i = 0;
        do {
            int i2 = this.a;
            if (i2 == 0) {
                i2 = q();
            }
            if (i2 == 3) {
                a(1);
                i++;
            } else if (i2 == 1) {
                a(3);
                i++;
            } else if (i2 == 4) {
                this.n--;
                i--;
            } else if (i2 == 2) {
                this.n--;
                i--;
            } else if (i2 == 14 || i2 == 10) {
                w();
            } else if (i2 == 8 || i2 == 12) {
                c('\'');
            } else if (i2 == 9 || i2 == 13) {
                c('\"');
            } else if (i2 == 16) {
                this.f += this.k;
            }
            this.a = 0;
        } while (i != 0);
        int[] iArr = this.p;
        int i3 = this.n - 1;
        iArr[i3] = iArr[i3] + 1;
        this.o[this.n - 1] = "null";
    }

    private void a(int i) {
        if (this.n == this.m.length) {
            Object obj = new int[(this.n * 2)];
            Object obj2 = new int[(this.n * 2)];
            Object obj3 = new String[(this.n * 2)];
            System.arraycopy(this.m, 0, obj, 0, this.n);
            System.arraycopy(this.p, 0, obj2, 0, this.n);
            System.arraycopy(this.o, 0, obj3, 0, this.n);
            this.m = obj;
            this.p = obj2;
            this.o = obj3;
        }
        int[] iArr = this.m;
        int i2 = this.n;
        this.n = i2 + 1;
        iArr[i2] = i;
    }

    private boolean b(int i) {
        Object obj = this.e;
        this.i -= this.f;
        if (this.g != this.f) {
            this.g -= this.f;
            System.arraycopy(obj, this.f, obj, 0, this.g);
        } else {
            this.g = 0;
        }
        this.f = 0;
        do {
            int read = this.c.read(obj, this.g, obj.length - this.g);
            if (read == -1) {
                return false;
            }
            this.g = read + this.g;
            if (this.h == 0 && this.i == 0 && this.g > 0 && obj[0] == '\ufeff') {
                this.f++;
                this.i++;
                i++;
            }
        } while (this.g < i);
        return true;
    }

    int r() {
        return this.h + 1;
    }

    int s() {
        return (this.f - this.i) + 1;
    }

    private int b(boolean z) {
        char[] cArr = this.e;
        int i = this.f;
        int i2 = this.g;
        while (true) {
            if (i == i2) {
                this.f = i;
                if (b(1)) {
                    i = this.f;
                    i2 = this.g;
                } else if (!z) {
                    return -1;
                } else {
                    throw new EOFException("End of input at line " + r() + " column " + s());
                }
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.h++;
                this.i = i3;
                i = i3;
            } else if (c == ' ' || c == '\r') {
                i = i3;
            } else if (c == '\t') {
                i = i3;
            } else if (c == '/') {
                this.f = i3;
                if (i3 == i2) {
                    this.f--;
                    boolean b = b(2);
                    this.f++;
                    if (!b) {
                        return c;
                    }
                }
                x();
                switch (cArr[this.f]) {
                    case R.styleable.AppCompatTheme_textAppearancePopupMenuHeader /*42*/:
                        this.f++;
                        if (a("*/")) {
                            i = this.f + 2;
                            i2 = this.g;
                            break;
                        }
                        throw b("Unterminated comment");
                    case R.styleable.AppCompatTheme_dropdownListPreferredItemHeight /*47*/:
                        this.f++;
                        y();
                        i = this.f;
                        i2 = this.g;
                        break;
                    default:
                        return c;
                }
            } else if (c == '#') {
                this.f = i3;
                x();
                y();
                i = this.f;
                i2 = this.g;
            } else {
                this.f = i3;
                return c;
            }
        }
    }

    private void x() {
        if (!this.d) {
            throw b("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void y() {
        char c;
        do {
            if (this.f < this.g || b(1)) {
                char[] cArr = this.e;
                int i = this.f;
                this.f = i + 1;
                c = cArr[i];
                if (c == '\n') {
                    this.h++;
                    this.i = this.f;
                    return;
                }
            } else {
                return;
            }
        } while (c != '\r');
    }

    private boolean a(String str) {
        while (true) {
            if (this.f + str.length() > this.g && !b(str.length())) {
                return false;
            }
            if (this.e[this.f] == '\n') {
                this.h++;
                this.i = this.f + 1;
            } else {
                int i = 0;
                while (i < str.length()) {
                    if (this.e[this.f + i] == str.charAt(i)) {
                        i++;
                    }
                }
                return true;
            }
            this.f++;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + " at line " + r() + " column " + s();
    }

    public String t() {
        StringBuilder append = new StringBuilder().append('$');
        int i = this.n;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.m[i2]) {
                case TwitterResponse.READ /*1*/:
                case TwitterResponse.READ_WRITE /*2*/:
                    append.append('[').append(this.p[i2]).append(']');
                    break;
                case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                case AdInfo.BANNER_KIND_BANNER5 /*5*/:
                    append.append('.');
                    if (this.o[i2] == null) {
                        break;
                    }
                    append.append(this.o[i2]);
                    break;
                default:
                    break;
            }
        }
        return append.toString();
    }

    private char z() {
        if (this.f != this.g || b(1)) {
            char[] cArr = this.e;
            int i = this.f;
            this.f = i + 1;
            char c = cArr[i];
            switch (c) {
                case AdInfo.BANNER_KIND_WALL1 /*10*/:
                    this.h++;
                    this.i = this.f;
                    return c;
                case R.styleable.AppCompatTheme_buttonBarPositiveButtonStyle /*98*/:
                    return '\b';
                case R.styleable.AppCompatTheme_buttonStyle /*102*/:
                    return '\f';
                case R.styleable.AppCompatTheme_ratingBarStyleSmall /*110*/:
                    return '\n';
                case R.styleable.AppCompatTheme_listMenuViewStyle /*114*/:
                    return '\r';
                case 't':
                    return '\t';
                case 'u':
                    if (this.f + 4 <= this.g || b(4)) {
                        int i2 = this.f;
                        int i3 = i2 + 4;
                        int i4 = i2;
                        c = '\u0000';
                        for (i = i4; i < i3; i++) {
                            char c2 = this.e[i];
                            c = (char) (c << 4);
                            if (c2 >= '0' && c2 <= '9') {
                                c = (char) (c + (c2 - 48));
                            } else if (c2 >= 'a' && c2 <= AppLovinTargetingData.GENDER_FEMALE) {
                                c = (char) (c + ((c2 - 97) + 10));
                            } else if (c2 < 'A' || c2 > 'F') {
                                throw new NumberFormatException("\\u" + new String(this.e, this.f, 4));
                            } else {
                                c = (char) (c + ((c2 - 65) + 10));
                            }
                        }
                        this.f += 4;
                        return c;
                    }
                    throw b("Unterminated escape sequence");
                default:
                    return c;
            }
        }
        throw b("Unterminated escape sequence");
    }

    private IOException b(String str) {
        throw new d(str + " at line " + r() + " column " + s() + " path " + t());
    }

    private void A() {
        b(true);
        this.f--;
        if (this.f + b.length <= this.g || b(b.length)) {
            int i = 0;
            while (i < b.length) {
                if (this.e[this.f + i] == b[i]) {
                    i++;
                } else {
                    return;
                }
            }
            this.f += b.length;
        }
    }
}
