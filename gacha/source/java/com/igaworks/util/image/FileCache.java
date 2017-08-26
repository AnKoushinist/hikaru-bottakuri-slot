package com.igaworks.util.image;

import java.io.File;

public interface FileCache {
    FileEntry get(String str);

    void put(String str, ByteProvider byteProvider);

    void put(String str, File file, boolean z);
}
