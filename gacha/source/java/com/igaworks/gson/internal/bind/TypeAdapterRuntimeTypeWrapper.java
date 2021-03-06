package com.igaworks.gson.internal.bind;

import com.igaworks.gson.Gson;
import com.igaworks.gson.TypeAdapter;
import com.igaworks.gson.internal.bind.ReflectiveTypeAdapterFactory.Adapter;
import com.igaworks.gson.reflect.TypeToken;
import com.igaworks.gson.stream.JsonReader;
import com.igaworks.gson.stream.JsonWriter;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class TypeAdapterRuntimeTypeWrapper<T> extends TypeAdapter<T> {
    private final Gson context;
    private final TypeAdapter<T> delegate;
    private final Type type;

    TypeAdapterRuntimeTypeWrapper(Gson gson, TypeAdapter<T> typeAdapter, Type type) {
        this.context = gson;
        this.delegate = typeAdapter;
        this.type = type;
    }

    public T read(JsonReader jsonReader) {
        return this.delegate.read(jsonReader);
    }

    public void write(JsonWriter jsonWriter, T t) {
        TypeAdapter typeAdapter = this.delegate;
        Type runtimeTypeIfMoreSpecific = getRuntimeTypeIfMoreSpecific(this.type, t);
        if (runtimeTypeIfMoreSpecific != this.type) {
            typeAdapter = this.context.getAdapter(TypeToken.get(runtimeTypeIfMoreSpecific));
            if ((typeAdapter instanceof Adapter) && !(this.delegate instanceof Adapter)) {
                typeAdapter = this.delegate;
            }
        }
        typeAdapter.write(jsonWriter, t);
    }

    private Type getRuntimeTypeIfMoreSpecific(Type type, Object obj) {
        if (obj == null) {
            return type;
        }
        if (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) {
            return obj.getClass();
        }
        return type;
    }
}
