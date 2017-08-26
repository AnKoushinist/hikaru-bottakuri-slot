package org.cocos2dx.lib;

import android.util.Log;
import com.d.a.a.e;
import java.io.File;
import org.apache.http.Header;

/* compiled from: Cocos2dxDownloader */
class FileTaskHandler extends e {
    private Cocos2dxDownloader _downloader;
    File _finalFile;
    int _id;
    private long _initFileLen = getTargetFile().length();
    private long _lastBytesWritten = 0;

    void LogD(String str) {
        Log.d("Cocos2dxDownloader", str);
    }

    public FileTaskHandler(Cocos2dxDownloader cocos2dxDownloader, int i, File file, File file2) {
        super(file, true);
        this._finalFile = file2;
        this._downloader = cocos2dxDownloader;
        this._id = i;
    }

    public void onProgress(long j, long j2) {
        this._downloader.onProgress(this._id, j - this._lastBytesWritten, j + this._initFileLen, j2 + this._initFileLen);
        this._lastBytesWritten = j;
    }

    public void onStart() {
        this._downloader.onStart(this._id);
    }

    public void onFinish() {
        this._downloader.runNextTaskIfExists();
    }

    public void onFailure(int i, Header[] headerArr, Throwable th, File file) {
        LogD("onFailure(i:" + i + " headers:" + headerArr + " throwable:" + th + " file:" + file);
        String str = BuildConfig.FLAVOR;
        if (th != null) {
            str = th.toString();
        }
        this._downloader.onFinish(this._id, i, str, null);
    }

    public void onSuccess(int i, Header[] headerArr, File file) {
        String str;
        LogD("onSuccess(i:" + i + " headers:" + headerArr + " file:" + file);
        if (this._finalFile.exists()) {
            if (this._finalFile.isDirectory()) {
                str = "Dest file is directory:" + this._finalFile.getAbsolutePath();
            } else if (!this._finalFile.delete()) {
                str = "Can't remove old file:" + this._finalFile.getAbsolutePath();
            }
            this._downloader.onFinish(this._id, 0, str, null);
        }
        getTargetFile().renameTo(this._finalFile);
        str = null;
        this._downloader.onFinish(this._id, 0, str, null);
    }
}
