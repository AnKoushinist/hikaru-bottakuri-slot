package com.squareup.phrase;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import twitter4j.TwitterResponse;

public final class ListPhrase {
    private final CharSequence finalElementSeparator;
    private final CharSequence nonFinalElementSeparator;
    private final CharSequence twoElementSeparator;

    public interface Formatter<T> {
        CharSequence format(T t);
    }

    public static ListPhrase from(CharSequence charSequence) {
        checkNotNull("separator", charSequence);
        return from(charSequence, charSequence, charSequence);
    }

    public static ListPhrase from(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return new ListPhrase(charSequence, charSequence2, charSequence3);
    }

    private ListPhrase(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        this.twoElementSeparator = (CharSequence) checkNotNull("two-element separator", charSequence);
        this.nonFinalElementSeparator = (CharSequence) checkNotNull("non-final separator", charSequence2);
        this.finalElementSeparator = (CharSequence) checkNotNull("final separator", charSequence3);
    }

    public <T> CharSequence join(T t, T t2, T... tArr) {
        return join(asList(t, t2, tArr));
    }

    public <T> CharSequence join(Iterable<T> iterable) {
        checkNotNullOrEmpty(iterable);
        return join(iterable, null);
    }

    public <T> CharSequence join(Iterable<T> iterable, Formatter<T> formatter) {
        checkNotNullOrEmpty(iterable);
        return joinIterableWithSize(iterable, getSize(iterable), formatter);
    }

    private <T> CharSequence joinIterableWithSize(Iterable<T> iterable, int i, Formatter<T> formatter) {
        switch (i) {
            case TwitterResponse.NONE /*0*/:
                throw new IllegalStateException("list cannot be empty");
            case TwitterResponse.READ /*1*/:
                return formatOrThrow(iterable.iterator().next(), 0, formatter);
            case TwitterResponse.READ_WRITE /*2*/:
                return joinTwoElements(iterable, formatter);
            default:
                return joinMoreThanTwoElements(iterable, i, formatter);
        }
    }

    private <T> CharSequence joinTwoElements(Iterable<T> iterable, Formatter<T> formatter) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = iterable.iterator();
        stringBuilder.append(formatOrThrow(it.next(), 0, formatter));
        stringBuilder.append(this.twoElementSeparator);
        stringBuilder.append(formatOrThrow(it.next(), 1, formatter));
        return stringBuilder.toString();
    }

    private <T> CharSequence joinMoreThanTwoElements(Iterable<T> iterable, int i, Formatter<T> formatter) {
        StringBuilder stringBuilder = new StringBuilder();
        int i2 = i - 2;
        Iterator it = iterable.iterator();
        for (int i3 = 0; i3 < i; i3++) {
            stringBuilder.append(formatOrThrow(it.next(), i3, formatter));
            if (i3 < i2) {
                stringBuilder.append(this.nonFinalElementSeparator);
            } else if (i3 == i2) {
                stringBuilder.append(this.finalElementSeparator);
            }
        }
        return stringBuilder.toString();
    }

    private static int getSize(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        int i = 0;
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            i++;
            it.next();
        }
        return i;
    }

    private static <T> List<T> asList(final T t, final T t2, final T[] tArr) {
        return new AbstractList<T>() {
            public T get(int i) {
                switch (i) {
                    case TwitterResponse.NONE /*0*/:
                        return t;
                    case TwitterResponse.READ /*1*/:
                        return t2;
                    default:
                        return tArr[i - 2];
                }
            }

            public int size() {
                return tArr.length + 2;
            }
        };
    }

    private static <T> CharSequence formatOrThrow(T t, int i, Formatter<T> formatter) {
        if (t == null) {
            throw new IllegalArgumentException("list element cannot be null at index " + i);
        }
        CharSequence obj = formatter == null ? t.toString() : formatter.format(t);
        if (obj == null) {
            throw new IllegalArgumentException("formatted list element cannot be null at index " + i);
        } else if (obj.length() != 0) {
            return obj;
        } else {
            throw new IllegalArgumentException("formatted list element cannot be empty at index " + i);
        }
    }

    private static <T> T checkNotNull(String str, T t) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(str + " cannot be null");
    }

    private static <T> void checkNotNullOrEmpty(Iterable<T> iterable) {
        if (iterable == null) {
            throw new IllegalArgumentException("list cannot be null");
        } else if (!iterable.iterator().hasNext()) {
            throw new IllegalArgumentException("list cannot be empty");
        }
    }
}
