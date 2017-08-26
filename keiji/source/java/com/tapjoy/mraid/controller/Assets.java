package com.tapjoy.mraid.controller;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Environment;
import android.os.StatFs;
import android.webkit.JavascriptInterface;
import com.tapjoy.TapjoyLog;
import com.tapjoy.internal.da;
import com.tapjoy.internal.em;
import com.tapjoy.mraid.view.MraidView;
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
    private static final char[] d = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
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
            File file = new File(Environment.getExternalStorageDirectory().toString() + "/" + str2);
            long currentTimeMillis = System.currentTimeMillis();
            TapjoyLog.d("MRAID Assets", "download beginning");
            TapjoyLog.d("MRAID Assets", "download url:" + url);
            TapjoyLog.d("MRAID Assets", "downloaded file name:" + str2);
            InputStream inputStream = em.a(url).getInputStream();
            OutputStream fileOutputStream = new FileOutputStream(file);
            da.a(inputStream, fileOutputStream);
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
            inputStream = em.a(str2);
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
        int i = 0;
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
                bArr = instance.digest();
                char[] cArr = new char[(bArr.length * 2)];
                int i2 = 0;
                while (i < bArr.length) {
                    int i3 = i2 + 1;
                    cArr[i2] = d[(bArr[i] >>> 4) & 15];
                    i2 = i3 + 1;
                    cArr[i3] = d[bArr[i] & 15];
                    i++;
                }
                str2 = new String(cArr);
                File file = new File(a + File.separator + str);
                new File(a + File.separator + "ad").mkdir();
                File file2 = new File(a + File.separator + "ad" + File.separator + str2);
                file2.mkdir();
                file.renameTo(new File(file2, file.getName()));
                str2 = file2.getPath() + File.separator;
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
        String str2 = "/";
        if (str.lastIndexOf(File.separatorChar) >= 0) {
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
        return "file://" + this.b.getFilesDir() + "/";
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
