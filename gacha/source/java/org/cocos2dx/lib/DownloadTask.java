package org.cocos2dx.lib;

import com.d.a.a.c;
import com.d.a.a.l;

/* compiled from: Cocos2dxDownloader */
class DownloadTask {
    long bytesReceived;
    byte[] data;
    l handle = null;
    c handler = null;
    long totalBytesExpected;
    long totalBytesReceived;

    DownloadTask() {
        resetStatus();
    }

    void resetStatus() {
        this.bytesReceived = 0;
        this.totalBytesReceived = 0;
        this.totalBytesExpected = 0;
        this.data = null;
    }
}
