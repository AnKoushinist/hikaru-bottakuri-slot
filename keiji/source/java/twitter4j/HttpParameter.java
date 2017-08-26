package twitter4j;

import com.d.a.a.c;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;

public final class HttpParameter implements Serializable, Comparable {
    private static final String GIF = "image/gif";
    private static final String JPEG = "image/jpeg";
    private static final String OCTET = "application/octet-stream";
    private static final String PNG = "image/png";
    private static final long serialVersionUID = 4046908449190454692L;
    private File file = null;
    private InputStream fileBody = null;
    private String name = null;
    private String value = null;

    public HttpParameter(String str, String str2) {
        this.name = str;
        this.value = str2;
    }

    public HttpParameter(String str, File file) {
        this.name = str;
        this.file = file;
    }

    public HttpParameter(String str, String str2, InputStream inputStream) {
        this.name = str;
        this.file = new File(str2);
        this.fileBody = inputStream;
    }

    public HttpParameter(String str, int i) {
        this.name = str;
        this.value = String.valueOf(i);
    }

    public HttpParameter(String str, long j) {
        this.name = str;
        this.value = String.valueOf(j);
    }

    public HttpParameter(String str, double d) {
        this.name = str;
        this.value = String.valueOf(d);
    }

    public HttpParameter(String str, boolean z) {
        this.name = str;
        this.value = String.valueOf(z);
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public File getFile() {
        return this.file;
    }

    public InputStream getFileBody() {
        return this.fileBody;
    }

    public boolean isFile() {
        return this.file != null;
    }

    public boolean hasFileBody() {
        return this.fileBody != null;
    }

    public String getContentType() {
        if (isFile()) {
            String name = this.file.getName();
            if (-1 == name.lastIndexOf(".")) {
                return OCTET;
            }
            name = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
            if (name.length() == 3) {
                if ("gif".equals(name)) {
                    return GIF;
                }
                if ("png".equals(name)) {
                    return PNG;
                }
                if ("jpg".equals(name)) {
                    return JPEG;
                }
                return OCTET;
            } else if (name.length() != 4) {
                return OCTET;
            } else {
                if ("jpeg".equals(name)) {
                    return JPEG;
                }
                return OCTET;
            }
        }
        throw new IllegalStateException("not a file");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpParameter)) {
            return false;
        }
        HttpParameter httpParameter = (HttpParameter) obj;
        if (this.file == null ? httpParameter.file != null : !this.file.equals(httpParameter.file)) {
            return false;
        }
        if (this.fileBody == null ? httpParameter.fileBody != null : !this.fileBody.equals(httpParameter.fileBody)) {
            return false;
        }
        if (!this.name.equals(httpParameter.name)) {
            return false;
        }
        if (this.value != null) {
            if (this.value.equals(httpParameter.value)) {
                return true;
            }
        } else if (httpParameter.value == null) {
            return true;
        }
        return false;
    }

    public static boolean containsFile(HttpParameter[] httpParameterArr) {
        if (httpParameterArr == null) {
            return false;
        }
        for (HttpParameter isFile : httpParameterArr) {
            if (isFile.isFile()) {
                return true;
            }
        }
        return false;
    }

    static boolean containsFile(List<HttpParameter> list) {
        for (HttpParameter isFile : list) {
            if (isFile.isFile()) {
                return true;
            }
        }
        return false;
    }

    public static HttpParameter[] getParameterArray(String str, String str2) {
        return new HttpParameter[]{new HttpParameter(str, str2)};
    }

    public static HttpParameter[] getParameterArray(String str, int i) {
        return getParameterArray(str, String.valueOf(i));
    }

    public static HttpParameter[] getParameterArray(String str, String str2, String str3, String str4) {
        return new HttpParameter[]{new HttpParameter(str, str2), new HttpParameter(str3, str4)};
    }

    public static HttpParameter[] getParameterArray(String str, int i, String str2, int i2) {
        return getParameterArray(str, String.valueOf(i), str2, String.valueOf(i2));
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = this.name.hashCode() * 31;
        if (this.value != null) {
            hashCode = this.value.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.file != null) {
            hashCode = this.file.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.fileBody != null) {
            i = this.fileBody.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "PostParameter{name='" + this.name + '\'' + ", value='" + this.value + '\'' + ", file=" + this.file + ", fileBody=" + this.fileBody + '}';
    }

    public int compareTo(Object obj) {
        HttpParameter httpParameter = (HttpParameter) obj;
        int compareTo = this.name.compareTo(httpParameter.name);
        if (compareTo == 0) {
            return this.value.compareTo(httpParameter.value);
        }
        return compareTo;
    }

    public static String encodeParameters(HttpParameter[] httpParameterArr) {
        if (httpParameterArr == null) {
            return BuildConfig.FLAVOR;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < httpParameterArr.length; i++) {
            if (httpParameterArr[i].isFile()) {
                throw new IllegalArgumentException("parameter [" + httpParameterArr[i].name + "]should be text");
            }
            if (i != 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(encode(httpParameterArr[i].name)).append("=").append(encode(httpParameterArr[i].value));
        }
        return stringBuilder.toString();
    }

    public static String encode(String str) {
        String str2 = null;
        try {
            str2 = URLEncoder.encode(str, c.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
        }
        StringBuilder stringBuilder = new StringBuilder(str2.length());
        int i = 0;
        while (i < str2.length()) {
            char charAt = str2.charAt(i);
            if (charAt == '*') {
                stringBuilder.append("%2A");
            } else if (charAt == '+') {
                stringBuilder.append("%20");
            } else if (charAt == '%' && i + 1 < str2.length() && str2.charAt(i + 1) == '7' && str2.charAt(i + 2) == 'E') {
                stringBuilder.append('~');
                i += 2;
            } else {
                stringBuilder.append(charAt);
            }
            i++;
        }
        return stringBuilder.toString();
    }
}
