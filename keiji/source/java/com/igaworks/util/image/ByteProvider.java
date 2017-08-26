package com.igaworks.util.image;

import java.io.OutputStream;

public interface ByteProvider {
    void writeTo(OutputStream outputStream);
}
