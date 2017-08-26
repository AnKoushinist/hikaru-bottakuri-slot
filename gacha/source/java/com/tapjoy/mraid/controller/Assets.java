package com.tapjoy.mraid.controller;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Environment;
import android.os.StatFs;
import android.webkit.JavascriptInterface;
import com.applovin.sdk.AppLovinTargetingData;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TapjoyLog;
import com.tapjoy.internal.cy;
import com.tapjoy.internal.eh;
import com.tapjoy.mraid.view.MraidView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.jar.JarFile;

public class Assets extends Abstract {
    private static final char[] d = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', AppLovinTargetingData.GENDER_FEMALE};
    private int c = 0;

    public Assets(MraidView mraidView, Context context) {
        super(mraidView, context);
    }

    @JavascriptInterface
    public void storePictureInit(final String str) {
        Builder builder = new Builder(this.b);
        builder.setMessage("Are you sure you want to save file from " + str + " to your SD card?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new OnClickListener(this) {
            final /* synthetic */ Assets b;

            public final void onClick(DialogInterface dialogInterface, int i) {
                this.b.storePicture(str);
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    public void storePicture(String str) {
        TapjoyLog.d("MRAID Assets", "Storing media from " + str + " to device photo album.  Output directory: " + Environment.getExternalStorageDirectory() + " state: " + Environment.getExternalStorageState());
        this.c++;
        try {
            URL url = new URL(str);
            String str2 = "MraidMedia" + this.c + ".jpg";
            File file = new File(Environment.getExternalStorageDirectory().toString() + Operation.DIVISION + str2);
            long currentTimeMillis = System.currentTimeMillis();
            TapjoyLog.d("MRAID Assets", "download beginning");
            TapjoyLog.d("MRAID Assets", "download url:" + url);
            TapjoyLog.d("MRAID Assets", "downloaded file name:" + str2);
            InputStream inputStream = eh.a(url).getInputStream();
            OutputStream fileOutputStream = new FileOutputStream(file);
            cy.a(inputStream, fileOutputStream);
            fileOutputStream.close();
            TapjoyLog.d("MRAID Assets", "download ready in" + ((System.currentTimeMillis() - currentTimeMillis) / 1000) + " sec");
        } catch (IOException e) {
            TapjoyLog.d("MRAID Assets", "Error: " + e);
        }
    }

    public String copyTextFromJarIntoAssetDir(String str, String str2) {
        InputStream open;
        Exception e;
        Throwable th;
        String str3 = null;
        try {
            URL resource = Assets.class.getClassLoader().getResource(str2);
            if (resource == null) {
                open = this.b.getAssets().open(str2);
            } else {
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
                open = jarFile.getInputStream(jarFile.getJarEntry(str2));
            }
            try {
                str3 = writeToDisk(open, str, false);
                if (open != null) {
                    try {
                        open.close();
                    } catch (Exception e2) {
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    TapjoyLog.e("MRAID Assets", "copyTextFromJarIntoAssetDir: " + e.toString());
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Exception e4) {
                        }
                    }
                    return str3;
                } catch (Throwable th2) {
                    th = th2;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Exception e5) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e6) {
            e = e6;
            open = str3;
            TapjoyLog.e("MRAID Assets", "copyTextFromJarIntoAssetDir: " + e.toString());
            if (open != null) {
                open.close();
            }
            return str3;
        } catch (Throwable th3) {
            open = str3;
            th = th3;
            if (open != null) {
                open.close();
            }
            throw th;
        }
        return str3;
    }

    public void addAsset(String str, String str2) {
        InputStream inputStream = null;
        try {
            inputStream = eh.a(str2);
            writeToDisk(inputStream, str, false);
            this.a.injectMraidJavaScript("MraidAdController.addedAsset('" + str + "' )");
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e3) {
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e4) {
                }
            }
        }
    }

    public int cacheRemaining() {
        StatFs statFs = new StatFs(this.b.getFilesDir().getPath());
        return statFs.getFreeBlocks() * statFs.getBlockSize();
    }

    public String writeToDisk(InputStream inputStream, String str, boolean z) {
        MessageDigest instance;
        int read;
        String a;
        String str2;
        FileOutputStream fileOutputStream = null;
        byte[] bArr = new byte[1024];
        if (z) {
            try {
                instance = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            fileOutputStream = getAssetOutputString(str);
            while (true) {
                read = inputStream.read(bArr);
                if (read > 0) {
                    break;
                }
                if (z && instance != null) {
                    instance.update(bArr);
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.flush();
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e2) {
                }
            }
            a = a();
            if (z || instance == null) {
                str2 = a;
            } else {
                str2 = a(str, a, a(instance));
            }
            return str2 + str;
        }
        instance = null;
        try {
            fileOutputStream = getAssetOutputString(str);
            while (true) {
                read = inputStream.read(bArr);
                if (read > 0) {
                    break;
                }
                instance.update(bArr);
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.flush();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            a = a();
            if (z) {
            }
            str2 = a;
            return str2 + str;
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e3) {
                }
            }
        }
    }

    public String writeToDiskWrap(InputStream inputStream, String str, boolean z, String str2, String str3) {
        MessageDigest instance;
        ByteArrayOutputStream byteArrayOutputStream;
        int read;
        String byteArrayOutputStream2;
        StringBuffer stringBuffer;
        String a;
        FileOutputStream fileOutputStream = null;
        Object obj = null;
        byte[] bArr = new byte[1024];
        if (z) {
            try {
                instance = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                try {
                    read = inputStream.read(bArr);
                    if (read > 0) {
                        break;
                    }
                    if (z && instance != null) {
                        instance.update(bArr);
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                } catch (Throwable th) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e2) {
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                }
            }
            byteArrayOutputStream2 = byteArrayOutputStream.toString();
            if (byteArrayOutputStream2.indexOf("</html>") >= 0) {
                obj = 1;
            }
            stringBuffer = new StringBuffer(byteArrayOutputStream2);
            if (obj != null) {
                if (a(stringBuffer, "mraid.js")) {
                    a(stringBuffer, "mraid.js", str3);
                } else if (a(stringBuffer, "ormma.js")) {
                    a(stringBuffer, "ormma.js", str3);
                } else if (a(stringBuffer, "ormma_bridge.js")) {
                    a(stringBuffer, "ormma_bridge.js", str3);
                }
            }
            fileOutputStream = getAssetOutputString(str);
            if (obj == null) {
                fileOutputStream.write("<html>".getBytes());
                fileOutputStream.write("<head>".getBytes());
                fileOutputStream.write("<meta name='viewport' content='user-scalable=no initial-scale=1.0' />".getBytes());
                fileOutputStream.write("<title>Advertisement</title> ".getBytes());
                fileOutputStream.write(("<script src=\"file://" + str3 + "\" type=\"text/javascript\"></script>").getBytes());
                if (str2 != null) {
                    fileOutputStream.write("<script type=\"text/javascript\">".getBytes());
                    fileOutputStream.write(str2.getBytes());
                    fileOutputStream.write("</script>".getBytes());
                }
                fileOutputStream.write("</head>".getBytes());
                fileOutputStream.write("<body style=\"margin:0; padding:0; overflow:hidden; background-color:transparent;\">".getBytes());
                fileOutputStream.write("<div align=\"center\"> ".getBytes());
            }
            if (obj != null) {
                fileOutputStream.write(byteArrayOutputStream.toByteArray());
            } else {
                fileOutputStream.write(stringBuffer.toString().getBytes());
            }
            if (obj == null) {
                fileOutputStream.write("</div> ".getBytes());
                fileOutputStream.write("</body> ".getBytes());
                fileOutputStream.write("</html> ".getBytes());
            }
            fileOutputStream.flush();
            byteArrayOutputStream.close();
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e4) {
                }
            }
            a = a();
            if (z || instance == null) {
                return a;
            }
            return a(str, a, a(instance));
        }
        Object obj2 = fileOutputStream;
        byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            read = inputStream.read(bArr);
            if (read > 0) {
                break;
            }
            instance.update(bArr);
            byteArrayOutputStream.write(bArr, 0, read);
        }
        byteArrayOutputStream2 = byteArrayOutputStream.toString();
        if (byteArrayOutputStream2.indexOf("</html>") >= 0) {
            obj = 1;
        }
        stringBuffer = new StringBuffer(byteArrayOutputStream2);
        if (obj != null) {
            if (a(stringBuffer, "mraid.js")) {
                a(stringBuffer, "mraid.js", str3);
            } else if (a(stringBuffer, "ormma.js")) {
                a(stringBuffer, "ormma.js", str3);
            } else if (a(stringBuffer, "ormma_bridge.js")) {
                a(stringBuffer, "ormma_bridge.js", str3);
            }
        }
        fileOutputStream = getAssetOutputString(str);
        if (obj == null) {
            fileOutputStream.write("<html>".getBytes());
            fileOutputStream.write("<head>".getBytes());
            fileOutputStream.write("<meta name='viewport' content='user-scalable=no initial-scale=1.0' />".getBytes());
            fileOutputStream.write("<title>Advertisement</title> ".getBytes());
            fileOutputStream.write(("<script src=\"file://" + str3 + "\" type=\"text/javascript\"></script>").getBytes());
            if (str2 != null) {
                fileOutputStream.write("<script type=\"text/javascript\">".getBytes());
                fileOutputStream.write(str2.getBytes());
                fileOutputStream.write("</script>".getBytes());
            }
            fileOutputStream.write("</head>".getBytes());
            fileOutputStream.write("<body style=\"margin:0; padding:0; overflow:hidden; background-color:transparent;\">".getBytes());
            fileOutputStream.write("<div align=\"center\"> ".getBytes());
        }
        if (obj != null) {
            fileOutputStream.write(stringBuffer.toString().getBytes());
        } else {
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
        }
        if (obj == null) {
            fileOutputStream.write("</div> ".getBytes());
            fileOutputStream.write("</body> ".getBytes());
            fileOutputStream.write("</html> ".getBytes());
        }
        fileOutputStream.flush();
        try {
            byteArrayOutputStream.close();
        } catch (Exception e5) {
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        a = a();
        if (z) {
        }
        return a;
    }

    private static boolean a(StringBuffer stringBuffer, String str) {
        try {
            if (stringBuffer.indexOf(str) >= 0) {
                return true;
            }
        } catch (Exception e) {
            TapjoyLog.d("contains", "html file does not contain " + str);
        }
        return false;
    }

    private static void a(StringBuffer stringBuffer, String str, String str2) {
        int indexOf = stringBuffer.indexOf(str);
        TapjoyLog.d("replace ", str2);
        stringBuffer.replace(indexOf, str.length() + indexOf, "file://" + str2);
    }

    private static String a(String str, String str2, String str3) {
        File file = new File(str2 + File.separator + str);
        new File(str2 + File.separator + "ad").mkdir();
        File file2 = new File(str2 + File.separator + "ad" + File.separator + str3);
        file2.mkdir();
        file.renameTo(new File(file2, file.getName()));
        return file2.getPath() + File.separator;
    }

    private static String a(MessageDigest messageDigest) {
        int i = 0;
        byte[] digest = messageDigest.digest();
        char[] cArr = new char[(digest.length * 2)];
        for (int i2 = 0; i2 < digest.length; i2++) {
            int i3 = i + 1;
            cArr[i] = d[(digest[i2] >>> 4) & 15];
            i = i3 + 1;
            cArr[i3] = d[digest[i2] & 15];
        }
        return new String(cArr);
    }

    private String a() {
        return this.b.getFilesDir().getPath();
    }

    public FileOutputStream getAssetOutputString(String str) {
        File a = a(b(str));
        a.mkdirs();
        return new FileOutputStream(new File(a, c(str)));
    }

    public void removeAsset(String str) {
        File a = a(b(str));
        a.mkdirs();
        new File(a, c(str)).delete();
        this.a.injectMraidJavaScript("MraidAdController.assetRemoved('" + str + "' )");
    }

    private File a(String str) {
        return new File(this.b.getFilesDir().getPath() + File.separator + str);
    }

    private static String b(String str) {
        int lastIndexOf = str.lastIndexOf(File.separatorChar);
        String str2 = Operation.DIVISION;
        if (lastIndexOf >= 0) {
            return str.substring(0, str.lastIndexOf(File.separatorChar));
        }
        return str2;
    }

    private static String c(String str) {
        if (str.lastIndexOf(File.separatorChar) >= 0) {
            return str.substring(str.lastIndexOf(File.separatorChar) + 1);
        }
        return str;
    }

    public String getAssetPath() {
        return "file://" + this.b.getFilesDir() + Operation.DIVISION;
    }

    public static boolean deleteDirectory(String str) {
        if (str != null) {
            return deleteDirectory(new File(str));
        }
        return false;
    }

    public static boolean deleteDirectory(File file) {
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    deleteDirectory(listFiles[i]);
                } else {
                    listFiles[i].delete();
                }
            }
        }
        return file.delete();
    }

    public void deleteOldAds() {
        deleteDirectory(new File(a() + File.separator + "ad"));
    }

    public void stopAllListeners() {
    }
}
