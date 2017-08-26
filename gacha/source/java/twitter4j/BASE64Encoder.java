package twitter4j;

import com.applovin.sdk.AppLovinTargetingData;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;

public final class BASE64Encoder {
    private static final char[] encodeTable = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', AppLovinTargetingData.GENDER_FEMALE, 'g', 'h', 'i', 'j', 'k', 'l', AppLovinTargetingData.GENDER_MALE, 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final char last2byte = ((char) Integer.parseInt("00000011", 2));
    private static final char last4byte = ((char) Integer.parseInt("00001111", 2));
    private static final char last6byte = ((char) Integer.parseInt("00111111", 2));
    private static final char lead2byte = ((char) Integer.parseInt("11000000", 2));
    private static final char lead4byte = ((char) Integer.parseInt("11110000", 2));
    private static final char lead6byte = ((char) Integer.parseInt("11111100", 2));

    private BASE64Encoder() {
    }

    public static String encode(byte[] bArr) {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder(((int) (((double) bArr.length) * 1.34d)) + 3);
        int i2 = 0;
        int i3 = 0;
        while (i < bArr.length) {
            i3 %= 8;
            while (i3 < 8) {
                switch (i3) {
                    case TwitterResponse.NONE /*0*/:
                        i2 = (char) (((char) (bArr[i] & lead6byte)) >>> 2);
                        break;
                    case TwitterResponse.READ_WRITE /*2*/:
                        i2 = (char) (bArr[i] & last6byte);
                        break;
                    case ApiAccessUtil.SERVER_TYPE_AMAZON /*4*/:
                        i2 = (char) (((char) (bArr[i] & last4byte)) << 2);
                        if (i + 1 >= bArr.length) {
                            break;
                        }
                        i2 = (char) (i2 | ((bArr[i + 1] & lead2byte) >>> 6));
                        break;
                    case AdInfo.BANNER_KIND_BANNER6 /*6*/:
                        i2 = (char) (((char) (bArr[i] & last2byte)) << 4);
                        if (i + 1 >= bArr.length) {
                            break;
                        }
                        i2 = (char) (i2 | ((bArr[i + 1] & lead4byte) >>> 4));
                        break;
                    default:
                        break;
                }
                stringBuilder.append(encodeTable[i2]);
                i3 += 6;
            }
            i++;
        }
        if (stringBuilder.length() % 4 != 0) {
            for (i = 4 - (stringBuilder.length() % 4); i > 0; i--) {
                stringBuilder.append(Operation.EQUALS);
            }
        }
        return stringBuilder.toString();
    }
}
