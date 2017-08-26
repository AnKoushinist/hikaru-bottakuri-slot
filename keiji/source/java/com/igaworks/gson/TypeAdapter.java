package com.igaworks.gson;

import com.igaworks.gson.internal.bind.JsonTreeWriter;
import com.igaworks.gson.stream.JsonReader;
import com.igaworks.gson.stream.JsonWriter;

public abstract class TypeAdapter<T> {
    public abstract T read(JsonReader jsonReader);

    public abstract void write(JsonWriter jsonWriter, T t);

    public final JsonElement toJsonTree(T t) {
        try {
            JsonWriter jsonTreeWriter = new JsonTreeWriter();
            write(jsonTreeWriter, t);
            return jsonTreeWriter.get();
        } catch (Throwable e) {
            throw new JsonIOException(e);
        }
    }
}
