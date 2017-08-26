package com.igaworks.dao;

import android.content.Context;

public abstract class AbstractCPEImpressionDAO {
    public abstract void clearImpressionData(Context context);

    public abstract String getImpressionData(Context context, int i, String str, String str2);

    public abstract void increaseImpressionData(Context context, int i, String str, String str2);

    public abstract void setImpressionData(Context context, int i, String str, String str2, String str3);
}
