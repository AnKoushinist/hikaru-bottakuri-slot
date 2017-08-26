package com.glossomads.View;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import com.d.a.a.c;
import com.glossomads.Logger.SugarDebugLogger;
import com.glossomads.Model.a;
import com.glossomads.SugarUtil;
import com.glossomads.s;
import com.raizlabs.android.dbflow.sql.language.Condition.Operation;
import com.tapjoy.TapjoyConnectCore;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import jp.reifrontier.silentlogsdkandroid.util.RFLConstants;
import org.cocos2dx.lib.BuildConfig;

public class v {
    public static int a = -1;
    public static int b = -2;

    public static Point a(int i, int i2, int i3, int i4) {
        Point point = new Point();
        point.set(i, i2);
        Point point2 = new Point();
        point2.set(i3, i4);
        return a(point, point2);
    }

    public static Point a(Point point, Point point2) {
        Point point3 = new Point();
        int i = point.x;
        int i2 = point.y;
        int i3 = point2.x;
        int i4 = point2.y;
        if (i3 < i4) {
            point3.set((int) (((((float) i2) * TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER) / (((float) i4) * TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER)) * ((float) i3)), i2);
        } else {
            point3.set(i, (int) (((((float) i) * TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER) / (((float) i3) * TapjoyConnectCore.DEFAULT_CURRENCY_MULTIPLIER)) * ((float) i4)));
        }
        return point3;
    }

    public static Bitmap a(File file, int i, int i2, long j) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(file.getPath());
        Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(RFLConstants.UPDATE_INTERVAL_IN_MILLISECONDS_ON_STAY * j);
        if (frameAtTime != null && frameAtTime.getWidth() > 0 && frameAtTime.getHeight() > 0 && i > 0 && i2 > 0) {
            Point point = new Point();
            point.set(i, i2);
            Point point2 = new Point();
            point2.set(frameAtTime.getWidth(), frameAtTime.getHeight());
            point = a(point, point2);
            frameAtTime = ThumbnailUtils.extractThumbnail(frameAtTime, point.x, point.y);
        }
        mediaMetadataRetriever.release();
        return frameAtTime;
    }

    public static boolean a(a aVar) {
        FileOutputStream fileOutputStream;
        Exception e;
        Throwable th;
        boolean z = false;
        if (aVar != null) {
            File file = new File(a());
            if (!file.exists()) {
                file.mkdirs();
            }
            String str = file.getPath() + Operation.DIVISION + d(aVar.l());
            File file2 = new File(str);
            if (file2.exists()) {
                file2.delete();
            }
            try {
                fileOutputStream = new FileOutputStream(new File(str));
                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, c.DEFAULT_CHARSET));
                    bufferedWriter.append(aVar.k());
                    z = true;
                    bufferedWriter.close();
                    SugarUtil.close(fileOutputStream);
                } catch (Exception e2) {
                    e = e2;
                    try {
                        SugarDebugLogger.printStackTrace(e);
                        SugarUtil.close(fileOutputStream);
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        SugarUtil.close(fileOutputStream);
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                fileOutputStream = null;
                SugarDebugLogger.printStackTrace(e);
                SugarUtil.close(fileOutputStream);
                return z;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                SugarUtil.close(fileOutputStream);
                throw th;
            }
        }
        return z;
    }

    public static void a(String str) {
        if (!SugarUtil.isEmptyValue(str)) {
            File file = new File(a() + Operation.DIVISION + d(str));
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static String a() {
        return s.f().getCacheDir().getAbsolutePath() + Operation.DIVISION + "sugar_endcard";
    }

    public static String b(String str) {
        if (SugarUtil.isEmptyValue(str)) {
            return null;
        }
        return "file://" + a() + Operation.DIVISION + d(str);
    }

    public static String c(String str) {
        if (SugarUtil.isEmptyValue(str)) {
            return null;
        }
        return a() + Operation.DIVISION + d(str);
    }

    public static String d(String str) {
        return str.replace(".", BuildConfig.FLAVOR).replace(Operation.DIVISION, BuildConfig.FLAVOR) + ".html";
    }

    public static void b() {
        File file = new File(a());
        if (file.exists()) {
            SugarUtil.deleteFile(file);
        }
        if (!file.mkdirs()) {
            SugarDebugLogger.e("making failed to end_card_dir.");
        }
    }
}
