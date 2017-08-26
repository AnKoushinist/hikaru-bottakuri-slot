package com.tapjoy;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import com.d.a.a.c;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import javax.xml.parsers.DocumentBuilderFactory;
import jp.tjkapp.adfurikunsdk.moviereward.ApiAccessUtil;
import org.cocos2dx.lib.BuildConfig;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TapjoyUtil {
    private static String a = null;
    private static HashMap b = new HashMap();

    public static String SHA1(String str) {
        return a("SHA-1", str);
    }

    public static String SHA256(String str) {
        return a("SHA-256", str);
    }

    private static String a(String str, String str2) {
        MessageDigest instance = MessageDigest.getInstance(str);
        instance.update(str2.getBytes("iso-8859-1"), 0, str2.length());
        byte[] digest = instance.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            int i2 = (digest[i] >>> 4) & 15;
            int i3 = 0;
            while (true) {
                if (i2 < 0 || i2 > 9) {
                    stringBuffer.append((char) ((i2 - 10) + 97));
                } else {
                    stringBuffer.append((char) (i2 + 48));
                }
                int i4 = digest[i] & 15;
                i2 = i3 + 1;
                if (i3 > 0) {
                    break;
                }
                i3 = i2;
                i2 = i4;
            }
        }
        return stringBuffer.toString();
    }

    public static Document buildDocument(String str) {
        Document document = null;
        try {
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            document = newInstance.newDocumentBuilder().parse(new ByteArrayInputStream(str.getBytes(c.DEFAULT_CHARSET)));
        } catch (Exception e) {
            TapjoyLog.e("TapjoyUtil", "buildDocument exception: " + e.toString());
        }
        return document;
    }

    public static String getNodeTrimValue(NodeList nodeList) {
        int i = 0;
        Element element = (Element) nodeList.item(0);
        String str = BuildConfig.FLAVOR;
        if (element == null) {
            return null;
        }
        NodeList childNodes = element.getChildNodes();
        int length = childNodes.getLength();
        String str2 = str;
        while (i < length) {
            Node item = childNodes.item(i);
            if (item != null) {
                str2 = str2 + item.getNodeValue();
            }
            i++;
        }
        if (str2 == null || str2.equals(BuildConfig.FLAVOR)) {
            return null;
        }
        return str2.trim();
    }

    public static void deleteFileOrDirectory(File file) {
        if (file != null) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (File deleteFileOrDirectory : listFiles) {
                        deleteFileOrDirectory(deleteFileOrDirectory);
                    }
                }
            }
            TapjoyLog.d("TapjoyUtil", "****************************************");
            TapjoyLog.d("TapjoyUtil", "deleteFileOrDirectory: " + file.getAbsolutePath());
            TapjoyLog.d("TapjoyUtil", "****************************************");
            file.delete();
        }
    }

    public static long fileOrDirectorySize(File file) {
        long j = 0;
        for (File file2 : file.listFiles()) {
            if (file2.isFile()) {
                j += file2.length();
            } else {
                j += fileOrDirectorySize(file2);
            }
        }
        return j;
    }

    public static void writeFileToDevice(BufferedInputStream bufferedInputStream, OutputStream outputStream) {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static Bitmap createBitmapFromView(View view) {
        Bitmap bitmap = null;
        if (view == null || view.getLayoutParams().width <= 0 || view.getLayoutParams().height <= 0) {
            return null;
        }
        try {
            bitmap = Bitmap.createBitmap(view.getLayoutParams().width, view.getLayoutParams().height, Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            view.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            TapjoyLog.d("TapjoyUtil", "error creating bitmap: " + e.toString());
            return bitmap;
        }
    }

    public static View scaleDisplayAd(View view, int i) {
        int i2 = view.getLayoutParams().width;
        int i3 = view.getLayoutParams().height;
        TapjoyLog.d("TapjoyUtil", "wxh: " + i2 + "x" + i3);
        if (i2 > i) {
            int intValue = Double.valueOf(Double.valueOf(Double.valueOf((double) i).doubleValue() / Double.valueOf((double) i2).doubleValue()).doubleValue() * 100.0d).intValue();
            ((WebView) view).getSettings().setSupportZoom(true);
            ((WebView) view).setPadding(0, 0, 0, 0);
            ((WebView) view).setVerticalScrollBarEnabled(false);
            ((WebView) view).setHorizontalScrollBarEnabled(false);
            ((WebView) view).setInitialScale(intValue);
            view.setLayoutParams(new LayoutParams(i, (i3 * i) / i2));
        }
        return view;
    }

    public static void safePut(Map map, String str, String str2, boolean z) {
        if (str != null && str.length() > 0 && str2 != null && str2.length() > 0) {
            if (z) {
                map.put(Uri.encode(str), Uri.encode(str2));
            } else {
                map.put(str, str2);
            }
        }
    }

    public static String convertURLParams(Map map, boolean z) {
        String str = BuildConfig.FLAVOR;
        String str2 = str;
        for (Entry entry : map.entrySet()) {
            if (str2.length() > 0) {
                str2 = str2 + "&";
            }
            if (z) {
                str2 = str2 + Uri.encode((String) entry.getKey()) + Operation.EQUALS + Uri.encode((String) entry.getValue());
            } else {
                str2 = str2 + ((String) entry.getKey()) + Operation.EQUALS + ((String) entry.getValue());
            }
        }
        return str2;
    }

    public static Map convertURLParams(String str, boolean z) {
        Map hashMap = new HashMap();
        Object obj = BuildConfig.FLAVOR;
        Object obj2 = BuildConfig.FLAVOR;
        Object obj3 = null;
        int i = 0;
        while (i < str.length() && i != -1) {
            char charAt = str.charAt(i);
            if (obj3 == null) {
                if (charAt == '=') {
                    if (z) {
                        obj2 = Uri.decode(obj);
                    } else {
                        obj2 = obj;
                    }
                    obj = BuildConfig.FLAVOR;
                    obj3 = 1;
                } else {
                    obj = obj + charAt;
                }
            } else if (obj3 == 1) {
                if (charAt == '&') {
                    if (z) {
                        obj3 = Uri.decode(obj);
                    } else {
                        obj3 = obj;
                    }
                    obj = BuildConfig.FLAVOR;
                    hashMap.put(obj2, obj3);
                    obj3 = null;
                } else {
                    obj = obj + charAt;
                }
            }
            i++;
        }
        if (obj3 == 1 && obj.length() > 0) {
            if (z) {
                obj = Uri.decode(obj);
            }
            hashMap.put(obj2, obj);
        }
        return hashMap;
    }

    public static String copyTextFromJarIntoString(String str) {
        return copyTextFromJarIntoString(str, null);
    }

    public static Bitmap loadBitmapFromJar(String str, Context context) {
        Bitmap decodeByteArray;
        Exception e;
        Throwable th;
        InputStream inputStream = null;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        byte[] bArr = (byte[]) getResource(str);
        if (bArr != null) {
            BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
            decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        } else {
            decodeByteArray = null;
        }
        if (decodeByteArray == null) {
            String str2 = "com/tapjoy/res/" + str;
            InputStream open;
            try {
                URL resource = TapjoyUtil.class.getClassLoader().getResource(str2);
                if (resource == null) {
                    AssetManager assets = context.getAssets();
                    try {
                        BitmapFactory.decodeStream(assets.open(str2), null, options);
                        open = assets.open(str2);
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            e.printStackTrace();
                            if (open != null) {
                                try {
                                    open.close();
                                } catch (IOException e3) {
                                }
                            }
                            return null;
                        } catch (Throwable th2) {
                            th = th2;
                            inputStream = open;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e4) {
                                }
                            }
                            throw th;
                        }
                    }
                }
                String file = resource.getFile();
                if (file.startsWith("jar:")) {
                    file = file.substring(4);
                }
                if (file.startsWith("file:")) {
                    file = file.substring(5);
                }
                int indexOf = file.indexOf("!");
                if (indexOf > 0) {
                    file = file.substring(0, indexOf);
                }
                JarFile jarFile = new JarFile(file);
                ZipEntry jarEntry = jarFile.getJarEntry(str2);
                BitmapFactory.decodeStream(jarFile.getInputStream(jarEntry), null, options);
                open = jarFile.getInputStream(jarEntry);
                decodeByteArray = BitmapFactory.decodeStream(open);
                if (open != null) {
                    try {
                        open.close();
                    } catch (IOException e5) {
                    }
                }
            } catch (Exception e6) {
                e = e6;
                open = null;
                e.printStackTrace();
                if (open != null) {
                    open.close();
                }
                return null;
            } catch (Throwable th3) {
                th = th3;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        }
        float deviceScreenDensityScale = TapjoyConnectCore.getDeviceScreenDensityScale();
        if (decodeByteArray != null) {
            return Bitmap.createScaledBitmap(decodeByteArray, (int) (((float) options.outWidth) * deviceScreenDensityScale), (int) (deviceScreenDensityScale * ((float) options.outHeight)), true);
        }
        return decodeByteArray;
    }

    public static String copyTextFromJarIntoString(String str, Context context) {
        InputStream inputStream;
        Throwable th;
        String str2 = null;
        byte[] bArr = new byte[1024];
        StringBuffer stringBuffer = new StringBuffer();
        URL resource = TapjoyUtil.class.getClassLoader().getResource(str);
        if (context == null || resource != null) {
            String file = resource.getFile();
            if (file.startsWith("jar:")) {
                file = file.substring(4);
            }
            if (file.startsWith("file:")) {
                file = file.substring(5);
            }
            int indexOf = file.indexOf("!");
            if (indexOf > 0) {
                file = file.substring(0, indexOf);
            }
            JarFile jarFile = new JarFile(file);
            inputStream = jarFile.getInputStream(jarFile.getJarEntry(str));
        } else {
            try {
                inputStream = context.getAssets().open(str);
            } catch (Exception e) {
                Exception e2 = e;
                Object obj = str2;
                try {
                    TapjoyLog.d("TapjoyUtil", "file exception: " + e2.toString());
                    e2.printStackTrace();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                inputStream = str2;
                th = th3;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        }
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                stringBuffer.append(new String(bArr).substring(0, read));
            } catch (Exception e5) {
                e2 = e5;
            }
        }
        str2 = stringBuffer.toString();
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e6) {
            }
        }
        return str2;
    }

    public static void setResource(String str, Object obj) {
        b.put(str, obj);
    }

    public static Object getResource(String str) {
        return b.get(str);
    }

    public static String getRedirectDomain(String str) {
        String str2 = BuildConfig.FLAVOR;
        if (str != null) {
            return str.substring(str.indexOf("//") + 2, str.lastIndexOf(Operation.DIVISION));
        }
        return str2;
    }

    public static String determineMimeType(String str) {
        String str2 = BuildConfig.FLAVOR;
        if (str.endsWith(".")) {
            str = str.substring(0, str.length() - 1);
        }
        if (str.lastIndexOf(46) != -1) {
            str2 = str.substring(str.lastIndexOf(46) + 1);
        }
        if (str2.equals("css")) {
            return "text/css";
        }
        if (str2.equals("js")) {
            return "text/javascript";
        }
        if (str2.equals(ApiAccessUtil.WEBAPI_KEY_HTML)) {
            return "text/html";
        }
        return "application/octet-stream";
    }

    public static Map jsonToStringMap(JSONObject jSONObject) {
        Map hashMap = new HashMap();
        if (jSONObject != JSONObject.NULL) {
            return toStringMap(jSONObject);
        }
        return hashMap;
    }

    public static Map toStringMap(JSONObject jSONObject) {
        Map hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, jSONObject.get(str).toString());
        }
        return hashMap;
    }

    public static void runOnMainThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }
}
