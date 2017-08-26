package com.vungle.publisher;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import com.vungle.log.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import twitter4j.TwitterResponse;

/* compiled from: vungle */
public abstract class dl<I> implements gr<I> {
    Class<I> s;
    protected I t;
    @Inject
    protected cf u;

    /* compiled from: vungle */
    public static abstract class a<T extends dl<I>, I> {
        @Inject
        protected cf d;

        protected abstract T a(T t, Cursor cursor);

        protected abstract T[] a(int i);

        protected abstract I[] b(int i);

        protected abstract String c();

        protected abstract T c_();

        protected a() {
        }

        public final int a(I... iArr) {
            int i = 0;
            int length = iArr == null ? 0 : iArr.length;
            if (length == 0) {
                Logger.d(Logger.DATABASE_TAG, "no " + c() + " records requested for delete");
                return 0;
            }
            int i2;
            c_();
            boolean z = iArr instanceof String[];
            String[] strArr = z ? (String[]) iArr : new String[length];
            if (!z) {
                int length2 = iArr.length;
                i2 = 0;
                while (i < length2) {
                    int i3 = i2 + 1;
                    strArr[i2] = String.valueOf(iArr[i]);
                    i++;
                    i2 = i3;
                }
            }
            i2 = this.d.getWritableDatabase().delete(c(), "id IN (" + cb.a(length) + ")", strArr);
            if (i2 == length) {
                Logger.d(Logger.DATABASE_TAG, "deleted " + i2 + " " + c() + " records by id in " + ags.b((Object[]) iArr));
                return i2;
            }
            Logger.w(Logger.DATABASE_TAG, "deleted " + i2 + " of " + length + " requested records by id in " + ags.b((Object[]) iArr));
            return i2;
        }

        public int a(List<T> list) {
            Object[] b;
            int i = 0;
            dl[] dlVarArr = list == null ? null : (dl[]) list.toArray(a(list.size()));
            if (dlVarArr != null) {
                b = b(dlVarArr.length);
                int length = dlVarArr.length;
                int i2 = 0;
                while (i2 < length) {
                    int i3 = i + 1;
                    b[i] = dlVarArr[i2].w();
                    i2++;
                    i = i3;
                }
            } else {
                b = null;
            }
            return a(b);
        }

        public List<T> d() {
            return a(null, null, null);
        }

        public List<T> a(String str, String[] strArr) {
            return a(str, strArr, null);
        }

        public List<T> c(int i) {
            return a(null, null, null, Integer.toString(i));
        }

        public final T a(I i) {
            dl c_ = c_();
            c_.a((Object) i);
            return a(c_);
        }

        final T a(T t) {
            if (t == null) {
                throw new IllegalArgumentException("null model");
            }
            String str = "id";
            Object w = t.w();
            if (w == null) {
                throw new IllegalArgumentException("null " + str);
            }
            StringBuilder append = new StringBuilder().append(str).append(" = ?");
            Iterable arrayList = new ArrayList();
            arrayList.add(String.valueOf(w));
            String stringBuilder = append.toString();
            List a = a(stringBuilder, (String[]) arrayList.toArray(new String[arrayList.size()]), null, null);
            int size = a.size();
            switch (size) {
                case TwitterResponse.NONE /*0*/:
                    return null;
                case TwitterResponse.READ /*1*/:
                    return (dl) a.get(0);
                default:
                    throw new SQLException(size + " " + c() + " records found for query: " + stringBuilder + ", parameters: " + ags.a(arrayList));
            }
        }

        protected final List<T> a(String str, String[] strArr, String str2) {
            return a(str, strArr, str2, null);
        }

        protected final List<T> a(String str, String[] strArr, String str2, String str3) {
            Throwable th;
            Cursor cursor;
            try {
                String c = c();
                Logger.d(Logger.DATABASE_TAG, "fetching " + (str == null ? "all " + c + " records" : c + " records by " + str + " " + ags.b((Object[]) strArr)));
                Cursor query = this.d.getReadableDatabase().query(c, null, str, strArr, null, null, str2, str3);
                try {
                    int count = query.getCount();
                    Logger.v(Logger.DATABASE_TAG, (count == 0 ? "no " : "fetched " + count + " ") + c + " records by " + str + " " + ags.b((Object[]) strArr));
                    List<T> a = a(query);
                    if (query != null) {
                        query.close();
                    }
                    return a;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }

        protected final boolean b(String str, String[] strArr) {
            boolean z = false;
            Cursor cursor = null;
            try {
                cursor = this.d.getWritableDatabase().rawQuery("SELECT EXISTS (SELECT 1 FROM " + c() + " WHERE " + str + " LIMIT 1)", strArr);
                if (cursor.moveToFirst() && cursor.getInt(0) != 0) {
                    z = true;
                }
                if (cursor != null) {
                    cursor.close();
                }
                return z;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        protected final int h() {
            Cursor cursor = null;
            int i = 0;
            try {
                cursor = this.d.getReadableDatabase().rawQuery("SELECT COUNT(*) FROM " + c(), null);
                if (cursor.moveToFirst()) {
                    i = cursor.getInt(0);
                }
                if (cursor != null) {
                    cursor.close();
                }
                return i;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        protected static void a(T... tArr) {
            if (tArr != null) {
                for (dl v : tArr) {
                    v.v();
                }
            }
        }

        private List<T> a(Cursor cursor) {
            List<T> arrayList = new ArrayList(cursor.getCount());
            while (cursor.moveToNext()) {
                arrayList.add(b(c_(), cursor));
            }
            return arrayList;
        }

        protected final T b(T t, Cursor cursor) {
            a((dl) t, cursor);
            Logger.v(Logger.DATABASE_TAG, "fetched " + t);
            return t;
        }
    }

    protected abstract ContentValues a(boolean z);

    protected abstract <T extends dl<I>> a<T, I> a_();

    protected abstract String c();

    static void a(StringBuilder stringBuilder, String str, Object obj, boolean z) {
        if (!z) {
            stringBuilder.append(", ");
        }
        stringBuilder.append(str).append(": ").append(obj);
    }

    public I w() {
        return this.t;
    }

    protected final void a(I i) {
        this.t = i;
    }

    protected boolean d_() {
        return true;
    }

    public I v() {
        Object w = w();
        if (!d_() || w == null) {
            Logger.d(Logger.DATABASE_TAG, "inserting " + this);
            cf cfVar = this.u;
            long insertOrThrow = cfVar.getWritableDatabase().insertOrThrow(c(), null, a(true));
            if (this.s == null || Integer.class.equals(this.s)) {
                this.t = Integer.valueOf((int) insertOrThrow);
            } else if (Long.class.equals(this.s)) {
                this.t = Long.valueOf(insertOrThrow);
            }
            Logger.v(Logger.DATABASE_TAG, "inserted " + this);
            return w();
        }
        throw new SQLException("attempt to insert with non-auto generated id " + z());
    }

    public final void x() {
        a_().a(this);
    }

    public final I y() {
        I w = w();
        if (w == null) {
            return v();
        }
        b_();
        return w;
    }

    public int b_() {
        String str = "id";
        Object w = w();
        if (w == null) {
            throw new IllegalArgumentException("null " + str);
        }
        String c = c();
        String str2 = str + " " + w;
        String[] strArr = new String[]{w.toString()};
        int updateWithOnConflict = this.u.getWritableDatabase().updateWithOnConflict(c, a(false), "id = ?", strArr, 3);
        switch (updateWithOnConflict) {
            case TwitterResponse.NONE /*0*/:
                Logger.d(Logger.DATABASE_TAG, "no " + c + " rows updated by " + str2);
                break;
            case TwitterResponse.READ /*1*/:
                Logger.d(Logger.DATABASE_TAG, "update successful " + z());
                break;
            default:
                Logger.w(Logger.DATABASE_TAG, "updated " + updateWithOnConflict + " " + c + " records for " + str2);
                break;
        }
        return updateWithOnConflict;
    }

    protected int n() {
        return a_().a(w());
    }

    public final String z() {
        return e_().append('}').toString();
    }

    protected StringBuilder e_() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{').append(A()).append(":: ");
        a(stringBuilder, "id", w(), true);
        return stringBuilder;
    }

    protected String A() {
        return c();
    }

    public String toString() {
        return m().append('}').toString();
    }

    protected StringBuilder m() {
        return e_();
    }
}
