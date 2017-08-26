package com.igaworks.gson.internal;

import com.igaworks.gson.JsonElement;
import com.igaworks.gson.internal.bind.TypeAdapters;
import com.igaworks.gson.stream.JsonWriter;

public final class Streams {
    public static void write(JsonElement jsonElement, JsonWriter jsonWriter) {
        TypeAdapters.JSON_ELEMENT.write(jsonWriter, jsonElement);
    }
}
