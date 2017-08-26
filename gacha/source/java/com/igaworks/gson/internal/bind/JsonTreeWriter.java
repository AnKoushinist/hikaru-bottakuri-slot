package com.igaworks.gson.internal.bind;

import com.igaworks.gson.JsonArray;
import com.igaworks.gson.JsonElement;
import com.igaworks.gson.JsonNull;
import com.igaworks.gson.JsonObject;
import com.igaworks.gson.JsonPrimitive;
import com.igaworks.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class JsonTreeWriter extends JsonWriter {
    private static final JsonPrimitive SENTINEL_CLOSED = new JsonPrimitive("closed");
    private static final Writer UNWRITABLE_WRITER = new Writer() {
        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }

        public void flush() {
            throw new AssertionError();
        }

        public void close() {
            throw new AssertionError();
        }
    };
    private String pendingName;
    private JsonElement product = JsonNull.INSTANCE;
    private final List<JsonElement> stack = new ArrayList();

    public JsonTreeWriter() {
        super(UNWRITABLE_WRITER);
    }

    public JsonElement get() {
        if (this.stack.isEmpty()) {
            return this.product;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.stack);
    }

    private JsonElement peek() {
        return (JsonElement) this.stack.get(this.stack.size() - 1);
    }

    private void put(JsonElement jsonElement) {
        if (this.pendingName != null) {
            if (!jsonElement.isJsonNull() || getSerializeNulls()) {
                ((JsonObject) peek()).add(this.pendingName, jsonElement);
            }
            this.pendingName = null;
        } else if (this.stack.isEmpty()) {
            this.product = jsonElement;
        } else {
            JsonElement peek = peek();
            if (peek instanceof JsonArray) {
                ((JsonArray) peek).add(jsonElement);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public JsonWriter beginArray() {
        JsonElement jsonArray = new JsonArray();
        put(jsonArray);
        this.stack.add(jsonArray);
        return this;
    }

    public JsonWriter endArray() {
        if (this.stack.isEmpty() || this.pendingName != null) {
            throw new IllegalStateException();
        } else if (peek() instanceof JsonArray) {
            this.stack.remove(this.stack.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public JsonWriter beginObject() {
        JsonElement jsonObject = new JsonObject();
        put(jsonObject);
        this.stack.add(jsonObject);
        return this;
    }

    public JsonWriter endObject() {
        if (this.stack.isEmpty() || this.pendingName != null) {
            throw new IllegalStateException();
        } else if (peek() instanceof JsonObject) {
            this.stack.remove(this.stack.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public JsonWriter name(String str) {
        if (this.stack.isEmpty() || this.pendingName != null) {
            throw new IllegalStateException();
        } else if (peek() instanceof JsonObject) {
            this.pendingName = str;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public JsonWriter value(String str) {
        if (str == null) {
            return nullValue();
        }
        put(new JsonPrimitive(str));
        return this;
    }

    public JsonWriter nullValue() {
        put(JsonNull.INSTANCE);
        return this;
    }

    public JsonWriter value(boolean z) {
        put(new JsonPrimitive(Boolean.valueOf(z)));
        return this;
    }

    public JsonWriter value(long j) {
        put(new JsonPrimitive(Long.valueOf(j)));
        return this;
    }

    public JsonWriter value(Number number) {
        if (number == null) {
            return nullValue();
        }
        if (!isLenient()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        put(new JsonPrimitive(number));
        return this;
    }

    public void flush() {
    }

    public void close() {
        if (this.stack.isEmpty()) {
            this.stack.add(SENTINEL_CLOSED);
            return;
        }
        throw new IOException("Incomplete document");
    }
}
