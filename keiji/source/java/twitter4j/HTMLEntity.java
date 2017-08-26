package twitter4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.MediaEntity.Size;

final class HTMLEntity {
    private static final Map<String, String> entityEscapeMap = new HashMap();
    private static final Map<String, String> escapeEntityMap = new HashMap();

    HTMLEntity() {
    }

    static String escape(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        escape(stringBuilder);
        return stringBuilder.toString();
    }

    static void escape(StringBuilder stringBuilder) {
        int i = 0;
        while (i < stringBuilder.length()) {
            String str = (String) entityEscapeMap.get(stringBuilder.substring(i, i + 1));
            if (str != null) {
                stringBuilder.replace(i, i + 1, str);
                i = str.length() + i;
            } else {
                i++;
            }
        }
    }

    static String unescape(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        unescape(stringBuilder);
        return stringBuilder.toString();
    }

    static void unescape(StringBuilder stringBuilder) {
        int i = 0;
        while (i < stringBuilder.length()) {
            int indexOf = stringBuilder.indexOf("&", i);
            if (-1 != indexOf) {
                int indexOf2 = stringBuilder.indexOf(";", indexOf);
                if (-1 != indexOf2) {
                    String str = (String) escapeEntityMap.get(stringBuilder.substring(indexOf, indexOf2 + 1));
                    if (str != null) {
                        stringBuilder.replace(indexOf, indexOf2 + 1, str);
                    }
                    i = indexOf + 1;
                } else {
                    return;
                }
            }
            return;
        }
    }

    static String unescapeAndSlideEntityIncdices(String str, UserMentionEntity[] userMentionEntityArr, URLEntity[] uRLEntityArr, HashtagEntity[] hashtagEntityArr, MediaEntity[] mediaEntityArr) {
        int length;
        Object obj = new EntityIndex[((mediaEntityArr == null ? 0 : mediaEntityArr.length) + (((0 + (userMentionEntityArr == null ? 0 : userMentionEntityArr.length)) + (uRLEntityArr == null ? 0 : uRLEntityArr.length)) + (hashtagEntityArr == null ? 0 : hashtagEntityArr.length)))];
        if (userMentionEntityArr != null) {
            System.arraycopy(userMentionEntityArr, 0, obj, 0, userMentionEntityArr.length);
            length = userMentionEntityArr.length + 0;
        } else {
            length = 0;
        }
        if (uRLEntityArr != null) {
            System.arraycopy(uRLEntityArr, 0, obj, length, uRLEntityArr.length);
            length += uRLEntityArr.length;
        }
        if (hashtagEntityArr != null) {
            System.arraycopy(hashtagEntityArr, 0, obj, length, hashtagEntityArr.length);
            length += hashtagEntityArr.length;
        }
        if (mediaEntityArr != null) {
            System.arraycopy(mediaEntityArr, 0, obj, length, mediaEntityArr.length);
        }
        Arrays.sort(obj);
        StringBuilder stringBuilder = new StringBuilder(str.length());
        int i = 0;
        int i2 = 1;
        for (int i3 = 0; i3 < str.length(); i3 = length + 1) {
            char charAt = str.charAt(i3);
            if (charAt == '&') {
                int indexOf = str.indexOf(";", i3);
                if (-1 != indexOf) {
                    String substring = str.substring(i3, indexOf + 1);
                    String str2 = (String) escapeEntityMap.get(substring);
                    if (str2 != null) {
                        stringBuilder.append(str2);
                        i3 = 1 - substring.length();
                        length = indexOf;
                    } else {
                        stringBuilder.append(charAt);
                        length = i3;
                        i3 = 0;
                    }
                } else {
                    stringBuilder.append(charAt);
                    length = i3;
                    i3 = 0;
                }
            } else {
                stringBuilder.append(charAt);
                length = i3;
                i3 = 0;
            }
            if (i < obj.length) {
                if (i2 != 0) {
                    if (obj[i].getStart() == i3 + length) {
                        obj[i].setStart(stringBuilder.length() - 1);
                        i3 = i;
                        i = 0;
                        i2 = i;
                        i = i3;
                    }
                } else if (obj[i].getEnd() == i3 + length) {
                    obj[i].setEnd(stringBuilder.length() - 1);
                    i3 = i + 1;
                    i = 1;
                    i2 = i;
                    i = i3;
                }
            }
            i3 = i;
            i = i2;
            i2 = i;
            i = i3;
        }
        if (i < obj.length && obj[i].getEnd() == str.length()) {
            obj[i].setEnd(stringBuilder.length());
        }
        return stringBuilder.toString();
    }

    static {
        String[][] strArr = new String[251][];
        strArr[0] = new String[]{"&nbsp;", "&#160;", "\u00a0"};
        strArr[1] = new String[]{"&iexcl;", "&#161;", "\u00a1"};
        strArr[2] = new String[]{"&cent;", "&#162;", "\u00a2"};
        strArr[3] = new String[]{"&pound;", "&#163;", "\u00a3"};
        strArr[4] = new String[]{"&curren;", "&#164;", "\u00a4"};
        strArr[5] = new String[]{"&yen;", "&#165;", "\u00a5"};
        strArr[6] = new String[]{"&brvbar;", "&#166;", "\u00a6"};
        strArr[7] = new String[]{"&sect;", "&#167;", "\u00a7"};
        strArr[8] = new String[]{"&uml;", "&#168;", "\u00a8"};
        strArr[9] = new String[]{"&copy;", "&#169;", "\u00a9"};
        strArr[10] = new String[]{"&ordf;", "&#170;", "\u00aa"};
        strArr[11] = new String[]{"&laquo;", "&#171;", "\u00ab"};
        strArr[12] = new String[]{"&not;", "&#172;", "\u00ac"};
        strArr[13] = new String[]{"&shy;", "&#173;", "\u00ad"};
        strArr[14] = new String[]{"&reg;", "&#174;", "\u00ae"};
        strArr[15] = new String[]{"&macr;", "&#175;", "\u00af"};
        strArr[16] = new String[]{"&deg;", "&#176;", "\u00b0"};
        strArr[17] = new String[]{"&plusmn;", "&#177;", "\u00b1"};
        strArr[18] = new String[]{"&sup2;", "&#178;", "\u00b2"};
        strArr[19] = new String[]{"&sup3;", "&#179;", "\u00b3"};
        strArr[20] = new String[]{"&acute;", "&#180;", "\u00b4"};
        strArr[21] = new String[]{"&micro;", "&#181;", "\u00b5"};
        strArr[22] = new String[]{"&para;", "&#182;", "\u00b6"};
        strArr[23] = new String[]{"&middot;", "&#183;", "\u00b7"};
        strArr[24] = new String[]{"&cedil;", "&#184;", "\u00b8"};
        strArr[25] = new String[]{"&sup1;", "&#185;", "\u00b9"};
        strArr[26] = new String[]{"&ordm;", "&#186;", "\u00ba"};
        strArr[27] = new String[]{"&raquo;", "&#187;", "\u00bb"};
        strArr[28] = new String[]{"&frac14;", "&#188;", "\u00bc"};
        strArr[29] = new String[]{"&frac12;", "&#189;", "\u00bd"};
        strArr[30] = new String[]{"&frac34;", "&#190;", "\u00be"};
        strArr[31] = new String[]{"&iquest;", "&#191;", "\u00bf"};
        strArr[32] = new String[]{"&Agrave;", "&#192;", "\u00c0"};
        strArr[33] = new String[]{"&Aacute;", "&#193;", "\u00c1"};
        strArr[34] = new String[]{"&Acirc;", "&#194;", "\u00c2"};
        strArr[35] = new String[]{"&Atilde;", "&#195;", "\u00c3"};
        strArr[36] = new String[]{"&Auml;", "&#196;", "\u00c4"};
        strArr[37] = new String[]{"&Aring;", "&#197;", "\u00c5"};
        strArr[38] = new String[]{"&AElig;", "&#198;", "\u00c6"};
        strArr[39] = new String[]{"&Ccedil;", "&#199;", "\u00c7"};
        strArr[40] = new String[]{"&Egrave;", "&#200;", "\u00c8"};
        strArr[41] = new String[]{"&Eacute;", "&#201;", "\u00c9"};
        strArr[42] = new String[]{"&Ecirc;", "&#202;", "\u00ca"};
        strArr[43] = new String[]{"&Euml;", "&#203;", "\u00cb"};
        strArr[44] = new String[]{"&Igrave;", "&#204;", "\u00cc"};
        strArr[45] = new String[]{"&Iacute;", "&#205;", "\u00cd"};
        strArr[46] = new String[]{"&Icirc;", "&#206;", "\u00ce"};
        strArr[47] = new String[]{"&Iuml;", "&#207;", "\u00cf"};
        strArr[48] = new String[]{"&ETH;", "&#208;", "\u00d0"};
        strArr[49] = new String[]{"&Ntilde;", "&#209;", "\u00d1"};
        strArr[50] = new String[]{"&Ograve;", "&#210;", "\u00d2"};
        strArr[51] = new String[]{"&Oacute;", "&#211;", "\u00d3"};
        strArr[52] = new String[]{"&Ocirc;", "&#212;", "\u00d4"};
        strArr[53] = new String[]{"&Otilde;", "&#213;", "\u00d5"};
        strArr[54] = new String[]{"&Ouml;", "&#214;", "\u00d6"};
        strArr[55] = new String[]{"&times;", "&#215;", "\u00d7"};
        strArr[56] = new String[]{"&Oslash;", "&#216;", "\u00d8"};
        strArr[57] = new String[]{"&Ugrave;", "&#217;", "\u00d9"};
        strArr[58] = new String[]{"&Uacute;", "&#218;", "\u00da"};
        strArr[59] = new String[]{"&Ucirc;", "&#219;", "\u00db"};
        strArr[60] = new String[]{"&Uuml;", "&#220;", "\u00dc"};
        strArr[61] = new String[]{"&Yacute;", "&#221;", "\u00dd"};
        strArr[62] = new String[]{"&THORN;", "&#222;", "\u00de"};
        strArr[63] = new String[]{"&szlig;", "&#223;", "\u00df"};
        strArr[64] = new String[]{"&agrave;", "&#224;", "\u00e0"};
        strArr[65] = new String[]{"&aacute;", "&#225;", "\u00e1"};
        strArr[66] = new String[]{"&acirc;", "&#226;", "\u00e2"};
        strArr[67] = new String[]{"&atilde;", "&#227;", "\u00e3"};
        strArr[68] = new String[]{"&auml;", "&#228;", "\u00e4"};
        strArr[69] = new String[]{"&aring;", "&#229;", "\u00e5"};
        strArr[70] = new String[]{"&aelig;", "&#230;", "\u00e6"};
        strArr[71] = new String[]{"&ccedil;", "&#231;", "\u00e7"};
        strArr[72] = new String[]{"&egrave;", "&#232;", "\u00e8"};
        strArr[73] = new String[]{"&eacute;", "&#233;", "\u00e9"};
        strArr[74] = new String[]{"&ecirc;", "&#234;", "\u00ea"};
        strArr[75] = new String[]{"&euml;", "&#235;", "\u00eb"};
        strArr[76] = new String[]{"&igrave;", "&#236;", "\u00ec"};
        strArr[77] = new String[]{"&iacute;", "&#237;", "\u00ed"};
        strArr[78] = new String[]{"&icirc;", "&#238;", "\u00ee"};
        strArr[79] = new String[]{"&iuml;", "&#239;", "\u00ef"};
        strArr[80] = new String[]{"&eth;", "&#240;", "\u00f0"};
        strArr[81] = new String[]{"&ntilde;", "&#241;", "\u00f1"};
        strArr[82] = new String[]{"&ograve;", "&#242;", "\u00f2"};
        strArr[83] = new String[]{"&oacute;", "&#243;", "\u00f3"};
        strArr[84] = new String[]{"&ocirc;", "&#244;", "\u00f4"};
        strArr[85] = new String[]{"&otilde;", "&#245;", "\u00f5"};
        strArr[86] = new String[]{"&ouml;", "&#246;", "\u00f6"};
        strArr[87] = new String[]{"&divide;", "&#247;", "\u00f7"};
        strArr[88] = new String[]{"&oslash;", "&#248;", "\u00f8"};
        strArr[89] = new String[]{"&ugrave;", "&#249;", "\u00f9"};
        strArr[90] = new String[]{"&uacute;", "&#250;", "\u00fa"};
        strArr[91] = new String[]{"&ucirc;", "&#251;", "\u00fb"};
        strArr[92] = new String[]{"&uuml;", "&#252;", "\u00fc"};
        strArr[93] = new String[]{"&yacute;", "&#253;", "\u00fd"};
        strArr[94] = new String[]{"&thorn;", "&#254;", "\u00fe"};
        strArr[95] = new String[]{"&yuml;", "&#255;", "\u00ff"};
        strArr[96] = new String[]{"&fnof;", "&#402;", "\u0192"};
        strArr[97] = new String[]{"&Alpha;", "&#913;", "\u0391"};
        strArr[98] = new String[]{"&Beta;", "&#914;", "\u0392"};
        strArr[99] = new String[]{"&Gamma;", "&#915;", "\u0393"};
        strArr[100] = new String[]{"&Delta;", "&#916;", "\u0394"};
        strArr[Size.CROP] = new String[]{"&Epsilon;", "&#917;", "\u0395"};
        strArr[R.styleable.AppCompatTheme_buttonStyle] = new String[]{"&Zeta;", "&#918;", "\u0396"};
        strArr[R.styleable.AppCompatTheme_buttonStyleSmall] = new String[]{"&Eta;", "&#919;", "\u0397"};
        strArr[R.styleable.AppCompatTheme_checkboxStyle] = new String[]{"&Theta;", "&#920;", "\u0398"};
        strArr[R.styleable.AppCompatTheme_checkedTextViewStyle] = new String[]{"&Iota;", "&#921;", "\u0399"};
        strArr[R.styleable.AppCompatTheme_editTextStyle] = new String[]{"&Kappa;", "&#922;", "\u039a"};
        strArr[R.styleable.AppCompatTheme_radioButtonStyle] = new String[]{"&Lambda;", "&#923;", "\u039b"};
        strArr[R.styleable.AppCompatTheme_ratingBarStyle] = new String[]{"&Mu;", "&#924;", "\u039c"};
        strArr[R.styleable.AppCompatTheme_ratingBarStyleIndicator] = new String[]{"&Nu;", "&#925;", "\u039d"};
        strArr[R.styleable.AppCompatTheme_ratingBarStyleSmall] = new String[]{"&Xi;", "&#926;", "\u039e"};
        strArr[R.styleable.AppCompatTheme_seekBarStyle] = new String[]{"&Omicron;", "&#927;", "\u039f"};
        strArr[R.styleable.AppCompatTheme_spinnerStyle] = new String[]{"&Pi;", "&#928;", "\u03a0"};
        strArr[R.styleable.AppCompatTheme_switchStyle] = new String[]{"&Rho;", "&#929;", "\u03a1"};
        strArr[R.styleable.AppCompatTheme_listMenuViewStyle] = new String[]{"&Sigma;", "&#931;", "\u03a3"};
        strArr[115] = new String[]{"&Tau;", "&#932;", "\u03a4"};
        strArr[116] = new String[]{"&Upsilon;", "&#933;", "\u03a5"};
        strArr[117] = new String[]{"&Phi;", "&#934;", "\u03a6"};
        strArr[118] = new String[]{"&Chi;", "&#935;", "\u03a7"};
        strArr[119] = new String[]{"&Psi;", "&#936;", "\u03a8"};
        strArr[120] = new String[]{"&Omega;", "&#937;", "\u03a9"};
        strArr[121] = new String[]{"&alpha;", "&#945;", "\u03b1"};
        strArr[122] = new String[]{"&beta;", "&#946;", "\u03b2"};
        strArr[123] = new String[]{"&gamma;", "&#947;", "\u03b3"};
        strArr[124] = new String[]{"&delta;", "&#948;", "\u03b4"};
        strArr[125] = new String[]{"&epsilon;", "&#949;", "\u03b5"};
        strArr[126] = new String[]{"&zeta;", "&#950;", "\u03b6"};
        strArr[127] = new String[]{"&eta;", "&#951;", "\u03b7"};
        strArr[128] = new String[]{"&theta;", "&#952;", "\u03b8"};
        strArr[129] = new String[]{"&iota;", "&#953;", "\u03b9"};
        strArr[130] = new String[]{"&kappa;", "&#954;", "\u03ba"};
        strArr[131] = new String[]{"&lambda;", "&#955;", "\u03bb"};
        strArr[132] = new String[]{"&mu;", "&#956;", "\u03bc"};
        strArr[133] = new String[]{"&nu;", "&#957;", "\u03bd"};
        strArr[134] = new String[]{"&xi;", "&#958;", "\u03be"};
        strArr[135] = new String[]{"&omicron;", "&#959;", "\u03bf"};
        strArr[136] = new String[]{"&pi;", "&#960;", "\u03c0"};
        strArr[137] = new String[]{"&rho;", "&#961;", "\u03c1"};
        strArr[138] = new String[]{"&sigmaf;", "&#962;", "\u03c2"};
        strArr[139] = new String[]{"&sigma;", "&#963;", "\u03c3"};
        strArr[140] = new String[]{"&tau;", "&#964;", "\u03c4"};
        strArr[141] = new String[]{"&upsilon;", "&#965;", "\u03c5"};
        strArr[142] = new String[]{"&phi;", "&#966;", "\u03c6"};
        strArr[143] = new String[]{"&chi;", "&#967;", "\u03c7"};
        strArr[144] = new String[]{"&psi;", "&#968;", "\u03c8"};
        strArr[145] = new String[]{"&omega;", "&#969;", "\u03c9"};
        strArr[146] = new String[]{"&thetasym;", "&#977;", "\u03d1"};
        strArr[147] = new String[]{"&upsih;", "&#978;", "\u03d2"};
        strArr[148] = new String[]{"&piv;", "&#982;", "\u03d6"};
        strArr[149] = new String[]{"&bull;", "&#8226;", "\u2022"};
        strArr[150] = new String[]{"&hellip;", "&#8230;", "\u2026"};
        strArr[151] = new String[]{"&prime;", "&#8242;", "\u2032"};
        strArr[152] = new String[]{"&Prime;", "&#8243;", "\u2033"};
        strArr[153] = new String[]{"&oline;", "&#8254;", "\u203e"};
        strArr[154] = new String[]{"&frasl;", "&#8260;", "\u2044"};
        strArr[155] = new String[]{"&weierp;", "&#8472;", "\u2118"};
        strArr[156] = new String[]{"&image;", "&#8465;", "\u2111"};
        strArr[157] = new String[]{"&real;", "&#8476;", "\u211c"};
        strArr[158] = new String[]{"&trade;", "&#8482;", "\u2122"};
        strArr[159] = new String[]{"&alefsym;", "&#8501;", "\u2135"};
        strArr[160] = new String[]{"&larr;", "&#8592;", "\u2190"};
        strArr[161] = new String[]{"&uarr;", "&#8593;", "\u2191"};
        strArr[162] = new String[]{"&rarr;", "&#8594;", "\u2192"};
        strArr[163] = new String[]{"&darr;", "&#8595;", "\u2193"};
        strArr[164] = new String[]{"&harr;", "&#8596;", "\u2194"};
        strArr[165] = new String[]{"&crarr;", "&#8629;", "\u21b5"};
        strArr[166] = new String[]{"&lArr;", "&#8656;", "\u21d0"};
        strArr[167] = new String[]{"&uArr;", "&#8657;", "\u21d1"};
        strArr[168] = new String[]{"&rArr;", "&#8658;", "\u21d2"};
        strArr[169] = new String[]{"&dArr;", "&#8659;", "\u21d3"};
        strArr[170] = new String[]{"&hArr;", "&#8660;", "\u21d4"};
        strArr[171] = new String[]{"&forall;", "&#8704;", "\u2200"};
        strArr[172] = new String[]{"&part;", "&#8706;", "\u2202"};
        strArr[173] = new String[]{"&exist;", "&#8707;", "\u2203"};
        strArr[174] = new String[]{"&empty;", "&#8709;", "\u2205"};
        strArr[175] = new String[]{"&nabla;", "&#8711;", "\u2207"};
        strArr[176] = new String[]{"&isin;", "&#8712;", "\u2208"};
        strArr[177] = new String[]{"&notin;", "&#8713;", "\u2209"};
        strArr[178] = new String[]{"&ni;", "&#8715;", "\u220b"};
        strArr[179] = new String[]{"&prod;", "&#8719;", "\u220f"};
        strArr[180] = new String[]{"&sum;", "&#8721;", "\u2211"};
        strArr[181] = new String[]{"&minus;", "&#8722;", "\u2212"};
        strArr[182] = new String[]{"&lowast;", "&#8727;", "\u2217"};
        strArr[183] = new String[]{"&radic;", "&#8730;", "\u221a"};
        strArr[184] = new String[]{"&prop;", "&#8733;", "\u221d"};
        strArr[185] = new String[]{"&infin;", "&#8734;", "\u221e"};
        strArr[186] = new String[]{"&ang;", "&#8736;", "\u2220"};
        strArr[187] = new String[]{"&and;", "&#8743;", "\u2227"};
        strArr[188] = new String[]{"&or;", "&#8744;", "\u2228"};
        strArr[189] = new String[]{"&cap;", "&#8745;", "\u2229"};
        strArr[190] = new String[]{"&cup;", "&#8746;", "\u222a"};
        strArr[191] = new String[]{"&int;", "&#8747;", "\u222b"};
        strArr[192] = new String[]{"&there4;", "&#8756;", "\u2234"};
        strArr[193] = new String[]{"&sim;", "&#8764;", "\u223c"};
        strArr[194] = new String[]{"&cong;", "&#8773;", "\u2245"};
        strArr[195] = new String[]{"&asymp;", "&#8776;", "\u2248"};
        strArr[196] = new String[]{"&ne;", "&#8800;", "\u2260"};
        strArr[197] = new String[]{"&equiv;", "&#8801;", "\u2261"};
        strArr[198] = new String[]{"&le;", "&#8804;", "\u2264"};
        strArr[199] = new String[]{"&ge;", "&#8805;", "\u2265"};
        strArr[HttpResponseCode.OK] = new String[]{"&sub;", "&#8834;", "\u2282"};
        strArr[201] = new String[]{"&sup;", "&#8835;", "\u2283"};
        strArr[202] = new String[]{"&sube;", "&#8838;", "\u2286"};
        strArr[203] = new String[]{"&supe;", "&#8839;", "\u2287"};
        strArr[204] = new String[]{"&oplus;", "&#8853;", "\u2295"};
        strArr[205] = new String[]{"&otimes;", "&#8855;", "\u2297"};
        strArr[206] = new String[]{"&perp;", "&#8869;", "\u22a5"};
        strArr[207] = new String[]{"&sdot;", "&#8901;", "\u22c5"};
        strArr[208] = new String[]{"&lceil;", "&#8968;", "\u2308"};
        strArr[209] = new String[]{"&rceil;", "&#8969;", "\u2309"};
        strArr[210] = new String[]{"&lfloor;", "&#8970;", "\u230a"};
        strArr[211] = new String[]{"&rfloor;", "&#8971;", "\u230b"};
        strArr[212] = new String[]{"&lang;", "&#9001;", "\u2329"};
        strArr[213] = new String[]{"&rang;", "&#9002;", "\u232a"};
        strArr[214] = new String[]{"&loz;", "&#9674;", "\u25ca"};
        strArr[215] = new String[]{"&spades;", "&#9824;", "\u2660"};
        strArr[216] = new String[]{"&clubs;", "&#9827;", "\u2663"};
        strArr[217] = new String[]{"&hearts;", "&#9829;", "\u2665"};
        strArr[218] = new String[]{"&diams;", "&#9830;", "\u2666"};
        strArr[219] = new String[]{"&quot;", "&#34;", "\""};
        strArr[220] = new String[]{"&amp;", "&#38;", "&"};
        strArr[221] = new String[]{"&lt;", "&#60;", "<"};
        strArr[222] = new String[]{"&gt;", "&#62;", ">"};
        strArr[223] = new String[]{"&OElig;", "&#338;", "\u0152"};
        strArr[224] = new String[]{"&oelig;", "&#339;", "\u0153"};
        strArr[225] = new String[]{"&Scaron;", "&#352;", "\u0160"};
        strArr[226] = new String[]{"&scaron;", "&#353;", "\u0161"};
        strArr[227] = new String[]{"&Yuml;", "&#376;", "\u0178"};
        strArr[228] = new String[]{"&circ;", "&#710;", "\u02c6"};
        strArr[229] = new String[]{"&tilde;", "&#732;", "\u02dc"};
        strArr[230] = new String[]{"&ensp;", "&#8194;", "\u2002"};
        strArr[231] = new String[]{"&emsp;", "&#8195;", "\u2003"};
        strArr[232] = new String[]{"&thinsp;", "&#8201;", "\u2009"};
        strArr[233] = new String[]{"&zwnj;", "&#8204;", "\u200c"};
        strArr[234] = new String[]{"&zwj;", "&#8205;", "\u200d"};
        strArr[235] = new String[]{"&lrm;", "&#8206;", "\u200e"};
        strArr[236] = new String[]{"&rlm;", "&#8207;", "\u200f"};
        strArr[237] = new String[]{"&ndash;", "&#8211;", "\u2013"};
        strArr[238] = new String[]{"&mdash;", "&#8212;", "\u2014"};
        strArr[239] = new String[]{"&lsquo;", "&#8216;", "\u2018"};
        strArr[240] = new String[]{"&rsquo;", "&#8217;", "\u2019"};
        strArr[241] = new String[]{"&sbquo;", "&#8218;", "\u201a"};
        strArr[242] = new String[]{"&ldquo;", "&#8220;", "\u201c"};
        strArr[243] = new String[]{"&rdquo;", "&#8221;", "\u201d"};
        strArr[244] = new String[]{"&bdquo;", "&#8222;", "\u201e"};
        strArr[245] = new String[]{"&dagger;", "&#8224;", "\u2020"};
        strArr[246] = new String[]{"&Dagger;", "&#8225;", "\u2021"};
        strArr[247] = new String[]{"&permil;", "&#8240;", "\u2030"};
        strArr[248] = new String[]{"&lsaquo;", "&#8249;", "\u2039"};
        strArr[249] = new String[]{"&rsaquo;", "&#8250;", "\u203a"};
        strArr[250] = new String[]{"&euro;", "&#8364;", "\u20ac"};
        for (Object[] objArr : strArr) {
            entityEscapeMap.put(objArr[2], objArr[0]);
            escapeEntityMap.put(objArr[0], objArr[2]);
            escapeEntityMap.put(objArr[1], objArr[2]);
        }
    }
}
