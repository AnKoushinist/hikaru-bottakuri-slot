package com.igaworks.gson.internal;

import com.igaworks.gson.stream.JsonReader;

public abstract class JsonReaderInternalAccess {
    public static JsonReaderInternalAccess INSTANCE;

    public abstract void promoteNameToValue(JsonReader jsonReader);
}
