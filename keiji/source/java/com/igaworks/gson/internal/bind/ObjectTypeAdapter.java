package com.igaworks.gson.internal.bind;

import com.igaworks.gson.Gson;
import com.igaworks.gson.TypeAdapter;
import com.igaworks.gson.TypeAdapterFactory;
import com.igaworks.gson.internal.LinkedTreeMap;
import com.igaworks.gson.reflect.TypeToken;
import com.igaworks.gson.stream.JsonReader;
import com.igaworks.gson.stream.JsonToken;
import com.igaworks.gson.stream.JsonWriter;
import java.util.ArrayList;
import java.util.List;
import jp.co.vaz.creator.hikaru2.R;
import twitter4j.TwitterResponse;

public final class ObjectTypeAdapter extends TypeAdapter<Object> {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$igaworks$gson$stream$JsonToken;
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() == Object.class) {
                return new ObjectTypeAdapter(gson);
            }
            return null;
        }
    };
    private final Gson gson;

    static /* synthetic */ int[] $SWITCH_TABLE$com$igaworks$gson$stream$JsonToken() {
        int[] iArr = $SWITCH_TABLE$com$igaworks$gson$stream$JsonToken;
        if (iArr == null) {
            iArr = new int[JsonToken.values().length];
            try {
                iArr[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[JsonToken.BEGIN_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[JsonToken.BOOLEAN.ordinal()] = 8;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[JsonToken.END_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[JsonToken.END_DOCUMENT.ordinal()] = 10;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[JsonToken.END_OBJECT.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[JsonToken.NAME.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[JsonToken.NULL.ordinal()] = 9;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[JsonToken.NUMBER.ordinal()] = 7;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[JsonToken.STRING.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            $SWITCH_TABLE$com$igaworks$gson$stream$JsonToken = iArr;
        }
        return iArr;
    }

    private ObjectTypeAdapter(Gson gson) {
        this.gson = gson;
    }

    public Object read(JsonReader jsonReader) {
        switch ($SWITCH_TABLE$com$igaworks$gson$stream$JsonToken()[jsonReader.peek().ordinal()]) {
            case TwitterResponse.READ /*1*/:
                List arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(read(jsonReader));
                }
                jsonReader.endArray();
                return arrayList;
            case TwitterResponse.READ_WRITE_DIRECTMESSAGES /*3*/:
                Object linkedTreeMap = new LinkedTreeMap();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    linkedTreeMap.put(jsonReader.nextName(), read(jsonReader));
                }
                jsonReader.endObject();
                return linkedTreeMap;
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsBackground /*6*/:
                return jsonReader.nextString();
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonTextAppearance /*7*/:
                return Double.valueOf(jsonReader.nextDouble());
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsButtonBackground /*8*/:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case R.styleable.WalletFragmentStyle_maskedWalletDetailsLogoTextColor /*9*/:
                jsonReader.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    public void write(JsonWriter jsonWriter, Object obj) {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        TypeAdapter adapter = this.gson.getAdapter(obj.getClass());
        if (adapter instanceof ObjectTypeAdapter) {
            jsonWriter.beginObject();
            jsonWriter.endObject();
            return;
        }
        adapter.write(jsonWriter, obj);
    }
}
