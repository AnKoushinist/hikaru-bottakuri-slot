package com.igaworks.util.image;

import java.io.File;

public class FileEntry {
    private File file;
    private String key;

    public FileEntry(String str, File file) {
        this.key = str;
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }
}
