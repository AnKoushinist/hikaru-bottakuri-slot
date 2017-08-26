package com.raizlabs.android.dbflow.structure.provider;

import android.database.Cursor;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.structure.BaseModel;
import org.cocos2dx.lib.BuildConfig;

public abstract class BaseProviderModel<TProviderModel extends BaseProviderModel> extends BaseModel implements ModelProvider {
    public void delete() {
        ContentUtils.delete(getDeleteUri(), this);
    }

    public void save() {
        if (ContentUtils.update(getUpdateUri(), this) == 0) {
            ContentUtils.insert(getInsertUri(), this);
        }
    }

    public void update() {
        ContentUtils.update(getUpdateUri(), this);
    }

    public void insert() {
        ContentUtils.insert(getInsertUri(), this);
    }

    public boolean exists() {
        boolean z = false;
        Cursor query = ContentUtils.query(FlowManager.getContext().getContentResolver(), getQueryUri(), getModelAdapter().getPrimaryConditionClause(this), BuildConfig.FLAVOR, new String[0]);
        if (query != null && query.getCount() > 0) {
            z = true;
        }
        if (query != null) {
            query.close();
        }
        return z;
    }

    public void load(ConditionGroup conditionGroup, String str, String... strArr) {
        Cursor query = ContentUtils.query(FlowManager.getContext().getContentResolver(), getQueryUri(), conditionGroup, str, strArr);
        if (query != null && query.moveToFirst()) {
            getModelAdapter().loadFromCursor(query, this);
            query.close();
        }
    }

    public void load() {
        load(getModelAdapter().getPrimaryConditionClause(this), BuildConfig.FLAVOR, new String[0]);
    }
}
