package com.igaworks.gson;

import com.igaworks.gson.internal.LinkedTreeMap;
import java.util.Map.Entry;
import java.util.Set;

public final class JsonObject extends JsonElement {
    private final LinkedTreeMap<String, JsonElement> members = new LinkedTreeMap();

    public void add(String str, JsonElement jsonElement) {
        if (jsonElement == null) {
            jsonElement = JsonNull.INSTANCE;
        }
        this.members.put(str, jsonElement);
    }

    public Set<Entry<String, JsonElement>> entrySet() {
        return this.members.entrySet();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof JsonObject) && ((JsonObject) obj).members.equals(this.members));
    }

    public int hashCode() {
        return this.members.hashCode();
    }
}
