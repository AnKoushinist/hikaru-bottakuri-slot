package com.raizlabs.android.dbflow.structure.cache;

public interface IMultiKeyCacheConverter<CacheKeyType> {
    CacheKeyType getCachingKey(Object[] objArr);
}
