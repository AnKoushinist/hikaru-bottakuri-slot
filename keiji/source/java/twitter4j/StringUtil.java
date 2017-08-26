package twitter4j;

class StringUtil {
    private StringUtil() {
        throw new AssertionError();
    }

    public static String join(long[] jArr) {
        StringBuilder stringBuilder = new StringBuilder(jArr.length * 11);
        for (long j : jArr) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(j);
        }
        return stringBuilder.toString();
    }

    public static String join(String[] strArr) {
        StringBuilder stringBuilder = new StringBuilder(strArr.length * 11);
        for (String str : strArr) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }
}
