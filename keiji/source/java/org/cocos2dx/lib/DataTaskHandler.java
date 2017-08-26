package org.cocos2dx.lib;

import android.util.Log;
import com.d.a.a.d;
import org.apache.http.Header;

/* compiled from: Cocos2dxDownloader */
class DataTaskHandler extends d {
    private Cocos2dxDownloader _downloader;
    int _id;
    private long _lastBytesWritten = 0;

    void LogD(String str) {
        Log.d("Cocos2dxDownloader", str);
    }

    public DataTaskHandler(Cocos2dxDownloader cocos2dxDownloader, int i) {
        super(new String[]{".*"});
        this._downloader = cocos2dxDownloader;
        this._id = i;
    }

    public void onProgress(long j, long j2) {
        this._downloader.onProgress(this._id, j - this._lastBytesWritten, j, j2);
        this._lastBytesWritten = j;
    }

    public void onStart() {
        this._downloader.onStart(this._id);
    }

    public void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        LogD("onFailure(i:" + i + " headers:" + headerArr + " throwable:" + th);
        String str = BuildConfig.FLAVOR;
        if (th != null) {
            str = th.toString();
        }
        this._downloader.onFinish(this._id, i, str, null);
    }

    public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        LogD("onSuccess(i:" + i + " headers:" + headerArr);
        this._downloader.onFinish(this._id, 0, null, bArr);
    }
}
