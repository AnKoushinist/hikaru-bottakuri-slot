package com.glossomads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import com.d.a.a.c;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.cocos2dx.lib.BuildConfig;

public class SugarUtil {
    public static String getStackTrace(Exception exception) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static boolean isEmptyValue(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmptyValue(String str) {
        return !isEmptyValue(str);
    }

    public static String encode(String str) {
        if (!isEmptyValue(str)) {
            try {
                str = URLEncoder.encode(str, c.DEFAULT_CHARSET);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static boolean isEmptyCollection(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static String decode(String str) {
        if (!isEmptyValue(str)) {
            try {
                str = URLDecoder.decode(str, c.DEFAULT_CHARSET);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static List<String> formattedList(String... strArr) {
        List arrayList = new ArrayList();
        if (strArr != null) {
            for (String str : strArr) {
                if (!isEmptyValue(str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    public static void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(FileOutputStream fileOutputStream) {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap decodeBase64(String str) {
        byte[] decode = Base64.decode(str, 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    public static String getFileExtension(URL url) {
        String[] split = url.getFile().split(Operation.DIVISION, -1);
        String[] split2 = split[split.length - 1].split("\\.", -1);
        String str = BuildConfig.FLAVOR;
        if (split2.length > 1) {
            return "." + split2[split2.length - 1];
        }
        return str;
    }

    public static boolean makeDir(String str) {
        if (isEmptyValue(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return false;
        }
        return file.mkdirs();
    }

    public static void deleteFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (File deleteFile : listFiles) {
                deleteFile(deleteFile);
            }
            file.delete();
            return;
        }
        file.delete();
    }
}
