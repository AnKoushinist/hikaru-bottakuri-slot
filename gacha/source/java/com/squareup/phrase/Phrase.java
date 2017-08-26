package com.squareup.phrase;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableStringBuilder;
import android.view.View;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Phrase {
    private static final int EOF = 0;
    private char curChar;
    private int curCharIndex;
    private CharSequence formatted;
    private Token head;
    private final Set<String> keys = new HashSet();
    private final Map<String, CharSequence> keysToValues = new HashMap();
    private final CharSequence pattern;

    private static abstract class Token {
        private Token next;
        private final Token prev;

        abstract void expand(SpannableStringBuilder spannableStringBuilder, Map<String, CharSequence> map);

        abstract int getFormattedLength();

        protected Token(Token token) {
            this.prev = token;
            if (token != null) {
                token.next = this;
            }
        }

        final int getFormattedStart() {
            if (this.prev == null) {
                return 0;
            }
            return this.prev.getFormattedStart() + this.prev.getFormattedLength();
        }
    }

    private static class KeyToken extends Token {
        private final String key;
        private CharSequence value;

        KeyToken(Token token, String str) {
            super(token);
            this.key = str;
        }

        void expand(SpannableStringBuilder spannableStringBuilder, Map<String, CharSequence> map) {
            this.value = (CharSequence) map.get(this.key);
            int formattedStart = getFormattedStart();
            spannableStringBuilder.replace(formattedStart, (this.key.length() + formattedStart) + 2, this.value);
        }

        int getFormattedLength() {
            return this.value.length();
        }
    }

    private static class LeftCurlyBracketToken extends Token {
        LeftCurlyBracketToken(Token token) {
            super(token);
        }

        void expand(SpannableStringBuilder spannableStringBuilder, Map<String, CharSequence> map) {
            int formattedStart = getFormattedStart();
            spannableStringBuilder.replace(formattedStart, formattedStart + 2, "{");
        }

        int getFormattedLength() {
            return 1;
        }
    }

    private static class TextToken extends Token {
        private final int textLength;

        TextToken(Token token, int i) {
            super(token);
            this.textLength = i;
        }

        void expand(SpannableStringBuilder spannableStringBuilder, Map<String, CharSequence> map) {
        }

        int getFormattedLength() {
            return this.textLength;
        }
    }

    public static Phrase from(Fragment fragment, int i) {
        return from(fragment.getResources(), i);
    }

    public static Phrase from(View view, int i) {
        return from(view.getResources(), i);
    }

    public static Phrase from(Context context, int i) {
        return from(context.getResources(), i);
    }

    public static Phrase from(Resources resources, int i) {
        return from(resources.getText(i));
    }

    public static Phrase from(CharSequence charSequence) {
        return new Phrase(charSequence);
    }

    public Phrase put(String str, CharSequence charSequence) {
        if (!this.keys.contains(str)) {
            throw new IllegalArgumentException("Invalid key: " + str);
        } else if (charSequence == null) {
            throw new IllegalArgumentException("Null value for '" + str + "'");
        } else {
            this.keysToValues.put(str, charSequence);
            this.formatted = null;
            return this;
        }
    }

    public Phrase put(String str, int i) {
        if (this.keys.contains(str)) {
            this.keysToValues.put(str, Integer.toString(i));
            this.formatted = null;
            return this;
        }
        throw new IllegalArgumentException("Invalid key: " + str);
    }

    public Phrase putOptional(String str, CharSequence charSequence) {
        return this.keys.contains(str) ? put(str, charSequence) : this;
    }

    public Phrase putOptional(String str, int i) {
        return this.keys.contains(str) ? put(str, i) : this;
    }

    public CharSequence format() {
        if (this.formatted == null) {
            if (this.keysToValues.keySet().containsAll(this.keys)) {
                CharSequence spannableStringBuilder = new SpannableStringBuilder(this.pattern);
                for (Token token = this.head; token != null; token = token.next) {
                    token.expand(spannableStringBuilder, this.keysToValues);
                }
                this.formatted = spannableStringBuilder;
            } else {
                Set hashSet = new HashSet(this.keys);
                hashSet.removeAll(this.keysToValues.keySet());
                throw new IllegalArgumentException("Missing keys: " + hashSet);
            }
        }
        return this.formatted;
    }

    public String toString() {
        return this.pattern.toString();
    }

    private Phrase(CharSequence charSequence) {
        char c = '\u0000';
        if (charSequence.length() > 0) {
            c = charSequence.charAt(0);
        }
        this.curChar = c;
        this.pattern = charSequence;
        Token token = null;
        while (true) {
            token = token(token);
            if (token == null) {
                return;
            }
            if (this.head == null) {
                this.head = token;
            }
        }
    }

    private Token token(Token token) {
        if (this.curChar == '\u0000') {
            return null;
        }
        if (this.curChar != '{') {
            return text(token);
        }
        char lookahead = lookahead();
        if (lookahead == '{') {
            return leftCurlyBracket(token);
        }
        if (lookahead >= 'a' && lookahead <= 'z') {
            return key(token);
        }
        throw new IllegalArgumentException("Unexpected character '" + lookahead + "'; expected key.");
    }

    private KeyToken key(Token token) {
        StringBuilder stringBuilder = new StringBuilder();
        consume();
        while (true) {
            if ((this.curChar < 'a' || this.curChar > 'z') && this.curChar != '_') {
                break;
            }
            stringBuilder.append(this.curChar);
            consume();
        }
        if (this.curChar != '}') {
            throw new IllegalArgumentException("Missing closing brace: }");
        }
        consume();
        if (stringBuilder.length() == 0) {
            throw new IllegalArgumentException("Empty key: {}");
        }
        String stringBuilder2 = stringBuilder.toString();
        this.keys.add(stringBuilder2);
        return new KeyToken(token, stringBuilder2);
    }

    private TextToken text(Token token) {
        int i = this.curCharIndex;
        while (this.curChar != '{' && this.curChar != '\u0000') {
            consume();
        }
        return new TextToken(token, this.curCharIndex - i);
    }

    private LeftCurlyBracketToken leftCurlyBracket(Token token) {
        consume();
        consume();
        return new LeftCurlyBracketToken(token);
    }

    private char lookahead() {
        return this.curCharIndex < this.pattern.length() + -1 ? this.pattern.charAt(this.curCharIndex + 1) : '\u0000';
    }

    private void consume() {
        this.curCharIndex++;
        this.curChar = this.curCharIndex == this.pattern.length() ? '\u0000' : this.pattern.charAt(this.curCharIndex);
    }
}
