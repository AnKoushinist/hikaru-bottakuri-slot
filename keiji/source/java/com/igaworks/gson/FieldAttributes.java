package com.igaworks.gson;

import com.igaworks.gson.internal.C$Gson$Preconditions;
import java.lang.reflect.Field;

public final class FieldAttributes {
    private final Field field;

    public FieldAttributes(Field field) {
        C$Gson$Preconditions.checkNotNull(field);
        this.field = field;
    }
}
