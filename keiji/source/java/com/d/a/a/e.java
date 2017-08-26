package com.d.a.a;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

/* compiled from: FileAsyncHttpResponseHandler */
public abstract class e extends c {
    private static final String LOG_TAG = "FileAsyncHttpRH";
    protected final boolean append;
    protected final File file;
    protected File frontendFile;
    protected final boolean renameIfExists;

    public abstract void onFailure(int i, Header[] headerArr, Throwable th, File file);

    public abstract void onSuccess(int i, Header[] headerArr, File file);

    public e(File file) {
        this(file, false);
    }

    public e(File file, boolean z) {
        this(file, z, false);
    }

    public e(File file, boolean z, boolean z2) {
        p.a(file != null, "File passed into FileAsyncHttpResponseHandler constructor must not be null");
        if (!(file.isDirectory() || file.getParentFile().isDirectory())) {
            p.a(file.getParentFile().mkdirs(), "Cannot create parent directories for requested File location");
        }
        if (file.isDirectory() && !file.mkdirs()) {
            a.a.b(LOG_TAG, "Cannot create directories for requested Directory location, might not be a problem");
        }
        this.file = file;
        this.append = z;
        this.renameIfExists = z2;
    }

    public e(Context context) {
        this.file = getTemporaryFile(context);
        this.append = false;
        this.renameIfExists = false;
    }

    public boolean deleteTargetFile() {
        return getTargetFile() != null && getTargetFile().delete();
    }

    protected File getTemporaryFile(Context context) {
        p.a(context != null, "Tried creating temporary file without having Context");
        try {
            return File.createTempFile("temp_", "_handled", context.getCacheDir());
        } catch (Throwable e) {
            a.a.b(LOG_TAG, "Cannot create temporary file", e);
            return null;
        }
    }

    protected File getOriginalFile() {
        p.a(this.file != null, "Target file is null, fatal!");
        return this.file;
    }

    public File getTargetFile() {
        if (this.frontendFile == null) {
            this.frontendFile = getOriginalFile().isDirectory() ? getTargetFileByParsingURL() : getOriginalFile();
        }
        return this.frontendFile;
    }

    protected File getTargetFileByParsingURL() {
        p.a(getOriginalFile().isDirectory(), "Target file is not a directory, cannot proceed");
        p.a(getRequestURI() != null, "RequestURI is null, cannot proceed");
        String uri = getRequestURI().toString();
        String substring = uri.substring(uri.lastIndexOf(47) + 1, uri.length());
        File file = new File(getOriginalFile(), substring);
        if (!file.exists() || !this.renameIfExists) {
            return file;
        }
        if (substring.contains(".")) {
            uri = substring.substring(0, substring.lastIndexOf(46)) + " (%d)" + substring.substring(substring.lastIndexOf(46), substring.length());
        } else {
            uri = substring + " (%d)";
        }
        int i = 0;
        while (true) {
            File file2 = new File(getOriginalFile(), String.format(uri, new Object[]{Integer.valueOf(i)}));
            if (!file2.exists()) {
                return file2;
            }
            i++;
        }
    }

    public final void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
        onFailure(i, headerArr, th, getTargetFile());
    }

    public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
        onSuccess(i, headerArr, getTargetFile());
    }

    protected byte[] getResponseData(HttpEntity httpEntity) {
        int i = 0;
        if (httpEntity != null) {
            InputStream content = httpEntity.getContent();
            long contentLength = httpEntity.getContentLength();
            OutputStream fileOutputStream = new FileOutputStream(getTargetFile(), this.append);
            if (content != null) {
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = content.read(bArr);
                        if (read == -1 || Thread.currentThread().isInterrupted()) {
                            a.a(content);
                            fileOutputStream.flush();
                            a.a(fileOutputStream);
                        } else {
                            i += read;
                            fileOutputStream.write(bArr, 0, read);
                            sendProgressMessage((long) i, contentLength);
                        }
                    }
                    a.a(content);
                    fileOutputStream.flush();
                    a.a(fileOutputStream);
                } catch (Throwable th) {
                    a.a(content);
                    fileOutputStream.flush();
                    a.a(fileOutputStream);
                }
            }
        }
        return null;
    }
}
