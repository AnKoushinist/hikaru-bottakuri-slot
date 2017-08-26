package com.raizlabs.android.dbflow.sql.language.property;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.queriable.ModelQueriable;
import com.raizlabs.android.dbflow.structure.Model;
import org.cocos2dx.lib.BuildConfig;

public class PropertyFactory {
    public static CharProperty from(char c) {
        return new CharProperty(null, NameAlias.rawBuilder("'" + c + "'").build());
    }

    public static IntProperty from(int i) {
        return new IntProperty(null, NameAlias.rawBuilder(i + BuildConfig.FLAVOR).build());
    }

    public static DoubleProperty from(double d) {
        return new DoubleProperty(null, NameAlias.rawBuilder(d + BuildConfig.FLAVOR).build());
    }

    public static LongProperty from(long j) {
        return new LongProperty(null, NameAlias.rawBuilder(j + BuildConfig.FLAVOR).build());
    }

    public static FloatProperty from(float f) {
        return new FloatProperty(null, NameAlias.rawBuilder(f + BuildConfig.FLAVOR).build());
    }

    public static ShortProperty from(short s) {
        return new ShortProperty(null, NameAlias.rawBuilder(s + BuildConfig.FLAVOR).build());
    }

    public static ByteProperty from(byte b) {
        return new ByteProperty(null, NameAlias.rawBuilder(b + BuildConfig.FLAVOR).build());
    }

    public static <T> Property<T> from(T t) {
        return new Property(null, NameAlias.rawBuilder(Condition.convertValueToString(t)).build());
    }

    public static <TModel extends Model> Property<TModel> from(ModelQueriable<TModel> modelQueriable) {
        return from(modelQueriable.getTable(), "(" + String.valueOf(modelQueriable.getQuery()).trim() + ")");
    }

    public static <T> Property<T> from(Class<T> cls, String str) {
        return new Property(null, NameAlias.rawBuilder(str).build());
    }
}
