package com.igaworks.util.image;

import java.io.File;
import java.util.concurrent.Callable;

public class FileDownloadCallable implements Callable<File> {
    private File file;
    private String url;

    public FileDownloadCallable(String str, File file) {
        this.url = str;
        this.file = file;
    }

    public File call() {
        return HttpRequestHelper.getInstance().download(this.url, this.file);
    }
}
