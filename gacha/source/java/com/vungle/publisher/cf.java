package com.vungle.publisher;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.vungle.log.Logger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public final class cf extends SQLiteOpenHelper {
    private static final String[] e = new String[]{"ad", "viewable", "archive_entry", "event_tracking", "ad_report", "ad_play", "ad_report_event", "ad_report_extra", "event_tracking_http_log", "logged_exceptions", "template_replacements", "errors"};
    @Inject
    qu a;
    @Inject
    ql b;
    @Inject
    pu c;
    @Inject
    public bt d;

    @Inject
    public cf(Context context) {
        super(context, "vungle", null, 10);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        Set hashSet = new HashSet(Arrays.asList(e));
        Map hashMap = new HashMap();
        Logger.d(Logger.DATABASE_TAG, "creating database: vungle");
        hashMap.put("ad", "CREATE TABLE ad (id STRING PRIMARY KEY, advertising_app_vungle_id INTEGER, call_to_action_final_url STRING, call_to_action_url STRING, delivery_id STRING NOT NULL, status STRING NOT NULL, type STRING NOT NULL, delete_local_content_attempts INTEGER, expiration_timestamp_seconds INTEGER, parent_path STRING, prepare_retry_count INTEGER, received_timestamp_millis INTEGER, html_content STRING, template_id STRING, insert_timestamp_millis INTEGER NOT NULL, update_timestamp_millis INTEGER NOT NULL, failed_timestamp_millis INTEGER NOT NULL);");
        hashMap.put("viewable", "CREATE TABLE viewable (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_id STRING NOT NULL REFERENCES ad(id) ON DELETE CASCADE ON UPDATE CASCADE, type STRING NOT NULL, ad_type STRING NOT NULL, url STRING NOT NULL, status STRING NOT NULL, size INTEGER, extension STRING, name STRING, width INTEGER, height INTEGER, show_close_delay_incentivized_seconds INTEGER, show_close_delay_interstitial_seconds INTEGER, show_countdown_delay_seconds INTEGER, cta_clickable_percent REAL, enable_cta_delay_seconds INTEGER, is_cta_enabled INTEGER, is_cta_shown_on_touch INTEGER, show_cta_delay_seconds INTEGER, checksum STRING);");
        hashMap.put("archive_entry", "CREATE TABLE archive_entry (id INTEGER PRIMARY KEY AUTOINCREMENT, viewable_id INTEGER NOT NULL REFERENCES viewable(id) ON DELETE CASCADE ON UPDATE CASCADE, relative_path STRING NOT NULL, size INTEGER, CONSTRAINT archive_entry_viewable_id_path_uk UNIQUE (id, relative_path));");
        hashMap.put("event_tracking", "CREATE TABLE event_tracking (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_id STRING NOT NULL REFERENCES ad(id) ON DELETE CASCADE ON UPDATE CASCADE, event INTEGER NOT NULL, url STRING NOT NULL);");
        hashMap.put("ad_report", "CREATE TABLE ad_report (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_id STRING NOT NULL REFERENCES ad(id) ON DELETE CASCADE ON UPDATE CASCADE, incentivized_publisher_app_user_id STRING, is_incentivized INTEGER NOT NULL, placement STRING, status STRING NOT NULL, video_duration_millis INTEGER, view_end_millis INTEGER, view_start_millis INTEGER, download_end_millis INTEGER, template_id STRING, insert_timestamp_millis INTEGER NOT NULL, update_timestamp_millis INTEGER NOT NULL);");
        hashMap.put("ad_play", "CREATE TABLE ad_play (id INTEGER PRIMARY KEY AUTOINCREMENT, report_id INTEGER NOT NULL REFERENCES ad_report(id) ON DELETE CASCADE ON UPDATE CASCADE, start_millis INTEGER, watched_millis INTEGER);");
        hashMap.put("ad_report_event", "CREATE TABLE ad_report_event (id INTEGER PRIMARY KEY AUTOINCREMENT, play_id INTEGER NOT NULL REFERENCES ad_play(id) ON DELETE CASCADE ON UPDATE CASCADE, event STRING NOT NULL, insert_timestamp_millis INTEGER NOT NULL, value STRING);");
        hashMap.put("ad_report_extra", "CREATE TABLE ad_report_extra (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_report_id INTEGER NOT NULL REFERENCES ad_report(id) ON DELETE CASCADE ON UPDATE CASCADE, name STRING, value STRING, CONSTRAINT ad_report_extra_id_name_uk UNIQUE (id, name));");
        hashMap.put("event_tracking_http_log", "CREATE TABLE event_tracking_http_log (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_id STRING NOT NULL, delivery_id STRING NOT NULL, event STRING NOT NULL, response_code INTEGER NOT NULL, response_timestamp_millis INTEGER, url STRING NOT NULL, insert_timestamp_millis INTEGER NOT NULL);");
        hashMap.put("logged_exceptions", "CREATE TABLE logged_exceptions (id INTEGER PRIMARY KEY AUTOINCREMENT, stack_trace STRING, tag STRING, log_message STRING, class STRING, type INTEGER NOT NULL, android_version STRING, sdk_version STRING NOT NULL, play_services_version STRING, insert_timestamp_millis INTEGER NOT NULL);");
        hashMap.put("template_replacements", "CREATE TABLE template_replacements (id INTEGER PRIMARY KEY AUTOINCREMENT, ad_id STRING NOT NULL REFERENCES ad(id) ON DELETE CASCADE ON UPDATE CASCADE, key STRING, value STRING);");
        hashMap.put("errors", "CREATE TABLE errors (id INTEGER PRIMARY KEY AUTOINCREMENT, report_id STRING NOT NULL REFERENCES ad_report(id) ON DELETE CASCADE ON UPDATE CASCADE, code STRING);");
        for (Entry key : hashMap.entrySet()) {
            if (!hashSet.contains((String) key.getKey())) {
                throw new RuntimeException("all created tables must be listed in the `ALL_TABLES` member");
            }
        }
        for (Entry entry : hashMap.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            Logger.d(Logger.DATABASE_TAG, "creating table: " + str);
            Logger.v(Logger.DATABASE_TAG, str2);
            sQLiteDatabase.execSQL(str2);
        }
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Logger.d(Logger.DATABASE_TAG, "downgrading databse version " + i + " --> " + i2);
        a(sQLiteDatabase);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Logger.d(Logger.DATABASE_TAG, "upgrading database version " + i + " --> " + i2);
        a(sQLiteDatabase);
    }

    public final Cursor a(hs hsVar) {
        return getReadableDatabase().query(hsVar.a, hsVar.b, hsVar.c, hsVar.d, hsVar.e, hsVar.f, hsVar.g, hsVar.h);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        for (String str : e) {
            Logger.d(Logger.DATABASE_TAG, "dropping table: " + str);
            String str2 = "DROP TABLE  IF EXISTS " + str2;
            Logger.v(Logger.DATABASE_TAG, str2);
            sQLiteDatabase.execSQL(str2);
        }
        qu quVar = this.a;
        Logger.d(Logger.FILE_TAG, "deleting ad temp directory");
        qu.a((String) quVar.a.get());
        onCreate(sQLiteDatabase);
    }

    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        Logger.d(Logger.DATABASE_TAG, "enabling foreign keys");
        sQLiteDatabase.execSQL("PRAGMA foreign_keys = true");
    }

    final void a(String... strArr) {
        String b = this.c.b();
        Logger.d(Logger.DATABASE_DUMP_TAG, b + " sdk version VungleDroid/4.0.3, database version 10");
        if (strArr == null || strArr.length <= 0) {
            Logger.d(Logger.DATABASE_DUMP_TAG, b + " dumping all tables");
            strArr = e;
        }
        SQLiteDatabase readableDatabase = getReadableDatabase();
        for (String str : r13) {
            Logger.i(Logger.DATABASE_DUMP_TAG, b + " dumping table " + str);
            Cursor query = readableDatabase.query(str, null, null, new String[0], null, null, null);
            StringBuilder stringBuilder = new StringBuilder();
            DatabaseUtils.dumpCursor(query, stringBuilder);
            Logger.d(Logger.DATABASE_DUMP_TAG, stringBuilder.toString());
        }
    }
}
