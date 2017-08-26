package com.igaworks.commerce.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.igaworks.commerce.model.PurchaseItem;
import com.igaworks.core.IgawLogger;
import java.util.ArrayList;
import java.util.List;

public class PurchaseRetryDAO extends CommerceDB {
    private static PurchaseRetryDAO counterForAllActivityDao;

    public static PurchaseRetryDAO getDAO(Context context) {
        if (counterForAllActivityDao == null) {
            counterForAllActivityDao = new PurchaseRetryDAO(context);
        }
        if (counterForAllActivityDao.context == null) {
            counterForAllActivityDao.context = context;
        }
        return counterForAllActivityDao;
    }

    private PurchaseRetryDAO(Context context) {
        try {
            this.context = context;
            if (dbHelper == null) {
                dbHelper = new CommerceDBOpenHelper(this.context, "commerce.db", null, 1);
            }
            open();
        } catch (Exception e) {
        }
    }

    public List<PurchaseItem> getRetryPurchase() {
        Cursor cursor = null;
        List<PurchaseItem> arrayList = new ArrayList();
        try {
            Cursor query = this.db.query(true, "PurchaseRestore", new String[]{"_id", "order_id", "product_id", "product_name", "price", "quantity", "currency", "category", "create_at", "retry_count"}, null, null, null, null, null, null);
            try {
                if (query.getCount() == 0 || !query.moveToFirst()) {
                    if (!(query == null || query.isClosed())) {
                        query.close();
                    }
                    return null;
                }
                do {
                    arrayList.add(new PurchaseItem(query.getInt(0), query.getString(1), query.getString(2), query.getString(3), query.getDouble(4), query.getInt(5), query.getString(6), query.getString(7), query.getString(8), query.getInt(9)));
                } while (query.moveToNext());
                if (!(query == null || query.isClosed())) {
                    query.close();
                }
                return arrayList;
            } catch (Exception e) {
                cursor = query;
                cursor.close();
                return arrayList;
            }
        } catch (Exception e2) {
            if (!(cursor == null || cursor.isClosed())) {
                cursor.close();
            }
            return arrayList;
        }
    }

    public void updateOrInsertConversionForRetry(int i, String str, String str2, String str3, double d, int i2, String str4, String str5, String str6) {
        final int i3 = i;
        final String str7 = str;
        final String str8 = str2;
        final String str9 = str3;
        final double d2 = d;
        final int i4 = i2;
        final String str10 = str4;
        final String str11 = str5;
        final String str12 = str6;
        new Thread(new Runnable() {
            public void run() {
                Cursor query;
                try {
                    query = PurchaseRetryDAO.this.db.query(true, "PurchaseRestore", new String[]{"_id", "retry_count"}, "_id=" + i3, null, null, null, null, null);
                    try {
                        PurchaseRetryDAO.this.db.beginTransaction();
                        ContentValues contentValues;
                        if ((i3 <= -1 || query.getCount() != 0) && query.moveToFirst()) {
                            contentValues = new ContentValues();
                            contentValues.put("retry_count", Integer.valueOf(query.getInt(1) + 1));
                            if (PurchaseRetryDAO.this.db.update("PurchaseRestore", contentValues, "_id=" + i3, null) > 0) {
                            }
                            int i = query.getInt(1) + 1;
                        } else {
                            contentValues = new ContentValues();
                            contentValues.put("order_id", str7);
                            contentValues.put("product_id", str8);
                            contentValues.put("product_name", str9);
                            contentValues.put("price", Double.valueOf(d2));
                            contentValues.put("quantity", Integer.valueOf(i4));
                            contentValues.put("currency", str10);
                            contentValues.put("category", str11);
                            contentValues.put("create_at", str12);
                            contentValues.put("retry_count", Integer.valueOf(0));
                            IgawLogger.Logging(PurchaseRetryDAO.this.context, "IGAW_QA", String.format("add retry purchase : key = %d", new Object[]{Integer.valueOf(i3)}), 2);
                            PurchaseRetryDAO.this.db.insert("PurchaseRestore", null, contentValues);
                        }
                        PurchaseRetryDAO.this.db.setTransactionSuccessful();
                        PurchaseRetryDAO.this.db.endTransaction();
                        if (query != null && !query.isClosed()) {
                            query.close();
                        }
                    } catch (Exception e) {
                        if (query != null && !query.isClosed()) {
                            query.close();
                        }
                    }
                } catch (Exception e2) {
                    query = null;
                    if (query != null) {
                    }
                }
            }
        }).start();
    }

    public boolean removeRetryCount(int i) {
        try {
            IgawLogger.Logging(this.context, "IGAW_QA", "removeRetryCount key =  " + i, 2);
            if (this.db.delete("PurchaseRestore", "_id=" + i, null) > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clearRetryItems() {
        try {
            IgawLogger.Logging(this.context, "IGAW_QA", "Remove restore queue", 2);
            if (this.db.delete("PurchaseRestore", null, null) > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
