package twitter4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import jp.co.vaz.creator.hikaru.R;
import org.cocos2dx.lib.BuildConfig;

public class JSONTokener {
    private int character;
    private boolean eof;
    private int index;
    private int line;
    private char previous;
    private final Reader reader;
    private boolean usePrevious;

    public JSONTokener(Reader reader) {
        if (!reader.markSupported()) {
            reader = new BufferedReader(reader);
        }
        this.reader = reader;
        this.eof = false;
        this.usePrevious = false;
        this.previous = '\u0000';
        this.index = 0;
        this.character = 1;
        this.line = 1;
    }

    public JSONTokener(InputStream inputStream) {
        this(new InputStreamReader(inputStream));
    }

    public JSONTokener(String str) {
        this(new StringReader(str));
    }

    public void back() {
        if (this.usePrevious || this.index <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        this.index--;
        this.character--;
        this.usePrevious = true;
        this.eof = false;
    }

    public boolean end() {
        return this.eof && !this.usePrevious;
    }

    public boolean more() {
        next();
        if (end()) {
            return false;
        }
        back();
        return true;
    }

    public char next() {
        int i;
        int i2 = 0;
        if (this.usePrevious) {
            this.usePrevious = false;
            i = this.previous;
        } else {
            try {
                i = this.reader.read();
                if (i <= 0) {
                    this.eof = true;
                    i = 0;
                }
            } catch (Throwable e) {
                throw new JSONException(e);
            }
        }
        this.index++;
        if (this.previous == '\r') {
            this.line++;
            if (i != 10) {
                i2 = 1;
            }
            this.character = i2;
        } else if (i == 10) {
            this.line++;
            this.character = 0;
        } else {
            this.character++;
        }
        this.previous = (char) i;
        return this.previous;
    }

    public char next(char c) {
        char next = next();
        if (next == c) {
            return next;
        }
        throw syntaxError("Expected '" + c + "' and instead saw '" + next + "'");
    }

    public String next(int i) {
        if (i == 0) {
            return BuildConfig.FLAVOR;
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = next();
            if (end()) {
                throw syntaxError("Substring bounds error");
            }
        }
        return new String(cArr);
    }

    public char nextClean() {
        char next;
        do {
            next = next();
            if (next == '\u0000') {
                break;
            }
        } while (next <= ' ');
        return next;
    }

    public String nextString(char c) {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            char next = next();
            switch (next) {
                case TwitterResponse.NONE /*0*/:
                case AdInfo.BANNER_KIND_WALL1 /*10*/:
                case R.styleable.Toolbar_subtitleTextAppearance /*13*/:
                    throw syntaxError("Unterminated string");
                case R.styleable.AppCompatTheme_colorBackgroundFloating /*92*/:
                    next = next();
                    switch (next) {
                        case R.styleable.AppCompatTheme_actionModePasteDrawable /*34*/:
                        case R.styleable.AppCompatTheme_actionModePopupWindowStyle /*39*/:
                        case R.styleable.AppCompatTheme_dropdownListPreferredItemHeight /*47*/:
                        case R.styleable.AppCompatTheme_colorBackgroundFloating /*92*/:
                            stringBuilder.append(next);
                            break;
                        case R.styleable.AppCompatTheme_buttonBarPositiveButtonStyle /*98*/:
                            stringBuilder.append('\b');
                            break;
                        case R.styleable.AppCompatTheme_buttonStyle /*102*/:
                            stringBuilder.append('\f');
                            break;
                        case R.styleable.AppCompatTheme_ratingBarStyleSmall /*110*/:
                            stringBuilder.append('\n');
                            break;
                        case R.styleable.AppCompatTheme_listMenuViewStyle /*114*/:
                            stringBuilder.append('\r');
                            break;
                        case 't':
                            stringBuilder.append('\t');
                            break;
                        case 'u':
                            stringBuilder.append((char) Integer.parseInt(next(4), 16));
                            break;
                        default:
                            throw syntaxError("Illegal escape.");
                    }
                default:
                    if (next != c) {
                        stringBuilder.append(next);
                        break;
                    }
                    return stringBuilder.toString();
            }
        }
    }

    public Object nextValue() {
        char nextClean = nextClean();
        switch (nextClean) {
            case R.styleable.AppCompatTheme_actionModePasteDrawable /*34*/:
            case R.styleable.AppCompatTheme_actionModePopupWindowStyle /*39*/:
                return nextString(nextClean);
            case R.styleable.AppCompatTheme_controlBackground /*91*/:
                back();
                return new JSONArray(this);
            case '{':
                back();
                return new JSONObject(this);
            default:
                StringBuilder stringBuilder = new StringBuilder();
                while (nextClean >= ' ' && ",:]}/\\\"[{;=#".indexOf(nextClean) < 0) {
                    stringBuilder.append(nextClean);
                    nextClean = next();
                }
                back();
                String trim = stringBuilder.toString().trim();
                if (!trim.equals(BuildConfig.FLAVOR)) {
                    return JSONObject.stringToValue(trim);
                }
                throw syntaxError("Missing value");
        }
    }

    public JSONException syntaxError(String str) {
        return new JSONException(str + toString());
    }

    public String toString() {
        return " at " + this.index + " [character " + this.character + " line " + this.line + "]";
    }
}
