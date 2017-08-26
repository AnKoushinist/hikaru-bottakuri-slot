package org.cocos2dx.lib;

import com.d.a.a.a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import javax.net.ssl.SSLException;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public class Cocos2dxDownloader {
    private int _countOfMaxProcessingTasks;
    private a _httpClient = new a();
    private int _id;
    private int _runningTaskCount = 0;
    private HashMap _taskMap = new HashMap();
    private Queue<Runnable> _taskQueue = new LinkedList();
    private String _tempFileNameSufix;

    native void nativeOnFinish(int i, int i2, int i3, String str, byte[] bArr);

    native void nativeOnProgress(int i, int i2, long j, long j2, long j3);

    void onProgress(int i, long j, long j2, long j3) {
        DownloadTask downloadTask = (DownloadTask) this._taskMap.get(Integer.valueOf(i));
        if (downloadTask != null) {
            downloadTask.bytesReceived = j;
            downloadTask.totalBytesReceived = j2;
            downloadTask.totalBytesExpected = j3;
        }
        final int i2 = i;
        final long j4 = j;
        final long j5 = j2;
        final long j6 = j3;
        Cocos2dxHelper.runOnGLThread(new Runnable() {
            public void run() {
                Cocos2dxDownloader.this.nativeOnProgress(Cocos2dxDownloader.this._id, i2, j4, j5, j6);
            }
        });
    }

    public void onStart(int i) {
        DownloadTask downloadTask = (DownloadTask) this._taskMap.get(Integer.valueOf(i));
        if (downloadTask != null) {
            downloadTask.resetStatus();
        }
    }

    public void onFinish(int i, int i2, String str, byte[] bArr) {
        if (((DownloadTask) this._taskMap.get(Integer.valueOf(i))) != null) {
            this._taskMap.remove(Integer.valueOf(i));
            final int i3 = i;
            final int i4 = i2;
            final String str2 = str;
            final byte[] bArr2 = bArr;
            Cocos2dxHelper.runOnGLThread(new Runnable() {
                public void run() {
                    Cocos2dxDownloader.this.nativeOnFinish(Cocos2dxDownloader.this._id, i3, i4, str2, bArr2);
                }
            });
        }
    }

    public static Cocos2dxDownloader createDownloader(int i, int i2, String str, int i3) {
        Cocos2dxDownloader cocos2dxDownloader = new Cocos2dxDownloader();
        cocos2dxDownloader._id = i;
        cocos2dxDownloader._httpClient.a(true);
        if (i2 > 0) {
            cocos2dxDownloader._httpClient.a(i2 * GameControllerDelegate.THUMBSTICK_LEFT_X);
        }
        a aVar = cocos2dxDownloader._httpClient;
        a.a(SSLException.class);
        cocos2dxDownloader._tempFileNameSufix = str;
        cocos2dxDownloader._countOfMaxProcessingTasks = i3;
        return cocos2dxDownloader;
    }

    public static void createTask(final Cocos2dxDownloader cocos2dxDownloader, final int i, final String str, final String str2) {
        cocos2dxDownloader.enqueueTask(new Runnable() {
            public void run() {
                DownloadTask downloadTask = new DownloadTask();
                if (str2.length() == 0) {
                    downloadTask.handler = new DataTaskHandler(cocos2dxDownloader, i);
                    downloadTask.handle = cocos2dxDownloader._httpClient.a(Cocos2dxHelper.getActivity(), str, downloadTask.handler);
                }
                if (str2.length() != 0) {
                    File file = new File(str2 + cocos2dxDownloader._tempFileNameSufix);
                    if (!file.isDirectory()) {
                        File parentFile = file.getParentFile();
                        if (parentFile.isDirectory() || parentFile.mkdirs()) {
                            parentFile = new File(str2);
                            if (!file.isDirectory()) {
                                Header[] headerArr;
                                downloadTask.handler = new FileTaskHandler(cocos2dxDownloader, i, file, parentFile);
                                long length = file.length();
                                if (length > 0) {
                                    List arrayList = new ArrayList();
                                    arrayList.add(new BasicHeader("Range", "bytes=" + length + "-"));
                                    headerArr = (Header[]) arrayList.toArray(new Header[arrayList.size()]);
                                } else {
                                    headerArr = null;
                                }
                                downloadTask.handle = cocos2dxDownloader._httpClient.a(Cocos2dxHelper.getActivity(), str, headerArr, null, downloadTask.handler);
                            }
                        }
                    }
                }
                if (downloadTask.handle == null) {
                    final String str = "Can't create DownloadTask for " + str;
                    Cocos2dxHelper.runOnGLThread(new Runnable() {
                        public void run() {
                            cocos2dxDownloader.nativeOnFinish(cocos2dxDownloader._id, i, 0, str, null);
                        }
                    });
                    return;
                }
                cocos2dxDownloader._taskMap.put(Integer.valueOf(i), downloadTask);
            }
        });
    }

    public static void cancelAllRequests(final Cocos2dxDownloader cocos2dxDownloader) {
        Cocos2dxHelper.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                for (Entry value : cocos2dxDownloader._taskMap.entrySet()) {
                    DownloadTask downloadTask = (DownloadTask) value.getValue();
                    if (downloadTask.handle != null) {
                        downloadTask.handle.a(true);
                    }
                }
            }
        });
    }

    public void enqueueTask(Runnable runnable) {
        synchronized (this._taskQueue) {
            if (this._runningTaskCount < this._countOfMaxProcessingTasks) {
                Cocos2dxHelper.getActivity().runOnUiThread(runnable);
                this._runningTaskCount++;
            } else {
                this._taskQueue.add(runnable);
            }
        }
    }

    public void runNextTaskIfExists() {
        synchronized (this._taskQueue) {
            Runnable runnable = (Runnable) this._taskQueue.poll();
            if (runnable != null) {
                Cocos2dxHelper.getActivity().runOnUiThread(runnable);
            } else {
                this._runningTaskCount--;
            }
        }
    }
}
