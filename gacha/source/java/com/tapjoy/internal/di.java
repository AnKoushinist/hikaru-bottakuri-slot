package com.tapjoy.internal;

import java.io.InputStream;

public abstract class di implements dw {
    private static final dn a = dn.a();

    private static du a(du duVar) {
        if (duVar == null || duVar.c()) {
            return duVar;
        }
        dz dzVar;
        if (duVar instanceof dh) {
            dzVar = new dz();
        } else {
            dzVar = new dz();
        }
        dq dqVar = new dq(dzVar.getMessage());
        dqVar.a = duVar;
        throw dqVar;
    }

    private du a(byte[] bArr, int i, dn dnVar) {
        du duVar;
        try {
            dl a = dl.a(bArr, i);
            duVar = (du) a(a, dnVar);
            a.a(0);
            return duVar;
        } catch (dq e) {
            e.a = duVar;
            throw e;
        } catch (dq e2) {
            throw e2;
        }
    }

    private du a(InputStream inputStream, dn dnVar) {
        dl dlVar = new dl(inputStream);
        du duVar = (du) a(dlVar, dnVar);
        try {
            dlVar.a(0);
            return duVar;
        } catch (dq e) {
            e.a = duVar;
            throw e;
        }
    }
}
