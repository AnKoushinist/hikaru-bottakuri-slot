package com.raizlabs.android.dbflow.runtime;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.NameAlias.Builder;
import com.raizlabs.android.dbflow.sql.language.SQLCondition;
import com.raizlabs.android.dbflow.structure.BaseModel.Action;
import com.raizlabs.android.dbflow.structure.Model;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

public class FlowContentObserver extends ContentObserver {
    private static final AtomicInteger REGISTERED_COUNT = new AtomicInteger(0);
    private static boolean forceNotify = false;
    protected boolean isInTransaction = false;
    private final Set<OnModelStateChangedListener> modelChangeListeners = new CopyOnWriteArraySet();
    private final Set<Uri> notificationUris = new HashSet();
    private boolean notifyAllUris = false;
    private final Set<OnTableChangedListener> onTableChangedListeners = new CopyOnWriteArraySet();
    private final Map<String, Class<? extends Model>> registeredTables = new HashMap();
    private final Set<Uri> tableUris = new HashSet();

    public interface OnModelStateChangedListener {
        void onModelStateChanged(Class<? extends Model> cls, Action action, SQLCondition[] sQLConditionArr);
    }

    public interface OnTableChangedListener {
        void onTableChanged(Class<? extends Model> cls, Action action);
    }

    public static boolean shouldNotify() {
        return forceNotify || REGISTERED_COUNT.get() > 0;
    }

    public static void clearRegisteredObserverCount() {
        REGISTERED_COUNT.set(0);
    }

    public static void setShouldForceNotify(boolean z) {
        forceNotify = z;
    }

    public FlowContentObserver() {
        super(null);
    }

    public FlowContentObserver(Handler handler) {
        super(handler);
    }

    public void setNotifyAllUris(boolean z) {
        this.notifyAllUris = z;
    }

    public void beginTransaction() {
        if (!this.isInTransaction) {
            this.isInTransaction = true;
        }
    }

    public void endTransactionAndNotify() {
        if (this.isInTransaction) {
            this.isInTransaction = false;
            if (VERSION.SDK_INT < 16) {
                onChange(true);
                return;
            }
            synchronized (this.notificationUris) {
                for (Uri onChange : this.notificationUris) {
                    onChange(true, onChange, true);
                }
                this.notificationUris.clear();
            }
            synchronized (this.tableUris) {
                for (Uri onChange2 : this.tableUris) {
                    for (OnTableChangedListener onTableChanged : this.onTableChangedListeners) {
                        onTableChanged.onTableChanged((Class) this.registeredTables.get(onChange2.getAuthority()), Action.valueOf(onChange2.getFragment()));
                    }
                }
                this.tableUris.clear();
            }
        }
    }

    public void addModelChangeListener(OnModelStateChangedListener onModelStateChangedListener) {
        this.modelChangeListeners.add(onModelStateChangedListener);
    }

    public void removeModelChangeListener(OnModelStateChangedListener onModelStateChangedListener) {
        this.modelChangeListeners.remove(onModelStateChangedListener);
    }

    public void addOnTableChangedListener(OnTableChangedListener onTableChangedListener) {
        this.onTableChangedListeners.add(onTableChangedListener);
    }

    public void removeTableChangedListener(OnTableChangedListener onTableChangedListener) {
        this.onTableChangedListeners.remove(onTableChangedListener);
    }

    public void registerForContentChanges(Context context, Class<? extends Model> cls) {
        registerForContentChanges(context.getContentResolver(), (Class) cls);
    }

    public void registerForContentChanges(ContentResolver contentResolver, Class<? extends Model> cls) {
        contentResolver.registerContentObserver(SqlUtils.getNotificationUri(cls, null), true, this);
        REGISTERED_COUNT.incrementAndGet();
        if (!this.registeredTables.containsValue(cls)) {
            this.registeredTables.put(FlowManager.getTableName(cls), cls);
        }
    }

    public void unregisterForContentChanges(Context context) {
        context.getContentResolver().unregisterContentObserver(this);
        REGISTERED_COUNT.decrementAndGet();
        this.registeredTables.clear();
    }

    public void onChange(boolean z) {
        for (OnModelStateChangedListener onModelStateChanged : this.modelChangeListeners) {
            onModelStateChanged.onModelStateChanged(null, Action.CHANGE, new SQLCondition[0]);
        }
        for (OnTableChangedListener onTableChanged : this.onTableChangedListeners) {
            onTableChanged.onTableChanged(null, Action.CHANGE);
        }
    }

    @TargetApi(16)
    public void onChange(boolean z, Uri uri) {
        onChange(z, uri, false);
    }

    @TargetApi(16)
    private void onChange(boolean z, Uri uri, boolean z2) {
        String fragment = uri.getFragment();
        String authority = uri.getAuthority();
        Set<String> queryParameterNames = uri.getQueryParameterNames();
        SQLCondition[] sQLConditionArr = new SQLCondition[queryParameterNames.size()];
        if (!queryParameterNames.isEmpty()) {
            int i = 0;
            for (String str : queryParameterNames) {
                sQLConditionArr[i] = Condition.column(new Builder(Uri.decode(str)).build()).value(Uri.decode(uri.getQueryParameter(str)));
                i++;
            }
        }
        Class cls = (Class) this.registeredTables.get(authority);
        Action valueOf = Action.valueOf(fragment);
        if (this.isInTransaction) {
            Action action;
            if (this.notifyAllUris) {
                action = valueOf;
            } else {
                action = Action.CHANGE;
                uri = SqlUtils.getNotificationUri(cls, action);
            }
            synchronized (this.notificationUris) {
                this.notificationUris.add(uri);
            }
            synchronized (this.tableUris) {
                this.tableUris.add(SqlUtils.getNotificationUri(cls, action));
            }
            return;
        }
        if (valueOf != null) {
            for (OnModelStateChangedListener onModelStateChanged : this.modelChangeListeners) {
                onModelStateChanged.onModelStateChanged(cls, valueOf, sQLConditionArr);
            }
        }
        if (!z2) {
            for (OnTableChangedListener onTableChanged : this.onTableChangedListeners) {
                onTableChanged.onTableChanged(cls, valueOf);
            }
        }
    }
}
