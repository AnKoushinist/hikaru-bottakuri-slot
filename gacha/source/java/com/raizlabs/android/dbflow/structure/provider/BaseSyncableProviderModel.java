package com.raizlabs.android.dbflow.structure.provider;

import android.database.Cursor;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.structure.BaseModel;
import org.cocos2dx.lib.BuildConfig;

public abstract class BaseSyncableProviderModel extends BaseModel implements ModelProvider {
    public void insert() {
        super.insert();
        ContentUtils.insert(getInsertUri(), this);
    }

    public void save() {
        super.save();
        if (exists()) {
            ContentUtils.update(getUpdateUri(), this);
        } else {
            ContentUtils.insert(getInsertUri(), this);
        }
    }

    public void delete() {
        super.delete();
        ContentUtils.delete(getDeleteUri(), this);
    }

    public void update() {
        super.update();
        ContentUtils.update(getUpdateUri(), this);
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
