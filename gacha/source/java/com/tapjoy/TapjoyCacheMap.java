package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import java.io.File;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.cocos2dx.lib.BuildConfig;

public class TapjoyCacheMap extends ConcurrentHashMap {
    private Context a;
    private int b = -1;

    public TapjoyCacheMap(Context context, int i) {
        this.a = context;
        this.b = i;
    }

    private String a() {
        long j = -1;
        String str = BuildConfig.FLAVOR;
        for (Entry entry : entrySet()) {
            String str2;
            long j2;
            long timestampInSeconds = ((TapjoyCachedAssetData) entry.getValue()).getTimestampInSeconds();
            if (j == 0 || timestampInSeconds < j) {
                str2 = (String) entry.getKey();
                j2 = timestampInSeconds;
            } else {
                str2 = str;
                j2 = j;
            }
            j = j2;
            str = str2;
        }
        return str;
    }

    public TapjoyCachedAssetData put(String str, TapjoyCachedAssetData tapjoyCachedAssetData) {
        TapjoyLog.d("TapjoyCacheMap", "TapjoyCacheMap::put() -- key: " + str + " assetURL: " + tapjoyCachedAssetData.getAssetURL());
        if (tapjoyCachedAssetData == null || tapjoyCachedAssetData.getTimeOfDeathInSeconds() <= System.currentTimeMillis() / 1000) {
            return null;
        }
        if (size() == this.b) {
            remove(a());
        }
        Editor edit = this.a.getSharedPreferences(TapjoyConstants.PREF_TAPJOY_CACHE, 0).edit();
        edit.putString(tapjoyCachedAssetData.getLocalFilePath(), tapjoyCachedAssetData.toRawJSONString());
        edit.commit();
        return (TapjoyCachedAssetData) super.put(str, tapjoyCachedAssetData);
    }

    public TapjoyCachedAssetData remove(Object obj) {
        if (!containsKey(obj)) {
            return null;
        }
        Editor edit = this.a.getSharedPreferences(TapjoyConstants.PREF_TAPJOY_CACHE, 0).edit();
        edit.remove(((TapjoyCachedAssetData) get(obj)).getLocalFilePath());
        edit.commit();
        String localFilePath = ((TapjoyCachedAssetData) get(obj)).getLocalFilePath();
        if (localFilePath != null && localFilePath.length() > 0) {
            TapjoyUtil.deleteFileOrDirectory(new File(localFilePath));
        }
        TapjoyLog.d("TapjoyCacheMap", "TapjoyCacheMap::remove() -- key: " + obj);
        return (TapjoyCachedAssetData) super.remove(obj);
    }

    public boolean replace(String str, TapjoyCachedAssetData tapjoyCachedAssetData, TapjoyCachedAssetData tapjoyCachedAssetData2) {
        throw new UnsupportedOperationException();
    }

    public TapjoyCachedAssetData replace(String str, TapjoyCachedAssetData tapjoyCachedAssetData) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }
}
