package com.igaworks.util.image;

import java.io.File;
import java.io.OutputStream;

public abstract class ByteProviderUtil {

    class AnonymousClass2 implements ByteProvider {
        private final /* synthetic */ File val$file;

        AnonymousClass2(File file) {
            this.val$file = file;
        }

        public void writeTo(OutputStream outputStream) {
            IOUtils.copy(this.val$file, outputStream);
        }
    }

    public static ByteProvider create(File file) {
        return new AnonymousClass2(file);
    }
}
